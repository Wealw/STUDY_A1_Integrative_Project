package Contract;

import Model.Vehicle;

import java.util.ArrayList;

public interface IModel
{
    public ArrayList<Vehicle> getVehicles();
    
    public void addNewVehicles(Vehicle vehicle);
}
