package com.ea.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * .
 */
public class EntryPoint {

    public static void main(String[] v) throws Exception {
        try (FileInputStream fileStream = new FileInputStream(v[0])) {
            try (InputStreamReader reader = new InputStreamReader(fileStream)) {
                try (BufferedReader buffered = new BufferedReader(reader)) {
                    String line = buffered.readLine();// number of cases
                    checkLine(line);
                    int casesAmount = Integer.valueOf(line);
                    int caseCounter = 1;
                    int skipCounter = 0;
                    if (v.length > 1) {
                        skipCounter = Integer.valueOf(v[1]);
                    }
                    while (true) {
                        line = buffered.readLine(); //Dimensions
                        checkLine(line);
                        GridMapParser parser = new GridMapParser(line);
                        int lineCounter = parser.getRows();

                        line = buffered.readLine();// 4 coordinates
                        checkLine(line);
                        int from = Util.parseFirstCoordinates(line, parser.getColumns());
                        int to = Util.parseSecondCoordinates(line, parser.getColumns());

                        while (lineCounter-- > 0) {
                            line = buffered.readLine();
                            checkLine(line);
                            if (skipCounter < 1) {
                                parser.consumeLine(line);
                            }
                        }
                        if (skipCounter > 0) {
                            System.out.println("Case #" + caseCounter + ": skipped");
                        } else {
                            RouteFinderLogic l = new RouteFinderLogic(parser.getGraph(), from, to);
                            long result = l.calculateBestRoute();
                            System.out.print("Case #" + caseCounter + ": ");
                            if (result != -1) {
                                System.out.println(result);
                            } else {
                                System.out.println("Mission Impossible.");
                            }
                        }
                        skipCounter--;
                        if (caseCounter++ == casesAmount) {
                            break;
                        }
                        System.gc();
                    }
                }
            }
        }
    }

    private static void checkLine(String line) {
        if (line == null) {
            System.err.println("Unexpected end of file");
            System.exit(1);
        }
    }
}
