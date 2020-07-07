import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author mariher
 * @author rebhern
 * @author juaherr
 */
public class generatorTestEstandar {
    /*
     * Genera una carpeta tests con ficheros de prueba para los algorítmos, los cuales son compatibles con los requisitos de estos, es decir
     * que el número de instantes de cada archivo sea constante para cada caso.
     */
    public static void main(String[] args) throws IOException {
            /*
            Utilizamos cinco intervalos distintos:
                Del 10 al 100 con salto 10
                Del 200 al 1.000 con salto 100
                Del 2.000 al 10.000 con salto 1.000
		Del 20.000 al 100.000 con salto 10.000
		Del 200.000 al 1.000.000 con salto 100.000
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
	     * Se itera cada tamaño de vector deseado.
	     */
            for (int tamarr = tamAct; tamarr <= tamMax; tamarr += tamIncr) {
                Random rand = new Random();
                FileWriter output = null;
                BufferedWriter file = null;
                try {
                    File f = new File("tests/test" + tamarr); //Se crea una carpeta con todos los ficheros de prueba llamados test<NumInstancias>
                    f.getParentFile().mkdirs(); //Se crea el directorio por si no existe
                    output = new FileWriter(f);
                    file = new BufferedWriter(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                file.write("40"); //Para cada fichero se crean 40 casos de prueba.
                file.newLine();
                for (int iter = 0; iter < 40; iter++) {
                    file.write(tamarr + " "); //Es guarda en la primera posición del caso el número de instantes
                    for (int j = 0; j < tamarr; j++) {
                        file.write(rand.nextInt(intervaloNums) + " "); //Se guardan todos los intantes.
                    }
                    file.newLine();
                    file.flush();
                }
            }
        }
    }
}
