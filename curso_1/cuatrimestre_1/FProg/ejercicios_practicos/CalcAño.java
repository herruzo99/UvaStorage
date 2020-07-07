import java.util.*;
public class CalcAño{
	public static int dias(int a,int m){
		if(m==2){
			if(a%400==0||(a%4==0 && a%100!=0)){
				return 29;
			}
			else{
				return 28;
			}
		}	
		else if(m==1 || m==3 || m==5 || m==7 || m==8 || m== 10 || m==12){
		return 31;
		}
		else{
		return 30;
		}
	
	}
	public static void main (String [] args){
		Scanner in = new Scanner(System.in);
		int año,mes,dias;
		mes = in.nextInt();
		año= in.nextInt();
		dias =dias(año,mes);
		System.out.println(dias);
	}	
}
