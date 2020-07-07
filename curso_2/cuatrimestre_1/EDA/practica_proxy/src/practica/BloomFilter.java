package practica;

public class BloomFilter {

	public static final int M = 6400000;
	public static byte[] tablaBloom;

	public static void inicializaBloom() {
		tablaBloom = new byte[800000];
	}

	// Pone a 1 el bit i‐esimo del array v
	public static void setBit(byte[] v, int i) {
		v[i / 8] |= 1 << (i % 8);
	}

	// Devuelve el valor (0 o 1) del bit i‐esimo del array v
	public static int getBit(byte[] v, int i) {
		return (v[i / 8] >> (i % 8)) & 1;
	}

	public static int hash1(String txt) { // Hash usado en Java
		int n = txt.length();
		int h = 0;
		for (int i = 0; i < n; i++) {
			h = 31 * h + txt.charAt(i);
		}
		return h;
	}

	public static int hash2(String txt) { // Hash usado en C#
		int n = txt.length();
		int h1 = 5381;
		for (int i = 0; i < n; i += 2) {
			h1 = (33 * h1) ^ txt.charAt(i);
		}
		int h2 = 5381;
		for (int i = 1; i < n; i += 2) {
			h2 = (33 * h2) ^ txt.charAt(i);
		}
		return h1 + (h2 * 1566083941);
	}

	public static void crearBloom(String url) {
		int x = hash1(url) % M;
		int y = hash2(url) % M;
		setBit(tablaBloom, x);
		for (int i = 1; i < 3; i++) {
			x = (x + y) % M;
			y = (y + i) % M;
			setBit(tablaBloom, x);

		}
	}

	public static int comprobarBloom(String url) {
		int resultado = 1;
		int x = hash1(url) % M;
		int y = hash2(url) % M;
		resultado = getBit(tablaBloom, x);
		int i = 0;
		while (i < 3 && resultado == 1) {
			x = (x + y) % M;
			y = (y + i) % M;
			resultado = getBit(tablaBloom, x);
			i++;
		}

		return resultado;
	}
}
