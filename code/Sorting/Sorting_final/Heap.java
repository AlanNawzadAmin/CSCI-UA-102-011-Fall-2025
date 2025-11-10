package Sorting_final;
import java.lang.Math;

public class Heap {
	static int MAX_HEIGHT = 9;
	static int MAX_SIZE = (int)(Math.pow(2, MAX_HEIGHT + 1)) - 1;
	Integer[] array;
	int size = 0;
	
	public Heap() {
		array = new Integer[MAX_SIZE];
	};
	
	public void add(int key) {
		array[size] = key;
		int current_ind = size;
		boolean in_order = false;
		while(!in_order && !isRoot(current_ind)) {
			int parent_ind = (current_ind - 1) / 2;
			if(array[current_ind]>array[parent_ind]) {
				in_order = true;
			} else {
				int temp = array[parent_ind];
				array[parent_ind] = array[current_ind];
				array[current_ind] = temp;
				current_ind = parent_ind;
			}
		}
		size++;
	}
	
	private boolean isRoot(int index) {
		return index == 0;
	}
	
	private boolean isLeaf(int index) {
		return 2 * index + 1 >= size;
	}
	
	public int removeMin() {
		int remove = array[0];
		array[0] = array[size - 1];
		array[size - 1] = null;
		size--;
		
		int current_ind = 0;
		boolean in_order = false;
		while(!in_order && !isLeaf(current_ind)){
			// find smaller child
			int child_ind_1 = 2 * current_ind + 1;
			int child_ind_2 = 2 * current_ind + 2;
			int smaller_child_ind = child_ind_1;
			if(child_ind_2 < size) {
				if(array[child_ind_1] > array[child_ind_2]) {
					smaller_child_ind = child_ind_2;
				}
			}
			// swap if needed
			if(array[smaller_child_ind]>array[current_ind]) {
				in_order = true;
			} else {
				int temp = array[smaller_child_ind];
				array[smaller_child_ind] = array[current_ind];
				array[current_ind] = temp;
				current_ind = smaller_child_ind;
			}
		}
		return remove;
	}
	
	public static void main(String[] args) {
		int[] intarray;
		intarray = new int[]{5, 4, 6, 3, 7, 2, 1, 8};
		
		Heap heap = new Heap();
		for(int i: intarray) {
			heap.add(i);
		}
		for(int i: intarray) {
			System.out.println(heap.removeMin());
		}
	}
}
