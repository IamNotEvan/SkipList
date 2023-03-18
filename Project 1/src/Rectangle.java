/**
 * This class represent the Renctangle class
 * 
 * @author Evan Lee (evan0110)
 * @version 2022.09.01
 *
 */
public class Rectangle {
    
    private String name;
    private int leftTopX;
    private int leftTopY;
    private int width;
    private int height;
    private static final int BOXLENG = 1024;
    
    /**
     * New rectangle object
     * 
     * @param recName
     *      the name of the rectangle
     * @param x
     *      upper left x value of rectangle
     * @param y
     *      upper left y value of rectangle
     * @param w
     *      width of the rectangle
     * @param h
     *      height of the rectangle
     */
    public Rectangle(String recName, int x, int y, int w, int h)
    {
        this.name = recName;
        this.leftTopX = x;
        this.leftTopY = y;
        this.width = w;
        this.height = h;
    }
    /**
     * This method returns the name of the rectangle
     * 
     * @return
     *      the name of the rectangle
     *      
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * This method sets new Name for a Rec
     * 
     * @param newName
     *      new name
     */
    public void setName(String newName)
    {
        this.name = newName;
    }
    /**
     * Returns the left top X coord
     * 
     * @return
     *      x coord
     */
    public int getTopX()
    {
        return this.leftTopX;
    }
    /**
     * Sets the top X coord
     * 
     * @param newX
     *      new x coord
     */
    public void setTopX(int newX)
    {
        this.leftTopX = newX;
    }
    /**
     * Returns the top y coord
     * 
     * @return
     *      top y coord
     */
    public int getTopY()
    {
        return this.leftTopY;
    }
    /**
     * Sets the top y coord
     * 
     * @param newY
     *      top y coord
     */
    public void setTopY(int newY)
    {
        this.leftTopY = newY;
    }
    /**
     * Returns the width of rectangle
     * 
     * @return
     *      width
     */
    public int getWidth()
    {
        return this.width;
    }
    /**
     * Sets the new width of a rectangle
     * 
     * @param newW
     *      new width
     */
    public void setWidth(int newW)
    {
        this.width = newW;
    }
    /**
     * Returns the height of the rectangle
     * 
     * @return
     *      height
     */
    public int getHeight()
    {
        return this.height;
    }
    /**
     * Sets the new height of the rectangle
     * 
     * @param newH
     *      new height
     */
    public void setHeight(int newH)
    {
        this.height = newH;
    }
    /**
     * Compares the tow rec by name
     * 
     * @param otherRec
     *      the other rec
     * @return
     *      the int value of compare to method
     */
    public int compareTo(Rectangle otherRec)
    {
        return this.getName().compareTo(otherRec.getName());
    }
    /**
     * If two rectangles are intersecting or not
     * 
     * @param otherRec
     *      other rec
     * @return
     *      true if intersect or false if they don't
     */
    public boolean intersect(Rectangle otherRec)
    {
        if ((this.getTopX() >= (otherRec.getTopX() + otherRec.getWidth())) 
            || (otherRec.getTopX() >= (this.getTopX() + this.getWidth())))
        {
            return false;
        }
        return !((this.getTopY() >=
            (otherRec.getTopY() + otherRec.getHeight())) 
            || (otherRec.getTopY() >= (this.getTopY() + this.getHeight())));
    }
    /**
     * Test if two recs have same size and coord
     * 
     * @param otherRec
     *      other rec
     * @return
     *      true if they do/false if they don't
     */
    public boolean sameSizeRec(Rectangle otherRec)
    {
        if (this.equals(otherRec))
        {
            return true;
        }
        if ((this.getTopX() == otherRec.getTopX())
            && (this.getWidth() == otherRec.getWidth())
            && (this.getTopY() == otherRec.getTopY())
            && (this.getHeight() == otherRec.getHeight()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Checks if this can be inside a box
     * 
     * @return
     *      can it be or not
     */
    public boolean canBeARec()
    {
        if (this.getTopX() < 0 || this.getTopY() < 0 
            || this.getWidth() <= 0 || this.getHeight() <= 0)
        {
            return false;
        }
        
        return !(this.getTopX() + this.getWidth() > Rectangle.BOXLENG 
            || this.getTopY() + this.getHeight() > Rectangle.BOXLENG);
    }
    /**
     * This method creates sting with () at the end
     * 
     * @return
     *      string value
     */
    public String toStringInFormatO()
    {
        if (this.getName() == null)
        {
            String noRec = ("(null)");
            return noRec;
        }
        StringBuilder format = new StringBuilder();
        format.append("(");
        format.append(this.getName() + ", ");
        format.append(this.getTopX() + ", ");
        format.append(this.getTopY() + ", ");
        format.append(this.getWidth() + ", ");
        format.append(this.getHeight() + ")");
        String singleStr = format.toString();
        return singleStr;
    }
    /**
     * This method creates string with no () at the end
     * @return
     *      string
     */
    public String toStringInFormatX()
    {
        StringBuilder format = new StringBuilder();
        format.append(this.getName() + ", ");
        format.append(this.getTopX() + ", ");
        format.append(this.getTopY() + ", ");
        format.append(this.getWidth() + ", ");
        format.append(this.getHeight());
        String singleStr = format.toString();
        return singleStr;
    }
    /**
     * This method returns the toString() method value
     * 
     * @return
     *      string
     */
    public String toString()
    {
        if (this.getName() == null)
        {
            String noRec = ("(null)");
            return noRec;
        }
        StringBuilder format2 = new StringBuilder();
        format2.append(this.getName() + ", ");
        format2.append(this.getTopX() + ", ");
        format2.append(this.getTopY() + ", ");
        format2.append(this.getWidth() + ", ");
        format2.append(this.getHeight());
        String singleStr = format2.toString();
        return singleStr;
    }
    
}