/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS101-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class GraphFromAdjMatrix {
    private int Vertex; // Number of vertices
    private int[][] adjMatrix; // Adjacency matrix

    public GraphFromAdjMatrix(int[][] matrix) {
        Vertex = matrix.length;
        adjMatrix = matrix;
    }

    public Map<String, Integer> listEdges() {
        Map<String, Integer> edgeCount = new HashMap<>();

        for (int i = 0; i < Vertex; i++) {
            for (int j = i; j < Vertex; j++) {
                if (adjMatrix[i][j] > 0) {
                    String edge = i + "-" + j;
                    edgeCount.put(edge, adjMatrix[i][j]);
                }
            }
        }

        return edgeCount;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases = new ArrayList<>();

        // Test case 1: Simple graph
        testCases.add(new int[][] {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0}
        });

        // Test case 2: Graph with multiple edges
        testCases.add(new int[][] {
                {0, 2, 0, 0, 0},
                {2, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 3},
                {0, 0, 0, 3, 0}
        });

        // Test case 3: Single vertex (no edges)
        testCases.add(new int[][] {
                {0}
        });

        // Test case 4: Graph with loops (self-edges)
        testCases.add(new int[][] {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        });

        // Test case 5 (intentionally incorrect): Incorrect adjacency matrix (claims edges that don't exist)
        testCases.add(new int[][] {
                {0, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        });

        for (int i = 0; i < testCases.size(); i++) {
            GraphFromAdjMatrix graph = new GraphFromAdjMatrix(testCases.get(i));
            Map<String, Integer> edges = graph.listEdges();

            System.out.println("Test case " + (i + 1) + ":");
            for (Map.Entry<String, Integer> entry : edges.entrySet()) {
                System.out.println("Edge: " + entry.getKey() + ", Count: " + entry.getValue());
            }
            System.out.println();
        }
    }
}
