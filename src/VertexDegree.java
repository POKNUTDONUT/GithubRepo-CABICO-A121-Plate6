/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS101-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class VertexDegree {
    private int Vertex; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list

    public VertexDegree(int vertices) {
        Vertex = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w); // Add w to v's list.
        adjList[w].add(v); // Since the graph is undirected
    }

    public int[] calculateDegrees() {
        int[] degrees = new int[Vertex];
        for (int i = 0; i < Vertex; i++) {
            degrees[i] = adjList[i].size();
        }
        return degrees;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases = new ArrayList<>();

        // Test case 1: Simple graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 4}
        });

        // Test case 2: Graph with multiple edges
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 0}
        });

        // Test case 3: Single vertex (no edges)
        testCases.add(new int[][] {
                // No edges
        });

        // Test case 4: Disconnected graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {3, 4}
        });

        // Test case 5 (intentionally incorrect): Incorrect degree calculation
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}
        });

        int[] vertices = {5, 5, 1, 5, 4}; // Number of vertices for each test case

        for (int i = 0; i < testCases.size(); i++) {
            VertexDegree g = new VertexDegree(vertices[i]);
            for (int[] edge : testCases.get(i)) {
                g.addEdge(edge[0], edge[1]);
            }

            System.out.println("Test case " + (i + 1) + ":");
            int[] degrees = g.calculateDegrees();
            for (int j = 0; j < degrees.length; j++) {
                System.out.println("Vertex " + j + " degree: " + degrees[j]);
            }
            System.out.println();
        }
    }
}