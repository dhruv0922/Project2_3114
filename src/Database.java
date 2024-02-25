import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * Also note that the Database class will have a clearer role in Project2,
 * where we will have two data structures. The Database class will then
 * determine
 * which command should be directed to which data structure.
 * hi
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    // This is an Iterator object over the SkipList to loop through it from
    // outside the class.
    // You will need to define an extra Iterator for the intersections method.
    private Iterator<KVPair<String, Rectangle>> itr1;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that
        Rectangle rect = pair.getValue();

        // Check if the rectangle has valid coordinates and dimensions
        if (rect.isInvalid()) {
            System.out.println("Rectangle rejected: (" + pair.getKey() + ", "
                + rect.toString() + ")");
        }

        // Insert the KVPair into the SkipList
        else {
            list.insert(pair);
            System.out.println("Rectangle inserted: (" + pair.getKey() + ", "
                + rect.toString() + ")");
        }
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, Rectangle> removedPair = list.remove(name);
        if (removedPair != null) {
            System.out.println("Rectangle removed: (" + removedPair.getKey()
                + ", " + removedPair.getValue().toString() + ")");
        }
        else {
            System.out.println("Rectangle not removed: " + name);
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        Rectangle rectToRemove = new Rectangle(x, y, w, h);
        KVPair<String, Rectangle> removedPair = list.removeByValue(
            rectToRemove);
        if (removedPair != null) {
            System.out.println("Rectangle removed: (" + removedPair.getKey()
                + ", " + removedPair.getValue().toString() + ")");
        }
        else {
            System.out.println("Rectangle rejected: " + rectToRemove
                .toString());
        }
    }


    /**
     * Searches for all rectangles that intersect with the given region.
     * 
     * @param x
     *            x-coordinate of the region
     * @param y
     *            y-coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        // Check if the width or height is less than or equal to 0
        if (w <= 0 || h <= 0) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
            return;
        }

        Rectangle region = new Rectangle(x, y, w, h);
        System.out.println("Rectangles intersecting region (" + x + ", " + y
            + ", " + w + ", " + h + "):");
        itr1 = list.iterator();
        while (itr1.hasNext()) {
            KVPair<String, Rectangle> pair = itr1.next();
            if (pair.getValue().intersect(region)) {
                System.out.println("  (" + pair.getKey() + ", " + pair
                    .getValue().toString() + ")");
            }
        }
    }


    /**
     * Finds all pairs of rectangles that intersect with each other.
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        itr1 = list.iterator();
        while (itr1.hasNext()) {
            KVPair<String, Rectangle> pair1 = itr1.next();
            Iterator<KVPair<String, Rectangle>> itr2 = list.iterator();
            while (itr2.hasNext()) {
                KVPair<String, Rectangle> pair2 = itr2.next();
                if ((pair1 != pair2) && pair1.getValue()
                    .intersect(pair2.getValue())) {
                    System.out.println("  (" + pair1.getKey() + ", " + pair1
                        .getValue().toString() + " | " + pair2.getKey() + ", "
                        + pair2.getValue().toString() + ")");
                }
            }
        }
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Rectangle>> results = list.search(name);
        if (results.isEmpty()) {
            System.out.println("Rectangle not found: " + name);
        }
        else {
            System.out.println("Rectangles found matching \"" + name + "\":");
            for (KVPair<String, Rectangle> pair : results) {
                System.out.println("  (" + pair.getKey() + ", " + pair
                    .getValue().toString() + ")");
            }
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
