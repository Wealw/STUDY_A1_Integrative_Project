package Controller;

import Contract.IController;
import Contract.IModel;
import Contract.IView;
import Model.Map;
import Model.Vehicle;

public class Controller implements IController,Runnable
{
    private IModel model;
    private IView view;
    private String[] args;
    public Controller(final String[] newArgs,final IModel newModel, final IView newView){
        this.model = newModel;
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
            Thread.sleep(500);
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(34));
            Thread.sleep(250);
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(18));
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(4));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(29));
            Thread.sleep(250);
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(32));
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(19));
            Thread.sleep(500);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(1));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(26));
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(37));
            Thread.sleep(500);
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(7));
            Thread.sleep(250);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(14));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(34));
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(35));
            Thread.sleep(250);
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(4));
            Thread.sleep(500);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(18));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(32));
            Thread.sleep(250);
            this.model.getVehicles().get(1).setSection(Map.getInstance().getConnections().get(22));
            this.model.getVehicles().get(2).setSection(Map.getInstance().getConnections().get(1));
            Thread.sleep(500);
            this.model.getVehicles().get(3).setSection(Map.getInstance().getConnections().get(19));
            Thread.sleep(2000);
            this.model.getVehicles().get(0).setSection(Map.getInstance().getConnections().get(39));
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

}
}