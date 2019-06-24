package Controller;

import Contract.IController;
import Contract.IModel;
import Contract.IView;

public class Controller implements IController,Runnable
{
    private IModel model;
    private IView view;
    public Controller(final IModel newModel, final IView newView){
        this.model = newModel;
        this.view = newView;
    }
    public void run(){
    
    }
}
