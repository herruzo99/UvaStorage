package caja.comun;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Caja extends Remote{
    public void guarda(Acumulador ac) throws RemoteException;
    public Acumulador recupera() throws RemoteException;
}
