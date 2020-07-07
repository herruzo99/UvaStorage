package my.entrega1rebhernjuaherrModelo;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */

import java.util.ArrayList;

/**
 *
 * @author Rebeca Hernado Brecht.
 * @author Juan Herruzo Herrero.
 */
public class ModeloEntrega {
    
    public int numBombilla;
    public Bombilla[] bombilla;
    public boolean masColores;
    public javax.swing.JToggleButton colorActual;

    public ModeloEntrega() {
        bombilla = new Bombilla[5];
        for (int i = 0; i < 5; i++) {
            bombilla[i] = new Bombilla();
        }
        masColores = false;
    }

}
