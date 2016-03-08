package com.ea.graph;

public class BellmanFord {
    private final int distances[];
    private final int mutationMatrix[][];
    private FuelPropertyGraph g;
    public BellmanFord(FuelPropertyGraph graph) {
        g = graph;
        distances = new int[g.getVerticesAmount()];
        mutationMatrix = new int[g.getVerticesAmount()][g.getVerticesAmount()];
        for (int s = 0; s < mutationMatrix.length; s++) {
            for (int d = 0; d < mutationMatrix.length; d++) {
                mutationMatrix[s][d] = g.getAdjacency(s, d);
            }
        }
    }

    public static int INF = 65536;
    public int[] evaluate(int source) {

        for (int d = 0; d < distances.length; d++) {
            if (d == source) {
                distances[d] = 0;
            } else {
                distances[d] = INF;
            }
        }


        for (int i = 1; i <= g.getVerticesAmount() - 1; i++) {
            for (FuelPropertyGraph.Vertex v : g.getVertices()) {
                for (FuelPropertyGraph.Vertex edge : v.connectedWith()) {
                    if (distances[v.getVertexIndex()] + 1 < distances[edge.getVertexIndex()]) {
                        distances[edge.getVertexIndex()] = distances[v.getVertexIndex()] + 1;
                    }
                }
            }
        }


        for (int s = 0; s < distances.length; s++) {
            if (distances[s] == INF) {
                distances[s] = 0;
            }
        }
        return distances;
    }

}