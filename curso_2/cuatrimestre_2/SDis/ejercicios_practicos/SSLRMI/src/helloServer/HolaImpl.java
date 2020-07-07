package helloServer;

public class HolaImpl extends java.rmi.server.UnicastRemoteObject implements Hola {
  private final String mensaje; /* mensajito de bienvenida */

  public HolaImpl(String mensa,
        java.rmi.server.RMIClientSocketFactory rmicsf,  /* factoría de socket de cliente */
        java.rmi.server.RMIServerSocketFactory rmissf) /* factoría de socket de servidor */
        throws java.rmi.RemoteException {
    super(0, rmicsf, rmissf);  /* mira la página del manual si tienes dudas */
    mensaje = new String(mensa);
  }

  public String dimeHola() throws java.rmi.RemoteException {
    return mensaje;
  }
}