import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Sort.java - contains various sorting methods to sort integer arrays
 * 
 * @author Jaap Singh
 *
 */
public class Sort {

	
	/**
	 * Insertion sort algorithm that runs in O(N^2) time
	 * 
	 * @param arr -  to be sorted
	 */
	public static void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; ++i) {
            int val = arr[i];
            int j = i - 1;
            // we move elements that are greater than the val one position ahead
            while (j >= 0 && arr[j] > val) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j + 1] = val;
        }
    }

	/**
	 * Quick sort method that reduces the problem size by half each time
	 * Run time complexity: O(NlogN)
	 * 
	 * @param array to be sorted
	 * @return sorted version of input array
	 */
	public static int[] quickSort(int[] array) {
		quickSortHelper(array, 0, array.length - 1); // helper method
		return array;
	}
	
	/**
	 * Helper method to facilitate quickSortHelper(). Splits the array with smaller 
	 * elements on the left side and larger elements on the right side
	 * 
	 * @param array - to be sorted
	 * @param start - starting index
	 * @param end - ending index 
	 * @return - middle of the array after swapping ends 
	 */
	private static int partition(int[] array, int start, int end) {
		int middle = array[(start + end) / 2];
		int left = start - 1;
		int right = end + 1;
		while (true) {
			do {
				left++; // keep incrementing left until we need to swap
			} while (array[left] < middle); 
			do {
				right--; // keep decrementing left until we need to swap
			} while (array[right] > middle);
			if (left < right)
				swap(array, left, right); // perform the swap
			else
				return right; // returning the middle value as there's no need to swap
		}
	}

	/**
	 * Swaps the elements at indices left and right
	 * 
	 * @param array - needs swapping
	 * @param left - index of first element
	 * @param right - index of second element 
	 */
	private static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	/**
	 * Recursive helper method to implement quickSort()
	 * 
	 * @param array - to be sorted
	 * @param start - starting index of array
	 * @param end - ending index of array
	 */
	private static void quickSortHelper(int[] array, int start, int end) {
		if (start >= end) // base case for when length = 1
			return;
		int split = partition(array, start, end); // splitting the array into to smaller arrays
		quickSortHelper(array, start, split); // recursive call to sort left side
		quickSortHelper(array, split + 1, end); // recursive call to sort right side
	}
	
	/**
	 * Implements merge sort algorithm to sort arrays. 
	 * Run time complexity: O(NlogN)
	 * 
	 * @param array - to be sorted
	 * @return -  sorted version of array
	 */
	public static int[] mergeSort(int[] array) {
		int[] temp = new int[array.length];
		mergeSortHelper(array, temp, 0, array.length - 1); // calling the helper method
		return array;
	}

	/**
	 * Recursive helper method to facilitate the merge sort algorithm
	 * 
	 * @param array - to be sorted
	 * @param temp - placeholder array to temporarily store and move data
	 * @param start - starting index
	 * @param end - ending index
	 */
	private static void mergeSortHelper(int[] array, int[] temp, int start, int end) {
		if (start >= end) // base case
			return;
		int middle = (start + end) / 2; // finds the middle
		mergeSortHelper(array, temp, start, middle); // recursive call to sort left side
		mergeSortHelper(array, temp, middle + 1, end); // recursive call to sort right side
		merge(array, temp, start, middle, middle + 1, end); // merges both sides together 
	}
	
	/**
	 * 
	 * @param array - unsorted array
	 * @param temp - placeholder array to temporarily store and move data
	 * @param leftStart - index of start of left side
	 * @param leftEnd - index of end of left side
	 * @param rightStart - index of start of right side
	 * @param rightEnd - index of end of right side
	 */
	private static void merge(int[] array, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd) {
		int leftArrayTemp = leftStart;
		int tempTraverser = leftStart;
		// extracting the required element and placing it in the right array
		while (leftArrayTemp <= leftEnd && rightStart <= rightEnd) { 
			if (array[leftArrayTemp] <= array[rightStart]) { 
				// we add the element to the left side if it is smaller than the middle value
				temp[tempTraverser] = array[leftArrayTemp]; 
				leftArrayTemp++;
			} else { // if not, add to right
				temp[tempTraverser] = array[rightStart];
				rightStart++;
			}
			tempTraverser++;
		}
		// completes the array by adding missing values 
		if (rightStart > rightEnd)
			while (leftArrayTemp <= leftEnd) {
				temp[tempTraverser] = array[leftArrayTemp];
				leftArrayTemp++;
				tempTraverser++;
			}
		else
			while (rightStart <= rightEnd) {
				temp[tempTraverser] = array[rightStart];
				rightStart++;
				tempTraverser++;
			}
		//array = Arrays.copyOfRange(temp, leftStart, rightEnd);
		// copy sorted values back to array
		for (int i = leftStart; i <= rightEnd; i++)
			array[i] = temp[i];
	}
	
	
	/** 
	 * This method returns an array of random integers between the specified values a and b
	 * 
	 * @param n - number of elements
	 * @param a - starting element of range (included)
	 * @param b - ending element of range (included)
	 * @return array that contains the values
	 */
	public static int[] randomArray(int n, int a, int b) {
		int[] toReturn = new int[n]; // creating array
		for (int i = 0; i < n; i++) {
			toReturn[i] = (int)(Math.random()*(b-a+1))+a; // using Math.random() to generate numbers
		}
		return toReturn;
	}

	public static void main(String[] args) {
		///// Benchmarking //////
		System.out.println("---- BENCHMARKING ----\n\n");
		int[] sizes = new int[] {10, 20, 50, 100, 200, 500, 1000, 2000, 5000};
		long start;
		long end;
		int[] array;
		
		// Insertion Sort
		System.out.println("Insertion Sort:");
		for (int size: sizes) {
			array = randomArray(size, -100, 100);
			start = System.nanoTime();
			insertionSort(array);
			end = System.nanoTime();
			System.out.println("With input size "+size+": " + (end - start) + " nanoseconds");
		}
		

		// Quick Sort
		System.out.println("\nQuick Sort:");
		for (int size: sizes) {
			array = randomArray(size, -100, 100);
			start = System.nanoTime();
			quickSort(array);
			end = System.nanoTime();
			System.out.println("With input size "+size+": " + (end - start) + " nanoseconds");
		}
		

		// Merge Sort
		System.out.println("\nMerge Sort:");
		for (int size: sizes) {
			array = randomArray(size, -100, 100);
			start = System.nanoTime();
			mergeSort(array);
			end = System.nanoTime();
			System.out.println("With input size "+size+": " + (end - start) + " nanoseconds");
		}
		
		
		///////// EXTRA CREDIT: ///////////
		System.out.println("\n\n---- Extra credit ----\n");
		System.out.println("All use an input of 5000.\n");
		/// My implementation vs other sorting methods
		array = randomArray(5000, -100, 100);
		start = System.nanoTime();
		Arrays.sort(array);
		end = System.nanoTime();
		System.out.println("Java's in-built Arrays.sort took " + (end - start) + " nanoseconds");
		
		ArrayList<Integer> builtInSort = new ArrayList<Integer>(5000);
		array = randomArray(5000, -100, 100);
		for (int i: array) {
			builtInSort.add(i);
		}
		start = System.nanoTime();
		Collections.sort(builtInSort);
		end = System.nanoTime();
		System.out.println("Java's in-built Collections.sort took " + (end - start) + " nanoseconds");
		
		array = randomArray(5000, -100, 100);
		start = System.nanoTime();
		heapSort(array);
		end = System.nanoTime();
		System.out.println("Heap Sort took " + (end - start) + " nanoseconds");
		
		array = randomArray(5000, -100, 100);
		start = System.nanoTime();
		selectionSort(array);
		end = System.nanoTime();
		System.out.println("Selection Sort took " + (end - start) + " nanoseconds");
		
		System.out.println("\nOut of all sorting algorithms, quick sort works fastest for random integer input.");
		
	}
	
	/** THE FOLLOWING METHODS ARE NOT MY IMPLMENTATION. THEY WERE ADDED FOR 
	 * 	EXTRA CREDIT PURPOSES
	 */
	
	/*
	 * Heapsort algorithm from the internet
	 */
	private static void heapSort(int arr[]) {
        int n = arr.length;
  
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
  
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
  
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
  
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    private static void heapify(int arr[], int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
  
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
  
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
  
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
  
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    
    /*
	 * Selection Sort algorithm from the internet
	 */
    private static void selectionSort(int arr[]) {
        int n = arr.length;
  
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
  
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
	
}
