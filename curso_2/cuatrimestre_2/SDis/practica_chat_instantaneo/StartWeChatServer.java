package wechat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Lanza el objeto remoto ObjetoWeChat de tipo Messenger.
 * @author H
 */
public class StartWeChatServer {

    public static void main(String[] args) {

        try {
            // CREAR
            // FACTORIAS DE SOCKETS SSL
            javax.rmi.ssl.SslRMIClientSocketFactory rmicsf = new javax.rmi.ssl.SslRMIClientSocketFactory();
            javax.rmi.ssl.SslRMIServerSocketFactory rmissf = new javax.rmi.ssl.SslRMIServerSocketFactory();

      

            //
            // CREAMOS EL OBJETO WECHAT SEGURO
            MessengerWeChat cc = new MessengerWeChat(rmicsf,rmissf);

            //
            // CREAMOS EL REGISTRO RMI CON CONEXIÓN SSL
            Registry registro = LocateRegistry.createRegistry(1100, rmicsf,rmissf);

            //
            // REGISTRAMOS EL OBJ WECHAT EN EL RMI-REG PARA HACERLO PÚBLICO
            registro.rebind("ObjetoWeChat", cc);
            System.out.println("Objeto remoto SEGURO! 'ObjetoWeChat' publicado en el RMI-REG");

        } catch (RemoteException re) {
            re.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}