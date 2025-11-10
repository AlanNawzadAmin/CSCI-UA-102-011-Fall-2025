package Sorting_final;

import Maps_final.Entry;

public class BucketSort {
	public static <V> void bucketSort(Entry<Integer, V>[] array) {
		int max = 0;
		for (int i = 0; i<array.length; i++) {
			max = Math.max(max, array[i].getKey());
		}
		
		Entry<Integer, V>[] new_array = (Entry<Integer, V>[])(new Entry[max+1]);
		for (int i = 0; i<array.length; i++) {
			new_array[array[i].getKey()] = array[i];
		}
		
		int pos_in_arr = 0;
		for (int i = 0; i<new_array.length; i++) {
			if (new_array[i] != null){
				array[pos_in_arr] = new_array[i];
				pos_in_arr++;
			}
		}
	}
	
	private static class dummyEntry implements Entry<Integer, Integer>{
		Integer key;
		public dummyEntry(int key) {
			this.key = key;
		}
		public Integer getKey() {return key;}
		public Integer getValue() {return 0;}
	}
	
	public static void main(String[] args) {
		Entry<Integer, Integer>[] intarray;
		intarray = (Entry<Integer, Integer>[])new Entry[]{
			new dummyEntry(5),
			new dummyEntry(4),
			new dummyEntry(6),
			new dummyEntry(3),
			new dummyEntry(7),
			new dummyEntry(2),
			new dummyEntry(1),
			new dummyEntry(8)};
		bucketSort(intarray);
		for(Entry<Integer, Integer> entry: intarray) {
			System.out.println(entry.getKey());
		}
	}
}
