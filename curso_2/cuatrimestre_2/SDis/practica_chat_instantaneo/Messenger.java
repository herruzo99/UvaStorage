package wechat.communication;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz para construir un objeto remoto Messenger que lea archivos de texto
 * donde se guardan los historiales de las conversaciones grupales.
 * @author H
 */
public interface Messenger extends Remote {
    public String login(String nombre, String password) throws RemoteException; // LOGIN METHOD
    public void openGroup(String token, String nombreArchivo, Client receptor) throws RemoteException, FileNotFoundException;
    public void write(String token, String nombreArchivo, String newLine) throws RemoteException, FileNotFoundException;
}
