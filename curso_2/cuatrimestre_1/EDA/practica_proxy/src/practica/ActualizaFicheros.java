package practica;

import java.io.*;
import java.util.*;

/**
 * @author Hernando Brecht, Rebeca
 * @author Herruzo Herrero, Juan
 */
public class ActualizaFicheros {

		public static void main(String[] args) {
			//Pide la nueva url.
			System.out.print("Nueva url: ");
			Scanner in = new Scanner(System.in);
			String url = in.nextLine();
			in.close();
			//obtiene el número de subficheros usando el fichero 000.
			int numeroFicheros=0;
			try {
			numeroFicheros = leeFich("000.txt").length/2;
			}
			catch (IOException e){
				System.out.println("Error al editar el subfichero 000.");
				System.exit(1);
			}
			
			//Reajusta el tamaño de los ficheros y crea el 000 de nuevo.
			try {
			//busca a que subfichero corresponde la url y la añade.
			int indice = buscarSubfich(url);
			añadeURLOrdenada(url,indice);
			ajustaTamaño(indice,numeroFicheros);
			} catch (IOException e) {
				System.out.println("Error al editar un subfichero.");
				e.printStackTrace();
				System.exit(1);
			}
			File maestro = new File("000.txt");
			maestro.delete();
			try {
			crearmaestro();
			}catch (IOException e){
				System.out.println("Error al editar el subfichero 000.");
				System.exit(1);
			}
		}
		
		//Función que lee los ficheros y devuelve su contenido en un array de strings.
		public static String[] leeFich(String nombre) throws IOException {
			
			File ficheroPath = new File(nombre); 
			Scanner fich = null;
			//lee el fichero
			
			fich = new Scanner(ficheroPath);
			int tamaño = 0;
			
			//Bucle que cuenta el número de líneas que tiene el fichero.
			while(fich.hasNextLine()) {
				
				fich.nextLine();
				tamaño++;
			}
			fich.close();
			//Guarda las URLs en un array de strings.
			fich = new Scanner(ficheroPath);
			
			String[] string_fichero = new String[tamaño];
			int i =0;
			while(fich.hasNextLine()) {
				string_fichero[i]=fich.nextLine();
				i++;
			}

			fich.close();

			return string_fichero;
		}
		
		//Busca el subfichero al que pertenece la URL comprendida entre la primera URL del fichero al que pertenece y 
		//la del siguiente utilizando el fichero 000.
				
		public static int buscarSubfich(String url) throws IOException {
			int indice = -1;
			int i =2;
			String[] maestro = leeFich("000.txt");
		
			while (i < maestro.length) {
				if (url.compareTo(maestro[i]) < 0) {
					indice = i/2 -1;
					i = maestro.length;
				}
				
				i+=2;
			}
			if(indice == -1) {
				indice = maestro.length/2 -1;
			}
			
			return indice;
		}
		
		//Añade una URL al fichero en la posición dada y devuelve el fichero actualizado.
		public static String[] addURL(String[] fich, int pos, String url) {
		    String[] result = new String[fich.length + 1];
		    
		    for(int i = 0; i < pos; i++) {
		        result[i] = fich[i];
		    }
		    
		    result[pos] = url;
		    
		    for(int i = pos + 1; i <= fich.length; i++) {
		        result[i] = fich[i - 1];
		    }

		    return result;
		}
		
		//Devuelve la posición de la URL en el fiechero dado ordenado alfabéticamnete.
		public static int posicion(String url, String[] fich) {

			int i = 0;

			while(i < fich.length && url.compareTo(fich[i]) > 0) {
				i++;
			}			
			return i;
		}
		
		//Dada una URL y el nombre de un archivo añade la URL ordenadamente al archivo.
		public static void añadeURLOrdenada(String url, int indice) throws IOException{

			String[] subFich = leeFich(indice + ".txt");
			int posicion = posicion(url, subFich);

			subFich = addURL(subFich, posicion, url);

			guardaFichero(indice,subFich);
		}
		
		//Ajusta el tamaño de un fichero dado y actualiza el número de ficheros utilizados si es necesario.
		public static void ajustaTamaño(int indice, int numero_ficheros) throws IOException {
			/*Para ajustar el tamaño aplica el siguiente algoritmo:
			 * 
			 *Quita las urls del fichero "indice" hasta que este ocupe menos que "bytes" bytes y los guarda en el array último.
			 *
			 *Despues dependiendo de si es el ultimo fichero o no:
			 *	Si no lo es añade esas urls al siguiente fichero y llama a esta funcion de nuevo sobre ese fichero.
			 *	Si lo es entonces crea un nuevo fichero donde guarda las últimas urls y llama esta funcion sobre ese nuevo fichero.
			 */
			int bytes = 800000; //Tamaño de cada subarchivo en bytes
			String[] fich = leeFich(indice+".txt");
			File fichero = new File(indice+".txt");
			int contador = 0;
			String[] ultimo = new String[13]; //13 es el peor caso de esta practica, ya que si las urls van de 200 a 18 lo peor es que tengas que desplazar 11'1.			
			while(fichero.length() > bytes){
				ultimo[contador]=fich[fich.length - 1];
				fich = Arrays.copyOf(fich, fich.length-1);
				
				contador++;
				guardaFichero(indice, fich);
				fichero = new File(indice+".txt");
			}



			if(indice < numero_ficheros - 1) {
				String[] fichSiguiente = leeFich(indice+1+".txt");
				for (int i = 0; i < contador; i++) {
					fichSiguiente = addURL(fichSiguiente, 0, ultimo[i]);
				}
				guardaFichero(indice+1, fichSiguiente);
				if (contador > 0) {
					ajustaTamaño(indice+1, numero_ficheros);
				}
			}else {

				if (contador > 0){
					String[] url = new String[contador];
	
					for (int i = 0; i < contador; i++) {
						url[i] = ultimo[i];
					}
	
					guardaFichero(indice+1, url);
					ajustaTamaño(indice+1, numero_ficheros);
					
				}
			}
		}
		
		//Crea el fichero 000.
		private static void crearmaestro() throws IOException {
			
			//Iniciación del fichero principal.
			FileWriter maestro = null;
			PrintWriter maestro_writer = null;
			maestro = new FileWriter("000.txt", true);
			maestro_writer = new PrintWriter(maestro);
			
			int contador = 0;
			
			File file = new File(contador +".txt");
			
			//Bucle que guarda en el fichero 000 la primera y la última URL de cada subfichero.
			while(file.exists()){
				String[] fich = leeFich(contador+ ".txt");
				maestro_writer.println(fich[0]);
				maestro_writer.println(fich[fich.length - 1]);
				contador++;
				file = new File(contador +".txt");
			}
			maestro_writer.flush();
		}
		
		//Guarda el array de string en indice.txt.
		private static void guardaFichero(int indice, String[] fich) throws IOException {
			FileWriter subfich = null;
			PrintWriter writer_subfich = null;
			subfich = new FileWriter(indice +".txt");
			writer_subfich = new PrintWriter(subfich);
			
			
			for (int i = 0; i < fich.length;i++) {
				writer_subfich.println(fich[i]);
			}
			writer_subfich.flush();

			
			
		}
}
