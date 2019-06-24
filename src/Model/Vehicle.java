package Model;

public class Vehicle
{
    private String[] name;
    private String[] mac;
    private Vector actualPosition;
    private Point facing;
    
    public Vehicle(final String[] newName, final String[] newMac , final Vector newActualPosition, final Point newFacing){
        this.name = newName;
        this.mac = newMac;
        this. actualPosition = newActualPosition;
        this. facing = newFacing;
    }
    
    private String[] getName()
    {
        return name;
    }
    private void setName(String[] name)
    {
        this.name = name;
    }
    private String[] getMac()
    {
        return mac;
    }
    private void setMac(String[] mac)
    {
        this.mac = mac;
    }
    private Vector getActualPosition()
    {
        return actualPosition;
    }
    private void setActualPosition(Vector actualPosition)
    {
        this.actualPosition = actualPosition;
    }
    private Point getFacing()
    {
        return facing;
    }
    private void setFacing(Point facing)
    {
        this.facing = facing;
    }
    
}
