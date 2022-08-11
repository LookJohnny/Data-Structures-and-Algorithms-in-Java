import java.util.Iterator;
import java.util.NoSuchElementException;

public class HomeWork5 {
    public static void main (String[] args) {
        int buckets = 5;
        double maxLoadFactor = 0.5;
        int resizeMultiplier = 2;

        ChainedHashTable<Integer, String> example = new ChainedHashTable<Integer, String>(buckets, maxLoadFactor, resizeMultiplier);
        try {
            //adds 10 elements and prints the information regarding it.
            example.insert(1, "One");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(2, "Two");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(3, "Three");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(4, "Four");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(5, "Five");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(6, "Six");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(7, "Seven");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(8, "Eight");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(9, "Nine");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(10, "Ten");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");
            example.insert(11, "Eleven");
            System.out.println("buckets " + example.getBuckets() + ", elements " + example.getSize() + ", lf " + example.getLoadFactor() + ", max lf 0.5, resize multiplier 2.0");

            //Look Up
            System.out.println(example.lookup(10));
            System.out.println(example.lookup(11));

        } catch (IllegalArgumentException | DuplicateKeyException e) {
            e.printStackTrace();
        }
    }
}

class SinglyLinkedList <E> {
    // An element in a linked list
    public class Element{
        private E data;
        private Element next;

        //Only allow SinglyLinkedList to construct Elements
        private Element(E data) {
            this.data = data;
            this.next = null;
        }

        public E getData() {
            return data;
        }

        public Element getNext() {
            return next;
        }

        private SinglyLinkedList getOwner() {
            return SinglyLinkedList.this;
        }
    }

    private Element head;
    private Element tail;
    private int size = 0;

    public Element getHead() {
        return head;
    }
    public Element getTail() {
        return tail;
    }
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public Element insertHead(E data) {
        Element newElement = new Element(data);
        if(isEmpty()) {
            //insert into empty list
            head = newElement;
            tail = newElement;
        }
        else {
            //Insert into non empty list
            newElement.next = head;
            head = newElement;
        }
        ++size;
        return newElement;
    }
    public Element insertTail(E data) {
        Element newElement = new Element(data);
        if(isEmpty()) {
            //insert into empty list
            head = newElement;
            tail = newElement;
        }
        else {
            //Insert into non empty list
            tail.next = newElement;
            tail = newElement;
        }
        ++size;
        return newElement;
    }
    public Element insertAfter(Element element, E data)
            throws IllegalArgumentException {
        if(element == null) {
            throw new IllegalArgumentException(
                    "Argument 'element' must not be null");
        }
        if(element.getOwner() != this) {
            throw new IllegalArgumentException(
                    "Argument 'element' does not belong to this list");
        }
        //insert new element
        Element newElement = new Element(data);
        if(tail == element) {
            //insert new tail
            element.next = newElement;
            tail = newElement;
        }
        else {
            //insert into middle of list
            newElement.next = element.next;
            element.next = newElement;
        }
        ++size;

        return newElement;
    }
    public E removeHead() throws NoSuchElementException {
        //check pre-conditions
        if(isEmpty()) {
            throw new NoSuchElementException ("Cannot remove from empty list");
        }

        //remove the head
        Element oldHead = head;
        if(size == 1) {
            //handles the removal of the last element
            head = null;
            tail = null;
        }
        else {
            head = head.next;
        }
        --size;
        return oldHead.data;
    }
    //Note that there is no removeTail. This cannot be implemented
    //efficiently because it would require O(n) to scan from head until
    //reaching the item_before_tail.
    public E removeAfter(Element element)
            throws IllegalArgumentException{
        //check preconditions
        if(element == null) {
            throw new IllegalArgumentException(
                    "Argument 'element' must not be null");
        }
        if(element.getOwner() != this) {
            throw new IllegalArgumentException(
                    "Argument 'element' does not belong to this list");
        }
        if(element.next == null) {
            throw new IllegalArgumentException(
                    "Argument 'element' must have a non-null next element");
        }

        //remove element
        Element elementToRemove = element.next;
        if(elementToRemove == tail) {
            //remove the tail
            element.next = null;
            tail = element;
        }
        else {
            element.next = elementToRemove.next;
        }
        --size;

        return elementToRemove.data;

    }
}

class ChainedHashTable<K, V> {
    //Table of buckets
    private SinglyLinkedList<KeyValuePair<K,V>>[] table;

    //Creates size, Max Load Factor, and Resize Factor
    private int size;
    //
    private double maxLoadFactor;
    private int resizeMultiplier;


    @SuppressWarnings("unchecked")
    public ChainedHashTable (int buckets, double maxLoadFactor, int resizeMultiplier) {
        // Create table of empty buckets
        table = new SinglyLinkedList[buckets];
        for(int i = 0; i < table.length; ++i) {
            table[i] = new SinglyLinkedList<KeyValuePair<K,V>>();
        }

        //set initial variables
        this.maxLoadFactor = maxLoadFactor;
        this.resizeMultiplier = resizeMultiplier;
        size = 0;
    }

    /**
     * Gets Size
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns whether size is zero
     * @return if the table is empty
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Inserts Keys and values as KeyValuePairs into table. Increases number of buckets when needed
     * @param key the key
     * @param value the value
     * @throws IllegalArgumentException if the key is null
     * @throws DuplicateKeyException if the key exists
     */
    @SuppressWarnings("unchecked")
    public void insert(K key, V value) throws
            IllegalArgumentException,
            DuplicateKeyException {
        //checks if key is null
        if(key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        //check if the key already exists
        if(contains(key)) {
            throw new DuplicateKeyException();
        }
        //inserts it into the proper bucket and increases size
        getBucket(key).insertHead(new KeyValuePair<K, V>(key, value));
        ++size;
        //Checks load factor
        if(getLoadFactor() >= maxLoadFactor) {
            //determines new amt of buckets
            int newBuckets = resizeMultiplier * table.length;
            //creates a temporary storage for key value pairs
            SinglyLinkedList<KeyValuePair<K,V>> temp= new SinglyLinkedList<KeyValuePair<K,V>>();
            // creates the keys iterator
            KeysIterator keys = new KeysIterator();
            //inserts all key value pairs into the new storages
            while(keys.hasNext()) {
                K tempKey = keys.next();
                V tempVal = lookup(tempKey);
                temp.insertHead(new KeyValuePair<K, V>(key, value));
            }
            //overrides the table with new larger array
            table = new SinglyLinkedList[newBuckets];

            for(int i = 0; i < table.length; ++i) {
                table[i] = new SinglyLinkedList<KeyValuePair<K,V>>();
            }
            //starts with the first element in storage and puts them into the new table
            SinglyLinkedList.Element elem = temp.getHead();
            while(!(elem == null)) {
                getBucket(key).insertHead((KeyValuePair<K, V>) elem.getData());
                elem = elem.getNext();
            }

        }
    }

    public double getLoadFactor() {
        //load factor is size divided by length
        return ((double)getSize() / (double)table.length);
    }
    public double getBuckets() {
        //gets number of buckets
        return table.length;
    }

    public V remove (K key) throws
            IllegalArgumentException,
            NoSuchElementException{
        if(key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        //if Empty Bucket
        SinglyLinkedList<KeyValuePair<K,V>> bucket = getBucket(key);
        if(bucket.isEmpty()) {
            throw new NoSuchElementException();
        }

        // If at head of Bucket
        SinglyLinkedList<KeyValuePair<K,V>>.Element elem = bucket.getHead();
        if(key.equals(elem.getData().getKey())) {
            --size;
            return bucket.removeHead().getValue();
        }
        //scan rest of buckets
        SinglyLinkedList<KeyValuePair<K,V>>.Element prev = elem;
        elem = elem.getNext();
        while(elem != null) {
            if(key.equals(elem.getData().getKey())) {
                --size;
                return bucket.removeAfter(prev).getValue();
            }
            prev = elem;
            elem = elem.getNext();
        }
        throw new NoSuchElementException();
    }

    public V lookup (K key) throws
            IllegalArgumentException,
            NoSuchElementException{
        if(key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        SinglyLinkedList<KeyValuePair<K,V>>.Element elem = getBucket(key).getHead();
        while(elem != null) {
            if(key.equals(elem.getData().getKey())) {
                return elem.getData().getValue();
            }
            elem = elem.getNext();
        }
        throw new NoSuchElementException();
    }

    public boolean contains(K key) {
        try {
            lookup(key);
        } catch (IllegalArgumentException ex) {
            return false;
        } catch (NoSuchElementException ex) {
            return false;
        }

        return true;
    }

    private SinglyLinkedList<KeyValuePair<K, V>> getBucket (K key) {
        //Division Method
        return table[(int) Math.floor((key.hashCode() * (Math.sqrt(5) - 1) ) % 1 * table.length)];
    }

    private class KeysIterator implements Iterator<K> {
        private int remaining;	// Number of keys remaining to iterate
        private int bucket;		// Bucket We're iterating
        private SinglyLinkedList<KeyValuePair<K, V>>.Element elem;
        // Position in bucket we're iterating

        public KeysIterator() {
            remaining = ChainedHashTable.this.size;
            bucket = 0;
            elem = ChainedHashTable.this.table[bucket].getHead();
        }

        public boolean hasNext() {
            return remaining > 0;
        }

        public K next() {
            if(hasNext()) {
                // If we've hit end of bucket, move to next non-empty bucket
                while (elem == null) {
                    elem = ChainedHashTable.this.table[++bucket].getHead();
                }

                // Get key
                K key = elem.getData().getKey();

                //Move to next element and decrement entries remaining
                elem = elem.getNext();
                --remaining;

                return key;
            } else {
                throw new NoSuchElementException();
            }

        }
    }

    public Iterable<K> keys(){
        return new Iterable<K>() {
            public Iterator<K> iterator() {
                return new KeysIterator();
            }
        };
    }

}

class DuplicateKeyException extends Exception {
    public DuplicateKeyException() {
        super("Duplicate Key Found");
    }
}

class KeyValuePair<K, V> {
    private K key;
    private V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}