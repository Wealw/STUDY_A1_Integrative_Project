package Model;

import Contract.IModel;
import Model.DAO.DAO;

import java.util.ArrayList;

public class Model implements IModel
{
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public Model(){
        DAO.getInstance().acquireFromDB(1);
        vehicles.add(new Vehicle("grp6_K2000", "grp6_K2000", Map.getInstance().getConnections().get(40), Map.getInstance().getIntersections().get(10)));
        vehicles.add(new Vehicle("grp6_K2001", "grp6_K2001", Map.getInstance().getConnections().get(16), Map.getInstance().getIntersections().get(6)));
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}