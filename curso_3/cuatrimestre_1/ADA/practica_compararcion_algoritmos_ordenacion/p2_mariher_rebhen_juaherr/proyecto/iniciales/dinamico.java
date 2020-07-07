import java.io.*;
import java.util.Scanner;

public class dinamico {
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

        File out = new File("outD.csv");
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
                int[] salida = new int[3];
                //Se lee la primera línea del documento donde viene especificado el número de pruebas que a a haber.
                int casos = read.nextInt();
                for (int i = 0; i < casos; i++) {
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
                    if (salida[0] <= 0) {
                        System.out.println("0");
                    } else {
                        System.out.println(salida[0] + " " + (salida[1] + 1) + " " + (salida[2] + 1));
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

    public static int[] dinamico(int[] lista, int[] salida) {

        int min = 0, minVal = lista[0];
        int minDif = 0, minDifVal = lista[0];
        int maxDif = 0, maxDifVal = lista[0];
        int valAct;
        NUM_ACCESO += 3;


        for (int i = 0; i < lista.length; i++) {

            valAct = lista[i];
            NUM_ACCESO++;

            if ((maxDifVal - minDifVal) < (valAct - minVal)) {
                maxDif = i;
                minDif = min;
                maxDifVal = valAct;
                minDifVal = minVal;
            }
            if (valAct < minVal) {
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
