/**
 * @author Kevin Vu
 */

import java.util.HashMap;
import java.util.Map;

public class Correlator {
	
	 private static int getTotalWords(DataCount<String>[] data) {
	    	int sum = 0;
	    	for (DataCount<String> d : data)
	    	{	sum = sum + d.count;	}
	    	return sum;
	 }
	 
	 private static Map<String,Double> getFrequencies(DataCount<String>[] data)
	 {
		 Map<String,Double> frequencies = new HashMap<>();
		 int totalWords = getTotalWords(data);
		 for (DataCount<String> d : data)
		 {
			 double frequency = (double) d.count / (double) totalWords;
			 if(frequency < 0.01 && frequency > 0.0001) {
	                System.out.format("%s - %f\n", d.data, frequency);
	                frequencies.put(d.data, frequency);
			 }
			 
		 }
		 return frequencies;
	 }
	 
	    private static double differenceMetric(DataCount<String>[] dataCounts1, DataCount<String>[] dataCounts2) {
	        System.out.println("Frequencies for first file:");
	        Map<String, Double> frequencies1 = getFrequencies(dataCounts1);

	        System.out.println("\nFrequencies for second file:");
	        Map<String, Double> frequencies2 = getFrequencies(dataCounts2);

	        double sum = 0;
	        for(String key : frequencies1.keySet()) {
	            if(frequencies2.containsKey(key)) {
	                double diff = Math.abs(frequencies1.get(key) - frequencies2.get(key));
	                sum += Math.pow(diff, 2);
	            }
	        }
	        return sum;
	    }

	 public static void main(String[] args) 
	 {
	        if(args.length != 3) {
	            System.out.println("Usage: [-b | -a | -h] <filename 1> <filename 2>\n");
	            System.out.println("-b Use an unbalanced BST in the backend");
	            System.out.println("-a Use an AVL Tree in the backend");
	            System.out.println("-h Use a Hashtable in the backend");
	            return;
	        }

	        DataCount<String>[] count1;
	        DataCount<String>[] count2;

	        count1 = WordCount.countWords(args[0], args[1]);
			count2 = WordCount.countWords(args[0], args[2]);

	        System.out.println("\nDifference metric: " + differenceMetric(count1, count2)); 
	        
	      
		 
		 

         
           

	 }
}
