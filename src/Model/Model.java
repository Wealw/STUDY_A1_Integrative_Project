package Model;

import Contract.IModel;
import Model.DAO.DAO;

import java.util.ArrayList;

public class Model implements IModel
{
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private ArrayList<Vector> parkings    = new ArrayList<Vector>();
    

    public Model(){
        DAO.getInstance().acquireFromDB(1);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addNewVehicles(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }
    public void addNewParking(Vector parking) {
        this.parkings.add(parking);
    }
    public ArrayList<Vector> getParkings()
    {
        return parkings;
    }
}