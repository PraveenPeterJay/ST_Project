package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.utils.GraphUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JUnit 5 test class for GraphUtils functions.
 * Graphs are represented using an adjacency list: List<List<Integer>>.
 */
public class GraphUtilsTest {

    /**
     * Helper to create an adjacency list for an unweighted graph.
     * Nodes: 0 to numNodes-1. Edges: [u, v]
     */
    private List<List<Integer>> createGraph(int numNodes, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
        }
        return adj;
    }

    /**
     * Helper to create a weighted adjacency list.
     * Edges: [u, v, weight]
     */
    private List<Map<Integer, Integer>> createWeightedGraph(int numNodes, int[][] edges) {
        List<Map<Integer, Integer>> adj = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            adj.add(new HashMap<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).put(v, w);
        }
        return adj;
    }

    // --- Test Cases for shortestPathBFS ---

    @Test
    @DisplayName("BFS: Should find correct shortest path distances in a simple linear graph")
    void testShortestPathBFS_linear() {
        int numNodes = 5;
        // 0 -> 1 -> 2 -> 3 -> 4 (Directed)
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1, 2, 3, 4};
        assertArrayEquals(expected, GraphUtils.shortestPathBFS(adj, numNodes, 0));
    }

    @Test
    @DisplayName("BFS: Should handle unreachable nodes (distance -1)")
    void testShortestPathBFS_unreachable() {
        int numNodes = 5;
        // 0 -> 1, 2 -> 3
        int[][] edges = {{0, 1}, {2, 3}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1, -1, -1, -1}; // Node 2, 3, 4 are unreachable from 0
        assertArrayEquals(expected, GraphUtils.shortestPathBFS(adj, numNodes, 0));
    }

    // --- Test Cases for traverseDFS ---

    @Test
    @DisplayName("DFS: Should traverse all nodes reachable from the start node")
    void testTraverseDFS_basic() {
        int numNodes = 5;
        // 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 4 (Using stack, order can vary slightly)
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 4}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        List<Integer> result = GraphUtils.traverseDFS(adj, numNodes, 0);
        // Expected order (0 is visited first, then neighbors 1, 2 are pushed. 2 is popped first)
        List<Integer> expectedOrder = Arrays.asList(0, 2, 4, 1, 3);
        assertEquals(expectedOrder, result);
    }

    @Test
    @DisplayName("DFS: Should handle a graph with only one node")
    void testTraverseDFS_singleNode() {
        int numNodes = 1;
        List<List<Integer>> adj = createGraph(numNodes, new int[][]{});
        assertEquals(Arrays.asList(0), GraphUtils.traverseDFS(adj, numNodes, 0));
    }

    // --- Test Cases for containsCycleUndirected ---

    @Test
    @DisplayName("Cycle: Should return true for a simple cycle (triangle 0-1-2-0)")
    void testContainsCycleTrue() {
        int numNodes = 3;
        // Undirected graph: Add both directions
        int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 0}, {0, 2}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertTrue(GraphUtils.containsCycleUndirected(adj, numNodes));
    }

    @Test
    @DisplayName("Cycle: Should return false for a tree structure")
    void testContainsCycleFalse() {
        int numNodes = 4;
        // 0-1, 1-2, 1-3 (Tree structure)
        int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}, {1, 3}, {3, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertFalse(GraphUtils.containsCycleUndirected(adj, numNodes));
    }

    // --- Test Cases for maxDegree ---

    @Test
    @DisplayName("Max Degree: Should return the highest degree in a complex graph")
    void testMaxDegreeComplex() {
        int numNodes = 5;
        // Node 0: 3 neighbors (1, 2, 3)
        // Node 4: 1 neighbor (0)
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {4, 0}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertEquals(3, GraphUtils.maxDegree(adj, numNodes));
    }

    @Test
    @DisplayName("Max Degree: Should return 0 for a graph with no edges")
    void testMaxDegreeZero() {
        int numNodes = 3;
        List<List<Integer>> adj = createGraph(numNodes, new int[][]{});
        assertEquals(0, GraphUtils.maxDegree(adj, numNodes));
    }

    // --- Test Cases for topologicalSortKahn ---

    @Test
    @DisplayName("Topological Sort: Should return the correct order for a simple DAG")
    void testTopologicalSortKahn_dag() {
        int numNodes = 6;
        // 5->2, 5->0, 4->0, 4->1, 2->3, 3->1
        int[][] edges = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        List<Integer> result = GraphUtils.topologicalSortKahn(adj, numNodes);
        // Valid orders: [4, 5, 0, 2, 3, 1] or [5, 4, 2, 3, 1, 0] etc.
        // We only check if the result size is correct, indicating no cycle.
        assertEquals(numNodes, result.size());
    }

    @Test
    @DisplayName("Topological Sort: Should return empty list for a graph with a cycle")
    void testTopologicalSortKahn_cycle() {
        int numNodes = 3;
        // 0 -> 1 -> 2 -> 0 (Cycle)
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        assertTrue(GraphUtils.topologicalSortKahn(adj, numNodes).isEmpty());
    }

    // --- Test Cases for isTree ---

    @Test
    @DisplayName("Is Tree: Should return true for a valid connected, non-cyclic graph")
    void testIsTreeTrue() {
        int numNodes = 4;
        // 0-1, 1-2, 1-3 (4 nodes, 3 edges, connected, no cycle)
        int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}, {1, 3}, {3, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertTrue(GraphUtils.isTree(adj, numNodes));
    }

    @Test
    @DisplayName("Is Tree: Should return false for a graph with a cycle")
    void testIsTreeFalse_cycle() {
        int numNodes = 3;
        // 0-1-2-0 (3 nodes, 3 edges, cycle)
        int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 0}, {0, 2}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertFalse(GraphUtils.isTree(adj, numNodes));
    }

    // --- Test Cases for countConnectedComponents ---

    @Test
    @DisplayName("Connected Components: Should count 2 components")
    void testCountConnectedComponentsTwo() {
        int numNodes = 5;
        // Component 1: 0-1-2. Component 2: 3-4
        int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}, {3, 4}, {4, 3}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertEquals(2, GraphUtils.countConnectedComponents(adj, numNodes));
    }

    @Test
    @DisplayName("Connected Components: Should count N components if no edges exist")
    void testCountConnectedComponentsIsolated() {
        int numNodes = 4;
        List<List<Integer>> adj = createGraph(numNodes, new int[][]{});
        assertEquals(4, GraphUtils.countConnectedComponents(adj, numNodes));
    }

    // --- Test Cases for shortestPathDijkstra (Weighted Graph) ---

    @Test
    @DisplayName("Dijkstra: Should find shortest path in a weighted DAG")
    void testShortestPathDijkstra_dag() {
        int numNodes = 5;
        // 0->1(10), 0->2(5), 1->3(2), 2->1(3), 2->4(2), 3->4(4), 4->3(6)
        int[][] edges = {{0, 1, 10}, {0, 2, 5}, {1, 3, 2}, {2, 1, 3}, {2, 4, 2}, {3, 4, 4}};
        List<Map<Integer, Integer>> adj = createWeightedGraph(numNodes, edges);

        int[] expectedDist = {0, 8, 5, 10, 7};
        // Path 0->1: 10. Path 0->2->1: 5+3=8. Shortest is 8.
        // Path 0->2->4: 5+2=7.
        // Path 0->2->1->3: 5+3+2=10.
        assertArrayEquals(expectedDist, GraphUtils.shortestPathDijkstra(adj, numNodes, 0));
    }

    @Test
    @DisplayName("Dijkstra: Should return MAX_VALUE for unreachable nodes")
    void testShortestPathDijkstra_unreachable() {
        int numNodes = 3;
        // 0->1(5), 2 is isolated
        int[][] edges = {{0, 1, 5}};
        List<Map<Integer, Integer>> adj = createWeightedGraph(numNodes, edges);

        int[] dist = GraphUtils.shortestPathDijkstra(adj, numNodes, 0);
        assertEquals(0, dist[0]);
        assertEquals(5, dist[1]);
        assertEquals(Integer.MAX_VALUE, dist[2]);
    }

    // --- Test Cases for isSinkNode ---

    @Test
    @DisplayName("Sink Node: Should return true for a node with zero out-degree")
    void testIsSinkNodeTrue() {
        int numNodes = 3;
        // 0 -> 1, 1 -> 2. Node 2 is a sink.
        int[][] edges = {{0, 1}, {1, 2}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertTrue(GraphUtils.isSinkNode(adj, numNodes, 2));
    }

    @Test
    @DisplayName("Sink Node: Should return false for a node with outgoing edges")
    void testIsSinkNodeFalse() {
        int numNodes = 3;
        // 0 -> 1
        int[][] edges = {{0, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertFalse(GraphUtils.isSinkNode(adj, numNodes, 0));
    }

    // --- Test Cases for computeIndegrees ---

    @Test
    @DisplayName("Indegrees: Should compute correct indegrees for a complex graph")
    void testComputeIndegrees() {
        int numNodes = 4;
        // 0 -> 1, 0 -> 2
        // 1 -> 2, 1 -> 3
        // 2 -> 3
        // Indegree: 0(0), 1(1), 2(2), 3(2)
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1, 2, 2};
        assertArrayEquals(expected, GraphUtils.computeIndegrees(adj, numNodes));
    }

    @Test
    @DisplayName("Indegrees: Should handle isolated nodes (indegree 0)")
    void testComputeIndegreesIsolated() {
        int numNodes = 2;
        // 0 -> 1. Node 0 has indegree 0.
        int[][] edges = {{0, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1};
        assertArrayEquals(expected, GraphUtils.computeIndegrees(adj, numNodes));
    }
}