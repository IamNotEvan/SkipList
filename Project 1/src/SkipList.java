import java.lang.reflect.Array;
import java.util.Random;
import student.TestableRandom;

/**
 * This is a SkipList class
 * 
 * @author Evan Lee (evan0110)
 * @version 09.04.2022
 *
 * @param <K>
 *      Comparable key
 * @param <E>
 *      Value of KVPair
 */
public class SkipList<K extends Comparable<K>, E> {
    private SkipNode<K, E> head;
    private int level;
    private int size;
    private Random rnd;

    /**
     * This creates new SkipList class
     */
    public SkipList() {
        head = new SkipNode<K, E>(null, null, 0);
        level = -1;
        size = 0;
        rnd = new TestableRandom();
    }
    
    /**
     * This method returns the head
     * 
     * @return
     *      the head
     */
    public SkipNode<?, ?> getHead()
    {
        return this.head;
    }
    
    /**
     * This method returns the size
     * 
     * @return
     *      the size
     */
    public int getSize()
    {
        return this.size;
    }
    
    /**
     * This method picks a level using a geometric distribution
     * 
     * @return
     *      the new random level
     */
    private int randomLevel() {
        
        int lev;
        for (lev = 0; rnd.nextBoolean(); lev++)
        {
            
        }
        return lev;
    }
    
    /**
     * This method inserts a KVPair into the skiplist
     * 
     * @param it
     *      inserting KVPair
     * @return
     *      if it can be inserted or not
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean insert(KVPair<K, E> it) {
        int newLevel = randomLevel();
        Comparable<K> k = it.key();
        if (level < newLevel)
        {
            adjustHead(newLevel);
        }
        SkipNode[] update = 
            (SkipNode[])Array.newInstance(SkipNode.class, level + 1);
        SkipNode<K, E> x = head;        // Start at header node
        for (int i = level; i >= 0; i--) 
        { // Find insert position
            while ((x.getForward()[i] != null) &&
                (k.compareTo(x.getForward()[i].element().key()) > 0))
                x = x.getForward()[i];
            update[i] = x;               // Track end at level i
        }
        x = new SkipNode<K, E>(it, newLevel);
        for (int i = 0; i <= newLevel; i++) {      // Splice into list
            x.getForward()[i] = update[i].getForward()[i]; 
            update[i].getForward()[i] = x;            // Who y points to
        }
        size++;                       // Increment dictionary size
        return true;
    }
    
    /**
     * This method adjust head
     * 
     * @param newLevel
     *      new level
     */
    private void adjustHead(int newLevel) {
        SkipNode<K, E> temp =  new SkipNode<K, E>(null, null, newLevel);
        for (int i = 0; i <= level; i++)
        {
            temp.getForward()[i] = head.getForward()[i];
        }
        head = temp;
        level = newLevel;
    }
    
    
    /**
     * This method returns the (first) matching matching element
     *  if one exists, null otherwise
     * 
     * @param key
     *      the search key
     * @return
     *      the matching pair
     */
    public KVPair<K, E> search(Comparable<K> key) {
        if (level < 0)
        {
            return null;
        }
        SkipNode<K, E> x = head;                     // Dummy header node
        for (int i = level; i >= 0; i--)
        {
            // For each level...
            while ((x.getForward()[i] != null) &&
                (key.compareTo(x
                    .getForward()[i].element().key()) > 0)) // go forward
            {
                x = x.getForward()[i];              // Go one last step
            }
        }
        x = x.getForward()[0];  // Move to actual record, if it exists
        if ((x != null) && (key.compareTo(x.key()) == 0))
            return (KVPair<K, E>)x.element();
        else
            return null;
    }
    
    /**
     * This method removes the KVPair with key if existed
     * 
     * @param key
     *       key
     * @return
     *      removed or not
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public KVPair<?, ?> remove(K key)
    {
        SkipNode[] update =
            (SkipNode[])Array.newInstance(SkipNode.class, level + 1);
        SkipNode<K, E> currentNode = head;
        
        for (int i = level; i >= 0; i--)
        {
            while ((currentNode.getForward()[i] != null) 
                && (key.compareTo(currentNode.getForward()[i]
                    .element().key()) > 0))
            {
                currentNode = currentNode.getForward()[i];
            }
            update[i] = currentNode;  
        }
        
        KVPair<?, ?> removedPair;
        if (this.search(key) == null)
        {
            return null;
        }
        else
        {
            removedPair = update[0].getForward()[0].element();
        }
        
        int removedPairForwardLeng = update[0].getForward()[0].forwardLength();
        for (int i = 0; i < removedPairForwardLeng; i++)
        {
            if (update[i].getForward()[i].getForward()[i] != null)
            {
                update[i].getForward()[i] =
                    update[i].getForward()[i].getForward()[i];
            }
            else
            {
                update[i].getForward()[i] = null;
            }
        }
        
        size--;
        return removedPair;
        
        
    }
    

    /**
     * This is a inner SkipIterator class
     * 
     * @author Evan Lee (evan0110)
     *
     * @param <K>
     *      the key
     * @param <E>
     *      the value
     */
    @SuppressWarnings("hiding")
    class SkipIterator<K, E> {
        
        private SkipNode<?, ?> currentNode;
        
        /**
         * creates new SKipIterator object
         */
        public SkipIterator()
        {
            //this.recList = newList;
            this.currentNode = head;
        }
        
        /**
         * This method shows it has next
         * 
         * @return
         *      has is or not
         */
        public boolean hasNext() {
            
            return currentNode != null && currentNode.getForward()[0] != null;
        }
        
        /**
         * This method returns the next SkipNode
         * 
         * @return
         *      the next skipNode
         */
        public SkipNode<?, ?> next() {
            currentNode = currentNode.getForward()[0];
            return currentNode;
        }

    }
        
    
    /**
     * This method creates SkipIterator for this SkipList
     * 
     * @return
     *      the SkipIterator object
     */
    public SkipIterator<?, ?> iterator1()
    {
        return new SkipIterator<Object, Object>();
    }

    
    
    
    
    
    

}
