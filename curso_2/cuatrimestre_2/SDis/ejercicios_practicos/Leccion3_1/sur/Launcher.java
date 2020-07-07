package caja.comun;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Launcher {
    
    public static void main(String[] args){
        try {
        
        //Se crea el registro rmi por lo que no hace falta crearlo desde consola.
        Registry registro = LocateRegistry.createRegistry(1099);
        
        RealCaja CajaRemota = new RealCaja();
        //Vincula el objeto CajaRemota con la id CajaRemota en el registro rmi.
        registro.rebind("CajaRemota", CajaRemota);
                 
        System.err.println("Servidor activo");
      } catch (Exception e) {
         System.err.println("Excepción del servidor: "+e.toString());
         e.printStackTrace();
      }
    }
}
