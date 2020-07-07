package ui.domotica.luz;

import java.awt.Color;
import ui.Main;
import ui.Modelo;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class ControladorLuz {

    private VistaLuz vista;
    private Modelo modelo;

    public ControladorLuz(VistaLuz v, Modelo m) {
        vista = v;
        modelo = m;
    }

    public void luz() {
        Main.getStateMachineDomotica().luz();
    }

    public void persiana() {
        Main.getStateMachineDomotica().persiana();
    }

    public void calefaccion() {
        Main.getStateMachineDomotica().calefaccion();
    }

    public void home() {
        Main.getStateMachineDomotica().home();
    }

    //Cambia en el modelo que bombilla está seleccionada y le manda a la vista actualizarse.
    public void mostrarBombilla(int i) {
        modelo.numBombilla = i;
        vista.actualizaVista();

    }

    /*
    Comprueba si la bombilla dada cumple los requisitos de intensidad, color y encendido.
     */
    public boolean compruebaPredeterminado(int i, int intensidad, int color, boolean encendido) {
        float error = 0.15f;
        return Math.abs(modelo.bombilla[i].getIntensidad() - intensidad) < error
                && modelo.bombilla[i].getColor() == color
                && modelo.bombilla[i].getEncendido() == encendido;

    }

    /*
    Comprueba si las bombillas estan en un estado en el que cumplan uno de los cuatro
    botones predeterminados, si es así manda a la vista que lo marque.
     */
    public void actualizaPredeterminado() {
        int encendidas = 0, apagadas = 0, ambiente = 0, lectura = 0;
        for (int i = 0; i < 5; i++) {
            if (compruebaPredeterminado(i, 100, 0, true)) {
                encendidas++;
            } else if (compruebaPredeterminado(i, modelo.bombilla[i].getIntensidad(), modelo.bombilla[i].getColor(), false)) {
                apagadas++;
                if (i == 1 || i == 3 || i == 4) {
                    lectura++;
                }
            } else if (compruebaPredeterminado(i, 30, 3, true)) {
                ambiente++;
            } else if ((i == 0 || i == 2) && compruebaPredeterminado(i, 60, 0, true)) {
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

    /*
    Alterna el encendido de la bombilla actual.
     */
    public void encender() {
        modelo.bombilla[modelo.numBombilla].setEncendido(!modelo.bombilla[modelo.numBombilla].getEncendido());
        modelo.bombilla[modelo.numBombilla].setColor(0);
        vista.actualizaVista();

    }

    /*
    Modifica el color de la bombilla actual al dado.
     */
    public void guardaColor(int color) {
        modelo.bombilla[modelo.numBombilla].setColor(color);

        vista.actualizaVista();

    }

    /*
    Modifica la intensidad de la bombilla actual al dado con rango 1..100
     */
    public void actualizaIntensidad(int i) {
        encenderIntensidad();
        i = Math.min(Math.max(i, 1), 100);
        modelo.bombilla[modelo.numBombilla].setIntensidad(i);

        vista.actualizaIntensidad();
    }

    /*
    Permite encender la bombilla con el Slider.
     */
    public void encenderIntensidad() {
        if (!modelo.bombilla[modelo.numBombilla].getEncendido()) {
            encender();
        }
    }


    //Cambia las propiedades la bombilla i.
    private void configuraBombilla(int i, int intensidad, int color, boolean encendido) {
        modelo.bombilla[i].setIntensidad(intensidad);
        modelo.bombilla[i].setColor(color);
        modelo.bombilla[i].setEncendido(encendido);
    }

    //Configura las bombillas según las caracteristicas del botón de la parte
    //superior que seleccione.
    public void botonPredeterminado(int i, int intensidad, int color, boolean encendido) {
        modelo.numBombilla = i;
        configuraBombilla(i, intensidad, color, encendido);
        vista.actualizaVista();

    }

    //botón superior que enciende todas las bombillas.
    public void botonEncendidas() {
        int bombillaActual = modelo.numBombilla;
        for (int i = 0; i < 5; i++) {
            botonPredeterminado(i, 100, 0, true);
        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }

    //botón superior que apaga todas las bombillas.
    public void botonApagadas() {
        int bombillaActual = modelo.numBombilla;

        for (int i = 0; i < 5; i++) {
            botonPredeterminado(i, modelo.bombilla[i].getIntensidad(), 0, false);

        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }

    //botón superior que apaga todas las bombillas.
    public void iniciarVista() {
        int bombillaActual = modelo.numBombilla;

        for (int i = 0; i < 5; i++) {
            botonPredeterminado(i, modelo.bombilla[i].getIntensidad(), modelo.bombilla[i].getColor(), modelo.bombilla[i].getEncendido());

        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }

    //botón superior que pone luz de ambiente.
    public void botonAmbiente() {
        int bombillaActual = modelo.numBombilla;

        for (int i = 0; i < 5; i++) {
            botonPredeterminado(i, 30, 3, true);
        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }

    //boton superior para la luz de lectura.
    public void botonLectura() {
        int bombillaActual = modelo.numBombilla;

        for (int i = 0; i < 5; i++) {
            if (i == 0 || i == 2) {
                botonPredeterminado(i, 60, 0, true);

            } else {

                botonPredeterminado(i, modelo.bombilla[i].getIntensidad(), 0, false);
            }
        }
        modelo.numBombilla = bombillaActual;
        vista.actualizaVista();

    }
}
