/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service;

import com.santander.meetups.entities.Notificacion;
import java.util.List;

/**
 *
 * @author augus
 */
public interface NotificacionService {
    
    void notificarUsuarios(Long meetupId, String mensaje);
    List<Notificacion> getAll();
}
