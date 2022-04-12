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
		
		// Insertion Sort
		int[] insertionSortArray = randomArray(5000, 1, 100);
		long start = System.nanoTime();
		insertionSort(insertionSortArray);
		long end = System.nanoTime();
		System.out.println("Insertion Sort took " + (end - start) / 1000000.0 + " milliseconds");

		// Quick Sort
		int[] quickSortArray = randomArray(5000, 1, 100);
		start = System.nanoTime();
		quickSort(quickSortArray);
		end = System.nanoTime();
		System.out.println("Quick Sort took " + (end - start) / 1000000.0 + " milliseconds");

		// Merge Sort
		int[] mergeSortArray = randomArray(5000, 1, 100);
		start = System.nanoTime();
		mergeSort(mergeSortArray);
		end = System.nanoTime();
		System.out.println("Merge Sort took " + (end - start) / 1000000.0 + " milliseconds");
		
	}
}
