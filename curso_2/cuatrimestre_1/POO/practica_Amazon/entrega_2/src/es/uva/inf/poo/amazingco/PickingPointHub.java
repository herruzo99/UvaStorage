package es.uva.inf.poo.amazingco;

import java.time.LocalTime;
import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * PickingPoint que almacena GroupablePickingPoints como el PackageLocker y el
 * Kiosk.
 * 
 * 
 * Permite añadir y borrar GroupablePickingPoints, tambien comprobar si uno de
 * estos está en el hub y saber cuales y cuantos estan guardados.
 * 
 * Tambiem permite almacenar paquete de la forma que lo hacen el PackageLocker y
 * el Kiosk siempre que esten en el hub.
 * 
 * @see es.uva.inf.poo.amazingco.PackageLocker
 * @see es.uva.inf.poo.amazingco.Kiosk
 * 
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class PickingPointHub extends PickingPoint {

	private ArrayList<GroupablePickingPoint> listaPuntos;

	private int numPackageLockers = 0;

	/**
	 * Inicializa el PickingPointHub con la id, ubicación, horario semanal y
	 * GroupablePoints que se quieran añadir.
	 * 
	 * @param id             id de la taquilla.
	 * @param ubicacion      ubicación de la taquilla.
	 * @param horario        forma en la que se representa el día de la semana y
	 *                       la hora de apertura y cierre de cada día. Esquema:
	 *                       [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 *                       momento creado o no.
	 * @param puntosRecogida GroupablePickingPoints que se quieren guardar.
	 * 
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int)
	 * @see es.uva.inf.poo.amazingco.GroupablePickingPoint
	 * 
	 */
	public PickingPointHub(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, GroupablePickingPoint[] puntosRecogida) {
		this(id, ubicacion, horario, puntosRecogida, true);
	}

	/**
	 * 
	 * Inicializa el PickingPointHub con la id, ubicación, horario semanal,
	 * GroupablePoints que se quieran añadir y operatividad dados.
	 * 
	 * @param id             id de la taquilla.
	 * @param ubicacion      ubicación de la taquilla.
	 * @param horario        forma en la que se representa el día de la semana y
	 *                       la hora de apertura y cierre de cada día. Esquema:
	 *                       [[LocalTime(apertura),Localtime(cierre)],...,[LocalTime,Localtime]]
	 * @param operativo      indica si el PackageLocker está operativo desde el
	 *                       momento creado o no.
	 * @param puntosRecogida GroupablePickingPoints que se quieren guardar.
	 * 
	 * @throws IllegalArgumentException si alguno de los argumentos es null.
	 * @throws IllegalArgumentException si el horario no contiene los 7 dias de
	 *                                  la semana, que uno de los dias sea null,
	 *                                  que un dia no tenga exactamente 2 horas
	 *                                  o que la hora de apertura sea mayor que
	 *                                  la hora de cierre.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#PickingPoint(String,
	 *      GPSCoordinate, LocalTime[][],int, boolean)
	 * @see es.uva.inf.poo.amazingco.GroupablePickingPoint
	 */
	public PickingPointHub(String id, GPSCoordinate ubicacion,
			LocalTime[][] horario, GroupablePickingPoint[] puntosRecogida,
			boolean operativo) {
		super(id, ubicacion, horario, 1, operativo);

		setNumeroTaquillas(0);

		if (puntosRecogida.length < 2) {
			throw new IllegalArgumentException(
					"Minimo tiene que haber dos puntos de recogida.");
		}
		listaPuntos = new ArrayList<>();

		for (int i = 0; i < puntosRecogida.length; i++) {
			addPickingPoint(puntosRecogida[i]);
		}
	}

	private ArrayList<GroupablePickingPoint> getListaPuntosInterna() {
		return listaPuntos;
	}

	/**
	 * Devuelve un array con todos los GroupablePickingPoints que estan
	 * guardados en el hub.
	 * 
	 * @return array de todos los GroupablePickingPoint.
	 */
	public GroupablePickingPoint[] getListaPuntos() {
		return getListaPuntosInterna().toArray(new GroupablePickingPoint[0]);
	}

	/**
	 * Devuelve el número de GroupablePickingPoints que hay.
	 * 
	 * @return número de GroupablePickingPoints que hay.
	 */
	public int getNumPuntos() {
		return getListaPuntosInterna().size();
	}

	/**
	 * Comprueba si un GroupablePickingPoint esta en el hub mediante su Id.
	 * 
	 * @param idPuntoRecogida Id del PickingPoint a comprobar.
	 * @return true si está el PickingPoint y false si no.
	 * @throws IllegalArgumentException si la id es null.
	 */
	public boolean estaPuntoRecogida(String idPuntoRecogida) {
		if (idPuntoRecogida == null) {
			throw new IllegalArgumentException("La id es null.");
		}
		return (posPuntoRecogida(idPuntoRecogida) != -1);
	}

	private int posPuntoRecogida(String idPuntoRecogida) {
		for (int j = 0; j < getListaPuntosInterna().size(); j++) {
			if (getListaPuntosInterna().get(j).getId() == idPuntoRecogida) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * Añade el GroupablePickingPoint dado siempre que se pueda.
	 * 
	 * @param puntoRecogida GroupablePickingPoint a añadir.
	 * 
	 * @throws IllegalArgumentException si el punto es null.
	 * @throws IllegalArgumentException si la ubicación no es la misma que la
	 *                                  del Hub.
	 * @throws IllegalArgumentException si ya hay un punto con la Id del punto
	 *                                  pasado.
	 */
	public void addPickingPoint(GroupablePickingPoint puntoRecogida) {
		if (puntoRecogida == null) {
			throw new IllegalArgumentException(
					"Uno de los puntos de recogida es null.");
		}
		if (!getUbicacion().equals(puntoRecogida.getUbicacion())) {
			throw new IllegalArgumentException(
					"Uno de los puntos de recogida no está en la misma ubicación.");
		}

		if (posPuntoRecogida(puntoRecogida.getId()) != -1) {
			throw new IllegalArgumentException(
					"Ya hay un punto de recogida con ese nombre.");
		}

		getListaPuntosInterna().add(numPackageLockers, puntoRecogida);

		if (puntoRecogida.getClass() == PackageLocker.class) {
			setNumPackageLockers(getNumPackageLockers() + 1);

		}
		int tam = getNumeroTaquillas();
		tam += puntoRecogida.getNumeroTaquillas();
		setNumeroTaquillas(tam);

	}

	private int getNumPackageLockers() {
		return numPackageLockers;
	}

	private void setNumPackageLockers(int num) {
		numPackageLockers = num;
	}

	/**
	 * Borra un GroupablePickingPoint mediente Id.
	 * 
	 * Para que un GroupablePickingPoint sea borrable
	 * GroupablePickingPoint.borrable() tiene que ser true.
	 * 
	 * @see es.uva.inf.poo.amazingco.PackageLocker#borrable()
	 * @see es.uva.inf.poo.amazingco.Kiosk#borrable()
	 * 
	 * 
	 * @param idPuntoRecogida Id del punto a borrar.
	 * 
	 * @throws IllegalStateException    si al borrar ese punto el Hub se quedára
	 *                                  con menos de dos.
	 * @throws IllegalStateException    si el punto a borrar tiene paquetes
	 *                                  dentro de él.
	 * @throws IllegalArgumentException si la Id es null.
	 * @throws IllegalArgumentException si no existe ningún punto con esa Id.
	 */
	public void removePickingPoints(String idPuntoRecogida) {
		if (idPuntoRecogida == null) {
			throw new IllegalArgumentException("La String es null.");
		}

		if (getNumPuntos() < 3) {
			throw new IllegalStateException(
					"Tiene que haber al menos dos puntos de recogida.");

		}
		int posicion = posPuntoRecogida(idPuntoRecogida);

		if (posicion == -1) {
			throw new IllegalArgumentException(
					"No hay ningún punto de recogida con esa id.");
		}
		if (!getListaPuntosInterna().get(posicion).borrable()) {
			throw new IllegalStateException("La taquilla tiene paquetes.");
		}
		if (getListaPuntosInterna().get(posicion)
				.getClass() == PackageLocker.class) {
			setNumPackageLockers(getNumPackageLockers() - 1);
		}
		getListaPuntosInterna().remove(posicion);
	}

	private void compruebaOperativo() {
		if (!buscaOperativo()) {
			setOperativo(false);
		}
	}

	private void compruebaTam() {
		int tam = 0;
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {
			tam += getListaPuntosInterna().get(i).getNumeroTaquillas();
		}
		setNumeroTaquillas(tam);
	}

	/**
	 * Devuelve el numero de taquillas totales.
	 * 
	 * Actualiza el numero de taquillas antes de devolverlo, suma todas las
	 * taquillas que tienen los GroupablesPickingPoint internos.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#getNumeroTaquillas()
	 */
	@Override
	public int getNumeroTaquillas() {
		compruebaTam();
		return super.getNumeroTaquillas();
	}

	/**
	 * Devuelve la operatividad del hub.
	 * 
	 * Actualiza la operatividad del Hub antes de devolverlo, comprueba si hay
	 * algún GroupablePickingPoint activo dentro si no el Hub se pone en fuera
	 * de servicio.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#getOperativo()
	 */
	@Override
	public boolean getOperativo() {
		compruebaOperativo();
		return super.getOperativo();
	}

	/**
	 * Devuelve todos los paquetes del Hub.
	 * 
	 * Une el getPaquetes() de todos los puntos en el Hub.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#getPaquetes()
	 */
	@Override
	public ArrayList<Package> getPaquetes() {
		ArrayList<Package> paquetes = new ArrayList<>();
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {
			paquetes.addAll(getListaPuntosInterna().get(i).getPaquetes());
		}
		return paquetes;

	}

	/**
	 * 
	 * Comprueba si un PickingPointHub es borrable o no.
	 * 
	 * Es borrable si todos los puntos del Hub son borrables.
	 * 
	 * @return true si todos los puntos del Hub son borrables.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#borrable()
	 * @see es.uva.inf.poo.amazingco.GroupablePickingPoint#borrable()
	 */
	@Override
	public boolean borrable() {
		for (int i = 0; i < getListaPuntosInterna().size(); i++) {

			if (!getListaPuntosInterna().get(i).borrable()) {
				return false;
			}
		}
		return true;

	}

	private boolean buscaOperativo() {
		int i = 0;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i).getOperativo()) {
				return true;
			}
			i++;
		}
		return false;
	}

	/**
	 * Cambia el estado de su operatividad.
	 * 
	 * Cambia el estado de todos los puntos internos a no operativo si el Hub se
	 * pasa a no operativo, solo puede ser puesto en operativo de nuevo si uno
	 * de los puntos internos está operativo.
	 * 
	 * @throws IllegalStateException si se intenta poner en operativo cuando
	 *                               todos los puntos internos están en no
	 *                               operativo.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#operatividad()
	 * 
	 */
	@Override
	public void operatividad() {
		if (getOperativo()) {
			setOperativo(false);

			for (int i = 0; i < getListaPuntosInterna().size(); i++) {
				if (getListaPuntosInterna().get(i).getOperativo()) {
					getListaPuntosInterna().get(i).operatividad();
				}
			}
		} else {

			if (!buscaOperativo()) {
				throw new IllegalStateException(
						"Tiene que haber al menos un punto operativo para activar el Hub.");
			}
			setOperativo(true);

		}
	}

	/**
	 * Asigna el paquete al hub.
	 * 
	 * Asigna el paquete a su lista principal como ademas de al primer
	 * GroupablePickingPoint donde sea posible asignarle.
	 * 
	 * @throws IllegalStateException    si se intenta asignar un paquete a
	 *                                  contrarrembolso y no hay ningun Kiosk
	 *                                  activo.
	 * @throws IllegalArgumentException Si el paquete es null.
	 * @throws IllegalStateException    Si el PickingPoint está lleno.
	 * @throws IllegalStateException    Si el PickingPoint no está operativo.
	 * @throws IllegalStateException    Si hay otro paquete con la misma id.
	 * @throws IllegalArgumentException Si el paquete no se puede guardar en el
	 *                                  PickingPoint.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#asignaPaquete(Package)
	 */
	@Override
	public void asignaPaquete(Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException("El paquete es null.");
		}

		int i = 0;
		boolean guardado = false;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i).getNumeroTaquillasVacias() > 0
					&& getListaPuntosInterna().get(i).paqueteValido(paquete)
					&& getListaPuntosInterna().get(i).getOperativo()) {

				super.asignaPaquete(paquete);
				getListaPuntosInterna().get(i).asignaPaquete(paquete);

				guardado = true;

				i = getListaPuntosInterna().size();
			}
			i++;
		}
		if (!guardado) {
			throw new IllegalStateException(
					"No se ha podido guardar el paquete.");

		}
	}

	/**
	 * Borra el paquete de la taquilla dada por Id.
	 * 
	 * Se borra de su lista principal como ademas del GroupablePickingPoint
	 * donde está guardado el paquete.
	 * 
	 * @throws IllegalArgumentException si el número de taquilla es erróneo.
	 * @throws IllegalStateException    si la taquilla está vacia.
	 * 
	 * @see es.uva.inf.poo.amazingco.PickingPoint#borraPaquete(int)
	 */
	@Override
	public void borraPaquete(int idTaquilla) {
		if (idTaquilla < 0 || idTaquilla > getNumeroTaquillas() - 1) {
			throw new IllegalArgumentException(
					"Número de taquilla erroneo. Debe estar comprendido entre 0 y numero de taquillas -1");
		}
		if (idTaquilla >= getPaquetesInterno().size()
				|| getPaquetesInterno().get(idTaquilla) == null) {
			throw new IllegalStateException("Esta taquilla está vacia.");
		}
		Package paquete = getPaquetesInterno().get(idTaquilla);
		super.borraPaquete(idTaquilla);

		int i = 0;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i)
					.buscaPaquete(paquete.getId()) > -1) {

				int pos = getListaPuntosInterna().get(i)
						.localizaPaquete(paquete.getId());
				getListaPuntosInterna().get(i).borraPaquete(pos);
				i = getListaPuntosInterna().size();
			}
			i++;
		}

	}

	/**
	 * Comprueba si un paquete puede ser guardado en el PickingPointHub o no.
	 * 
	 * Un paquete es valido siempre que sea válido es sus GroupablePickingPoints
	 * guardado..
	 * 
	 * @return true si el paquete es valido en los GroupablePickingPoints
	 *         guardados.
	 * @throws IllegalArgumentException si el paquete es null.
	 * @see es.uva.inf.poo.amazingco.PickingPoint#paqueteValido(Package)
	 */
	@Override
	public boolean paqueteValido(Package paquete) {
		int i = 0;
		while (i < getListaPuntosInterna().size()) {
			if (getListaPuntosInterna().get(i).paqueteValido(paquete)) {
				return true;
			}

			i++;
		}
		return false;
	}
}
