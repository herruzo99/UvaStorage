package caja.comun;

import java.io.Serializable;

public class Acumulador implements Serializable{
    private int acumulado;
    public Acumulador(int valorInicial)
       {   this.acumulado = valorInicial;    }
    public int valor()
       { return acumulado; }
    public int incrementa()
       { return this.acumulado++; }
}
