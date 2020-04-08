/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service.impl;

import com.santander.meetups.entities.Notificacion;
import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.repository.NotificacionRepository;
import com.santander.meetups.service.NotificacionService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author augus
 */
@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository repository;
    @Autowired
    private MeetupRepository meetupRepository;

    @Override
    public void notificarUsuarios(Long meetupId, String mensaje) {
        repository.saveAll(
                meetupRepository.findById(meetupId).get()
                        .getUsuarioMeetup()
                        .stream()
                        .map((um) -> {
                            return new Notificacion(um.getUsuario(), um.getMeetup(), mensaje);
                        })
                        .collect(Collectors.toList())
        );
    }

}
