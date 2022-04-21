/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.service;

import com.santander.meetups.MeetUpsApplicationComponents;
import com.santander.meetups.entities.TipoUsuario;
import com.santander.meetups.entities.Usuario;
import com.santander.meetups.rest.MeetupRestController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 *
 * @author augus
 */
public class MeetupServiceInt extends MeetUpsApplicationComponents {

    private MockMvc mockMvc;
    @Autowired
    private MeetupRestController meetupRestController;

    @Before
    public void init() {
        mockMvc = standaloneSetup(meetupRestController).build();
        assertThat(usuarioService.crear(new Usuario("admin", TipoUsuario.ADMIN)).getId().equals(1));
        assertThat(usuarioService.crear(new Usuario("invitado", TipoUsuario.INVITADO)).getId().equals(2));
        assertThat(usuarioService.crear(new Usuario("invitado2", TipoUsuario.INVITADO)).getId().equals(3));
    }

    @Test
    public void meetupFlow() throws Exception {
        //CREATE MEETUP
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/meetup?user=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fechaFin\": \"2020-04-11T03:35:01.603Z\", \"fechaInicio\": \"2020-04-11T03:35:01.604Z\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
        
        //SUBSCRIBE
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/meetup/1/users?userId=2")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.usuarioMeetup", hasSize(2)));

        //INVITE
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/meetup/1/admin/1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1, 2, 3]")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.usuarioMeetup", hasSize(3)));
        //CHECK IN
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/meetup/1/checkin?userId=2")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
       
        assertThat(meetupRepository.findById(1L).get().getUsuarioMeetup()
                .stream().filter(um -> um.getId().getUsuarioId().equals(2L)).findFirst()
                .get().getFechaCheckIn());
        
    }
}
