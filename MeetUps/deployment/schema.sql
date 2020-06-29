/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  augus
 * Created: 20/02/2019
 */
DROP TABLE IF EXISTS Notificacion;
DROP TABLE IF EXISTS UsuarioMeetup;
DROP TABLE IF EXISTS Meetup;
DROP TABLE IF EXISTS Usuario;

CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipoUsuario VARCHAR(30) NOT NULL
);

CREATE TABLE Meetup (
    id SERIAL PRIMARY KEY,
    fechaInicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fechaFin TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Notificacion (
    id SERIAL PRIMARY KEY,
    usuarioId INTEGER NOT NULL,
    meetupId INTEGER NOT NULL,
    mensaje VARCHAR(300) NOT NULL,
    fechaLeido TIMESTAMP DEFAULT NULL
);

CREATE TABLE UsuarioMeetup (
    usuarioId INTEGER NOT NULL,
    meetupId INTEGER NOT NULL,
    fechaCheckIn TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (usuarioId, meetupId)
);

