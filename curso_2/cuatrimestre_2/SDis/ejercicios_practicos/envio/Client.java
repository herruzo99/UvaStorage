
public class Client {
  public static final int PUERTO = 2000;

  public static void main(String[] args) {
    String linea = null;
    try {
      java.io.BufferedReader tec =
	 new java.io.BufferedReader(
	    new java.io.InputStreamReader(System.in));

      java.net.Socket miSocket = new java.net.Socket("localhost", PUERTO);

      java.io.BufferedReader inred =
	 new java.io.BufferedReader(
            new java.io.InputStreamReader(miSocket.getInputStream()));

      java.io.PrintStream outred =
                new java.io.PrintStream(miSocket.getOutputStream());

      while ((linea = tec.readLine()) != null) { // lee de teclado
        outred.println(linea);                   // envia al servidor
        linea = inred.readLine();                // lee del servidor
        System.out.println("Recibido: "+linea);  // eco local del servidor
      }
    } catch (Exception e) { e.printStackTrace(); }
  }
}
