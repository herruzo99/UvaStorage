package udp.echo;

public class UDPEchoServer {
  static final int BUFFERSIZE = 256;

  public static void main(String[] args) {
    java.net.DatagramSocket sock;
    java.net.DatagramPacket pack =
     new java.net.DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
    try {
      sock = new java.net.DatagramSocket(1919);
    } catch (java.net.SocketException e) {
      System.out.println(e);
      return;
    }
    // echo back everything
    while (true) {
      try {
        sock.receive(pack);
        System.out.println("["+pack.getAddress().getHostAddress()+":"+
                pack.getPort()+"> "+
                (new String(pack.getData())));
        sock.send(pack);
      } catch (java.io.IOException ioe) {
        System.out.println(ioe);
      }
    }
  }
}