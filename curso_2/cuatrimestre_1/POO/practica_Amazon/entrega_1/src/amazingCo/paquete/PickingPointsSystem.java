package amazingCo.paquete;

import java.time.LocalTime;
import java.util.ArrayList;
import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Administra los PackageLockers, pudiendo añadirlos o eliminarlos, ver los
 * operativos y fuera de servicio, encontrar los que hay en un radio dado, ver
 * cuales tienen taquillas vacias y cuales están completamente llenos.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class PickingPointsSystem {

	private ArrayList<PackageLocker> listaPackageLockers;

	private ArrayList<PackageLocker> getListaPackageLockers() {
		return listaPackageLockers;
	}

	/**
	 * Inicializa PickingPointsSystem.
	 */
	public PickingPointsSystem() {
		listaPackageLockers = new ArrayList<PackageLocker>();
	}

	/**
	 * 
	 * Crear y guarda el PackageLocker con la id, ubicación, horario semanal, número
	 * de taquillas y operatividad dados.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         tabla en la que se representa el día de la semana y la
	 *                        hora de apertura y cierre de cada día. Esquema:
	 *                        [[LocalTime (apertura),Localtime
	 *                        (cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas que tiene el PackageLocker.
	 * @param operativo       indica si el PackageLocker está operativo desde el
	 *                        momento creado o no.
	 * @throws IllegalArgumentException si uno de los argumentos es null.
	 * @throws IllegalArgumentException si ya existe un PackageLocker con la id
	 *                                  introducida.
	 */
	public void crearPackageLocker(String id, GPSCoordinate ubicacion, LocalTime[][] horario, int numeroTaquillas,
			boolean operativo) {
		if (id == null || ubicacion == null || horario == null) {
			throw new IllegalArgumentException("Uno de los argumentos es null.");
		}
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (id == getListaPackageLockers().get(i).getId()) {
				throw new IllegalArgumentException("Ya existe un PackageLocker con la id indicada.");
			}
		}
		PackageLocker PackageLocker = new PackageLocker(id, ubicacion, horario, numeroTaquillas, operativo);

		getListaPackageLockers().add(PackageLocker);
	}

	/**
	 * 
	 * Crear y guarda el PackageLocker operativo con la id, ubicación, horario
	 * semanal y número de taquillas.
	 * 
	 * @param id              id de la taquilla.
	 * @param ubicacion       ubicación de la taquilla.
	 * @param horario         tabla en la que se representa el día de la semana y la
	 *                        hora de apertura y cierre de cada día. Esquema:
	 *                        [[LocalTime (apertura),Localtime
	 *                        (cierre)],...,[LocalTime,Localtime]]
	 * @param numeroTaquillas número de taquillas que tiene el PackageLocker.
	 * @throws IllegalArgumentException si uno de los argumentos es null.
	 * @throws IllegalArgumentException si ya existe un PackageLocker con la id
	 *                                  introducida.
	 */
	public void crearPackageLocker(String id, GPSCoordinate ubicacion, LocalTime[][] horario, int numeroTaquillas) {
		crearPackageLocker(id, ubicacion, horario, numeroTaquillas, true);
	}

	/**
	 * Devuelve todos los PackageLockers.
	 * 
	 * @return todos los PackageLockers.
	 */
	public PackageLocker[] getTodosPackageLocker() {
		PackageLocker[] vector = new PackageLocker[getListaPackageLockers().size()];
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			vector[i] = getListaPackageLockers().get(i);
		}
		return vector;
	}

	/**
	 * Devuelve el PackageLocker correspondiente a la id introducida.
	 * 
	 * @param id id del packagelocker que se quiere obtener.
	 * @return packagelocker con la id dada.
	 * @throws IllegalStateException    si no hay PackageLockers creados.
	 * @throws IllegalArgumentException si no existe ningún PackageLocker con la id
	 *                                  introducida.
	 * 
	 */
	public PackageLocker getPackageLocker(String id) {
		if (getListaPackageLockers().size() == 0) {
			throw new IllegalStateException("No hay PackageLockers creados.");
		}
		int i = 0;
		PackageLocker PackageLocker = null;
		while (i < getListaPackageLockers().size()) {
			if (id == getListaPackageLockers().get(i).getId()) {
				PackageLocker = getListaPackageLockers().get(i);
				i = getListaPackageLockers().size();
			} else {
				i++;

			}
		}
		if (PackageLocker == null) {
			throw new IllegalArgumentException("No existe ningún PackageLocker con esa id.");

		} else {
			return PackageLocker;
		}
	}

	/**
	 * Devuelve todos los PackageLockers operativos.
	 * 
	 * @return PackageLockers operativos.
	 * @throws IllegalStateException si no hay PackageLockers creados.
	 * 
	 */
	public PackageLocker[] getPackageLockerOperativos() {
		// recorre dos veces la lista de packageLockers creados. Una para conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPackageLockers().size() == 0) {
			throw new IllegalStateException("No hay PackageLockers creados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (getListaPackageLockers().get(i).getOperativo()) {
				contador++;
			}
		}
		PackageLocker[] vector = new PackageLocker[contador];
		contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (getListaPackageLockers().get(i).getOperativo()) {
				vector[contador] = getListaPackageLockers().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve todos los PackageLockers fuera de servicio.
	 * 
	 * @return PackageLockers fuera de servicio.
	 * @throws IllegalStateException si no hay PackageLockers creados.
	 * 
	 */
	public PackageLocker[] getPackageLockerFueraDeServicio() {
		// recorre dos veces la lista de packageLockers creados. Una para conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPackageLockers().size() == 0) {
			throw new IllegalStateException("No hay PackageLockers guardados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (!getListaPackageLockers().get(i).getOperativo()) {
				contador++;
			}
		}
		PackageLocker[] vector = new PackageLocker[contador];
		contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (!getListaPackageLockers().get(i).getOperativo()) {
				vector[contador] = getListaPackageLockers().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve los PackagerLockers en radio dado desde una ubicación dada.
	 * 
	 * @param ubicacion zona desde la que se genera el radio.
	 * @param radio     distancia desde la ubicación que se quiere abarcar.
	 * @return todos los PackageLockers operativos que están en el radio indicado
	 *         desde la ubicación indicada.
	 * @throws IllegalArgumentException si la ubicaón introducida es nula.
	 * @throws IllegalArgumentException si el radioi dado es negativo.
	 * @throws IllegalStateException    si no hay PackageLockers creados.
	 */
	public PackageLocker[] getPackageLockerEnZona(GPSCoordinate ubicacion, double radio) {
		if (ubicacion == null) {
			throw new IllegalArgumentException("La ubicación es nula.");
		}
		if (getListaPackageLockers().size() == 0) {
			throw new IllegalStateException("No hay PackageLockers creados.");
		}
		if (radio < 0) {
			throw new IllegalArgumentException("El radio no puede ser negativo.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			double distancia = getListaPackageLockers().get(i).getUbicacion().getDistanceTo(ubicacion);
			distancia *= 1000;
			if (distancia <= radio) {
				contador++;
			}
		}
		PackageLocker[] vector = new PackageLocker[contador];
		contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			double distancia = getListaPackageLockers().get(i).getUbicacion().getDistanceTo(ubicacion);
			distancia *= 1000;
			if (distancia <= radio) {
				vector[contador] = getListaPackageLockers().get(i);
				contador++;
			}
		}
		return vector;

	}

	/**
	 * Devuelve todos los PackageLockers con alguna taquilla vacia.
	 * 
	 * @return PackageLockers con taquillas vacias.
	 * @throws IllegalStateException si no hay PackageLockers creados.
	 */
	public PackageLocker[] getPackageLockerTaquillasVacias() {
		// recorre dos veces la lista de packageLockers creados. Una para conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPackageLockers().size() == 0) {
			throw new IllegalStateException("No hay PackageLockers creados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (getListaPackageLockers().get(i).getNumeroTaquillasVacias() != 0) {
				contador++;
			}
		}
		PackageLocker[] vector = new PackageLocker[contador];
		contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (getListaPackageLockers().get(i).getNumeroTaquillasVacias() != 0) {
				vector[contador] = getListaPackageLockers().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve todos los PackageLockers operativos con alguna taquilla vacia.
	 * 
	 * @return PackageLockers operativos con taquillas vacias.
	 * @throws IllegalStateException si no hay PackageLockers creados.
	 */
	public PackageLocker[] getPackageLockerTaquillasVaciasOperativas() {
		// recorre dos veces la lista de packageLockers creados. Una para conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPackageLockers().size() == 0) {
			throw new IllegalStateException("No hay PackageLockers creados.");
		}
		int contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (getListaPackageLockers().get(i).getNumeroTaquillasVacias() != 0
					&& getListaPackageLockers().get(i).getOperativo()) {
				contador++;
			}
		}
		PackageLocker[] vector = new PackageLocker[contador];
		contador = 0;
		for (int i = 0; i < getListaPackageLockers().size(); i++) {
			if (getListaPackageLockers().get(i).getNumeroTaquillasVacias() != 0
					&& getListaPackageLockers().get(i).getOperativo()) {
				vector[contador] = getListaPackageLockers().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Elimina un PackageLocker por id introducida.
	 * 
	 * @param id del PackageLocker a borrar.
	 * @throws IllegalStateException    si no hay ningún PackageLocker creado.
	 * @throws IllegalStateException    si hay paquetes sin recoger o devolver en el
	 *                                  PackageLocker.
	 * @throws IllegalArgumentException si no existe ningún PackageLocker con la id
	 *                                  introducida.
	 */
	public void eliminarPackageLocker(String id) {
		if (getListaPackageLockers().size() == 0) {
			throw new IllegalStateException("No hay PackageLockers creado.");
		}
		int i = 0;
		boolean encontrado = false;
		while (i < getListaPackageLockers().size()) {
			PackageLocker PackageLocker = getListaPackageLockers().get(i);
			if (id == PackageLocker.getId()) {
				for (int j = 0; j < PackageLocker.getNumeroTaquillas(); j++) {
					if (PackageLocker.getTaquillas()[j] != null) {
						throw new IllegalStateException("Todavia hay paquetes en el PackageLocker.");
					}
				}
				getListaPackageLockers().remove(i);
				i = getListaPackageLockers().size();
				encontrado = true;
			} else {
				i++;
			}
		}
		if (!encontrado) {
			throw new IllegalArgumentException("No existe ningún PackageLocker con esa id.");

		}
	}
}
