
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

public class Quicksort {

    private static long NUM_ASIGNACIONES = 0;
    private static long NUM_COMPARACIONES = 0;

    private static final int DIVMILIS = 1000000;
    private static final int AMPLIACION = 10;
    private static final int REPETICIONES = 20;
    private static final int MIN_UMBRAL_ESTANDAR = 1;
    private static final int MAX_UMBRAL_ESTANDAR = 20;


    public static void main(String[] args) {

	/*
	Selección del umbral, por defecto es MIN_UMBRAL_ESTANDAR..MAX_UMBRAL_ESTANDAR
	Si se pasa un argumento solo se comprueba ese umbral
	Si se pasan dos se comprueba el intervalo
	 */
        int min_umbral = MIN_UMBRAL_ESTANDAR;
        int max_umbral = MAX_UMBRAL_ESTANDAR;
        if (args.length > 0) {
            min_umbral = Integer.parseInt(args[0]);
            if (args.length > 1) {
                if (args[1].compareTo(args[0]) < 0) {
                    throw new IllegalArgumentException("EL umbral mínimo tiene que se menor o igual al máximo");
                }
                max_umbral = Integer.parseInt(args[1]);
            } else {
                max_umbral = Integer.parseInt(args[0]);
            }
        }

        int[] array;
        double tiempoProm;
        double tiempoIter;
        int asigProm;
        int comProm;
        String path, data;

	    /*
	    Bucle que realiza cada umbral, se genera:
	        Una carpeta con nombre "umbral" + umbral actual
	        Dentro de ella un archivo con los promedios de ese umbral y una carpeta con todas las iteracciones hechas,
	        cada tamaño de vector en un archivo.
	     */
        for (int umbral = min_umbral; umbral <= max_umbral; umbral++) {

            /*
            Se borran antiguos datos que hubiesen.
             */
            File pathDel = new File("umbral" + umbral);
            borrarAntiguos(pathDel);

            path = "umbral" + umbral + "/promedio.csv";
            data = "TamArray,NumAsig,NumComp,Tiempo(ms)";
            guardarNewLineData(path, data);

            /*
            Utilizamos tres intervalos distintos:
                Del 3 al 103 con salto 5
                Del 10.000 al 100.000 con salto 10.000
                Del 100.000 al 1.000.000 con talto 100.000
             */
            for (int bucle = 0; bucle < 3; bucle++) {
                int tamAct = 0;
                int tamMax = 0;
                int tamIncr = 0;
                switch (bucle) {
                    case 0:
                        tamAct = 3;
                        tamMax = 103;
                        tamIncr = 5;
                        break;
                    case 1:
                        tamAct = 10000;
                        tamMax = 90000;
                        tamIncr = 10000;
                        break;
                    case 2:
                        tamAct = 100000;
                        tamMax = 1000000;
                        tamIncr = 100000;
                        break;


                }

	    	    /*
	    	    Se itera cada tamaño de vector deseado.
	    	     */
                for (int tamarr = tamAct; tamarr <= tamMax; tamarr += tamIncr) {

                    /*
                    Datos promedios de cada tamaño de vector.
                     */
                    asigProm = 0;
                    comProm = 0;
                    tiempoProm = 0;

                    path = "umbral" + umbral + "/individuales/tamArr" + tamarr + ".csv";
                    data = "TamArray,NumAsig,NumComp,Tiempo(ns)";
                    guardarNewLineData(path, data);

                    /*
                    Para evitar anomalias los cálculos se repiten REPETICIONES veces.
                     */
                    for (int i = 0; i < REPETICIONES; i++) {
                        NUM_ASIGNACIONES = 0;
                        NUM_COMPARACIONES = 0;

                        array = randArr(tamarr);
                        /*
                        Justo antes de empezar el algoritmo se cuenta el tiempo para evitar la mayor cantidad de
                        variables posibles
                         */
                        double TIEMPO = System.nanoTime();
                        if (umbral < tamarr) {
                            //umbral : tamaño a partir (si es igual a umbral también) se hace insercción.
                            quickSort(array, 0, array.length - 1, umbral);
                        }
                        if (umbral > 1) {
                            insercion(array);
                        }

                        tiempoIter = (System.nanoTime() - TIEMPO);
                        tiempoProm += tiempoIter;
                        asigProm += NUM_ASIGNACIONES;
                        comProm += NUM_COMPARACIONES;

                        path = "umbral" + umbral + "/individuales/tamArr" + tamarr + ".csv";
                        data = tamarr + ",\"" + NUM_ASIGNACIONES + "\",\"" + NUM_COMPARACIONES + "\",\"" + tiempoIter + "\"";
                        guardarNewLineData(path, data);

                    }

                    /*
                    Para calcular el promedio se divide la suma total de operaciones elementales entre REPETICIONES y
                    se parsea.
                     */
                    String asigFin = Double.toString(((double) asigProm) / REPETICIONES);
                    String comFin = Double.toString(((double) comProm) / REPETICIONES);
                    String tiempoFin = String.format("%.5f", (tiempoProm) / DIVMILIS / REPETICIONES);

                    asigFin = asigFin.replace(".", ",");
                    comFin = comFin.replace(".", ",");
                    tiempoFin = tiempoFin.replace(".", ",");

                    path = "umbral" + umbral + "/promedio.csv";
                    data = tamarr + ",\"" + asigFin + "\",\"" + comFin + "\",\"" + tiempoFin + "\"";
                    guardarNewLineData(path, data);
                }
            }
            System.out.println("umbral " + umbral + " acabado");
        }
    }

    /*
    Generador de vectores aleatorios de tamaño tamArr, genera números entre 0 y tamArr*AMPLIACION.
     */
    private static int[] randArr(int tamArr) {
        int[] array = new int[tamArr];

        Random rand = new Random();
        for (int j = 0; j < array.length; j++) {
            array[j] = rand.nextInt(tamArr * AMPLIACION);
        }
        return array;
    }


    /*
    Algoritmo de ordenacion rápida.
    Usamos de pivote la mediana entre tres elementos al azar del array.
    Divide el array dsel siguiente modo:	[menores que el pivote | mayores que el pivote].
    Los menores y mayores que el pivote se ordenan recursivamente por este mismo algoritmo mientras que los vectores
    sean de un tamaño mayor que el umbral.
    */
    private static void quickSort(int[] array, int ini, int fin, int umbral) {
        int pivote = seleccionPivote(array, ini, fin);
        int izq = ini;
        int der = fin;
        int cambio;
        while (izq <= der) {
            //Selecciona el elemento mayor que el pivote más a la izquierda del array mientres queden elemenros mayores que el pivote y no sobrepase el contador derecha.
            while (array[izq] < pivote) {
                NUM_COMPARACIONES++;
                izq++;
            }
            NUM_COMPARACIONES++;
            //Selecciona el elemnteo menor que el pivote más a la derecha.
            while (array[der] > pivote) {
                NUM_COMPARACIONES++;
                der--;
            }
            NUM_COMPARACIONES++;
            //Si ha encontrado un elemento mayor y otro menor los intercambia.
            if (izq <= der) {
                cambio = array[izq];
                NUM_ASIGNACIONES++;
                array[izq] = array[der];
                NUM_ASIGNACIONES++;
                array[der] = cambio;
                NUM_ASIGNACIONES++;
                izq++;
                der--;
            }
        }
        //Llamada recursiva de [menores que el pivote] [mayores que el pivote] respetando el umbral.
        if (ini < izq - 1) {
            if (izq - ini > umbral) {
                quickSort(array, ini, izq - 1, umbral);
            }
            if (izq < fin) {
                if (fin - izq + 1 > umbral) {
                    quickSort(array, izq, fin, umbral);
                }
            }
        }
    }
    /*
    Devuelve un elemento del array contenido entre las posiciones ini y fin.
    Para ello selecciona tres valores al hacar del vector dado entre ini y fin y calcula la mediana.
     */
    private static int seleccionPivote(int[] array, int ini, int fin) {
        Random rand = new Random();
        int[] candidatos = new int[3];

        for (int i = 0; i < candidatos.length; i++) {
            candidatos[i] = array[ini + rand.nextInt(fin - ini)];
            NUM_ASIGNACIONES++;
        }
        if (candidatos[0] > candidatos[1]) {
            if (candidatos[1] > candidatos[2]) {
                return candidatos[1];

            } else return Math.min(candidatos[0], candidatos[2]);

        } else {
            if (candidatos[1] < candidatos[2]) {
                return candidatos[1];

            } else return Math.max(candidatos[0], candidatos[2]);
        }
    }

    /*
    Algoritmo de insercción
    Ordena el array dado.
     */
    private static void insercion(int[] array) {
        int p, j;
        int aux;
        for (p = 0; p < array.length; p++) { // desde el segundo elemento hasta
            aux = array[p]; // el final, guardamos el elemento y
            NUM_ASIGNACIONES++;

            j = p - 1; // empezamos a comprobar con el anterior
            while ((j >= 0) && (aux < array[j])) { // mientras queden posiciones y el
                NUM_COMPARACIONES++;

                // valor de aux sea menor que los
                array[j + 1] = array[j];       // de la izquierda, se desplaza a
                NUM_ASIGNACIONES++;

                j--;                   // la derecha
            }
            array[j + 1] = aux; // colocamos aux en su sitio
            NUM_ASIGNACIONES++;

        }
    }

    /*
    Añade al fichero path la información data y un salto de línea.
    Si no existe el fichero o sus directorios, los crea.
     */
    private static void guardarNewLineData(String path, String data) {
        File file = new File(path);
        file.getParentFile().mkdirs();
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file, true));

            writer.write(data);
            writer.write("\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Borra el arbol de directorios desde el archivo dado.
     */
    private static void borrarAntiguos(File path) {
        File[] allContents = path.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                borrarAntiguos(file);
            }
        }
        path.delete();
    }
}

