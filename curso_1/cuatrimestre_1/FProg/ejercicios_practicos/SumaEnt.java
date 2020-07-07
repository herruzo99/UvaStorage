import java.util.Scanner;
public class SumaEnt {
	public static void main (String[] args){
		Scanner in = new Scanner (System.in);
		int n1, n2;
		System.out.println("Introduzca un número entero: ");
		n1 = in.nextInt();
                System.out.println("Teclee otro número entero: ");
                n2 = in.nextInt();
		System.out.println("Su suma es: "+ (n1+n2));
	}
}
