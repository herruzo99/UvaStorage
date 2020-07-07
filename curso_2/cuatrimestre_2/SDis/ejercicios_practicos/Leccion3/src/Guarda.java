
import caja.comun.Acumulador;
import caja.comun.Caja;
import java.rmi.Naming;


public class Guarda {
    public static void main(String [ ] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
         
         Caja stub = (Caja) Naming.lookup("//localhost/CajaRemota");
         stub.guarda(new Acumulador(7));
         
      } catch (Exception e) {
         System.err.println("<Cliente: Excepcion: "+e);
         e.printStackTrace();
      }
   }
}
