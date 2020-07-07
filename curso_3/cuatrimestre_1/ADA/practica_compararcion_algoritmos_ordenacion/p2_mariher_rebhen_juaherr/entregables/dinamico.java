import java.io.*;
import java.util.*;
/**
 * @author mariher
 * @author rebhern
 * @author juaherr
 */
public class dinamico {
    public static void main(String[] args) {
        Scanner read;
        try {
            read = new Scanner(new File("entrada.txt"));
            int[] salida = new int[3];
            //Se lee la primera línea del documento donde viene especificado el número de pruebas que a a haber.
            int casos = read.nextInt();
            for (int i = 0; i < casos; i++) {
		// Se parsea el string con los datos de la iteración a un array con dichos datos.
                int instantes = read.nextInt();
                int[] listaInstantes = new int[instantes];
                for (int j = 0; j < instantes; j++) {
                    listaInstantes[j] = read.nextInt();
                }
                salida = dinamico(listaInstantes, salida);

                //Si existe un instante de compra y uno de venta lo imprime, si no imprime 0.
                if (salida[0] <= 0) {
                    System.out.println("0");
                } else {
                    System.out.println(salida[0] + " " + (salida[1] + 1) + " " + (salida[2] + 1));
                }
            }
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
        for (int i = 0; i < lista.length; i++) {
            valAct = lista[i];
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
        }
        salida[0] = (maxDifVal - minDifVal);
        salida[1] = minDif;
        salida[2] = maxDif;
        return salida;
    }

}
