/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.rest;

import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.Notificacion;
import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.service.NotificacionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author augus
 */
@RestController
@RequestMapping(path = "api/notificacion")
@CrossOrigin()
public class NotificacionRestController {

    @Autowired
    private NotificacionService service;
    @Autowired
    private MeetupRepository meetupRepository;

    @GetMapping
    public List<Notificacion> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity notificar(
            @RequestParam(name = "meetupId") Long meetupId,
            @RequestBody String mensaje) {
        Optional<Meetup> meetup = meetupRepository.findById(meetupId);
        if (meetup.isPresent()) {
            service.notificarUsuarios(meetupId, mensaje);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
