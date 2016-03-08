package com.ea.graph;

/**
 * Created by Андрей on 30.07.2015.
 */
public class GridMapParser {

    private final int rows, columns;
    private int rowCounter = 0;
    private int lineCounter = 0;
    private final FuelPropertyGraph graph;
    private boolean valid = true;

    public GridMapParser(int n, int m) {
        rows = n;
        columns = m;
        graph = new FuelPropertyGraph(n * m);
    }

    public GridMapParser(String line) {
        String[] dimensions = line.split(" ");
        rows = Integer.valueOf(dimensions[0]).intValue();
        columns = Integer.valueOf(dimensions[1]).intValue();
        graph = new FuelPropertyGraph(rows * columns);
    }


    public void consumeLine(String line) {
        if (!valid) {
            throw new IllegalStateException("Parser can not consume lines in current state");
        }
        String[] chunks = line.split(" ");
        if (chunks.length != columns) {
            valid = false;
            throw new IllegalArgumentException("Line does not fit");
        }
        for (String one : chunks) {
            Integer fuel = Integer.valueOf(one);
            FuelPropertyGraph.Vertex v;
            v = graph.getVertex(getCurrentIndex());
            if (fuel > -1) {
                v.setFuelAmount(fuel);
                int fieldAbove = getAboveCurrentIndex();
                int previousField = getBeforeCurrentIndex();
                if (fieldAbove != -1) {
                    if (graph.getVertex(fieldAbove).getFuelAmount() != -1) {
                        v.addEdge(fieldAbove);
                        graph.getVertex(fieldAbove).addEdge(v.getVertexIndex());
                    }
                }
                if (previousField != -1) {
                    if (graph.getVertex(previousField).getFuelAmount() != -1) {
                        v.addEdge(previousField);
                        graph.getVertex(previousField).addEdge(v.getVertexIndex());
                    }
                }
            }
            lineCounter++;
        }
        rowCounter++;
        lineCounter = 0;
        if (rowCounter == rows) {
            valid = false;
        }
    }

    public int getCurrentIndex() {
        if (rowCounter == 0) {
            return lineCounter;
        }
        return rowCounter * columns + lineCounter;
    }

    public int getAboveCurrentIndex() {
        if (rowCounter == 0) {
            return -1;
        }
        return (rowCounter - 1) * columns + lineCounter;
    }

    public int getBeforeCurrentIndex() {
        if (lineCounter == 0) {
            return -1;
        }
        if (rowCounter == 0) {
            return lineCounter - 1;
        }
        return rowCounter * columns - 1 + lineCounter;
    }

    public FuelPropertyGraph getGraph() {
        return graph;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
