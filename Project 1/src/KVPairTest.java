
 
/**
 * Test class for KVPair class
 * 
 * @author Evan Lee (evan0110)
 * @version 09.04.2022
 * 
 */
public class KVPairTest extends student.TestCase {
    
    private KVPair<?, ?> intPair;
    private KVPair<?, ?> recPair;
    private Rectangle rec1;
    
    /**
     * Test setUp method
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setUp()
    {
        rec1 = new Rectangle("a", 1, 1, 3, 3);
        intPair = new KVPair(1, 3);
        recPair = new KVPair(rec1.getName(), rec1);
    }
    
    /**
     *  This class tests all the key() and value() methods
     */
    public void testKeyAndValue()
    {
        assertEquals(intPair.key(), 1);
        assertEquals(recPair.key(), "a");
        assertEquals(intPair.value(), 3);
        assertEquals(recPair.value(), rec1);
    }
    
    /**
     * This class tests toString method
     */
    public void testToString()
    {
        assertEquals(intPair.toString(), "1, 3");
        assertEquals(recPair.toString(), "a, a, 1, 1, 3, 3");
    }
    
    /**
     * This class tests compareTo method
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testCompareTo()
    {
        KVPair samplePair1 = new KVPair(1, 4);
        KVPair samplePair2 = new KVPair(0, 4);
        KVPair samplePair3 = new KVPair("a", rec1);
        KVPair samplePair4 = new KVPair("b", rec1);
        //int a = (int)samplePair1.key();
        
        //Comparable k = samplePair1.key();
        assertEquals(intPair.compareTo(samplePair1), 0);
        //assertEquals(intPair.compareTo(k), 0);
        assertEquals(intPair.compareTo(samplePair2), 1);
        assertEquals(recPair.compareTo(samplePair3), 0);
        assertEquals(recPair.compareTo(samplePair4), -1);
        //assertEquals(recPair.compareTo((KVPair<?, ?>)recPair.key()), 0);
    }
    

}
