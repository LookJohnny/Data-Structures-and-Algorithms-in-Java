import java.util.Comparator;
import java.util.Random;

public class Homework3 {
	//a
	static class CompareCarsByMakeThenModel implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			// TODO Auto-generated method stub
			//compare make and then model
			if(o1.make.compareTo(o2.make) == 0) {
				return o1.model.compareTo(o2.model);
			}
			return o1.make.compareTo(o2.make);
		}
	}
	//b
	static class CompareCarsByDescendingMPG implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			// TODO Auto-generated method stub
			//compare mpg by descending
			return (o2.mpg - o1.mpg);
		}
	}
	//c
	static class CompareCarsByMakeThenDescendingMPG implements Comparator<Car> {

		@Override
		public int compare(Car o1, Car o2) {
			// TODO Auto-generated method stub
			//comapre make then mpg(descending)
			if(o1.make.compareTo(o2.make) == 0) {
				return o2.mpg - o1.mpg;
			}
			return o1.make.compareTo(o2.make);
		}
	}
	//d
	public static void main(String[] args) {
		Car cars[] = {
				//array for cars
				new Car ("Toyota" ,"Camry", 33),
				new Car ("Ford" ,"Focus" , 40),
				new Car ("Honda" ,"Accord" ,34),
				new Car ("Ford" , "Mustang", 31),
				new Car ("Honda" ,"Civic" , 39),
				new Car ("Toyota", "Prius" , 48),
				new Car ("Honda" ,"Fit" ,35),
				new Car ("Toyota" ,"Corolla" ,35),
				new Car ("Ford" ,"Taurus" ,28),
		};
		//print out the original array for cars
		System.out.println(" Unsorted Array ");
		for(Car car : cars) {
			System.out.println(car);
		}
		//print out the array by make then model
		System.out.println(" Sorted By Make Then Model ");
		QuickSort.quickSort(cars, new CompareCarsByMakeThenModel());
		for(Car car : cars) {
			System.out.println(car);
		}
		//print out the array by descending MPG
		System.out.println("Sorted By Descending MPG");
		QuickSort.quickSort(cars, new CompareCarsByDescendingMPG());
		for(Car car : cars) {
			System.out.println(car);
		}
		//print out the array by make then descending MPG
		System.out.println("Sorted By Make Then Descending MPG");
		QuickSort.quickSort(cars, new CompareCarsByMakeThenDescendingMPG());
		for(Car car : cars) {
			System.out.println(car);
		}
	}
	
}
//source code for the note for QuickSort
	class QuickSort {
	    public static <T> void quickSort(
	            T[] array,
	            Comparator<? super T> comparator) {
	        quickSortRecursive(array, 0, array.length - 1, comparator);
	    }
	    private static <T> void quickSortRecursive(
	            T[] array,
	            int i,
	            int k,
	            Comparator<? super T> comparator) {

	        //Stop the recursion when it is not possible to partition further
	        if (i >= k) {
	            return;
	        }

	        // Determine where to partition the elements
	        int j = partition(array, i, k, comparator);

	        //Sort the left partititon
	        quickSortRecursive(array,i, j, comparator);

	        //Sort the right partition
	        quickSortRecursive(array, j+1, k, comparator);
	    }

	    private static <T> int partition(
	            T[] array,
	            int i,
	            int k,
	            Comparator<? super T> comparator) {

	        //Use the median-of-three method to find the partition value
	        T p = medianOfThree(array, i, k, comparator);

	        //Create two partitions around the partition value
	        --i;
	        ++k;
	        while (true) {
	            //move left until element is found in the wrong partition
	            do {
	                --k;
	            } while (comparator.compare(array[k], p) > 0);

	            //move right until element is found in the wrong partition
	            do {
	                ++i;
	            } while (comparator.compare(array[i], p) < 0);

	            if(i >= k) {
	                //stop partioning when the left and right indices cross
	                break;
	            } else {
	                //swap the elements at the indices
	                T temp = array[i];
	                array[i] = array[k];
	                array[k] = temp;
	            }
	        }

	        return k;
	    }

	    private static <T> T medianOfThree(
	            T[] array,
	            int i,
	            int k,
	            Comparator<? super T> comparator) {

	        //Get 3 random values from the array
	        Random random = new Random();
	        T a = array[random.nextInt(k - i + 1) + i];
	        T b = array[random.nextInt(k - i + 1) + i];
	        T c = array[random.nextInt(k - i + 1) + i];

	        //Return the median of the values
	        if(comparator.compare(a,b) > 0) {
	            if(comparator.compare(b, c) > 0) {
	                return b;
	            } else if(comparator.compare(a, c) > 0) {
	                return c;
	            } else {
	                return a;
	            }
	        } else {
	            if(comparator.compare (a, c) > 0) {
	                return a;
	            } else if (comparator.compare (b, c) > 0) {
	                return c;
	            } else {
	                return b;
	            }
	        }

	    }

	}
	//create a class named Car
class Car
{
    public String make;
    public String model;
    public int mpg;

    Car (String make, String model, int mpg){
        this.make = make;
        this.model = model;
        this.mpg = mpg;
    } // constructor

    @Override
    public String toString() {
        return String.format("%s %s (MPG: %d) ", make, model, mpg);    //Formats the string
    }
}