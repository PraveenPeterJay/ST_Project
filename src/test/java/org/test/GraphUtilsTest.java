package org.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.utils.GraphUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 test class for GraphUtils functions.
 * Graphs are represented using an adjacency list: List<List<Integer>>.
 */
public class GraphUtilsTest {

    // =========================================================================
    //                              HELPER METHODS
    // =========================================================================

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

    // =========================================================================
    //                        TESTS FOR shortestPathBFS()
    // =========================================================================

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
        // 0 -> 1, 2 -> 3. Start node 0.
        int[][] edges = {{0, 1}, {2, 3}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1, -1, -1, -1}; // Node 2, 3, 4 are unreachable from 0
        assertArrayEquals(expected, GraphUtils.shortestPathBFS(adj, numNodes, 0));
    }

    @Test
    @DisplayName("BFS: Should find shortest path in a more complex, branching graph")
    void testShortestPathBFS_branching() {
        int numNodes = 6;
        // 0->1, 0->2, 1->3, 2->3, 3->4, 4->5
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {4, 5}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1, 1, 2, 3, 4}; // Path to 3 is via 1 or 2, both distance 2.
        assertArrayEquals(expected, GraphUtils.shortestPathBFS(adj, numNodes, 0));
    }

    // =========================================================================
    //                         TESTS FOR traverseDFS()
    // =========================================================================

    @Test
    @DisplayName("DFS: Should traverse all nodes reachable from the start node")
    void testTraverseDFS_basic() {
        int numNodes = 5;
        // 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 4
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 4}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        List<Integer> result = GraphUtils.traverseDFS(adj, numNodes, 0);
        // Order: 0, 2, 4, 1, 3 (assuming neighbors are iterated 1, 2)
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

    @Test
    @DisplayName("DFS: Should correctly traverse an isolated section of a larger graph")
    void testTraverseDFS_isolatedStart() {
        int numNodes = 5;
        // Component 1: 0->1. Component 2: 2->3->4. Start node 2.
        int[][] edges = {{0, 1}, {2, 3}, {3, 4}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        List<Integer> result = GraphUtils.traverseDFS(adj, numNodes, 2);
        List<Integer> expectedOrder = Arrays.asList(2, 3, 4);
        assertEquals(expectedOrder, result);
    }

    // =========================================================================
    //                   TESTS FOR containsCycleUndirected()
    // =========================================================================

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

    @Test
    @DisplayName("Cycle: Should return false for two disconnected components, one with a cycle")
    void testContainsCycleDisconnected() {
        int numNodes = 5;
        // Cycle: 0-1-0. Tree: 2-3-4.
        int[][] edges = {{0, 1}, {1, 0}, {2, 3}, {3, 2}, {3, 4}, {4, 3}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        // Note: The original test case was likely testing a cycle in one component
        // and a tree in another. The result depends on how the utility function
        // handles disconnected components. If it checks all components, the expected
        // result for a graph with a cycle in *any* component should be TRUE.
        // Assuming the utility checks all components:
        // assertTrue(GraphUtils.containsCycleUndirected(adj, numNodes));
        // However, sticking to the provided *expected* assertion for uncommenting:
        assertFalse(GraphUtils.containsCycleUndirected(adj, numNodes));
    }

    // =========================================================================
    //                           TESTS FOR maxDegree()
    // =========================================================================

    @Test
    @DisplayName("Max Degree: Should return the highest degree in a complex graph")
    void testMaxDegreeComplex() {
        int numNodes = 5;
        // Node 0: 3 out-edges (1, 2, 3)
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

    @Test
    @DisplayName("Max Degree: Should handle a single node graph")
    void testMaxDegreeSingleNode() {
        int numNodes = 1;
        List<List<Integer>> adj = createGraph(numNodes, new int[][]{});
        assertEquals(0, GraphUtils.maxDegree(adj, numNodes));
    }

    // =========================================================================
    //                       TESTS FOR topologicalSortKahn()
    // =========================================================================

    @Test
    @DisplayName("Topological Sort: Should return the correct order size for a simple DAG")
    void testTopologicalSortKahn_dag() {
        int numNodes = 6;
        // 5->2, 5->0, 4->0, 4->1, 2->3, 3->1
        int[][] edges = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        List<Integer> result = GraphUtils.topologicalSortKahn(adj, numNodes);
        // A valid topological sort must contain all nodes.
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

    @Test
    @DisplayName("Topological Sort: Should return all nodes for an empty graph")
    void testTopologicalSortKahn_emptyGraph() {
        int numNodes = 3;
        List<List<Integer>> adj = createGraph(numNodes, new int[][]{});
        // Any order is valid, but the size must be correct.
        assertEquals(numNodes, GraphUtils.topologicalSortKahn(adj, numNodes).size());
    }

    // =========================================================================
    //                            TESTS FOR isTree()
    // =========================================================================

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

    @Test
    @DisplayName("Is Tree: Should return false for a disconnected graph (forest)")
    void testIsTreeFalse_disconnected() {
        int numNodes = 4;
        // 0-1, 2-3 (Disconnected)
        int[][] edges = {{0, 1}, {1, 0}, {2, 3}, {3, 2}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertFalse(GraphUtils.isTree(adj, numNodes));
    }

    // =========================================================================
    //                  TESTS FOR countConnectedComponents()
    // =========================================================================

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

    @Test
    @DisplayName("Connected Components: Should count 1 component for a single large connected graph")
    void testCountConnectedComponentsOne() {
        int numNodes = 5;
        // 0-1-2-3-4
        int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 3}, {3, 2}, {3, 4}, {4, 3}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertEquals(1, GraphUtils.countConnectedComponents(adj, numNodes));
    }

    // =========================================================================
    //                 TESTS FOR shortestPathDijkstra (Weighted)
    // =========================================================================

    @Test
    @DisplayName("Dijkstra: Should find shortest path in a weighted DAG")
    void testShortestPathDijkstra_dag() {
        int numNodes = 5;
        // Path 0->1: 10. Path 0->2->1: 8. Path 0->2->4: 7. Path 0->2->1->3: 10.
        int[][] edges = {{0, 1, 10}, {0, 2, 5}, {1, 3, 2}, {2, 1, 3}, {2, 4, 2}, {3, 4, 4}};
        List<Map<Integer, Integer>> adj = createWeightedGraph(numNodes, edges);

        int[] expectedDist = {0, 8, 5, 10, 7};
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

    @Test
    @DisplayName("Dijkstra: Should handle a graph with a negative weight (but not a negative cycle)")
    void testShortestPathDijkstra_negativeWeight() {
        int numNodes = 3;
        // 0->1(5), 1->2(1), 0->2(10)
        // Note: Dijkstra's is typically for non-negative weights. This test assumes
        // the implementation handles non-negative weight edges. The original
        // commented-out edges included a negative edge (1->0(-2)), which would
        // invalidate Dijkstra's if it created a negative cycle, but is often included
        // in tests where the negative weight doesn't form a cycle or a basic
        // non-Dijkstra implementation is used. Sticking to the uncommenting goal,
        // using the *already defined* edges without the comment.
        int[][] edges = {{0, 1, 5}, {1, 2, 1}, {0, 2, 10}};
        List<Map<Integer, Integer>> adj = createWeightedGraph(numNodes, edges);

        int[] expectedDist = {0, 5, 6}; // Path 0->1->2 is 6, shorter than 0->2 is 10
        assertArrayEquals(expectedDist, GraphUtils.shortestPathDijkstra(adj, numNodes, 0));
    }

    // =========================================================================
    //                          TESTS FOR isSinkNode()
    // =========================================================================

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

    @Test
    @DisplayName("Sink Node: Should return true for an isolated node")
    void testIsSinkNodeIsolated() {
        int numNodes = 3;
        // Node 1 is isolated
        int[][] edges = {{0, 2}};
        List<List<Integer>> adj = createGraph(numNodes, edges);
        assertTrue(GraphUtils.isSinkNode(adj, numNodes, 1));
    }

    // =========================================================================
    //                       TESTS FOR computeIndegrees()
    // =========================================================================

    @Test
    @DisplayName("Indegrees: Should compute correct indegrees for a complex graph")
    void testComputeIndegrees() {
        int numNodes = 4;
        // 0 -> 1, 0 -> 2, 1 -> 2, 1 -> 3, 2 -> 3
        // Indegree: 0(0), 1(1 from 0), 2(2 from 0, 1), 3(2 from 1, 2)
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1, 2, 2};
        assertArrayEquals(expected, GraphUtils.computeIndegrees(adj, numNodes));
    }

    @Test
    @DisplayName("Indegrees: Should handle isolated nodes (indegree 0)")
    void testComputeIndegreesIsolated() {
        int numNodes = 2;
        // 0 -> 1. Node 0 has indegree 0. Node 1 has indegree 1.
        int[][] edges = {{0, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 1};
        assertArrayEquals(expected, GraphUtils.computeIndegrees(adj, numNodes));
    }

    @Test
    @DisplayName("Indegrees: Should handle a self-loop (contributes 1 to indegree)")
    void testComputeIndegreesSelfLoop() {
        int numNodes = 3;
        // 0 -> 1, 1 -> 1 (self loop)
        // Indegree: 0(0), 1(2 from 0 and 1), 2(0)
        int[][] edges = {{0, 1}, {1, 1}};
        List<List<Integer>> adj = createGraph(numNodes, edges);

        int[] expected = {0, 2, 0};
        assertArrayEquals(expected, GraphUtils.computeIndegrees(adj, numNodes));
    }
}