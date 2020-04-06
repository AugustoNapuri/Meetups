/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service.impl;

import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.entities.UsuarioMeetup;
import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.repository.UsuarioMeetupRepository;
import com.santander.meetups.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author augus
 */
@Service
public class MeetupServiceImpl implements MeetupService{
    
    @Autowired
    private MeetupRepository repository;
    @Autowired
    private UsuarioMeetupRepository usuarioMeetupRepository;

    @Override
    public Meetup crear(Usuario usuario, Meetup meetup) {
        repository.save(meetup);
        return usuarioMeetupRepository.save(new UsuarioMeetup(usuario, meetup)).getMeetup();
    }
    
}
