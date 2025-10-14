package Maps_final;
import LinkedList_final.Position;
import LinkedList_final.PositionList;
import LinkedList_final.DoublyLinkedList;

public class HashMap<K, V> implements Map<K, V>{
	// No collision resolution!
	public static int MAX_SIZE = 1000;
	private class EntryHash implements Entry<K, V>{
		private K key;
		private V value;
		private Position<Entry<K, V>> pos_in_list;
		EntryHash(K key, V value){
			this.key = key;
			this.value = value;
		}
		public K getKey() {return key;}
		public V getValue() {return value;}
		public Position<Entry<K, V>> getpos() {return pos_in_list;}
		public void setPos(Position<Entry<K, V>> pos) {pos_in_list = pos;}
	}
	
	EntryHash[] list;
	PositionList<Entry<K, V>> entryList;
	int size;
	public HashMap() {
		list = (EntryHash[])(new Object[MAX_SIZE]);
		entryList = new DoublyLinkedList<Entry<K, V>>();
	}
	
	public V put(K key, V value) {
		int hash_code = key.hashCode();
		int hash = hash_code % MAX_SIZE;
		if (list[hash] == null) {
			size++;
			list[hash] = new EntryHash(key, value);
			entryList.addLast(list[hash]);
			list[hash].setPos(entryList.last());
			return null;
		}
		else {
			V old_value = list[hash].getValue();
			Position<Entry<K, V>> pos = list[hash].getpos();
			entryList.remove(pos);
			list[hash] = new EntryHash(key, value);
			entryList.addLast(list[hash]);
			list[hash].setPos(entryList.last());
			return old_value;
		}
	}
	
	public V get(K key) {
		int hash_code = key.hashCode();
		int hash = hash_code % MAX_SIZE;
		if (list[hash] == null) {
			return null;
		}
		else {
			return list[hash].getValue();
		}
	}
	
	public V remove(K key) {
		int hash_code = key.hashCode();
		int hash = hash_code % MAX_SIZE;
		if (list[hash] == null) {
			return null;
		}
		else {
			V old_value = list[hash].getValue();
			Position<Entry<K, V>> pos = list[hash].getpos();
			entryList.remove(pos);
			list[hash] = null;
			size--;
			return old_value;
		}
	}
	
	public int size() {return size;};
	public boolean isEmpty() { return size() == 0;};
	
	public PositionList<K> keySet() {
		PositionList<K> keyList = new DoublyLinkedList<K>();
		for (int i=0; i<MAX_SIZE; i++) {
			if (list[i] != null) {
				keyList.addLast(list[i].getKey());;
			}
		}
		return keyList;
	}
	
	public PositionList<V> values() {
		PositionList<V> valueList = new DoublyLinkedList<V>();
		for (int i=0; i<MAX_SIZE; i++) {
			if (list[i] != null) {
				valueList.addLast(list[i].getValue());;
			}
		}
		return valueList;
	}
	
	public PositionList<Entry<K, V>> entrySet() {
		return entryList;
	}
}
