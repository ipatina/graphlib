package com.ipatina.graphlib;

public class Edge<Vertex> {

    private Vertex source;
    private Vertex destination;

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }
}
