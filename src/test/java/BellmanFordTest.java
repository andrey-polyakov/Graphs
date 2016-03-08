import com.ea.graph.BellmanFord;
import com.ea.graph.FuelPropertyGraph;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 *
 */
public class BellmanFordTest {

    @Test
    public void normalMatrix() {
        Integer matrix[][] = {
                {0, 1, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0}
        };
        int expected[] = {0, 1, 1, 2, 3, 4};
        FuelPropertyGraph g = new FuelPropertyGraph(matrix);
        BellmanFord bellmanford = new BellmanFord(g);
        assertArrayEquals(expected, bellmanford.evaluate(0));
    }

    @Test
    public void isolatedVertex() {
        Integer matrix[][] = {
                {0, 1, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int expected[] = {0, 1, 1, 2, 3, 0};
        FuelPropertyGraph g = new FuelPropertyGraph(matrix);
        BellmanFord bellmanford = new BellmanFord(g);
        int[] distances = bellmanford.evaluate(0);
        assertArrayEquals(expected, distances);
    }

    @Test
    public void zeroMatrix() {
        Integer matrix[][] = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int expected[] = {0, 0, 0, 0, 0, 0};
        FuelPropertyGraph g = new FuelPropertyGraph(matrix);
        BellmanFord bellmanford = new BellmanFord(g);
        int[] distances = bellmanford.evaluate(0);
        assertArrayEquals(expected, distances);
    }

}
