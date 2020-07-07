import java.util.Scanner;
public class PrecioBillete{
	public static double calcularPrecio(int km, int dias){
		if(km > 1000 && dias >7){
			return 2* (km * 11.5)*0.7;
		}
		return 2* (km*11.5);
	}
	public static void main(String[] args){
		Scanner in = new Scanner (System.in);
		int km, dias;
		km = in.nextInt();
		dias = in.nextInt();
		System.out.println("El precio es: " + calcularPrecio(km,dias)+"€" );
	}
}
