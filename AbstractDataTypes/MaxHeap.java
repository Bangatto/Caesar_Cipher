import java.util.*;
//Max_heap operates on the idea that the Maximum element is at the root
//the root is greater than or equal to its descendants
//Max_heap maintains the complete binary tree property that all the levels are filled up
//Except the last level which must be filled from the left
//parent is at index [i / 2]
//leftchild is at index [2 * i]
//rightChild ----> index[2 * i + 1]
//Array[0] is always empty

//METHODS:
//1. getMax() ---> O (1) return the maximum element of the maxHeap 
//2. extractMax() ---> O(LOG N) -->remove the maximum element of the maxheap--ideally the root
//3. insert()-O(LOG N)-> add an element into the maxheap and maintain the heap property(Adds it at the end and do heapify)
public class MaxHeap {
	//class fields
	private int[] maxHeap;
	private int size ;
	private int maxSize;
	//initialize the constructor
	MaxHeap(int maxSize){
		this.size = 0;
		//index zero is given maxValue
		maxHeap[0] = Integer.MAX_VALUE;
		this.maxSize = maxSize;
		//leave an extra space to add a new element always.
		maxHeap = new int[maxSize+1];
	}
	//get the position of the parent
	private int parent(int par) {
		return (par/2);
	}
	//get the position of the leftChild
	private int leftChild(int leftchild) {
		return (leftchild * 2);
	}
	//get the position of the rightChild
	private int rightChild(int rightchild) {
		return (rightchild * 2) + 1;
	}
	//swap the parent with either the left or the right child
	private void swap(int parent, int child) {
		int temp = maxHeap[parent];
		maxHeap[parent] = maxHeap[child];
		maxHeap[child] = temp;
	}
	//insert a new element...compare with its parent, if its smaller, do nothing, if its bigger, swap with its parent
	//UPHEAPIFY
	private void UpHeapify(int Position) {
		//store the item at the parent position
		int temp = maxHeap[Position];
		//parent(parentPosition) --> return the value of the parents of the position added
		while(Position > 0 && temp > maxHeap[parent(Position)]) {
			//keep moving the element up by swapping with its parent
			maxHeap[Position] = maxHeap[parent(Position)];
			Position = parent(Position);
		}
		//add the element at its right position
		maxHeap[Position] = temp;	
	}
	//DOWNHEAPIFY
	//check the largest value of the left and the right child, make it the parent
	//repeat until you find the right replacement
	private void DownHeapify(int position) {
		//check if the element is a leaf, do nothing
		if (position >= (size / 2) && position <= size)
        return;
		//check if there is a swap needed
        if (maxHeap[position] < maxHeap[leftChild(position)] || maxHeap[position] < maxHeap[rightChild(position)]) {
        	//replacing parent with maximum of left and right child 
            if (maxHeap[leftChild(position)] > maxHeap[rightChild(position)]) {
                swap(position, leftChild(position));
              //after swaping, heapify is called on the leftchild to fix its position 
                DownHeapify(leftChild(position));
            } else {
            	//the right child is bigger,we swap
               swap(position, rightChild(position));
               //after swaping, heapify is called on the children 
                DownHeapify(rightChild(position));
            }
        }
	}
	
		
	//insert an element 1. Increase the size of the heap 2. keep the new element at the end of the heap
	//3. heapify i.e check is smaller than its parent, do nothing otherwsie heapifyUp
	private void Insert(int element) {
		//increase the size of the heap
		size ++;
		maxHeap[size] = element;
		int currentSize = size;
		//perform upHeapify
		UpHeapify(currentSize);
	}
	//delete an element 
	private int extraMax(int element) {
		int maxValue = maxHeap[1];
		//copy the last element into the root
		maxHeap[1] = maxHeap[size--];
		//perform downHeapify i.e check the largest child between the left and the right
		DownHeapify(1);
		
		return maxValue;
	}
	
//implemented using a priority queue but order of the elements is reversed
	public static void main(String[] args) {
		//sort the elements of the maxheap---> Ascending order[3,4,5,6,8,9,10]
		ArrayList<Integer> maxHeapSort = new ArrayList<>();
		//sort elements from the minHeap---> Descending order [10,9,8,6,5,4,3]
		ArrayList<Integer> minHeapSort = new ArrayList<>();
		//implement mAxHeap...> reverse the order at which
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		//implement minHeap....Maintain natural ordering
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		//initialize the ARRAY implementation of the MaxHeap
		MaxHeap mxHeap = new MaxHeap(20);
		mxHeap.Insert(1);
		mxHeap.Insert(5);
		mxHeap.Insert(9);
		mxHeap.Insert(7);
		//System.out.println("mxHeap is here: "+ mxHeap.toString());
		

		//add all the elements into the maxHeap--> [8,6,3,5,10,4,9]
		maxHeap.add(8);
		maxHeap.add(6);
		maxHeap.add(3);
		maxHeap.add(5);
		maxHeap.add(4);
		maxHeap.add(9);
		maxHeap.add(10);
		minHeap.add(8);
		minHeap.add(6);
		minHeap.add(3);
		minHeap.add(5);
		minHeap.add(4);
		minHeap.add(9);
		minHeap.add(10);
		System.out.println("Elements in the Min-heap :"+ minHeap.toString());
		System.out.println("Elements in the Max-heap :"+ maxHeap.toString());
		
		//add all the elements into the array in ascending order
		Iterator iter = maxHeap.iterator();
		while(iter.hasNext()) {	
			maxHeapSort.add(0, maxHeap.poll());
			System.out.println("Sorted elements :"+ maxHeapSort.toString());
			System.out.println("Elements in the heap :"+ maxHeap.toString());
			//System.out.println("Sorted elements :"+ heapsort.toString());
			//System.out.println(iter.next());
		}
		//add all the elements into the array in descending order
		Iterator itr = minHeap.iterator();
		while(itr.hasNext()) {	
			minHeapSort.add(0, minHeap.poll());
			System.out.println("Sorted elements from Min-Heap :"+ minHeapSort.toString());
			//System.out.println("Elements in the heap :"+ maxHeap.toString());
			//System.out.println("Sorted elements :"+ heapsort.toString());
			//System.out.println(iter.next());
			}
	}
}
