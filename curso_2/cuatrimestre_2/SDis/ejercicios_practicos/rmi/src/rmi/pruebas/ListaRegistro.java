package rmi.pruebas;

public class ListaRegistro {
  public static void main(String [] args) {
    try {
      System.out.println("Registro: ");
      for (String n : java.rmi.Naming.list("rmi://157.88.125.192/"))
        System.out.println("> " + n);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}