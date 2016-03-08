import com.ea.graph.FuelPropertyGraph;
import com.ea.graph.GridMapParser;
import com.ea.graph.RouteFinderLogic;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test gas calculation .
 */
public class LogicTest {

    @Test
    public void tinySwapedVertices() {
        GridMapParser gmp = new GridMapParser(1, 2);
        gmp.consumeLine("97 68");
        FuelPropertyGraph g = gmp.getGraph();
        assertEquals(165, new RouteFinderLogic(g, 1, 0).calculateBestRoute());
    }

    @Test
    public void tiny() {
        GridMapParser gmp = new GridMapParser(1, 2);
        gmp.consumeLine("97 68");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 1);
        assertEquals(165, l.calculateBestRoute());
    }

    @Test
    public void tinyVertical() {
        GridMapParser gmp = new GridMapParser(2, 1);
        gmp.consumeLine("11");
        gmp.consumeLine("92");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 1);
        assertEquals(103, l.calculateBestRoute());
    }

    @Test
    public void missionImpossible() {
        GridMapParser gmp = new GridMapParser(2, 2);
        gmp.consumeLine("0 -1");
        gmp.consumeLine("-1 3");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 3);
        assertEquals(-1, l.calculateBestRoute());
    }

    @Test
    public void mission2x2() {
        GridMapParser gmp = new GridMapParser(2, 2);
        gmp.consumeLine("1 11");
        gmp.consumeLine("97 3");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 3);
        assertEquals(101, l.calculateBestRoute());
    }

    @Test
    public void mission2x2Reversed() {
        GridMapParser gmp = new GridMapParser(2, 2);
        gmp.consumeLine("1 11");
        gmp.consumeLine("97 3");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 3, 0);
        assertEquals(101, l.calculateBestRoute());
    }


    @Test
    public void mission1x4() {
        GridMapParser gmp = new GridMapParser(1, 4);
        gmp.consumeLine("1 1 97 1");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 3);
        assertEquals(100, l.calculateBestRoute());
    }


    @Test
    public void mission4x4() {
        GridMapParser gmp = new GridMapParser(4, 4);
        gmp.consumeLine("1 1 1 5");
        gmp.consumeLine("9 1 1 1");
        gmp.consumeLine("8 2 1 1");
        gmp.consumeLine("4 4 4 44");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 11);
        assertEquals(22, l.calculateBestRoute());
    }

    @Test
    public void mission5x5() {
        GridMapParser gmp = new GridMapParser(5, 5);
        gmp.consumeLine("1 8 1 1 1");
        gmp.consumeLine("2 7 6 1 1");
        gmp.consumeLine("3 1 5 4 1");
        gmp.consumeLine("4 1 1 3 2");
        gmp.consumeLine("5 6 7 9 2");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 24);
        assertEquals(45, l.calculateBestRoute());
    }

    @Test
    public void mission5x6() {
        GridMapParser gmp = new GridMapParser(5, 6);
        gmp.consumeLine("1 1 1 1 1 1");
        gmp.consumeLine("2 7 5 1 1 1");
        gmp.consumeLine("3 1 5 5 1 1");
        gmp.consumeLine("1 1 1 5 2 1");
        gmp.consumeLine("1 1 1 9 1 1");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 29);
        assertEquals(41, l.calculateBestRoute());
    }

    @Test
    public void mission10x10() {
        GridMapParser gmp = new GridMapParser(10, 10);
        gmp.consumeLine( "10 10 10 10 10 5 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 20");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 99);
        assertEquals(195, l.calculateBestRoute());
    }

    @Test
    public void mission10x10v2() {
        GridMapParser gmp = new GridMapParser(10, 10);
         gmp.consumeLine("10 10 10 10 10 5 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 10 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
        gmp.consumeLine("-1 -1 -1 -1 -1 -1 10 10 10 10");
         gmp.consumeLine("-1 -1 -1 -1 -1 1 10 10 10 10");
         gmp.consumeLine("-1 -1 -1 -1 -1 1 10 10 10 10");
         gmp.consumeLine("-1 -1 -1 -1 -1 1 10 10 10 10");
         gmp.consumeLine("-1 -1 -1 -1 -1 1 10 10 10 10");
         gmp.consumeLine("-1 -1 -1 -1 -1 1 10 10 21 10");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 99);
        assertEquals(185, l.calculateBestRoute());
    }


    @Test
    public void mission4x4withObstructions() {
        GridMapParser gmp = new GridMapParser(4, 4);
        gmp.consumeLine("0 1 9 5");
        gmp.consumeLine("9 -1 9 -1");
        gmp.consumeLine("8 -1 2 3");
        gmp.consumeLine("-1 -1 1 44");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 11);
        assertEquals(24, l.calculateBestRoute());
    }

    @Test
    public void missionImpossible2() {
        GridMapParser gmp = new GridMapParser(4, 4);
        gmp.consumeLine("10 10 90 5");
        gmp.consumeLine("90 -1 -1 -1");
        gmp.consumeLine("80 -1 20 30");
        gmp.consumeLine("-1 -1 10 44");
        FuelPropertyGraph g = gmp.getGraph();
        RouteFinderLogic l = new RouteFinderLogic(g, 0, 11);
        assertTrue(l.calculateBestRoute() <= 0);
    }

}
