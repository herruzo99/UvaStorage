/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.entrega1rebhernjuaherrModelo;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class Bombilla {

    private boolean encendido;
    private int intensidad;
    private float color;
    private float saturacion;
    private float brillo;

    public Bombilla() {
        encendido = false;
        intensidad = 100;
        color = 0;
        saturacion = 0;
        brillo = 0.5f;
    }
    public float getColor(){
        return color;
    }
    public void setColor(float c){
        color = c;
    }
    public float getSaturacion(){
        return saturacion;
    }
    public void setSaturacion(float s){
        saturacion = s;
    }
    public float getBrillo(){
        return brillo;
    }
    public void setBrillo(float b){
        brillo = b;
    }
    public int getIntensidad(){
        return intensidad;
    }
    public void setIntensidad(int i){
        intensidad = i;
    }
    public boolean getEncendido(){
        return encendido;
    }
    public void setEncendido(boolean e){
        encendido = e;
    }
    
}
