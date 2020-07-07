package caja.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Caja extends Remote{
    public void guarda(Acumulador x) throws RemoteException;   // línea 04
    public Acumulador recupera() throws RemoteException;   // línea 05
}
                                                                           
