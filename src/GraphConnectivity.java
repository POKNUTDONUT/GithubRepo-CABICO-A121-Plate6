/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS110-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class GraphConnectivity {
    private int Vertex; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list

    public GraphConnectivity(int vertices) {
        Vertex = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w); // Add w to v's list.
        adjList[w].add(v); // Since the graph is undirected
    }

    // A function used by Depth First Search Algorithm
    private void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recursion for all the vertices adjacent to this vertex
        for (int n : adjList[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[Vertex];

        // Find all reachable vertices from the first vertex
        DFSUtil(0, visited);

        // Check if all vertices are visited
        for (boolean v : visited) {
            if (!v)
                return false;
        }
        return true;
    }

    public int countConnectedComponents() {
        boolean[] visited = new boolean[Vertex];
        int count = 0;

        for (int v = 0; v < Vertex; ++v) {
            if (!visited[v]) {
                DFSUtil(v, visited);
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases = new ArrayList<>();

        // Test case 1: Connected graph
        testCases.add(new int[][] { {0, 1}, {1, 2}, {2, 3}, {3, 4} });

        // Test case 2: Disconnected graph (two components)
        testCases.add(new int[][] { {0, 1}, {1, 2}, {3, 4} });

        // Test case 3: Single vertex (connected)
        testCases.add(new int[][] { });

        // Test case 4: Disconnected graph (three components)
        testCases.add(new int[][] { {0, 1}, {2, 3}, {4, 5} });

        // Test case 5 (intentionally incorrect): Disconnected graph (claims to be connected)
        testCases.add(new int[][] { {0, 1}, {1, 2}, {3, 4}, {4, 5} });

        int[] vertices = {5, 5, 1, 6, 6};

        for (int i = 0; i < testCases.size(); i++) {
            GraphConnectivity g = new GraphConnectivity(vertices[i]);
            for (int[] edge : testCases.get(i)) {
                g.addEdge(edge[0], edge[1]);
            }

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Is connected: " + g.isConnected());
            if (!g.isConnected()) {
                System.out.println("Number of connected components: " + g.countConnectedComponents());
            }
            System.out.println();
        }
    }
}