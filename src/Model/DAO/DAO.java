package Model.DAO;

import Model.Map;
import Model.Point;
import Model.Vector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Use to load the map from a distant or local DB.
 * Used to load the map from a distant or local DB using the "../../resources/model.properties" files to get database log in.
 *
 * @author Théo Weimann
 * @version 1.2
 */
public class DAO
{

    /**
     * Unique instance of the DAOmap class.
     */
    final static private DAO instance = new DAO(DBConnection.getInstance()
            .getConnection());

    /**
     * The connection parameter inherited from the "../../resources/model.properties" and get by the Abstract class Connection.
     */
    private final Connection connection;

    /**
     * Constructor of the DAOmap class.
     *
     * @param connection The connection session get from the
     */
    private DAO(final Connection connection)
    {
        this.connection = connection;
    }

    /**
     * Use to get the unique instance of the class DAO.
     *
     * @return Return the unique instance of the class DAO.
     */
    public static DAO getInstance()
    {
        return instance;
    }

    /**
     * Import a map from a distant DB.
     *
     * @param id The id of the map you want to import.
     */
    public void acquireFromDB(final int mapId)
    {
        try
        {
            ArrayList<Point>  importedIntersections = new ArrayList<Point>();
            ArrayList<Vector> importedConnections   = new ArrayList<Vector>();
            ResultSet         resultSet             = callProcedure("{call getPoints(?)}", mapId);
            if (resultSet.first())
            {
                while (!resultSet.isAfterLast())
                {
                    importedIntersections.add(new Point(resultSet.getString(1)
                            .toCharArray()[0], resultSet.getInt(2), resultSet.getInt(3)));
                    resultSet.next();
                }
            }
            resultSet = callProcedure("{call getVectors(?)}", mapId);
            if (resultSet.first())
            {
                while (!resultSet.isAfterLast())
                {
                    for (Point importedInterstionOrigin : importedIntersections)
                    {

                        if (importedInterstionOrigin.getId() == resultSet.getString(1)
                                .toCharArray()[0])
                        {
                            for (Point importedInterstionDestination : importedIntersections)
                            {
                                if (importedInterstionDestination.getId() == resultSet.getString(2)
                                        .toCharArray()[0])
                                {
                                    importedConnections.add(new Vector(importedInterstionOrigin, importedInterstionDestination));
                                }
                            }
                        }

                    }
                    resultSet.next();
                }
            }
            Map.getInstance()
                    .setIntersections(importedIntersections);
            Map.getInstance()
                    .setConnections(importedConnections);
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            throw new Error("Database not found. See if it's available or credential are good in model.properties");
        }
    }
    private ResultSet callProcedure(final String procedure, final int mapId)
    {
        try
        {
            final String            sql  = procedure; //CALL OF THE PROCEDURE
            final CallableStatement call = this.connection.prepareCall(sql);
            call.setInt(1, mapId);
            call.execute();
            return call.getResultSet();
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            throw new Error("Database not found. See if it's available or credential are good in model.properties");
        }

    }

}