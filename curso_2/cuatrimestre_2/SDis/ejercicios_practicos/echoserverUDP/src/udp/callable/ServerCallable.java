package udp.callable;

public class ServerCallable {
  static final int BUFSIZ = 256;
  static final java.util.HashMap<java.net.SocketAddress,
      SessionCallableEcho> sesiones = new java.util.HashMap<>();

  public static void main(String[] args) {
    java.util.concurrent.ExecutorService pool =
        java.util.concurrent.Executors.newFixedThreadPool(3);
    java.net.DatagramSocket sock;
    try {
      sock = new java.net.DatagramSocket(1919);
    } catch (java.net.SocketException e) {
      System.out.println(e);
      return;
    }
    // echo back everything
    while (true) {
      try {
        java.net.DatagramPacket pack =
           new java.net.DatagramPacket(new byte[BUFSIZ],BUFSIZ);
    
        sock.receive(pack);
        SessionCallableEcho s;
        
        if ((s = sesiones.get(pack.getSocketAddress())) == null) {
            s = new SessionCallableEcho(pack.getSocketAddress(), sock);
            sesiones.put(pack.getSocketAddress(), s);
        }
        s.putDP(pack);
        java.util.concurrent.Future<Boolean> tarea = pool.submit(s);
        System.out.println(tarea.isDone());
      } catch (java.io.IOException ioe) {
        System.out.println(ioe);
      }
    }
  }
}