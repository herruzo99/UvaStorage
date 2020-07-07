package runfileserver;

import fileserver.FileServidor;
import fileserver.FileServidorImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Lanza el objeto remoto ObjetoFileServidor de tipo FileServidor.
 * @author cllamas
 */
public class RunFileServidor {
    public static void main(String[] args) {
        try {
        FileServidor cc = new FileServidorImpl();

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