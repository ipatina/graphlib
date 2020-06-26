package com.ipatina.graphlib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents a graph.
 * Doesn't allow duplicates.
 *
 * @param <Vertex> type of vertex
 */
public abstract class Graph<Vertex> {

    private static final Logger LOGGER = LogManager.getLogger(Graph.class);

    protected Map<Vertex, List<Vertex>> graph = new HashMap<>();

    /**
     * Add vertex to this graph.
     *
     * @param vertex to be added to this graph
     * @return false if graph already contains vertex, otherwise true
     */
    public boolean addVertex(Vertex vertex) {
        if (graph.containsKey(vertex)) {
            LOGGER.warn("Vertex '{}' is already exists in the graph", vertex);
            return false;
        } else {
            graph.put(vertex, new LinkedList<>());
            return true;
        }
    }

    /**
     * Add edge between 2 vertices.
     *
     * @param source      vertex for the edge
     * @param destination vertex for the edge
     * @return false if graph already contains edge for the specified vertices, otherwise true
     * @throws IllegalArgumentException if any of the specified vertices doesn't exist in the graph
     */
    abstract boolean addEdge(Vertex source, Vertex destination);

    /**
     * Path between 2 vertices.
     *
     * @param source      start point for the path
     * @param destination end point for the path
     * @return List of {@link Edge} entities between source and destination vertices.
     * Returns empty List if there is no edges between source and destination vertices.
     */
    public List<Edge<Vertex>> getPath(Vertex source, Vertex destination) {
        LinkedList<Edge<Vertex>> edges = new LinkedList<>();
        List<Vertex> visited = new ArrayList<>();

        rec(source, destination, edges, visited);
        return edges;
    }

    private Vertex rec(Vertex source, Vertex destination, LinkedList<Edge<Vertex>> edges, List<Vertex> visited) {
        List<Vertex> current = graph.get(source);
        LOGGER.debug("Current vertex: " + source);
        visited.add(source);

        if (current.contains(destination)) {
            LOGGER.debug("Found edge source: " + source + ", destination: " + destination);
            edges.addFirst(new Edge<>(source, destination));
            return destination;
        }

        for (Vertex vertex : current) {
            if (visited.contains(vertex)) continue;

            Vertex nextVertex = rec(vertex, destination, edges, visited);
            if (nextVertex != null) {
                LOGGER.debug("Found edge source: " + source + ", destination: " + vertex);
                edges.addFirst(new Edge<>(source, vertex));
                return vertex;
            }
        }

        return null;
    }

    protected void checkExistance(Vertex source, Vertex destination) {
        if (!graph.containsKey(source)) {
            String message = "Source vertex '" + source + "' doesn't exist in the graph";
            throw new IllegalArgumentException(message);
        }

        if (!graph.containsKey(destination)) {
            String message = "Destination vertex '" + destination + "' doesn't exist in the graph";
            throw new IllegalArgumentException(message);
        }
    }
}
