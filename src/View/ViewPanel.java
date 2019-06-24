package View;

import Contract.IModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewPanel extends JPanel implements Observer
{
    private final IModel model;
    private ViewFrame viewFrame;

    public ViewPanel(IModel model)
    {
        this.model = model;
        if (this.model == null)
            System.err.println("model null in viewPanel builder");
        setVisible(true);
    }

    public void update(final Observable observable, final Object object)
    {
        this.repaint();
    }

    private ViewFrame getViewFrame()
    {
        return this.viewFrame;
    }

    private void setViewFrame(final ViewFrame viewFrame)
    {
        this.viewFrame = viewFrame;
    }

    @Override
    protected void paintComponent(final Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D) graphics;
        paintMap(graphics2D);
        paintVehiculeData(graphics2D);
        paintUserData(graphics2D);
        suckingDicks(graphics2D);
    }

    public void paintMap(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(50, 250, 900, 450);
        graphics2D.setColor(Color.BLACK);
        this.setFont(new Font("Dialog", Font.BOLD, 20));
        graphics2D.drawString("NOM DE LA MAP A INSERER", 375, 275);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.drawOval(100,330,15,15);
        graphics2D.drawOval(266,330,15,15);
        graphics2D.drawOval(599,330,15,15);
        graphics2D.drawOval(764,330,15,15);
        graphics2D.drawOval(875,330,15,15);
        graphics2D.drawOval(100,413,15,15);
        graphics2D.drawOval(266,413,15,15);
        graphics2D.drawOval(599,413,15,15);
        graphics2D.drawOval(100,496,15,15);
        graphics2D.drawOval(764,496,15,15);
        graphics2D.drawOval(875,496,15,15);
        graphics2D.drawOval(266,558,15,15);
        graphics2D.drawOval(764,558,15,15);
        graphics2D.drawOval(100,621,15,15);
        graphics2D.drawOval(266 ,621,15,15);
        graphics2D.drawOval(875,621,15,15);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(116,338, 264,338);
        graphics2D.drawLine(282,338, 597,338);
        graphics2D.drawLine(615,338, 762,338);
        graphics2D.drawLine(780,338, 873,338);
        graphics2D.drawLine(108,346, 108,411);
        graphics2D.drawLine(274,346, 274,411);
        graphics2D.drawLine(607,346, 607,411);
        graphics2D.drawLine(108,429, 108,496);
        graphics2D.drawLine(108,512, 108,621);
        graphics2D.drawLine(116,421, 264,421);
        graphics2D.drawLine(282,421, 597,421);
        graphics2D.drawLine(772,346, 772,494);
        graphics2D.drawLine(883,346, 883,494);
        graphics2D.drawLine(116,504, 762,504);
        graphics2D.drawLine(780,504, 873,504);
        graphics2D.drawLine(116,629, 264,629);
        graphics2D.drawLine(274,620, 274,574);
        graphics2D.drawLine(282,566, 762,566);
        graphics2D.drawLine(772,558, 772,512);
        graphics2D.drawLine(282,629, 873,629);
        graphics2D.drawLine(883,621, 883,512);
    }

    public void paintVehiculeData(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 750, 600, 250);
        graphics2D.setColor(Color.BLACK);
        this.setFont(new Font("Dialog", Font.BOLD, 20));
        graphics2D.drawString("DONNEES DU VEHICULE", 175, 775);
        graphics2D.drawString("POINT", 15, 850);
        graphics2D.drawString("DISTANCE", 15, 900);
        graphics2D.drawString("DISTANCE TOTALE", 15, 950);
    }

    public void paintUserData(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 700, 200);
        graphics2D.setColor(Color.BLACK);
        this.setFont(new Font("Dialog", Font.BOLD, 20));
        graphics2D.drawString("INFORMATIONS DU GROUPE", 225, 50);
        graphics2D.drawString("GR6 - K2000 (Version 1.0)", 15, 100);
        graphics2D.drawString("AMARY Clément, CARRE Etienne, DE GROSSI Hugo, WEIMANN Théo", 15, 150);
    }

    public void suckingDicks(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(700, 750, 300, 250);
    }
}
