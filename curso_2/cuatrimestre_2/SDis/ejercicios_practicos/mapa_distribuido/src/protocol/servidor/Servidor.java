package protocol.servidor;

import protocol.common.MensajeProtocolos;
import protocol.common.Primitiva;
import protocol.common.UDPHelper;
import protocol.common.MalMensajeProtocoloException;
import distribuidos.mapqueue.MultiMap;

public class Servidor {
  static final private int PUERTO = 1919;
  static final private int MAXDATAGRAMA = 1024;

  public static void main(String[] args) throws java.io.IOException {
    String mensaje;
    MensajeProtocolos me;
    MensajeProtocolos ms;
    MultiMap<String, String> mapa = new MultiMap<>();

    byte[] bytesOut = null;
    byte[] bytesIn  = new byte[MAXDATAGRAMA];
    UDPHelper serialHandler = new UDPHelper(bytesIn);

    java.net.DatagramPacket output = null;
    java.net.DatagramPacket input = null;

    java.net.DatagramSocket socket;
    java.net.InetAddress iaServer = null;

    try {
      socket = new java.net.DatagramSocket(PUERTO);
    } catch (java.net.SocketException e) {
      System.out.println("Puerto ocupado en el servidor: " + e);
      return;
    }

    // attend protocol primitives.
    while (true) {
      try {
        java.net.DatagramPacket datagrama =
               new java.net.DatagramPacket(bytesIn, bytesIn.length);
        socket.receive(datagrama);

        me = (MensajeProtocolos) serialHandler.aMensaje();
        System.out.println("<<" + me);

        switch (me.getPrimitiva()) {
        case HELLO:
             ms = new MensajeProtocolos(Primitiva.HELLO, "Hola!");

             datagrama.setData(serialHandler.aBytes(ms));
             socket.send(datagrama);
             break;
        case PUSH:
             mapa.push(me.getIdCola(), me.getMensaje());
             synchronized (mapa) { // sí, con sus fallos :D
               mapa.notify();
             }
             ms = new MensajeProtocolos(Primitiva.PUSH_OK);

             datagrama.setData(serialHandler.aBytes(ms));
             socket.send(datagrama);
             break;
        case PULL_NOWAIT:
         if (null != (mensaje = mapa.pop(me.getIdCola()))) {
               ms = new MensajeProtocolos(Primitiva.PULL_OK, mensaje);
             } else {
               ms = new MensajeProtocolos(Primitiva.NOTHING);
             }

             datagrama.setData(serialHandler.aBytes(ms));
             socket.send(datagrama);
             break;
        case PULL_WAIT: // a consecuencia de 1 solo hilo ...
         String idCola = me.getIdCola();
             Runnable respuesta = () -> {
           String mensaje_;
               try {
                 java.net.SocketAddress sa =
                     datagrama.getSocketAddress();
                 synchronized (mapa) { // ya sabemos donde falla.
                   while (null ==(mensaje_=mapa.pop(idCola))) {
                     mapa.wait();
                   }
                 }
                 MensajeProtocolos ms_ =
           new MensajeProtocolos(Primitiva.PULL_OK, mensaje_);
                 byte[] out = serialHandler.aBytes(ms_);
                 socket.send(
                   new java.net.DatagramPacket(out, out.length, sa));
               } catch (MalMensajeProtocoloException mmpe) {
                 System.out.println(mmpe);
               } catch (InterruptedException ie) {
                 System.out.println(ie);
               } catch (java.io.IOException ioe) {
                 System.out.println(ioe);
               }
             } ;
             new Thread(respuesta).start();
             break;
        default:
             ms = new MensajeProtocolos(Primitiva.NOTUNDERSTAND);

             datagrama.setData(serialHandler.aBytes(ms));
             socket.send(datagrama);
             // break;             // break;
           }
        } catch (java.io.IOException ioe) {
           System.out.println(ioe);
        } catch (MalMensajeProtocoloException mmpe) {
           System.out.println(mmpe);
        }
      }
   }
}