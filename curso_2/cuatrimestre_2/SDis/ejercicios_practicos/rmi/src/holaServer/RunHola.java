package holaServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunHola {

    public static void main(String[] args) {
        try {
            HolaImpl oRemoto = new HolaImpl();
            Registry registro = LocateRegistry.getRegistry("localhost");
            registro.rebind("ObjetoHola", oRemoto);

            System.err.println("Servidor preparado");
        } catch (Exception e) {
            System.err.println("Excepción del servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
