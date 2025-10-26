package BinarySearchTrees_final;

import Maps_final.Entry;
import Maps_final.Map;

// largely taken from textbook
public class AVLTree<V> extends BinarySearchTree<V>{
	private boolean isBalanced(SortEntry<V> node) {
		SortEntry<V> left = node.getLeft();
		SortEntry<V> right = node.getRight();
		if (left == null && right == null) {
			return true;
		}
		else if (left == null) {
			return right.getHeight() <= 0;
		}
		else if (right == null) {
			return left.getHeight() <= 0;
		}
		else {
			return Math.abs(left.getHeight() - right.getHeight()) <= 1;	
		}
	}

	private SortEntry<V> highestChild(SortEntry<V> node) {
		SortEntry<V> left = node.getLeft();
		SortEntry<V> right = node.getRight();
		if(left == null)
			return right;
		else if (right == null)
			return left;
		else {
			if (left.getHeight() > right.getHeight())
				return left;
			else
				return right;
		}
	}
	
	private void replaceChild(SortEntry<V> parent, SortEntry<V> new_child, boolean isLeft) {
		if (isLeft)
			parent.setLeft(new_child);
		else
			parent.setRight(new_child);
		if (new_child != null) new_child.setParent(parent);
	}
	
	private void rotate(SortEntry<V> node) {
		SortEntry<V> node_p = node.getParent(); // assume not null
		SortEntry<V> node_pp = node_p.getParent();
		if (node_pp == null) {
			root = node;
			node.setParent(null);
		}
		else {
			replaceChild(node_pp, node, node_p==node_pp.getLeft());
		}
		if (node == node_p.getLeft()) {
			replaceChild(node_p, node.getRight(), true);
			replaceChild(node, node_p, false);
		}
		else {
			replaceChild(node_p, node.getLeft(), false);
			replaceChild(node, node_p, true);
		}
	}
	
	private void recomputeHeight(SortEntry<V> node) {
		SortEntry<V> left = node.getLeft();
		SortEntry<V> right = node.getRight();
		int left_height = -1;
		int right_height = -1;
		if (left != null) {left_height = left.getHeight();}
		if (right != null) {right_height = right.getHeight();}
		node.setHeight(Math.max(left_height, right_height) + 1);
	}
	
	private void rearrange(SortEntry<V> new_node) {
		SortEntry<V> node = new_node.getParent();
		while (node != null && isBalanced(node)) {
			recomputeHeight(node);
			node = node.getParent();
		}
		if (node != null) {
			// get nodes to rearrange
			SortEntry<V> node_c = highestChild(node);
			SortEntry<V> node_cc = highestChild(node_c);
			// rearrange
			if ((node.getLeft() == node_c) == (node_c.getLeft() == node_cc)) {
				rotate(node_c);
				recomputeHeight(node);
				recomputeHeight(node_cc);
				recomputeHeight(node_c);
			}
			else {
				rotate(node_cc);
				rotate(node_cc);
				recomputeHeight(node);
				recomputeHeight(node_c);
				recomputeHeight(node_cc);
			}
		}
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
			SortEntry<V> new_node = new SortEntry<V>(key, value, nearest_node);
			nearest_node.setLeft(new_node);
			size++;
			rearrange(new_node);
			return null;
		} else {
			SortEntry<V> new_node = new SortEntry<V>(key, value, nearest_node);
			nearest_node.setRight(new_node);
			size++;
			rearrange(new_node);
			return null;
		}
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = new AVLTree<String>();
		map.put(0, "zero");
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");

//		map.remove(3);
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
