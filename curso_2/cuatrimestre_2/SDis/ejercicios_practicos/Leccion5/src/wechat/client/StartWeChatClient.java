package wechat.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

import javax.rmi.ssl.SslRMIServerSocketFactory;

import wechat.communication.Client;
import wechat.communication.Messenger;



/**
 * Cliente WeChat
 * @author H
 */
public class StartWeChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //
        // CHECK THE ARGS
        if(args.length < 3){ System.out.println("Use: name password groupName"); return;}
        String nickName  = args[0];
        String password  = args[1];
        String groupName = args[2];

        try {
            //
            // 0: COGEMOS LA REFERENCIA DEL OBJETO SERVIDOR PUBLICADA EN EL RMI-REG
            //
        	javax.rmi.ssl.SslRMIClientSocketFactory rmicsf = new javax.rmi.ssl.SslRMIClientSocketFactory();;
            Registry reg = LocateRegistry.getRegistry("localhost",1099,rmicsf);
            Messenger iServer = (Messenger) reg.lookup("ObjetoWeChat");


            //
            // 1: EJECUTAMOS MÉTODO REMOTO LOGIN del server y COGEMOS EL TOKEN
            //
            String token = iServer.login(nickName, password);
            if(token == null){
                System.out.println("#####################");
                System.out.println(">>> Wrong credentials");
                return;
            }else{
                System.out.println("Logged in with token: "+ token);
            }

            // *** AQUÍ INSERTAREMOS CÓDIGO PARA LOS OBJETIVOS B Y C*/
            //
            // 2.1: CREAMOS NUESTRO OBJETO RECEPTOR DE MENSAJES (NÚMERO WECHAT)
            //
            SslRMIServerSocketFactory rmissf = new SslRMIServerSocketFactory();
            Client iClient = new ClientReceptor(nickName, token, rmicsf ,  rmissf);

            //
            // 2.2: LANZAMOS UN HILO PENDIENTE DE LAS COMUNICACIONES ENTRANTES
            //
            Runnable listener = () -> {
                try {

                    System.out.println("##########################");
                    System.out.println("### GROUP: "+groupName+"  ");
                    System.out.println("##########################");

                    iServer.openGroup(token, groupName, iClient); // Invocación de Método Remoto _openGroup_

                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            };
            new Thread(listener, "listener").start();

            //
            // 3: EMPEZAMOS A ESCRIBIR EN BÚCLE AL GRUPO
            //
            String newLine = null;
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
            while((newLine=keyboardReader.readLine()) != null){
                cleanEntry();
                iServer.write(token, groupName, nickName+": "+newLine); // Invocación de Método Remoto _write_
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    private static String login(String nickName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void cleanEntry(){
        System.out.print(String.format("\033[%dA",1)); // Move up
        System.out.print("\033[2K"); // Erase line content
    }
}