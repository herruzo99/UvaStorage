import java.util.*;
public class Edad{
	public static int edad(int año,int mes,int dia,int añoh,int mesh,int diah){
		if(mes < mesh){
			return añoh-año;
		}
		else if(dia < diah && mes==mesh){
			return añoh-año;
		}
		else{
			return añoh-año-1;
		}
	}
	public static void main ( String [] args){
		Scanner in = new Scanner(System.in);
		int año,mes,dia,añoh,mesh,diah;
		diah=in.nextInt();
                mesh=in.nextInt();
                añoh=in.nextInt();
                dia=in.nextInt();
                mes=in.nextInt();
                año=in.nextInt();
		System.out.println("Tienes "+edad(año,mes,dia,añoh,mesh,diah)+" años");

	}
}
