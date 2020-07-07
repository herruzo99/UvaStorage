package fileserver.server;

import fileserver.client.FileReceiver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileServerImpl extends UnicastRemoteObject implements FileServer {

    public FileServerImpl() throws RemoteException {
        super();
    }

    @Override
    public void openRead(String nombreArchivo, FileReceiver receptor) throws RemoteException, FileNotFoundException {
        //Se crea un hilo cada vez que es llamado
        Thread thread = new Thread() {
            public void run() {
                File archivo = null;
                FileReader fr = null;
                BufferedReader br = null;
                int numLineas = 0; //número de líneas ya leidas.
                try {

                    archivo = new File(nombreArchivo);
                    //Si no existe al archivo lo crea.
                    if(!archivo.exists()){
                        archivo.createNewFile();
                    }
                    //Recorre todo el rato el archivo.
                    while (true) {
                        fr = new FileReader(archivo);
                        br = new BufferedReader(fr);
                        
                        //Se salta las líneas ya leidas.
                        for (int i = 0; i < numLineas; i++) {
                            br.readLine();
                        }
                        
                        //Envia línea a línea el archivo.
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            receptor.receiveLine(linea);
                            numLineas++;
                        }
                        //Cierra el lector para abrirlo de nuevo en la siguiente iteracción.
                        fr.close();
                        br.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        thread.start(); // Empieza el hilo.

    }

    @Override
    public void injectLine(String nombreArchivo, String linea) throws RemoteException, FileNotFoundException {
        
        //Añade una línea al final del fichero dado.
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombreArchivo, true);
            pw = new PrintWriter(fichero);
            linea = linea + "\n";
            pw.append(linea);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(FileServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
