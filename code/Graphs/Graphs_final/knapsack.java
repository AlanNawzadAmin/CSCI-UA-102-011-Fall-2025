package Graphs_final;

public class knapsack {
	private static Integer[] copyWithoutFirst(Integer[] list) {
//		copy list
		Integer[] newList = new Integer[list.length - 1];
		for (int j=0; j<list.length-1; j++) {
			newList[j] = list[j+1];
		}
		return newList;
	}
	
	public static boolean ks(Integer[] list, int target) {
//		Can improve by checking target at intermediate nodes
//		and removing obviously bad branches
		if (list.length == 0) {
			return target == 0;
		}
		boolean targetReached = false;
		Integer[] newList = copyWithoutFirst(list);
		targetReached = ks(newList, target - list[0]);

		if(!targetReached){
			targetReached = ks(newList, target);
		}
		return targetReached;
	}
}
