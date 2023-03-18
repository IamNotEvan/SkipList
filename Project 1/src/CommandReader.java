import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is Command Reader class where it reads input and operate commands
 * 
 * @author Evan Lee (evan0110)
 * @version 09.07.2022
 *
 */
public class CommandReader {
    
    @SuppressWarnings("rawtypes")
    private SkipList recList;
    private String fileName;
    private static final int SAMPLELENG = 1;
    
    /**
     * This is a constructor
     * 
     * @param commandFileName
     *      the name of input file
     * @throws FileNotFoundException
     *      when file name is incorrect
     */
    @SuppressWarnings("rawtypes")
    public CommandReader(String commandFileName)
    {
        recList = new SkipList();    
        fileName = commandFileName;
        //recList = new SkipList();
       
    }
    
    /**
     * This is a constructor for testing purpose
     */
    @SuppressWarnings("rawtypes")
    public CommandReader()
    {
        recList = new SkipList();
        
    }
    
    /**
     * This method returns the recList;
     * 
     * @return
     *      recList
     */
    @SuppressWarnings("rawtypes")
    public SkipList getSkipList()
    {
        return this.recList;
    }
    
    /**
     * This method read command file operate the program
     * 
     * @throws FileNotFoundException
     *      when the file is not found
     * @return
     *      the string value of output
     */
    public String readCommandFile() throws FileNotFoundException
    {
        Scanner file =  new Scanner(new File(fileName));
        String command = "";
        String newName = "";
        int newX = 0;
        int newY = 0;
        int newW = 0;
        int newH = 0;
        StringBuilder outPut = new StringBuilder();
        while (file.hasNextLine())
        {
            //file.nextLine();
            command = file.next();
            switch(command) {
                case "insert":
                    newName = file.next();
                    newX = Integer.parseInt(file.next());
                    newY = Integer.parseInt(file.next());
                    newW = Integer.parseInt(file.next());
                    newH = Integer.parseInt(file.next());
                    //insertingRec(newName, newX, newY, newTopX, newTopY);
                    String outPutInsert = insertingRec(newName,
                        newX, newY, newW, newH);
                    outPut.append(outPutInsert);
                    break;
                    
                case "dump":
                    String outPutDump = dumpRecs();
                    outPut.append(outPutDump);
                    break;
                    
                case "remove":
                    if (file.hasNextInt())
                    {
                        newX = Integer.parseInt(file.next());
                        newY = Integer.parseInt(file.next());
                        newW = Integer.parseInt(file.next());
                        newH = Integer.parseInt(file.next());
                        String removeCoordStr =
                            removeByCoords(newX, newY, newW, newH);
                        outPut.append(removeCoordStr);
                        break;
                        
                    }
                    else
                    {
                        newName = file.next();
                        String removeNameStr = removeByName(newName);
                        outPut.append(removeNameStr);
                        break;
                        
                    }
                    
                    
                case "regionsearch":
                    newX = Integer.parseInt(file.next());
                    newY = Integer.parseInt(file.next());
                    newW = Integer.parseInt(file.next());
                    newH = Integer.parseInt(file.next());
                    String regionSearchStr =
                        regionSearch(newX, newY, newW, newH);
                    outPut.append(regionSearchStr);
                    
                    break;
                    
                case "intersections":
                    String intersectionStr = intersectingRecs();
                    outPut.append(intersectionStr);
                    break;
                    
                case "search":
                    newName = file.next();
                    String searchStr = searchRec(newName);
                    outPut.append(searchStr);
                    break;
            }
            if (file.hasNextLine())
            {
                file.nextLine();
            }
        }
        String outStr = outPut.toString();
        return outStr;
    } 
    /**
     * This method read the command and insert the matching rectangle
     * to the skipList
     * 
     * @param newName
     *      name of the rectangle
     * @param newX
     *      X coord of the rectangle
     * @param newY
     *      Y coord of the rectangle
     * @param newW
     *      width of the rectangle
     * @param newH
     *      height of the rectangle
     * @return
     *      the expecting output
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String insertingRec(String newName, int newX,
        int newY, int newW, int newH)
    {
        Rectangle newRec = new Rectangle(newName, newX, newY, newW, newH);
        if (newRec.canBeARec())
        {
            StringBuilder insertStr = new StringBuilder("Rectangle inserted: ");
            insertStr.append(newRec.toStringInFormatO());
            insertStr.append("\n");
            String format = insertStr.toString();
            KVPair rec1 = new KVPair(newName, newRec);
            this.recList.insert(rec1);
            return format;
        }
        else
        {
            StringBuilder rejectStr = new StringBuilder("Rectangle rejected: ");
            rejectStr.append(newRec.toStringInFormatO());
            rejectStr.append("\n");
            String format = rejectStr.toString();
            //System.out.println(format);
            return format;
        }
    }
    
    /**
     * This method operate the dump command
     * 
     * @return
     *      the dump command output string
     */
    @SuppressWarnings("rawtypes")
    public String dumpRecs()
    {
        StringBuilder dump = new StringBuilder("SkipList dump:");
        dump.append("\n");
        SkipList.SkipIterator dumpIter = recList.iterator1();
        if (!dumpIter.hasNext())
        {
            dump.append("Node has depth 1, Value (null)\n"
                + "SkipList size is: 0\n");
            String emptyDump = dump.toString();
            return emptyDump;
            
        }
        dump.append("Node has depth ");
        dump.append(recList.getHead().forwardLength());
        dump.append(", Value (null)\n");
        while (dumpIter.hasNext())
        {
            dump.append("Node has depth ");
            
            SkipNode currentNode = dumpIter.next();
            int numOfPointers = currentNode.forwardLength();
            String valueOfRec = ((Rectangle)currentNode.element().value())
                .toStringInFormatO();
            dump.append(numOfPointers + ", Value " + valueOfRec + "\n");
        }
        dump.append("SkipList size is: " + recList.getSize() + "\n");
        String dumpStr = dump.toString();
        return dumpStr;
    }
           
    /**
     * This method operates the remove by name command
     * 
     * @param name
     *      the name of rectangle
     * @return
     *      the remove command output string
     */
    @SuppressWarnings("rawtypes")
    public String removeByName(String name)
    {
        SkipList.SkipIterator removeNameIter = recList.iterator1();
        while (removeNameIter.hasNext())
        {
            SkipNode currentNode = removeNameIter.next();
            Comparable currentKey = currentNode.key();
            String currentRecName = (String)currentNode.key();
            if (currentRecName.equals(name))
            {
                StringBuilder removeName = 
                    new StringBuilder("Rectangle removed: ");
                removeName.append(((Rectangle)currentNode.element().value())
                    .toStringInFormatO());
                removeName.append("\n");
                recList.remove(currentKey);
                String removeNameStr = removeName.toString();
                return removeNameStr;
            }
        }
        StringBuilder notFound = 
            new StringBuilder("Rectangle not removed: (" + name + ")\n");
        String notFoundStr = notFound.toString();
        return notFoundStr;
        
    }
    
    /**
     * This method operates the remove command with coordinates
     * 
     * @param newX
     *      X coord of the rectangle
     * @param newY
     *      Y coord of the rectangle
     * @param newW
     *      Width of the rectangle
     * @param newH
     *      Height of the rectangle
     * @return
     *      the output for the command
     */
    @SuppressWarnings("rawtypes")
    public String removeByCoords(int newX, int newY, int newW, int newH)
    {
        
        SkipList.SkipIterator removeCoordsIter = recList.iterator1();
        Rectangle compareRec = new Rectangle("sample", newX, newY, newW, newH);
        while (removeCoordsIter.hasNext())
        {
            SkipNode currentNode = removeCoordsIter.next();
            Comparable currentKey = currentNode.key();
            Rectangle currentRec = (Rectangle)currentNode.element().value();
            if (compareRec.sameSizeRec(currentRec))
            {
                StringBuilder found = 
                    new StringBuilder("Rectangle removed: "
                        + currentRec.toStringInFormatO());
                found.append("\n");
                String foundStr = found.toString();
                recList.remove(currentKey);
                return foundStr;
            } 
        }
        StringBuilder notFound = new StringBuilder("Rectangle not removed: (");
        notFound.append(newX + ", ");
        notFound.append(newY + ", ");
        notFound.append(newW + ", ");
        notFound.append(newH + ")\n");
        String notFoundStr = notFound.toString();
        return notFoundStr;
    }
    
    /**
     * This method operates the region search command
     * 
     * @param newX
     *      X coord of the rectangle
     * @param newY
     *      Y coord of the rectangle
     * @param newW
     *      Width of the rectangle
     * @param newH
     *      Height of the rectangle
     * @return
     *      the output for the command
     *      
     */
    @SuppressWarnings("rawtypes")
    public String regionSearch(int newX, int newY, int newW, int newH)
    {
        SkipList.SkipIterator regionSearchIter = recList.iterator1();
        Rectangle compareRec = new Rectangle("sample", newX, newY, newW, newH);
        
        if (newW <= 0 || newH <= 0)
        {
            StringBuilder reject = new StringBuilder("Rectangle rejected: (");
            reject.append(newX + ", ");
            reject.append(newY + ", ");
            reject.append(newW + ", ");
            reject.append(newH + ")");
            reject.append("\n");
            String rejectStr = reject.toString();
            return rejectStr;
        }
        StringBuilder region = 
            new StringBuilder("Rectangles intersecting region (");
        region.append(newX + ", ");
        region.append(newY + ", ");
        region.append(newW + ", ");
        region.append(newH + "):");
        region.append("\n");
        while (regionSearchIter.hasNext())
        {
            SkipNode currentNode = regionSearchIter.next();
            Rectangle currentRec = (Rectangle)currentNode.element().value();
            if (currentRec.intersect(compareRec))
            {
                region.append(currentRec.toStringInFormatO());
                region.append("\n");
            }
        }
        String intersectStr = region.toString();
        return intersectStr;
    }
    
    /**
     * This method operates the intersection command
     * 
     * @return
     *      the output for the command
     *      
     */
    @SuppressWarnings("rawtypes")
    public String intersectingRecs()
    {
        
        SkipList.SkipIterator outerIter = recList.iterator1();
        StringBuilder found = new StringBuilder("Intersection pairs:");
        found.append("\n");
        int i = 0;
        
        if (recList.getSize() > 1)
        {
            while (outerIter.hasNext())
            {
                i++;
                int j = 0;
                SkipNode currentNode = outerIter.next();
                Rectangle currentRec = (Rectangle)currentNode.element().value();
                SkipList.SkipIterator inerIter = recList.iterator1();
                while (inerIter.hasNext())
                {
                    
                    SkipNode currentInerNode = inerIter.next();
                    Rectangle inerCurrentRec = 
                        (Rectangle)currentInerNode.element().value();
                    j++;
                    if (currentRec.intersect(inerCurrentRec) && (j != i))
                    {
                        found.append("(");
                        found.append(currentRec.toStringInFormatX());
                        found.append(" | ");
                        found.append(inerCurrentRec.toStringInFormatX());
                        found.append(")");
                        found.append("\n");
                    }
                    
                }
            }
        }
        String intersectStr = found.toString();
        return intersectStr;
    }
    
    /**
     * This method operates the serach command
     * 
     * @param newName
     *      the name of the rectangle
     * @return
     *      output for the command
     */
    @SuppressWarnings("rawtypes")
    public String searchRec(String newName)
    {
        Rectangle newRec = 
            new Rectangle(newName, SAMPLELENG, SAMPLELENG,
                SAMPLELENG, SAMPLELENG);
        SkipList.SkipIterator search = recList.iterator1();
        StringBuilder searchFound = new StringBuilder("Rectangles found:");
        searchFound.append("\n");
        int count = 0;
        while (search.hasNext())
        {
            SkipNode currentNode = search.next();
            Rectangle currentRec = (Rectangle)currentNode.element().value();
            if (currentRec.compareTo(newRec) == 0)
            {
                searchFound.append(currentRec.toStringInFormatO());
                searchFound.append("\n");
                count++;
            }  
        }
        if (count != 0)
        {
            String searchStr = searchFound.toString();
            return searchStr;
        }
        else
        {
            StringBuilder notFound = 
                new StringBuilder("Rectangle not found: (" + newName + ")\n");
            String notFoundStr = notFound.toString();
            return notFoundStr;
        }
    }

}
