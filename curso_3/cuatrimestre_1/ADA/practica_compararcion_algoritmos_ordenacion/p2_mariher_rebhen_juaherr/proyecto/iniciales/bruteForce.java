import java.io.*;
import java.util.Scanner;

public class bruteForce {
    private static long NUM_COMPARACIONES;
    private static long NUM_ACCESO;

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("número insuficiente de argumentos, hay que dar el nombre del archivo a probar");
        }

        File folder = new File(args[0]);

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("El argumento no es un directorio con las pruebas.");
        }

        File out = new File("outB.csv");
        FileWriter write = null;

        try {
            write = new FileWriter(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter fileOut = new BufferedWriter(write);
        try {
            fileOut.write("tam,comparaciones,accesos,tiempo\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (final File fileEntry : folder.listFiles()) {
            Scanner read;
            try {
                int instantes = 0;
                long comparaciones_media = 0;
                long acceso_media = 0;
                long tiempo_medio = 0;

                read = new Scanner(fileEntry);
                //Se lee la primera línea del documento donde viene especificado el número de pruebas que a a haber.
                int casos = read.nextInt();
                for (int i = 0; i < casos; i++) {
                    instantes = read.nextInt();
                    int[] listaInstantes = new int[instantes];
                    for (int j = 0; j < instantes; j++) {
                        listaInstantes[j] = read.nextInt();
                    }

                /*
                    Recorre el vector (i) y compara su valor con el resto de vector que queda (j) hasta encontrar un máximo.
                    Si el siguiente valor de i es menor al actual no hace esa iteración de j.
                 */

                    NUM_ACCESO = 0;
                    NUM_COMPARACIONES = 0;
                    long tiempo = System.nanoTime();

                    int minimo = Integer.MAX_VALUE;
                    int instanteCompra = -1, instanteVenta = -1, diff = 0;
                    for (int j = 0; j < instantes; j++) {
                        int compraActual = listaInstantes[j];
                        NUM_ACCESO++;
                        if (minimo > compraActual) {
                            minimo = compraActual;
                            for (int k = j + 1; k < instantes; k++) {
                                int localdiff = listaInstantes[k] - compraActual;
                                NUM_ACCESO++;
                                if (localdiff > diff) {
                                    diff = localdiff;
                                    instanteCompra = j;
                                    instanteVenta = k;
                                }
                                NUM_COMPARACIONES++;

                            }
                        }
                        NUM_COMPARACIONES++;

                    }

                    tiempo = System.nanoTime() - tiempo;

                    comparaciones_media += NUM_COMPARACIONES;
                    acceso_media += NUM_ACCESO;
                    tiempo_medio += tiempo;

                    //Si existe un instante de compra y uno de venta lo imprime, si no imprime 0.
                    if (instanteCompra != -1) {

                        System.out.println(diff + " " + (instanteCompra + 1) + " " + (instanteVenta + 1));
                    } else {
                        System.out.println(0);
                    }
                }
                comparaciones_media /= casos;
                acceso_media /= casos;
                tiempo_medio /= casos;
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
}
