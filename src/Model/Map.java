package Model;

import java.util.ArrayList;

public class Map
{
    private String name;
    final static private Map instance = new Map();
    private ArrayList<Point> intersections = new ArrayList<Point>();
    private ArrayList<Vector> connections = new ArrayList<Vector>();

    public Map(String name)
    {
    this.name = name;
    }

    public Map() {

    }

    public static Map getInstance(){
        return instance;
    }


    public ArrayList<Point> getIntersections()
    {
        return intersections;
    }
    public void setIntersections(ArrayList<Point> intersections)
    {
        this.intersections = intersections;
    }
    public ArrayList<Vector> getConnections()
    {
        return connections;
    }
    public void setConnections(ArrayList<Vector> connections)
    {
        this.connections = connections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}