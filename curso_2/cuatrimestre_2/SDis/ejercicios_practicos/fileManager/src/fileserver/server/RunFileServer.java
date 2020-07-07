
package fileserver.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunFileServer {
    public static void main(String[] args) {
        try {
            FileServer cc = new FileServerImpl();

        Registry registro = LocateRegistry.createRegistry(1099);

        registro.rebind("Server", cc);
        System.out.println("Objeto remoto 'ObjetoFileServidor' enlazado");
        } catch (RemoteException re) {
            re.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
