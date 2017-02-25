/**
 * 
 * @author Kevin Vu
 *
 */

public class TestHash {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashTable table = new HashTable(100);
		
		String[] data = {"ayee", "shoop", "lmao", "bruh", "ayee", "kevin", "rip", "salt", "ayee", "shoop", "boop", "beep", "hoo","lmao", "bam"
			, "bomp", "lmao", "kevin", "man"};
		for(String s : data)
		{
			table.incCount(s);
		}
		
		table.printTable();
		System.out.println(table.getLength());
		
		DataCount<String>[] dataCountsArray = table.getCounts();
		
		for (int i = 0; i < dataCountsArray.length; i++)
		{
			System.out.println("DataCount String: " + dataCountsArray[i].data + " | DataCount Count: " + dataCountsArray[i].count);
		}
		
		    DataCount<String> d = new DataCount<>("ayee", 3);
	        DataCount<String> d1 = new DataCount<>("lmao", 3);
	        DataCount<String> d2 = new DataCount<>("salt", 1);
	        DataCount<String> d3 = new DataCount<>("shoop", 2);
	        DataCount<String> d4 = new DataCount<>("kevin", 2);
	        DataCount<String> d5 = new DataCount<>("bruh", 1);
	        DataCount<String> d6 = new DataCount<>("rip", 1);
	        DataCount<String> d7 = new DataCount<>("boop", 1);
	        DataCount<String> d8 = new DataCount<>("beep", 1);
	        DataCount<String> d9 = new DataCount<>("hoo", 1);
	        DataCount<String> d10 = new DataCount<>("bam", 1);
	        DataCount<String> d11 = new DataCount<>("bomp", 1);
	        DataCount<String> d12 = new DataCount<>("man", 1);
	        


	        DataCount[] expected = {d, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12};

	        boolean error = false;

	        if(dataCountsArray.length != expected.length) {
	            error = true;
	        } else {
	            int k = 0;
	            for (int i = 0; i < expected.length; i++)
	            {
	            	for (int j = 0; j < dataCountsArray.length; j++)
	            	{
	            		
	            		if (!(dataCountsArray[i].data.equals(expected[j].data)) || dataCountsArray[i].count != (expected[j].count))
	            		{
	            			error = false;
	            		}
	            		
	            	}
	            }
	        }

	        if(error) {
	            System.out.println("Test failed");
	        } else {
	            System.out.println("Test passed");
	        }
	        
	       
	      
	        
	        

	}
}
