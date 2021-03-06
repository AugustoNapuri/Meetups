/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author augus
 */
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private final String nombre;
    @Enumerated(EnumType.STRING)
    private final TipoUsuario tipoUsuario;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Notificacion> notificaciones;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<UsuarioMeetup> usuarioMeetup;

    public Usuario(Long id) {
        this.id = id;
        this.nombre = "";
        this.tipoUsuario = TipoUsuario.INVITADO;
    }
    
    protected Usuario() {
        this.nombre = "";
        this.tipoUsuario = TipoUsuario.INVITADO;
    }
    
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.tipoUsuario = TipoUsuario.INVITADO;
    }

    public Usuario(String nombre, TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public List<UsuarioMeetup> getUsuarioMeetup() {
        return usuarioMeetup;
    }

    public void setUsuarioMeetup(List<UsuarioMeetup> usuarioMeetup) {
        this.usuarioMeetup = usuarioMeetup;
    }

}
