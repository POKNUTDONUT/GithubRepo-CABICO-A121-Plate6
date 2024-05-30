/*
CABICO, Karsten Gabriel L.
BSCS - A121
CS110-2: Discrete Structures 2
9:30AM - 10:45AM (MWF)
Week 8 (May 19-26, 2024)
Plate #6: Representing Graphs, Graph Isomorphism and Connectivity
*/
import java.util.*;

public class GraphIsomorphism {
    private static class Graph {
        int V;
        List<List<Integer>> adjList;

        public Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int v, int w) {
            adjList.get(v).add(w);
            adjList.get(w).add(v);
        }

        public List<Integer> getAdjList(int v) {
            return adjList.get(v);
        }
    }

    public static boolean areIsomorphic(Graph g1, Graph g2) {
        if (g1.V != g2.V) return false;

        int[] degreeSequence1 = getDegreeSequence(g1);
        int[] degreeSequence2 = getDegreeSequence(g2);

        Arrays.sort(degreeSequence1);
        Arrays.sort(degreeSequence2);

        if (!Arrays.equals(degreeSequence1, degreeSequence2)) return false;

        boolean[] visited1 = new boolean[g1.V];
        boolean[] visited2 = new boolean[g2.V];

        return isIsomorphicUtil(g1, g2, visited1, visited2, new HashMap<>(), 0);
    }

    private static int[] getDegreeSequence(Graph g) {
        int[] degrees = new int[g.V];
        for (int i = 0; i < g.V; i++) {
            degrees[i] = g.getAdjList(i).size();
        }
        return degrees;
    }

    private static boolean isIsomorphicUtil(Graph g1, Graph g2, boolean[] visited1, boolean[] visited2, Map<Integer, Integer> mapping, int v) {
        if (v == g1.V) return true;

        for (int i = 0; i < g2.V; i++) {
            if (!visited2[i] && canMap(g1, g2, mapping, v, i)) {
                mapping.put(v, i);
                visited1[v] = true;
                visited2[i] = true;

                if (isIsomorphicUtil(g1, g2, visited1, visited2, mapping, v + 1)) {
                    return true;
                }

                mapping.remove(v);
                visited1[v] = false;
                visited2[i] = false;
            }
        }
        return false;
    }

    private static boolean canMap(Graph g1, Graph g2, Map<Integer, Integer> mapping, int v1, int v2) {
        for (int adj : g1.getAdjList(v1)) {
            if (mapping.containsKey(adj) && !g2.getAdjList(v2).contains(mapping.get(adj))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[][]> testCases1 = new ArrayList<>();
        List<int[][]> testCases2 = new ArrayList<>();

        // Test case 1: Isomorphic graphs
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 0}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {2, 0}});

        // Test case 2: Non-isomorphic graphs (different structure)
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 3}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {0, 2}});

        // Test case 3: Isomorphic graphs (different labeling)
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}});
        testCases2.add(new int[][] {{0, 2}, {2, 3}, {3, 1}, {1, 0}});

        // Test case 4: Non-isomorphic graphs (different number of vertices)
        testCases1.add(new int[][] {{0, 1}, {1, 2}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {2, 3}});

        // Test case 5: Isomorphic graphs (one self-loop and same structure)
        testCases1.add(new int[][] {{0, 1}, {1, 2}, {2, 0}, {2, 2}});
        testCases2.add(new int[][] {{0, 1}, {1, 2}, {2, 0}, {1, 1}});

        for (int i = 0; i < testCases1.size(); i++) {
            int V1 = Arrays.stream(testCases1.get(i)).flatMapToInt(Arrays::stream).max().orElse(0) + 1;
            int V2 = Arrays.stream(testCases2.get(i)).flatMapToInt(Arrays::stream).max().orElse(0) + 1;

            Graph g1 = new Graph(V1);
            Graph g2 = new Graph(V2);

            for (int[] edge : testCases1.get(i)) {
                g1.addEdge(edge[0], edge[1]);
            }

            for (int[] edge : testCases2.get(i)) {
                g2.addEdge(edge[0], edge[1]);
            }

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Are isomorphic: " + areIsomorphic(g1, g2));
            System.out.println();
        }
    }
}