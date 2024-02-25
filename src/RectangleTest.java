
import student.TestCase;

/**
 * This class tests the methods of Rectangle class,
 * ensuring that they work as they should.
 * 
 * @author Abdullah Khurram
 * 
 * @version 1
 */
public class RectangleTest extends TestCase {

    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;
    private Rectangle rectangle1copy;

    /**
     * Sets up the tests
     */
    public void setUp() {
        rectangle1 = new Rectangle(0, 0, 5, 5);
        rectangle1copy = new Rectangle(0, 0, 5, 5);
        rectangle2 = new Rectangle(3, 3, 5, 5);
        rectangle3 = new Rectangle(10, 10, 5, 5);
    }


    /**
     * Tests the intersect method.
     */
    public void testIntersect() {
        assertTrue(rectangle1.intersect(rectangle2));
        assertFalse(rectangle1.intersect(rectangle3));
    }


    /**
     * Tests the equals method.
     */
    public void testEquals() {
        assertFalse(rectangle1.equals(rectangle2));
        assertFalse(rectangle1.equals(null));
        assertTrue(rectangle1.equals(rectangle1));
        assertFalse(rectangle1.equals(new Object()));
        Rectangle rectangle4 = new Rectangle(0, 0, 5, 5);
        assertTrue(rectangle1.equals(rectangle4));
    }


    /**
     * Tests the isInvalid method.
     */
    public void testIsInvalid() {
        assertFalse(rectangle1.isInvalid());
        Rectangle rectangle5 = new Rectangle(0, 0, -5, 5);
        assertTrue(rectangle5.isInvalid());
        Rectangle rectangle6 = new Rectangle(-1, -1, 0, 0);
        assertTrue(rectangle6.isInvalid());
    }


    /**
     * Tests the getters.
     */
    public void testGetters() {
        assertEquals(0, rectangle1.getxCoordinate());
        assertEquals(0, rectangle1.getyCoordinate());
        assertEquals(5, rectangle1.getWidth());
        assertEquals(5, rectangle1.getHeight());
    }


    /**
     * Tests the toString method.
     */
    public void testToString() {
        assertEquals("0, 0, 5, 5", rectangle1.toString());
    }


    /**
     * Tests the intersect method with rectangles that touch but do not
     * intersect.
     */
    public void testIntersectTouch() {
        Rectangle rectangle4 = new Rectangle(8, 8, 1, 1);
        assertFalse(rectangle1.intersect(rectangle4));
    }


    /**
     * Tests the equals method with a rectangle that has the same dimensions but
     * different coordinates.
     */
    public void testEqualsDifferentCoordinates() {
        Rectangle rectangle5 = new Rectangle(1, 1, 5, 5);
        assertFalse(rectangle1.equals(rectangle5));

    }


    /**
     * Tests the intersect method with a rectangle that is contained within
     * another rectangle.
     */
    public void testIntersectContained() {
        Rectangle rectangle6 = new Rectangle(1, 1, 3, 3);
        assertTrue(rectangle1.intersect(rectangle6));
    }


    /**
     * Tests the equals method with a rectangle that has the same coordinates
     * but different dimensions.
     */
    public void testEqualsDifferentDimensions() {
        Rectangle rectangle7 = new Rectangle(0, 0, 6, 6);
        assertFalse(rectangle1.equals(rectangle7));
    }


    /**
     * Tests the isInvalid method with a rectangle that has zero width and
     * height.
     */
    public void testIsInvalidZeroDimensions() {
        Rectangle rectangle8 = new Rectangle(0, 0, 0, 0);
        assertTrue(rectangle8.isInvalid());
    }


    /**
     * test intersect p 2
     */
    public void testIntersectp2() {
        // Testing intersect method with rectangles that intersect
        assertTrue("rect1 intersects rect2", rectangle1.intersect(rectangle2));

        // Testing intersect method with rectangles that do not intersect
        assertFalse("rect1 intersects rect3", rectangle1.intersect(rectangle3));
    }


    /**
     * test equal p 2
     */
    public void testEqualsp2() {
        // Testing equals method with two rectangles having same coordinates and
        // dimensions
        assertTrue("rect1 equals rect2", rectangle1.equals(rectangle1copy));

        // Testing equals method with two rectangles having different
        // coordinates
        assertFalse("rect1 equals rect3", rectangle1.equals(rectangle3));
    }
}
