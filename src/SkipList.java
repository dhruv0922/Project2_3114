import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import student.TestableRandom;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List
    private Random rng;
    private TestableRandom value;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 1);
        size = 0;
        value = new TestableRandom();
    }


    /**
     * randomLevel() method
     * 
     * @return a random level using geometric distribution
     */

    public int randomLevel() {
        int level;
        for (level = 0; Math.abs(value.nextInt()) % 2 == 0; level++) {
            // Do nothing
        }
        return level;
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * @return result
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        ArrayList<KVPair<K, V>> result = new ArrayList<>();
        SkipNode x = head;
        while (x != null && x.forward[0] != null) {
            int cmp = x.forward[0].element().getKey().compareTo(key);
            if (cmp < 0) {
                x = x.forward[0];
            }
            else {
                while (x.forward[0] != null && x.forward[0].element().getKey()
                    .compareTo(key) == 0) {
                    result.add(x.forward[0].element());
                    x = x.forward[0];
                }
                break;
            }
        }
        return result;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int level = randomLevel();
        if (level > head.level) {
            adjustHead(level);
        }

        SkipNode newNode = new SkipNode(it, level);
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, level + 1);

        SkipNode x = head;
        for (int i = level; i >= 0; i--) {
            while (x.forward[i] != null && x.forward[i].element().getKey()
                .compareTo(it.getKey()) < 0) {
                x = x.forward[i];
            }
            update[i] = x;
        }

        for (int i = 0; i <= level; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
        size++;
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel) {
        SkipNode newHead = new SkipNode(head.element(), newLevel);
        for (int i = 0; i <= head.level; i++) {
            newHead.forward[i] = head.forward[i];
        }
        for (int i = head.level + 1; i <= newLevel; i++) {
            newHead.forward[i] = null;
        }
        head = newHead;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode x = head;
        for (int i = head.level; i >= 0; i--) {
            while (x.forward[i] != null && x.forward[i].element().getKey()
                .compareTo(key) < 0) {
                x = x.forward[i];
            }
            update[i] = x;
        }

        x = x.forward[0];
        if (x != null && x.element().getKey().compareTo(key) == 0) {
            // The key exists in the skip list, remove it
            for (int i = 0; i <= head.level; i++) {
                if (update[i].forward[i] != x) {
                    break;
                }
                update[i].forward[i] = x.forward[i];
            }

            // Reduce the level of the head node if necessary
            while (head.level > 0 && head.forward[head.level] == null) {
                head.level--;
            }

            size--;
            return x.element();
        }
        else {
            // The key does not exist in the skip list, return null
            return null;
        }
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> removeByValue(V val) {
        SkipNode x = head.forward[0];
        while (x != null && x.forward[0] != null) {
            if (x.forward[0].element() != null && x.forward[0].element()
                .getValue().equals(val)) {
                return remove(x.forward[0].element().getKey());
            }
            x = x.forward[0];
        }
        return null;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {

        System.out.println("SkipList dump:");

        SkipNode node = head;
        while (node != null) {
            int depth = node.level;
// KVPair<K, V> value = node.pair;
            System.out.print("Node has depth " + depth + ", Value (" + node
                .element());
            System.out.print(")");
            System.out.println();
            node = node.forward[0];
        }

        System.out.println("SkipList size is: " + size);

    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            return current.forward[0] != null;
        }


        @Override
        public KVPair<K, V> next() {
            KVPair<K, V> elem = current.forward[0].element();
            current = current.forward[0];
            return elem;
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
