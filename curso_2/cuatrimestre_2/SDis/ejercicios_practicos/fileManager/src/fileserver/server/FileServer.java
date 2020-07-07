
package fileserver.server;

import fileserver.client.FileReceiver;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileServer extends  Remote{
    public void openRead(String nombreArchivo, FileReceiver receptor)
      throws RemoteException, FileNotFoundException;
    
    //Se utiliza para añadir lineas al fichero dado.
    public void injectLine(String nombreArchivo, String linea)
      throws RemoteException, FileNotFoundException;
}
