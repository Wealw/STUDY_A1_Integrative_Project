package Model;

public class Point
{
    private char id;
    private int x;
    private int y;
    
    public Point(final char newId, final int newX, final int newY)
    {
        this.id = newId;
        this.x = newX;
        this.y = newY;
    }
    
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public char getId()
    {
        return id;
    }
    
}
