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
public class Clima {

    public final static Float KELVIN = 273.15F;
    private final Float temperaturaKelvin;
    private final Float temperaturaCelcius;

    public Clima() {
        this.temperaturaKelvin = 0F;
        this.temperaturaCelcius = 0F;
    }

    public Clima(Float temperaturaEnKelvin) {
        this.temperaturaKelvin = temperaturaEnKelvin;
        this.temperaturaCelcius = temperaturaEnKelvin - KELVIN;
    }

    public Float getTemperaturaKelvin() {
        return temperaturaKelvin;
    }

    public Float getTemperaturaCelcius() {
        return temperaturaCelcius;
    }

}
