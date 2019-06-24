package Model;

public class Vector
{
    
    private Point origin;
    private Point destination;
    
    public Vector(final Point newOrigin, final Point newDestination)
    {
        this.origin = newOrigin;
        this.destination = newDestination;
    }
    
    public Point getOrigin()
    {
        return origin;
    }
    public Point getDestination()
    {
        return destination;
    }
    
}
