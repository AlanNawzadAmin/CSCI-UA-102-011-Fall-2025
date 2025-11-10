package Sorting_final;

public class Sort {
	public static boolean isSorted(int[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] > array[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	public static void selectionSort(int[] array) {
		for(int i = 0; i < array.length; i++) {
			// find minimum entry
			int min_ind = i;
			for(int j = i+1; j < array.length; j++) {
				if(array[min_ind] > array[j]) {
					min_ind = j;
				}
			}
			// bring j to front (could also switch)
			for(int j=min_ind; j > i; j--) {
				int temp = array[j-1];
				array[j-1] = array[j];
				array[j] = temp;
			}
		}
	}
	
	public static void bubbleSort(int[] array) {
		boolean sorted = false;
		while(!sorted) {
			sorted = true;
			for(int i = 0; i < array.length - 1; i++) {
				if(array[i] > array[i+1]) {
					sorted = false;
					int temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
		}
	}
	
	public static void insertionSort(int[] array) {
		for(int i = 0; i < array.length; i++) {
			int j = i;
			while (j > 0 && array[j-1] > array[j]){
				int temp = array[j-1];
				array[j-1] = array[j];
				array[j] = temp;
				j--;
			}
		}
	}

	public static void main(String[] args) {
		int[] intarray;
		intarray = new int[]{5, 4, 6, 3, 7, 2, 1, 8};
		System.out.println(isSorted(intarray));
		bubbleSort(intarray);
		selectionSort(intarray);
		insertionSort(intarray);
		for(int i: intarray) {
			System.out.println(i);
		}
	}
}
