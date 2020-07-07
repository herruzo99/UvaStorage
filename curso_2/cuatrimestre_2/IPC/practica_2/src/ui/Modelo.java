package ui;
import java.util.ArrayList;

/**
 *
 * @author Rebeca Hernado Brecht.
 * @author Juan Herruzo Herrero.
 */
public class Modelo {
    /*Luminaria*/
    public int numBombilla;
    public Bombilla[] bombilla;
    
    /* Calefación*/
    public int temperatura;
    public boolean calEncendida;
    
    /* Persiana del 1 al 10*/
    public Persiana[] persiana;
    
    public Modelo() {
        bombilla = new Bombilla[5];
        for (int i = 0; i < 5; i++) {
            bombilla[i] = new Bombilla();
        }
        
        persiana = new Persiana[2];
        for (int i = 0 ; i <2; i++){
            persiana[i] = new Persiana();
        }
        
        temperatura = 21;
        calEncendida = false;
    }
    
    /* Temperatura */
    public boolean getcalEncendida(){
        return calEncendida;
    }
    public int getTemperatura(){
        return temperatura;
    }
    
    public void setcalEncendida(){
        calEncendida = !calEncendida;
    }
    public void setTemperatura(int temp){
        temperatura = temp;
    }
    
    
}
