package tcp.server;

      //Se cambia la funcion para quenos pida un int y nos devuelva un string

@FunctionalInterface
public interface Proceso {
    public String  procesaLinea(int linea);
}