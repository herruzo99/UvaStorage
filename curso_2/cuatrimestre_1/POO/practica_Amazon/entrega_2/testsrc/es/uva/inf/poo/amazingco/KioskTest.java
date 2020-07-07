package es.uva.inf.poo.amazingco;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import es.uva.inf.poo.amazingco.Kiosk;
import es.uva.inf.poo.maps.GPSCoordinate;

import org.junit.Test;

public class KioskTest {
	/*
	 * Test constructores.
	 */
	@Test
	public void KioskConOpcionOperativo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);

		assertEquals("0", kiosk.getId());
		assertEquals(gps, kiosk.getUbicacion());
		assertArrayEquals(horario, kiosk.getHorario());
		assertEquals(1, kiosk.getNumeroTaquillas());
		assertTrue(kiosk.getOperativo());
	}

	/*
	 * Pruebas no válidas del constructor con opción de operatividad.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativaIdNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk(null, gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoGpsNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		Kiosk kiosk = new Kiosk("0", null, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoHorarioNull() {
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0000000000", gps, null, 1, true);
	}

	/*
	 * Pruebas no válida del constructor con opción de operatividad donde se
	 * analiza el horario como un conjunto, por tanto se prueban los errores en
	 * los extremos.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoOchoDias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoSeisDias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoPrimerDiaNull() {
		LocalTime[][] horario = { null,
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoUltimoDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, null };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoHoraAperturaPrimerDiaNull() {
		LocalTime[][] horario = { { null, LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoHoraCierrePrimerDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), null },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoHoraAperturaUltimoDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ null, LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoHoraCierreUltimoDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), null } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoPrimerDiaTresHoras() {
		LocalTime[][] horario = {
				{ LocalTime.of(3, 30), LocalTime.of(8, 0),
						LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoPrimerDiaUnaHora() {
		LocalTime[][] horario = { { LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoUltimoDiaTresHoras() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(1, 0), LocalTime.of(2, 15),
						LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoUltimoDiaUnaHora() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoPrimerDiaHorasMalIntroducidas() {
		LocalTime[][] horario = { { LocalTime.of(23, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoUltimoDiaHorasMalIntroducidas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(23, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);
	}

	/*
	 * Pruebas no válidas del constructor con opción de operativiad respecto al
	 * número de Taquillas.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoTaquillasNegativas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, -1, true);

	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskConOpcionOperativoTaquillasCero() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 0, true);

	}

	/*
	 * Test constructor Kiosk con operatividad = true.
	 */
	@Test
	public void testKioskOperativoDirectamente() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1, true);

		assertEquals("0", kiosk.getId());
		assertEquals(gps, kiosk.getUbicacion());
		assertArrayEquals(horario, kiosk.getHorario());
		assertEquals(1, kiosk.getNumeroTaquillas());
		assertTrue(kiosk.getOperativo());

	}

	/*
	 * Pruebas no válidas del constructor con opcion operativo.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteIdNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk(null, gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteGpsNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		Kiosk kiosk = new Kiosk("0", null, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteHorarioNull() {
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, null, 1);
	}

	/*
	 * Pruebas no válida del constructor del Kiosk operativo donde se analiza el
	 * horario como un conjunto, por tanto se prueba los extremos de los erroes.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteOchoDias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteSeisDias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamentePrimerDiaNull() {
		LocalTime[][] horario = { null,
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteUltimoDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) }, null };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteHoraAperturaPrimerDiaNull() {
		LocalTime[][] horario = { { null, LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteHoraCierrePrimerDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), null },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteHoraAperturaUltimoDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ null, LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteHoraCierreUltimoDiaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), null } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamentePrimerDiaTresHoras() {
		LocalTime[][] horario = {
				{ LocalTime.of(3, 30), LocalTime.of(8, 0),
						LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamentePrimerDiaUnaHora() {
		LocalTime[][] horario = { { LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteUltimoDiaTresHoras() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(1, 0), LocalTime.of(2, 15),
						LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteUltimoDiaUnaHora() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamentePrimerDiaHorasMalIntroducidas() {
		LocalTime[][] horario = { { LocalTime.of(23, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteUltimoDiaHorasMalIntroducidas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(23, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
	}

	/*
	 * Pruebas no váidas del constructor de Kiosk operativo respecto al número
	 * de Taquillas.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteTaquillasNegativas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, -1, true);

	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testKioskOperativoDirectamenteTaquillasCero() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 0, true);

	}

	@Test
	public void testGetDinero() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 1, false);// paquete no
																// pagado
		Package paquete1 = new Package("0000000011", 2, false);// paquete no
																// pagado

		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(8, 1));
		assertEquals(1, kiosk.getDinero(), 0.01);

		kiosk.asignaPaquete(paquete1);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000011"), LocalDate.now(),
				0, LocalTime.of(8, 1));
		assertEquals(3, kiosk.getDinero(), 0.01);
	}

	/*
	 * Test AsignaPaquete()
	 */

	@Test
	public void testAsignaPaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, true);
		Package paquete1 = new Package("0000000011", 0, false);
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);
		assertEquals(paquete0,
				kiosk.getPaquete(kiosk.localizaPaquete("0000000000")));
		assertEquals(paquete1,
				kiosk.getPaquete(kiosk.localizaPaquete("0000000011")));

		// Caja blanca comprobar que guarda bien en una posicion ya borrada.
		Package paquete3 = new Package("0000000022", 0, true);
		kiosk.borraPaquete(0);
		kiosk.asignaPaquete(paquete3);
		assertEquals(paquete3,
				kiosk.getPaquete(kiosk.localizaPaquete("0000000022")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAsignaPaqueteCertificado() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		String[] dni = { "1234" };

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 0, false, dni);// paquete
																	// certificado
		kiosk.asignaPaquete(paquete0);

	}

	@Test(expected = IllegalStateException.class)
	public void testAsignaPaqueteKioskNoOperativo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2, false);
		Package paquete0 = new Package("0000000000", 0, true);

		kiosk.asignaPaquete(paquete0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAsignaPaqueteNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		kiosk.asignaPaquete(null);
	}

	@Test(expected = IllegalStateException.class)
	public void testAsignaPaqueteKioskLleno() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);

		Package paquete0 = new Package("0000000000", 0, true);
		Package paquete1 = new Package("0000000011", 0, false);
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);

	}

	@Test(expected = IllegalStateException.class)
	public void testAsignaPaqueteIdRepetido() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);

		Package paquete0 = new Package("0000000000", 0, true);
		Package paquete1 = new Package("0000000000", 0, false);
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);

	}
	/*
	 * Test SacaPaquete()
	 */

	@Test
	public void testSacaPaqueteAlAbrir() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
																// pagado
		kiosk.asignaPaquete(paquete0);

		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(8, 0));

		assertEquals(1, paquete0.getEstado());
	}

	@Test
	public void testSacaPaqueteAlCerrar() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
																// pagado
		kiosk.asignaPaquete(paquete0);

		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(14, 0));

		assertEquals(1, paquete0.getEstado());
	}

	@Test(expected = IllegalStateException.class)
	public void testSacaPaqueteFueraDeServicio() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, false);
		kiosk.sacaPaquete(0, LocalDate.now(), 0, LocalTime.of(8, 1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacaPaqueteTaquillaMenorCero() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(-1, LocalDate.now(), 0, LocalTime.of(8, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacaPaqueteMayorNumeroTaquillas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.getNumeroTaquillas() + 1, LocalDate.now(), 0,
				LocalTime.of(8, 1));
	}

	@Test(expected = IllegalStateException.class)
	public void testSacaPaqueteTaquillaVacia() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		kiosk.sacaPaquete(0, LocalDate.now(), 0, LocalTime.of(8, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacaPaqueteFechaNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), null, 1,
				LocalTime.of(8, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacaPaqueteDiaMenorDe0() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				-1, LocalTime.of(8, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacaPaqueteDiaMayorDe6() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				7, LocalTime.of(8, 0));
	}

	@Test(expected = IllegalStateException.class)
	public void testSacaPaqueteKioskCerradoAntesDeAbrir() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(7, 59));

	}

	@Test(expected = IllegalStateException.class)
	public void testSacaPaqueteKioskCerradoDespuesDeCerrar() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(14, 01));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSacaPaqueteHoraNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				1, null);
	}

	@Test(expected = IllegalStateException.class)
	public void testSacaPaqueteFechaFueraDePlazo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"),
				LocalDate.now().plusDays(Package.getDiasMaximos() + 1), 0,
				LocalTime.of(8, 1));

	}

	/*
	 * Test devuelvePaquete().
	 */
	@Test
	public void testDevuelvePaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		Package paquete1 = new Package("0000000011", 0, false);// paquete no
																// pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);
		kiosk.devuelvePaquete(kiosk.localizaPaquete(paquete0.getId()));
		kiosk.devuelvePaquete(kiosk.localizaPaquete(paquete1.getId()));

		assertEquals(2, paquete0.getEstado());
		assertEquals(2, paquete1.getEstado());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testDevuelvePaqueteMenorCero() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.devuelvePaquete(-1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testDevuelvePaqueteMayorNumeroTaquillas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		Package paquete0 = new Package("0000000000", 0, true);// paquete pagado
		kiosk.asignaPaquete(paquete0);
		kiosk.devuelvePaquete(kiosk.getNumeroTaquillas() + 1);

	}

	@Test(expected = IllegalStateException.class)
	public void testDevuelvePaqueteTaquillaVacia() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("0", gps, horario, 2, true);
		kiosk.devuelvePaquete(0);

	}

	/*
	 * Test dineroAmazingCo()
	 */
	@Test
	public void testDineroAmazingCo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		Package paquete0 = new Package("0000000000", 1, false);// paquete no
																// pagado

		kiosk.asignaPaquete(paquete0);
		kiosk.sacaPaquete(kiosk.localizaPaquete("0000000000"), LocalDate.now(),
				0, LocalTime.of(8, 1));
		kiosk.dineroAmazingCo();
		assertEquals(0, kiosk.getDinero(), 0.01);
	}

	/*
	 * Test modificaNumTaquillas()
	 */
	@Test
	public void testModificarNumTaquillas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		kiosk.modificarNumTaquillas(1);
		assertEquals(2, kiosk.getNumeroTaquillas());

		kiosk.modificarNumTaquillas(-1);
		assertEquals(1, kiosk.getNumeroTaquillas());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testModificarNumTaquillasMenor1() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		kiosk.modificarNumTaquillas(-1);
	}

	@Test
	public void testGetOperativo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		Kiosk kiosk1 = new Kiosk("1", gps, horario, 1, false);
		assertTrue(kiosk.getOperativo());
		assertFalse(kiosk1.getOperativo());
	}

	@Test
	public void testgetNumeroTaquillas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);

		assertEquals(1, kiosk.getNumeroTaquillas());

	}

	@Test
	public void testgetNumeroTaquillasLlenas() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		Kiosk kiosk1 = new Kiosk("1", gps, horario, 1);
		Package paquete0 = new Package("0000000000", 0, true);
		kiosk1.asignaPaquete(paquete0);

		assertEquals(0, kiosk.getNumeroTaquillasLlenas());
		assertEquals(1, kiosk1.getNumeroTaquillasLlenas());
	}

	@Test
	public void testgetNumeroTaquillasVacias() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		Kiosk kiosk1 = new Kiosk("1", gps, horario, 1);
		Package paquete0 = new Package("0000000000", 0, true);
		kiosk1.asignaPaquete(paquete0);

		assertEquals(1, kiosk.getNumeroTaquillasVacias());
		assertEquals(0, kiosk1.getNumeroTaquillasVacias());
	}

	@Test
	public void testgetPaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);

		Package paquete0 = new Package("0000000000", 0, true);
		Package paquete1 = new Package("0000000011", 0, false);
		kiosk.asignaPaquete(paquete0);
		assertEquals(paquete0, kiosk.getPaquete(0));

		kiosk.asignaPaquete(paquete1);
		assertEquals(paquete1, kiosk.getPaquete(1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testgetPaqueteFueraDeRangoInferior() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		kiosk.getPaquete(-1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testgetPaqueteFueraDeRangoSuperior() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		kiosk.getPaquete(2);

	}

	@Test(expected = IllegalStateException.class)
	public void testgetPaqueteTaquillaVacia() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);
		kiosk.getPaquete(0);

	}

	@Test
	public void testgetPaquetes() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 2);

		Package paquete0 = new Package("0000000000", 0, true);
		Package paquete1 = new Package("0000000011", 0, false);
		kiosk.asignaPaquete(paquete0);
		kiosk.asignaPaquete(paquete1);
		assertTrue(kiosk.getPaquetes().contains(paquete0));
		assertTrue(kiosk.getPaquetes().contains(paquete1));

	}

	@Test
	public void testBorrable() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		Kiosk kiosk1 = new Kiosk("1", gps, horario, 1);
		Package paquete0 = new Package("0000000000", 0, true);
		kiosk1.asignaPaquete(paquete0);

		assertTrue(kiosk.borrable());
		assertFalse(kiosk1.borrable());
	}

	@Test
	public void testGetHorario() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);

		assertArrayEquals(horario, kiosk.getHorario());

	}

	@Test
	public void testGetUbicacion() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);

		assertEquals(gps, kiosk.getUbicacion());
	}

	@Test
	public void testGetId() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);

		assertEquals("0", kiosk.getId());
	}

	@Test
	public void testOperatividad() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("0", gps, horario, 1);
		Kiosk kiosk1 = new Kiosk("1", gps, horario, 1, false);

		kiosk.operatividad();
		kiosk1.operatividad();

		assertFalse(kiosk.getOperativo());
		assertTrue(kiosk1.getOperativo());

	}

	@Test
	public void testLocalizaPaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);

		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);
		Package paquete = new Package("0000000000", 0, true);

		kiosk.asignaPaquete(paquete);
		assertEquals(0, kiosk.localizaPaquete("0000000000"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLocalizaPaqueteIdNull() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		kiosk.localizaPaquete(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLocalizaPaqueteKioskVacio() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		kiosk.localizaPaquete("0000000000");
	}

	@Test
	public void testBorraPaquete() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 2);

		Package paquete = new Package("0000000000", 0, true);
		Package paquete2 = new Package("0000000011", 0, false);

		kiosk.asignaPaquete(paquete);
		kiosk.asignaPaquete(paquete2);

		kiosk.borraPaquete(kiosk.localizaPaquete("0000000000"));
		assertEquals(paquete2,
				kiosk.getPaquete(kiosk.localizaPaquete("0000000011")));

		assertEquals(1, kiosk.getNumeroTaquillasLlenas());
		assertEquals(1, kiosk.getNumeroTaquillasVacias());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBorraPaqueteFueraDeRangoArriba() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		Package paquete = new Package("0000000000", 0, true);

		kiosk.asignaPaquete(paquete);
		kiosk.borraPaquete(2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testBorraPaqueteFueraDeRengoAbajo() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);
		Package paquete = new Package("0000000000", 0, true);

		kiosk.asignaPaquete(paquete);
		kiosk.borraPaquete(-1);

	}

	@Test(expected = IllegalStateException.class)
	public void testBorraPaqueteTaquillasVacia() {
		LocalTime[][] horario = { { LocalTime.of(8, 0), LocalTime.of(14, 0) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(9, 30), LocalTime.of(21, 10) },
				{ LocalTime.of(7, 15), LocalTime.of(20, 20) },
				{ LocalTime.of(6, 30), LocalTime.of(21, 0) },
				{ LocalTime.of(5, 45), LocalTime.of(15, 50) },
				{ LocalTime.of(2, 15), LocalTime.of(23, 00) } };
		GPSCoordinate gps = new GPSCoordinate(41.6551455, -4.7381979);
		Kiosk kiosk = new Kiosk("kiosk", gps, horario, 1);

		kiosk.borraPaquete(0);

	}

}
