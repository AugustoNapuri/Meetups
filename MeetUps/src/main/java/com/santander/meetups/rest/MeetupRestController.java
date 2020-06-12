/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.rest;

import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.entities.UsuarioMeetupKey;
import com.santander.meetups.exceptions.ClimaException;
import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.repository.UsuarioRepository;
import com.santander.meetups.service.MeetupService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author augus
 */
@RestController
@RequestMapping(path = "api/meetup")
@CrossOrigin()
public class MeetupRestController {

    @Autowired
    private MeetupService service;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MeetupRepository repository;

    //For testing purposes
    @GetMapping
    public List<Meetup> getAll() {
        return service.getAll();
    }

    //Returns the Meetup filled with the weather of the event day
    @GetMapping(path = "/{id}/clima")
    public ResponseEntity infoClima(
            @PathVariable Long id) {
        Optional<Meetup> meetup = repository.findById(id);
        if (!meetup.isPresent()) {
            return new ResponseEntity("Meetup not found", HttpStatus.NOT_FOUND);
        }
        try {
            return new ResponseEntity(service.infoClima(repository.getOne(id)), HttpStatus.OK);
        } catch (ClimaException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    //Fills the meetup with the details of the Cerveza neeeded.
    @GetMapping(path = "/{id}/cervezas")
    public ResponseEntity infoCervezas(
            @PathVariable Long id,
            @RequestParam(name = "userId") Long userId) {

        Optional<Meetup> meetup = repository.findById(id);
        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if (!meetup.isPresent()) {
            return new ResponseEntity("Meetup not found", HttpStatus.NOT_FOUND);
        }
        if (!usuario.isPresent()) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(service.infoCervezas(meetup.get(), usuario.get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Meetup> create(
            @RequestParam(name = "user") Long admin,
            @RequestBody Meetup meetup) {
        try {
            return new ResponseEntity<>(service.crear(usuarioRepository.getOne(admin), meetup), HttpStatus.CREATED);
        } catch (UnsupportedOperationException uoe) {
            return new ResponseEntity(uoe.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(path = "/{id}/checkin")
    public ResponseEntity checkIn(
            @PathVariable(name = "id") Long meetupId,
            @RequestParam(name = "userId") Long userId) {

        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        Optional<Meetup> meetup = repository.findById(meetupId);
        if (!meetup.isPresent()) {
            return new ResponseEntity("Meetup not found", HttpStatus.NOT_FOUND);
        }
        if (!usuario.isPresent()) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        service.checkIn(new UsuarioMeetupKey(userId, meetupId));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/admin/{userId}/users")
    public ResponseEntity invite(
            @RequestBody List<Long> usuariosId,
            @PathVariable(name = "id") Long meetupId,
            @PathVariable(name = "userId") Long userId) {

        Optional<Meetup> meetup = repository.findById(meetupId);
        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if (!meetup.isPresent()) {
            return new ResponseEntity("Meetup not found", HttpStatus.NOT_FOUND);
        }
        if (!usuario.isPresent()) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        try {
            service.invitar(usuario.get(), usuariosId, meetup.get());
            return new ResponseEntity(HttpStatus.OK);
        } catch (UnsupportedOperationException uoe) {
            return new ResponseEntity(uoe.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(path = "/{id}/users")
    public ResponseEntity signOn(
            @PathVariable(name = "id") Long meetupId,
            @RequestParam(name = "userId") Long userId) {

        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        Optional<Meetup> meetup = repository.findById(meetupId);
        if (!meetup.isPresent()) {
            return new ResponseEntity("Meetup not found", HttpStatus.NOT_FOUND);
        }
        if (!usuario.isPresent()) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(service.inscribir(usuario.get(), meetup.get()), HttpStatus.OK);
    }

}
