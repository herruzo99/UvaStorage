package servidor;
import caja.comun.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RealCaja extends UnicastRemoteObject implements Caja {
	
	Acumulador a;
	
	public RealCaja() throws RemoteException {
        super();
	}
	
	@Override
	public void guarda(Acumulador x) throws RemoteException {
		a=x;
		
	}

	@Override
	public Acumulador recupera() throws RemoteException {
		return a;
	}
}
