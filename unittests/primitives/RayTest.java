package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Ray class
 *
 * @author Avraham Hassson
 */
public class RayTest {

    /**
     * Test method for {@link Ray#getP0()}.
     */
    @Test
    public void testGetP0() {
        Ray r = new Ray(new Point(1,1,1),new Vector(2,2,2));
        Point result = new Point(1,1,1);
        assertEquals(r.getP0(), result, "does not return the correct point");
    }
    /**
     * Test method for {@link Ray#getDir()}.
     */
    @Test
    public void testGetDir() {
        Ray r = new Ray(new Point(1,1,1),new Vector(2,2,2));
        Point result = new Vector(2,2,2).normalize();
        assertEquals(r.getDir(), result, "does not return the correct direction");
    }
    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partition Test ==============
        // TC01: the closest point is in the middle of the list.
        Ray r = new Ray(new Point(1,1,1),new Vector(2,2,2));
        Point p1 = new Point(3,2,3);
        Point p2 = new Point(1, 2, 1);
        Point p3 = new Point(3, 4, 5);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> r.findClosestPoint(List.of(p1,p2,p3)), "findClosestPoint() throws an unexpected exception");
        // generate the test result
        Point result = r.findClosestPoint(List.of(p1,p2,p3));
        assertEquals(p2, result, "The correct point is in the middle of the list");
        // =============== Boundary Values Tests ==================
        // TC11: empty list.
        assertNull(r.findClosestPoint(List.of()), "There are no points listed");
        // TC12: The closest point is at the beginning of the list.
        // generate the test result
        result = r.findClosestPoint(List.of(p2,p1,p3));
        assertEquals(p2, result, "The correct point is in the beginning of the list");
        // TC13: The closest point is at the end of the list.
        // generate the test result
        result = r.findClosestPoint(List.of(p3,p1,p2));
        assertEquals(p2, result, "The correct point is in the end of the list");
    }
    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    @Test
    public void testGetPoint() {
        //Ray initialization.
        Point p0 = Point.ZERO;
        Vector dir = new Vector(1, 0, 0);
        Ray ray = new Ray(p0, dir);

        // ============ Equivalence Partition Test ==============
        // TC01: t is positive (1).
        double t = 1;
        Point result = ray.getPoint(t);
        Point expected = new Point(1,0,0);
        assertEquals(expected, result, "The correct point is (1,0,0)");
        // TC02: t is negative (-1).
        t = -1;
        result = ray.getPoint(t);
        expected = new Point(-1,0,0);
        assertEquals(expected, result, "The correct point is (-1,0,0)");
        // =============== Boundary Values Tests ==================
        // TC11: t is zero.
        t = 0;
        result = ray.getPoint(t);
        expected = Point.ZERO;
        assertEquals(expected, result, "The correct point is (0,0,0)");

    }

}
