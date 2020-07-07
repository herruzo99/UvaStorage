package distribuidos.mapqueue;

public class MapQueue {
	public static void main(String [] args) {
		MultiMap<String,String> chm = new MultiMap<String,String>();
		chm.push("hola", "me llamo juan");
		chm.push("hola", "me llamo pedro");
		chm.push("adios", "me llamo alicia");

		System.out.println("hola -> "+chm.pop("hola"));
		System.out.println("hola -> "+chm.pop("hola"));
		System.out.println("adios -> "+chm.pop("adios"));
		System.out.println("adios -> "+chm.pop("adios"));
		System.out.println("otro -> "+chm.pop("otro"));

	}
}