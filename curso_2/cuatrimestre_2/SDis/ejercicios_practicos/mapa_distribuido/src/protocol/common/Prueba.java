package protocol.common;

public class Prueba {

  public static void main(String [] args) {
    try {
      MensajeProtocolos mp = new MensajeProtocolos(Primitiva.HELLO, "hola") ;
      System.out.println(mp);
      mp = new MensajeProtocolos(Primitiva.PULL_WAIT, "ca") ;
      System.out.println(mp);
      mp = new MensajeProtocolos(Primitiva.PULL_NOWAIT, "cb") ;
      System.out.println(mp);
      mp = new MensajeProtocolos(Primitiva.PULL_OK, "mens") ;
      System.out.println(mp);
      mp = new MensajeProtocolos(Primitiva.PUSH, "c1", "m1.1") ;
      System.out.println(mp);
      mp = new MensajeProtocolos(Primitiva.PUSH_OK) ;
      System.out.println(mp);
      mp = new MensajeProtocolos(Primitiva.NOTHING) ;
      System.out.println(mp);
      mp = new MensajeProtocolos(Primitiva.NOTUNDERSTAND) ;
      System.out.println(mp);

      /*
      mp = new MensajeProtocolo(Primitiva.PULL_NOWAIT) ;
      System.out.println(mp);*/

    } catch (MalMensajeProtocoloException mmpe) {
      System.err.println(mmpe) ;
    }

  }
}