import java.io.*;
import java.util.*;
/**
 * @author mariher
 * @author rebhern
 * @author juaherr
 */
public class bruteForce {
    public static void main(String[] args) {
        Scanner read;
        try {
            read = new Scanner(new File("entrada.txt"));
            //Se lee la primera línea del documento donde viene especificado el número de pruebas que a a haber.
            int casos = read.nextInt();
            for (int i = 0; i < casos; i++) {
		// Se parsea el string con los datos de la iteración a un array con dichos datos.
                int instantes = read.nextInt();
                int[] listaInstantes = new int[instantes];
                for (int j = 0; j < instantes; j++) {
                    listaInstantes[j] = read.nextInt();
                }
                /*
                    Recorre el vector (i) y compara su valor con el resto de vector que queda (j) hasta encontrar un máximo.
                    Si el siguiente valor de i es menor al actual no hace esa iteración de j.
                 */
                int minimo = Integer.MAX_VALUE;
                int instanteCompra = -1, instanteVenta = -1, diff = 0;
                for (int j = 0; j < instantes; j++) {
                    int compraActual = listaInstantes[j];
                    if (minimo > compraActual) {
                        minimo = compraActual;
                        for (int k = j + 1; k < instantes; k++) {
                            int localdiff = listaInstantes[k] - compraActual;
                            if (localdiff > diff) {
                                diff = localdiff;
                                instanteCompra = j;
                                instanteVenta = k;
                            }
                        }
                    }
                }
                //Si existe un instante de compra y uno de venta lo imprime, si no imprime 0.
                if (instanteCompra != -1) {

                    System.out.println(diff + " " + (instanteCompra + 1) + " " + (instanteVenta + 1));
                } else {
                    System.out.println(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

