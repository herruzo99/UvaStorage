import java.awt.Point;
import java.util.*;

//Programa de Juan Herruzo Herrero y Mario Llorente Pérez
public class JuaHerr {

	// Cambia el tamaño de la tabla.
	// Aviso :
	// --X e Y menor o igual que 101 y mayor o igual que 5.
	// --Para poder usar el modo tabla fija X e Y tinen que ser 11.
	// --El numero de filas y columnas final sera el puesto menos dos.
	public static final int COLUMNAS = 11;
	public static final int FILAS = 11;

	// Cambia el tipo de caramelos.
	public static final char[] CARAMELOS = { '*', '<', 'x', '#', '$' };

	// Cambia el numero de movimientos.
	public static final int MOVIMIENTOS = 10;

	public static boolean sonCero(int x1, int y1, int x2, int y2) {
		// Si los 4 ints son 0 devuelve true.

		if (y1 == 0 && y2 == 0 && x1 == 0 && x2 == 0) {
			return true;
		}
		return false;
	}

	public static boolean datosValidos(int x1, int y1, int x2, int y2) {
		// Comprueba que los datos dados son validos.
		// Si los datos son adyacentes y estan dentro del rango devuelve true

		if (((y1 == y2 && Math.abs(x1 - x2) == 1) || (x1 == x2 && Math.abs(y1 - y2) == 1))
				&& (0 < x1 && x1 < COLUMNAS - 1 && 0 < y1 && y1 < FILAS - 1 && 0 < x2 && x2 < COLUMNAS - 1 && 0 < y2 && y2 < FILAS - 1)) {
			return true;
		}
		if (sonCero(x1, y1, x2, y2)) {
			return true;
		}
		return false;
	}

	public static void crearTabla(char[][] tabla, int modo) {
		// Crea la tabla de caramelos.
		// PRE: modo = 0 o entre 3 y 5.
		// La tabla tiene un borde e una fila y una columna para facilitar los calculos.

		if (modo == 0) {
			char[][] tablaFija = { { 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N' },
					{ 'N', CARAMELOS[1], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], 'N' },
					{ 'N', CARAMELOS[0], CARAMELOS[1], CARAMELOS[1], CARAMELOS[1], CARAMELOS[0], CARAMELOS[1], CARAMELOS[1], CARAMELOS[1], CARAMELOS[0], 'N' },
					{ 'N', CARAMELOS[2], CARAMELOS[1], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[0], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], 'N' },
					{ 'N', CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], 'N' },
					{ 'N', CARAMELOS[1], CARAMELOS[1], CARAMELOS[1], CARAMELOS[1], CARAMELOS[0], CARAMELOS[1], CARAMELOS[1], CARAMELOS[0], CARAMELOS[2], 'N' },
					{ 'N', CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[0], 'N' },
					{ 'N', CARAMELOS[0], CARAMELOS[0], CARAMELOS[1], CARAMELOS[0], CARAMELOS[2], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], CARAMELOS[0], 'N' },
					{ 'N', CARAMELOS[1], CARAMELOS[1], CARAMELOS[0], CARAMELOS[1], CARAMELOS[1], CARAMELOS[1], CARAMELOS[1], CARAMELOS[0], CARAMELOS[1], 'N' },
					{ 'N', CARAMELOS[2], CARAMELOS[1], CARAMELOS[0], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[2], CARAMELOS[0], 'N' },
					{ 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N' } };

			// La tabla fija se copia a la tabla del juego.
			for (int i = 1; i < tabla.length - 1; i++) {
				for (int j = 1; j < tabla[0].length - 1; j++) {
					tabla[i][j] = tablaFija[i][j];
				}
			}
		} else {

			// Crea una tabla al azar de "modo" caramelos distintos.
			for (int i = 1; i < tabla.length - 1; i++) {
				for (int j = 1; j < tabla[0].length - 1; j++) {
					tabla[i][j] = CARAMELOS[(int) Math.floor(Math.random() * modo)];
				}
			}
		}
	}

	public static boolean tablaCorrecta(char[][] tabla) {
		// Comprueba si la tabla no tiene movimientos validos, devolviendo true si no existen movimientos.
		// PRE: El numero minimo de filas del char[][] dado tiene que ser 4.

		boolean hayMovimientos = false;

		for (int i = 1; i < tabla.length - 3; i++) {
			for (int j = 1; j < tabla[0].length - 1; j++) {

				// Comprueba las posible combinaciones que deberian existir para que se pudiera hacer un
				// grupo de tres con solo un movimento valido.
				if (tabla[i][j] == tabla[i + 1][j - 1] || tabla[i][j] == tabla[i + 1][j + 1]) {
					if (tabla[i][j] == tabla[i + 2][j]) {
						hayMovimientos = true;
					}
				}
				if (tabla[i][j] == tabla[i + 2][j - 1] || tabla[i][j] == tabla[i + 2][j + 1]) {
					if (tabla[i][j] == tabla[i + 1][j]) {
						hayMovimientos = true;
					}
				}
				if (tabla[i][j - 1] == tabla[i + 1][j] || tabla[i][j + 1] == tabla[i + 1][j]) {
					if (tabla[i + 1][j] == tabla[i + 2][j]) {
						hayMovimientos = true;
					}
				}
				if (tabla[i][j] == tabla[i + 1][j] || tabla[i][j] == tabla[i + 2][j]) {
					if (tabla[i][j] == tabla[i + 3][j]) {
						hayMovimientos = true;
					}
				}
			}
		}
		return hayMovimientos;
	}

	public static void actualizarTabla(char[][] tabla, int modo, Point puntuacion) {
		// Comprueba si hay bloques de 3 o mas, los elimina y aumenta la puntuacion.
		// PRE: modo entre 3 y 5

		boolean actualizada;
		puntuacion.y = 0;
		// Mientras haya habido un bloque destruido volvera a comprobar por nuevos bloques por si ha aparecido alguno nuevo.
		do {
			actualizada = false;

			for (int i = 1; i < tabla.length - 3; i++) {
				for (int j = 1; j < tabla[0].length - 1; j++) {

					// Hace una comprobacion inicial de tres seguidos.
					if (tabla[i][j] == tabla[i + 1][j] && tabla[i][j] == tabla[i + 2][j]) {
						actualizada = true;
						int seguidos = 3;

						// Si hay un bloque de 3 comprueba si en realidad es de 4 o mas.
						while (tabla[i][j] == tabla[i + seguidos][j]) {
							seguidos++;
						}

						// Copia los caramelos de la parte superior del bloque y los pega en la parte del bloque.
						// (Si no hubiera caramelos suficientes por encima, rellena con caramelos al azar en el do while siguiente)
						for (int k = 1; k < i; k++) {
							tabla[i + seguidos - k][j] = tabla[i - k][j];
						}

						// Rellena los nuevos huecos con caramelos al azar y lo reitera hasta que haya un movimiento valido.
						do {
							for (int k = 1; k <= seguidos; k++) {
								tabla[k][j] = CARAMELOS[(int) Math.floor(Math.random() * modo)];
							}
						} while (!tablaCorrecta(tabla));
						puntuacion.x += 10 * (seguidos - 2);
						puntuacion.y += 10 * (seguidos - 2);
					}
				}
			}
		} while (actualizada == true);
	}

	public static void mostrarTabla(char[][] tabla) {
		// Procedimiento: Muestra por consola el char[][] dado numerando sus filas y columnas.

		for (int i = 1; i < tabla.length - 1; i++) {

			// Numeracion filas
			if ((tabla.length - i) < 11) {
				System.out.print((tabla.length - i - 1) + "   ");
			} else {
				System.out.print((tabla.length - i - 1) + "  ");
			}

			// Imprime la matriz en pantalla.
			for (int j = 1; j < tabla[0].length - 1; j++) {
				if (tabla[0].length < 13) {
					System.out.print(tabla[i][j] + " ");
				} else {
					System.out.print(tabla[i][j] + "  ");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.print("    ");
		for (int j = 1; j < tabla[0].length - 1; j++) {

			// Numeracion columnas
			if (tabla[0].length - 1 < 11) {
				System.out.print(j + " ");
			} else {
				if (j < 10) {
					System.out.print(j + "  ");
				} else {
					System.out.print(j + " ");
				}
			}
		}
		System.out.println();
	}

	public static void moverCaramelos(int x1, int y1, int x2, int y2, char[][] tabla) {
		// Intercambia la posicion de dos caramelos dados por sus coordenadas.
		// PRE las x entre 1 y la constante X e y entre 1 y la constante Y

		char aux;
		aux = tabla[y1][x1];
		tabla[y1][x1] = tabla[y2][x2];
		tabla[y2][x2] = aux;
	}

	public static void initJuego(char[][] tabla, int modo, Point puntuacionYAux) {
		// Crea una tabla valida para jugar.
		// Mientras no tenga movimientos crea otra tabla.

		do {
			crearTabla(tabla, modo);
			actualizarTabla(tabla, modo, puntuacionYAux);
		} while (!tablaCorrecta(tabla));
	}

	public static void juego(int modo, Scanner in) {
		// Funcion donde funciona el juego, por asi decirlo la funcion base del programa.

		char[][] tabla = new char[FILAS][COLUMNAS];

		// Point:
		// la X es la puntuacion total del Juego.
		// la Y es la puntuacion por ronda del juego.
		Point puntuacion = new Point(0, 0);

		initJuego(tabla, modo, puntuacion);

		// Si se elija la tabla fija(modo = 0), se cambia el modo para poder crear bien
		// los caramelos al azar al jugar.
		if (modo == 0) {
			modo = 3;
		}

		// Se reinicia la puntuacion del juego a 0.
		puntuacion.x = 0;
		// La puntuacion por ronda se pone a 1 para no afectar al inicio del progama
		puntuacion.y = 1;

		// Se inician la variables en un valor que no afecte al primer bucle del programa.
		int y1 = -1, y2 = -1, x1 = -1, x2 = -1;

		int contador = 0;

		while (contador < MOVIMIENTOS && !sonCero(x1, y1, x2, y2)) {

			// Si el movimiento anterior ha sido nulo,no se habran obtenido puntos, por lo que no se muestra la tabla de nuevo.
			if (puntuacion.y != 0) {
				System.out.println("Movimientos restantes: " + (MOVIMIENTOS - contador));
				mostrarTabla(tabla);
				System.out.println("Escoja las coordenadas de cos casillas adyacentes:Filas=[1," + (FILAS - 2) + "] Columnas=[1," + (COLUMNAS - 2) + "]");
			}

			y1 = in.nextInt();
			x1 = in.nextInt();
			y2 = in.nextInt();
			x2 = in.nextInt();

			// Para que la primera fila sea la de abajo, se crea una variable "y" invertida para evitar complejidad.
			int y1Invert = (FILAS - y1 - 1);
			int y2Invert = (FILAS - y2 - 1);

			// Se piden los datos hasta que sean validos.
			while (!datosValidos(x1, y1, x2, y2)) {
				System.out.println("Coordenadas no adyacentes o fuera del limite, escoja otras:Filas=[1," + (FILAS - 2) + "] Columnas=[1," + (COLUMNAS - 2)
						+ "]");
				y1 = in.nextInt();
				x1 = in.nextInt();
				y2 = in.nextInt();
				x2 = in.nextInt();
				y1Invert = (FILAS - y1 - 1);
				y2Invert = (FILAS - y2 - 1);
			}

			if (!sonCero(x1, y1, x2, y2)) {
				moverCaramelos(x1, y1Invert, x2, y2Invert, tabla);
				actualizarTabla(tabla, modo, puntuacion);
				// Si al mover los caramelos no se ha creado un bloque devuelve los caramelos a su lugar y no cuneta como jugada.
				if (puntuacion.y == 0) {
					System.out.println("Movimiento nulo, repita otro:");
					moverCaramelos(x1, y1Invert, x2, y2Invert, tabla);

				} else {
					contador++;

					// Se comprueba que no es la ultima jugada para no escribir la puntuacion dos veces.
					if (contador < MOVIMIENTOS) {
						System.out.println("Puntuacion de la ronda : " + puntuacion.y);
						System.out.println("Puntuacion actual: " + puntuacion.x + "\n");
					}
				}
			}
		}
		System.out.println("Puntuacion de la ronda : " + puntuacion.y);
		System.out.println("Puntuacion final:" + puntuacion.x + "\n");

	}

	public static void main(String[] args) {
		// PRE: Existen las constantes: (int)COLUMNAS, (int)FILAS, (char[][])CARAMELOS y (int)MOVIMIENTOS.
		// Empieza el menu principal del juego.
		int nivel;
		Scanner in = new Scanner(System.in);

		do {
			System.out.println("Bienvenido a Candy Crush");
			System.out.println("Elija el tipo de tablero:");
			System.out.println("1. Facil");
			System.out.println("2. Intermedio");
			System.out.println("3. Dificil");
			System.out.println("4. Tablero fijo");
			System.out.println("0. Salir");

			nivel = in.nextInt();

			// Comprueba que el dato sera valido.
			while (nivel < 0 || 5 < nivel) {
				System.out.println("Modo incorrecto, elija un modo valido");
				System.out.println("1. Facil");
				System.out.println("2. Intermedio");
				System.out.println("3. Dificil");
				System.out.println("4. Tablero fijo");
				System.out.println("0. Salir");

				nivel = in.nextInt();
			}

			// Dependiendo de dificultad elije el numero de caramelos de la partida.
			switch (nivel) {
			case 1:
				System.out.println("Modo facil");
				juego(3, in);
				break;
			case 2:
				System.out.println("Modo intermedio");
				juego(4, in);
				break;
			case 3:
				System.out.println("Modo dificil");
				juego(5, in);
				break;
			case 4:
				System.out.println("Tablero fijo");
				juego(0, in);
				break;
			case 0:
				break;
			}

		} while (nivel != 0);
		in.close();
		System.out.println("Gracias por jugar!");
	}
}
