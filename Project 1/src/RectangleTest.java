
import student.TestCase;

/**
 * Test class for Rectangle Class
 * 
 * @author Evan Lee (evan0110)
 * @version 09.04.2022
 *
 */
public class RectangleTest extends TestCase {
    
    private Rectangle rec1;
    private int a;
    /**
     * Test setUP method
     */
    public void setUp()
    {
        rec1 = new Rectangle("a", 1, 1, 3, 3);
        a = 1;
    }
    
    /**
     * This method tests all getter and setter methods
     */ 
    public void testGetSet()
    {
        rec1.setName("b");
        assertEquals(rec1.getName(), "b");
        rec1.setTopX(2);
        assertEquals(rec1.getTopX(), 2);
        rec1.setTopY(2);
        assertEquals(rec1.getTopY(), 2);
        rec1.setHeight(5);
        assertEquals(rec1.getHeight(), 5);
        rec1.setWidth(5);
        assertEquals(rec1.getWidth(), 5);
    }
    /**
     * This method tests all compareTo() method
     */
    public void testCompareTo()
    {
        Rectangle rec2 = new Rectangle("b", 1, 1, 3, 3);
        assertEquals(rec1.compareTo(rec2), -1);
        rec2.setName("a");
        assertEquals(rec1.compareTo(rec2), 0);
        rec1.setName("b");
        assertEquals(rec1.compareTo(rec2), 1);
    }
    /**
     * This method tests intersect() method
     */
    public void testIntersect()
    {
        rec1.setTopX(99);
        Rectangle rec2 = new Rectangle("b", 1, 1, 3, 3);
        assertFalse(rec1.intersect(rec2));
        rec1.setTopX(1);
        rec2.setTopX(99);
        assertFalse(rec2.intersect(rec1));
        assertFalse(rec1.intersect(rec2));
        rec1.setTopY(99);
        assertFalse(rec1.intersect(rec2));
        rec1.setTopY(1);
        rec2.setTopY(99);
        assertFalse(rec1.intersect(rec2));
        Rectangle rec3 = new Rectangle("b", 1, 1, 2, 2);
        assertTrue(rec1.intersect(rec3));
    }
    /**
     * This test method tests sameSizeRec() method
     */
    public void testSameSizeRec()
    {
        Rectangle rec2 = new Rectangle("b", 1, 1, 3, 3);
        assertTrue(rec1.sameSizeRec(rec2));
        Rectangle rec3 = new Rectangle("b", 1, 1, 3, 4);
        assertFalse(rec1.sameSizeRec(rec3));
        Rectangle rec4 = new Rectangle("b", 2, 1, 3, 4);
        assertFalse(rec1.sameSizeRec(rec4));
        Rectangle rec5 = new Rectangle("b", 1, 2, 3, 3);
        assertFalse(rec1.sameSizeRec(rec5));
        Rectangle rec6 = new Rectangle("b", 1, 1, 4, 3);
        assertFalse(rec1.sameSizeRec(rec6));
        Rectangle rec7 = new Rectangle("b", 1, 5, 5, 5);
        assertFalse(rec1.sameSizeRec(rec7));
        assertTrue(rec1.sameSizeRec(rec1));
        assertFalse(rec1.sameSizeRec(rec7));
    }
    /**
     * This test method tests testCanBeARec() method
     */
    public void testCanBeARec()
    {
        assertTrue(rec1.canBeARec());
        Rectangle rec2 = new Rectangle("b", -1, 1, 3, 3);
        assertFalse(rec2.canBeARec());
        Rectangle rec3 = new Rectangle("b", 1, -1, 3, 3);
        assertFalse(rec3.canBeARec());
        Rectangle rec4 = new Rectangle("b", 1, 1, -3, 3);
        assertFalse(rec4.canBeARec());
        Rectangle rec5 = new Rectangle("b", 1, 1, 3, -3);
        assertFalse(rec5.canBeARec());
        Rectangle rec6 = new Rectangle("b", 1, 1, 2000, 3);
        assertFalse(rec6.canBeARec());
        Rectangle rec7 = new Rectangle("b", 1, 1, 3, 2000);
        assertFalse(rec7.canBeARec());
    }
    /**
     * This test method tests all toString type method
     */
    public void testtoStringInFormat()
    {
        Rectangle emptyRec = new Rectangle(null, 1, 1, 1, 1);
        assertEquals(rec1.toStringInFormatO(), "(a, 1, 1, 3, 3)");
        assertEquals(emptyRec.toStringInFormatO(), "(null)");
        assertEquals(rec1.toStringInFormatX(), "a, 1, 1, 3, 3");
        assertEquals(rec1.toString(), "a, 1, 1, 3, 3" );
        assertEquals(emptyRec.toString(), "(null)");
    }
            
}
