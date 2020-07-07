package distribuidos.mapqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
* Multimap concurrente para la práctica de Distribuidos de 2019.
*/
public class MultiMap<K, T> {
  private final ConcurrentMap<K, ConcurrentLinkedQueue<T>> map =new ConcurrentHashMap<K, ConcurrentLinkedQueue<T>>();

  public void push(K clave, T valor) {
    java.util.Queue<T> cola = map.get(clave);
   
    if (null == cola) {
    	
       /* putIfAbsent es atómica pero requiere "nueva", y es costoso */
       ConcurrentLinkedQueue<T> nueva = new ConcurrentLinkedQueue<T>();
       ConcurrentLinkedQueue<T> previa = map.putIfAbsent(clave, nueva);
       cola = (null == previa) ? nueva : previa ;
    }
    cola.add(valor);
  }

  public T pop(K clave) {
    ConcurrentLinkedQueue<T> cola = map.get(clave);
    return (null != cola) ? cola.poll() : null ;
  }
  //Comprobamos si la cola existe
  public boolean colaExist(K clave){
	  boolean existe=true;
	  java.util.Queue<T> cola = map.get(clave);
	  if(cola == null){
		  existe = false;
	  }
	 return existe;
  }
}