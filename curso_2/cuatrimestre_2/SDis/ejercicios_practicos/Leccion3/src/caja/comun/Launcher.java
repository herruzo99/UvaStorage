/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.comun;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author juan
 */
public class Launcher {
    
    public static void main(String[] args){
        try {
        
        Registry registro = LocateRegistry.createRegistry(1099);
        
        RealCaja CajaRemota = new RealCaja();
        
        registro.rebind("//localhost/cajaRemota", CajaRemota);
                 
        System.err.println("Servidor activo");
      } catch (Exception e) {
         System.err.println("Excepción del servidor: "+e.toString());
         e.printStackTrace();
      }
    }
}
