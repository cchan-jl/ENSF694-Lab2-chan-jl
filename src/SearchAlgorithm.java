import java.util.Scanner;
import java.util.Arrays;

/**
 * Class implements search algorithms to an integer array. The algorithms
 * include a linear search, an optimized linear search, and an interpolation search.
 * 
 * @author Carrie Chan
 *
 */
public class SearchAlgorithm {
	
	/**
	 * Linear search through the user input array to find the index of the specified key.
	 * 
	 * @param array user input integer array
	 * @param key user input search key
	 * @return index of key if found and -1 if key is not found
	 */
	public static int linearSearch(int[] array, int key) {
		for(int i = 0; i < array.length; i ++) {
			if(array[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Implementation of an optimized linear search by checking two elements at once, which
	 * halves the number of loop iterations and improves running time.
	 * 
	 * @param array user input integer array
	 * @param key input search key
	 * @return index of key if found and -1 if key is not found
	 */
	public static int linearSearchImproved(int[] array, int key) {
		for(int i = 0; i < array.length; i += 2) {  //incrementing by two for each loop iteration
			if(array[i] == key) {
				return i;
			}
			
			if(i + 1 < array.length && array[i + 1] == key) {  //check for second element
				if(array[i + 1] == key) {
					return i + 1;  //return index of second element if key is found
				}
			}
		}
		return -1;
	}
	
	/**
	 * Interpolation search through user input array to find the index of specified key.
	 * 
	 * @param array user input integer array
	 * @param key input search key
	 * @return index of key if found and -1 if key is not found
	 */
	public static int interpolationSearch(int[] array, int key) {		
		int low = 0, mid, high = array.length - 1;
		
		Arrays.sort(array);  //sort array for interpolation operation
		while (low <= high) {
			double pos = (double)(key - array[low]) / (double)(array[high] - array[low]);  //cast to double to ensure accurate computation and minimize rounding
			mid = low + (int)((high - low) * pos);
			if(key < array[mid]) {
				high = mid - 1;
			} else if(array[mid] < key) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	

	public static void main(String[] args) {
		//Scanner to read integer user input
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the number of elements in the array: ");
		int arrayElements = reader.nextInt();
		int array[] = new int[arrayElements];
	
		System.out.println("Enter the elements in the array: ");
		for(int i = 0; i < arrayElements; i ++) {  //loop to read specified number of integer elements into array
			array[i] = reader.nextInt();
		}
		
		System.out.print("Enter the search key: ");
		int searchKey = reader.nextInt();
		
		//Linear search outputs
		long startTimeLinear = System.nanoTime();
		int linearIndex = linearSearch(array, searchKey);
		long endTimeLinear = System.nanoTime();
		System.out.println("\nLinear Search:");
		if(linearIndex == -1) {  //when returned index is -1, key is not found in array
			System.out.println("Search key NOT FOUND");
		} else {
			System.out.println("Search key FOUND at index " + linearIndex);
		}
		long linearTime = endTimeLinear - startTimeLinear;
		System.out.println("Time in nanoseconds = " + linearTime);
		
		//Improved linear search outputs
		long startTimeImproved = System.nanoTime();
		int linearIndexImproved = linearSearchImproved(array, searchKey);
		long endTimeImproved = System.nanoTime();
		System.out.println("\nImproved Linear Search:");
		if(linearIndexImproved == -1) {
			System.out.println("Search key NOT FOUND");
		} else {
			System.out.println("Search key FOUND at index " + linearIndexImproved);
		}
		long linearTimeImproved = endTimeImproved - startTimeImproved;
		System.out.println("Improved time in nanoseconds = " + linearTimeImproved);
		
		//Interpolation search outputs
		long startTimeInterpolation = System.nanoTime();
		int interpolationIndex = interpolationSearch(array, searchKey);
		long endTimeInterpolation = System.nanoTime();
		System.out.println("\nInterpolation Search:");
		if(interpolationIndex == -1) {
			System.out.println("Search key NOT FOUND");
		} else {
			System.out.println("Search key FOUND at index " + interpolationIndex);
		}
		long interpolationTime = endTimeInterpolation - startTimeInterpolation;
		System.out.println("Time in nanoseconds = " + interpolationTime);
		
		reader.close();
	}

// For the assignment sample input, the linear search was faster than the interpolation search
// since the sample input had a small amount of data and the array for interpolation had to be sorted beforehand which adds time.
}
