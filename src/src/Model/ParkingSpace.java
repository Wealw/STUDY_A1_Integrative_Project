package Model;

public class ParkingSpace
{

    private boolean avaliable;
    private int     length;

    public ParkingSpace(final int newLength)
    {
        avaliable = true;
        this.length = newLength;
    }

    private boolean isAvaliable()
    {
        return avaliable;
    }
    private void setAvaliable(boolean avaliable)
    {
        this.avaliable = avaliable;
    }
    private int getLength()
    {
        return length;
    }

}