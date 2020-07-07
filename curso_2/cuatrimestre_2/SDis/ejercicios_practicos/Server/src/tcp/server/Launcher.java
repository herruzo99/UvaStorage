package tcp.server;

public class Launcher {
  public static void main(String[] args) throws java.io.IOException {
      
      //La lambda parsea el int a string.
    Server ser = new Server(2000, (entrada) -> { return Integer.toString(entrada); });
    ser.startServer(true);
  }
}
