import java.util.*;
import java.lang.*; 
public class CartAPol{
	public static double mod(double  real,double img){
		return Math.sqrt(real*real+img*img);
	}
        public static double arg(double real,double img){
                return Math.atan(img/real);
        }
	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		double real = (in.nextDouble());
		double img =in.nextDouble();
	
		System.out.println("Tu num: "+mod(real,img) + "  "+(360/Math.PI)* arg(real,img) );
	}

}
