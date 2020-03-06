import java.util.Arrays;

public class insertionSort {
	public static int[] insertionSort(int[] sortedNums) {
		int i, j ; // i for outer loop and j for inner loop
		int key, temp; // key for the start of the list already sorted and temp for swapping
		//i start at 1 because the first number is already sorted
		for (i = 1; i < sortedNums.length; i++) {
			key = sortedNums[i]; // is the first unsorted number in the array
			j = i-1; // the previous number
			while ( j >= 0 && key < sortedNums[j]) {
				temp = sortedNums[j];
				sortedNums[j] = sortedNums[j+1];
				sortedNums[j+1] = temp;
				j--;
				
			}
		}
		
		return sortedNums;
	}
	public static void main(String[] args) {
		int[] sortedNums = {3,17,-5,-2,23,4};
		System.out.println(Arrays.toString(insertionSort(sortedNums)));
	}

}
