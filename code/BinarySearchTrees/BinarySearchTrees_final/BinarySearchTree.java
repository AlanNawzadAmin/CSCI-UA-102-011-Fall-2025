package BinarySearchTrees_final;

import LinkedList_final.DoublyLinkedList;
import LinkedList_final.PositionList;
import Maps_final.Entry;
import Maps_final.Map;

public class BinarySearchTree<V> implements Map<Integer, V> {
	protected static class SortEntry<V> implements Entry<Integer, V>{
		Integer key;
		V value;
		SortEntry<V> left;
		SortEntry<V> right;
		SortEntry<V> parent;
		int height;
		SortEntry(Integer key, V value, SortEntry<V> parent){
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
		public Integer getKey() { return key;}
		public V getValue() { return value;}
		public SortEntry<V> getLeft() { return left;}
		public SortEntry<V> getRight() { return right;}
		public SortEntry<V> getParent() { return parent;}
		public int getHeight() { return height;}
		public void setLeft(SortEntry<V> left) { this.left=left;}
		public void setRight(SortEntry<V> right) { this.right=right;}
		public void setParent(SortEntry<V> parent) { this.parent=parent;}
		public void setHeight(int height) { this.height=height;}
		public void setKey(Integer key) { this.key = key;}
		public void setValue(V value) { this.value = value;}
	}
	
	int size;
	SortEntry<V> root;
	
	public int size() { return size;}
	public boolean isEmpty() { return size==0;}
	protected SortEntry<V> nearestNode(Integer key){
		SortEntry<V> current_node = root;
		SortEntry<V> next_node = current_node;
		while (true) {
			if (current_node.getKey() == key)
				return current_node;
			else if (current_node.getKey() < key)
				next_node = current_node.getRight();
			else if (current_node.getKey() > key)
				next_node = current_node.getLeft();
			if (next_node != null)
				current_node = next_node;
			else
				return current_node;
		}
	}
	public V get(Integer key) {
		if (isEmpty()) return null;
		SortEntry<V> nearest_node = nearestNode(key);
		if (key == nearest_node.getKey()) {
			return nearest_node.getValue();
		}
		return null;
	}
	public V put(Integer key, V value) {
		if (isEmpty()) {
			root = new SortEntry<V>(key, value, null);
			size++;
			return null;
		}
		SortEntry<V> nearest_node = nearestNode(key);
		if (key == nearest_node.getKey()) {
			V old_value = nearest_node.getValue();
			nearest_node.setValue(value);
			return old_value;
		} else if (key < nearest_node.getKey()) {
			nearest_node.setLeft(new SortEntry<V>(key, value, nearest_node));
			size++;
			return null;
		} else {
			nearest_node.setRight(new SortEntry<V>(key, value, nearest_node));
			size++;
			return null;
		}
	}
	
	protected void removeSingleChildNode(SortEntry<V> node) {
//		Get parent, if it exists
		SortEntry<V> parent = node.getParent();
//		Get child, if it exists
		SortEntry<V> child = null;
		if (node.getLeft() != null)
			child = node.getLeft();
		else if (node.getRight() != null)
			child = node.getRight();
//		Point parent and child to each other
		if (parent != null) {
			if (parent.getLeft() == node) {
				parent.setLeft(child);
			} else {
				parent.setRight(child);
			}
		} else {
			root = child;
		}
//		Point child to parent
		if (child != null)
			child.setParent(parent);
//		Decrement size
		size--;
	}
	protected SortEntry<V> largestInSubtree(SortEntry<V> node) {
		SortEntry<V> next_node = node;
		while(next_node != null) {
			node = next_node;
			next_node = node.getRight();
		}
		return node;
	}
	protected SortEntry<V> smallestInSubtree(SortEntry<V> node) {
		SortEntry<V> next_node = node;
		while(next_node != null) {
			node = next_node;
			next_node = node.getLeft();
		}
		return node;
	}
	public V remove(Integer key) {
		if (isEmpty()) return null;
		SortEntry<V> nearest_node = nearestNode(key);
		if (key != nearest_node.getKey())
			return null;

		V old_value = nearest_node.getValue();
		if (nearest_node.getLeft() == null || nearest_node.getRight() == null) {
			removeSingleChildNode(nearest_node);
		} else {
			SortEntry<V> largest_minor = largestInSubtree(nearest_node.getLeft());
			nearest_node.setKey(largest_minor.getKey());
			nearest_node.setValue(largest_minor.getValue());
			removeSingleChildNode(largest_minor);
		}
		return old_value;
	}
	

	private void inorderEntries(SortEntry<V> p, PositionList<Entry<Integer, V>> list){
		if (p != null) {
			inorderEntries(p.getLeft(), list);
			list.addLast(p);
			inorderEntries(p.getRight(), list);
		}
	}
	public PositionList<Entry<Integer, V>> entrySet(){
		PositionList<Entry<Integer, V>> list = new DoublyLinkedList<Entry<Integer, V>>();
		inorderEntries(root, list);
		return list;
	}
	
	private void inorderValues(SortEntry<V> p, PositionList<V> list){
		if (p != null) {
			inorderValues(p.getLeft(), list);
			list.addLast(p.getValue());
			inorderValues(p.getRight(), list);
		}
	}
	public PositionList<V> values(){
		PositionList<V> list = new DoublyLinkedList<V>();
		inorderValues(root, list);
		return list;
	}
	
	private void inorderKeys(SortEntry<V> p, PositionList<Integer> list){
		if (p != null) {
			inorderKeys(p.getLeft(), list);
			list.addLast(p.getKey());
			inorderKeys(p.getRight(), list);
		}
	}
	public PositionList<Integer> keySet(){
		PositionList<Integer> list = new DoublyLinkedList<Integer>();
		inorderKeys(root, list);
		return list;
	}

	public static void main(String[] args) {
		Map<Integer, String> map = new BinarySearchTree<String>();
		map.put(3, "three");
		map.put(4, "four");
		map.put(1, "one");
//		map.put(2, "two");
//		map.put(0, "zero");
		map.remove(3);
		System.out.println("\n\nEntries:");
		for (Entry<Integer, String> entry: map.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		System.out.println("\n\nValues:");
		for (Integer key: map.keySet()) {
			System.out.println(map.get(key));
		}
	}
}
