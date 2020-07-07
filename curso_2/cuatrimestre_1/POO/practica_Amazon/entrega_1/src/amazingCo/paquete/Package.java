package amazingCo.paquete;

import java.time.LocalDate;

/**
 * Permite crear paquetes, controlar y conocer su estado, conocer su id y saber
 * su fecha límite de recogida y si esta ha sido superada frente a otra.
 * <br><br>
 * El tiempo por defecto de margen es 7 días.
 * 
 * @author juaherr
 * @author rebhern
 * @author pedrher
 * 
 * 
 */
public class Package {

	private static int diasMaximos = 7;
	private String id;
	private LocalDate fechaCreacion;
	/*
	 * Estados: 0 para recoger. 1 recogido. 2 devuelto.
	 */
	private int estado;

	/**
	 * Devuelve el número de dias que puede estar un paquete en una taquilla como
	 * máximo.
	 *
	 * @return número de dias que puede estar un paquete en una taquilla como
	 *         máximo.
	 */
	public static int getDiasMaximos() {
		return diasMaximos;
	}

	/**
	 * Cambia el número de dias que puede estar un paquete en una taquilla como
	 * máximo al número de días dado.
	 * 
	 * El tiempo por defecto de margen de días es 7.
	 * 
	 * @param dias número de dias que puede estar un paquete en una taquilla como
	 *             máximo.
	 * 
	 * @throws IllegalArgumentException si el número de días es negativo.
	 */
	public static void setDiasMaximos(int dias) {
		if (dias < 0) {
			throw new IllegalArgumentException("Número máximo de días negativo.");
		}
		diasMaximos = dias;
	}

	/**
	 * Inicializa el paquete con la id y fecha límite a partir de la fecha actual.
	 * 
	 * La id del paquete sigue la siguiente restricción: "Debe tener diez
	 * caracteres, de los cuales los primeros nueve son dígitos y el décimo es un
	 * dígito resultante del resto de la división entre 10 de la suma de los 9
	 * primeros."
	 * 
	 * @param id id del paquete siguiendo las restriciones de id: "Debe tener diez
	 *           caracteres, de los cuales los primeros nueve son dígitos y el
	 *           décimo es un dígito resultante del resto de la división entre 10 de
	 *           la suma de los 9 primeros."
	 * @throws IllegalArgumentException si la id es null.
	 * @throws IllegalArgumentException si la longitud de la id es distinta de 10.
	 * 
	 * @throws IllegalArgumentException si los caracteres de la id son distintos de
	 *                                  [0,9].
	 * @throws IllegalArgumentException si no se verifica el dígito de condición.
	 */
	public Package(String id) {
		if (id == null) {
			throw new IllegalArgumentException("La id es null.");
		}
		if (id.length() != 10) {
			throw new IllegalArgumentException("La id no tiene no tiene 10 dígitos");
		}
		int acumulado = 0;
		// Transforma cada dígito de char a int para después operar con ellos.
		// Se resta el código ASCII del número 0 para pasar de ASCII a decimal.
		for (int i = 0; i < 9; i++) {
			int digito = id.charAt(i) - '0';

			// Una de las ramas es inaccesible ya que 10 < digito < 1 es imposible.
			if (digito < 10 && digito > -1) {
				acumulado += (digito);
			} else {
				throw new IllegalArgumentException("La id contiene caracteres distintos de [0,9]");
			}
		}
		if (acumulado % 10 == (id.charAt(9) - '0')) {
			this.id = id;
			fechaCreacion = LocalDate.now();
		} else {
			throw new IllegalArgumentException("No se verifica el dígito de condición.");
		}
	}

	/**
	 * Devuelve la fecha límite del paquete.
	 * 
	 * @return fecha límite del paquete.
	 */
	public LocalDate getFecha() {
		return fechaCreacion.plusDays(diasMaximos);
	}

	/**
	 * Devuelve estado del paquete.
	 * 
	 * 0 = para recoger. 1 = recogido. 2 = devuelto.
	 * 
	 * @return estado del paquete. 0 = para recoger. 1 = recogido. 2 = devuelto.
	 */
	public int getEstado() {
		return estado;
	}

	private void setEstado(int nuevoEstado) {
		estado = nuevoEstado;
	}

	/**
	 * Devuelve la id del paquete.
	 * 
	 * @return id del paquete.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Indica si todavia se puede recoger el paquete respecto a la fecha dada.
	 * <br><br>
	 * Se admite que se pueda recoger el mismo día que vence la fecha límite.
	 * 
	 * @param fecha fecha con la que se desea comprobar.
	 * @return true si se puede recoger y false si no.
	 * @throws IllegalArgumentException si la fecha es null.
	 */
	public boolean fechaEnPlazo(LocalDate fecha) {
		if (fecha == null) {
			throw new IllegalArgumentException("La fecha es null.");

		}
		return !getFecha().isBefore(fecha);
	}

	/**
	 * Cambia el estado del paquete a recogido en caso de que sea posible.
	 * 
	 * @throws IllegalStateException si el paquete se intenta recoger pero su estado
	 *                               es 1 (recogido) o 2 (devuelto).
	 */
	public void recogido() {
		if (getEstado() == 0) {
			setEstado(1);
		} else if (getEstado() == 1) {
			throw new IllegalStateException("Paquete ya recogido.");
		} else {
			throw new IllegalStateException("Paquete ya devuelto.");

		}
	}

	/**
	 * Cambia el estado del paquete a devuelto en caso de que sea posible.
	 * 
	 * @throws IllegalStateException si el paquete se intenta devolver pero su
	 *                               estado es 1 (recogido) o 2 (devuelto).
	 */
	public void devuelto() {
		if (getEstado() == 0) {
			setEstado(2);
		} else if (getEstado() == 1) {
			throw new IllegalStateException("Paquete ya recogido.");
		} else {
			throw new IllegalStateException("Paquete ya devuelto.");
		}
	}

}