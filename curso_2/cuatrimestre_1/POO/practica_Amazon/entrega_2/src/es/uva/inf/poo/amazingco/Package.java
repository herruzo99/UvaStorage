package es.uva.inf.poo.amazingco;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Permite crear paquetes, controlar y conocer su estado, conocer su id y saber
 * su fecha límite de recogida y si esta ha sido superada frente a otra. <br>
 * <br>
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

	private double precio;
	private boolean pagado;
	private boolean certificado;
	private HashSet<String> dni;

	/**
	 * Devuelve el número de dias que puede estar un paquete en una taquilla
	 * como máximo.
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
	 * @param dias número de dias que puede estar un paquete en una taquilla
	 *             como máximo.
	 * 
	 * @throws IllegalArgumentException si el número de días es negativo.
	 */
	public static void setDiasMaximos(int dias) {
		if (dias < 0) {
			throw new IllegalArgumentException(
					"Número máximo de días negativo.");
		}
		diasMaximos = dias;
	}

	/**
	 * Inicializa el paquete no certificado con la id, precio, estado del pago y
	 * fecha límite a partir de la fecha actual.
	 * 
	 * 
	 * La id del paquete sigue la siguiente restricción: "Debe tener diez
	 * caracteres, de los cuales los primeros nueve son dígitos y el décimo es
	 * un dígito resultante del resto de la división entre 10 de la suma de los
	 * 9 primeros."
	 * 
	 * @param id     id del paquete siguiendo las restriciones de id: "Debe
	 *               tener diez caracteres, de los cuales los primeros nueve son
	 *               dígitos y el décimo es un dígito resultante del resto de la
	 *               división entre 10 de la suma de los 9 primeros."
	 * 
	 * @param precio precio del paquete.
	 * @param pagado si está pagado o no.
	 * @throws IllegalArgumentException si la id es null.
	 * @throws IllegalArgumentException si la longitud de la id es distinta de
	 *                                  10.
	 * 
	 * @throws IllegalArgumentException si los caracteres de la id son distintos
	 *                                  de [0,9].
	 * @throws IllegalArgumentException si no se verifica el dígito de
	 *                                  condición.
	 * 
	 * @throws IllegalArgumentException si El precio es negativo.
	 * 
	 */
	public Package(String id, int precio, boolean pagado) {
		if (id == null) {
			throw new IllegalArgumentException("La id es null.");
		}
		if (id.length() != 10) {
			throw new IllegalArgumentException(
					"La id no tiene no tiene 10 dígitos.");
		}
		int acumulado = 0;
		// Transforma cada dígito de char a int para después operar con ellos.
		// Se resta el código ASCII del número 0 para pasar de ASCII a decimal.
		for (int i = 0; i < 9; i++) {
			int digito = id.charAt(i) - '0';

			// Una de las ramas es inaccesible ya que 10 < digito < 1 es
			// imposible.
			if (digito < 10 && digito > -1) {
				acumulado += (digito);
			} else {
				throw new IllegalArgumentException(
						"La id contiene caracteres distintos de [0,9].");
			}
		}
		if (acumulado % 10 == (id.charAt(9) - '0')) {
			this.id = id;
			fechaCreacion = LocalDate.now();
		} else {
			throw new IllegalArgumentException(
					"No se verifica el dígito de condición.");
		}
		if (precio < 0) {
			throw new IllegalArgumentException(
					"El precio no puede ser negativo.");
		}
		this.precio = precio;
		this.pagado = pagado;

		this.certificado = false;
	}

	/**
	 * Inicializa el paquete certificado con la id, precio, estado del pago, dni
	 * del autorizado y fecha límite a partir de la fecha actual.
	 * 
	 * La id del paquete sigue la siguiente restricción: "Debe tener diez
	 * caracteres, de los cuales los primeros nueve son dígitos y el décimo es
	 * un dígito resultante del resto de la división entre 10 de la suma de los
	 * 9 primeros."
	 * 
	 * @param id     id del paquete siguiendo las restriciones de id: "Debe
	 *               tener diez caracteres, de los cuales los primeros nueve son
	 *               dígitos y el décimo es un dígito resultante del resto de la
	 *               división entre 10 de la suma de los 9 primeros."
	 * 
	 * @param precio precio del paquete.
	 * @param pagado si está pagado o no.
	 * @param dni    array de dnis autorizados.
	 * @throws IllegalArgumentException si la id es null.
	 * @throws IllegalArgumentException si la longitud de la id es distinta de
	 *                                  10.
	 * 
	 * @throws IllegalArgumentException si los caracteres de la id son distintos
	 *                                  de [0,9].
	 * @throws IllegalArgumentException si no se verifica el dígito de
	 *                                  condición.
	 * 
	 * @throws IllegalArgumentException si El precio es negativo.
	 * 
	 * @throws IllegalArgumentException si el dni es null.
	 * 
	 */
	public Package(String id, int precio, boolean pagado, String[] dni) {
		this(id, precio, pagado);
		this.certificado = true;
		this.dni = new HashSet<>();
		addDni(dni);
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
	 * Devuelve si el paquete está certificado o no.
	 * 
	 * @return si está certificado o no.
	 */
	public boolean getCertificado() {
		return certificado;
	}

	// Usar cuando se necesite el Set de dnis.
	private HashSet<String> getDni() {
		return dni;
	}

	/**
	 * Devuelve el precio del paquete.
	 * 
	 * @return precio del paquete.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Devuelve si el paquete está pagado o no.
	 * 
	 * @return si el paquete está pagado o no.
	 */
	public boolean getPagado() {
		return pagado;
	}

	protected void pagado() {
		pagado = !pagado;
	}

	/**
	 * Añade un dni autorizado a el paquete certificado.
	 * 
	 * @param dni dni a añadir como autorizado.
	 * 
	 * @throws IllegalArgumentException si el dni es null.
	 * @throws IllegalArgumentException si el dni ya está autorizado.
	 * @throws IllegalStateException    si el paquete no está certificado.
	 * @throws IllegalStateException    si el paquete ya no está en la taquilla.
	 */
	public void addDni(String[] dni) {
		if (getEstado() != 0) {
			throw new IllegalStateException(
					"El paquete ya no está en la taquilla.");
		}
		if (!getCertificado()) {
			throw new IllegalStateException("El paquete no está certificado.");
		}
		for (int i = 0; i < dni.length; i++) {
			if (dni[i].isEmpty()) {
				throw new IllegalArgumentException("Uno de los dni es null.");
			}
			if (getDni().contains(dni[i])) {
				throw new IllegalArgumentException(
						"Uno de los dni esta ya en la lista.");
			}
			getDni().add(dni[i]);
		}
	}

	/**
	 * Indica si todavia se puede recoger el paquete respecto a la fecha dada.
	 * <br>
	 * <br>
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
	 * Cambia el estado del paquete a recogido en caso de que esté en el plazo
	 * de entrega y siga en el taquilla.
	 * 
	 * @param fecha fecha en la que se quiere recoger el paquete.
	 * @throws IllegalStateException si el paquete se intenta recoger pero su
	 *                               estado es 1 (recogido) o 2 (devuelto).
	 * @throws IllegalStateException si {@code fechaEnPlazo == False}.
	 */
	public void recogido(LocalDate fecha) {
		if (fechaEnPlazo(fecha)) {
			if (getEstado() == 0) {
				setEstado(1);
			} else if (getEstado() == 1) {
				throw new IllegalStateException("Paquete ya recogido.");
			} else {
				throw new IllegalStateException("Paquete ya devuelto.");
			}
		} else {
			throw new IllegalStateException(
					"La fecha de entrega ha sido superada.");
		}
	}

	/**
	 * Cambia el estado del paquete a devuelto en caso de que siga en la
	 * taquilla.
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

	/**
	 * Comprueba si el paquete dado es igual a this.
	 * 
	 * @return true si ambos paquetes son iguales.
	 * 
	 * @see java.util.Objects#equals(Object)
	 */
	@Override
	public boolean equals(Object paquete) {
		if (paquete != null && paquete.getClass() == this.getClass()) {
			boolean parcial = getId() == ((Package) paquete).getId()
					&& getFecha().equals(((Package) paquete).getFecha())
					&& getPrecio() == ((Package) paquete).getPrecio()
					&& getEstado() == ((Package) paquete).getEstado()
					&& getCertificado() == ((Package) paquete).getCertificado()
					&& getPagado() == ((Package) paquete).getPagado();

			if (((Package) paquete).getCertificado() && getCertificado()) {
				return parcial && getDni().equals(((Package) paquete).getDni());
			}
			return parcial;
		}
		return false;

	}

	/**
	 * Pasa el paquete a formato string.
	 * 
	 * @return El paquete en modo String.
	 * 
	 * @see java.lang.String#toString()
	 */
	@Override
	public String toString() {
		String resultado = "La id es " + getId() + ", su fecha limite es "
				+ getFecha().toString() + ", con precio " + getPrecio();
		if (getPagado()) {
			resultado += ", está pagado";
		} else {
			resultado += ", con pago a contrarrembolso";
		}

		if (getCertificado()) {
			resultado += ", está certificado con los DNIs: "
					+ getDni().toString();
		} else {
			resultado += ", no está certificado";

		}
		if (getEstado() == 0) {
			resultado += " y está en la taquilla.";

		} else if (getEstado() == 1) {
			resultado += " y está recogido.";

		} else {
			resultado += " y está devuelto.";
		}
		return resultado;

	}

}