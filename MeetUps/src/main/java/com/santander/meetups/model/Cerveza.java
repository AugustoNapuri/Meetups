/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santander.meetups.model;

/**
 *
 * @author augus
 */
public class Cerveza {
    
    public static final Integer CANTIDAD_POR_CAJON = 6;
    public final Integer cervezas;
    public final Integer cajones;

    public Cerveza(Integer cervezas) {
        int cantCajones = cervezas / CANTIDAD_POR_CAJON;
        if ( cervezas % CANTIDAD_POR_CAJON > 0 ) {
            cantCajones++;
        }
        this.cajones = cantCajones;
        this.cervezas = cantCajones * CANTIDAD_POR_CAJON;
    }

    public Integer getCervezas() {
        return cervezas;
    }

    public Integer getCajones() {
        return cajones;
    }
    
}
