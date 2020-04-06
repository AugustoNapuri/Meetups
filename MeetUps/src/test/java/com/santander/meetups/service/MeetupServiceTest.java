/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service;

import com.santander.meetups.MeetUpsApplicationTests;
import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.repository.UsuarioMeetupRepository;
import com.santander.meetups.repository.UsuarioRepository;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author augus
 */
public class MeetupServiceTest extends MeetUpsApplicationTests{
    
    @Autowired
    private MeetupService meetupService;
    @Autowired
    private MeetupRepository meetupRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMeetupRepository usuarioMeetupRepository;
    @Test()
    public void crearMeetup_admin_ok() {
        Usuario admin = new Usuario("augusto");
        usuarioRepository.save(admin);
        meetupService.crear(admin, new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        assertEquals(1, meetupRepository.count());
        assertEquals(1, usuarioMeetupRepository.count());
        assertEquals(1, usuarioRepository.count());
    }
//    @Test()
//    public void crearMeetup_conInvitado_error() {
//        Usuario invitado = new Usuario("invitado");
//        meetupService.crear(invitado, new Meetup(LocalDateTime.MIN, LocalDateTime.MAX));
//    }
}
