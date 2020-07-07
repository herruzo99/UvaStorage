package servidor;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Launcher {

   public static void main(String [ ] args) {
      try {
	RealCaja caja = new RealCaja();
         Registry registro = LocateRegistry.createRegistry(1099);
         registro.rebind("ObjetoCaja", caja);

         System.err.println("Servidor preparado");
      } catch (Exception e) {
         System.err.println("Excepción del servidor: "+e.toString());
         e.printStackTrace();
      }
   }
}
