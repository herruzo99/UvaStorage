import java.util.*;

public class Triangulos{
	public static void main (String args[]){
		Scanner in = new Scanner(System.in);
		System.out.println("teclee el número de filas: ");
		int n = in.nextInt();

		int ast;
		int fil=1;
		while(fil <=n){
			ast=1;
			while(ast<=n-fil){
				System.out.print(" ");
				ast++;
			}
			ast=1;
			while(ast <= fil){
				System.out.print ("*");
				ast++;
			}
		System.out.println("");
		fil++;
		}
	}
}
