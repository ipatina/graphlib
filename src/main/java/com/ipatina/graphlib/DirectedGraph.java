package com.ipatina.graphlib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Represents a directed graph.
 *
 * @param <Vertex> type of vertex
 */
public class DirectedGraph<Vertex> extends Graph<Vertex> {

    private static final Logger LOGGER = LogManager.getLogger(DirectedGraph.class);

    /**
     * Add edge between 2 vertices.
     *
     * @param source      vertex for the edge
     * @param destination vertex for the edge
     * @return false if graph already contains edge for the specified vertices, otherwise true
     * @throws IllegalArgumentException if any of the specified vertices doesn't exist in the graph
     */
    public boolean addEdge(Vertex source, Vertex destination) {
        checkExistance(source, destination);
        List<Vertex> edges = graph.get(source);
        if (edges.contains(destination)) {
            LOGGER.warn("Edge '{}' - '{}' is already exists in the graph", source, destination);
            return false;
        }

        return edges.add(destination);
    }
}
