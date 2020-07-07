package echoclientseguro;

public class EchoClient {
  public static void main(String[] arstring) {
    try {
      javax.net.ssl.SSLSocketFactory factoria =
          (javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault();
      javax.net.ssl.SSLSocket sslsocket = (javax.net.ssl.SSLSocket) factoria.createSocket("localhost", 9999);

      java.io.InputStreamReader inputstreamreader = new java.io.InputStreamReader(System.in);
      java.io.BufferedReader bufferedreader = new java.io.BufferedReader(inputstreamreader);
      java.io.OutputStream outputstream = sslsocket.getOutputStream();
      java.io.OutputStreamWriter outputstreamwriter = new java.io.OutputStreamWriter(outputstream);
      java.io.BufferedWriter bufferedwriter = new java.io.BufferedWriter(outputstreamwriter);

      
      
      java.io.InputStream sslinputstream = sslsocket.getInputStream();        /* linea 13 */
      java.io.InputStreamReader sslinputstreamreader = new java.io.InputStreamReader(sslinputstream);
      java.io.BufferedReader sslbufferedreader = new java.io.BufferedReader(sslinputstreamreader);
      
      String string = null;
      while ((string = bufferedreader.readLine()) != null) {
        bufferedwriter.write(string + '\n');
        bufferedwriter.flush();
        
        String input = sslbufferedreader.readLine();
        System.out.println(input);
        
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
