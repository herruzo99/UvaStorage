package amazingCo.paquete;

import static org.junit.Assert.*;

import org.junit.Test;
import java.time.LocalDate;

public class PackageTest {
	/*
	 * Pruebas generador tratado como conjunto, por lo que probamos los extremos.
	 */
	@Test
	public void testPackageDiezDigitos0() {
		Package p = new Package("0000000000");
		assertNotNull(p);
		assertEquals("0000000000", p.getId());
		LocalDate fecha = LocalDate.now().plusDays(7);
		assertEquals(fecha, p.getFecha());
	}

	@Test
	public void testPackageDiezDigitos9() {
		Package p = new Package("9999999991");
		assertNotNull(p);
		assertEquals("9999999991", p.getId());
		LocalDate fecha = LocalDate.now().plusDays(7);
		assertEquals(fecha, p.getFecha());
	}

	/*
	 * Pruebas no válidas del generador.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPackageNull() {
		Package p = new Package(null);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPackageNueveDigitos() {
		Package p = new Package("012345678");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPackageOnceDigitos() {
		Package p = new Package("01234567890");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPackageIncumpleLaCondicion() {
		Package p = new Package("1111111111");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPackagePrimeroNoEsDigito() {
		Package p = new Package("a111111111");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPackageNovenoNoEsDigito() {
		Package p = new Package("11111111a1");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testPackageDecimoNoEsDigito() {
		Package p = new Package("111111111a");
	}
	/*
	 * Pruebas del método setDiasMaximos().
	 */
	@Test
	public void testsetDiasMaximos() {
		Package.setDiasMaximos(0);
		assertEquals(Package.getDiasMaximos(), 0);
	}
	@Test( expected = IllegalArgumentException.class)
	public void testsetDiasMaximosNegativo() {
		Package.setDiasMaximos(-1);
	}
	/*
	 * Pruebas del método getFecha().
	 */
	@Test
	public void testGetFecha() {
		Package p = new Package("0000000000");
		LocalDate fecha = LocalDate.now().plusDays(Package.getDiasMaximos());
		assertEquals(fecha, p.getFecha());
	}

	/*
	 * Pruebas del método getId().
	 */
	@Test
	public void testGetId() {
		Package p = new Package("0000000000");
		assertEquals("0000000000", p.getId());
	}

	/*
	 * Pruebas del método fechaEnPlazo().
	 */
	@Test
	public void testFechaEnPlazoEnPlazo() {
		Package p = new Package("0000000000");
		LocalDate fecha = LocalDate.now().plusDays(Package.getDiasMaximos());
		assertTrue(p.getFecha() + "<" + fecha, p.fechaEnPlazo(fecha));

	}

	@Test
	public void testFechaEnPlazoPasada() {
		Package p = new Package("0000000000");
		LocalDate fecha = LocalDate.now().plusDays(Package.getDiasMaximos()+1);
		assertFalse(p.getFecha() + "<" + fecha, p.fechaEnPlazo(fecha));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testFechaEnPlazoNull() {
		Package p = new Package("0000000000");
		p.fechaEnPlazo(null);
	}

	/*
	 * Pruebas del método Recogido().
	 */
	@Test
	public void testRecogidoValido() {
		Package p = new Package("0000000000");
		p.recogido();
		assertEquals(1, p.getEstado());
	}

	@Test(expected = IllegalStateException.class)
	public void testRecogidoRecogido() {
		Package p = new Package("0000000000");
		p.recogido();
		p.recogido();
	}

	@Test(expected = IllegalStateException.class)
	public void testRecogidoDevuelto() {
		Package p = new Package("0000000000");
		p.devuelto();
		p.recogido();
	}

	/*
	 * Pruebas del método devuelto().
	 */
	@Test
	public void testDevueltoValido() {
		Package p = new Package("0000000000");
		p.devuelto();
		assertEquals(2, p.getEstado());
	}

	@Test(expected = IllegalStateException.class)
	public void testDevueltoRecogido() {
		Package p = new Package("0000000000");
		p.devuelto();
		p.devuelto();
	}

	@Test(expected = IllegalStateException.class)
	public void testDevueltoDevuelto() {
		Package p = new Package("0000000000");
		p.recogido();
		p.devuelto();
	}

}
