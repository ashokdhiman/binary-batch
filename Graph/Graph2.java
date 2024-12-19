package Graph;

import java.util.*;

public class Graph2 {

    // Question 1: Detect Cycle in a Graph using DFS
    private static boolean detectCycleDFS(int node, boolean[] visited, boolean[] recStack, List<List<Integer>> graph) {
        if (recStack[node]) return true;
        if (visited[node]) return false;

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : graph.get(node)) {
            if (detectCycleDFS(neighbor, visited, recStack, graph)) {
                return true;
            }
        }

        recStack[node] = false;
        return false;
    }

    public static boolean hasCycle(List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i] && detectCycleDFS(i, visited, recStack, graph)) {
                return true;
            }
        }
        return false;
    }

    // Question 2: Check if the graph is a Directed Acyclic Graph (DAG)
    public static boolean isDAG(List<List<Integer>> graph, int n) {
        return !hasCycle(graph, n);
    }

    // Question 3: Find the least-cost path from a source to a destination with exactly m edges
    public static int findLeastCostPath(List<List<int[]>> graph, int src, int dest, int m) {
        int[][] dp = new int[m + 1][graph.size()];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][src] = 0;

        for (int edges = 1; edges <= m; edges++) {
            for (int node = 0; node < graph.size(); node++) {
                if (dp[edges - 1][node] != Integer.MAX_VALUE) {
                    for (int[] edge : graph.get(node)) {
                        int neighbor = edge[0];
                        int weight = edge[1];
                        dp[edges][neighbor] = Math.min(dp[edges][neighbor], dp[edges - 1][node] + weight);
                    }
                }
            }
        }

        return dp[m][dest] == Integer.MAX_VALUE ? -1 : dp[m][dest];
    }

    // Question 4: Find total number of routes from source to destination with exactly m edges
    public static int findRoutes(List<List<Integer>> graph, int src, int dest, int m) {
        int[][] dp = new int[m + 1][graph.size()];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], 0);
        }
        dp[0][src] = 1;

        for (int edges = 1; edges <= m; edges++) {
            for (int node = 0; node < graph.size(); node++) {
                for (int neighbor : graph.get(node)) {
                    dp[edges][neighbor] += dp[edges - 1][node];
                }
            }
        }

        return dp[m][dest];
    }

    // Question 5: Check whether the directed graph has an Eulerian path or not
    public static boolean hasEulerianPath(List<List<Integer>> graph, int n) {
        int oddDegreeCount = 0;
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int neighbor : graph.get(i)) {
                outDegree[i]++;
                inDegree[neighbor]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (Math.abs(outDegree[i] - inDegree[i]) > 1) {
                return false;
            }
            if (outDegree[i] - inDegree[i] == 1) oddDegreeCount++;
            if (inDegree[i] - outDegree[i] == 1) oddDegreeCount++;
        }

        return oddDegreeCount == 0 || oddDegreeCount == 2;
    }

    public static void main(String[] args) {
        // Example for Graph1 and Graph2 (Example graph for testing purposes)

        // Example for Detect Cycle
        List<List<Integer>> graph1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) graph1.add(new ArrayList<>());
        graph1.get(0).add(1);
        graph1.get(1).add(2);
        graph1.get(2).add(3);
        graph1.get(3).add(0);  // Adding cycle here (0 -> 1 -> 2 -> 3 -> 0)

        System.out.println("Cycle Detected in Graph1: " + hasCycle(graph1, 4));

        // Example for DAG Check
        System.out.println("Graph1 is a DAG: " + isDAG(graph1, 4));

        // Example for Least Cost Path with m edges
        List<List<int[]>> weightedGraph = new ArrayList<>();
        for (int i = 0; i < 5; i++) weightedGraph.add(new ArrayList<>());
        weightedGraph.get(0).add(new int[]{1, 2});
        weightedGraph.get(1).add(new int[]{2, 3});
        weightedGraph.get(2).add(new int[]{3, 1});
        weightedGraph.get(3).add(new int[]{4, 4});

        System.out.println("Least cost path from 0 to 3 with exactly 3 edges: " + findLeastCostPath(weightedGraph, 0, 3, 3));

        // Example for Total Routes with m edges
        List<List<Integer>> simpleGraph = new ArrayList<>();
        for (int i = 0; i < 5; i++) simpleGraph.add(new ArrayList<>());
        simpleGraph.get(0).add(1);
        simpleGraph.get(1).add(2);
        simpleGraph.get(2).add(3);
        simpleGraph.get(0).add(3);

        System.out.println("Total routes from 0 to 3 with exactly 3 edges: " + findRoutes(simpleGraph, 0, 3, 3));

        // Example for Eulerian Path Check
        List<List<Integer>> eulerGraph = new ArrayList<>();
        for (int i = 0; i < 6; i++) eulerGraph.add(new ArrayList<>());
        eulerGraph.get(0).add(1);
        eulerGraph.get(1).add(2);
        eulerGraph.get(2).add(3);
        eulerGraph.get(3).add(0);
        eulerGraph.get(3).add(4);
        eulerGraph.get(4).add(5);
        eulerGraph.get(5).add(3);

        System.out.println("Eulerian path exists in the graph: " + hasEulerianPath(eulerGraph, 6));
    }
}
