import java.util.Iterator;

import org.junit.Test;
import java.util.ArrayList;

import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the methods of SkipList class
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 */

public class SkipListTest extends TestCase {

    private SkipList<Integer, String> sl;

    /**
     * setUp method
     */
    public void setUp() {
        sl = new SkipList<>();
        TestableRandom.setNextInts(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3,
            4, 1, 2, 3, 4);
        sl.insert(new KVPair<>(1, "one"));
        sl.insert(new KVPair<>(2, "two"));
        sl.insert(new KVPair<>(3, "three"));
        sl.insert(new KVPair<>(4, "four"));
        sl.insert(new KVPair<>(5, "five"));
    }


    /**
     * tests dump()
     */
    public void testDump() {
        sl.dump();
        assertFuzzyEquals("SkipList dump:\n"
            + "Node has depth 1, Value (null)\n"
            + "Node has depth 0, Value ((1, one))\n"
            + "Node has depth 1, Value ((2, two))\n"
            + "Node has depth 1, Value ((3, three))\n"
            + "Node has depth 1, Value ((4, four))\n"
            + "Node has depth 1, Value ((5, five))\n" + "SkipList size is: 5",
            systemOut().getHistory());
    }

    /**
     * tests empty list
     */
    public void testEmptyList() {
        SkipList<Integer, String> emptyList = new SkipList<>();
        emptyList.dump();
        assertFuzzyEquals("skiplist dump\n" + "node has depth 1 value null\n"
            + "skiplist size is 0", systemOut().getHistory());
    }


    /**
     * tests size()
     */
    @Test
    public void testSize() {
        assertEquals(5, sl.size());
    }


    /**
     * tests search()
     */
    @Test
    public void testSearch() {
        ArrayList<KVPair<Integer, String>> result = sl.search(3);
        assertEquals(1, result.size());
        assertEquals("three", result.get(0).getValue());
    }


    /**
     * tests insert()
     */
    @Test
    public void testInsert() {
        sl.insert(new KVPair<>(6, "six"));
        assertEquals(6, sl.size());
        ArrayList<KVPair<Integer, String>> result = sl.search(6);
        assertEquals(1, result.size());
        assertEquals("six", result.get(0).getValue());
    }


    /**
     * tests remove()
     */
    @Test
    public void testRemove() {
        KVPair<Integer, String> removed = sl.remove(4);
        assertEquals("four", removed.getValue());
        assertEquals(4, sl.size());
    }


    /**
     * tests removeByValue()
     */
    @Test
    public void testRemoveByValue() {
        KVPair<Integer, String> removed = sl.removeByValue("two");
        assertEquals("two", removed.getValue());
        assertEquals(4, sl.size());
    }


    /**
     * tests iterator()
     */
    @Test
    public void testIterator() {
        Iterator<KVPair<Integer, String>> iterator = sl.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("one", iterator.next().getValue());
        assertEquals("two", iterator.next().getValue());
        assertEquals("three", iterator.next().getValue());
        assertEquals("four", iterator.next().getValue());
        assertEquals("five", iterator.next().getValue());
        assertFalse(iterator.hasNext());
    }


    /**
     * tests insert() with negative key
     */
    @Test
    public void testInsertNegative() {
        sl.insert(new KVPair<>(-1, "minus one"));
        assertEquals(6, sl.size());
        ArrayList<KVPair<Integer, String>> result = sl.search(-1);
        assertEquals(1, result.size());
        assertEquals("minus one", result.get(0).getValue());
    }


    /**
     * tests search() with a key that does not exist
     */
    @Test
    public void testSearchNonExistentKey() {
        ArrayList<KVPair<Integer, String>> result = sl.search(100);
        assertEquals(0, result.size()); // should not find any result
    }

// /**
// * tests search() with a null key
// */
// @Test
// public void testSearchNullKey() {
// ArrayList<KVPair<Integer, String>> result = sl.search(null);
// assertEquals(0, result.size()); // should not find any result
// }


    /**
     * tests search() with a negative key
     */
    @Test
    public void testSearchNegativeKey() {
        sl.insert(new KVPair<>(-1, "minus one"));
        ArrayList<KVPair<Integer, String>> result = sl.search(-1);
        assertEquals(1, result.size()); // should find the inserted value
        assertEquals("minus one", result.get(0).getValue());
    }

}
