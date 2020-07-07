package fileserver.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileReceiverImpl extends UnicastRemoteObject implements FileReceiver {

    public FileReceiverImpl() throws RemoteException {
        super();
    }

    @Override
    public void receiveLine(String linea) throws RemoteException {
       
        System.out.println(linea);
    }

}
