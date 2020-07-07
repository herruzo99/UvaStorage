
package fileserver.client;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface FileReceiver extends Remote{
    public void receiveLine(String linea) throws RemoteException;
}
