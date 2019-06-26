import Controller.Controller;
import Model.Model;
import View.View;

import java.io.IOException;

/**
 * The Class Main.
 *
 * @author Théo Weimann
 * @version 1.0
 */
public abstract class Main
{

    /**
     * The main method.
     *
     * @param args Main's argument.
     */
    public static void main(final String[] args) throws InterruptedException, IOException {
        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(args, model, view);
        view.setController(controller);
        controller.run();
    }

}
