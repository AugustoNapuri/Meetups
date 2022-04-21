/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.rest;

import com.santander.meetups.entities.Usuario;
import com.santander.meetups.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author augus
 */
@RestController
@RequestMapping(path = "api/usuario")
@CrossOrigin()
public class UsuarioRestController {
    
    @Autowired
    private UsuarioService service;
    
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return service.crear(usuario);
    }
    
    @GetMapping
    public List<Usuario> getAll() {
        return service.getAll();
    }
}
