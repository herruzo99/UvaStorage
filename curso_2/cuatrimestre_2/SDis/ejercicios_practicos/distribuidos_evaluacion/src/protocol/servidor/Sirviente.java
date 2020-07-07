package protocol.servidor;

import protocol.common.MensajeProtocolo;
import protocol.common.Primitiva;

import java.util.ArrayList;

import distribuidos.mapqueue.MultiMap;

class Sirviente implements Runnable {
  private final java.net.Socket socket;
  private final MultiMap<String, String> mapa;
  private final java.io.ObjectOutputStream oos;
  private final java.io.ObjectInputStream  ois;
  private final int ns;
  private boolean superUsuario = false;
  private static java.util.concurrent.atomic.AtomicInteger nInstance=
	           new java.util.concurrent.atomic.AtomicInteger();
  
  Sirviente(java.net.Socket s, MultiMap
           <String, String> c) throws java.io.IOException {
    this.socket = s;
    this.mapa   = c;
    this.ns     = nInstance.getAndIncrement();
    this.oos = new java.io.ObjectOutputStream(socket.getOutputStream());
    this.ois = new java.io.ObjectInputStream(socket.getInputStream());
  }

  public void run() {
	 ArrayList<String> checkpw = null;
    try {
      checkpw = new ArrayList<String>();
      checkpw.add("1234");
      while (true) {
	String mensaje;
        MensajeProtocolo me = (MensajeProtocolo) ois.readObject();
        MensajeProtocolo ms;

        System.out.println("Sirviente: "+ns+": [ME: "+ me); // depuracion me
        switch (me.getPrimitiva()) {
        case HELLO:
        	String password = me.getMensaje();
            ms = new MensajeProtocolo(Primitiva.HELLO, "["+ns+": "+socket+"]");
            if(checkpw.contains(password)){
            	this.superUsuario=true;
            	System.out.println("Super usuario");
            }
            break;
        case PUSH:
        	boolean existe = mapa.colaExist(me.getIdCola());
        	if(existe){
        		mapa.push(me.getIdCola(), me.getMensaje());
        		ms = new MensajeProtocolo(Primitiva.PUSH_OK);
        	}else{
        		if(this.superUsuario ){
        			mapa.push(me.getIdCola(), me.getMensaje());
        			ms = new MensajeProtocolo(Primitiva.PUSH_OK);
        		}else{
        			ms = new MensajeProtocolo(Primitiva.DENIED);
        		}
        	}
	    synchronized (mapa) {
	       mapa.notify();
	    }
            break;
        case PULL_NOWAIT:
            if (null != (mensaje = mapa.pop(me.getIdCola()))) {
              ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
            } else {
              ms = new MensajeProtocolo(Primitiva.NOTHING);
            }
            break;
        case PULL_WAIT:
	    synchronized (mapa) {
               while (null == (mensaje = mapa.pop(me.getIdCola()))) {
                 mapa.wait();
	       }
            }
            ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
            break;
        default:
            ms = new MensajeProtocolo(Primitiva.NOTUNDERSTAND);
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