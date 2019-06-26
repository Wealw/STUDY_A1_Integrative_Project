package Contract;

import Model.Vector;
import Model.Vehicle;

import java.util.ArrayList;

public interface IModel
{
    public ArrayList<Vehicle> getVehicles();
    public void addNewVehicles(Vehicle vehicle);
    public void addNewParking(Vector parkingPlace);
    public ArrayList<Vector> getParkings();
}
