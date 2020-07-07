package caja.comun;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RealCaja extends UnicastRemoteObject implements Caja {
    
    Acumulador dentro; //Variable interna para guardar el acumulador.
    
    public RealCaja() throws RemoteException{
        super();
    }
    
    public void guarda(Acumulador ac) throws RemoteException{
    
        if(dentro == null){
            System.err.println(">Recivido acumulador con valor " + ac.valor());
        }else{
            System.err.println(">Recivido acumulador con valor " + ac.valor() + " (Sobreescribiendo acumulador anterior)");
        }
        
        dentro = ac;
        
    }
    
    public Acumulador recupera() throws RemoteException{
        if(dentro == null){
            System.err.println(">Intento de recuperación, pero la caja está vacia");
        }else{
            System.err.println(">Enviando acumulador guardado");
        }
        
        //Se crea un temporal para poder borrar el acumulador de la caja.
        Acumulador tmp = dentro;
        dentro = null;
        return tmp;
    }  
    
}
