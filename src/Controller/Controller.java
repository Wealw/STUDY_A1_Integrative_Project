package Controller;

import Contract.IController;
import Contract.IModel;
import Contract.IView;
import Model.ArduinoToJava;
import Model.Map;
import Model.Vehicle;

import static java.lang.Math.abs;

public class Controller implements IController,Runnable
{
    private IModel model;
    private IView view;
    private String[] args;
    public Controller(final String[] newArgs,final IModel newModel, final IView newView){
        this.model = newModel;
        ArduinoToJava.getInstance().setModel(this.model);
        this.view = newView;
        this.args = newArgs;
    }
    public void run(){
        switch (args[0]){
            case  "-demo":
                demoMode();
                break;
            case "-com":
            default:
                comMode();
                break;
        }
    }
    
private void demoMode(){
    this.model.addNewVehicles(new Vehicle("grp6_K2001", "grp6_K2001", Map.getInstance().getConnections().get(41), Map.getInstance().getIntersections().get(10)));
    this.model.addNewVehicles(new Vehicle("grp6_K2002", "grp6_K2002", Map.getInstance().getConnections().get(34), Map.getInstance().getIntersections().get(11)));
    this.model.addNewVehicles(new Vehicle("grp6_K2003", "grp6_K2003", Map.getInstance().getConnections().get(18), Map.getInstance().getIntersections().get(7)));
    this.model.addNewVehicles(new Vehicle("grp6_K2003", "grp6_K2003", Map.getInstance().getConnections().get(4), Map.getInstance().getIntersections().get(0)));
    while (true) {
        try
        {
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(41));
            refreshSection();
            Thread.sleep(500);
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(34));
            Thread.sleep(250);
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(18));
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(4));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(29));
            refreshSection();
            Thread.sleep(250);
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(32));
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(19));
            Thread.sleep(500);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(1));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(26));
            refreshSection();
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(37));
            Thread.sleep(500);
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(7));
            Thread.sleep(250);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(14));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(34));
            refreshSection();
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(35));
            Thread.sleep(250);
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(4));
            Thread.sleep(500);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(18));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(32));
            refreshSection();
            Thread.sleep(250);
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(22));
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(1));
            Thread.sleep(500);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(19));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(39));
            refreshSection();
            Thread.sleep(500);
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(26));
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(14));
            Thread.sleep(250);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(7));
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

private void comMode(){
    this.model.addNewVehicles(new Vehicle("grp6_K2000", "grp6_K2000", Map.getInstance().getConnections().get(40), Map.getInstance().getIntersections().get(10)));
    ArduinoToJava.getInstance().initialize();
}
    private void refreshSection(){
        this.model.getVehicles().get(0).setDistanceOnSection((abs(this.model.getVehicles().get(0).getSection().getOrigin().getX()-this.model.getVehicles().get(0).getSection().getDestination().getX())+abs(this.model.getVehicles().get(0).getSection().getOrigin().getY()-this.model.getVehicles().get(0).getSection().getDestination().getY()))/3);
        this.model.getVehicles().get(0).setTotalDistance(this.model.getVehicles().get(0).getTotalDistance()+this.model.getVehicles().get(0).getDistanceOnSection());
        this.model.getVehicles().get(0).setSpeed((int) (this.model.getVehicles().get(0).getDistanceOnSection() / 2.750));
    }
}

