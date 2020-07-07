package protocol.cliente;

import protocol.common.Primitiva;
import protocol.common.MensajeProtocolo;
import protocol.common.MalMensajeProtocoloException;

public class Cliente {
  final private int PUERTO = 2000;
  static java.io.ObjectInputStream ois = null;
  static java.io.ObjectOutputStream oos = null;

  public static void main(String[] args) throws java.io.IOException {
    String host = args[0];
    String linea;

    java.net.Socket sock = new java.net.Socket(host, 2000); /* creación del socket */
    try {
      /** Creación de los canales de serialización de objetos */      
      oos = new java.io.ObjectOutputStream(sock.getOutputStream());
      ois = new java.io.ObjectInputStream(sock.getInputStream());

      /* no vamos a utilizar teclado, y vamos a probar las primitivas por programa */
      System.out.println("Pulsa <Enter> para comenzar"); System.in.read();
      /* Escenario 1 */
      prueba(new MensajeProtocolo(Primitiva.HELLO, "Pedro"));

      prueba(new MensajeProtocolo(Primitiva.PUSH, "010", "abc"));

      prueba(new MensajeProtocolo(Primitiva.PULL_NOWAIT, "010"));

      //Se añaden pruebas para ver la funcionalidad de SuperUsuario.
      
      prueba(new MensajeProtocolo(Primitiva.HELLO, "1234"));
      
      prueba(new MensajeProtocolo(Primitiva.PUSH, "010", "abc"));

      prueba(new MensajeProtocolo(Primitiva.PULL_NOWAIT, "010"));

      prueba(new MensajeProtocolo(Primitiva.HELLO, "rooot"));

      prueba(new MensajeProtocolo(Primitiva.PUSH, "010", "abc"));

      prueba(new MensajeProtocolo(Primitiva.PULL_NOWAIT, "010"));
      
      prueba(new MensajeProtocolo(Primitiva.PUSH, "01000", "abc"));

      prueba(new MensajeProtocolo(Primitiva.PULL_NOWAIT, "01000"));

      /*** aquí se supone que tiene que llegar otro cliente e insertar
      *    un mensaje en la cola */

      /* FIN del escenario 1 */
    } catch (java.io.EOFException e) {
      System.err.println("Cliente: Fin de conexión.");
    } catch (java.io.IOException e) {
      System.err.println("Cliente: Error de apertura o E/S sobre objetos: "+e);
    } catch (MalMensajeProtocoloException e) {
      System.err.println("Cliente: Error mensaje Protocolo: "+e);
    } catch (Exception e) {
      System.err.println("Cliente: Excepción. Cerrando Sockets: "+e);
    } finally {
      ois.close();
      oos.close();
      sock.close();
    }
  }

  static void prueba(MensajeProtocolo mp) throws java.io.IOException,
	      MalMensajeProtocoloException, ClassNotFoundException {
    System.out.println("> "+mp);
    oos.writeObject(mp);
    System.out.println("< "+(MensajeProtocolo) ois.readObject());
    System.out.println("Pulsa <Enter>"); System.in.read();
  }
}