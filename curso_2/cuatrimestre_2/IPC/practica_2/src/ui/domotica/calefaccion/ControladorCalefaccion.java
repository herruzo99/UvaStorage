package ui.domotica.calefaccion;

import ui.Main;
import ui.Modelo;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class ControladorCalefaccion {

    private VistaCalefaccion vista;
    private Modelo modelo;

    public ControladorCalefaccion(VistaCalefaccion v, Modelo m) {
        vista = v;
        modelo = m;
    }

    //Cambia al panel de las luces.
    public void luz() {
        Main.getStateMachineDomotica().luz();
    }

    //Cambia al panel de las persinas.
    public void persiana() {
        Main.getStateMachineDomotica().persiana();
    }

//Cambia al panel de la calefacción.
    public void calefaccion() {
        Main.getStateMachineDomotica().calefaccion();
    }

    //Cambia al panel de home.
    public void home() {
        Main.getStateMachineDomotica().home();
    }

    //Cambia en modelo si está encendida o apagada la calefacción según
    //corresponda.
    public void encender() {
        modelo.setcalEncendida();
        vista.actualizaEncendido();
    }

    //Cambia la temperatura y modifica el Slider desde los otros botones.
    public void cambiarTemperatura(int valor) {
        encenderOtrosBotones();
        valor = Math.min(Math.max(valor, 4), 32);
            modelo.setTemperatura(valor);
        
        pintaCalor();
    }

    //Si no está encendida la calefacción y si se toca los botones de más, menos
    //o el slider se enciende y por tanto se presiona el botón.
    public void encenderOtrosBotones() {
        if (!modelo.getcalEncendida()) {
            encender();
        }
    }

    //Actualiza el Slider.
    public void pintaCalor() {
        vista.actualizaCalor();
    }
}
