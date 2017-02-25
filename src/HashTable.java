/**
 * An implementation of hashtable mapping strings to
 * integers. The hashtable uses separate chaining 
 * for collision reduction.
 * 
 * @author Kevin Vu
 */
public class HashTable implements DataCounter<String> {
    private static final int DEFAULT_BUCKET_SIZE = 20;

    private Node[] buckets;

    private int currentSize;

    public HashTable() {
        this(DEFAULT_BUCKET_SIZE);
    }

    public HashTable(int bucketCount) {
        this.buckets = new Node[bucketCount];
        currentSize = 0;
    }

    
    @SuppressWarnings("unchecked")
    public DataCount<String>[] getCounts() {
        DataCount<String>[] dataCounts = new DataCount[currentSize];
        int i = 0;
        for(Node node : buckets) {
            for(; node != null; node = node.next) {
                dataCounts[i++] = new DataCount<>(node.data, node.count);
            }
        }
        return dataCounts;
    }

    
    public int getSize() {
        return currentSize;
    }
    
    public int getLength() {
    	return buckets.length;
    }

  
    public void incCount(String data) {
        if(calculateLoadFactor() > 0.7) {
            rehash();
        }
        if(insert(buckets, data)) currentSize++;
    }
    
    public void printTable()
    {
    	 for(Node node : buckets) {
             for(; node != null; node = node.next) {
                 System.out.println(node.data + "  " + node.count);
             }
         }
    }
    
  
    private boolean insert(Node[] buckets, String data) {
        int hash = hashString(data) % buckets.length;
        Node node = buckets[hash];
        if(node == null) {
            buckets[hash] = new Node(data);
            return true;
        } 
        else {
            while(node != null && !node.data.equals(data)) {
                node = node.next;
            }

            if(node == null) {
                buckets[hash] = new Node(data, buckets[hash]);
                return true;
            } 
            else {
                node.count++;
                return false;
            }
        }
    }
    
  
    private double calculateLoadFactor() {
        return (double) currentSize / (double) buckets.length;
    }

   
    private void rehash() {
        Node[] buckets = new Node[this.buckets.length * 2];
        for(Node node : this.buckets) {
            for(; node != null; node = node.next) {
                put(buckets, node.data, node.count);
            }
        }
        this.buckets = buckets;
    }
    
  
    private void put(Node[] buckets, String data, int count) {
        int hash = hashString(data) % buckets.length;
        Node node = buckets[hash];
        if(node != null) {
            while(node != null && !node.data.equals(data)) {
                node = node.next;
            }
            if(node != null) {
                node.count = count;
            } else {
                buckets[hash] = new Node(data, buckets[hash]);
                buckets[hash].count = count;
            }
        } else {
            buckets[hash] = new Node(data);
            buckets[hash].count = count;
        }

    }

 
    private int hashString(String str) {
        final int prime_multiplier = 17;
        int sum = 0;
        for(int i = 0; i < str.length(); i++) {
            sum += (str.charAt(i) * prime_multiplier * (i + 1)) + (i);
        }

        return sum;
    }
    

    private class Node {
        private String data;
        private int    count;
        private Node   next;

        public Node(String data) {
            this(data, null);
        }

        public Node(String data, Node next) {
            this.data = data;
            this.count = 1;
            this.next = next;
        }
    }


}