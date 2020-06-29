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
    tipo_usuario VARCHAR(30) NOT NULL
);

CREATE TABLE Meetup (
    id SERIAL PRIMARY KEY,
    fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_fin TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Notificacion (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    meetup_id INTEGER NOT NULL,
    mensaje VARCHAR(300) NOT NULL,
    fecha_leido TIMESTAMP DEFAULT NULL
);

CREATE TABLE UsuarioMeetup (
    usuario_id INTEGER NOT NULL,
    meetup_id INTEGER NOT NULL,
    fecha_check_in TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (usuario_id, meetup_id)
);

