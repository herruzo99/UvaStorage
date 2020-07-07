package wechat.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz del receptor de mensajes enviados al cliente desde el servidor
 * @author H
 */
public interface Client extends Remote {
    public void receiveLine(String token, String linea) throws RemoteException;
}