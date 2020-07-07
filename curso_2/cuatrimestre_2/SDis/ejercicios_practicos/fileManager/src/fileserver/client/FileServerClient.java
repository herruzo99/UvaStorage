package fileserver.client;

import fileserver.server.FileServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileServerClient {

    public static void main(String[] args) throws RemoteException {

        FileReceiver revicer = new FileReceiverImpl();
        String nombreArchivo = "Hola.txt"; //Nombre del archivo.
        FileServer fs;
        try {
            fs = (FileServer) Naming.lookup("rmi://localhost/Server");

            //Se crea un hilo para que pueda coger texto por pantalla y añadirlo al archivo.
            Thread thread = new Thread() {

                public void run() {

                    //Comprueba si se ha pasado un argumento para usarlo como usuario.
                    String nombre = null;
                    if (args.length == 1) {
                        nombre = args[0];
                    } else {
                        System.out.println("Pueses personalizar tu nombre pasandoselo como argumento.");
                        nombre = "Anónimo" + (int) (Math.random() * 1000);
                    }
                    System.out.println("\n#------Escribe para mandar un mensaje.------#\n");

                    BufferedReader reader
                            = new BufferedReader(new InputStreamReader(System.in));
                    //Se queda en bucle esperando a recibir una línea por pantalla.
                    while (true) {
                        String linea;
                        try {
                            linea = reader.readLine();
                            fs.injectLine(nombreArchivo, nombre + ": " + linea);

                        } catch (IOException ex) {
                            Logger.getLogger(FileServerClient.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            };
            thread.start(); //Empieza el hilo
            

            fs.openRead(nombreArchivo, revicer);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
