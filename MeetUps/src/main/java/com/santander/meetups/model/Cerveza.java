/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.model;

import com.santander.meetups.entities.Meetup;

/**
 *
 * @author augus
 */
public class Cerveza {

    public static final Integer CANTIDAD_POR_CAJON = 6;
    private final Integer cervezas;
    private final Integer cajones;

    private Cerveza(Integer cervezas) {
        int cantCajones = cervezas / CANTIDAD_POR_CAJON;
        if (cervezas % CANTIDAD_POR_CAJON > 0) {
            cantCajones++;
        }
        this.cajones = cantCajones;
        this.cervezas = cantCajones * CANTIDAD_POR_CAJON;
    }

    public static Cerveza builder(Meetup meetup) {
        return new Cerveza(calcularCantidadCervezas(meetup));
    }

    public static Integer calcularCantidadCervezas(Meetup meetup) {
        if (meetup.getClima().getTemperaturaCelcius() < 20F) {
            Float cantidadCervezas = meetup.getUsuarioMeetup().size() * 0.75F;
            if (cantidadCervezas % 1 > 0) {
                return cantidadCervezas.intValue() + 1;
            } else {
                return cantidadCervezas.intValue();
            }
        } else if (meetup.getClima().getTemperaturaCelcius() >= 20F
                && meetup.getClima().getTemperaturaCelcius() <= 24F) {
            return meetup.getUsuarioMeetup().size();
        } else {
            return meetup.getUsuarioMeetup().size() * 2;
        }
    }

    public Integer getCervezas() {
        return cervezas;
    }

    public Integer getCajones() {
        return cajones;
    }

}
