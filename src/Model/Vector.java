package Model;

public class Vector
{

    private Point origin;
    private Point destination;
    private int tag;

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
    public int getTag(){
        return this.tag;
    }
    public void setTag(int newTag){
        this.tag = newTag;
    }

}