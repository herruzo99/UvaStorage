package udp.callable;

public class SessionCallableEcho
               implements java.util.concurrent.Callable<Boolean> {
    final java.net.SocketAddress sa;
    static private int idCounter = 0;
    private final int id;
    private java.net.DatagramPacket dp;
    private java.net.DatagramSocket ds;
    
    SessionCallableEcho(java.net.SocketAddress sa,
                  java.net.DatagramSocket ds) {
        this.sa = sa;
        this.ds = ds;
        this.id = ++idCounter;
    }

    @Override
    public Boolean call() {
        String texto = new String(dp.getData());
        if (!texto.startsWith("Adios.") ) {
            System.out.println("<"+id+">["+sa+"]: "+texto);
            try { this.ds.send(dp); }
            catch (java.io.IOException ioe) {
                System.err.println("SessionCallableEcho: IOE: "+ioe);
            }
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    public int getId() {
        return id;
    }
    public void putDP(java.net.DatagramPacket dp) {
        this.dp = dp;
    }
}