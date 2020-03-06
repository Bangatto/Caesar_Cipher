import java.util.Arrays;

public class selectionSort {
	//method
	public static int[] selectionSort(int[] sortedNums) {
		//search through the entire list for the smallest number and assign it minValue and move it to the first index
		//swap it with the number in place
		int i,j;
		int minValue,minIndex;
		int temp;
		//loop through upto the end of the list and search for the minValue--> the outer loop
		for(i=0; i < sortedNums.length; i++) {
			//assign minValue the first index of the list
			minValue = sortedNums[i];
			minIndex = i;
			//the inner loop starts at the **first unsorted** item and continues upto the last item of the list
			for(j=i; j < sortedNums.length; j++) {
				//check if there is any number which is less than the min value and replace the min value
				if (sortedNums[j] < minValue) {
					minValue = sortedNums[j];
					minIndex = j;
				}
			}
			//check if the min value was the first unsorted value, if yes, we need not to swap and if no, we can swap
			if (minValue < sortedNums[i] ) {
				temp = sortedNums[i];
				sortedNums[i] = sortedNums[minIndex];
				sortedNums[minIndex] = temp;
			}
		}
		return sortedNums;
	}
	public static void main(String[] args) {
		int[] sortedNums = {3,17,-5,-2,23,4};
		System.out.println(Arrays.toString(selectionSort(sortedNums)));
	}
}
