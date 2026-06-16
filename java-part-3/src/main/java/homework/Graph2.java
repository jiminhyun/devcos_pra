package homework;

import java.util.LinkedList;

public class Graph2 {

    LinkedList<Integer>[] adjacencyList;

    public Graph2(int vertex) {
        adjacencyList = new LinkedList[vertex+1];
        for (int i = 0; i < vertex+1 ; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public LinkedList<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }

    public void addEdge(int vertex1, int vertex2) {
        adjacencyList[vertex1].add(vertex2);
        adjacencyList[vertex2].add(vertex1);
    }

    public void printGraph() {
        System.out.println();
        for (int i = 1; i < adjacencyList.length; i++) {
            System.out.print("Vertex " +i + " : ");
            for(int j: adjacencyList[i]) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
