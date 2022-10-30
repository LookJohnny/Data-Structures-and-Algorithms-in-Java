import java.util.NoSuchElementException;
/**
 * This is an algorithm that uses stack to add numbers of any size.
 * @author yangliu
 *
 */

public class Homework4 {

    public void addLargeNumbers(String num1, String num2) {

    	Stack<Integer> stack1 = new Stack<Integer>();
    	//declare a new stack called stack1, this stack is used to hold the num1 string into single elements.
    	
    	for(char numerals : num1.toCharArray()) {
    	// each elements of the num1 as a CharArray is assigned to a char named numerals.
    		stack1.push(Character.getNumericValue(numerals));
    	//insert an head elements into stack1
    		//the elements is the single char from the num1, string into single element char.
    	}
    	
    	Stack<Integer> stack2 = new Stack<Integer>();
    	//declare a new stack called stack2. this stack is used to hold the num2.
    	for(char numerals2 : num2.toCharArray()) {
    	// each elements of the num2 as a CharArray is assigned to char named numerals.
    		stack2.push(Character.getNumericValue(numerals2));
    		//insert an head elements into stack2.
    		//the elements is the single char from the num2, string into single element char.
    	}
    	int result = 0;
    	Stack<Integer> stack3 = new Stack<Integer>();
    	while(!stack1.isEmpty() || !stack2.isEmpty() || result != 0) {
    		//when stack1 , stack2 is not empty, and result != 0,
    		if(!stack1.isEmpty()) {
    			//if stack1 is not empty.
    			result += stack1.pop();
    			//put and add the next elements from the stacks into result.
    		}
    		if(!stack2.isEmpty()) {
    			//stack2 is not empty.
    			result += stack2.pop();
    			//add the next element into result.
    		}
    		stack3.push(result % 10); 
    		//stack3 holds the number for result, push means add from the head.
    		result = result / 10;
    		//the number that need to be add in next loop.
    	}
    	while(!stack3.isEmpty()) {
    		//while stack3 is not empty
    		System.out.print(stack3.pop());
    		//print out stack 3 as the last-in, first out order.
    	}
    	System.out.println();
    	//next line,just for a better output.
    }
/*
 * test
 */
    public static void main (String[] args) {
        //Example One
        Homework4 homework4 = new Homework4();
        homework4.addLargeNumbers("1234567890987654321","1234567890987654321");
        //Example Two
        homework4.addLargeNumbers("46374899271846574930192837465818274618294756102846381974600011928474","14326214599987654890006521476897531679075786428097888900");
        //Example Three
        homework4.addLargeNumbers("12345329482946876868768768768768768768689403092832843298578","73285798372985729579832759879898732383294829432843298578");
    }


}


class Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<E>();

    public void push (E data) {
        list.insertHead(data);
    }

    public E pop() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.removeHead();
    }

    public E peek() throws NoSuchElementException{
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.getHead().getData();
    }

    public int getSize() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}

class Queue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<E>();

    public void enqueue(E data) {
        list.insertTail(data);
    }

    public E dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.removeHead();
    }

    public E peek() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.getHead().getData();
    }

    public int getSize() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.isEmpty();
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

        private SinglyLinkedList<E> getOwner() {
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
        //check per-conditions
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
