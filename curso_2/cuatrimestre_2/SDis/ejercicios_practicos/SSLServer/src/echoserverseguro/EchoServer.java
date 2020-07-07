package echoserverseguro;

public class EchoServer { 
  public static void main(String[] arstring) {
    try {
      javax.net.ssl.SSLServerSocketFactory factoriaServer =        /* linea 6 */
          (javax.net.ssl.SSLServerSocketFactory) javax.net.ssl.SSLServerSocketFactory.getDefault();
      javax.net.ssl.SSLServerSocket socketServidor =                    /* linea 8 */
          (javax.net.ssl.SSLServerSocket) factoriaServer.createServerSocket(9999);

      javax.net.ssl.SSLSocket socket = (javax.net.ssl.SSLSocket) socketServidor.accept();  /* linea 11 */

      java.io.InputStream inputstream = socket.getInputStream();        /* linea 13 */
      java.io.InputStreamReader inputstreamreader = new java.io.InputStreamReader(inputstream);
      java.io.BufferedReader bufferedreader = new java.io.BufferedReader(inputstreamreader);
      java.io.PrintStream retorno = new java.io.PrintStream(socket.getOutputStream());
      

      String string = null;
      while ((string = bufferedreader.readLine()) != null) {
        System.out.println(string);
        retorno.println(string);

        System.out.flush();
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}