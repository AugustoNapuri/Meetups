/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service.impl;

import com.santander.meetups.entities.Usuario;
import com.santander.meetups.repository.UsuarioRepository;
import com.santander.meetups.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author augus
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario crear(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

}
