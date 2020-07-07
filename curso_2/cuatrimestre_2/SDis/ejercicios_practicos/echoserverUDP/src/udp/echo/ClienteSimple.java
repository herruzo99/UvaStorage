package udp.echo;

public class ClienteSimple {
  public static void main(String[] args) throws java.io.IOException {
    String host = "localhost";
    String linea;
    int port = 1919;
    java.net.InetAddress ia = java.net.InetAddress.getByName(host);
    java.net.DatagramSocket socket = new java.net.DatagramSocket();
    socket.connect(ia, port);

        
    java.io.Reader r1 = new java.io.InputStreamReader(System.in);
    java.io.BufferedReader teclado = new java.io.BufferedReader(r1); // teclado 
        
    while ((linea = teclado.readLine()) != null) {
      byte[] dataOut = linea.getBytes();
      java.net.DatagramPacket output =
                new java.net.DatagramPacket(dataOut, dataOut.length, ia, port);
      socket.send(output);
      byte[] dataIn = new byte[160];        
      java.net.DatagramPacket input =
            new java.net.DatagramPacket(dataIn, dataIn.length);
        
      socket.receive(input);
      linea = new String(input.getData());
      System.out.println("Echo: "+linea);
      if (linea.startsWith("Adios.")) System.exit(0);
    }
  }
}