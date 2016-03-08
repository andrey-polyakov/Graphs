package com.ea.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Graph based on adjacency list. However, it is possible to use it where adjacency matrix needed.
 */
public class FuelPropertyGraph {

    private final Map<Integer, Vertex> vertices;

    public FuelPropertyGraph(Integer[][] matrix) {
        this(matrix.length);
        for (int s = 0; s < matrix.length; s++) {
            for (int d = 0; d < matrix[s].length; d++) {
                if (matrix[s][d] > 0) {
                    addAdjacency(s, d);
                }
            }
        }
    }

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    class Vertex {
        private Map<Integer, Vertex> edges = new HashMap<>();

        private Integer fuel = Integer.valueOf(-1);
        private final Integer vertexIndex;

        public Vertex(Integer index) {
            vertexIndex = index;
        }

        public Integer getFuelAmount() {
            return fuel;
        }

        public void setFuelAmount(Integer fuelAmount) {
            fuel = fuelAmount;
        }

        public void addEdge(Integer toVertex) {
            if (toVertex > vertices.size() - 1) {
                throw new IndexOutOfBoundsException("Vertex does not exist");
            }
            edges.put(toVertex, vertices.get(toVertex));
        }

        public Integer getAdjacency(Integer withVertex) {
            if (withVertex > vertices.size() - 1) {
                throw new IndexOutOfBoundsException("Vertex does not exist");
            }
            Vertex vertex = edges.get(withVertex);
            if (vertex != null) {
                return Integer.valueOf(1);
            } else {
                return Integer.valueOf(0);
            }
        }

        public Collection<Vertex> connectedWith() {
            return edges.values();
        }

        public Integer getVertexIndex() {
            return vertexIndex;
        }

        public void removeVertex() {
            for (Vertex edge : edges.values()) {
                edge.edges.remove(this);
            }
            vertices.remove(this);
        }

        @Override
        public String toString() {
            return "{" +
                    "index=" + vertexIndex + ", fuel=" + fuel +
                    '}';
        }
    }

    public FuelPropertyGraph(int numberOfVertices) {
        vertices = new HashMap<>(numberOfVertices + 2, (float) 0.99);
        for (int ii = 0; ii < numberOfVertices; ii++) {
            vertices.put(Integer.valueOf(ii), new Vertex(Integer.valueOf(ii)));
        }
    }

    public void addAdjacency(int v, int v2) {
        vertices.get(v).addEdge(v2);
    }

    public Integer getAdjacency(int v, int v2) {
        return vertices.get(v).getAdjacency(v2);
    }

    public Vertex getVertex(Integer i) {
        return vertices.get(i);
    }

    public int getVerticesAmount() {
        return vertices.size();
    }

}
