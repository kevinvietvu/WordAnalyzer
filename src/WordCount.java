import java.io.IOException;
import java.util.Arrays;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 * 
 * @author Kevin Vu
 */
public class WordCount {
	
private static final int CUTOFF = 10;
	
	public static DataCount<String>[] countWords(String dataStructure, String file) {
			DataCount<String>[] counts;
			DataCounter<String> counter;
		   switch(dataStructure) {
           case "-b":
        	   counter = new BinarySearchTree<>();
               break;
           case "-a":
        	   counter = new AVLTree<>();
               break;
           case "-h":
        	   counter = new HashTable();
               break;
           default:
               counter = new BinarySearchTree<>();
               System.out.println("Invalid data structure. Using BST by default.");
               break;
       }
		   try {
				FileWordReader reader = new FileWordReader(file);
				String word = reader.nextWord();
				while (word != null) {
					counter.incCount(word);
					word = reader.nextWord();
				}
			} catch (IOException e) {
				System.err.println("Error processing " + file + " " + e);
				System.exit(1);
			}
		counts = counter.getCounts();
		return counts;
	}


	public static void print(DataCount<String>[] counts) {
		for (DataCount<String> c : counts) {
			System.out.println(c.count + " \t" + c.data);
		}
	}
	
	public static <E> int compareTo(DataCount<E> a, DataCount<E> b) {
		if (a.count == b.count)
		{
			if (((String) a.data).compareTo((String) b.data) < 0)
			{
				return 1;
			}
			else if (((String) a.data).compareTo((String) b.data) > 0)
			{
				return -1;
			}
		}
		else { return a.count - b.count; }
		return 0;
	}
	

	public static <E> void swapReferences(DataCount<E>[] array, int index1, int index2) 
		// pre: array is full and index1, index2 < array.length
		// post: the values at indices 1 and 2 have been swapped
	{
		DataCount<E> temp = array[index1];           // store the first value in a temp
		array[index1] = array[index2];      // copy the value of the second into the first
		array[index2] = temp;               // copy the value of the temp into the second
	}
	
	/**
	 * Quicksort algorithm.
	 * @param a an array of Comparable items.
	 */
	 public static <E extends Comparable<? super E>> void quickSort(DataCount<E>[ ] a )
	 {
		 quicksort( a, 0, a.length - 1 );
	 }
	 
	 /**
	  * Return median of left, center, and right.
	  * Order these and hide the pivot.
	  */
	  private static <E extends Comparable<? super E>> DataCount<E> median3( DataCount<E>[ ] a, int left, int right )
	  {
	  int center = ( left + right ) / 2;
	  if (compareTo(a[center],a[left]) > 0 )
	  swapReferences( a, left, center );
	  if (compareTo(a[ right ], a[ left ] ) > 0 )
	  swapReferences( a, left, right );
	  if(compareTo( a[ right ],a[ center ] ) > 0 )
	  swapReferences( a, center, right );
	 
	  // Place pivot at position right - 1
	  swapReferences( a, center, right - 1 );
	  return a[ right - 1 ];
	  }
	  
	  /**
	   * Internal quicksort method that makes recursive calls.
	   * Uses median-of-three partitioning and a cutoff of 10.
	   * @param a an array of Comparable items.
	   * @param left the left-most index of the subarray.
	   * @param right the right-most index of the subarray.
	   */
	   private static <E extends Comparable<? super E>> void quicksort(DataCount<E>[ ] a, int left, int right )
	   {
	   if( left + CUTOFF <= right )
	   {
		   DataCount<E> pivot = median3( a, left, right );
	  
		   // Begin partitioning
		   int i = left + 1, j = right;
		   for( ; ; )
		   { 
			   while( compareTo(a[ i ], pivot ) > 0 ) { i++; }
			   while( compareTo(a[ j ], pivot ) < 0 ) { j--; }
			   if( i < j )
				   swapReferences( a, i, j );
			   else
				   break;
		   }
	  
		   swapReferences( a, i, right - 1 ); // Restore pivot
	  
	   	   quicksort( a, left, i - 1 ); // Sort small elements
	   	   quicksort( a, i + 1, right ); // Sort large elements
	   }
	   else // Do an insertion sort on the subarray
		   insertionSort( a );
	   }
	   
	   /**
	    * Simple insertion sort.
	    * @param a an array of Comparable items.
	    */
	    public static <E extends Comparable<? super E>> void insertionSort( DataCount<E>[ ] a )
	    {
	    	int j;
	   
	    	for( int p = 1; p < a.length; p++ )
	    	{
	    		DataCount<E> tmp = a[ p ];
	    		for( j = p; j > 0 && compareTo(tmp, a[ j - 1 ] ) > 0; j-- )
	    			a[ j ] = a[ j - 1 ];
	    		a[ j ] = tmp;
	    	}
	    }


	/**  
	private static <E extends Comparable<? super E>> void sortByDescendingCount(DataCount<E>[] counts) {
		for (int i = 1; i < counts.length; i++) {
			DataCount<E> x = counts[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (counts[j].count > x.count) {
					break;
				} else if (counts[j].count == x.count) {
					if (counts[j].data.compareTo(x.data) <= 0) {
						break;
					}
				}
				counts[j + 1] = counts[j];
			}
			counts[j + 1] = x;
		}
	}*/

	/**
	 * Internal method that makes recursive calls.
	 * @param <E>
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	private static <E extends Comparable<? super E>> void mergeSort(DataCount<E>[] a, DataCount<E>[] tmpArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	/**
	 * Mergesort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static <E extends Comparable<? super E>> void mergeSort(DataCount<E>[] a) {
		DataCount<E>[] tmpArray =  new DataCount[a.length];

		mergeSort(a, tmpArray, 0, a.length - 1);
	}
	
	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * @param a an array of Comparable items.
	 * @param tmpArray an array to place the merged result.
	 * @param leftPos the left-most index of the subarray.
	 * @param rightPos the index of the start of the second half.
	 * @param rightEnd the right-most index of the subarray.
	 */
	 private static <E extends Comparable<? super E>> void merge( DataCount<E>[] a, DataCount<E>[] tmpArray, int leftPos, int rightPos, int rightEnd) {
		 int leftEnd = rightPos - 1;
		 int tmpPos = leftPos;
		 int numElements = rightEnd - leftPos + 1;
		
		 // Main loop
		 while( leftPos <= leftEnd && rightPos <= rightEnd )
			 if(compareTo(a[ leftPos ], a[ rightPos ]) > 0 )  //a[ leftPos ].count > (a[ rightPos ].count) || (a[leftPos].count == a[ rightPos ].count && a[ leftPos ].data.compareTo(a[ rightPos ].data) <= 0)
			 { tmpArray[ tmpPos++ ] = a[ leftPos++ ]; }
			 else {
				 tmpArray[ tmpPos++ ] = a[ rightPos++ ]; }
	
		 while( leftPos <= leftEnd ) { // Copy rest of first half 
			 tmpArray[ tmpPos++ ] = a[ leftPos++ ]; }
	
		 while( rightPos <= rightEnd ) { // Copy rest of right half
			 tmpArray[ tmpPos++ ] = a[ rightPos++ ]; }
	
		 // Copy tmpArray back
		 for( int i = 0; i < numElements; i++, rightEnd-- ) {
			 a[ rightEnd ] = tmpArray[ rightEnd ];
		 }
	 }

	private static double timeRunnable(Runnable runnable) {
		long startTime = System.nanoTime();
		runnable.run();
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000.0;
	}

	public static void main(String[] args) throws IOException {
		DataCount<String>[] counts;
		/**
        if(args.length != 3) {
            System.out.println("Usage: [-b | -a | -h] [-is | -qs | -ms] <filename>\n");
            System.out.println("-b - Use an Unbalanced BST");
            System.out.println("-a - Use an AVL Tree");
            System.out.println("-h - Use a Hashtable\n");
            System.out.println("-is - Sorts using insertion sort");
            System.out.println("-qs - Sorts using quick sort");
            System.out.println("-ms - Sorts using merge sort");
            return;
        }

        switch(args[1]) {
		    case "-is":
		    	counts = countWords(args[0], args[2]);
		        insertionSort(counts);
		    	print(counts);
		        break;
		    case "-qs":
		    	counts = countWords(args[0], args[2]);
		    	quickSort(counts);
		    	print(counts);
		        break;
		    case "-ms":
		    	counts = countWords(args[0], args[2]);
		    	mergeSort(counts);
		    	print(counts);
		        break;
		    default:
		        System.out.println("Invalid choice for second argument");
		        break; 
		} 
		*/
		counts = countWords("-h", "hamlet.txt");
    	mergeSort(counts);
    	print(counts);

    }

}

