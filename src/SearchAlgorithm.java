import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;

public class SearchAlgorithm {
	
	public static int linearSearch(int[] array, int key) {
		for(int i = 0; i < array.length; i ++) {
			if(array[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	public static int linearSearchImproved(int[] array, int key) {
		int endOfArray = array.length - 1;
		for(int i = 0; i < array.length; i ++) {
			if(array[i] == key) {
				return i;
			}	
			
			if(array[endOfArray] == key) {
				return endOfArray;
			}
			endOfArray -= 1;
		}
		return -1;
	}
	
	public static int interpolationSearch(int[] array, int key) {
		Arrays.sort(array);
		int low = 0, mid, high = array.length - 1;
		
		while (low <= high) {
			double pos = (double)(key - array[low]) / (double)(array[high] - array[low]);
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
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the number of elements in the array: ");
		int arrayElements = reader.nextInt();
		int array[] = new int[arrayElements];
		
		System.out.println("Enter the elements in the array: ");
		for(int i = 0; i < arrayElements; i ++) {
			array[i] = reader.nextInt();
		}
		
		System.out.print("Enter the search key: ");
		int searchKey = reader.nextInt();
		
		long startTimeLinear = System.nanoTime();
		int linearIndex = linearSearch(array, searchKey);
		long endTimeLinear = System.nanoTime();
		if(linearIndex == -1) {
			System.out.println("Search key NOT FOUND");
		} else {
			System.out.println("Search key FOUND at index " + linearIndex);
		}
		long linearTime = endTimeLinear - startTimeLinear;
		System.out.println("Time in nanoseconds = " + linearTime);
		
		long startTimeInterpolation = System.nanoTime();
		int interpolationIndex = interpolationSearch(array, searchKey);
		long endTimeInterpolation = System.nanoTime();
		if(interpolationIndex == -1) {
			System.out.println("Search key NOT FOUND");
		} else {
			System.out.println("Search key FOUND at index " + interpolationIndex);
		}
		long interpolationTime = endTimeInterpolation - startTimeInterpolation;
		System.out.println("Time in nanoseconds = " + interpolationTime);
		
		long startTimeImproved = System.nanoTime();
		int linearIndexImproved = linearSearchImproved(array, searchKey);
		long endTimeImproved = System.nanoTime();
		if(linearIndexImproved == -1) {
			System.out.println("Search key NOT FOUND");
		} else {
			System.out.println("Search key FOUND at index improved " + linearIndexImproved);
		}
		long linearTimeImproved = endTimeImproved - startTimeImproved;
		System.out.println("Improved time in nanoseconds = " + linearTimeImproved);
	}

// For the assignment sample input, the linear search was faster than the inpterpolation search
// since the sample input had a small amount of data and the array for interpolation had to be sorted beforehand which adds time.
}
