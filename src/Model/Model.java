package Model;

import Contract.IModel;
import Model.DAO.DAO;

import java.util.ArrayList;

public class Model implements IModel
{
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public Model(){
        DAO.getInstance().acquireFromDB(1);
    }
    
    

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addNewVehicles(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}