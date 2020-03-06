import java.util.Arrays;

public class bubbleSort {
	//methods
	public static int[] bubbleSort(int[] sortedNums) {
		//initial i for outer loop, j for inner loop and a temp variable for the swap
		//if the number at j is greater than the number at j+1, then swap
		//we expect a sorted list in ascending order
		int i;
		int j;
		int temp;
		int count = 0; // counter to count how many iterations it take to sort the elements
		for (i=0; i < sortedNums.length-1; i++) {
			//j runs upto the number of items already sorted or the number of iterations already completed
			for (j=0; j < sortedNums.length-1-i; j++) {
				if (sortedNums[j] > sortedNums[j+1]) {
					temp = sortedNums[j];
					sortedNums[j] = sortedNums[j+1];
					sortedNums[j+1] = temp;
				}
			}
		count ++;
		System.out.println(count);
		}
		return sortedNums;
	}
	public static void main(String[] args) {
		int[] sortedNums = {3,17,-5,-2,23,4};
		System.out.println(Arrays.toString(bubbleSort(sortedNums)));
		
	}

}
