package protocol.common;

public class Prueba {

  public static void main(String [] args) {
    try {
      MensajeProtocolo mp = new MensajeProtocolo(Primitiva.HELLO, "hola") ;
      System.out.println(mp);
      mp = new MensajeProtocolo(Primitiva.PULL_WAIT, "ca") ;
      System.out.println(mp);
      mp = new MensajeProtocolo(Primitiva.PULL_NOWAIT, "cb") ;
      System.out.println(mp);
      mp = new MensajeProtocolo(Primitiva.PULL_OK, "mens") ;
      System.out.println(mp);
      mp = new MensajeProtocolo(Primitiva.PUSH, "c1", "m1.1") ;
      System.out.println(mp);
      mp = new MensajeProtocolo(Primitiva.PUSH_OK) ;
      System.out.println(mp);
      mp = new MensajeProtocolo(Primitiva.NOTHING) ;
      System.out.println(mp);
      mp = new MensajeProtocolo(Primitiva.NOTUNDERSTAND) ;
      System.out.println(mp);

      /*
      mp = new MensajeProtocolo(Primitiva.PULL_NOWAIT) ;
      System.out.println(mp);*/

    } catch (MalMensajeProtocoloException mmpe) {
      System.err.println(mmpe) ;
    }

  }
}