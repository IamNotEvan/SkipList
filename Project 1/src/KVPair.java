/* *** ODSATag: KVPair *** */
//KVPair class definition
/**
 * This class is for KV Pair
 * 
 * @author Evan Lee (evan0110)
 * @version 04.09.2022
 *
 * @param <K>
 *  the K value
 * @param <E>
 *  the E value
 */
public class KVPair<K extends Comparable<K>, E>
            implements Comparable<KVPair<K, E>>
{
  
    private K theKey;
    private E theVal;

    /**
     * Creates new KVPair object
     * 
     * @param k
     *  theKey value
     * @param v
     *  theVal Value
     */
    KVPair(K k, E v)
    {
        theKey = k;
        theVal = v;
    }

    // Compare KVPairs
    /**
     * Compare KVPairs
     * 
     * @param it
     *  comparing KVPair
     * @return
     *  compareTo int value
     */
    public int compareTo(KVPair<K, E> it) {
        return theKey.compareTo(it.key());
    }

    /**
     * Return theKey value
     * 
     * @return
     *  theKey value
     */
    public K key() {
        return theKey;
    }

    /**
     * Return theVal value
     * 
     * @return
     *  theVal value
     *  
     */
    public E value() {
        return theVal;
    }


    /**
     * Creates String
     * 
     * @return
     *  the string 
     * 
     */
    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }
}
/* *** ODSAendTag: KVPair *** */
