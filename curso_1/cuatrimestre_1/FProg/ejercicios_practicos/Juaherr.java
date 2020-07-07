import java.util.*;

public class Juaherr {
	public static long collatz(long numero) {
		// PRE numero>0 
		// DEVUELVE el maximo de ls serie
		
		long maximo = 0;
			
		// Aplica la conjetura de Collatz.
		while (numero != 1) {
			if (numero % 2 == 0) {
				numero /= 2;
			} else {
				numero = 3 * numero + 1;
			}
			
			/* Comprueba en cada iteracion de la conjetura si el numero 
			 * actual es el maximo hasta el momento.*/
			maximo = Math.max(numero, maximo);
		
		}
		return maximo;
	}
	
	public static void main(String[] args) {
		
		// Entrada núemros
		Scanner in = new Scanner(System.in);
		System.out.println("De dos numeros positivos");
		long numero = in.nextLong();
		long numero2 = in.nextLong();
		
		// Verificación de que sean positivos
		while (numero <= 0 || numero2 <= 0) {
			System.out.println("Los dos numeros tienen que ser positivos");
			numero = in.nextLong();
			numero2 = in.nextLong();
		}
		in.close();
		
		// Ordenación de los números en numero < numero2
		if (numero2 < numero) {
			long aux = numero2;
			numero2 = numero;
			numero = aux;
		}
		/*Comprueba para cada i numero<=i<=numero2 si el maximo de una serie 
		 * de Collatz es el maximo de todas las series hasta el momento*/
		long maxAbsoluto=0,maxRelativo=0;
		for (long i = numero; i <= numero2; i++) {
			maxRelativo = collatz(i);
			maxAbsoluto = Math.max(maxRelativo,maxAbsoluto);
		}
		
		//Escribe la solución
		System.out.println("El número máximo de todas las cadenas es "+ maxAbsoluto);

	}

}
