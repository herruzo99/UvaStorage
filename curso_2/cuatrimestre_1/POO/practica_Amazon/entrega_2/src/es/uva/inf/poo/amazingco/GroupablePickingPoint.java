package es.uva.inf.poo.amazingco;

import java.time.LocalTime;
import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Agrupacion de Kiosks y PackageLockers.
 * 
 * Su función principal es poder crear un array de Kiosk y PAckageLockers
 * indistintamente en el PickingPointHub.
 * 
 * @see es.uva.inf.poo.amazingco.PickingPointHub
 * 
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 *
 */
public abstract class GroupablePickingPoint extends PickingPoint {

	/**
	 * Inicializa el GroupablePickingPoint con la id, ubicación, horario semanal
	 * y número de taquillas.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana
	 *                        y la hora de apertura y cierre de cada día.
	 *                        Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas del PickingPoint.
	 * 
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
	public GroupablePickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas) {
		super(id, ubicacion, horario, numeroTaquillas);
	}

	/**
	 * Inicializa el GroupablePickingPoint con la id, ubicación, horario
	 * semanal, número de taquillas y operatividad dados.
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
	public GroupablePickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, numeroTaquillas, operativo);
	}

	/**
	 * Comprueba si un paquete puede ser guardado en el GroupablePickingPoint o
	 * no.
	 * 
	 * Un paquete es valido siempre que no sea certificado.
	 * 
	 * @throws IllegalArgumentException si el paquete es null.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		return !paquete.getCertificado();
	}

	/**
	 * Comprueba si un GroupablePickingPoint es borrable o no.
	 * 
	 * Es borrable si no contiene ningún paquete.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#borrable()
	 */
	@Override
	public boolean borrable() {
		ArrayList<Package> paq = getPaquetes();
		return paq.isEmpty();

	}

}
