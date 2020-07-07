


package cliente;

import caja.comun.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class Client {
   private Client () { }

   public static void main(String [ ] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
         /* OBSOLETO: Esto está un poco rancio,
            Registry registro = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registro.lookup(***);
          */
         Caja stub = (Caja) Naming.lookup("//localhost/ObjetoCaja"); /* ¿Qué hay aquí?*/

         Acumulador respuesta = stub.recupera();
         System.out.println("[Respuesta: recuperado");
      } catch (Exception e) {
         System.err.println("<Cliente: Excepcion: "+e);
         e.printStackTrace();
      }
   }
}
