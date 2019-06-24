package Model;

import Contract.IModel;
import Model.DAO.DAO;

public class Model implements IModel
{
    public Model(){
        DAO.getInstance().acquireFromDB(1);
        for (Point intersection : Map.getInstance().getIntersections()){
            System.out.println(intersection.getId() +":"+intersection.getX()+ ":" + intersection.getY());
        }
        for (Vector connection : Map.getInstance().getConnections()){
            System.out.println(connection.getOrigin().getId() +":"+connection.getDestination().getId());
        }
    }
    
}
