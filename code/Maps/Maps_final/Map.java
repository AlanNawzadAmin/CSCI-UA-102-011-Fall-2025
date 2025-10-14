package Maps_final;
import LinkedList_final.PositionList;

public interface Map<K, V> {
	int size();
	boolean isEmpty();
	V get(K key);
	V put(K key, V value);
	V remove(K key);
	
	PositionList<K> keySet();
	PositionList<V> values();
	PositionList<Entry<K,V>> entrySet();
}