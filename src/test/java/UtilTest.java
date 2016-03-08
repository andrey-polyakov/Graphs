import com.ea.graph.Util;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Андрей on 31.07.2015.
 */
public class UtilTest {

    @Test
    public void coordinatesX1() {
        assertEquals(1, Util.parseFirstCoordinates("1 0 0 0", 1));
    }

    @Test
    public void coordinatesX2() {
        assertEquals(0, Util.parseFirstCoordinates("0 0 0 0", 1));
    }

    @Test
    public void coordinatesX3() {
        assertEquals(6, Util.parseFirstCoordinates("1 1 0 0", 5));
    }

    @Test
    public void coordinatesX4() {
        assertEquals(0, Util.parseFirstCoordinates("0 0 0 0", 5));
    }


}
