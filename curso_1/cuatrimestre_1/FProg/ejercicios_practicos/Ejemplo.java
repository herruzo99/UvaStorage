import java.util.Scanner;
import java.util.Locale;
public class Ejemplo {
	public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);

		String cadena, palabra;
		int i;
		float x;
		System.out.println("Teclee una línes: ");
		palabra=in.nextLine();
		System.out.println("Palabra leída desde teclado: "+ palabra);
		
		System.out.println("Teclee una línes: ");
                cadena=in.next();
                System.out.println("Palabra leída desde teclado: "+ cadena);

		System.out.println("numero");
		i = in.nextInt();
		System.out.println("Número leído: " +i);
		System.out.println("Teclee una palabra seguida de un entero y un número real:");

		palabra = in.next();
		i=in.nextInt();
		x=in.nextFloat();
		System.out.println ("Palabra: " +palabra + ". Número entero: "+i+". Número real: " + x);
	}
}
