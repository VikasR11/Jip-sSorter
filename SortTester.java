import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class that tests the functionality of each method in Sort.java
 * 
 * @author Jaap Singh
 *
 */
class SortTester {

	/**
	 * Tests the functionality of insertionSort()
	 */
	@Test
	void testInsertionSort() {
		// creating test array and its sorted version
		int[] array = new int[] {7, 31, 54, 0, 2, 4, 5, 1, 2, 10, 8};
		int[] expected = new int[] {0, 1, 2, 2, 4, 5, 7, 8, 10, 31, 54};
		Sort.insertionSort(array);
		Assert.assertArrayEquals(expected, array);
	}
	
	/**
	 * Tests the functionality of quickSort()
	 */
	@Test
	void testQuickSort() {
		// creating test array and its sorted version
		int[] array = new int[] {5, 1, -6, 1, 56, 12 , 94, 23, 0, -5, 1};
		int[] expected = new int[] {-6, -5, 0, 1, 1, 1, 5, 12, 23, 56, 94};
		Sort.quickSort(array);
		Assert.assertArrayEquals(expected, array);
	}
	
	/**
	 * Tests the functionality of mergeSort()
	 */
	@Test
	void testMergeSort() {
		// creating test array and its sorted version
		int[] array = new int[] {-1, 46, 19, 0, 0, -4, 5, 2, 7, 2, 73, -100};
		int[] expected = new int[] {-100, -4, -1, 0, 0, 2, 2, 5, 7, 19, 46, 73};
		Sort.mergeSort(array);
		Assert.assertArrayEquals(expected, array);
	}
	
	/**
	 * Tests the functionality of randomArray()
	 */
	@Test
	void testRandomArray() {
		int[] array = Sort.randomArray(20, 10, 50);
		assertEquals(20, array.length); // making sure the array is of correct length
		for (int i = 0; i < 20; i++) {
			// checking if all the values fall within the specified range
			assertTrue(array[i] >= 10 && array[i] <= 50);
		}
	}

}
