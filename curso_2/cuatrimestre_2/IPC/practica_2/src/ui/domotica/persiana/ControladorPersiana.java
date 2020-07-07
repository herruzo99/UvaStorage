package ui.domotica.persiana;

import ui.Main;
import ui.Modelo;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class ControladorPersiana {

    private VistaPersiana vista;
    private Modelo modelo;

    public ControladorPersiana(VistaPersiana v, Modelo m) {
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

    /*Cambia la altura de la persiana indicada al valor indicado.
    El el valor ha de estar enetre 1 y 10.*/
    public void cambiarPersianas(int i, int valor) {
        valor = Math.min(Math.max(valor, 0), 10);
            modelo.persiana[i].setAltura(valor);
            vista.actualizaAltura();
        
    }

}
