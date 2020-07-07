package amazingCo.paquete;

import es.uva.inf.poo.maps.GPSCoordinate;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * Permite la creación de PackageLockers con su id, horario, ubicación, número
 * de taquillas y operatividad y controlar los paquetes que tiene dicho
 * PackageLocker. Los números de las taquillas comienzan en 0.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class PackageLocker {

	private String id;
	private GPSCoordinate ubicacion;
	private LocalTime[][] horario;
	private int numeroTaquillas;
	private int ocupadas;
	private boolean operativo;
	private Package[] taquillas;

	/**
	 * Inicializa el PackageLocker con la id, ubicación, horario semanal, número de
	 * taquillas y operatividad dados.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana y la
	 *                        hora de apertura y cierre de cada día. Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas que tiene el PackageLocker.
	 * @param operativo       indica si el PackageLocker está operativo desde el
	 *                        momento creado o no.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de la
	 *                                  semana, que uno de los dias sea null, que un
	 *                                  dia no tenga exactamente 2 horas o que la
	 *                                  hora de apertura sea mayor que la hora de
	 *                                  cierre.
	 * @throws IllegalArgumentException si el numeroTaquillas es negativo.
	 */
	public PackageLocker(String id, GPSCoordinate ubicacion, LocalTime[][] horario, int numeroTaquillas,
			boolean operativo) {
		compruebaGenerador(id, ubicacion, horario, numeroTaquillas);
		this.id = id;
		this.ubicacion = ubicacion;
		this.horario = horario;
		this.numeroTaquillas = numeroTaquillas;
		this.operativo = operativo;
		this.taquillas = new Package[numeroTaquillas];
	}

	/**
	 * Inicializa el PackageLocker operativo con la id, ubicación, horario semanal y
	 * número de taquillas.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         forma en la que se representa el día de la semana y la
	 *                        hora de apertura y cierre de cada día. Esquema:
	 *                        [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas que tiene el PackageLocker.
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de la
	 *                                  semana, que uno de los dias sea null, que un
	 *                                  dia no tenga exactamente 2 horas o que la
	 *                                  hora de apertura sea mayor que la hora de
	 *                                  cierre.
	 * @throws IllegalArgumentException si el numeroTaquillas es negativo.
	 */
	public PackageLocker(String id, GPSCoordinate ubicacion, LocalTime[][] horario, int numeroTaquillas) {
		this(id, ubicacion, horario, numeroTaquillas, true);
	}

	// Comprueba que los argumentos dados para la inicialización del PackageLocker
	// sean
	// válidos.
	private void compruebaGenerador(String id, GPSCoordinate ubicacion, LocalTime[][] horario, int numeroTaquillas) {
		if (id == null || ubicacion == null || horario == null) {
			throw new IllegalArgumentException("Uno de los argumentos es null.");
		}
		// Comprobación de las especificaciones del horario. Esquema:
		// [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
		if (horario.length != 7) {
			throw new IllegalArgumentException("Son necesarios los horarios de los 7 dias de la semana.");
		}
		for (int i = 0; i < 7; i++) {
			if (horario[i] == null) {
				throw new IllegalArgumentException("Uno de los dias es null.");
			}
			if (horario[i].length != 2) {
				throw new IllegalArgumentException("Cada dia tiene que tener horario de apertura y cierre");

			}
			for (int j = 0; j < 2; j++) {
				if (horario[i][j] == null) {
					throw new IllegalArgumentException("Al menos una de las horas es null.");
				}
			}
			if (horario[i][0].isAfter(horario[i][1])) {
				throw new IllegalArgumentException("Horas mal introducidas. Debe ser Apertura → Cierre");
			}
		}
		if (numeroTaquillas < 1) {
			throw new IllegalArgumentException("Número de taquillas no positivo.");

		}

	}

	/**
	 * Devuelve true si el PackageLocker está operativo y false en caso contrario.
	 * 
	 * @return true si el PackageLocker está operativo y false si no lo está.
	 */
	public boolean getOperativo() {
		return operativo;

	}
	
	private void setOperativo(boolean op) {
		operativo = op;
	}

	private void setOcupadas(int numero) {
		ocupadas = numero;
	}

	/**
	 * Devuelve una copia de las taquillas del PackageLocker.
	 * 
	 * @return copia de taquillas del PackageLocker.
	 */
	public Package[] getTaquillas() {
		Package[] devolver = new Package[getNumeroTaquillas()];
		System.arraycopy(getTaquillasInterno(), 0, devolver, 0, getNumeroTaquillas());
		return devolver;
	}

	private Package[] getTaquillasInterno() {
		return taquillas;
	}

	/**
	 * Devuelve la id del PackageLocker.
	 * 
	 * @return id del PackageLocker.
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
	 * Devuelve el número de taquillas que tiene el PackageLocker.
	 * 
	 * @return número de taquillas.
	 */
	public int getNumeroTaquillas() {
		return numeroTaquillas;
	}

	/**
	 * Devuelve el número de taquillas llenas que tiene el PackageLocker.
	 * 
	 * @return número de taquillas llenas.
	 */
	public int getNumeroTaquillasLlenas() {
		return ocupadas;
	}

	/**
	 * Devuelve el número de taquillas vacías que tiene el PackageLocker.
	 * 
	 * @return número de taquillas vacías.
	 */
	public int getNumeroTaquillasVacias() {
		return getNumeroTaquillas() - getNumeroTaquillasLlenas();
	}

	/**
	 * Devuelve la copia de la ubicación geográfica del PackageLocker.
	 * 
	 * @return copia de la ubicación geográfica.
	 */
	public GPSCoordinate getUbicacion() {
		GPSCoordinate devolver = new GPSCoordinate(ubicacion.getLatitudeGD(), ubicacion.getLongitudeGD());
		return devolver;
	}

	/**
	 * Cambia el packageLocker a operativo si está fuera de servicio y viceversa.
	 */
	public void operatividad() {
		if (getOperativo()) {
			setOperativo(false);
		} else {
			setOperativo(true);
		}
	}

	/**
	 * Devuelve el número de taquilla en el que se encuentra el paquete indicado.
	 * 
	 * @param idPaquete id del paquete.
	 * @return Número de la taquilla en la que está el paquete.
	 * @throws IllegalArgumentException Si la id es null
	 * @throws IllegalArgumentException Si no existe el paquete en el PackageLocker
	 *                                  indicado.
	 */
	public int locaclizaPaquete(String idPaquete) {
		if (idPaquete == null) {
			throw new IllegalArgumentException("La id es null.");
		}
		int i = 0;
		int zona = -1;
		while (i < getNumeroTaquillas()) {
			if (idPaquete == getTaquillasInterno()[i].getId()) {
				zona = i;
				i = getNumeroTaquillas();
			} else {
				i++;
			}
		}
		if (zona == -1) {
			throw new IllegalArgumentException("No existe ese paquete en el PackageLocker.");
		} else {
			return zona;
		}

	}

	/**
	 * Asigna el paquete dado a una taquilla.
	 * 
	 * @param paquete paquete a guardar.
	 * @throws IllegalArgumentException Si el paquete es null.
	 * @throws IllegalStateException    Si el PackageLocker está lleno.
	 * @throws IllegalStateException    Si hay otro paquete con la misma id.
	 */
	public void asignaPaquete(Package paquete) {

		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}
		if (getNumeroTaquillasLlenas() == getNumeroTaquillas()) {
			throw new IllegalStateException("PackageLocker lleno.");
		}
		// comprueba que no haya un paquete con la misma id en el PackageLocker.
		for (int i = 0; i < getNumeroTaquillas(); i++) {
			if (getTaquillasInterno()[i] != null) {
				if (paquete.getId() == getTaquillasInterno()[i].getId()) {
					throw new IllegalStateException("Hay otro paquete con la misma id");
				}
			}
		}
		// guarda el paquete en la primera taquilla libre.
		int i = 0;
		while (i < getNumeroTaquillas()) {
			if (getTaquillasInterno()[i] == null) {
				getTaquillasInterno()[i] = paquete;
				i = getNumeroTaquillas();
			} else {
				i++;
			}
		}
		setOcupadas(getNumeroTaquillasLlenas() + 1);

	}

	/**
	 * Muestra el paquete de la taquilla dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que sacar el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 * @return paquete que estaba en la taquilla indicada.
	 */
	public Package getPaquete(int idTaquilla) {

		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}

		if (getTaquillasInterno()[idTaquilla] == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}

		Package paquete = getTaquillasInterno()[idTaquilla];
		return paquete;
	}

	/**
	 * Borra el paquete de la taquilla dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que sacar el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 */
	public void borraPaquete(int idTaquilla) {

		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}

		if (getTaquillasInterno()[idTaquilla] == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}
		getTaquillasInterno()[idTaquilla] = null;
		setOcupadas(getNumeroTaquillasLlenas() - 1);

	}

	/**
	 * Modifica el estado del paquete a sacado de la taquilla dada.
	 * 
	 * @param idTaquilla  id de la taquilla de la que sacar el paquete.
	 * @param fechaSacada fecha en la que se saca el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia o si la fecha de
	 *                                  entrega ha sido superada.
	 */
	public void sacaPaquete(int idTaquilla, LocalDate fechaSacada) {
		if (!getOperativo()) {
			throw new IllegalStateException("El PackageLocker no está operativo.");

		}
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}
		if (fechaSacada == null) {
			throw new IllegalArgumentException("La fecha es nula.");

		}
		if (getTaquillasInterno()[idTaquilla] == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");

		} else if (!getTaquillasInterno()[idTaquilla].fechaEnPlazo(fechaSacada)) {
			throw new IllegalStateException("La fecha de entrega ha sido superada.");

		}
		getTaquillasInterno()[idTaquilla].recogido();
	}

	/**
	 * Modifica el estado del paquete a devuelto de la taquilla dada.
	 * 
	 * @param idTaquilla id de la taquilla de la que se devuelve el paquete.
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 */
	public void devuelvePaquete(int idTaquilla) {
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}
		if (getTaquillasInterno()[idTaquilla] == null) {
			throw new IllegalStateException("Esta taquilla está vacía.");
		}
		getTaquillasInterno()[idTaquilla].devuelto();
	}
}
