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
import com.santander.meetups.entities.UsuarioMeetup;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author augus
 */
public class NotificacionServiceTest extends MeetUpsApplicationComponents {

    @Test
    public void notificarUsuarios_3usuarios_ok() {
        Usuario admin = usuarioRepository.save(new Usuario("admin", TipoUsuario.ADMIN));
        Usuario invitado1 = usuarioRepository.save(new Usuario("invitado1", TipoUsuario.INVITADO));
        Usuario invitado2 = usuarioRepository.save(new Usuario("invitado2", TipoUsuario.INVITADO));
        List<Long> ids = new ArrayList();
        ids.add(invitado1.getId());
        ids.add(invitado2.getId());
        String mensaje = "Nueva notificacion para el evento";

        Meetup meetup = meetupService.crear(admin, new Meetup(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        meetupService.invitar(admin, ids, meetup);
        notificacionService.notificarUsuarios(meetup.getId(), mensaje);
        meetup = meetupRepository.getOne(meetup.getId());

        assertEquals(3, notificacionRepository.count());
        meetup.getUsuarioMeetup()
                .stream()
                .map(UsuarioMeetup::getUsuario)
                .collect(Collectors.toList())
                .forEach((usuario) -> {
                    assertEquals(mensaje, usuario.getNotificaciones().get(0).getMensaje());
                });
    }
}
