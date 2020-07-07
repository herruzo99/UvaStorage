package es.uva.inf.poo.amazingco;

import java.time.LocalDate;

import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * GroupablePickingPoint que almacena paquetes con pago a contrareembolso o no.
 * 
 * Calcula el dinero que hay que dar a AmazingCo por los paquetes a reembolso,
 * perimite modificar el número de taquillas del Kiosk creado y asignar, borrar,
 * devolver y sacar paquetes actualizando el estado del paquete a pagado si es
 * necesario.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class Kiosk extends GroupablePickingPoint {
//constantes de error
	private static final String DEMASIADAS_TAQUILLAS_A_BORRAR = "No se pueden borrar tantas taquillas.";

	private double dinero = 0;

	/**
	 * Inicializa el Kiosk con la id, ubicación, horario semanal y número de
	 * taquillas.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana
	 *                        y la hora de apertura y cierre de cada día.
	 *                        Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas del PickingPoint.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * @throws IllegalArgumentException si el numeroTaquillas es menor que 0.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int)
	 */
	public Kiosk(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			int numeroTaquillas) {
		super(id, ubicacion, horario, numeroTaquillas);
	}

	/**
	 * Inicializa el Kiosk con la id, ubicación, horario semanal, número de
	 * taquillas y operatividad dados.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana
	 *                        y la hora de apertura y cierre de cada día.
	 *                        Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas del PickingPoint.
	 * @param operativo       indica si el PackageLocker está operativo desde el
	 *                        momento creado o no.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * @throws IllegalArgumentException si el numeroTaquillas es menor que 0.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int, boolean)
	 */
	public Kiosk(String id, GPSCoordinate ubicacion, LocalTime[][] horario,
			int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, numeroTaquillas, operativo);
	}

	private void setDinero(double num) {
		dinero = num;
	}

	/**
	 * Devuelve el dinero que se le debe a AmazingCo.
	 * 
	 * @return dinero que se le debe a AmazingCO.
	 */
	public double getDinero() {
		return dinero;
	}

	/**
	 * Paga el dinero debido a AmazingCo.
	 */
	public void dineroAmazingCo() {
		setDinero(0);
	}

	/**
	 * Incrementa/decrementa el número de taquillas dado.
	 * 
	 * @param modificacion modificacion de taquillas a realizar.
	 * 
	 * @throws IllegalArgumentException si se intentan borrar mas taquillas de
	 *                                  las existentes, o mas taquillas de las
	 *                                  disponibles en el momento.
	 */
	public void modificarNumTaquillas(int modificacion) {
		if (-modificacion >= getNumeroTaquillasVacias()) {
			throw new IllegalArgumentException(DEMASIADAS_TAQUILLAS_A_BORRAR);
		}

		setNumeroTaquillas(modificacion + getNumeroTaquillas());
	}

	/**
	 * Modifica el estado del paquete a sacado.
	 * 
	 * Modifica el estado del paquete a sacado del paquete de la taquilla dada
	 * en caso de que esté pagado, si no lo está, lo paga y luego se cambia el
	 * estado a sacado de la taquilla dada.
	 * 
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia o si la fecha
	 *                                  de entrega ha sido superada.
	 * @throws IllegalStateException    si el paquete se intenta recoger pero su
	 *                                  estado es 1 (recogido) o 2 (devuelto).
	 * @throws IllegalStateException    si {@code fechaEnPlazo == False}.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#sacaPaquete(int, LocalDate,
	 *      int, LocalTime)
	 * @see es.uva.inf.poo.amazingco.Package#recogido(LocalDate)
	 */
	@Override
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada, int dia,
			LocalTime hora) {
		super.sacaPaquete(idTaquilla, fechaSacada, dia, hora);

		Package paquete = getPaquetesInterno().get(idTaquilla);
		if (!paquete.getPagado()) {
			setDinero(getDinero() + paquete.getPrecio());
			paquete.pagado();
		}
	}

}
