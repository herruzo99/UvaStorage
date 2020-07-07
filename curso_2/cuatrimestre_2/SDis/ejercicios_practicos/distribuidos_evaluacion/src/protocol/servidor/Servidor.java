package protocol.servidor;

public class Servidor {
  public static void main(String args[]) {
    int PUERTO=2000; /* puerto de servicio */
    int NThreads=5;  /* # hilos del ThreadPool */

    distribuidos.mapqueue.MultiMap<String, String>
              mapa = new distribuidos.mapqueue.MultiMap<>();

    java.util.concurrent.ExecutorService exec =
	    java.util.concurrent.Executors.newFixedThreadPool(NThreads);

    try {
      java.net.ServerSocket sock = new java.net.ServerSocket(PUERTO);
      System.err.println("Servidor: WHILE [INICIANDO]");
      Thread mainServer = new Thread(() -> {
        try {
          // WHILE
          while (true) {
            java.net.Socket socket = sock.accept();
            try  {
	      protocol.servidor.Sirviente serv =
                  new protocol.servidor.Sirviente(socket, mapa);
              exec.execute(serv);
            } catch (java.io.IOException ioe) {
              System.out.println("Servidor: WHILE [ERR ObjectStreams]");
	    }
	  }
       	} catch (java.io.IOException ioe) {
          System.err.println("Servidor: WHILE [Error.E/S]");
        } catch (Exception e) {
          System.err.println("Servidor: WHILE [Error.execute]");
        }
      }, "RUN(WHILE)");
      mainServer.start();
      System.out.println("Servidor: [CORRIENDO]");
    } catch (java.io.IOException ioe) {
      System.out.println("Servidor: [ERR SSOCKET]");
    }

  }
}