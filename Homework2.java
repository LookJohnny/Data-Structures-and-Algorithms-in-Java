import java.util.NoSuchElementException;

public class Homework2 {
    public static void main(String[] args) {
        Homework2 hw2 = new Homework2();
//		SinglyLinkedList<Double> polynomial = new SinglyLinkedList();
//		appendTerm(polynomial, 1.0);
//		appendTerm(polynomial, 1.0);
//		display(polynomial);

//      Arrays a = new

//       D1
        SinglyLinkedList<Double> polynomial = new SinglyLinkedList<Double>();
//      the terms that needs to be tested.
        appendTerm(polynomial, 1.0);
        appendTerm(polynomial, 1.0);
//        appendTerm(polynomial, 0.0);
//      displays
        display(polynomial);
        System.out.println(evaluate(polynomial, 1.0) == 2.0);

//       D2
        SinglyLinkedList<Double> polynomial2 = new SinglyLinkedList<Double>();
//      adds the needed terms
        appendTerm(polynomial2, 1.0);
        appendTerm(polynomial2, 0.0);
        appendTerm(polynomial2, -1.0);
//      displays polynomial
        display(polynomial2);
        System.out.println(evaluate(polynomial2, 2.03) == 3.0);

//       D3
        SinglyLinkedList<Double> polynomial3 = new SinglyLinkedList<Double>();
//      adds needed terms
        appendTerm(polynomial3, -3.0);
        appendTerm(polynomial3, 0.5);
        appendTerm(polynomial3, -2.0);
        appendTerm(polynomial3, 0.0);
        display(polynomial3);
        System.out.println(evaluate(polynomial3, 05.0) == -82.5);
//       D4
        SinglyLinkedList<Double> polynomial4 = new SinglyLinkedList<Double>();
//      adds needed terms
        appendTerm(polynomial4, -0.3125);
        appendTerm(polynomial4, 0.0);
        appendTerm(polynomial4, -9.915);
        appendTerm(polynomial4, -7.75);
        appendTerm(polynomial4, -40.0);
//      displays polynomial
        display(polynomial4);
//      make sure it evaluates to the right value
        System.out.println(evaluate(polynomial4, 123.45) == -309.64);
    }
    //	creat a SinglyLinkedList called polynomial,and coefficient data type is double.
    static void appendTerm(SinglyLinkedList<Double> polynomial, Double coefficient) {

        if (polynomial.isEmpty()) {
            polynomial.insertTail(coefficient);
        } else {
            polynomial.insertTail(coefficient);

//same thing 		polynomial.insertAfter(polynomial.getTail(), coefficient);
        }

    }


    static void display(SinglyLinkedList<Double> polynomial) {
//         get the size of polynomial
        int polynomialSize = polynomial.getSize() - 1;
        SinglyLinkedList.Element coefficient = polynomial.getHead();
//         we need to get the coefficient of the polynomial
        double coefficientValue = (double) coefficient.getData();
        for (int i = polynomialSize; i >= 0; i--) {

            if ((double) coefficient.getData() != 0) {
                if (i != polynomialSize) {
//                     print the operating symbol
                    if ((double) coefficient.getData() > 0) {
                        System.out.print(" + ");
                    } else {
                        System.out.print(" - ");
                    }
//                    take the absolute value of the data because negatives are accounted for above
                    coefficientValue = Math.abs((double) coefficient.getData());
                }
                if (coefficientValue != 1 || i == 0) {
//                     if i == 0, we dont print the x
                    System.out.print(coefficientValue);
                }
//                 if i == 1, we just print x without ^
                if (i == 1) {
                    System.out.print("x");
                } else if (i != 0) {
                    System.out.print("x^" + i);
                }
            }
            coefficient = coefficient.getNext();
        }
        System.out.println();
    }


    static Double evaluate(SinglyLinkedList<Double> polynomial, Double x) {
    	double result = 0.0;
    	int polynomialSize = polynomial.getSize() - 1;
    	SinglyLinkedList.Element cofficient = polynomial.getHead();
    	for (int i = polynomialSize; i >= 0; i--) {
    		result += ((double)cofficient.getData() * Math.pow(polynomialSize, i));
    		cofficient = cofficient.getNext();
    	}
    	return result;
    }

}
