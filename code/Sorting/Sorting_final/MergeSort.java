package Sorting_final;

public class MergeSort {
	public static int[] merge(int[] array1, int[] array2) {
		int combineLength = array1.length + array2.length;
		int[] combinedArray = new int[combineLength];
		int index1 = 0;
		int index2 = 0;
		for(int i=0; i < combineLength; i++) {
			if(index1 >= array1.length) {
				combinedArray[i] = array2[index2];
				index2++;
			}
			else if(index2 >= array2.length){
				combinedArray[i] = array1[index1];
				index1++;
			}
			else {
				if (array1[index1] > array2[index2]) {
					combinedArray[i] = array2[index2];
					index2++;
				}
				else {
					combinedArray[i] = array1[index1];
					index1++;
				}
			}
		}
		return combinedArray;
	}
	
	public static void mergeSort(int[] arr) {
		if (arr.length > 1) {
			int len1 = arr.length/2;
			int[] arr1 = new int[len1];
			int[] arr2 = new int[arr.length - len1];
			for (int i=0; i<arr.length; i++) {
				if (i<len1) arr1[i] = arr[i];
				else arr2[i-len1] = arr[i];
			}
			
			mergeSort(arr1);
			mergeSort(arr2);
			int[] new_arr = merge(arr1, arr2);
			for (int i=0; i<arr.length; i++) arr[i] = new_arr[i];
		}
	}
	
	public static void main(String[] args) {
		int[] intarray1 = new int[]{5, 4, 6, 3};
		int[] intarray2 = new int[]{7, 2, 1, 8};
		Sort.insertionSort(intarray1);
		Sort.insertionSort(intarray2);
		int[] combArray = merge(intarray1, intarray2);
		System.out.println(Sort.isSorted(combArray));
		for(int i: combArray) {
			System.out.println(i);
		}
		
		int[] intarray3 = new int[]{5, 4, 6, 3, 7, 2, 1, 8};
		mergeSort(intarray3);
		for(int i: intarray3) {
			System.out.println(i);
		}
	}
}
