/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author augus
 */
@Embeddable
public class UsuarioMeetupKey implements Serializable{
    
    @Column(name = "usuario_id")
    private Long usuarioId;
    @Column(name = "meetup_id")
    private Long meetupId;

    protected UsuarioMeetupKey() {
    }

    public UsuarioMeetupKey(Long usuarioId, Long meetupId) {
        this.usuarioId = usuarioId;
        this.meetupId = meetupId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getMeetupId() {
        return meetupId;
    }

    public void setMeetupId(Long meetupId) {
        this.meetupId = meetupId;
    }
    
}
