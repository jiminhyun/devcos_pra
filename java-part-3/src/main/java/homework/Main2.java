package homework;

import java.util.*;

public class Main2 {
    static int inputVertex = 9;
    public static void dfs(Graph graph, int startVertex) {// 스택 정방향 호출
        boolean[] visited = new boolean[inputVertex + 1];
        visited[startVertex] = true;
        Stack<Integer> stack = new Stack<>();
        stack.add(startVertex);
        while (!stack.isEmpty()) {
            int visitVertex = stack.pop();
            System.out.print(visitVertex + " ");

            for (int i : graph.getAdjacencyList()[visitVertex]) {
                if (!visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                }
            }
        }
    }

    public static void dfs2(Graph graph, int startVertex, boolean[] visited) {// 재귀 호출
        visited[startVertex] = true;
        System.out.print(startVertex + " ");
        for (int i : graph.getAdjacencyList()[startVertex]) {
            if (!visited[i]) {
                dfs2(graph, i, visited);
            }
        }
    }

    static void main(String[] args) {
        boolean[] visited2 = new boolean[inputVertex + 1];
        Graph graph = new Graph(inputVertex);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 5);
        graph.addEdge(4, 7);
        graph.addEdge(4, 8);
        graph.addEdge(5, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        dfs(graph, 1);
        graph.printGraph();
        dfs2(graph,1, visited2);
    }
}
