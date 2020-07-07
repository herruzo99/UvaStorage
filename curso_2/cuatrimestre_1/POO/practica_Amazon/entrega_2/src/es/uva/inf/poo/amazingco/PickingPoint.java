package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Punto de recogida de paquetes donde se administra su asignación y extración.
 * 
 * Tiene una id como tambien la ubicación, horario de apertura y cierre semanal
 * y su estado de operatividad (operativo o fuera de servicio).
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 *
 */
public abstract class PickingPoint {

	private String id;
	private GPSCoordinate ubicacion;
	private LocalTime[][] horario;
	private int numeroTaquillas;
	private ArrayList<Package> paquetes;
	private int ocupadas;
	private boolean operativo;
	private static final String TAQUILLA_VACIA = "Esta taquilla está vacia.";
	private static final String NUMERO_TAQUILLA_ERRONEO = "Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1";

	/**
	 * Inicializa el PickingPoint con la id, ubicación, horario semanal, número
	 * de taquillas y operatividad dados.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana
	 *                        y la hora de apertura y cierre de cada día.
	 *                        Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas del PickingPoint.
	 * @param operativo       indica si el PickingPoint está operativo desde el
	 *                        momento creado o no.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * @throws IllegalArgumentException si el numeroTaquillas es menor que 0.
	 */
	public PickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas, boolean operativo) {

		// Comprueba que los argumentos dados para la inicialización del
		// PickingPoint
		// sean válidos.
		if (id == null || ubicacion == null || horario == null) {
			throw new IllegalArgumentException(
					"Uno de los argumentos es null.");
		}
		// Comprobación de las especificaciones del horario. Esquema:
		// [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
		if (horario.length != 7) {
			throw new IllegalArgumentException(
					"Son necesarios los horarios de los 7 dias de la semana.");
		}
		for (int i = 0; i < 7; i++) {
			if (horario[i] == null || horario[i].length != 2) {
				throw new IllegalArgumentException("Días mal introducidos.");
			}

			if (horario[i][0] == null || horario[i][1] == null) {
				throw new IllegalArgumentException("Horas mal introducidas.");
			}
			if (horario[i][0].isAfter(horario[i][1])) {

				throw new IllegalArgumentException("Horas mal introducidas.");
			}
		}

		if (numeroTaquillas < 1) {
			throw new IllegalArgumentException(
					"Número de taquillas no positivo.");

		}
		this.id = id;
		this.ubicacion = ubicacion;
		this.horario = horario;
		this.operativo = operativo;
		this.numeroTaquillas = numeroTaquillas;

		this.paquetes = new ArrayList<>();

	}

	/**
	 * Inicializa el PickingPoint operativo con la id, ubicación, horario
	 * semanal y número de taquillas.
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
	 */
	public PickingPoint(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, int numeroTaquillas) {
		this(id, ubicacion, horario, numeroTaquillas, true);
	}

	/**
	 * Comprueba la operatividad.
	 * 
	 * Devuelve true si el PickingPoint está operativo y false en caso
	 * contrario.
	 * 
	 * @return true si el PickingPoint está operativo y false si no lo está.
	 */
	public boolean getOperativo() {
		return operativo;

	}

	protected void setOperativo(boolean op) {
		operativo = op;
	}

	private void setOcupadas(int numero) {
		ocupadas = numero;
	}

	protected void setNumeroTaquillas(int numero) {
		numeroTaquillas = numero;
	}

	/**
	 * 
	 * Devuelve el número de taquillas totales.
	 * 
	 * @return número de taquillas totales.
	 */
	public int getNumeroTaquillas() {
		return numeroTaquillas;
	}

	/**
	 *
	 * Devuelve una copia de las taquillas.
	 * 
	 * Se asegura de solo devolver los paquetes y no devolver ningún null.
	 * 
	 * @return copia de taquillas del PickingPoint.
	 */
	public ArrayList<Package> getPaquetes() {
		ArrayList<Package> listaPaquetes = new ArrayList<>(
				getPaquetesInterno());
		listaPaquetes.removeAll(Collections.singleton(null));
		return listaPaquetes;
	}

	protected ArrayList<Package> getPaquetesInterno() {
		return paquetes;
	}

	/**
	 * Comprueba si un PickingPoint es borrable.
	 * 
	 * @return true si es borrable.
	 */
	public abstract boolean borrable();

	/**
	 * Devuelve la id del PickingPoint.
	 * 
	 * @return id del PickingPoint.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Devuleve la una copia del horario.
	 * 
	 * @return copia del horario.
	 */
	public LocalTime[][] getHorario() {
		LocalTime[][] devolver = new LocalTime[7][2];
		System.arraycopy(horario, 0, devolver, 0, 7);
		return devolver;
	}

	/**
	 * Devuelve el número de taquillas llenas.
	 * 
	 * @return número de taquillas llenas.
	 */
	public int getNumeroTaquillasLlenas() {
		return ocupadas;
	}

	/**
	 * Devuelve el número de taquillas vacías.
	 * 
	 * @return número de taquillas vacías.
	 */
	public int getNumeroTaquillasVacias() {
		return getNumeroTaquillas() - getNumeroTaquillasLlenas();
	}

	/**
	 * Devuelve la copia de la ubicación geográfica.
	 * 
	 * @return copia de la ubicación geográfica.
	 */
	public GPSCoordinate getUbicacion() {
		return new GPSCoordinate(ubicacion.getLatitudeGD(),
				ubicacion.getLongitudeGD());
	}

	/**
	 * Cambia la operatividad.
	 * 
	 * Pasa el PickingPoint a operativo si está fuera de servicio y viceversa.
	 */
	public void operatividad() {
		setOperativo(!getOperativo());
	}

	protected int buscaPaquete(String idPaquete) {
		int i = 0;

		while (i < getPaquetesInterno().size()) {
			if (getPaquetesInterno().get(i) != null
					&& getPaquetesInterno().get(i).getId() == idPaquete) {

				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * Devuelve el número de taquilla en el que se encuentra el paquete indicado
	 * por id.
	 * 
	 * @param idPaquete id del paquete.
	 * @return Número de la taquilla en la que está el paquete.
	 * @throws IllegalArgumentException Si la id es null
	 * @throws IllegalArgumentException Si no existe el paquete en el
	 *                                  PickingPoint indicado.
	 */
	public int localizaPaquete(String idPaquete) {
		if (idPaquete == null) {
			throw new IllegalArgumentException("La id es null.");
		}
		int posicion = buscaPaquete(idPaquete);
		if (posicion == -1) {
			throw new IllegalArgumentException(
					"No existe ese paquete en el PickingPoint.");
		} else {
			return posicion;
		}

	}

	/**
	 * Comprueba si un paquete puede ser guardado en el PickingPoint o no.
	 * 
	 * @param paquete paquete a comprobar.
	 * @return true si se puede guardar y false si no.
	 */
	public abstract boolean paqueteValido(Package paquete);

	/**
	 * Asigna el paquete dado a una taquilla.
	 * 
	 * El paquete es guardado en la primera taquilla disponible.
	 * 
	 * @param paquete paquete a guardar.
	 * @throws IllegalArgumentException Si el paquete es null.
	 * @throws IllegalStateException    Si el PickingPoint está lleno.
	 * @throws IllegalStateException    Si el PickingPoint no está operativo.
	 * @throws IllegalStateException    Si hay otro paquete con la misma id.
	 * @throws IllegalArgumentException Si el paquete no se puede guardar en el
	 *                                  PickingPoint.
	 */
	public void asignaPaquete(Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		if (!getOperativo()) {
			throw new IllegalStateException("PickingPoint no operativo.");
		}
		if (getNumeroTaquillasLlenas() == getNumeroTaquillas()) {
			throw new IllegalStateException("PickingPoint lleno.");
		}

		if (!paqueteValido(paquete)) {
			throw new IllegalArgumentException(
					"Paquete no valido para el picking point.");
		}

		// comprueba que no haya un paquete con la misma id en el PickingPoint.

		if (buscaPaquete(paquete.getId()) != -1) {
			throw new IllegalStateException("Hay otro paquete con la misma id");
		}

		// guarda el paquete en la primera taquilla libre.
		int i = 0;
		while (i < getNumeroTaquillas()) {
			if (getPaquetesInterno().size() == i) {
				getPaquetesInterno().add(paquete);
				i = getNumeroTaquillas();

			} else if (getPaquetesInterno().get(i) == null) {

				getPaquetesInterno().set(i, paquete);
				i = getNumeroTaquillas();
			} else {
				i++;
			}

		}

		setOcupadas(getNumeroTaquillasLlenas() + 1);

	}

	/**
	 * Devuelve el paquete en la taquilla con la id dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que sacar el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 * @return paquete que estaba en la taquilla con la id indicada.
	 */
	public Package getPaquete(int idTaquilla) {

		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(NUMERO_TAQUILLA_ERRONEO);
		}

		if (idTaquilla >= getPaquetesInterno().size()
				|| getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException(TAQUILLA_VACIA);
		}

		return getPaquetesInterno().get(idTaquilla);
	}

	/**
	 * Borra el paquete de la taquilla con la id dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que sacar el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 */
	public void borraPaquete(int idTaquilla) {

		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(NUMERO_TAQUILLA_ERRONEO);
		}

		if (idTaquilla >= getPaquetesInterno().size()
				|| getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException(TAQUILLA_VACIA);
		}
		getPaquetesInterno().set(idTaquilla, null);
		setOcupadas(getNumeroTaquillasLlenas() - 1);

	}

	/**
	 * Modifica el estado del paquete a sacado de la taquilla dada.
	 * 
	 * @param idTaquilla  id de la taquilla de la que sacar el paquete.
	 * @param fechaSacada fecha en la que se saca el paquete.
	 * @param dia         dia de la semana que se saca, lunes = 0.
	 * @param hora        hora en la que se saca.
	 * 
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia o si la fecha
	 *                                  de entrega ha sido superada.
	 * @throws IllegalStateException    si el paquete se intenta recoger pero su
	 *                                  estado es 1 (recogido) o 2 (devuelto).
	 * @throws IllegalStateException    si {@code fechaEnPlazo == False}.
	 * @see es.uva.inf.poo.amazingco.Package#recogido(LocalDate)
	 */
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada, int dia,
			LocalTime hora) {
		if (!getOperativo()) {

			throw new IllegalStateException(
					"El PickingPoint no está operativo.");

		}
		if (hora == null) {
			throw new IllegalArgumentException("La hora es nula.");

		}
		if (dia < 0 || dia > 6) {
			throw new IllegalArgumentException(
					"El dia de la semana esta comprendido entre 0 y 6.");

		}

		if (getHorario()[dia][0].isAfter(hora)
				|| getHorario()[dia][1].isBefore(hora)) {
			throw new IllegalStateException(
					"El PickingPoint está cerrado a esta hora del dia.");
		}
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(NUMERO_TAQUILLA_ERRONEO);
		}
		if (fechaSacada == null) {
			throw new IllegalArgumentException("La fecha es nula.");

		}
		if (idTaquilla >= getPaquetesInterno().size()
				|| getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException(TAQUILLA_VACIA);

		}
		getPaquetesInterno().get(idTaquilla).recogido(fechaSacada);
	}

	/**
	 * Modifica el estado del paquete a devuelto de la taquilla dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que se devuelve el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 * @throws IllegalStateException    si el paquete se intenta recoger pero su
	 *                                  estado es 1 (recogido) o 2 (devuelto).
	 * @throws IllegalStateException    si {@code fechaEnPlazo == False}.
	 * @see es.uva.inf.poo.amazingco.Package#devuelto()
	 */
	public void devuelvePaquete(int idTaquilla) {
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(NUMERO_TAQUILLA_ERRONEO);
		}
		if (idTaquilla >= getPaquetesInterno().size()
				|| getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException(TAQUILLA_VACIA);
		}
		getPaquetesInterno().get(idTaquilla).devuelto();
	}
}
