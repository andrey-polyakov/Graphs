package com.ea.graph;


import com.ea.graph.FuelPropertyGraph.Vertex;
import java.util.*;

public class RouteFinderLogic {

    private FuelPropertyGraph g;
    private int start, destination;
    private int[] distanceFromDestination;
    private int minimalRoute = 0;
    private Map<Integer, Set<Vertex>> routeClasses = new HashMap<>();

    public RouteFinderLogic(FuelPropertyGraph graph, int start, int destination) {
        g = graph;
        this.start = start;
        this.destination = destination;
    }

    private void addRouteToClass(FuelPropertyGraph.Vertex v) {
        Set<FuelPropertyGraph.Vertex> segment;
        int index = distanceFromDestination[v.getVertexIndex()];
        if (routeClasses.get(index) == null) {
            segment = new HashSet<>();
            routeClasses.put(index, segment);
        } else {
            segment = routeClasses.get(index);
        }
        segment.add(v);
    }

    private void init() {
        distanceFromDestination = new BellmanFord(g).evaluate(destination);
        minimalRoute = distanceFromDestination[start];
        for (FuelPropertyGraph.Vertex v : g.getVertices()) {
            addRouteToClass(v);
        }
    }

    public long calculateBestRoute() {
        init();
        if (minimalRoute == 0) {
            return -1;
        }
        int max = 0;
        for (Vertex v : routeClasses.get(minimalRoute)) {
            int segment = minimalRoute - 1;
            Vertex current = v;
            int localSum = current.getFuelAmount();
            while (segment != 0) {
                Vertex bottomClassMaximum = null;
                Collection<Vertex> vertices = current.connectedWith();
                for (Vertex neighbour : vertices) {
                    if (routeClasses.get(segment).contains(neighbour)) {
                        if (bottomClassMaximum == null || bottomClassMaximum.getFuelAmount() < neighbour.getFuelAmount()) {
                            bottomClassMaximum = neighbour;
                        }
                    }
                }
                current = bottomClassMaximum;
                localSum += current.getFuelAmount();
                segment--;
            }
            max = Math.max(max, localSum);
        }
        //max += g.getVertex(start).getFuelAmount();
        max += g.getVertex(destination).getFuelAmount();
        return max;
    }
}
