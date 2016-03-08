import com.ea.graph.BellmanFord;
import com.ea.graph.FuelPropertyGraph;
import com.ea.graph.GridMapParser;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Андрей on 31.07.2015.
 */
public class IntegrationTest {

    @Test
    public void allTogether() {
        GridMapParser gmp = new GridMapParser(10, 10);
        gmp.consumeLine("1 2 3 4 5 6 7 8 9 10");
        gmp.consumeLine("1 2 3 4 5 6 7 8 9 11");
        gmp.consumeLine("1 2 3 4 5 6 7 8 9 12");
        gmp.consumeLine("1 2 3 4 5 6 7 8 9 10");
        gmp.consumeLine("1 2 3 4 5 6 7 8 9 10");
        gmp.consumeLine("1 2 3 4 5 6 7 8 9 10");
        gmp.consumeLine("1 2 3 1 5 6 7 8 9 10");
        gmp.consumeLine("1 2 1 4 5 6 7 8 9 10");
        gmp.consumeLine("1 1 3 4 5 6 7 8 9 10");
        gmp.consumeLine("9 2 3 4 5 6 7 8 9 10");

        FuelPropertyGraph g = gmp.getGraph();
        BellmanFord bellmanford = new BellmanFord(g);
        int[] distances = bellmanford.evaluate(99);
        assertEquals(2, distances[97]);
    }

    @Test
    public void allTogetherSmall() {
        GridMapParser gmp = new GridMapParser(5, 5);
        gmp.consumeLine("9 2 6 4 5");
        gmp.consumeLine("1 2 6 4 2");
        gmp.consumeLine("1 2 6 4 5");
        gmp.consumeLine("4 2 6 8 5");
        gmp.consumeLine("6 9 3 4 10");
        FuelPropertyGraph g = gmp.getGraph();
        BellmanFord bellmanford = new BellmanFord(g);
        int[] distances = bellmanford.evaluate(24);
        assertEquals(2, distances[22]);
    }



}
