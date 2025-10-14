package Maps_final;

import LinkedList_final.Position;
import LinkedList_final.PositionList;
import LinkedList_final.DoublyLinkedList;

public class UnsortedMap<K, V> implements Map<K, V>{
	private int size;
	private PositionList<Entry<K, V>> entrylist;
	private static class UnsortEntry<K, V> implements Entry<K, V>{
		K key;
		V value;
		UnsortEntry(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {return key;};
		public V getValue() {return value;};
	}
	
	public UnsortedMap(){
		entrylist = new DoublyLinkedList<Entry<K, V>>();
	}
	
	public int size() {return size;};
	public boolean isEmpty() { return size() == 0;};
	public PositionList<Entry<K,V>> entrySet(){
		return entrylist;
	};
	public PositionList<K> keySet(){
		PositionList<K> keylist = new DoublyLinkedList<K>();
		for (Entry<K,V> entry: entrylist) {
			keylist.addLast(entry.getKey());
		}
		return keylist;
	}
	
	public PositionList<V> values(){
		PositionList<V> valuelist = new DoublyLinkedList<V>();
		for (Entry<K,V> entry: entrylist) {
			valuelist.addLast(entry.getValue());
		}
		return valuelist;
	}
	
	public V get(K key) {
		for (Entry<K,V> entry: entrylist) {
			if (entry.getKey() == key) return entry.getValue();
		}
		return null;
	};
	
	public V remove(K key) {
//		for (Entry<K,V> entry: entrylist) {
//			if (entry.getKey() == key) {
//				entrylist.remove(entry);???
//				return entry.getValue();
//			}
//		}
		Position<Entry<K,V>> current_pos = entrylist.first();
		for (int i = 0; i< entrylist.size(); i++) {
			Entry<K, V> entry = current_pos.getElement();
			if (entry.getKey() == key) {
				entrylist.remove(current_pos);
				size--;
				return entry.getValue();
			}
			current_pos = entrylist.after(current_pos);
		}
		return null;
	};
	
	
	public V put(K key, V value) {
		V old_val = get(key);
		if (old_val == null) {
			entrylist.addLast(new UnsortEntry<K, V>(key, value));
			size++;
			return null;
		}
		else{
			V returnValue = remove(key);
			entrylist.addLast(new UnsortEntry<K, V>(key, value));
			return returnValue;
		}
	};
	
}
