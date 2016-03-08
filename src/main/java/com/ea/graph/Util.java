package com.ea.graph;

/**
 * Created by Андрей on 31.07.2015.
 */
public class Util {

    public static int parseFirstCoordinates(String s, int columns) {
        int column = new Integer(s.split(" ")[1]).intValue();
        int row = new Integer(s.split(" ")[0]).intValue();
        if (row > 0) {
            return row * columns + column;
        } else {
            return column;
        }
    }

    public static int parseSecondCoordinates(String s, int columns) {
        int column = new Integer(s.split(" ")[3]).intValue();
        int row = new Integer(s.split(" ")[2]).intValue();
        if (row > 0) {
            return row * columns + column;
        } else {
            return column;
        }
    }
}
