
package caja.comun;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RealCaja extends UnicastRemoteObject implements Caja {
    
    Acumulador dentro;
    
    public RealCaja() throws RemoteException{
        super();
    }
    
    public void guarda(Acumulador ac) throws RemoteException{
    dentro = ac;
        
    }
    public Acumulador recupera() throws RemoteException{
        Acumulador tmp = dentro;
        dentro = null;
        return tmp;
    }  
    
}
