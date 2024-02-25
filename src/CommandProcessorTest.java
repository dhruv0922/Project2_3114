import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly.
 * Also test passing improper command to ensure
 * all class functionalities work as intended.
 * 
 * @author Abdullah Khurram (abkhur)
 *         hi
 * @version 1
 */
public class CommandProcessorTest extends TestCase {

    /**
     * The setUp() method will be called automatically before
     * each test and reset whatever the test modified. For this
     * test class, only a new database object is needed, so
     * create a database here for use in each test case.
     */
    private CommandProcessor cp;
    
    /**
     * Sets up commandprocessortest
     */
    public void setUp() {
        cp = new CommandProcessor();
    }


    /**
     * Tests processor overall
     */
    public void testOverallProcessor() {
        TestableRandom.setNextInts(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3,
            4, 1, 2, 3, 4);
        cp.processor("dump");
        cp.processor("intersections");
        cp.processor("insert rec 1 1 2 3");
        cp.processor("dump");
        cp.processor("remove rec");
        cp.processor("remove 100 100 100 100");
        cp.processor("dump");
        cp.processor("regionsearch 1 2 0 0");
        cp.processor("regionsearch 1 2 -1 -1");
        cp.processor("regionsearch 1 2 -1 3");
        cp.processor("regionsearch 1 2 1 -3");
        cp.processor("regionsearch -100 -100 -100 -100");
        cp.processor("insert w 0 0 1000 10");
        cp.processor("insert x 0 0 910 10");
        cp.processor("regionsearch 900 5 0 0");
        cp.processor("insert rec2 1 2 3 4");
        cp.processor("insert rec3 1 2 3 4");
        cp.processor("regionsearch 1 5 5 5");
        cp.processor("regionsearch 1 5 -5 -5");
        cp.processor("regionsearch 100 100 900 900");
        cp.processor("intersections");
        cp.processor("search rec2");
        cp.processor("insert rec 1 1 3 4");
        cp.processor("insert invalidrec -1 -1 -1 -2");
        cp.processor("intersections");
        cp.processor("remove 1 2 3 4");
        cp.processor("remove 1 1 3 4");
        cp.processor("insert a 10 10 5 5");
        cp.processor("insert a 1 2 3 4");
        cp.processor("search a");
        cp.processor("insert b 11 11 5 5");
        cp.processor("remove 10 20 30 40");
        cp.processor("remove -10 20 30 40");
        cp.processor("insert c 10 20 30 40");
        cp.processor("remove c");
        cp.processor("intersections");
        cp.processor("dump");
        cp.processor("blah");
        assertFuzzyEquals("SkipList dump:\n"
            + "Node has depth 1, Value (null)\n"
            + "SkipList size is: 0\n"
            + "Intersection pairs:\n"
            + "Rectangle inserted: (rec, 1, 1, 2, 3)\n"
            + "SkipList dump:\n"
            + "Node has depth 1, Value (null)\n"
            + "Node has depth 0, Value ((rec, 1, 1, 2, 3))\n"
            + "SkipList size is: 1\n"
            + "Rectangle removed: (rec, 1, 1, 2, 3)\n"
            + "Rectangle rejected: 100, 100, 100, 100\n"
            + "SkipList dump:\n"
            + "Node has depth 0, Value (null)\n"
            + "SkipList size is: 0\n"
            + "Rectangle rejected: (1, 2, 0, 0)\n"
            + "Rectangle rejected: (1, 2, -1, -1)\n"
            + "Rectangle rejected: (1, 2, -1, 3)\n"
            + "Rectangle rejected: (1, 2, 1, -3)\n"
            + "Rectangle rejected: (-100, -100, -100, -100)\n"
            + "Rectangle inserted: (w, 0, 0, 1000, 10)\n"
            + "Rectangle inserted: (x, 0, 0, 910, 10)\n"
            + "Rectangle rejected: (900, 5, 0, 0)\n"
            + "Rectangle inserted: (rec2, 1, 2, 3, 4)\n"
            + "Rectangle inserted: (rec3, 1, 2, 3, 4)\n"
            + "Rectangles intersecting region (1, 5, 5, 5):\n"
            + "  (rec2, 1, 2, 3, 4)\n"
            + "  (rec3, 1, 2, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10)\n"
            + "  (x, 0, 0, 910, 10)\n"
            + "Rectangle rejected: (1, 5, -5, -5)\n"
            + "Rectangles intersecting region (100, 100, 900, 900):\n"
            + "Intersection pairs:\n"
            + "  (rec2, 1, 2, 3, 4 | rec3, 1, 2, 3, 4)\n"
            + "  (rec2, 1, 2, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (rec2, 1, 2, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (rec3, 1, 2, 3, 4 | rec2, 1, 2, 3, 4)\n"
            + "  (rec3, 1, 2, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (rec3, 1, 2, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (w, 0, 0, 1000, 10 | rec2, 1, 2, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | rec3, 1, 2, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | x, 0, 0, 910, 10)\n"
            + "  (x, 0, 0, 910, 10 | rec2, 1, 2, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | rec3, 1, 2, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | w, 0, 0, 1000, 10)\n"
            + "Rectangles found matching \"rec2\":\n"
            + "  (rec2, 1, 2, 3, 4)\n"
            + "Rectangle inserted: (rec, 1, 1, 3, 4)\n"
            + "Rectangle rejected: (invalidrec, -1, -1, -1, -2)\n"
            + "Intersection pairs:\n"
            + "  (rec, 1, 1, 3, 4 | rec2, 1, 2, 3, 4)\n"
            + "  (rec, 1, 1, 3, 4 | rec3, 1, 2, 3, 4)\n"
            + "  (rec, 1, 1, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (rec, 1, 1, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (rec2, 1, 2, 3, 4 | rec, 1, 1, 3, 4)\n"
            + "  (rec2, 1, 2, 3, 4 | rec3, 1, 2, 3, 4)\n"
            + "  (rec2, 1, 2, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (rec2, 1, 2, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (rec3, 1, 2, 3, 4 | rec, 1, 1, 3, 4)\n"
            + "  (rec3, 1, 2, 3, 4 | rec2, 1, 2, 3, 4)\n"
            + "  (rec3, 1, 2, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (rec3, 1, 2, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (w, 0, 0, 1000, 10 | rec, 1, 1, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | rec2, 1, 2, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | rec3, 1, 2, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | x, 0, 0, 910, 10)\n"
            + "  (x, 0, 0, 910, 10 | rec, 1, 1, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | rec2, 1, 2, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | rec3, 1, 2, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | w, 0, 0, 1000, 10)\n"
            + "Rectangle removed: (rec2, 1, 2, 3, 4)\n"
            + "Rectangle rejected: 1, 1, 3, 4\n"
            + "Rectangle inserted: (a, 10, 10, 5, 5)\n"
            + "Rectangle inserted: (a, 1, 2, 3, 4)\n"
            + "Rectangles found matching \"a\":\n"
            + "  (a, 1, 2, 3, 4)\n"
            + "  (a, 10, 10, 5, 5)\n"
            + "Rectangle inserted: (b, 11, 11, 5, 5)\n"
            + "Rectangle rejected: 10, 20, 30, 40\n"
            + "Rectangle rejected: -10, 20, 30, 40\n"
            + "Rectangle inserted: (c, 10, 20, 30, 40)\n"
            + "Rectangle removed: (c, 10, 20, 30, 40)\n"
            + "Intersection pairs:\n"
            + "  (a, 1, 2, 3, 4 | rec, 1, 1, 3, 4)\n"
            + "  (a, 1, 2, 3, 4 | rec3, 1, 2, 3, 4)\n"
            + "  (a, 1, 2, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (a, 1, 2, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (a, 10, 10, 5, 5 | b, 11, 11, 5, 5)\n"
            + "  (b, 11, 11, 5, 5 | a, 10, 10, 5, 5)\n"
            + "  (rec, 1, 1, 3, 4 | a, 1, 2, 3, 4)\n"
            + "  (rec, 1, 1, 3, 4 | rec3, 1, 2, 3, 4)\n"
            + "  (rec, 1, 1, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (rec, 1, 1, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (rec3, 1, 2, 3, 4 | a, 1, 2, 3, 4)\n"
            + "  (rec3, 1, 2, 3, 4 | rec, 1, 1, 3, 4)\n"
            + "  (rec3, 1, 2, 3, 4 | w, 0, 0, 1000, 10)\n"
            + "  (rec3, 1, 2, 3, 4 | x, 0, 0, 910, 10)\n"
            + "  (w, 0, 0, 1000, 10 | a, 1, 2, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | rec, 1, 1, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | rec3, 1, 2, 3, 4)\n"
            + "  (w, 0, 0, 1000, 10 | x, 0, 0, 910, 10)\n"
            + "  (x, 0, 0, 910, 10 | a, 1, 2, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | rec, 1, 1, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | rec3, 1, 2, 3, 4)\n"
            + "  (x, 0, 0, 910, 10 | w, 0, 0, 1000, 10)\n"
            + "SkipList dump:\n"
            + "Node has depth 1, Value (null)\n"
            + "Node has depth 1, Value ((a, 1, 2, 3, 4))\n"
            + "Node has depth 1, Value ((a, 10, 10, 5, 5))\n"
            + "Node has depth 1, Value ((b, 11, 11, 5, 5))\n"
            + "Node has depth 1, Value ((rec, 1, 1, 3, 4))\n"
            + "Node has depth 1, Value ((rec3, 1, 2, 3, 4))\n"
            + "Node has depth 1, Value ((w, 0, 0, 1000, 10))\n"
            + "Node has depth 1, Value ((x, 0, 0, 910, 10))\n"
            + "SkipList size is: 7\n"
            + "Unrecognized command.\n"
            + "",
            systemOut().getHistory());
    }

}
