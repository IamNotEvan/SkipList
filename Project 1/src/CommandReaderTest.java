import java.io.FileNotFoundException;
import student.TestCase;
import student.TestableRandom;
/**
 * Test class for CommandReader class
 * 
 * @author Evan Lee (evan0110)
 * @version 09.04.2022
 *
 */
public class CommandReaderTest extends student.TestCase {
    
    private CommandReader a;
    private CommandReader b;
    
    /**
     * Set up method for this test class
     */
    public void setUp()
    {
        a = new CommandReader();
        student.TestableRandom.setNextInts(new int[] {5, 4, 3});
        b = new CommandReader("testingInput");
    }
    
    /**
     * This method tests insertingRec() method
     */
    public void testInsetingRec()
    {
        assertEquals("Rectangle rejected: (a, 1, 1, -3, 3)\n",
            a.insertingRec("a", 1, 1, -3, 3));
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals(a.getSkipList().getSize(), 1);
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals(a.getSkipList().getSize(), 2);
    }
    
    /**
     * This method tests dumpRecs() method
     */
    public void testDumpRecs()
    {
        assertEquals("SkipList dump:\n"
            + "Node has depth 1, Value (null)\n"
            + "SkipList size is: 0\n", a.dumpRecs());
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals("Rectangle inserted: (b, 1, 1, 4, 3)\n",
            a.insertingRec("b", 1, 1, 4, 3));
        assertEquals("Rectangle inserted: (c, 1, 1, 4, 3)\n",
            a.insertingRec("c", 1, 1, 4, 3));
//        assertEquals("SkipList dump:\n"
//            + "Node has depth 6, Value (null)\n"
//            + "Node has depth 6, Value (a, 1, 1, 3, 3)\n"
//            + "Node has depth 5, Value (b, 1, 1, 4, 3)\n"
//            + "Node has depth 4, Value (c, 1, 1, 4, 3)\n"
//            + "SkipList size is: 3\n", a.dumpRecs());
    }
    
    /**
     * This method tests removeByName() method
     */
    public void testRemoveByName()
    {
        assertEquals("Rectangle not removed: (c)\n", a.removeByName("c"));
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals("Rectangle removed: (a, 1, 1, 3, 3)\n",
            a.removeByName("a"));
        
    }
    
    /**
     * This method tests removeByCoords method
     */
    public void testRemoveByCoords()
    {
        assertEquals("Rectangle not removed: (1, 1, 1, 1)\n",
            a.removeByCoords(1, 1, 1, 1));
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals("Rectangle removed: (a, 1, 1, 3, 3)\n",
            a.removeByCoords(1, 1, 3, 3));
        assertEquals("Rectangle not removed: (1, 1, 1, 1)\n",
            a.removeByCoords(1, 1, 1, 1));
    }
    
    /**
     * This method tests regionSearch method
     */
    public void testRegionSearch()
    {
        assertEquals("Rectangle rejected: (1, 1, 0, 5)\n",
            a.regionSearch(1, 1, 0, 5));
        assertEquals("Rectangle rejected: (1, 1, 5, 0)\n",
            a.regionSearch(1, 1, 5, 0));
        assertEquals("Rectangles intersecting region (1, 1, 3, 3):\n",
            a.regionSearch(1, 1, 3, 3));
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals("Rectangle inserted: (b, 1, 1, 2, 2)\n",
            a.insertingRec("b", 1, 1, 2, 2));
        assertEquals("Rectangle inserted: (b, 5, 5, 2, 2)\n",
            a.insertingRec("b", 5, 5, 2, 2));
        assertEquals("Rectangles intersecting region (1, 1, 3, 3):\n"
            + "(a, 1, 1, 3, 3)\n"
            + "(b, 1, 1, 2, 2)\n", a.regionSearch(1, 1, 3, 3));  
    }
    
    /**
     * This method tests intersectingRecs method
     */
    public void testIntersectingRecs()
    {
        assertEquals("Intersection pairs:\n", a.intersectingRecs());
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals("Intersection pairs:\n", a.intersectingRecs());
        assertEquals("Rectangle inserted: (b, 5, 5, 2, 2)\n",
            a.insertingRec("b", 5, 5, 2, 2));
        assertEquals("Rectangle inserted: (c, 1, 1, 3, 3)\n",
            a.insertingRec("c", 1, 1, 3, 3));
        assertEquals("Intersection pairs:\n"
            + "(a, 1, 1, 3, 3 | c, 1, 1, 3, 3)\n"
            + "(c, 1, 1, 3, 3 | a, 1, 1, 3, 3)\n", a.intersectingRecs());
    
   
    }
    
    /**
     * This method test searchRec() method
     */
    public void testSearchRecc()
    {
        assertEquals("Rectangle not found: (c)\n", a.searchRec("c"));
        assertEquals("Rectangle inserted: (a, 1, 1, 3, 3)\n",
            a.insertingRec("a", 1, 1, 3, 3));
        assertEquals("Rectangles found:\n"
            + "(a, 1, 1, 3, 3)\n", a.searchRec("a"));
        assertEquals("Rectangle inserted: (a, 2, 2, 3, 3)\n",
            a.insertingRec("a", 2, 2, 3, 3));
        assertEquals("Rectangles found:\n"
            + "(a, 2, 2, 3, 3)\n"
            + "(a, 1, 1, 3, 3)\n", a.searchRec("a"));
        assertEquals("Rectangle inserted: (b, 1, 1, 3, 3)\n",
            a.insertingRec("b", 1, 1, 3, 3));
        assertEquals("Rectangles found:\n"
            + "(a, 2, 2, 3, 3)\n"
            + "(a, 1, 1, 3, 3)\n", a.searchRec("a"));
        
    }
    
    /**
     * This method test readCommandFile() method
     * @throws FileNotFoundException 
     */
    public void testReadCoommandFile() throws FileNotFoundException
    {
        //assertEquals(b.readCommandFile(), "");
        //CommandReader c = new CommandReader("ADC");
        
        
    }

}
