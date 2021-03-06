/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.TipoUsuario;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.entities.UsuarioMeetup;
import com.santander.meetups.entities.UsuarioMeetupKey;
import com.santander.meetups.exceptions.ClimaException;
import com.santander.meetups.model.Cerveza;
import com.santander.meetups.model.Clima;
import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.repository.UsuarioMeetupRepository;
import com.santander.meetups.service.MeetupService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author augus
 */
@Service
public class MeetupServiceImpl implements MeetupService {

    @Autowired
    private MeetupRepository repository;
    @Autowired
    private UsuarioMeetupRepository usuarioMeetupRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final static String URL_CLIMA = "https://community-open-weather-map.p.rapidapi.com/forecast/daily";
    private final static String HOST = "community-open-weather-map.p.rapidapi.com";
    private final static int CANTIDAD_DIAS_CLIMA = 7;
    private final static String LOCALIDAD = "Buenos Aires";
    @Value("${weather.key}")
    private String weatherKey;

    @Override
    public Meetup crear(Usuario admin, Meetup meetup) {
        if (admin.getTipoUsuario().equals(TipoUsuario.INVITADO)) {
            throw new SecurityException("No tienes permitido crear Meetups");
        }
        meetup = repository.save(meetup);
        return inscribir(admin, meetup);
    }

    @Override
    public Meetup inscribir(Usuario usuario, Meetup meetup) {
        return repository.getOne(
                usuarioMeetupRepository
                        .save(new UsuarioMeetup(usuario, meetup))
                        .getMeetup()
                        .getId());
    }

    @Override
    @Transactional
    public void checkIn(UsuarioMeetupKey key) {
        usuarioMeetupRepository
                .getOne(key)
                .setFechaCheckIn(LocalDateTime.now());
    }

    @Override
    @Transactional
    public Meetup invitar(Usuario admin, List<Long> usuariosId, Meetup meetup) {
        if (admin.getTipoUsuario().equals(TipoUsuario.INVITADO)) {
            throw new SecurityException("No tienes permitido invitar usuarios");
        }

        return usuariosId.stream()
                .map(m -> inscribir(new Usuario(m), meetup))
                .reduce((first, second) -> second)
                .get();
    }

    @Override
    public Meetup infoCervezas(Meetup meetup, Usuario usuario) throws SecurityException{
        if (!usuario.getTipoUsuario().equals(TipoUsuario.ADMIN)) {
            throw new SecurityException("No puede acceder a este recurso");
        }
        try {
            meetup = infoClima(meetup);
            meetup.setCerveza(Cerveza.builder(meetup));
        } catch (ClimaException ex) {
            meetup.setCerveza(Cerveza.defaultBuilder(meetup));
        }
        return meetup;
    }

    @Override
    public List<Meetup> getAll() {
        return repository.findAll();
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorClima", ignoreExceptions = {ClimaException.class}, commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public Meetup infoClima(Meetup meetup) throws ClimaException {
        if (LocalDateTime.now().plusDays(CANTIDAD_DIAS_CLIMA - 1).isBefore(meetup.getFechaInicio())) {
            throw new ClimaException("No se puede obtener con precision el clima para esa fecha");
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL_CLIMA)
                .queryParam("q", LOCALIDAD)
                .queryParam("cnt", CANTIDAD_DIAS_CLIMA);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", weatherKey);
        headers.set("x-rapidapi-host", HOST);

        ResponseEntity<String> response
                = restTemplate.exchange(uriBuilder.build().toUriString(), HttpMethod.GET, new HttpEntity(headers), String.class);

        JsonObject json = new JsonParser().parse(response.getBody()).getAsJsonObject();
        JsonArray jsonArray = json.get("list").getAsJsonArray();
        Clima clima;
        for (int i = 0; i < CANTIDAD_DIAS_CLIMA; i++) {
            if (LocalDateTime.ofInstant(Instant.ofEpochSecond(
                    jsonArray.get(i)
                            .getAsJsonObject()
                            .get("dt")
                            .getAsLong()), ZoneId.systemDefault())
                    .getDayOfYear() == meetup.getFechaInicio().getDayOfYear()) {
                clima = new Clima(jsonArray.get(i)
                        .getAsJsonObject()
                        .get("temp")
                        .getAsJsonObject()
                        .get("day")
                        .getAsFloat());
                meetup.setClima(clima);
            }
        }
        return meetup;
    }

    public Meetup errorClima(Meetup meetup) throws ClimaException {
        throw new ClimaException("El servicio no esta available");
    }
}
