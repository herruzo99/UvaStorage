package protocol.common;

public class MensajeProtocolos implements java.io.Serializable {
  private final Primitiva primitiva;
  private final String mensaje; /* HELLO, PUSH, PULL_OK */
  private final String idCola;  /* PUSH, PULL_WAIT, PULL_NOWAIT */

  /* Constructor para PUSH_OK, NOTHING, NOTUNDERSTAND */
  public MensajeProtocolos(Primitiva p)
      throws MalMensajeProtocoloException {
    if (p == Primitiva.PUSH_OK || p == Primitiva.NOTUNDERSTAND ||
        p == Primitiva.NOTHING ) {
       this.primitiva = p;
       this.mensaje = this.idCola = null;
    } else
       throw new MalMensajeProtocoloException();
  }

  /* Constructor para HELLO, PULL_OK, PULL_WAIT, PULL_NOWAIT */
  public MensajeProtocolos(Primitiva p, String mensaje)
      throws MalMensajeProtocoloException {
    if (null == p) throw new MalMensajeProtocoloException(); else switch (p) {
          case HELLO:
          case PULL_OK:
          case DENIED: //DENIED al fin y al cabo se comporta como un HELLO.
              this.mensaje = mensaje;
              this.idCola  = null;
              break;
          case PULL_WAIT:
          case PULL_NOWAIT:
              this.idCola  = mensaje;
              this.mensaje = null;
              break;
          default:
              throw new MalMensajeProtocoloException();
      }
    this.primitiva = p;
  }

  /* Constructor para PUSH */
  public MensajeProtocolos(Primitiva p, String idCola, String mensaje)
      throws MalMensajeProtocoloException {
    if (p == Primitiva.PUSH) {
      this.primitiva = p;
      this.mensaje = mensaje;
      this.idCola = idCola;
    } else 
      throw new MalMensajeProtocoloException();
  }

  public Primitiva getPrimitiva() { return this.primitiva; }
  public String getMensaje() { return this.mensaje; }
  public String getIdCola() { return this.idCola; }

  public String toString() { /* prettyPrinter de la clase */
    switch (this.primitiva) {
      case HELLO: 
      case PULL_OK:
        return this.primitiva+":"+this.mensaje ;
      case PULL_WAIT:
      case PULL_NOWAIT:
        return this.primitiva+":"+this.idCola ;
      case PUSH:
        return this.primitiva+":"+this.idCola+":"+this.mensaje ;
        
        //toString de la primitiva DENIED.
      case DENIED:
        return this.primitiva+":"+this.mensaje ;
      default :
        return this.primitiva.toString() ;
    }
  }
}