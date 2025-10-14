package Maps_final;
import LinkedList_final.Position;
import LinkedList_final.PositionList;
import LinkedList_final.DoublyLinkedList;

public class HashMapSC<K, V> implements Map<K, V>{
	// No collision resolution!
	public static int MAX_SIZE = 1000;
	
	UnsortedMap<K, V>[] list;
	int size;
	public HashMapSC() {
		list = (UnsortedMap<K, V>[])(new Object[MAX_SIZE]);
		for (int i=0; i<MAX_SIZE; i++) {
			list[i] = new UnsortedMap<K, V>();
		}
	}
	
	public V get(K key) {
		int hash = key.hashCode() % MAX_SIZE;
		return list[hash].get(key);
	}
	
	public V remove(K key) {
		int hash = key.hashCode() % MAX_SIZE;
		V value = list[hash].remove(key);
		if (value != null) {
			size--;
		}
		return value;
	}
	
	public V put(K key, V value) {
		int hash = key.hashCode() % MAX_SIZE;
		V value_old = list[hash].put(key, value);
		if (value_old == null) {
			size++;
		}
		return value_old;
	}

	public int size() {return size;};
	public boolean isEmpty() { return size() == 0;};
	
	public PositionList<K> keySet() {
		PositionList<K> keyList = new DoublyLinkedList<K>();
		for (int i=0; i<MAX_SIZE; i++) {
			PositionList<K> map_keys = (DoublyLinkedList<K>)(list[i].keySet());
			Position<K> current_pos = map_keys.first();
			for (int j=0; j<map_keys.size(); j++) {
				keyList.addLast(current_pos.getElement());
				current_pos = map_keys.after(current_pos);
			}
		}
		return keyList;
	}
	
	public PositionList<V> values() {
		PositionList<V> valList = new DoublyLinkedList<V>();
		for (int i=0; i<MAX_SIZE; i++) {
			PositionList<V> map_vals = (DoublyLinkedList<V>)(list[i].values());
			Position<V> current_pos = map_vals.first();
			for (int j=0; j<map_vals.size(); j++) {
				valList.addLast(current_pos.getElement());
				current_pos = map_vals.after(current_pos);
			}
		}
		return valList;
	}
	
	public PositionList<Entry<K, V>> entrySet() {
		PositionList<Entry<K, V>> entryList = new DoublyLinkedList<Entry<K, V>>();
		for (int i=0; i<MAX_SIZE; i++) {
			PositionList<Entry<K, V>> map_entries = (DoublyLinkedList<Entry<K, V>>)(list[i].entrySet());
			Position<Entry<K, V>> current_pos = map_entries.first();
			for (int j=0; j<map_entries.size(); j++) {
				entryList.addLast(current_pos.getElement());
				current_pos = map_entries.after(current_pos);
			}
		}
		return entryList;
	}
}
