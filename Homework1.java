import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.Random;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Homework1 {
	// performance o(n)
	// array The original array of ints.
	// index The location of where the new value will be inserted
	// value The value to be inserted.

	static int[] insert(int[] array, int index, int value) { // o(n)
		if (array == null || array.length == 0) { // o(n)
			if (index == 0) { // o(1)
				return new int[] { value }; // o(1)
			} else { // o(1)
				return null; // o(1)
			}
		}
		int[] newArray = new int[array.length + 1]; // o(1)
		int j = 0; // o(1)

		for (int i = 0; i < array.length + 1; i++) { // o(n)
			if (index == i) { // o(1)
				newArray[i] = value; // o(1)
			} else { // o(1)
				newArray[i] = array[j++]; // o(1)
			}
		}
		return newArray; // o(1)
	}

	private static final int NUM_READINGS = 60;
	private static final int INSERTS_PER_READING = 1000;

	public static void main(String[] args) {
		System.out.println("Array length\tSeconds per insert");
		int[] array = new int[0];
		for (int i = 0; i < NUM_READINGS; i++) {
			Random random = new Random();
			long startTime = System.nanoTime();
			for (int j = 0; j < INSERTS_PER_READING; j++) {
				int index = array.length == 0 ? 0 : random.nextInt(array.length);
				array = Homework1.insert(array, index, random.nextInt());
			}
			long stopTime = System.nanoTime();
			double timePerInsert = (stopTime - startTime) / 1000000000.0 / INSERTS_PER_READING;
			System.out.printf("%d\t\t\t%f/n", array.length, timePerInsert);
		}
	}
}