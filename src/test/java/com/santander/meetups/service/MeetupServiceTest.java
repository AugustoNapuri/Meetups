/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service;

import com.santander.meetups.MeetUpsApplicationComponents;
import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.TipoUsuario;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.entities.UsuarioMeetupKey;
import com.santander.meetups.exceptions.ClimaException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author augus
 */
public class MeetupServiceTest extends MeetUpsApplicationComponents{
    
    @Test()
    public void crearMeetup_admin_ok() {
        Usuario admin = usuarioRepository.save(new Usuario("augusto", TipoUsuario.ADMIN));
        Meetup meetup = meetupService.crear(admin, new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        assertEquals(1, meetup.getUsuarioMeetup().size());
    }
    @Test(expected = SecurityException.class)
    public void crearMeetup_conInvitado_error() {
        Usuario invitado = new Usuario("invitado");
        meetupService.crear(invitado, new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
    }
    
    @Test
    public void inscribirse_ok() {
        Usuario usuario = usuarioRepository.save(new Usuario("usuario"));
        Meetup meetup = meetupRepository.save(new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        meetup = meetupService.inscribir(usuario, meetup);
        assertEquals(usuario.getId(), meetup.getUsuarioMeetup().get(0).getUsuario().getId());
    }
    
    @Test
    public void checkIn_ok() {
        Usuario usuario = usuarioRepository.save(new Usuario("usuario"));
        Meetup meetup = meetupRepository.save(new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        meetup = meetupService.inscribir(usuario, meetup);
        
        meetupService.checkIn(new UsuarioMeetupKey(usuario.getId(), meetup.getId()));
        usuario = usuarioRepository.getOne(usuario.getId());
        
        assertNotNull(usuario.getUsuarioMeetup().get(0).getFechaCheckIn());
    }
    
    @Test
    public void invitar_ok() {
        Usuario admin = usuarioRepository.save(new Usuario("admin", TipoUsuario.ADMIN));
        Usuario invitado1 = usuarioRepository.save(new Usuario("invitado1"));
        Usuario invitado2 = usuarioRepository.save(new Usuario("invitado2"));
        List<Long> ids = new ArrayList();
        ids.add(invitado1.getId());
        ids.add(invitado2.getId());
        
        Meetup meetup = meetupService.crear(admin, new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        meetupService.invitar(admin, ids, meetup);
        meetup = meetupRepository.findById(meetup.getId()).get();
        
        assertEquals(3, meetup.getUsuarioMeetup().size());
    }
    
    @Test
    public void infoActualizada_siendoInvitado_vuelveClimaNoNulo() throws ClimaException {
        Usuario admin = usuarioRepository.save(new Usuario("admin", TipoUsuario.ADMIN));
        Usuario invitado = usuarioRepository.save(new Usuario("invitado"));
        Meetup meetup = meetupService.crear(admin, new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        meetup = meetupService.infoClima(meetup);
        
        assertNotNull(meetup.getClima());
        assertTrue(meetup.getClima().getTemperaturaKelvin() > 0F);
    }
    
    @Test(expected = ClimaException.class)
    public void infoActualizada_paraMeetupEn8Dias_error() throws ClimaException {
        Usuario admin = usuarioRepository.save(new Usuario("admin", TipoUsuario.ADMIN));
        Meetup meetup = meetupService.crear(admin, new Meetup(LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(8)));
        meetupService.infoClima(meetup);
    }
    
    @Test
    public void infoCervezas_siendoAdmin_devuelveCervezas() throws ClimaException {
        Usuario admin = usuarioRepository.save(new Usuario("admin", TipoUsuario.ADMIN));
        Meetup meetup = meetupService.crear(admin, new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        meetup = meetupService.infoCervezas(meetup, admin);
        
        assertNotNull(meetup.getCerveza());
        assertNotNull(meetup.getCerveza().getCervezas());
    }
    
}
