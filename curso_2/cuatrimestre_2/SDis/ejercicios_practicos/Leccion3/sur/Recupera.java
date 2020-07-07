package cliente;

import caja.comun.Acumulador;
import caja.comun.Caja;
import java.rmi.Naming;


public class Recupera {
    public static void main(String [ ] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
         //Busca el objeto en el registro rmi con la id CajaRemota.
         Caja stub = (Caja) Naming.lookup("//localhost/CajaRemota");
         
         Acumulador ac = (Acumulador) stub.recupera();
         if (ac == null){
            //Si no habia acumulador en la caja escribe un mensaje distinto.
            System.out.println("No hay ningún acumulador en la caja");
         }else{
            System.out.println("El valor recuperado es " + ac.valor());
         }
         
      } catch (Exception e) {
         System.err.println("<Cliente: Excepcion: "+e);
         e.printStackTrace();
      }
   }
}
