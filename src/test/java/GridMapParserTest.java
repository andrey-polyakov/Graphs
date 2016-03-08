import com.ea.graph.FuelPropertyGraph;
import com.ea.graph.GridMapParser;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 *
 */
public class GridMapParserTest {

    @Test
    public void tiny() {
        GridMapParser gmp = new GridMapParser(1, 2);
        gmp.consumeLine("97 68");
        FuelPropertyGraph g = gmp.getGraph();
        Integer[][] mutationMatrix = new Integer[g.getVerticesAmount()][g.getVerticesAmount()];
        for (int s = 0; s < mutationMatrix.length; s++) {
            for (int d = 0; d < mutationMatrix.length; d++) {
                mutationMatrix[s][d] = g.getAdjacency(s, d);
            }
        }
        assertArrayEquals(new Integer[][]{
                {0, 1},
                {1, 0}
        }, mutationMatrix);
    }


    @Test
    public void simple4x4() {
        GridMapParser gmp = new GridMapParser(2, 2);
        gmp.consumeLine("0 1");
        gmp.consumeLine("2 3");
        FuelPropertyGraph g = gmp.getGraph();
        Integer[][] mutationMatrix = new Integer[g.getVerticesAmount()][g.getVerticesAmount()];
        for (int s = 0; s < mutationMatrix.length; s++) {
            for (int d = 0; d < mutationMatrix.length; d++) {
                mutationMatrix[s][d] = g.getAdjacency(s, d);
            }
        }
        assertArrayEquals(new Integer[][]{
                {0, 1, 1, 0}, //0
                {1, 0, 0, 1}, //1
                {1, 0, 0, 1}, //2
                {0, 1, 1, 0}  //3
        }, mutationMatrix);
    }

    @Test
    public void obstructed4x4() {
        String l1 = "0 -1";
        String l2 = "-1 3";
        GridMapParser gmp = new GridMapParser(2, 2);
        gmp.consumeLine(l1);
        gmp.consumeLine(l2);
        FuelPropertyGraph g = gmp.getGraph();
        Integer[][] mutationMatrix = new Integer[g.getVerticesAmount()][g.getVerticesAmount()];
        for (int s = 0; s < mutationMatrix.length; s++) {
            for (int d = 0; d < mutationMatrix.length; d++) {
                mutationMatrix[s][d] = g.getAdjacency(s, d);
            }
        }
        assertArrayEquals(new Integer[][]{
                {0, 0, 0, 0}, //0
                {0, 0, 0, 0}, //1
                {0, 0, 0, 0}, //2
                {0, 0, 0, 0}  //3
        }, mutationMatrix);
    }

    @Test
    public void semiObstructed4x4() {
        String l1 = "0 -1";
        String l2 = "22 3";
        GridMapParser gmp = new GridMapParser(2, 2);
        gmp.consumeLine(l1);
        gmp.consumeLine(l2);
        FuelPropertyGraph g = gmp.getGraph();
        Integer[][] mutationMatrix = new Integer[g.getVerticesAmount()][g.getVerticesAmount()];
        for (int s = 0; s < mutationMatrix.length; s++) {
            for (int d = 0; d < mutationMatrix.length; d++) {
                mutationMatrix[s][d] = g.getAdjacency(s, d);
            }
        }
        assertArrayEquals(new Integer[][]{
        //       0  1  2  3
                {0, 0, 1, 0}, //0
                {0, 0, 0, 0}, //1
                {1, 0, 0, 1}, //2
                {0, 0, 1, 0}  //3
        }, mutationMatrix);
    }

    @Test
    public void rectangular() {
        String l1 = "0 -1 4";
        String l2 = "21 3 5";
        GridMapParser gmp = new GridMapParser(2, 3);
        gmp.consumeLine(l1);
        gmp.consumeLine(l2);
        FuelPropertyGraph g = gmp.getGraph();
        Integer[][] mutationMatrix = new Integer[g.getVerticesAmount()][g.getVerticesAmount()];
        for (int s = 0; s < mutationMatrix.length; s++) {
            for (int d = 0; d < mutationMatrix.length; d++) {
                mutationMatrix[s][d] = g.getAdjacency(s, d);
            }
        }
        assertArrayEquals(new Integer[][]{
        //       0  1  2  3  4  5
                {0, 0, 0, 1 ,0 ,0},  //0
                {0, 0, 0, 0 ,0 ,0},  //1
                {0, 0, 0, 0 ,0 ,1},  //2
                {1, 0, 0, 0 ,1 ,0},  //3
                {0, 0, 0, 1 ,0 ,1},  //4
                {0, 0, 1, 0 ,1 ,0}   //5
        }, mutationMatrix);
    }

}
