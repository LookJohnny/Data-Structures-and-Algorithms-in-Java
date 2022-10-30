import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Homework8 {
    static Boolean isReachable(Graph<Character, Character> graph,char entrance, char exit)
    {
        //list of all visited characters
        ArrayList<Character> visited = new ArrayList<Character>();
        //add entrance
        visited.add(entrance);
        //create a queue and add entrance
        Queue<Graph<Character, Character>.Vertex> queue =
                new Queue<Graph<Character, Character>.Vertex>();
        queue.enqueue(graph.getVertex(entrance));

        //loop until queue is empty
        while (!queue.isEmpty()) {
            //dequeue on vertex
            Graph<Character, Character>.Vertex vertex = queue.dequeue();
            //loop through the edges that start at the vertex
            for(Graph<Character, Character>.Edge edge : vertex.getEdgesIncidentFrom()) {
                //look at where it goes to
                Graph<Character, Character>.Vertex adjacentVertex = edge.getTo();
                //if its the exit return true
                if(adjacentVertex.getData().equals(exit)) {
                    return true;
                    //else if you havent visited add it to the list of visited and add it to the queue
                } else if(!visited.contains(adjacentVertex.getData())) {
                    visited.add(adjacentVertex.getData());
                    queue.enqueue(adjacentVertex);
                }
            }
        }
        return false;
    }

    public static void main(String args[]) throws
            NullPointerException,
            IllegalStateException,
            IllegalArgumentException,
            DuplicateElementException	{
        //entrance and exit that we are using
        char entrance = 'A';
        char exit = 'G';
        // Create a graph given in the first diagram
        Graph<Character, Character> positiveTest = new Graph<Character, Character>();
        //insert vertices
        positiveTest.insertVertex('A');
        positiveTest.insertVertex('B');
        positiveTest.insertVertex('C');
        positiveTest.insertVertex('D');
        positiveTest.insertVertex('E');
        positiveTest.insertVertex('F');
        positiveTest.insertVertex('G');
        //insert edges both ways because there is no direction
        positiveTest.insertEdge('A', 'C', ' ');
        positiveTest.insertEdge('C', 'A', ' ');
        positiveTest.insertEdge('A', 'D', ' ');
        positiveTest.insertEdge('D', 'A', ' ');
        positiveTest.insertEdge('B', 'D', ' ');
        positiveTest.insertEdge('D', 'B', ' ');
        positiveTest.insertEdge('D', 'E', ' ');
        positiveTest.insertEdge('E', 'D', ' ');
        positiveTest.insertEdge('D', 'G', ' ');
        positiveTest.insertEdge('G', 'D', ' ');
        positiveTest.insertEdge('C', 'F', ' ');
        positiveTest.insertEdge('F', 'C', ' ');
        positiveTest.insertEdge('F', 'G', ' ');
        positiveTest.insertEdge('G', 'F', ' ');

        System.out.println("Positive Test Path is Reachable:" + isReachable(positiveTest,entrance,exit));
        // Create a graph given in the second diagram
        Graph<Character, Character> negativeTest = new Graph<Character, Character>();
        // Create a graph given in the above diagram
        negativeTest.insertVertex('A');
        negativeTest.insertVertex('B');
        negativeTest.insertVertex('C');
        negativeTest.insertVertex('D');
        negativeTest.insertVertex('E');
        negativeTest.insertVertex('F');
        negativeTest.insertVertex('G');
        //insert edges both ways because there is no direction
        negativeTest.insertEdge('A', 'C', ' ');
        negativeTest.insertEdge('C', 'A', ' ');
        negativeTest.insertEdge('A', 'D', ' ');
        negativeTest.insertEdge('D', 'A', ' ');
        negativeTest.insertEdge('B', 'D', ' ');
        negativeTest.insertEdge('D', 'B', ' ');
        negativeTest.insertEdge('C', 'F', ' ');
        negativeTest.insertEdge('F', 'C', ' ');


        System.out.println("Negative Test Path is Reachable:"  + isReachable(negativeTest,entrance,exit));

    }
}

