/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service;

import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.entities.UsuarioMeetupKey;
import com.santander.meetups.exceptions.ClimaException;
import java.util.List;

/**
 *
 * @author augus
 */
public interface MeetupService {
    
    Meetup crear(Usuario admin, Meetup meetup);
    Meetup inscribir(Usuario usuario, Meetup meetup);
    void invitar(Usuario admin, List<Usuario> usuarios, Meetup meetup);
    void checkIn(UsuarioMeetupKey usuarioMeetupKey);
    Meetup infoClima(Meetup meetup) throws ClimaException;
    Meetup infoCervezas(Meetup meetup);
}
