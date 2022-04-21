/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.repository;

import com.santander.meetups.entities.UsuarioMeetup;
import com.santander.meetups.entities.UsuarioMeetupKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author augus
 */
@Repository
public interface UsuarioMeetupRepository extends JpaRepository<UsuarioMeetup, UsuarioMeetupKey>{
    
}
