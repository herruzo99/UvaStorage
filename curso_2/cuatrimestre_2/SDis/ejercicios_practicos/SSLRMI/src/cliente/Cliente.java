package cliente;

public class Cliente {
  public static void main(String[] args) {
    try {
      javax.rmi.ssl.SslRMIClientSocketFactory rmicsf = new javax.rmi.ssl.SslRMIClientSocketFactory();
      java.rmi.registry.Registry reg =
            java.rmi.registry.LocateRegistry.getRegistry("localhost", 1100, rmicsf);
      helloServer.Hola holaRemoto = (helloServer.Hola) reg.lookup("ObjetoHola");
      System.out.println("ObjetoHola responde: " + holaRemoto.dimeHola());
    } catch (Exception e) {
      System.err.println("Excepción... " + e);
    }
  }
}