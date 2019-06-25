package View;

import Contract.IController;
import Contract.IModel;
import Contract.IView;

import java.io.IOException;
import java.util.Observer;

public class  View implements IView
{
    private ViewFrame frame;
    private IController controller;

    public View(IModel model) throws IOException {
        if(model == null)System.err.println("model null in view");
        this.frame = new ViewFrame(model);
    }

    public void close() {
        this.frame.dispose();
    }

    public Observer getObserver() {
        return this.frame.getViewPanel();
    }

    public void printMessage(final String message) {
        this.frame.printMessage(message);
    }

    public void setController(IController controller) {
        this.controller = controller;
    }
}
