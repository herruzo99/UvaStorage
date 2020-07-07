package ui;

/**
 *
 * @author Rebeca Hernando Brecht.
 * @author Juan Herruzo Herrero.
 */
public class Bombilla {
    private boolean encendido;
    private int intensidad;
    /*
    Blanco      0 
    Verde       1
    Azul        2
    Amarillo    3
    Rojo        4
    */
    private int color;

    public Bombilla() {
        encendido = false;
        intensidad = 100;
        color = 0;
    }
    public int getColor(){
        return color;
    }
    public void setColor(int c){
        color = c;
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
