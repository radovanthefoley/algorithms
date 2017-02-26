import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class BodyTest {

    private Body body;

    @Before
    public void init() {
        body = new Body(0, 0, 0, 0, 10, Color.WHITE);
    }

    @Test
    public void testPlusReflexivity() {
        Body bodyToSum = new Body(10, 10, 0, 0, 10, Color.WHITE);
        Body result1 = body.plus(bodyToSum);
        assertNotNull(result1);
        Body result2 = bodyToSum.plus(body);
        assertNotNull(result2);

        assertEquals(result1.getMass(), result2.getMass(), 0.0);
    }

    @Test
    public void testPlusCorrectness() {
        Body bodyToSum = new Body(10, 10, 0, 0, 10, Color.WHITE);
        Body result = body.plus(bodyToSum);
        assertNotNull(result);
        assertEquals(20, result.getMass(), 0.0);
        assertEquals(5, result.getRx(), 0.0);
        assertEquals(5, result.getRy(), 0.0);
    }

    @Test
    public void testPlusCorrectness2() {
        Body bodyToSum = new Body(-10, -10, 0, 0, 30, Color.WHITE);
        Body result = body.plus(bodyToSum);
        assertNotNull(result);
        assertEquals(40, result.getMass(), 0.0);
        assertEquals(-7.5, result.getRx(), 0.0);
        assertEquals(-7.5, result.getRy(), 0.0);
    }

    @Test
    public void testPlusCorrectness3() {
        Body bodyToSum = new Body(-10, -10, 0, 0, 0, Color.WHITE);
        Body result = body.plus(bodyToSum);
        assertNotNull(result);
        assertEquals(10, result.getMass(), 0.0);
        assertEquals(0, result.getRx(), 0.0);
        assertEquals(0, result.getRy(), 0.0);
    }

}
