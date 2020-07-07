package ui.domotica;

import java.awt.Rectangle;
import javax.swing.JFrame;
import ui.Modelo;
import ui.domotica.home.*;
import ui.domotica.luz.*;
import ui.domotica.calefaccion.*;
import ui.domotica.persiana.*;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class DomoticaStateMachine {

    private JFrame currentState;
    private Modelo modelo;

    public DomoticaStateMachine(Modelo mod) {
        modelo = mod;
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                currentState = new VistaHome();
                currentState.setVisible(true);
            }
        });
    }

    public void home() {
        currentState.setVisible(false);
        currentState.dispose();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Rectangle tam = currentState.getBounds();
                currentState = new VistaHome();
                currentState.setBounds(tam);
                currentState.setVisible(true);
            }
        });
    }

    public void luz() {
        currentState.setVisible(false);
        currentState.dispose();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Rectangle tam = currentState.getBounds();
                currentState = new VistaLuz();
                currentState.setBounds(tam);
                currentState.setVisible(true);
            }
        });
    }

    public void calefaccion() {
        currentState.setVisible(false);
        currentState.dispose();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Rectangle tam = currentState.getBounds();
                currentState = new VistaCalefaccion();
                currentState.setBounds(tam);
                currentState.setVisible(true);
            }
        });
    }

    public void persiana() {
        currentState.setVisible(false);
        currentState.dispose();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Rectangle tam = currentState.getBounds();
                currentState = new VistaPersiana();
                currentState.setBounds(tam);
                currentState.setVisible(true);
            }
        });
    }

    public Modelo getModelo() {
        return modelo;

    }
}
