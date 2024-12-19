package Graph;
import java.util.*;

public class Graph1 {

    // Q1. Check if the graph is bipartite
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1); // -1 means uncolored

        for (int i = 0; i < n; i++) {
            if (color[i] == -1 && !dfsBipartite(graph, color, i, 0)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfsBipartite(int[][] graph, int[] color, int node, int c) {
        color[node] = c;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == -1) {
                if (!dfsBipartite(graph, color, neighbor, 1 - c)) {
                    return false;
                }
            } else if (color[neighbor] == color[node]) {
                return false;
            }
        }
        return true;
    }

    // Q2. Find itinerary from departure and arrival airports
    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        List<String> itinerary = new ArrayList<>();
        dfsItinerary("JFK", graph, itinerary);
        return itinerary;
    }

    private static void dfsItinerary(String airport, Map<String, PriorityQueue<String>> graph, List<String> itinerary) {
        while (graph.containsKey(airport) && !graph.get(airport).isEmpty()) {
            dfsItinerary(graph.get(airport).poll(), graph, itinerary);
        }
        itinerary.add(0, airport);
    }

    // Q3. Check if destination is reachable from source
    public static boolean isReachable(int[][] graph, int source, int destination) {
        boolean[] visited = new boolean[graph.length];
        return dfsReachable(graph, source, destination, visited);
    }

    private static boolean dfsReachable(int[][] graph, int source, int destination, boolean[] visited) {
        if (source == destination) return true;
        visited[source] = true;
        for (int neighbor : graph[source]) {
            if (!visited[neighbor] && dfsReachable(graph, neighbor, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    // Q4. Find the arrival and departure time of vertices in DFS
    public static void dfsArrivalDeparture(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int[] arrival = new int[n];
        int[] departure = new int[n];
        int time = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsArrivalDepartureHelper(graph, i, visited, arrival, departure, time++);
            }
        }

        System.out.println("Arrival times: " + Arrays.toString(arrival));
        System.out.println("Departure times: " + Arrays.toString(departure));
    }

    private static void dfsArrivalDepartureHelper(int[][] graph, int node, boolean[] visited, int[] arrival, int[] departure, int time) {
        visited[node] = true;
        arrival[node] = time++;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfsArrivalDepartureHelper(graph, neighbor, visited, arrival, departure, time);
            }
        }
        departure[node] = time++;
    }

    // Q5. Find the root vertex in a directed graph
    public static int findRootVertex(int[][] graph) {
        int n = graph.length;
        int[] outDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                outDegree[neighbor]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                return i; // This vertex has no outgoing edges, and is a root
            }
        }
        return -1; // No root vertex found
    }

    public static void main(String[] args) {
        // Q1: Bipartite Check
        int[][] bipartiteGraph = {
                {1, 3}, // vertex 0 connected to 1 and 3
                {0, 2}, // vertex 1 connected to 0 and 2
                {1, 3}, // vertex 2 connected to 1 and 3
                {0, 2}  // vertex 3 connected to 0 and 2
        };
        System.out.println("Is graph bipartite? " + isBipartite(bipartiteGraph)); // Expected output: true

        // Q2: Itinerary
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("HKG", "DXB"));
        tickets.add(Arrays.asList("FRA", "HKG"));
        tickets.add(Arrays.asList("DEL", "FRA"));
        System.out.println("Itinerary: " + findItinerary(tickets)); // Expected output: DEL -> FRA -> HKG -> DXB

        // Q3: Reachable Vertex
        int[][] graphReachable = {
                {3, 4},   // vertex 0 connected to 3 and 4
                {2},      // vertex 1 connected to 2
                {3},      // vertex 2 connected to 3
                {},       // vertex 3 has no outgoing edges
                {5},      // vertex 4 connected to 5
                {}        // vertex 5 has no outgoing edges
        };
        System.out.println("Is 7 reachable from 0? " + isReachable(graphReachable, 0, 7)); // Expected output: true

        // Q4: Arrival and Departure Times in DFS
        int[][] graphDFS = {
                {1},    // vertex 0 connected to 1
                {2},    // vertex 1 connected to 2
                {3},    // vertex 2 connected to 3
                {4},    // vertex 3 connected to 4
                {}      // vertex 4 has no outgoing edges
        };
        dfsArrivalDeparture(graphDFS); // Print arrival and departure times

        // Q5: Root Vertex
        int[][] graphRoot = {
                {1},    // vertex 0 connected to 1
                {2},    // vertex 1 connected to 2
                {3},    // vertex 2 connected to 3
                {4},    // vertex 3 connected to 4
                {}      // vertex 4 has no outgoing edges
        };
        System.out.println("Root vertex: " + findRootVertex(graphRoot)); // Expected output: 4
    }
}
