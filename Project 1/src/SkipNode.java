    /**
     * Skip Node class
     * 
     * @author Evan Lee (evan0110)
     * @version 04.09.2022
     *
     * @param <K>
     *  K value
     * @param <E>
     *  E value
     */
public class SkipNode<K extends Comparable<K>, E> {
    
    private KVPair<K, E> rec;
    private SkipNode<K, E>[] forward;

    /**
     * This method returns the KVPair in the skipnode
     * 
     * @return
     *      the KVPair
     */
    public KVPair<K, E> element() {
        return rec;
    }

    /**
     * This method returns the key of the KVPair inside SkipNode
     * 
     * @return
     *      the key
     */
    public K key() {
        return rec.key();
    }
    
    /**
     * This method returns the forward array
     * 
     * @return
     *      forward array
     */
    public SkipNode<K, E>[] getForward()
    {
        return this.forward;
    }
        
    /**
     * Creates new SkipNode object
     * 
     * @param key
     *      KVPair key
     * @param elem
     *      KVPair value
     * @param level
     *      Number of pointers
     */
    @SuppressWarnings("unchecked")
    public SkipNode(K key, E elem, int level) {
        rec = new KVPair<K, E>(key, elem);
        forward = new SkipNode[level + 1];
        for (int i = 0; i < level; i++)
            forward[i] = null;
    }
        
    /**
     * Creates new SkipNode object
     * 
     * @param it
     *      KVPair
     * @param level
     *      number of pointers
     */
    @SuppressWarnings("unchecked")
    public SkipNode(KVPair<K, E> it, int level)
    {
        rec = it;
        forward = new SkipNode[level + 1];
        for (int i = 0; i < level; i++)
            forward[i] = null;
        
    }
        
    /**
     * This method returns the number of pointes for this SkipNode
     * 
     * @return
     *      number of pointers
     */
    public int forwardLength()
    {
        return this.forward.length;
    }

    /**
     * Return the string valeu
     * 
     * @return
     *      the string value of KVPair
     */
    public String toString() {
        return rec.toString();
    }
}
