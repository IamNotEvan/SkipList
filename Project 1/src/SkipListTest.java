
/**
 * 
 * Test class for SkipList class
 * 
 * @author Evan Lee (evan0110)
 * @version 09.04.2022
 * 
 */
public class SkipListTest extends student.TestCase {
    
    @SuppressWarnings("rawtypes")
    private SkipList recList;
    @SuppressWarnings("rawtypes")
    private KVPair recPair;
    private Rectangle rec1;
    @SuppressWarnings("rawtypes")
    private Comparable key;
    
    /**
     * 
     * Set up for this test class
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setUp()
    {
        rec1 = new Rectangle("a", 1, 1, 3, 3);
        recList = new SkipList();
        recPair = new KVPair(rec1.getName(), rec1);
        key = recPair.key();
    }
    
    /**
     *  This test method tests all getter methods
     */
    public void testGetMethods()
    {
        //assertNull(recList.getHead());
        assertEquals(recList.getSize(), 0);
    }
    
    /**
     * This test method tests insert() method
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testInsert()
    {
        assertTrue(recList.insert(recPair));
        assertEquals(recList.getSize(), 1);
        Rectangle newRec = new Rectangle("b", 1, 1, 3, 3);
        KVPair newPair = new KVPair(newRec.getName(), newRec);
        assertTrue(recList.insert(newPair));
        assertEquals(recList.getSize(), 2);
        KVPair intPair = new KVPair("a", 3);
        assertTrue(recList.insert(intPair));
        assertEquals(recList.getSize(), 3); 
        
    }
   
    /**
     * This test method tests search() method
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testSearch()
    {
        assertNull(recList.search(recPair));
        assertTrue(recList.insert(recPair));
        assertEquals(recList.search(key), recPair);
        Rectangle newRec = new Rectangle("b", 1, 1, 3, 3);
        KVPair newPair = new KVPair(newRec.getName(), newRec);
        assertTrue(recList.insert(newPair));
        assertEquals(recList.search(newPair.key()), newPair);
        KVPair intPair = new KVPair("c", 3);
        assertNull(recList.search(intPair.key()));
        assertTrue(recList.insert(intPair));
        assertEquals(recList.search(intPair.key()), intPair);
    }

    /**
     * This test method test remove() by key method
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testRemoveByKey()
    {
        assertNull(recList.remove(key));
        assertTrue(recList.insert(recPair));
        assertTrue(recList.insert(recPair));
        assertTrue(recList.insert(recPair));
        assertEquals(recList.remove(key), recPair);
        assertEquals(recList.getSize(), 2);
        Rectangle newRec = new Rectangle("b", 1, 1, 3, 3);
        KVPair newPair = new KVPair(newRec.getName(), newRec);
        assertNull(recList.remove(newPair.key()));
        assertTrue(recList.insert(newPair));
        assertEquals(recList.remove(newPair.key()), newPair);
        assertEquals(recList.getSize(), 2);
          
    }
    
 
    /**
     * This test method tests iterator1() method
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testIterator1()
    {
        
//        SkipList.SkipIterator newIter = recList.iterator1();
//        assertFalse(newIter.hasNext());
//        assertTrue(recList.insert(recPair));
//        SkipList.SkipIterator newIter1 = recList.iterator1();
//        assertTrue(newIter1.hasNext());
//        assertEquals(newIter1.next().element(), recPair);
//        Rectangle newRec = new Rectangle("b", 1, 1, 3, 3);
//        KVPair newPair = new KVPair(newRec.getName(), newRec);
//        assertTrue(recList.insert(newPair));
//        SkipList.SkipIterator newIter2 = recList.iterator1();
//        assertEquals(newIter2.next().element(), recPair);
//        assertEquals(newIter2.next().element(), newPair);
    }
}
