/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS110-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class AdjacencyMatrixGraph {
    private int Vertex; // Number of vertices
    private int[][] adjMatrix; // Adjacency matrix

    public AdjacencyMatrixGraph(int vertices) {
        Vertex = vertices;
        adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int v, int w, boolean directed) {
        adjMatrix[v][w]++;
        if (!directed) {
            adjMatrix[w][v]++;
        }
    }

    public void printAdjMatrix() {
        for (int i = 0; i < Vertex; i++) {
            for (int j = 0; j < Vertex; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases = new ArrayList<>();

        // Test case 1: Simple undirected graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}
        });

        // Test case 2: Graph with multiple edges and loops
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 0}, {0, 0}, {1, 1}, {2, 2}
        });

        // Test case 3: Directed graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}, {2, 0}
        });

        // Test case 4: Disconnected graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {3, 4}
        });

        // Test case 5 (intentionally incorrect): Claims a directed edge but input is undirected
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}, {1, 3}
        });

        boolean[] directedFlags = {false, false, true, false, true};

        for (int i = 0; i < testCases.size(); i++) {
            int maxVertex = 0;
            for (int[] edge : testCases.get(i)) {
                maxVertex = Math.max(maxVertex, Math.max(edge[0], edge[1]));
            }
            AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(maxVertex + 1);
            for (int[] edge : testCases.get(i)) {
                g.addEdge(edge[0], edge[1], directedFlags[i]);
            }

            System.out.println("Test case " + (i + 1) + ":");
            g.printAdjMatrix();
            System.out.println();
        }
    }
}