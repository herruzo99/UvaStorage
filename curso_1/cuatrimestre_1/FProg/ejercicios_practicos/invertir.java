import java.util.*;
 public class invertir {
	public static String invertir(int n){
                String numero=""+n;
                String fnumero="";
                int l=numero.length();
                while(l>0){
                        fnumero+=numero.charAt(l-1);
                        l--;
                }
		return fnumero;

	}
	public static void main(String[] args){
		System.out.println("Teclee un numero positivo");
		Scanner in=new Scanner(System.in);
		int num=in.nextInt();
		while(num<0){
			System.out.println("Dato no valido, tiene que ser positivo");
			num=in.nextInt();
		}	
		System.out.println("Tu numero es "+invertir(num));
	}
}
/*Otro modo:
while(n>0){
	System.out.print(n%10);
	n=m/10;
}

*/
