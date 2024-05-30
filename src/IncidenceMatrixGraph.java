/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS101-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class IncidenceMatrixGraph {
    private int Vertex; // Number of vertices
    private List<int[]> edges; // List of edges with their multiplicities

    public IncidenceMatrixGraph(int vertices) {
        Vertex = vertices;
        edges = new ArrayList<>();
    }

    public void addEdge(int v, int w, int count) {
        for (int i = 0; i < count; i++) {
            edges.add(new int[]{v, w});
        }
    }

    public int[][] constructIncidenceMatrix() {
        int E = edges.size();
        int[][] incidenceMatrix = new int[Vertex][E];

        for (int j = 0; j < E; j++) {
            int[] edge = edges.get(j);
            incidenceMatrix[edge[0]][j] = 1;
            incidenceMatrix[edge[1]][j] = 1;
        }

        return incidenceMatrix;
    }

    public void printIncidenceMatrix(int[][] incidenceMatrix) {
        for (int[] row : incidenceMatrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases = new ArrayList<>();
        List<int[]> multiplicities = new ArrayList<>();

        // Test case 1: Simple undirected graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}
        });
        multiplicities.add(new int[]{1, 1, 1, 1});

        // Test case 2: Graph with multiple edges
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 0}
        });
        multiplicities.add(new int[]{2, 1, 1, 1, 1, 1});

        // Test case 3: Single vertex (no edges)
        testCases.add(new int[][] {
                // No edges
        });
        multiplicities.add(new int[]{});

        // Test case 4: Disconnected graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {3, 4}
        });
        multiplicities.add(new int[]{1, 1, 1});

        // Test case 5 (intentionally incorrect): Claims incorrect multiplicity
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}, {1, 3}
        });
        multiplicities.add(new int[]{1, 1, 1, 1, 3});

        int[] vertices = {4, 5, 1, 5, 4};

        for (int i = 0; i < testCases.size(); i++) {
            IncidenceMatrixGraph g = new IncidenceMatrixGraph(vertices[i]);
            int[][] edges = testCases.get(i);
            int[] counts = multiplicities.get(i);
            for (int j = 0; j < edges.length; j++) {
                g.addEdge(edges[j][0], edges[j][1], counts[j]);
            }

            System.out.println("Test case " + (i + 1) + ":");
            int[][] incidenceMatrix = g.constructIncidenceMatrix();
            g.printIncidenceMatrix(incidenceMatrix);
            System.out.println();
        }
    }
}
