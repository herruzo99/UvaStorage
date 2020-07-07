import java.io.*;
import java.util.Scanner;

public class recursividad {
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

        File out = new File("outR.csv");
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

                    NUM_ACCESO = 0;
                    NUM_COMPARACIONES = 0;
                    long tiempo = System.nanoTime();

                    int[] resultado = calculoInversion(0, listaInstantes.length - 1, listaInstantes);


                    tiempo = System.nanoTime() - tiempo;

                    comparaciones_media += NUM_COMPARACIONES;
                    acceso_media += NUM_ACCESO;
                    tiempo_medio += tiempo;

                    //Si existe un instante de compra y uno de venta lo imprime, si no imprime 0.
                    int diferencia = listaInstantes[resultado[1]] - listaInstantes[resultado[0]];
                    if (diferencia <= 0) {
                        System.out.println("0");
                    } else {
                        System.out.println(diferencia + " " + (resultado[0] + 1) + " " + (resultado[1] + 1));
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

    /*
    Calculo recursivo que devuelve el valor de compra y venta más óptimos.
    El array interno que se usa se basa de: [punto de compra actual, punto de venta actual, minimo absoluto, maximo absoluto]
     */
    private static int[] calculoInversion(int ini, int fin, int[] instantes) {
        if (ini == fin) {
            return new int[]{ini, fin, ini, fin};
        } else {

            int[] recu1 = calculoInversion(ini, ini + ((fin - ini) / 2), instantes);
            int[] recu2 = calculoInversion(ini + ((fin - ini) / 2) + 1, fin, instantes);
            int maxRecu1 = 0, maxRecu2 = 0, maxRecuMix;

            /*
            En cada segmento se calcula si es mejor quedarse con valores de los subsegmentos izquierdo o derecho o quedarse con la mezcla entre el izquierdo y el derecho.
             */
            int compra1 = instantes[recu1[0]];
            int compra2 = instantes[recu2[0]];
            int venta1 = instantes[recu1[1]];
            int venta2 = instantes[recu2[1]];
            int min1 = instantes[recu1[2]];
            int min2 = instantes[recu2[2]];
            int max1 = instantes[recu1[3]];
            int max2 = instantes[recu2[3]];
            NUM_ACCESO += 8;

            maxRecu1 = venta1 - compra1;
            maxRecu2 = venta2 - compra2;
            maxRecuMix = max2 - min1;
            int max = Math.max(Math.max(maxRecu1, maxRecu2), maxRecuMix);

            if (max <= 0) {
                recu1[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu1[3] = max1 > max2 ? recu1[3] : recu2[3];
                NUM_COMPARACIONES += 3;

                return recu1;

            } else if (max == venta1 - compra1) {
                recu1[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu1[3] = max1 > max2 ? recu1[3] : recu2[3];
                NUM_COMPARACIONES += 4;

                return recu1;

            } else if (max == venta2 - compra2) {
                recu2[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu2[3] = max1 > max2 ? recu1[3] : recu2[3];
                NUM_COMPARACIONES += 5;

                return recu2;

            } else {
                recu1[0] = recu1[2];
                recu1[1] = recu2[3];
                recu1[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu1[3] = max1 > max2 ? recu1[3] : recu2[3];
                NUM_COMPARACIONES += 5;

                return recu1;
            }
        }
    }
}
