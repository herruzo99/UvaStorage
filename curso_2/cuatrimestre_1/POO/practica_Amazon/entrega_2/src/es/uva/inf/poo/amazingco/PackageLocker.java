package es.uva.inf.poo.amazingco;

import java.time.LocalTime;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * GroupablePickingPoint que almacena paquetes ya pagados.
 * 
 * Permite asignar, borrar, devolver y sacar paquetes actualizando el estado del
 * paquete si es necesario.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class PackageLocker extends GroupablePickingPoint {
	/**
	 * Inicializa el PackageLocker con la id, ubicación, horario semanal y
	 * número de taquillas.
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
	public PackageLocker(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas) {
		super(id, ubicacion, horario, numeroTaquillas);

	}

	/**
	 * Inicializa el PackageLocker con la id, ubicación, horario semanal, número
	 * de taquillas y operatividad.
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
	public PackageLocker(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas, boolean operativo) {
		super(id, ubicacion, horario, numeroTaquillas, operativo);
	}

	/**
	 * Comprueba si un paquete puede ser guardado en el PackageLocker o no.
	 * 
	 * Un paquete es valido siempre que no sea certificado ni a contrarrembolso.
	 * 
	 * @return true si el paquete no es certificado ni a contrarrembolso.
	 * @throws IllegalArgumentException si el paquete es null.
	 * @see es.uva.inf.poo.amazingco.GroupablePickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		return paquete.getPagado() && super.paqueteValido(paquete);
	}

}
