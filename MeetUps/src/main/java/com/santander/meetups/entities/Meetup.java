/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.entities;

import com.santander.meetups.model.Clima;
import com.santander.meetups.model.Cerveza;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author augus
 */
@Entity
public class Meetup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "meetup",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UsuarioMeetup> usuarioMeetup;
    @OneToMany(mappedBy = "meetup", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Notificacion> notificaciones;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    @Transient
    private Clima clima;
    @Transient
    private Cerveza cerveza;

    protected Meetup() {
    }

    public Meetup(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addUsuario(Usuario usuario) {
        if (getUsuarioMeetup() == null) {
            this.usuarioMeetup = new ArrayList<>();
        }
        getUsuarioMeetup().add(new UsuarioMeetup(usuario, this));
    }
    public List<UsuarioMeetup> getUsuarioMeetup() {
        return usuarioMeetup;
    }

    public void setUsuarioMeetup(List<UsuarioMeetup> usuarioMeetup) {
        this.usuarioMeetup = usuarioMeetup;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    public Cerveza getCerveza() {
        return cerveza;
    }

    public void setCerveza(Cerveza cerveza) {
        this.cerveza = cerveza;
    }

    @Override
    public String toString() {
        return "Meetup{" + "id=" + id + ", usuarioMeetup=" + usuarioMeetup + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", clima=" + clima + ", cerveza=" + cerveza + '}';
    }

}
