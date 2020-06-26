package com.ipatina.graphlib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Represents a undirected graph.
 *
 * @param <Vertex>
 */
public class UndirectedGraph<Vertex> extends Graph<Vertex> {

    private static final Logger LOGGER = LogManager.getLogger(UndirectedGraph.class);

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

        List<Vertex> sourceEdges = graph.get(source);
        if (sourceEdges.contains(destination)) {
            LOGGER.warn("Edge '{}' - '{}' is already exists in the graph", source, destination);
            return false;
        }

        List<Vertex> destinationEdges = graph.get(destination);
        sourceEdges.add(destination);
        destinationEdges.add(source);
        return true;
    }
}
