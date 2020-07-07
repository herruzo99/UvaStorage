import java.util.*;
import java.lang.*; 
public class PolACart{
	public static double real(double  mod,double arg){
		return mod*Math.cos(arg);
	}
        public static double img(double mod,double arg){
                return mod*Math.sin(arg);
        }
	public static void main(String [] args){
		Scanner in = new Scanner(System.in);
		double mod = (in.nextDouble());
		double arg =(360/Math.PI)*in.nextDouble();
		mod = real(mod,arg);
		arg=img(mod,arg);
		System.out.println("Tu num: "+mod + " + " + arg + "i");
	}

}
