import java.io.*;
import java.util.*;
/**
 * @author mariher
 * @author rebhern
 * @author juaherr
 */
public class recursividad {
    public static void main(String[] args) {
        Scanner read;
        try {
            read = new Scanner(new File("entrada.txt"));
            //Se lee la primera línea del documento donde viene especificado el número de pruebas que a a haber.
            int casos = read.nextInt();
            for (int i = 0; i < casos; i++) {
                int instantes = read.nextInt();
                int[] listaInstantes = new int[instantes];
                for (int j = 0; j < instantes; j++) {
                    listaInstantes[j] = read.nextInt();
                }

                int[] resultado = calculoInversion(0, listaInstantes.length - 1, listaInstantes);

                //Si existe un instante de compra y uno de venta lo imprime, si no imprime 0.
                int diferencia = listaInstantes[resultado[1]] - listaInstantes[resultado[0]];
                if (diferencia <= 0) {
                    System.out.println("0");
                } else {
                    System.out.println(diferencia + " " + (resultado[0] + 1) + " " + (resultado[1] + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * Calculo recursivo que devuelve el instante de compra y de venta más óptimos.
     * El array interno que se usa se basa de: [punto de compra actual, punto de venta actual, minimo absoluto, maximo absoluto]
     */
    private static int[] calculoInversion(int ini, int fin, int[] instantes) {
        if (ini == fin) {
            return new int[]{ini, fin, ini, fin};
        } else {

            int[] recu1 = calculoInversion(ini, ini + ((fin - ini) / 2), instantes);
            int[] recu2 = calculoInversion(ini + ((fin - ini) / 2) + 1, fin, instantes);
            int maxRecu1 = 0, maxRecu2 = 0, maxRecuMix;

            /*
             * En cada segmento se calcula si es mejor quedarse con valores de los subsegmentos izquierdo o derecho o quedarse con la mezcla entre el izquierdo y el derecho.
             */
            int compra1 = instantes[recu1[0]];
            int compra2 = instantes[recu2[0]];
            int venta1 = instantes[recu1[1]];
            int venta2 = instantes[recu2[1]];
            int min1 = instantes[recu1[2]];
            int min2 = instantes[recu2[2]];
            int max1 = instantes[recu1[3]];
            int max2 = instantes[recu2[3]];


            maxRecu1 = venta1 - compra1; // Diferencia de la compra y venta óptima del vector izquierdo
            maxRecu2 = venta2 - compra2; // Diferencia de la compra y venta óptima del vector derecho
            maxRecuMix = max2 - min1; // Diferencia del mínimo absoluto del vector izquierdo y el máximo absoluto del vector derecho

            int max = Math.max(Math.max(maxRecu1, maxRecu2), maxRecuMix); //Nos quedamos con la diferencia más grande de las tres anteriores

            if (max <= 0) { // Si ninguna de las diferencias es util para la compra/venta
                recu1[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu1[3] = max1 > max2 ? recu1[3] : recu2[3];
                return recu1;

            } else if (max == venta1 - compra1) { // Si la mejor diferencia es la del vector izquierdo
                recu1[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu1[3] = max1 > max2 ? recu1[3] : recu2[3];
                return recu1;

            } else if (max == venta2 - compra2) { // Si la mejor diferencia es la del vector derecho
                recu2[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu2[3] = max1 > max2 ? recu1[3] : recu2[3];
                return recu2;

            } else { // Si la mejor diferencia es la mezcla entre el izquierdo y el derecho
                recu1[0] = recu1[2];
                recu1[1] = recu2[3];
                recu1[2] = min1 < min2 ? recu1[2] : recu2[2];
                recu1[3] = max1 > max2 ? recu1[3] : recu2[3];
                return recu1;
            }
        }
    }
}
