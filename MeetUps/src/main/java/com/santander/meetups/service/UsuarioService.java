/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service;

import com.santander.meetups.entities.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author augus
 */
@Repository
public interface UsuarioService {
    
    Usuario crear(Usuario usuario);
    List<Usuario> getAll();
}
