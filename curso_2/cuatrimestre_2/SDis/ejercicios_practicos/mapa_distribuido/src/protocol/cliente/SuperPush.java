package protocol.cliente;

import protocol.common.Primitiva;
import protocol.common.MensajeProtocolos;
import protocol.common.MalMensajeProtocoloException;

public class SuperPush {
  final private int PUERTO = 2000;
  final static String user = "admin";
  static java.io.ObjectInputStream ois = null;
  static java.io.ObjectOutputStream oos = null;

  public static void main(String[] args) throws java.io.IOException {
    if (args.length != 3) {
      System.out.println("Use:\njava protocol.cliente.Hello host clave valor");
      System.exit(-1);
    }
    String host  = args[0]; // localhost o ip|dn del servidor
    String clave = args[1]; // clave del deposito
    String valor = args[2]; // valor del par (clave,valor)

    java.net.Socket sock = new java.net.Socket(host, 2000);
    try {
      oos = new java.io.ObjectOutputStream(sock.getOutputStream());
      ois = new java.io.ObjectInputStream(sock.getInputStream());
      
      //Se pasa a modo SuperUsuario.
      pruebaSilenciosa(new MensajeProtocolos(Primitiva.HELLO, user));
      
      //Se añade a la cola.
      prueba(new MensajeProtocolos(Primitiva.PUSH, clave, valor));

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

  static void prueba(MensajeProtocolos mp) throws java.io.IOException,
	      MalMensajeProtocoloException, ClassNotFoundException {
    System.out.println("> "+mp);
    oos.writeObject(mp);
    System.out.println("< "+(MensajeProtocolos) ois.readObject());
  }
  /*
  Se crea para que el HELLO no sea visible.
  */
  static void pruebaSilenciosa(MensajeProtocolos mp) throws java.io.IOException,
	      MalMensajeProtocoloException, ClassNotFoundException {
    oos.writeObject(mp);
    ois.readObject();
  }
} 