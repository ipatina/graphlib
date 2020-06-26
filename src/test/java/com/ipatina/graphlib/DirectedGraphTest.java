package com.ipatina.graphlib;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class DirectedGraphTest {

    @Test
    public void test_addVertex() {
        DirectedGraph<Integer> graph = new DirectedGraph<>();

        boolean firstAddition = graph.addVertex(1);
        boolean duplicateAddition = graph.addVertex(1);

        assertTrue(firstAddition);
        assertFalse(duplicateAddition);
    }

    @Test
    public void test_addEdge() {
        DirectedGraph<String> graph = new DirectedGraph<>();

        Exception sourceException = assertThrows(IllegalArgumentException.class, () -> graph.addEdge("1", "2"));
        assertTrue(sourceException.getMessage().contains("Source vertex '1' doesn't exist in the graph"));

        graph.addVertex("1");
        Exception destinationException = assertThrows(IllegalArgumentException.class, () -> graph.addEdge("1", "2"));
        assertTrue(destinationException.getMessage().contains("Destination vertex '2' doesn't exist in the graph"));

        graph.addVertex("2");
        boolean result = graph.addEdge("1", "2");
        assertTrue(result);

        boolean duplicateResult = graph.addEdge("1", "2");
        assertFalse(duplicateResult);

        boolean reversedResult = graph.addEdge("2", "1");
        assertTrue(reversedResult);
    }

    @Test
    public void test_getPath() {
        DirectedGraph<Integer> graph = new DirectedGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        List<Edge<Integer>> result = graph.getPath(1, 5);
        assertEquals(1, (int) result.get(0).getSource());
        assertEquals(3, (int) result.get(0).getDestination());
        assertEquals(3, (int) result.get(1).getSource());
        assertEquals(4, (int) result.get(1).getDestination());
        assertEquals(4, (int) result.get(2).getSource());
        assertEquals(5, (int) result.get(2).getDestination());

        result = graph.getPath(2, 2);
        assertEquals(2, (int) result.get(0).getSource());
        assertEquals(2, (int) result.get(0).getDestination());

        result = graph.getPath(3, 2);
        assertTrue(result.isEmpty());
    }
}