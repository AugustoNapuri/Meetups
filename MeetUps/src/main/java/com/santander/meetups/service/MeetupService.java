/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service;

import com.santander.meetups.entities.Meetup;
import com.santander.meetups.entities.Usuario;

/**
 *
 * @author augus
 */
public interface MeetupService {
    
    Meetup crear(Usuario usuario, Meetup meetup);
}
