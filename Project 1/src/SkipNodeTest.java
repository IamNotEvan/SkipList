import student.TestCase;

/**
 * This class is a test class for SkipNode class
 * 
 * @author Evan Lee (evan0110)
 * @version 09.04.2022
 * 
 */
public class SkipNodeTest extends TestCase {
    
    @SuppressWarnings("rawtypes")
    private SkipNode strInt;
    @SuppressWarnings("rawtypes")
    private SkipNode strRec;
    private Rectangle rec;
    @SuppressWarnings("rawtypes")
    private KVPair recPair;
    
    /**
     * This is set up for this test class
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setUp()
    {
        rec = new Rectangle("a", 1, 1, 3, 3);
        recPair = new KVPair(rec.getName(), rec);
        strInt = new SkipNode(1, 1, 1);
        strRec = new SkipNode(recPair, 3);
    }
    
    /**
     * This class tests all the element() and key() methods
     */
    public void testElementKey()
    {
        assertEquals(strInt.key(), 1);
        assertEquals(strRec.key(), "a");
        assertEquals(strRec.element(), recPair);
    }
    
    /**
     * This class tests the forwardLength() method
     */
    public void testForwardLength()
    {
        assertEquals(strInt.forwardLength(), 2);
        assertEquals(strRec.forwardLength(), 4);
    }
    
    /**
     * This class test the getForward method
     */
    public void testGetForward()
    {
        assertEquals(strInt.getForward().length, 2);
        assertEquals(strRec.getForward().length, 4);
    }
    
    
    /**
     * This class test to string method
     */
    public void testToString()
    {
        assertEquals(strInt.toString(), "1, 1");
        assertEquals(strRec.toString(), "a, a, 1, 1, 3, 3");
    }

}
