package tcp.server;

import java.util.ArrayList;

public class Sirviente implements Runnable, AutoCloseable {
    private final java.io.PrintStream   out;
    private final java.io.BufferedReader in;
    private final java.net.Socket s;
    private final Proceso p;
    
    //ArrayList para guardar Strings del cliente.
    private ArrayList<String> lista;
    
    public final int id;
    private static int idSec;

    Sirviente(java.net.ServerSocket sS, Proceso p) throws java.io.IOException {
	this.s = sS.accept();
        lista = new ArrayList<>();
        this.p = p;
        this.id = idSec++;
        this.in   = new java.io.BufferedReader(
                          new java.io.InputStreamReader(s.getInputStream()));
        this.out = new java.io.PrintStream(s.getOutputStream());
    }

    String dameLinea() throws java.io.IOException {
        return in.readLine();
    }

    void ponLinea(String linea) throws java.io.IOException {
        out.println(linea);
    }
   
    public void close() throws java.io.IOException {
        out.close();
        in.close();
    }
    public void calcLista(String entrada){
        //Comprueba si el String no está en la lista, si es así lo añade.
        if(!lista.contains(entrada)){
            lista.add(entrada);
        }
    }
    public void finHilo(){
        
        System.out.println("\nLíneas distintas totales: " + lista.size());
        String ip = s.getInetAddress().toString();
        
        /* El getRemoteSocketAddres deuvelve tipo: /x.x.x.x
         * por lo que haciento un split de "/" y quedandote con la segunda 
         * parte te queda x.x.x.x
        */
        
        ip = ip.split("/")[1];
        System.out.println("IP del cliente: " + ip);
        System.out.println("Puerto del cliente : " + s.getPort()+ "\n");
        
    }
    public void run() {
        try {
            String entrada, salida;
            while (null != (entrada = dameLinea())) {
                calcLista(entrada);
                
                //.size() devuelve el número de Strings que tenemos por lo que
                // no hace falta tener un contador.
                salida = this.p.procesaLinea(lista.size());
                ponLinea(salida);
            }
            
            //Cuando el cliente cierra la sesión se llama ya a finHilo()
            finHilo();
            
        } catch (java.net.SocketException se) {
	    System.err.println("Conexion cerrada por el host cliente");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}