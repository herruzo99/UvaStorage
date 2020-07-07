package primeraPracticaTds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInfo {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		String[][] lista;

		lista = leerPasswd();

		leerLast(lista);

		parsearLista(lista);

	}

	private static String[][] leerPasswd(){
		String[] nextLineSplited;
		String line;
		int tam=0;
		File archivoPasswd = new File("passwd");
		FileReader passwd;
		
		try {
			passwd = new FileReader(archivoPasswd);
			BufferedReader buffer = new BufferedReader(passwd);
			//Cuenta el número de filas del fichero
			while ((line = buffer.readLine())!=null) {
				  tam++;
				}

			String[][] lista= new String[(int) archivoPasswd.length()][4];

			
			for(int i=0; i < tam; i++) {
				//Filas del fichero /etc/psswd = NombreCuenta:passwd:UID:Grupo:NomUser:...
				//TODO arreglar
				/*nextLineSplited = buffer.readLine().split(" ");
				lista[i][0]= nextLineSplited[4];
				lista[i][1]= nextLineSplited[3];*/
			}
			
			return lista;


		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}	

	/*
	 * Método que ejecuta el comando last y obtiene la última conexión y si cada
	 * usuario está conectado.
	 */
	private static void leerLast(String[][] lista) {

		for (int i = 0; i < lista.length; i++) {
			String line;
			String[] nextLineSplited;
			Process last;
			try {
				last = Runtime.getRuntime().exec("last" + lista[i][0]);
				BufferedReader br = new BufferedReader(new InputStreamReader(last.getInputStream()));

				line = br.readLine();
				nextLineSplited = br.readLine().split(" ");
				// Si no se encuentra ninguna hora de inicio se empieza por una línea en blanco.
				if (line.equals("\n")) {

					// Salida de last: wtmp empieza Sat Jun 22 18:10:15 2019
					// Si separamos por espacios la hora es la sexta.
					lista[i][2] = "N";
					lista[i][3] = nextLineSplited[5];

				} else {
					// TODO
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void parsearLista(String[][] lista) {
		// TODO Auto-generated method stub

	}

}
