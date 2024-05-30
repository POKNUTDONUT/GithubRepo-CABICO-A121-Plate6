/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS101-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class CycleDetectionGraph {
    private int Vertex; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list

    public CycleDetectionGraph(int vertices) {
        Vertex = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w); // Add w to v's list.
        adjList[w].add(v); // Since the graph is undirected
    }

    // A utility function to detect cycle in a graph using DFS Depth First Search Algorithm
    private boolean isCyclicUtil(int v, boolean[] visited, int parent) {
        visited[v] = true;

        for (Integer i : adjList[v]) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v))
                    return true;
            } else if (i != parent) {
                return true;
            }
        }

        return false;
    }

    // Method to check if the graph contains a cycle
    public boolean isCyclic() {
        boolean[] visited = new boolean[Vertex];

        for (int u = 0; u < Vertex; u++) {
            if (!visited[u]) {
                if (isCyclicUtil(u, visited, -1))
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases = new ArrayList<>();

        // Test case 1: Graph with a cycle
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}
        });

        // Test case 2: Graph without a cycle
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 4}
        });

        // Test case 3: Single vertex (no edges)
        testCases.add(new int[][] {
                // No edges
        });

        // Test case 4: Disconnected graph with a cycle in one component
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 0}, {3, 4}
        });

        // Test case 5 (intentionally incorrect): Claims no cycle but has one
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}
        });

        int[] vertices = {5, 5, 1, 5, 6};

        for (int i = 0; i < testCases.size(); i++) {
            CycleDetectionGraph g = new CycleDetectionGraph(vertices[i]);
            for (int[] edge : testCases.get(i)) {
                g.addEdge(edge[0], edge[1]);
            }

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Has cycle: " + g.isCyclic());
            System.out.println();
        }
    }
}