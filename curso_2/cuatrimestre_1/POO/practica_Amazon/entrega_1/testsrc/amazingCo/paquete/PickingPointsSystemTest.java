package amazingCo.paquete;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;

import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointsSystemTest {
	/*
	 * Pruebas válidas del generador.
	 */
	@Test
	public void testPickingPointsSystem() {
		PickingPointsSystem pps = new PickingPointsSystem();
		assertNotNull(pps);
	}

	/*
	 * Pruebas válidas para crear PackageLockers.
	 */
	@Test
	public void testCrearPackageLockerConOpcionOperativo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1, true);
		pps.crearPackageLocker("0000000001", gps, horario, 1, true);
		assertNotNull(pps.getPackageLocker("0000000000"));
		assertNotNull(pps.getPackageLocker("0000000001"));
		assertEquals(pps.getTodosPackageLocker().length, 2);
	}

	@Test
	public void testCrearPackageLockerOperativoDirectamente() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		pps.crearPackageLocker("0000000001", gps, horario, 1);
		assertNotNull(pps.getPackageLocker("0000000000"));
		assertNotNull(pps.getPackageLocker("0000000001"));
		assertEquals(pps.getTodosPackageLocker().length, 2);
	}

	/*
	 * Pruebas no validas para crear PackageLockers.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerConOpcionOperativoMismaId() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1, true);
		pps.crearPackageLocker("0000000000", gps, horario, 1, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerConOpcionOperativoIdNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker(null, gps, horario, 1, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerConOpcionOperativoUbicacionNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", null, horario, 1, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerConOpcionOperativoHorarioNull() {
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, null, 1, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerOperativoDirectamenteMismaId() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		pps.crearPackageLocker("0000000000", gps, horario, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerOperativoDirectamenteIdNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker(null, gps, horario, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerOperativoDirectamenteUbicacionNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", null, horario, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCrearPackageLockerOperativoDirectamenteHorarioNull() {
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, null, 1);
	}

	/*
	 * Pruebas válidas del metodo EliminarPackageLocker().
	 */
	@Test
	public void testEliminarPackageLocker() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		pps.crearPackageLocker("0000000001", gps, horario, 1);
		pps.eliminarPackageLocker("0000000000");
		assertNotNull(pps.getPackageLocker("0000000001"));
		assertEquals(pps.getTodosPackageLocker().length, 1);
	}

	/*
	 * Pruebas no válidas del metodo EliminarPackageLocker().
	 */
	@Test(expected = IllegalStateException.class)
	public void testEliminarPackageLockerTodaviaHayPaquetes() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		Package p = new Package("0000000000");
		pps.getPackageLocker("0000000000").asignaPaquete(p);
		pps.eliminarPackageLocker("0000000000");
	}

	@Test(expected = IllegalStateException.class)
	public void testEliminarPackageLockerArrayVacio() {
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.eliminarPackageLocker("0000000000");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEliminarPackageLockerIdNoEncontrada() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		pps.eliminarPackageLocker("0000000001");
	}

	/*
	 * Pruebas válidas del metodo getTodosPackageLocker().
	 */
	@Test
	public void testGetTodosPackageLocker() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getTodosPackageLocker().length, 1);
		assertEquals(pps.getTodosPackageLocker()[0].getId(), "0000000000");
	}

	/*
	 * Pruebas válidas del metodo getPackageLocker().
	 */
	@Test
	public void testGetPackageLocker() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertNotNull(pps.getPackageLocker("0000000000"));
		assertEquals(pps.getPackageLocker("0000000000").getId(), "0000000000");
	}

	/*
	 * Pruebas no válidas del metodo getPackageLocker().
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetPackageLockerArrayVacio() {
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.getPackageLocker("0000000000");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPackageLockerIdNoEncontrada() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		pps.getPackageLocker("0000000001");
	}

	/*
	 * Pruebas válidas del metodo getPackageLockerOperativos().
	 */
	@Test
	public void testGetPackageLockerOperativos() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getPackageLockerOperativos()[0].getId(), "0000000000");
		assertEquals(pps.getPackageLockerOperativos().length, 1);
	}

	@Test
	public void testGetPackageLockerOperativosNoDevolverNinguno() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1, false);
		assertEquals(pps.getPackageLockerOperativos().length, 0);
	}

	/*
	 * Pruebas no válidas del metodo getPackageLockerOperativos().
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetPackageLockerOperativosArrayVacio() {
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.getPackageLockerOperativos();
	}

	/*
	 * Pruebas válidas del metodo getPackageLockerFueraDeServicio().
	 */
	@Test
	public void testGetPackageLockerFueraDeServicio() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1, false);
		assertEquals(pps.getPackageLockerFueraDeServicio()[0].getId(), "0000000000");
		assertEquals(pps.getPackageLockerFueraDeServicio().length, 1);
	}

	@Test
	public void testGetPackageLockerFueraDeServicioNoDevolverNinguno() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getPackageLockerFueraDeServicio().length, 0);
	}

	/*
	 * Pruebas no válidas del metodo getPackageLockerFueraDeServicio().
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetPackageLockerFueraDeServicioArrayVacio() {
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.getPackageLockerFueraDeServicio();
	}

	/*
	 * Pruebas válidas del metodo getPackageLockerEnZona().
	 */
	@Test
	public void testGetPackageLockerEnZona() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		GPSCoordinate gps2 = new GPSCoordinate(0.9999909564, 1);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getPackageLockerEnZona(gps2, 1)[0].getId(), "0000000000");
		assertEquals(pps.getPackageLockerEnZona(gps2, 1).length, 1);
	}

	@Test
	public void testGetPackageLockerEnZonaNoEnZona() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		GPSCoordinate gps2 = new GPSCoordinate(0.9999909563, 1);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getPackageLockerEnZona(gps2, 0).length, 0);
	}

	@Test
	public void testGetPackageLockerEnZonaRadioCeroMetros() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		GPSCoordinate gps2 = new GPSCoordinate(1, 1);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getPackageLockerEnZona(gps2, 0)[0].getId(), "0000000000");
		assertEquals(pps.getPackageLockerEnZona(gps2, 0).length, 1);
	}

	/*
	 * Pruebas no válidas del metodo getPackageLockerEnZona().
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetPackageLockerEnZonaRadioNegativo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		GPSCoordinate gps2 = new GPSCoordinate(0.9999909564, 1);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		pps.getPackageLockerEnZona(gps2, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPackageLockerEnZonaUbicacionNula() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		pps.getPackageLockerEnZona(null, 1);
	}

	@Test(expected = IllegalStateException.class)
	public void testGetPackageLockerEnZonaArrayVacio() {
		PickingPointsSystem pps = new PickingPointsSystem();
		GPSCoordinate gps = new GPSCoordinate(1, 1);
		pps.getPackageLockerEnZona(gps, 1);
	}

	/*
	 * Pruebas válidas del metodo getPackageLockerTaquillasVacias().
	 */
	@Test
	public void testGetPackageLockerTaquillasVacias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getPackageLockerTaquillasVacias()[0].getId(), "0000000000");
		assertEquals(pps.getPackageLockerTaquillasVacias().length, 1);
	}

	@Test
	public void testGetPackageLockerTaquillasVaciasNoDevolverNinguno() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		Package p = new Package("0000000000");
		pps.getPackageLocker("0000000000").asignaPaquete(p);
		assertEquals(pps.getPackageLockerTaquillasVacias().length, 0);
	}

	/*
	 * Pruebas no válidas del metodo getPackageLockerTaquillasVacias().
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetPackageLockerTaquillasVaciasArrayVacio() {
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.getPackageLockerTaquillasVacias();
	}

	/*
	 * Pruebas válidas del metodo getPackageLockerTaquillasVaciasOperativas().
	 */
	@Test
	public void testGetPackageLockerTaquillasVaciasOperativas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		assertEquals(pps.getPackageLockerTaquillasVaciasOperativas()[0].getId(), "0000000000");
		assertEquals(pps.getPackageLockerTaquillasVaciasOperativas().length, 1);
	}

	@Test
	public void testGetPackageLockerTaquillasVaciasOperativasNoDevolverNingunoCeroTaquillas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1);
		Package p = new Package("0000000000");
		pps.getPackageLocker("0000000000").asignaPaquete(p);
		assertEquals(pps.getPackageLockerTaquillasVaciasOperativas().length, 0);
	}

	@Test
	public void testGetPackageLockerTaquillasVaciasOperativasNoDevolverNingunoFueraDeServicio() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1, false);
		assertEquals(pps.getPackageLockerTaquillasVaciasOperativas().length, 0);
	}

	@Test
	public void testGetPackageLockerTaquillasVaciasOperativasNoDevolverNingunoFueraDeServicioLleno() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) }, { LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, { LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		PickingPointsSystem pps = new PickingPointsSystem();
		pps.crearPackageLocker("0000000000", gps, horario, 1, false);
		Package p = new Package("0000000000");
		pps.getPackageLocker("0000000000").asignaPaquete(p);

		assertEquals(pps.getPackageLockerTaquillasVaciasOperativas().length, 0);
	}

	/*
	 * Pruebas no válidas del metodo getPackageLockerTaquillasVaciasOperativas().
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetPackageLockerTaquillasVaciasOperativasArrayVacio() {
		PickingPointsSystem pps = new PickingPointsSystem();
		pps.getPackageLockerTaquillasVaciasOperativas();
	}

}
