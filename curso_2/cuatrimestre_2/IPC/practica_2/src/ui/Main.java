package ui;

import ui.domotica.DomoticaStateMachine;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class Main {

    private static Modelo modelo;
    private static DomoticaStateMachine domoticaStateMachine;

    public static void main(String args[]) {
        modelo = new Modelo();

        domoticaStateMachine = new DomoticaStateMachine(modelo);
    }

    public static DomoticaStateMachine getStateMachineDomotica() {
        return domoticaStateMachine;
    }
}
