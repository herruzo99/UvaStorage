import java.util.Scanner;
public class NumeroIguales{
	public static int nIguales(int n1, int n2, int n3){
		if(n1==n2){
			if(n2==n3){
			return 2;
			}
			else{
				return 1;
			}
		}
		else if(n1==n3){
			return 3;
		}
		else if(n2==n3){
			return 3;
		}
		else{
			return 0;
		}
	
	}
	public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int a = in.nextInt();
	int b = in.nextInt();
	int c = in.nextInt();
	int nfinal = nIguales(a,b,c);
	if(nfinal==0){
		System.out.println("No hay números iguales");
	}
	if(nfinal == 1){
		System.out.println("Hay dos números iguales a "+a);
	}
        if(nfinal == 3){
                System.out.println("Hay dos números iguales a "+c);
        }
	if(nfinal == 2){
		System.out.println("Hay tres números iguales a "+b);
	}

	}
}
