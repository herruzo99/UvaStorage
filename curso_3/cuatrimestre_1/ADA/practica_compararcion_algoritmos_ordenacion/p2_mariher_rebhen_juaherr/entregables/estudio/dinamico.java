import java.io.*;
import java.util.Scanner;
/**
 * @author mariher
 * @author rebhern
 * @author juaherr
 */
public class dinamico {
    private static long NUM_COMPARACIONES;
    private static long NUM_ACCESO;

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("número insuficiente de argumentos, hay que dar el nombre del archivo a probar");
        }
	
	//Se comprueba que el argumento es el nombre de una carpeta
        File folder = new File(args[0]); 
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("El argumento no es un directorio con las pruebas.");
        }

	//El output del análisis será escrito aquí
        File out = new File("outD.csv");
        FileWriter write = null;
        try {
            write = new FileWriter(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter fileOut = new BufferedWriter(write);
        try {
            fileOut.write("tam,comparaciones,accesos,tiempo\n"); //Se crean las columnas que va a tener el fichero csv
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
 * La carpeta dada tiene exclusivamente ficheros de prueba para el algorítmo, cada fichero se requiere que cada caso tenga el mismo número de instantes, para así poder dar un resultado coherente.
 *
 */        for (final File fileEntry : folder.listFiles()) {
            Scanner read;
            try {
                int instantes = 0;
                long comparaciones_media = 0;
                long acceso_media = 0;
                long tiempo_medio = 0;

                read = new Scanner(fileEntry);
                int[] salida = new int[3];
                //Se lee la primera línea del documento donde viene especificado el número de pruebas que a a haber.
                int casos = read.nextInt();
                for (int i = 0; i < casos; i++) {
		    // Se parsea el string con los datos de la iteración a un array con dichos datos.
                    instantes = read.nextInt();
                    int[] listaInstantes = new int[instantes];
                    for (int j = 0; j < instantes; j++) {
                        listaInstantes[j] = read.nextInt();
                    }
                    NUM_ACCESO = 0;
                    NUM_COMPARACIONES = 0;
                    long tiempo = System.nanoTime();
                    
		    salida = dinamico(listaInstantes, salida);

                    tiempo = System.nanoTime() - tiempo;
                    comparaciones_media += NUM_COMPARACIONES;
                    acceso_media += NUM_ACCESO;
                    tiempo_medio += tiempo;

                    //Si existe un instante de compra y uno de venta lo imprime, si no imprime 0.
                    if (salida[0] <= 0) {
                        System.out.println("0");
                    } else {
                        System.out.println(salida[0] + " " + (salida[1] + 1) + " " + (salida[2] + 1));
                    }
                }
		//Se calcula la media de comparaciones, accesos y tiempos para el fichero que ha sido leido.
                comparaciones_media /= casos;
                acceso_media /= casos;
                tiempo_medio /= casos;

		//Es usa la variable instantes ya que se requiere que todos los casos del fichero tengan el mismo número de instantes.
                fileOut.write(instantes + "," + comparaciones_media + "," + acceso_media + "," + tiempo_medio + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Calculo dinámico que devuelve la diferencia, el instante de compra y de venta más óptimos.
     * Se recorre solo una vez el vector guardando en cada iteración el mínimo absoluto y el punto de compra y de venta más optimos para el vector[0..i]
     */
    public static int[] dinamico(int[] lista, int[] salida) {

        int min = 0, minVal = lista[0];
        int minDif = 0, minDifVal = lista[0];
        int maxDif = 0, maxDifVal = lista[0];
        int valAct;
        NUM_ACCESO += 3;

        for (int i = 0; i < lista.length; i++) {
            valAct = lista[i];
            NUM_ACCESO++;

            if ((maxDifVal - minDifVal) < (valAct - minVal)) { // Si la diferencia contra el instante actual es mejor, se actualiza el instante de compra y de venta
                maxDif = i;
                minDif = min;
                maxDifVal = valAct;
                minDifVal = minVal;
            }
            if (valAct < minVal) { // Siempre nos quedamos con el mínimo absoluto del vector
                min = i;
                minVal = valAct;
            }
            NUM_COMPARACIONES += 2;
        }
        salida[0] = (maxDifVal - minDifVal);
        salida[1] = minDif;
        salida[2] = maxDif;
        return salida;
    }
}
