package ui.domotica.home;

import ui.Main;
import ui.Modelo;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class ControladorHome {
    
     private VistaHome vista;
    private Modelo modelo;

    public ControladorHome(VistaHome v, Modelo m) {
        vista = v;
        modelo = m;
    }
    /* Llamada a la máquina de estados para llamar al menú de la luz */
    public void luz(){
        Main.getStateMachineDomotica().luz();
    }
    /* Llamada a la máquina de estados para llamar al menú de las persianas*/
     public void persiana(){
        Main.getStateMachineDomotica().persiana();
    }
     /* Llamada a la máquina de estados para llamar al menú de la calefacción*/
      public void calefaccion(){
        Main.getStateMachineDomotica().calefaccion();
    }
      
}
