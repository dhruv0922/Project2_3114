import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author CS Staff
 * 
 * @version 2024.1
 */
public class KVPairTest extends TestCase {

    private KVPair<String, String> pair1;
    private KVPair<String, String> pair2;
    private KVPair<String, String> pair3;
    private KVPair<String, String> pair4;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        pair1 = new KVPair<>("Key1", "Value1");
        pair2 = new KVPair<>("Key2", "Value2");
        pair3 = new KVPair<>(null, "Value3");
        pair4 = new KVPair<>("", "");
    }


    /**
     * Tests the getKey method.
     */
    public void testGetKey() {
        assertEquals("Key1", pair1.getKey());
        assertEquals("Key2", pair2.getKey());
        assertNull(pair3.getKey());
        assertEquals("", pair4.getKey());
    }


    /**
     * Tests the getValue method.
     */
    public void testGetValue() {
        assertEquals("Value1", pair1.getValue());
        assertEquals("Value2", pair2.getValue());
        assertEquals("Value3", pair3.getValue());
        assertEquals("", pair4.getValue());
    }


    /**
     * Tests the toString method.
     */
    public void testToString() {
        assertEquals("(Key1, Value1)", pair1.toString());
        assertEquals("(Key2, Value2)", pair2.toString());
        assertEquals("(null, Value3)", pair3.toString());
        assertEquals("(, )", pair4.toString());
    }


    /**
     * Tests the KVPair constructor with valid inputs.
     */
    public void testConstructorValidInputs() {
        KVPair<String, String> pair = new KVPair<>("Key", "Value");
        assertEquals("Key", pair.getKey());
        assertEquals("Value", pair.getValue());
    }


    /**
     * Tests the KVPair constructor with null key.
     */
    public void testConstructorNullKey() {
        KVPair<String, String> pair = new KVPair<>(null, "Value");
        assertNull(pair.getKey());
        assertEquals("Value", pair.getValue());
    }


    /**
     * Tests the KVPair constructor with null value.
     */
    public void testConstructorNullValue() {
        KVPair<String, String> pair = new KVPair<>("Key", null);
        assertEquals("Key", pair.getKey());
        assertNull(pair.getValue());
    }


    /**
     * Tests the KVPair constructor with null key and value.
     */
    public void testConstructorNullKeyAndValue() {
        KVPair<String, String> pair = new KVPair<>(null, null);
        assertNull(pair.getKey());
        assertNull(pair.getValue());
    }
}
