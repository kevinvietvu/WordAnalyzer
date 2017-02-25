//one from online, the one I used in project was from the book
public class OtherQuickSort {
       /**
	   private static <E> void quickSort(DataCount<E>[] a)
	   {
		    quickSort(a, 0, a.length - 1);
	   }
	    
	 
	  private static <E> void quickSort(DataCount<E>[] counts,int low, int high) {
		  int i = low;
		  int j = high;
		  // pivot is middle index
		  DataCount<E> pivot = counts[low + (high - low) / 2];

		  // Divide into two arrays
		  while (i <= j) {
	  
	  	     
			  while (compareTo(counts[i],pivot) > 0 ) {
				  i++;
			  }
			  while (compareTo(counts[j],pivot) < 0 ) {
				  j--;
			  }
			  if (i <= j) {
				  swapReferences(counts ,i , j);
				  // move index to next position on both sides
				  i++;
				  j--;
			  }	
		  }

		  // calls quickSort() method recursively
		  if (low < j) {
			  quickSort(counts,low, j);
		  }

		  if (i < high) {
		      quickSort(counts,i, high);
		  }
	  }
	  */

}
