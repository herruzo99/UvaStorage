import java.util.Scanner;
public class MenorDeTres{
	public static int MenorDeTres(int n1, int n2, int n3){
		if(n1>n2){
			if(n2 > n3){
				return n3;
			}
			else{
				return n2;
			} 
		}
		else{
			if(n1<n3){
				return n1;
			}
			else{
				return n3;
			}
		}
	}
	public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		int a,b,c;
		a=in.nextInt();
		b=in.nextInt();
		c=in.nextInt();
		System.out.println("El menor es " + MenorDeTres(a,b,c));
	}
}
