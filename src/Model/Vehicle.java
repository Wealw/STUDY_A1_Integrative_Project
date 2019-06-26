package Model;

import java.awt.*;
import java.util.Random;

public class Vehicle
{
    private String name;
    private String mac;
    private Vector section;
    private Point  facing;
    private float  speed;
    private int    distanceOnSection;
    private int    totalDistance;
    private int    timeOnSection;
    private int    lastDirection;
    private Color  color;
    

    public Vehicle(final String newName, final String newMac , final Vector newActualSection, final Point newFacing){
        this.name = newName;
        this.mac = newMac;
        this.section = newActualSection;
        this.facing = newFacing;
        this.distanceOnSection = 0;
        this.totalDistance = 0;
        float r = new Random().nextFloat();
        float g = new Random().nextFloat();
        float b = new Random().nextFloat();
        this.color = new Color(r , g ,b);
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getMac()
    {
        return mac;
    }
    public void setMac(String mac)
    {
        this.mac = mac;
    }
    public Vector getSection()
    {
        return section;
    }
    public void setSection(Vector section)
    {
        this.section = section;
    }
    public Point getFacing()
    {
        return facing;
    }
    public void setFacing(Point facing)
    {
        this.facing = facing;
    }
    public float getSpeed()
    {
        return speed;
    }
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    public int getDistanceOnSection()
    {
        return distanceOnSection;
    }
    public void setDistanceOnSection(int distanceOnSection)
    {
        this.distanceOnSection = distanceOnSection;
    }
    public int getTotalDistance()
    {
        return totalDistance;
    }
    public void setTotalDistance(int totalDistance)
    {
        this.totalDistance = totalDistance;
    }
    public Color getColor()
    {
        return color;
    }
    
    public int getTimeOnSection()
    {
        return timeOnSection;
    }
    public void setTimeOnSection(int timeOnsection)
    {
        this.timeOnSection = timeOnsection;
    }
    
    public int getLastDirection()
    {
        return lastDirection;
    }
    public void setLastDirection(int lastDirection)
    {
        this.lastDirection = lastDirection;
    }
    
}