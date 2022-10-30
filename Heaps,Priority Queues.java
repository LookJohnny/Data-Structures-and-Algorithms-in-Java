import java.util.Comparator;

public class Homework7 {
	// a
	public static Person[] outputSorted(Person[] persons, Comparator<Person> comparator) {
		Heap<Person> personHeap = new Heap<Person>(comparator);
		//insert everyone to the personHeap
		for(Person person: persons) {
			personHeap.insert(person);
		}
		Person[] sortedPersons = new Person[persons.length];
		for(int i = 0; i < sortedPersons.length; ++i) {
			sortedPersons[i] = personHeap.extract();
		}
		
		return sortedPersons;
	}
	// b c d
	public static void main(String[] args) {
		Person person1 = new Person("Anni", 16, 5.8);
		Person person2 = new Person("Black", 19, 6.1);
		Person person3 = new Person("Cathy", 7, 3.6);
		Person person4 = new Person("Denny", 22, 6.0);
		Person person5 = new Person("Ethan", 32, 6.2);
		Person person6 = new Person("Frank", 28, 5.4);
		Person[] persons = {person1, person2, person3, person4, person5, person6};
		// b: by ascending name:
		System.out.println("By ascending name: ");
		persons = outputSorted(persons,new Comparator<Person> () {
					public int compare(Person o1, Person o2) {
						return o2.name.compareTo(o1.name);
					}
				}
			);
		for(Person person : persons) {
			System.out.println(person);
		}
		//c: by ascending age
		System.out.println("By ascending age: ");
		persons = outputSorted(persons,new Comparator<Person> () {
			public int compare(Person o1, Person o2) {
				return o2.age - o1.age;
			}
		}
	);
		for (Person person : persons) {
			System.out.println(person);
		}
		//d: by ascending height
		System.out.println("By ascending height: ");
		persons = outputSorted(persons,new Comparator<Person> () {
			public int compare(Person o1, Person o2) {
				if(o1.height - o2.height > 0) {
					return -1;
				} else if(o1.height - o2.height < 0) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	);
		for (Person person : persons) {
			System.out.println(person);
		}
	}
}

class Person{
	String name;
	int age;
	double height;
	
	public Person(String name, int age, double height) { //constructor
		this.name = name;
		this.age = age;
		this.height = height;
	}
	public String toString() {
		return String.format("Name: %s/ Age; %d/ Height: %s", name , age, height);
	}
	
}

class Heap<E> {
    Object[] tree;

    private Comparator<? super E> comparator;

    public Heap(Comparator <? super E> comparator) {
        this.comparator = comparator;
    }

    public int getSize() {
        return tree == null ? 0 : tree.length;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    @SuppressWarnings("unchecked")
    public void insert(E data) {
        //Grow tree by 1 to hold new node
        Object[] temp;
        if(tree == null) {
            temp = new Object[1];
        } else {
            temp = new Object[tree.length + 1];
            System.arraycopy(tree, 0, temp, 0, tree.length);
        }
        tree = temp;

        // Insert data as right-most node in last level
        tree[getSize() - 1] = data;

        // Push node upward to correct position
        int childIndex = getSize() - 1;
        int parentIndex = getParentIndex(childIndex);
        while(childIndex > 0
                && comparator.compare(
                (E) tree[parentIndex],
                (E)tree[childIndex]) < 0) {
            // Parent and child out of order, swap
            swapNodes(parentIndex, childIndex);

            // Move up one level in the tree
            childIndex = parentIndex;
            parentIndex = getParentIndex(parentIndex);
        }
    }

    @SuppressWarnings("unchecked")
    public E extract() throws IllegalStateException{
        if(isEmpty()) {
            throw new IllegalStateException(
                    "Cannot extract from empty heap");
        }

        //Extract data at top of heap
        E extracted = (E) tree[0];

        // If extracting last node, set tree to empty, return extracted data
        if(getSize() == 1) {
            tree = null;
            return extracted;
        }

        // Save and remove right-most node from last level
        E save = (E)tree[tree.length - 1];
        Object[] temp = new Object[tree.length - 1];
        System.arraycopy(tree, 0, temp, 0, tree.length - 1);
        tree = temp;

        // Set saved node as the new root
        tree[0] = save;

        // Push root down to correct position
        int parentIndex = 0;
        while(true) {
            int leftChildIndex = getLeftChildIndex(parentIndex);
            int rightChildIndex = getRightChildIndex(parentIndex);

            int maxIndex = parentIndex;
            if(leftChildIndex < tree.length && comparator.compare(
                    (E)tree[leftChildIndex], (E)tree[maxIndex]) > 0) {
                maxIndex = leftChildIndex;
            }
            if(rightChildIndex < tree.length && comparator.compare(
                    (E)tree[rightChildIndex], (E)tree[maxIndex]) > 0) {
                maxIndex = rightChildIndex;
            }

            // If parent is largest, root has been pushed to correct position
            if (parentIndex == maxIndex) {
                break;
            } else {
                // Parent and a child out of order, swap with larger child
                swapNodes(parentIndex, maxIndex);

                // Moce down one level in the tree
                parentIndex = maxIndex;
            }
        }
        return extracted;
    }

    private static int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private static int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private static int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private void swapNodes(int index1, int index2) {
        Object temp = tree[index1];
        tree[index1] = tree[index2];
        tree[index2] = temp;
    }  // swap

}
