package holaServer;

import java.util.Map;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HolaRegistro extends Remote{
    Map<String,String> lista() throws RemoteException;
}