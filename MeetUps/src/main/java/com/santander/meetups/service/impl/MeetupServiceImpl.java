/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service.impl;

import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.TipoUsuario;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.entities.UsuarioMeetup;
import com.santander.meetups.entities.UsuarioMeetupKey;
import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.repository.UsuarioMeetupRepository;
import com.santander.meetups.service.MeetupService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Override
    public Meetup crear(Usuario admin, Meetup meetup) {
        if (admin.getTipoUsuario().equals(TipoUsuario.INVITADO)) {
            throw new UnsupportedOperationException("agregar spring security");
        }
        meetup = repository.save(meetup);
        return inscribir(admin, meetup.getId());
    }
    
    @Override
    public Meetup inscribir(Usuario usuario, Long meetupId) {
        Meetup meetup = repository.getOne(meetupId);
        return repository.findById(
                usuarioMeetupRepository
                        .save(new UsuarioMeetup(usuario, meetup))
                        .getMeetup()
                        .getId())
                .get();
    }
    
    @Override
    @Transactional
    public void checkIn(UsuarioMeetupKey key) {
        usuarioMeetupRepository
                .getOne(key)
                .setFechaCheckIn(LocalDateTime.now());
    }

    @Override
    public void invitar(Usuario admin, List<Usuario> usuarios, Long meetupId) {
        if (admin.getTipoUsuario().equals(TipoUsuario.INVITADO)) {
            throw new UnsupportedOperationException("agregar spring security");
        }
        usuarios.forEach((usuario) -> {
            inscribir(usuario, meetupId);
        });
    }
    
    
}
