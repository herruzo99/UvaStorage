package udp.server;

public class UDPEchoSessionServer {
  static final int BUFFERSIZE = 256;
  static final java.util.HashMap<java.net.SocketAddress,udp.common.Sesion>
        sesiones = new java.util.HashMap<>();

  public static void main(String[] args) {
    java.net.DatagramSocket sock;
    try {
      sock = new java.net.DatagramSocket(1919);
    } catch (java.net.SocketException e) {
      System.out.println(e);
      return;
    }
    // echo de todo sin importar el origen.
    while (true) {
      try {
        java.net.DatagramPacket pack =
       new java.net.DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
    
        sock.receive(pack);
        udp.common.Sesion s;
        if ((s = sesiones.get(pack.getSocketAddress())) == null) {
            s = new udp.common.Sesion(pack.getSocketAddress());
            sesiones.put(pack.getSocketAddress(), s);
        }
        String linea = "<"+s.getId()+">["+pack.getAddress().getHostAddress()+
                 ":"+pack.getPort()+"] "+(new String(pack.getData()));
        System.out.println(linea);

    byte[] buffer = linea.getBytes();

    pack.setData(buffer); // no es preciso setLength para enviar.

        sock.send(pack);      // se aprovecha el mismo datagrama.
        System.out.println(pack.getPort());
      } catch (java.io.IOException ioe) {
        System.out.println(ioe);
      }
    }
  }
}