import java.io.FileNotFoundException;

/**
 * This class runs the program
 * 
 * @author Evan Lee (evan0110)
 * @version 04.09.2022
 *
 */
public class Rectangle1 {
    
    /**
     * This is a constructor
     */
    public Rectangle1()
    {
        
    }
    
    /**
     * Main method for this project
     * 
     * @param args
     *      the name of file
     * @throws FileNotFoundException
     *      if the name of file is invalid
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        String fileName;
        
        if (args.length == 1)
        {
            fileName = args[0];
        }
        else
        {
            fileName = "SkipListSampleInPut.txt";
        }
        
        try
        {
            CommandReader reader = new CommandReader(fileName);
            String output = reader.readCommandFile();
            System.out.println(output);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Invalid Input");
            throw new FileNotFoundException();
            
        }
        
    }
    
    

}
