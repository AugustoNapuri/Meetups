/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author augus
 */
@Entity
public class UsuarioMeetup implements Serializable{
    
    @EmbeddedId
    private UsuarioMeetupKey id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    @MapsId(value = "usuario_id")
    @JoinColumn(name = "usuario_id",  insertable=false, updatable=false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @MapsId(value = "meetup_id")
    @JoinColumn(name = "meetup_id")
    private Meetup meetup;        
    private LocalDateTime fechaCheckIn;

    protected UsuarioMeetup() {
    }
    
    public UsuarioMeetup(Usuario usuario, Meetup meetup) {
        this.id = new UsuarioMeetupKey(usuario.getId(), meetup.getId());
        this.usuario = usuario;
        this.meetup = meetup;
    }

    public UsuarioMeetupKey getId() {
        return id;
    }

    public void setId(UsuarioMeetupKey id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Meetup getMeetup() {
        return meetup;
    }

    public void setMeetup(Meetup meetup) {
        this.meetup = meetup;
    }

    public LocalDateTime getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(LocalDateTime fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }
    
}
