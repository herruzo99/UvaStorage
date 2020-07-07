/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.entrega1rebhernjuaherrVista;

import java.awt.Color;
import my.entrega1rebhernjuaherrModelo.ModeloEntrega;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class ControladorEntrega {

    private VistaEntrega vista;
    private ModeloEntrega modelo;

    public ControladorEntrega(VistaEntrega v, ModeloEntrega m) {
        vista = v;
        modelo = m;
    }

    //Cambia las propiedades la bombilla i.
    private void configuraBombilla(int i, int intensidad, float color, float saturacion, float brillo, boolean encendido) {
        modelo.bombilla[i].setIntensidad(intensidad);
        modelo.bombilla[i].setColor(color);
        modelo.bombilla[i].setSaturacion(saturacion);
        modelo.bombilla[i].setBrillo(brillo);
        modelo.bombilla[i].setEncendido(encendido);
    }

    //Configura las bombillas según las caracteristicas del botón de la parte
    //superior que seleccione.
    public void botonPredeterminado(int i, int intensidad, float color, float saturacion, float brillo, boolean encendido) {
        modelo.numBombilla = i;
        configuraBombilla(i, intensidad, color, saturacion, brillo, encendido);
        vista.actualizaVista();

    }

    //botón superior que enciende todas las bombillas.
    public void botonEncendidas() {
        int bombillaActual = modelo.numBombilla;
        for (int i = 0; i < 5; i++) {
            botonPredeterminado(i, 100, 0, 0, 1, true);
        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }

    //botón superior que apaga todas las bombillas.
    public void botonApagadas() {
        int bombillaActual = modelo.numBombilla;

        for (int i = 0; i < 5; i++) {
            botonPredeterminado(i, modelo.bombilla[i].getIntensidad(), 0, 0, 0.5f, false);

        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }

    //botón superior que pone luz de ambiente.
    public void botonAmbiente() {
        int bombillaActual = modelo.numBombilla;

        for (int i = 0; i < 5; i++) {
            botonPredeterminado(i, 30, (float) (60 / 360.0), 1, 1, true);
        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }

    //boton superior para la luz de lectura.
    public void botonLectura() {
        int bombillaActual = modelo.numBombilla;

        for (int i = 0; i < 5; i++) {
            if (i == 0 || i == 2) {
                botonPredeterminado(i, 60, 0, 0, 1, true);

            } else {

                botonPredeterminado(i, modelo.bombilla[i].getIntensidad(), 0, 0, 0.5f, false);
            }
        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }
    public void letra(int i){
        modelo.tamLetra = modelo.tamLetra + i;
        vista.actualizaLetra();
    }
    public void contraste(){
        modelo.contraste = !modelo.contraste;
        vista.actualizaContraste();
    }
    //Cambia el panel de propiedades según la bombilla seleccionada.
    public void mostrarBombilla(int i) {
        modelo.numBombilla = i;
        vista.actualizaVista();

    }

    public void mostrarColores(boolean mostrar) {
        modelo.masColores = mostrar;
        vista.actualizaCuadroColores();
    }

    public void guardaColorImagen(int x, int width, int y, int height) {

        float brit = x / (float) width;
        float hue = (y / (float) height);
        float s, b;
        if (brit < 0.5) {
            s = 2 * brit;
            b = 1;
        } else {

            s = 1;
            b = 2 * ((float) 1 - brit);
        }

        guardaColor(hue, s, b);

    }

    public void guardaColor(float hue, float s, float b) {
        modelo.bombilla[modelo.numBombilla].setColor(hue);
        modelo.bombilla[modelo.numBombilla].setSaturacion(s);
        modelo.bombilla[modelo.numBombilla].setBrillo(b);

        vista.actualizaVista();

    }

    public void encender() {
        modelo.bombilla[modelo.numBombilla].setEncendido(!modelo.bombilla[modelo.numBombilla].getEncendido());
        vista.actualizaColorEncender();
        vista.actualizaVista();

    }

    /*public void desactivarBotonColor() {
        modelo.colorActual.setSelected(false);

    }*/
    public void pintaBotonColor() {
        vista.actualizaColorBombilla();
    }

    public void actualizaIntensidad(int i) {

        modelo.bombilla[modelo.numBombilla].setIntensidad(i);
        pintaIntensidad();
    }

    public void encenderIntensidad() {
        if (!modelo.bombilla[modelo.numBombilla].getEncendido()) {
            encender();
        }
    }

    public void pintaIntensidad() {
        vista.actualizaIntensidad();
    }

    public boolean compruebaPredeterminado(int i, int intensidad, float color, float saturacion, float brillo, boolean encendido) {
//TODO ERROR al comparar
        float error = 0.15f;
        return Math.abs(modelo.bombilla[i].getBrillo() - brillo) < error
                && Math.abs(modelo.bombilla[i].getIntensidad() - intensidad) < error
                && Math.abs(modelo.bombilla[i].getColor() - color) < error
                && Math.abs(modelo.bombilla[i].getSaturacion() - saturacion) < error
                && modelo.bombilla[i].getEncendido() == encendido;

    }

    public void actualizaPredeterminado() {
        int encendidas = 0, apagadas = 0, ambiente = 0, lectura = 0;
        for (int i = 0; i < 5; i++) {
            if (compruebaPredeterminado(i, 100, 0, 0, 1, true)) {
                encendidas++;
            } else if (compruebaPredeterminado(i, modelo.bombilla[i].getIntensidad(), modelo.bombilla[i].getColor(), modelo.bombilla[i].getSaturacion(), modelo.bombilla[i].getBrillo(), false)) {
                apagadas++;
                if (i == 1 || i == 3 || i == 4) {
                    lectura++;
                }
            } else if (compruebaPredeterminado(i, 30, (float) (60 / 360.0), 1, 1, true)) {
                ambiente++;
            } else if ((i == 0 || i == 2) && compruebaPredeterminado(i, 60, 0, 0, 1, true)) {
                lectura++;
            }
        }

        if (encendidas == 5) {
            vista.activaBotonPredeterminado("encendidas");
        } else if (apagadas == 5) {
            vista.activaBotonPredeterminado("apagadas");
        } else if (lectura == 5) {
            vista.activaBotonPredeterminado("lectura");
        } else if (ambiente == 5) {
            vista.activaBotonPredeterminado("ambiente");
        } else {
            vista.activaBotonPredeterminado("ninguno");
        }

    }
}
