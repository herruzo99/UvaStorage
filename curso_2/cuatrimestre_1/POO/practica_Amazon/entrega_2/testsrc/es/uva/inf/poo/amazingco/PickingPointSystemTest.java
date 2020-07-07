package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointSystemTest {
	/*
	 * Test addPickingPoint()
	 */
	@Test
	public void testAddPickingPoint() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PostOffice office = new PostOffice("2", gps, horario);
		GroupablePickingPoint[] puntosRecogida = { kiosk, locker };
		PickingPointHub hub = new PickingPointHub("3", gps, horario,
				puntosRecogida);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		assertEquals(1, pps.getTodosPickingPoint().length);
		assertEquals(kiosk, pps.getPickingPoint("0"));

		pps.addPickingPoint(locker);
		assertEquals(2, pps.getTodosPickingPoint().length);
		assertEquals(locker, pps.getPickingPoint("1"));

		pps.addPickingPoint(office);
		assertEquals(3, pps.getTodosPickingPoint().length);
		assertEquals(office, pps.getPickingPoint("2"));

		pps.addPickingPoint(hub);
		assertEquals(4, pps.getTodosPickingPoint().length);
		assertEquals(hub, pps.getPickingPoint("3"));

	}
	/*
	 * Test addPickingPoint()
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testAddPickingPointNull() {
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddPickingPointMismaId() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("0", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
	}

	/*
	 * Test getTodosPickingPoint()
	 */

	@Test
	public void testGetTodosPickingPoint() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(2, pps.getTodosPickingPoint().length);
		assertTrue(Arrays.asList(pps.getTodosPickingPoint()).contains(kiosk));
		assertTrue(Arrays.asList(pps.getTodosPickingPoint()).contains(locker));
	}

	/*
	 * Test getPickingPoint()
	 */
	@Test
	public void testGetPickingPoint() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(2, pps.getTodosPickingPoint().length);
		assertEquals(kiosk, pps.getPickingPoint("0"));
		assertEquals(locker, pps.getPickingPoint("1"));

	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointSinPickingPointCreado() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPoint("0");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPickingPointIdNoValida() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();
		pps.addPickingPoint(kiosk);
		pps.getPickingPoint("a");

	}

	/*
	 * Test getPickingPointOperativos()
	 */
	@Test
	public void testGetPickingPointOperativos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, false);// no operativo
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(1, pps.getPickingPointOperativos().length);
		assertFalse(
				Arrays.asList(pps.getPickingPointOperativos()).contains(kiosk));
		assertTrue(Arrays.asList(pps.getPickingPointOperativos())
				.contains(locker));
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointOperativosPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPointOperativos();
	}
	/*
	 * Test getPickingPointFeueraDeServicio()
	 */

	@Test
	public void testGetPickingPointFueraDeServicio() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, false);// fuera de
																// servicio
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		assertEquals(1, pps.getPickingPointFueraDeServicio().length);
		assertTrue(Arrays.asList(pps.getPickingPointFueraDeServicio())
				.contains(kiosk));
		assertFalse(Arrays.asList(pps.getPickingPointFueraDeServicio())
				.contains(locker));
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointFueraDeServicioPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPointFueraDeServicio();
	}

	/*
	 * Test getPickingPointTaquillasVacias()
	 */
	@Test
	public void testGetPickingPointTaquillasVacias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		PackageLocker locker = new PackageLocker("1", gps, horario, 1);
		PickingPointSystem pps = new PickingPointSystem();
		Package paquete0 = new Package("0000000000", 0, true);

		kiosk.asignaPaquete(paquete0);
		pps.addPickingPoint(kiosk);// no tiene taquillas vacias
		pps.addPickingPoint(locker);
		assertEquals(1, pps.getPickingPointTaquillasVacias().length);
		assertFalse(Arrays.asList(pps.getPickingPointTaquillasVacias())
				.contains(kiosk));
		assertTrue(Arrays.asList(pps.getPickingPointTaquillasVacias())
				.contains(locker));
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointTaquillasVaciasPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPointTaquillasVacias();
	}

	/*
	 * Test getPickingPointEnZona()
	 */
	@Test
	public void testGetPickingPointEnZona() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		GPSCoordinate gps1 = new GPSCoordinate(2, 2);
		GPSCoordinate gps2 = new GPSCoordinate(0.9999909564, 1);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Kiosk kiosk1 = new Kiosk("2", gps1, horario, 2);// kiosk1 fuera de radio

		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk1);// no está dentro del radio
		pps.addPickingPoint(kiosk);
		assertEquals(1, pps.getPickingPointEnZona(gps2, 1).length);
		assertTrue(Arrays.asList(pps.getPickingPointEnZona(gps2, 1))
				.contains(kiosk));

		pps.addPickingPoint(locker);
		assertEquals(2, pps.getPickingPointEnZona(gps2, 1).length);
		assertTrue(Arrays.asList(pps.getPickingPointEnZona(gps2, 1))
				.contains(locker));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPickingPointEnZonaUbicacionNull() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.getPickingPointEnZona(null, 1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPickingPointEnZonaRadioNegativo() {
		PickingPointSystem pps = new PickingPointSystem();
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		pps.getPickingPointEnZona(gps, -1);
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPickingPointEnZonaPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		pps.getPickingPointEnZona(gps, 1);
	}

	/*
	 * Test getPickingPointEnZonaPaquete()
	 */

	@Test
	public void testGetPickingPointEnZonaPaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		GPSCoordinate gps1 = new GPSCoordinate(2, 2);
		GPSCoordinate gps2 = new GPSCoordinate(0.9999909564, 1);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Kiosk kiosk1 = new Kiosk("2", gps1, horario, 2);// kiosk1 fuera de radio

		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PickingPointSystem pps = new PickingPointSystem();
		Package paquete0 = new Package("0000000000", 0, true);

		pps.addPickingPoint(kiosk1);// no está dentro del radio
		pps.addPickingPoint(kiosk);
		assertEquals(1,
				pps.getPickingPointEnZonaPaquete(gps2, 1, paquete0).length);
		assertTrue(Arrays
				.asList(pps.getPickingPointEnZonaPaquete(gps2, 1, paquete0))
				.contains(kiosk));

		pps.addPickingPoint(locker);
		assertEquals(2,
				pps.getPickingPointEnZonaPaquete(gps2, 1, paquete0).length);
		assertTrue(Arrays
				.asList(pps.getPickingPointEnZonaPaquete(gps2, 1, paquete0))
				.contains(locker));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPickingPointEnZonaPaquetePaqueteNull() {
		PickingPointSystem pps = new PickingPointSystem();
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		pps.getPickingPointEnZonaPaquete(gps, 1, null);
	}
	/*
	 * Test eliminarPickingPoint()
	 */

	@Test
	public void testEliminarPickingPoint() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		PackageLocker locker = new PackageLocker("1", gps, horario, 2);
		PostOffice office = new PostOffice("2", gps, horario);
		GroupablePickingPoint[] puntosRecogida = { kiosk, locker };
		PickingPointHub hub = new PickingPointHub("3", gps, horario,
				puntosRecogida);

		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.addPickingPoint(locker);
		pps.addPickingPoint(office);
		pps.addPickingPoint(hub);

		pps.eliminarPickingPoint("0");// elimina el kiosk
		assertEquals(3, pps.getTodosPickingPoint().length);
		assertFalse(Arrays.asList(pps.getTodosPickingPoint()).contains(kiosk));

		pps.eliminarPickingPoint("1");
		assertEquals(2, pps.getTodosPickingPoint().length);
		assertFalse(Arrays.asList(pps.getTodosPickingPoint()).contains(locker));

		pps.eliminarPickingPoint("2");
		assertEquals(1, pps.getTodosPickingPoint().length);
		assertFalse(Arrays.asList(pps.getTodosPickingPoint()).contains(office));

		pps.eliminarPickingPoint("3");
		assertEquals(0, pps.getTodosPickingPoint().length);
		assertFalse(Arrays.asList(pps.getTodosPickingPoint()).contains(hub));

	}

	@Test(expected = IllegalStateException.class)
	public void testEliminarPickingPointTodaviaHayPaquetes() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, true);
		kiosk.asignaPaquete(paquete0);
		PickingPointSystem pps = new PickingPointSystem();

		pps.addPickingPoint(kiosk);
		pps.eliminarPickingPoint("0");
	}

	@Test(expected = IllegalStateException.class)
	public void testEliminarPickingPointPickingPointsNoCreados() {
		PickingPointSystem pps = new PickingPointSystem();
		pps.eliminarPickingPoint("0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEliminarPickingPointIdNoEncontrada() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointSystem pps = new PickingPointSystem();
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);

		pps.addPickingPoint(kiosk);
		pps.eliminarPickingPoint("a");
	}

}
