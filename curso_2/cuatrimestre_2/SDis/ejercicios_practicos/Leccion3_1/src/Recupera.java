import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;
import caja.comun.*;

public class Recupera{
  public static void main(String[] args) {
    try{
      Caja stub = (Caja) Naming.lookup("CajaRemota");
      Acumulador ac = stub.recupera();
      if(ac == null) {System.out.println("La caja estaba vacía");} // si la caja estaba vacía devuelve null
      else System.out.println("La caja contenía un acumulador con valor: "+ac.valor());
    } catch (Exception e) {
       System.err.println("<Cliente: Excepcion: "+e);
       e.printStackTrace();
    }
  }
}
