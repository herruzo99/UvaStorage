
import caja.comun.Acumulador;
import caja.comun.Caja;
import java.rmi.Naming;


public class Recupera {
    public static void main(String [ ] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
         
         Caja stub = (Caja) Naming.lookup("//localhost/CajaRemota");
         Acumulador ac = (Acumulador) stub.recupera();
         
         System.out.println("El valor recuperado es " + ac.valor());
         
      } catch (Exception e) {
         System.err.println("<Cliente: Excepcion: "+e);
         e.printStackTrace();
      }
   }
}
