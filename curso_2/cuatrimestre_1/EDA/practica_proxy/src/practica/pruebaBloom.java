package practica;

public class pruebaBloom {
	public static void main(String[] args) {
		BloomFilter.inicializaBloom();
		BloomFilter.crearBloom("Hola que tal jajaja");
		BloomFilter.crearBloom("Rebeca");
		BloomFilter.crearBloom("Juan");
		System.out.println(BloomFilter.comprobarBloom("Hola que tal jajaja"));
		System.out.println(BloomFilter.comprobarBloom("Hola que tal jajaj"));
		System.out.println(BloomFilter.comprobarBloom("Rebeca"));
		System.out.println(BloomFilter.comprobarBloom("rslgivlrd"));
		System.out.println(BloomFilter.comprobarBloom("juan"));
		System.out.println(BloomFilter.comprobarBloom("Juan"));







	}
}
