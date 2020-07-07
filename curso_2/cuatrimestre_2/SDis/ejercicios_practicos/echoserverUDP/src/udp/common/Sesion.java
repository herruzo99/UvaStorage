package udp.common;

public class Sesion {
    final java.net.SocketAddress sa;
    static private int idCounter = 0;
    private final int id;
    public Sesion(java.net.SocketAddress sa) {
        this.sa = sa;
        this.id = ++Sesion.idCounter;
    }

    public int getId() {
        return id;
    }
}