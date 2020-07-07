package practica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Hernando Brecht, Rebeca
 * @author Herruzo Herrero, Juan
 */

public class GeneraFicheros {
	
	static byte[] fich; // Datos del subfichero (var. global)
	static int[] indURL; // Indices de las URLs en "fich"
	
	public static void main(String[] args) {
		int bytes = 800000; //Tamaño de cada subarchivo en bytes.
		try {
		System.out.print("Fichero con las urls: ");
		generaFichero();
		}
		catch( FileNotFoundException e) {
			System.out.println("El fichero no existe.");
			System.exit(1);
		}
		catch (IOException e){
			System.out.println("Error al abrir el fichero de las urls.");
			System.exit(1);
		}
		
		creaIndices();
		borrarFicheros();

		String[] fich_string = binarioTOstring(); //String donde estan guardadas todas las urls.
		
		quickSort(fich_string, 0, fich_string.length-1);
		
		try {
		dividirFichero(bytes, fich_string);
		}
		catch(IOException e){
			System.out.println("Error al editar los ficheros.");
			System.exit(1);
		}
	}
	
	//Borra todos los subficheros antes de crear los nuevos.
	public static void borrarFicheros() {
		int contador = 0;
		
		File file = new File(contador +".txt");
		while(file.exists()){
			file.delete();
			contador++;
			file = new File(contador +".txt");
		}
	}
	
	//Divide fich_string en subficheros de "bytes" bytes.
	//El nombre de los ficheros creados es 0.txt, 1.txt ...
	// Tambien guarda la primera y ultima url de cada subfichero en un fichero principal llamado 000.
	public static void dividirFichero(int bytes, String[] fich_string) throws IOException{
		
		//Iniciación del fichero principal
		FileWriter maestro = null;
		PrintWriter maestro_writer = null;
		maestro = new FileWriter("000.txt");
		maestro_writer = new PrintWriter(maestro);
		
	

		int contador = 0;//Cuenta la url por la que se llega del fich_string.
		int contador_fichero= 2; //Cuenta el fichero por el que se llega creando.
		
		//Itera mientras queden urls en fich_string.
		while(contador < fich_string.length) {
			int byte_temp = 0;
			
			//Iniciacion de un subfichero.
			FileWriter subfich = null;
			PrintWriter writer_subfich = null;
			
			subfich = new FileWriter(contador_fichero +".txt");
			writer_subfich = new PrintWriter(subfich);
			
		
			maestro_writer.println(fich_string[contador]);	//Se guarda en el 000 la url inicial.

			
			//Itera mientras el subfichero sea menor que "bytes" bytes y en fich_string queden urls.
			while (byte_temp < bytes && contador < fich_string.length){
				//Como cada caracter en ASCII es un byte, el numero de caracteres de una url = numero de bytes de ella.
				byte_temp += fich_string[contador].length() + 1; // Se suma una más por el final de límnea.
				if (byte_temp < bytes) {
					writer_subfich.println(fich_string[contador]); //Guarda la url en el subfichero.
					contador++;
				}
				
			}
			writer_subfich.flush();
			maestro_writer.println(fich_string[contador-1]); // Se guarda en el 000 la ultima url del subfichero. //TODO cambiar a solo la primera
			contador_fichero++;
		}
		maestro_writer.flush();

	}

	//Pide el nombre de un archivo, y pasa el nombre a leeFichero.
	public static void generaFichero() throws IOException {
		Scanner in = new Scanner(System.in);
		String nombre_archivo = in.nextLine();
		in.close();
		leeFichero(nombre_archivo);
	}
	
		
	//Crea un byte[] en "fich" leyendo el fichero con el nombre dado.
	public static void leeFichero(String nomfich) throws IOException{
		File f = new File(nomfich);
		int tam = (int) f.length(); // Tamaño en bytes.
		fich = null;
		fich = new byte[tam];
		FileInputStream fis = new FileInputStream(f);
		fis.read(fich); // Lectura de todo el fichero.
		
	}
	//Genera un array de indicies que indican donde estan los saltos de linea de "fich".
	// para asi distinguir urls.
	//Debe correrse después de leeFichero.
	//Guarda el valor en indURL.
	 public static void creaIndices() {
	   // 1. Contar el numero de URLs.
	   int n = 0;
	   for(int i = 0; i < fich.length; i++) {
	       if(fich[i] == 10) { n++; }
	   }
	   // 2. Almacenar posición de separadores.
	   indURL = null;
	   indURL = new int[n];
	   int k = 0;
	   for(int i = 0; i < fich.length; i++) {
	       if(fich[i] == 10) { indURL[k++] = i; }
	   }
	 }
	 
	 //Devuelve la i-esima url de "fich" en modo String.
	 public static String accesoURL(int i) {
	     int a = i == 0 ? 0 : indURL[i - 1] + 1;
	     int b = indURL[i] - 1;

	     return new String(fich, a, b-a+1, StandardCharsets.US_ASCII);
	 }
	 
	 //Devuelve en modo String[] todo el array "fich".
	 public static String[] binarioTOstring() {
		 String[] temp = new String[indURL.length];
		 for (int i = 0; i < indURL.length; i ++) {
			 temp[i] = accesoURL(i);
		 }
		 return temp;
	 }
	 
	 //Algoritmo de ordenacion rápida.
	 //Usamos de pivote el pimer elemento del array.
	 //Divide el array dsel siguiente modo:	[menores que el pivote | pivote| mayores que el pivote].
	 //Los menores y mayores que el pivote se ordenan recursivamente por este mismo algoritmo.
	 
	 public static void quickSort(String[] fich_string, int ini, int fin){
		 String pivote = fich_string[ini];
		 int izq = ini;
		 int der = fin;
		 String cambio;
		 while(izq < der) {
			 //Selecciona el elemento mayor que el pivote más a la izquierda del array mientres queden elemenros mayores que el pivote y no sobrepase el contador derecha.
			 while(fich_string[izq].compareTo(pivote) <= 0 && izq < der) {
				 izq ++;
			 }
			 //Selecciona el elemnteo menor que el pivote más a la derecha.
			 while(fich_string[der].compareTo(pivote) > 0 && der > 0) {
				 der --;
			 }
			 //Si ha encontrado un elemento mayor y otro menor los intercambia.
			 if (izq < der) {
				 cambio = fich_string[izq];
				 fich_string[izq]=fich_string[der];
				 fich_string[der]= cambio;
			 }
		 }		 
		//Si no encuentra ninguno elemento mayor sustituye el elemento menor por el pivote.
		 fich_string[ini]=fich_string[der];
		 fich_string[der]=pivote;
		 
		 //Llamada recursiva de [menores que el pivote] [mayores que el pivote].
		 if (ini < der - 1) {
			
			 quickSort(fich_string, ini, der-1);
		 }
		 if (der+1 < fin) {
			
			 quickSort(fich_string, der + 1, fin);
		 }
	 }
	 
}
