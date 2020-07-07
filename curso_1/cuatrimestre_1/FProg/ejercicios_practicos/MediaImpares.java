import java.util.Scanner;
public class MediaImpares{
	public static float MediaImpares (int n1, int n2, int n3, int n4, int n5){
		int a,b,c,d,impares,e;
		a=b=c=d=e=impares=0;
		if(n1%2!=0){
			a=n1;
			impares++;
		}
                if(n2%2!=0){
                        b=n2;
			impares++;
                }
                if(n3%2!=0){
                        c=n3;
			impares++;
                }
                if(n4%2!=0){
                        d=n4;
			impares++;
                }
                if(n5%2!=0){
                        e=n5;
			impares++;
                }
		if(impares==0){
			impares++;
		}
		return(a+b+c+d+e)/impares;

	}	
	public static void main(String[] args){
		Scanner in = new Scanner (System.in);
		int a,b,c,d,e;
		a=in.nextInt();
		b=in.nextInt();
		c=in.nextInt();
		d=in.nextInt();
		e=in.nextInt();
		System.out.println("La media de los impares es " + MediaImpares(a,b,c,d,e));
	}
}
