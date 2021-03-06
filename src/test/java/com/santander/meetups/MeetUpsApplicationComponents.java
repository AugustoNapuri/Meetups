package com.santander.meetups;

import com.santander.meetups.repository.MeetupRepository;
import com.santander.meetups.repository.NotificacionRepository;
import com.santander.meetups.repository.UsuarioMeetupRepository;
import com.santander.meetups.repository.UsuarioRepository;
import com.santander.meetups.service.MeetupService;
import com.santander.meetups.service.NotificacionService;
import com.santander.meetups.service.UsuarioService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class MeetUpsApplicationComponents {
    
    @Autowired
    protected UsuarioService usuarioService;
    @Autowired
    protected MeetupService meetupService;
    @Autowired
    protected NotificacionService notificacionService;
    @Autowired
    protected UsuarioRepository usuarioRepository;
    @Autowired
    protected UsuarioMeetupRepository usuarioMeetupRepository;
    @Autowired
    protected MeetupRepository meetupRepository;
    @Autowired
    protected NotificacionRepository notificacionRepository;
}
