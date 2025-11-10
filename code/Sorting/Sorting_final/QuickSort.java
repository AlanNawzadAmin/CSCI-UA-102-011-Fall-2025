package Sorting_final;

public class QuickSort {
	public static void quickSort(int[] arr) {
		if (arr.length > 1) {
			int pivot = arr[arr.length / 2];
			int n_smaller_than_pivot = 0;
			for (int i =0; i<arr.length; i++) {
				if (arr[i] < pivot) n_smaller_than_pivot++;
			}
			
			int[] arr1 = new int[n_smaller_than_pivot];
			int[] arr2 = new int[arr.length - n_smaller_than_pivot - 1];
			int pos1 = 0;
			int pos2 = 0;
			for (int i =0; i<arr.length; i++) {
				if (arr[i] < pivot) {
					arr1[pos1] = arr[i];
					pos1++;
				}
				else if (arr[i] > pivot) {
					arr2[pos2] = arr[i];
					pos2++;
				}
			}
			
			quickSort(arr1);
			quickSort(arr2);
			for (int i =0; i<n_smaller_than_pivot; i++) {
				arr[i] = arr1[i];
			}
			arr[n_smaller_than_pivot] = pivot;
			for (int i = n_smaller_than_pivot+1; i<arr.length; i++) {
				arr[i] = arr2[i - n_smaller_than_pivot-1];
			}
		}
	}
	
	public static void main(String[] args) {
		int[] intarray3 = new int[]{5, 4, 6, 3, 7, 2, 1, 8};
		quickSort(intarray3);
		for(int i: intarray3) {
			System.out.println(i);
		}
	}
}
