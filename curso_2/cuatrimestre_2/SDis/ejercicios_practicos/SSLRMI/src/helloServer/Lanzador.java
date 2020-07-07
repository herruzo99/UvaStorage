package helloServer;

public class Lanzador {
  public static void main(String[] args) {
    try {
      javax.rmi.ssl.SslRMIClientSocketFactory rmicsf = new javax.rmi.ssl.SslRMIClientSocketFactory();
      javax.rmi.ssl.SslRMIServerSocketFactory rmissf = new javax.rmi.ssl.SslRMIServerSocketFactory();

      //  El viejo truco de crear nosotros el registro cuando rmiregistry no basta
      java.rmi.registry.Registry r = java.rmi.registry.LocateRegistry.createRegistry(1100, rmicsf, rmissf);

      helloServer.HolaImpl holaObj = new helloServer.HolaImpl("Hola amigo!", rmicsf, rmissf);      
      r.rebind("ObjetoHola", holaObj);
    } catch (Exception e) {
      System.err.println("Excepción: " + e);
    }
  }
}
