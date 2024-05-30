/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS110-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class BipartiteGraph {
    private int Vertex; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list

    public BipartiteGraph(int vertices) {
        Vertex = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w); // Add w to v's list.
        adjList[w].add(v); // Since the graph is undirected
    }

    public boolean isBipartite() {
        int[] colors = new int[Vertex];
        Arrays.fill(colors, -1);

        for (int i = 0; i < Vertex; i++) {
            if (colors[i] == -1) {
                if (!isBipartiteUtil(i, colors)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Utility function for BFS to check bipartite nature ( breath first search algo )
    private boolean isBipartiteUtil(int src, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        colors[src] = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adjList[u]) {
                if (colors[v] == -1) {
                    colors[v] = 1 - colors[u];
                    queue.add(v);
                } else if (colors[v] == colors[u]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases = new ArrayList<>();

        // Test case 1: Simple bipartite graph
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}
        });

        // Test case 2: Non-bipartite graph (odd-length cycle)
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 0}
        });

        // Test case 3: Bipartite graph with two components
        testCases.add(new int[][] {
                {0, 1}, {2, 3}
        });

        // Test case 4: Single vertex (trivially bipartite)
        testCases.add(new int[][] {
                // No edges
        });

        // Test case 5 (intentionally incorrect): Claims bipartite but is not
        testCases.add(new int[][] {
                {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}
        });

        int[] vertices = {4, 3, 4, 1, 5};

        for (int i = 0; i < testCases.size(); i++) {
            BipartiteGraph g = new BipartiteGraph(vertices[i]);
            for (int[] edge : testCases.get(i)) {
                g.addEdge(edge[0], edge[1]);
            }

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Is bipartite: " + g.isBipartite());
            System.out.println();
        }
    }
}