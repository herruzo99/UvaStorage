package practica;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Hernando Brecht, Rebeca
 * @author Herruzo Herrero, Juan
 */
public class ProxySim {
	static byte[] subfich; // Datos del subfichero.
	static int[] indURL; // Indices de las URLs en "subfich".
	
	//Control uso memoria.
	public static int TAM_MAX = 102000;
	public static int TAM_ACT;
	public static int  NUM_URL;
	public static long memIni;
	public static Runtime rt;
	
	// Contador del numero de operaciones de comparacion entre URLs
    public static long numOper = 0;
	
	// Debe llamarse una unica vez al comienzo del programa.
    public static void initGesMem() {
        rt = Runtime.getRuntime();
        rt.gc();
        memIni = rt.totalMemory() - rt.freeMemory();
        // El array de bytes que contiene el subfichero se crea
        // una unica vez con tamano maximo, y estara parcialmente  
        // ocupado, su tamano real estara dado por TAM_ACT
        subfich = new byte[TAM_MAX];          
        TAM_ACT = 0;
    }    
    
 
	public static long usoMemoria() {
	  rt.gc();
	  return rt.totalMemory() - rt.freeMemory() - memIni;
	}
    
	//Crea un FileInputStream en "fich" leyendo el archivo con el nombre dado.
	//El argumento "maestro" es para diferenciar el fichero 000 del resto de ficheros.
	public static boolean leeSubfichero(String nomfich, boolean maestro) throws IOException {
        File fich = new File(nomfich);
        if(!fich.exists()) { return false; }
        FileInputStream fis = new FileInputStream(fich);
    	int tam = (int) fich.length(); // Tamaño en bytes
		subfich = null;
		subfich = new byte[tam];

		if(maestro){
			TAM_ACT = fis.read(subfich);  // Lectura del 000, su tamaño es distinto al de el resto de subficheros.

		}else {
			if (TAM_MAX < tam) {
			TAM_ACT = fis.read(subfich, 0, TAM_MAX);  // Lectura del fichero hasta TAM_MAX.
			}
			else {
				TAM_ACT = fis.read(subfich); // Lectura de todo el fichero.
			}
		}
    	rt.gc();
    	return true;
	}
	//Genera un array de indicies que indican donde estan los saltos de linea de "fich"
	// para asi distinguir urls.
	//Debe correrse después de leeFichero.
	//Guarda el valor en el array "indURL".
	 public static void creaIndices() {
	        // 1. Contar el numero de URLs
	        NUM_URL = 0;
	        for(int i = 0; i < TAM_ACT; i++) {
	            if(subfich[i] == 10) { NUM_URL++; }
	        }
	        // 2. Almacenar posición de separadores
	        if (indURL == null || indURL.length < NUM_URL) {
	            indURL = null;
	            indURL = new int[NUM_URL];
	        }
	        int k = 0;
	        for(int i = 0; i < TAM_ACT; i++) {
	            if(subfich[i] == 10) { indURL[k++] = i; }
	        }
	    }
		 
	//Devuelve la i-esima url del fichero dado en modo String
	static String accesoURL(int i, byte[] fich, int[] fichIndice) {
			int a = i == 0 ? 0 : fichIndice[i-1] + 1;
			int b = fichIndice[i] - 1;
			return new String(fich, a, b-a+1, StandardCharsets.US_ASCII);
	}
	
	
	//main del programa.    
	public static void main(String[] args) {
		initGesMem();            // Inicializar gestión de memoria
        int numURLProc = 0;      // Numero de URLs procesadas
        long numAccesos = 0;     // Numero total de accesos a subficheros
        long memMax = 0, memAct = 0; // Memoria usada     
		
		//Guarda el fichero 000 en formato bytes[] , "maestro", con su correspondeinte indice en int[],"maestroIndice".
		try {
			 leeSubfichero("000.txt", true);
			 
			}catch(IOException e) {
				
			}
			creaIndices();
			byte[] maestro = Arrays.copyOf(subfich, subfich.length);
			int[] maestroIndice = Arrays.copyOf(indURL,NUM_URL); 
			memAct = usoMemoria();
	        if (memAct > memMax) { memMax = memAct; }
	        
	    //Se pide el nombre del archivo que tiene las urls a comprobar
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print("Fichero de simulacion: ");
	    String nomFich = null;
	    try {
	    	nomFich = br.readLine();
	    }catch(IOException e){
	    	
	    }
		try (BufferedReader fich = new BufferedReader(new FileReader(nomFich), 400))
		{
		   while(fich.ready()) {
			     // Leemos las URLs del fichero de prueba de una en una
			     String url = fich.readLine();
			     numURLProc++;// Incremento del contador de URL procesadas
			     
			     /*Se comprueba en que archivo puede estar la url de la siguiente forma.
			      * Si igual a uno del 000 esa url ya es directamente peligrosa ahorrando una lectura de archivo.
			      * 
			      * Por cada par del 000 primera-ultima de cada subfichero.
			      * 	Si es mayor que la última nos aseguramos de que la url, si es peligrosa, tendrá que estar a partir del siguiente subfichero.
			      * 	Si es menor que la última y mayor que la primera puede estar en ese archivo y se lee.
			      * 	Si es menor que la última y que la primera significa que no pertenece al rango de ningún subfichero por lo que nos ahorramos una lectura.
			      */
			     int i = 0;
			     int numArchivo = -1; 
			     while(i < maestroIndice.length) {

			    	 if (compararCon(url, accesoURL(i,maestro,maestroIndice)) == 0 || compararCon(url, accesoURL(i+1,maestro,maestroIndice))== 0) {
			    		 System.out.println(url);
			    		 i = maestroIndice.length;
			    	 }
			    	 else if (compararCon(url, accesoURL(i+1,maestro,maestroIndice)) < 0) {
			    		 if (compararCon(url, accesoURL(i,maestro,maestroIndice)) > 0) {
				    		 numArchivo = i/2 + 2;
				    		 i = maestroIndice.length;
				    	 } 
			    		 else {
				    		 i = maestroIndice.length; 
			    		 }
			    	 }
			    	 i+=2;
			     }
			   //Si la variable no cambia después del algoritmo, significa que no hay que leer ningún fichero.
			     if (numArchivo != -1) {
			        leeSubfichero(numArchivo+".txt", false);
			        creaIndices();
			        numAccesos++;
			        memAct = usoMemoria();
			        if (memAct > memMax) { memMax = memAct; }
			        
			        //Se comprueba si la url está en el subfichero o no.
			        i = 0;
			        while (i < NUM_URL) {
			        	if (compararCon(url, accesoURL(i,subfich,indURL))== 0) {
				    		 System.out.println(url);
				    		 i= NUM_URL;
			        	}else {
			        		i++;
			        	}
			        }
			     }
			}
		}
		catch(Exception e){
			System.out.println("Fallo al leer el fichero");
			System.out.println(e);
			e.printStackTrace();
			System.exit(1);
		}
		
		//Estadísticas:
        System.out.printf("Accesos a subficheros: %d%n", numAccesos);
        System.out.printf("Accesos por URL: %f%n", (float) numAccesos /(float) numURLProc);
        System.out.printf("Comparaciones por URL: %,.1f%n", (float) numOper / (float) numURLProc);
        System.out.printf("Maximo uso de memoria: %,d bytes%n", memMax);
	}
	
	//Para poder comprobar el número de veces que comparamos se utiliza esta función.
	static int compararCon(String url, String conUrl) {
		numOper++;
		return url.compareTo(conUrl);
	}
	

}

