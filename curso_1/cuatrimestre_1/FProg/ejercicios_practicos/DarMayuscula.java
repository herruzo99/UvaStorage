import java.util.*;
public class DarMayuscula{
	public static char mayuscula(String a){
		char b;
		b=a.charAt(0);
		return	(char)(b-32);
	}
	public static void main (String [] args){
		Scanner in = new Scanner(System.in);
		String let;
		char leet;
		let = in.next();
		leet = mayuscula(let);
		
		System.out.println(leet);
	}
}
