package es.uva.inf.poo.amazingco;

import java.util.ArrayList;
import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Administra los PickingPoint, pudiendo añadirlos o eliminarlos, ver los
 * operativos y fuera de servicio, encontrar los que hay en un radio dado y
 * saber en cuales de ellos se puede introducir un paquete concreto, ver cuales
 * tienen taquillas vacias y cuales están completamente llenos.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 */
public class PickingPointSystem {
	// constantes de error
	private static final String PICKING_POINT_NULO = "El pickingPoint introducdo es nulo";
	private static final String PICKING_POINT_MISMA_ID = "Ya hay un picking point con la misma id.";
	private static final String PICKING_POINT_NO_CREADO = "No hay PickingPoints creados.";
	private static final String PICKING_POINT_SIN_ID = "No existe ningún PickingPoint con la id introducida.";
	private static final String UBICACION_NULA = "La ubicación introducida es nula.";
	private static final String RADIO_NEGATIVO = "El radio introducido no puede ser negativo.";
	private static final String PAQUETE_NULL = "El paquete introducido es null.";
	private static final String PAQUETES_EN_PICKING_POINT = "Hay paquetes en el PickingPoint.";

	private ArrayList<PickingPoint> listaPickingPoint;

	private ArrayList<PickingPoint> getListaPickingPoint() {
		return listaPickingPoint;
	}

	/**
	 * Inicializa PickingPointSystem.
	 */
	public PickingPointSystem() {
		listaPickingPoint = new ArrayList<>();
	}

	/**
	 * Añade el pickingPoint a la lista pickingPoints.
	 * 
	 * @param pickingPoint pickingPoint a añadir.
	 * 
	 * @throws IllegalArgumentException si el pickingPoint es nulo.
	 */
	public void addPickingPoint(PickingPoint pickingPoint) {

		if (pickingPoint == null) {
			throw new IllegalArgumentException(PICKING_POINT_NULO);
		}
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getId() == pickingPoint.getId()) {
				throw new IllegalArgumentException(PICKING_POINT_MISMA_ID);
			}
		}
		listaPickingPoint.add(pickingPoint);
	}

	/**
	 * Devuelve todos los PickingPoint.
	 * 
	 * @return todos los PickingPoint.
	 */
	public PickingPoint[] getTodosPickingPoint() {
		PickingPoint[] vector = new PickingPoint[getListaPickingPoint().size()];
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			vector[i] = getListaPickingPoint().get(i);
		}
		return vector;
	}

	/**
	 * Devuelve el PickingPoint correspondiente a la id introducida.
	 * 
	 * @param id id del PickingPoint que se quiere obtener.
	 * @return PickingPoint con la id dada.
	 * @throws IllegalStateException    si no hay PickingPoint creados.
	 * @throws IllegalArgumentException si no existe ningún PickingPoint con la
	 *                                  id introducida.
	 * 
	 */
	public PickingPoint getPickingPoint(String id) {
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException(PICKING_POINT_NO_CREADO);
		}
		int i = 0;
		PickingPoint pickingPoint = null;
		while (i < getListaPickingPoint().size()) {
			if (id == getListaPickingPoint().get(i).getId()) {
				pickingPoint = getListaPickingPoint().get(i);
				i = getListaPickingPoint().size();
			} else {
				i++;

			}
		}
		if (pickingPoint == null) {
			throw new IllegalArgumentException(PICKING_POINT_SIN_ID);

		} else {
			return pickingPoint;
		}
	}

	/**
	 * Devuelve todos los PickingPoints operativos.
	 * 
	 * @return PickingPoints operativos.
	 * @throws IllegalStateException si no hay PickingPoints creados.
	 * 
	 */
	public PickingPoint[] getPickingPointOperativos() {
		// recorre dos veces la lista de PickingPoints creados. Una para
		// conocer el tamaño del vector que se devuelve y otra para rellenarlo.
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException(PICKING_POINT_NO_CREADO);
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getOperativo()) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getOperativo()) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve todos los PickingPoints fuera de servicio.
	 * 
	 * @return PickingPoints fuera de servicio.
	 * @throws IllegalStateException si no hay PickingPoints creados.
	 * 
	 */
	public PickingPoint[] getPickingPointFueraDeServicio() {
		// recorre dos veces la lista de PickingPoints creados. Una para
		// conocer el
		// tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException(PICKING_POINT_NO_CREADO);
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (!getListaPickingPoint().get(i).getOperativo()) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (!getListaPickingPoint().get(i).getOperativo()) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve todos los PickingPoints con alguna taquilla vacia.
	 * 
	 * @return PickingPoints con taquillas vacias.
	 * @throws IllegalStateException si no hay PickingPoints creados.
	 */
	public PickingPoint[] getPickingPointTaquillasVacias() {
		// recorre dos veces la lista de PickingPoints creados. Una para
		// conocer el tamaño del vector que se devulve y otra para rellenarlo.
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException(PICKING_POINT_NO_CREADO);
		}
		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getNumeroTaquillasVacias() != 0) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			if (getListaPickingPoint().get(i).getNumeroTaquillasVacias() != 0) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Devuelve los PickingPoints existentes dentro de un radio dado desde una
	 * ubicación dada.
	 * 
	 * @param ubicacion zona desde la que se genera el radio.
	 * @param radio     distancia desde la ubicación que se quiere abarcar.
	 * @return todos los PickingPoints operativos que están en el radio indicado
	 *         desde la ubicación indicada.
	 * @throws IllegalArgumentException si la ubicaón introducida es nula.
	 * @throws IllegalArgumentException si el radio dado es negativo.
	 * @throws IllegalStateException    si no hay PickingPoints creados.
	 */
	public PickingPoint[] getPickingPointEnZona(GPSCoordinate ubicacion,
			double radio) {
		if (ubicacion == null) {
			throw new IllegalArgumentException(UBICACION_NULA);
		}
		if (radio < 0) {
			throw new IllegalArgumentException(RADIO_NEGATIVO);
		}
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException(PICKING_POINT_NO_CREADO);
		}

		int contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			double distancia = getListaPickingPoint().get(i).getUbicacion()
					.getDistanceTo(ubicacion);
			distancia *= 1000;
			if (distancia <= radio) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];
		contador = 0;
		for (int i = 0; i < getListaPickingPoint().size(); i++) {
			double distancia = getListaPickingPoint().get(i).getUbicacion()
					.getDistanceTo(ubicacion);
			distancia *= 1000;
			if (distancia <= radio) {
				vector[contador] = getListaPickingPoint().get(i);
				contador++;
			}
		}
		return vector;

	}

	/**
	 * Dada una ubicación, un radio y un paquete indica que picking points hay a
	 * dicho radio de la ubicación donde se puede guardar el paquete.
	 * 
	 * 
	 * @param ubicacion ubicación desde la que parte el radio.
	 * @param radio     distancia máxima entre la ubicaicón dada y el picking
	 *                  point.
	 * @param paquete   paquete que se quiere guardar en un picking point.
	 * @return todos los picking points en los que se puede guarar el paquete
	 *         indicado dentro del radio indicado desde la ubicacion dada.
	 * 
	 * @throws IllegalArgumentException si el paquete introducido es null.
	 */
	public PickingPoint[] getPickingPointEnZonaPaquete(GPSCoordinate ubicacion,
			double radio, Package paquete) {
		if (paquete == null) {
			throw new IllegalArgumentException(PAQUETE_NULL);
		}
		PickingPoint[] puntosEnZona = getPickingPointEnZona(ubicacion, radio);
		int contador = 0;
		for (int i = 0; i < puntosEnZona.length; i++) {
			if (puntosEnZona[i].getNumeroTaquillasVacias() != 0
					&& puntosEnZona[i].paqueteValido(paquete)) {
				contador++;
			}
		}
		PickingPoint[] vector = new PickingPoint[contador];

		contador = 0;
		for (int i = 0; i < vector.length; i++) {
			if (puntosEnZona[i].getNumeroTaquillasVacias() != 0
					&& puntosEnZona[i].paqueteValido(paquete)) {
				vector[contador] = puntosEnZona[i];
				contador++;
			}
		}
		return vector;
	}

	/**
	 * Elimina el PickingPoint con la id introducida.
	 * 
	 * @param id del PickingPoint a borrar.
	 * @throws IllegalStateException    si no hay ningún PickingPoint creado.
	 * @throws IllegalStateException    si hay paquetes sin recoger o devolver
	 *                                  en el PickingPoint.
	 * @throws IllegalArgumentException si no existe ningún PickingPoint con la
	 *                                  id introducida.
	 */
	public void eliminarPickingPoint(String id) {
		if (getListaPickingPoint().isEmpty()) {
			throw new IllegalStateException(PICKING_POINT_NO_CREADO);
		}
		int i = 0;
		boolean encontrado = false;
		while (i < getListaPickingPoint().size()) {
			PickingPoint pickingPoint = getListaPickingPoint().get(i);
			if (id == pickingPoint.getId()) {

				if (!pickingPoint.borrable()) {
					throw new IllegalStateException(PAQUETES_EN_PICKING_POINT);

				}
				getListaPickingPoint().remove(i);
				i = getListaPickingPoint().size();
				encontrado = true;
			} else {
				i++;
			}
		}
		if (!encontrado) {
			throw new IllegalArgumentException(PICKING_POINT_SIN_ID);
		}
	}
}
