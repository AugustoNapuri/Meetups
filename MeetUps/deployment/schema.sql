/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  augus
 * Created: 20/02/2019
 */
DROP TABLE IF EXISTS notificacion;
DROP TABLE IF EXISTS usuario_meetup;
DROP TABLE IF EXISTS meetup;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo_usuario VARCHAR(30) NOT NULL
);

CREATE TABLE meetup (
    id SERIAL PRIMARY KEY,
    fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_fin TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE notificacion (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    meetup_id INTEGER NOT NULL,
    mensaje VARCHAR(300) NOT NULL,
    fecha_leido TIMESTAMP DEFAULT NULL
);

CREATE TABLE usuario_meetup (
    usuario_id INTEGER NOT NULL,
    meetup_id INTEGER NOT NULL,
    fecha_check_in TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (usuario_id, meetup_id)
);

