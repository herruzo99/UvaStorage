import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class generatorTestEstandar {
    public static void main(String[] args) throws IOException { // args: nombre fich / num tests / num nums por test / tam nums
        if (args.length < 1) {
            throw new IllegalArgumentException("número insuficiente de argumentos \n args: nombre fich");
        }

            /*
            Utilizamos tres intervalos distintos:
                Del 10 al 100 con salto 10
                Del 100 al 1.000 con salto 100
                Del 1.000 al 10.000 con salto 1.000
             */
        for (int bucle = 0; bucle < 5; bucle++) {
            int tamAct = 0;
            int tamMax = 0;
            int tamIncr = 0;
            switch (bucle) {
                case 0:
                    tamAct = 10;
                    tamMax = 100;
                    tamIncr = 10;
                    break;
                case 1:
                    tamAct = 200;
                    tamMax = 1000;
                    tamIncr = 100;
                    break;
                case 2:
                    tamAct = 2000;
                    tamMax = 10000;
                    tamIncr = 1000;
                    break;
                case 3:
                    tamAct = 20000;
                    tamMax = 100000;
                    tamIncr = 10000;
                    break;
                case 4:
                    tamAct = 200000;
                    tamMax = 1000000;
                    tamIncr = 100000;
                    break;

            }

            int intervaloNums = tamMax * 10;
	    	    /*
	    	    Se itera cada tamaño de vector deseado.
	    	     */
            for (int tamarr = tamAct; tamarr <= tamMax; tamarr += tamIncr) {
                Random rand = new Random();
                FileWriter output = null;
                BufferedWriter file = null;
                try {
                    File f = new File("tests/"+ args[0] + tamarr);
                    f.getParentFile().mkdirs();
                    output = new FileWriter(f);
                    file = new BufferedWriter(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                file.write("40");
                file.newLine();

                for (int iter = 0; iter < 40; iter++) {


                    file.write(tamarr + " ");

                    for (int j = 0; j < tamarr; j++) {
                        file.write(rand.nextInt(intervaloNums) + " ");
                    }
                    file.newLine();


                    file.flush();


                }

            }
        }

    }
}
