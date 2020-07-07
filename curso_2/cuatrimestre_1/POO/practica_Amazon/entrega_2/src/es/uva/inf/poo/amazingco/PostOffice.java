package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * PickingPoint que almacena una cantidad ilimitada de paquetes y permite
 * realizar entregas de paquetes certificados.
 * 
 * Calcula el dinero que hay que dar a AmazingCo por los paquetes a reembolso, y
 * asignar, borrar, devolver y sacar paquetes actualizando el estado del paquete
 * si es necesario.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 *
 */

public class PostOffice extends PickingPoint implements IdentificationRegistry {

	private double dinero = 0;
	ArrayList<Object[]> registro = new ArrayList<>();
	private static final String NO_EN_EL_REGISTRO = "El paquete no está en el registro.";

	/**
	 * Inicializa el PostOffice con la id, ubicación, horario semanal y número
	 * de taquillas.
	 * 
	 * @param id        id de la taquilla.
	 * @param ubicacion ubicación de la taquilla.
	 * @param horario   forma en la que se representa el día de la semana y la
	 *                  hora de apertura y cierre de cada día. Esquema:
	 *                  [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * 
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int)
	 */
	public PostOffice(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario) {
		super(id, ubicacion, horario, (int) Double.POSITIVE_INFINITY);
	}

	/**
	 * Inicializa el PostOffice con la id, ubicación, horario semanal, número de
	 * taquillas y operatividad.
	 * 
	 * @param id        id de la taquilla.
	 * @param ubicacion ubicación de la taquilla.
	 * @param horario   forma en la que se representa el día de la semana y la
	 *                  hora de apertura y cierre de cada día. Esquema:
	 *                  [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param operativo indica si el PackageLocker está operativo desde el
	 *                  momento creado o no.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int ,boolean)
	 */
	public PostOffice(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			boolean operativo) {
		super(id, ubicacion, horario, (int) Double.POSITIVE_INFINITY,
				operativo);
	}

	private ArrayList<Object[]> getRegistro() {
		return registro;
	}

	private void setDinero(double num) {
		dinero = num;
	}

	/**
	 * Devuelve el dinero que se le debe a AmazingCo
	 * 
	 * @return dinero que se le debe a AmazingCO
	 */
	public double getDinero() {
		return dinero;
	}

	/**
	 * Modifica el estado del paquete a sacado.
	 * 
	 * Modifica el estado del paquete a sacado del paquete de la taquilla dada
	 * en caso de que esté pagado, si no lo está, lo paga y luego se cambia el
	 * estado a sacado de la taquilla dada.
	 * 
	 * @throws IllegalStateException    Si el paquete está certificado.
	 * @throws IllegalArgumentException Si el número de taquilla es erróneo.
	 * @throws IllegalStateException    Si la taquilla está vacia o si la fecha
	 *                                  de entrega ha sido superada.
	 * @throws IllegalStateException    Si el paquete se intenta recoger pero su
	 *                                  estado es 1 (recogido) o 2 (devuelto).
	 * @throws IllegalStateException    Si {@code fechaEnPlazo == False}.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#sacaPaquete(int, LocalDate,
	 *      int, LocalTime)
	 * @see es.uva.inf.poo.amazingco.Package#recogido(LocalDate)
	 */
	@Override
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada, int dia,
			LocalTime hora) {

		Package paquete = getPaquetesInterno().get(idTaquilla);

		if (paquete == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}

		if (paquete.getCertificado()) {
			throw new IllegalStateException("El paquete está certificado");
		}

		super.sacaPaquete(idTaquilla, fechaSacada, dia, hora);

		if (!paquete.getPagado()) {
			setDinero(getDinero() + paquete.getPrecio());
			paquete.pagado();
		}

	}

	private int buscaRegistro(String packageCode) {
		int i = 0;
		while (i < getRegistro().size()) {
			if (((Package) getRegistro().get(i)[0]).getId() == packageCode) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#isPackagePickupRegistered(String)
	 */
	@Override
	public boolean isPackagePickupRegistered(String packageCode) {
		return (buscaRegistro(packageCode) != -1);
	}

	/**
	 * @throws IllegalArgumentException si el paquete no está en el registro.
	 * 
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#getPackageRegistered(String)
	 */
	@Override
	public Package getPackageRegistered(String packageCode) {
		if (!isPackagePickupRegistered(packageCode)) {
			throw new IllegalArgumentException(NO_EN_EL_REGISTRO);
		}
		return (Package) getRegistro().get(buscaRegistro(packageCode))[0];

	}

	/**
	 * @throws IllegalArgumentException si el paquete no está en el registro.
	 * 
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#getRegisteredIdFor(String)
	 */
	@Override
	public String getRegisteredIdFor(String packageCode) {
		if (!isPackagePickupRegistered(packageCode)) {
			throw new IllegalArgumentException(NO_EN_EL_REGISTRO);
		}
		return (String) getRegistro().get(buscaRegistro(packageCode))[1];
	}

	/**
	 * @throws IllegalArgumentException si el paquete no está en el registro.
	 * 
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#getPickupDateFor(String)
	 */
	@Override
	public LocalDate getPickupDateFor(String packageCode) {
		if (!isPackagePickupRegistered(packageCode)) {
			throw new IllegalArgumentException(NO_EN_EL_REGISTRO);
		}
		return (LocalDate) getRegistro().get(buscaRegistro(packageCode))[2];
	}

	/**
	 * @throws IllegalArgumentException si el paquete indicado es null o si la
	 *                                  fecha de vencimiento ya ha pasado.
	 * 
	 * @see es.uva.inf.poo.amazingco.IdentificationRegistry#registerCertifiedPackagePickup(Package,
	 *      String, LocalDate)
	 */
	@Override
	public void registerCertifiedPackagePickup(Package p, String dni,
			LocalDate pickupDate) {

		if (pickupDate == null) {
			throw new IllegalArgumentException("El pickupDate es null.");
		}
		if (pickupDate.isAfter(p.getFecha())) {
			throw new IllegalArgumentException(
					"La fecha de vencimiento ya ha pasado.");
		}
		Object[] resgistroPaquete = { p, dni, pickupDate };
		getRegistro().add(resgistroPaquete);

	}

	/**
	 * Comprueba si un PostOffice es borrable.
	 * 
	 * Es borrable si no contiene ningún paquete.
	 * 
	 * @return true si no contiene ningún paquete.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#borrable()
	 */
	@Override
	public boolean borrable() {
		ArrayList<Package> paq = getPaquetes();
		return paq.isEmpty();

	}

	/**
	 * Comprueba si un paquete puede ser guardado en el PostOffice o no.
	 * 
	 * Un paquete siempre es valido en el PostOffice.
	 * 
	 * @param paquete paquete a comprobar.
	 * @return true.
	 * @throws IllegalArgumentException si el paquete es null.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		return true;
	}
}
