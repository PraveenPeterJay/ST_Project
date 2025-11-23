package org.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Utility class containing various graph manipulation and algorithm functions.
 * Graphs are represented using an adjacency list: List<List<Integer>> where
 * the index is the node and the inner list contains its neighbors.
 */
public final class GraphUtils {

    // Private constructor to prevent instantiation
    private GraphUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // --- Helper for Basic Graph Validation ---

    private static void validateGraph(List<List<Integer>> adj, int numNodes) {
        if (adj == null || adj.size() != numNodes) {
            throw new IllegalArgumentException("Adjacency list must match the number of nodes.");
        }
        for (List<Integer> neighbors : adj) {
            if (neighbors == null) {
                throw new IllegalArgumentException("Neighbor list cannot be null.");
            }
            for (int neighbor : neighbors) {
                if (neighbor < 0 || neighbor >= numNodes) {
                    throw new IllegalArgumentException("Node index is out of bounds: " + neighbor);
                }
            }
        }
    }

    // --- 10 Graph Algorithm Functions ---

    /**
     * 1. Breadth-First Search (BFS) to find the shortest path distance from a start node.
     * Works for unweighted graphs.
     *
     * @param adj Adjacency list representation of the graph.
     * @param numNodes Total number of nodes (0-indexed).
     * @param startNode The starting node for BFS.
     * @return An array where distance[i] is the shortest distance from startNode to i.
     */
    public static int[] shortestPathBFS(List<List<Integer>> adj, int numNodes, int startNode) {
        validateGraph(adj, numNodes);
        if (startNode < 0 || startNode >= numNodes) {
            throw new IllegalArgumentException("Start node is out of bounds.");
        }

        int[] distance = new int[numNodes];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new LinkedList<>();

        distance[startNode] = 0;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    queue.add(v);
                }
            }
        }
        return distance;
    }

    /**
     * 2. Depth-First Search (DFS) to traverse all reachable nodes from a start node.
     *
     * @param adj Adjacency list representation of the graph.
     * @param numNodes Total number of nodes.
     * @param startNode The starting node.
     * @return A list of nodes in the order they were visited by DFS.
     */
    public static List<Integer> traverseDFS(List<List<Integer>> adj, int numNodes, int startNode) {
        validateGraph(adj, numNodes);
        if (startNode < 0 || startNode >= numNodes) {
            return Collections.emptyList();
        }

        boolean[] visited = new boolean[numNodes];
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startNode);
        visited[startNode] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            result.add(u);

            // Iterate through neighbors in reverse order if you want the order to match recursive DFS (optional)
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    stack.push(v);
                }
            }
        }
        return result;
    }

    /**
     * 3. Checks if the graph contains a cycle using DFS (for undirected graphs).
     * Assumes the graph is undirected (if an edge u->v exists, v->u must be in the list).
     *
     * @param adj Adjacency list representation of the graph.
     * @param numNodes Total number of nodes.
     * @return true if a cycle is detected, false otherwise.
     */
    public static boolean containsCycleUndirected(List<List<Integer>> adj, int numNodes) {
        validateGraph(adj, numNodes);
        boolean[] visited = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i]) {
                if (isCyclicDFS(adj, i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper for cycle detection (DFS with parent node).
     */
    private static boolean isCyclicDFS(List<List<Integer>> adj, int u, boolean[] visited, int parent) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                if (isCyclicDFS(adj, v, visited, u)) {
                    return true;
                }
            } else if (v != parent) {
                // If a visited node is encountered and it's not the immediate parent, a cycle exists.
                return true;
            }
        }
        return false;
    }

    /**
     * 4. Computes the maximum degree (maximum number of neighbors) of any node.
     *
     * @param adj Adjacency list representation of the graph.
     * @param numNodes Total number of nodes.
     * @return The maximum degree, or -1 if the graph is invalid/empty.
     */
    public static int maxDegree(List<List<Integer>> adj, int numNodes) {
        if (adj == null || numNodes <= 0) {
            return -1;
        }
        validateGraph(adj, numNodes);

        int maxDeg = 0;
        for (List<Integer> neighbors : adj) {
            if (neighbors.size() > maxDeg) {
                maxDeg = neighbors.size();
            }
        }
        return maxDeg;
    }

    /**
     * 5. Performs Topological Sort for a Directed Acyclic Graph (DAG) using Kahn's algorithm (BFS).
     *
     * @param adj Adjacency list representation of the DAG.
     * @param numNodes Total number of nodes.
     * @return A list of nodes in topological order, or an empty list if a cycle is present.
     */
    public static List<Integer> topologicalSortKahn(List<List<Integer>> adj, int numNodes) {
        validateGraph(adj, numNodes);
        int[] inDegree = new int[numNodes];

        // 1. Compute in-degrees
        for (int u = 0; u < numNodes; u++) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }

        // 2. Initialize queue with all nodes having 0 in-degree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 3. Process nodes
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);

            // Decrease in-degree of neighbors
            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // 4. Check for cycle
        if (result.size() != numNodes) {
            return Collections.emptyList(); // Cycle detected
        }
        return result;
    }

    /**
     * 6. Checks if a graph is a tree (connected and has exactly N-1 edges, or is connected and cycle-free).
     * This method checks for connectivity and cycle-free status using BFS/DFS and the cycle detection logic.
     * Assumes the graph is undirected.
     *
     * @param adj Adjacency list representation of the graph.
     * @param numNodes Total number of nodes.
     * @return true if the graph is a tree, false otherwise.
     */
    public static boolean isTree(List<List<Integer>> adj, int numNodes) {
        if (numNodes == 0) return true;
        validateGraph(adj, numNodes);

        // 1. Check for cycles (using modified DFS with parent tracking)
        boolean[] visited = new boolean[numNodes];
        if (isCyclicDFS(adj, 0, visited, -1)) {
            return false;
        }

        // 2. Check for connectivity (all nodes must be visited)
        for (int i = 0; i < numNodes; i++) {
            if (!visited[i]) {
                return false; // Not connected
            }
        }

        // 3. Count edges (in an undirected graph, 2*E = sum of degrees)
        int edgeCount = 0;
        for (List<Integer> neighbors : adj) {
            edgeCount += neighbors.size();
        }
        int numEdges = edgeCount / 2; // Since each edge is listed twice

        // 4. A connected graph is a tree if and only if |V| = |E| + 1
        return numNodes == numEdges + 1;
    }

    /**
     * 7. Counts the number of connected components in an undirected graph.
     *
     * @param adj Adjacency list representation of the graph.
     * @param numNodes Total number of nodes.
     * @return The number of connected components.
     */
    public static int countConnectedComponents(List<List<Integer>> adj, int numNodes) {
        validateGraph(adj, numNodes);
        boolean[] visited = new boolean[numNodes];
        int count = 0;

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i]) {
                // Found a new component, perform DFS/BFS to mark all nodes in it
                dfsHelper(adj, i, visited);
                count++;
            }
        }
        return count;
    }

    /**
     * Private recursive DFS helper for connectivity.
     */
    private static void dfsHelper(List<List<Integer>> adj, int u, boolean[] visited) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfsHelper(adj, v, visited);
            }
        }
    }

    // --- Weighted Graph Functions (Using Adjacency Matrix for simplicity in this utility class) ---

    /**
     * Represents a weighted edge for Dijkstra's.
     */
    private static class Edge {
        int target;
        int weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    /**
     * 8. Dijkstra's Algorithm: Finds the shortest path from a source node to all other nodes
     * in a weighted graph with non-negative edge weights.
     *
     * @param weightedAdj Adjacency list: List of map entries, where map key is target node and value is weight.
     * @param numNodes Total number of nodes.
     * @param startNode The source node.
     * @return An array of shortest distances from the start node (Integer.MAX_VALUE if unreachable).
     */
    public static int[] shortestPathDijkstra(List<Map<Integer, Integer>> weightedAdj, int numNodes, int startNode) {
        if (weightedAdj == null || weightedAdj.size() != numNodes) {
            throw new IllegalArgumentException("Invalid weighted adjacency structure.");
        }
        if (startNode < 0 || startNode >= numNodes) {
            throw new IllegalArgumentException("Start node is out of bounds.");
        }

        // Use a list of Lists of Edges for clearer structure, simplifying map use.
        // If the user input is a List<Map>, we must process it:
        // We assume weightedAdj uses Map<Integer, Integer> where key is neighbor, value is weight.

        int[] dist = new int[numNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        // PriorityQueue stores [distance, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, startNode});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0];
            int u = current[1];

            if (d > dist[u]) {
                continue;
            }

            for (Map.Entry<Integer, Integer> entry : weightedAdj.get(u).entrySet()) {
                int v = entry.getKey();
                int weight = entry.getValue();

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    /**
     * 9. Checks if a node is a 'sink' node (has no outgoing edges).
     *
     * @param adj Adjacency list representation of the graph (directed or undirected).
     * @param numNodes Total number of nodes.
     * @param node The node index to check.
     * @return true if the node has an out-degree of 0, false otherwise.
     */
    public static boolean isSinkNode(List<List<Integer>> adj, int numNodes, int node) {
        validateGraph(adj, numNodes);
        if (node < 0 || node >= numNodes) {
            throw new IllegalArgumentException("Node index is out of bounds.");
        }
        return adj.get(node).isEmpty();
    }

    /**
     * 10. Computes the indegree of every node in a directed graph.
     *
     * @param adj Adjacency list representation of the directed graph.
     * @param numNodes Total number of nodes.
     * @return An array where indegree[i] is the number of incoming edges to node i.
     */
    public static int[] computeIndegrees(List<List<Integer>> adj, int numNodes) {
        validateGraph(adj, numNodes);
        int[] inDegree = new int[numNodes];

        for (int u = 0; u < numNodes; u++) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }
        return inDegree;
    }
}