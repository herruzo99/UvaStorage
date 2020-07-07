package tcp.server;


public class Server implements Runnable {
  private final int port;
  private final java.net.ServerSocket ss;
  private final Proceso p;
  private final ThreadGroup tg;
  private       boolean serverIniciado = false;

  public Server(int port, Proceso p) throws java.io.IOException {
    this.p    = p;
    this.port = port;
    this.ss   = new java.net.ServerSocket(port);
    this.tg   = new ThreadGroup("Server: "+port);
  }

  public ThreadGroup startServer(boolean daemon) {
    if (! serverIniciado) {
      (new Thread(this.tg, this, "Server Loop")).start();
      tg.setDaemon(daemon);
    }
    return tg;
  }

  public void run() {
    for (int i=1; true;i++) {
      try {
        System.out.println("---Aceptando conexion---");
	(new Thread(tg, new Sirviente(ss, p), "ALMACEN")).start();
      } catch (java.io.IOException e) {
        e.printStackTrace(System.err);
      }
    }
  }
}