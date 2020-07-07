package protocol.servidor;

import protocol.common.MensajeProtocolos;
import protocol.common.Primitiva;
import distribuidos.mapqueue.MultiMap;

class Sirviente implements Runnable {
  private final java.net.Socket socket;
  private final MultiMap<String, String> mapa;
  private final java.io.ObjectOutputStream oos;
  private final java.io.ObjectInputStream  ois;
  private final int ns;
  private static java.util.concurrent.atomic.AtomicInteger nInstance=
	           new java.util.concurrent.atomic.AtomicInteger();
  
  
  private boolean su;
  private static java.util.ArrayList<String> passwd;

  Sirviente(java.net.Socket s, MultiMap
           <String, String> c, java.util.ArrayList<String> passwd) throws java.io.IOException {
    this.socket = s;
    this.mapa   = c;
    this.ns     = nInstance.getAndIncrement();
    this.oos = new java.io.ObjectOutputStream(socket.getOutputStream());
    this.ois = new java.io.ObjectInputStream(socket.getInputStream());
    
    this.passwd = passwd;
    //Al empezar no eres SuperUsuario.
    su = false;
  }

  public void run() {
    try {
      while (true) {
	String mensaje;
        MensajeProtocolos me = (MensajeProtocolos) ois.readObject();
        MensajeProtocolos ms;

        System.out.println("Sirviente: "+ns+": [ME: "+ me +"]"); // depuracion me
        switch (me.getPrimitiva()) {
        case HELLO:
            /* 
            Si el mensaje coincide con una de las contraseña se otorga SuperUsuario.
            */
            if(passwd.contains(me.getMensaje())){
                su = true;
                            ms = new MensajeProtocolos(Primitiva.HELLO, "["+ns+": SuperUsuario]");

            }else{
                su = false;
                            ms = new MensajeProtocolos(Primitiva.HELLO, "["+ns+": Usuario]");

            }
            break;
        case PUSH:
            
            /*
             Comprueba si la cola ya existe o si eres su.
                Si existe o eres su te deja añadir el mensaje.
                Si no te manda un mensaje con la primitiva DENIED.
            */
            if(mapa.existe(me.getIdCola()) || su){
                mapa.push(me.getIdCola(), me.getMensaje());
                synchronized (mapa) {
                   mapa.notify();
                }
                ms = new MensajeProtocolos(Primitiva.PUSH_OK);
            }else{
                ms = new MensajeProtocolos(Primitiva.DENIED, "No tienes suficientes permisos para crear una cola.");
            }
            break;
        case PULL_NOWAIT:
            if (null != (mensaje = mapa.pop(me.getIdCola()))) {
              ms = new MensajeProtocolos(Primitiva.PULL_OK, mensaje);
            } else {
              ms = new MensajeProtocolos(Primitiva.NOTHING);
            }
            break;
        case PULL_WAIT:
	    synchronized (mapa) {
               while (null == (mensaje = mapa.pop(me.getIdCola()))) {
                 mapa.wait();
	       }
            }
            ms = new MensajeProtocolos(Primitiva.PULL_OK, mensaje);
            break;
        default:
            ms = new MensajeProtocolos(Primitiva.NOTUNDERSTAND);
        } /* fin switch */
        oos.writeObject(ms);
         /* mensaje saliente de depuración */
        System.out.println("Sirviente: "+ns+": [RESP: "+ms+"]");
      }
    } catch (java.io.IOException e) {
      System.err.println("Sirviente: "+ns+": [FIN]");
    } catch (ClassNotFoundException ex) {
      System.err.println("Sirviente: "+ns+": [ERR Class not found]");
    } catch (InterruptedException ex) {
      System.err.println("Sirviente: "+ns+": [Interrumpido wait()]");
    } catch (protocol.common.MalMensajeProtocoloException ex) {
      System.err.println("Sirviente: "+ns+": [ERR MalMensajeProtocolo !!]");
    } finally {
      /* seguimos deshaciéndonos de los sockets y canales abiertos. */
      try {
          ois.close();
          oos.close();
          socket.close();
      } catch (Exception x) {
          System.err.println("Sirviente: "+ns+": [ERR Cerrando sockets]");
      }
    }
  }
}