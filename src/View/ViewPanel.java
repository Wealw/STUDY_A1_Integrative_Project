package View;

import Contract.IModel;
import Model.Map;
import Model.Point;
import Model.Vector;
import Model.Vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import static java.lang.StrictMath.abs;

public class ViewPanel extends JPanel implements Observer
{
    private final IModel model;
    final BufferedImage logo = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("./cesi.png")));
    final BufferedImage car = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("./car.png")));
    private ViewFrame viewFrame;

    public ViewPanel(IModel model) throws IOException {
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
        graphics2D.drawString("Carte : " + Map.getInstance().getName(), 425, 275);
        for (Point intersection : Map.getInstance().getIntersections()){
            graphics2D.setStroke(new BasicStroke(1));
            graphics2D.drawOval(intersection.getX(),intersection.getY(),15,15);
        }
        for (Vector connection : Map.getInstance().getConnections()){
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.drawLine(connection.getOrigin().getX()+8,connection.getOrigin().getY()+8,connection.getDestination().getX()+8,connection.getDestination().getY()+8);
        }
        for (Vehicle vehicle : this.model.getVehicles()){
            graphics2D.drawImage(car, (abs(vehicle.getSection().getOrigin().getX()+vehicle.getSection().getDestination().getX())/2)-14, (abs(vehicle.getSection().getOrigin().getY()+vehicle.getSection().getDestination().getY())/2)-25, null);
        }
    }

    public void paintVehiculeData(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 750, 600, 250);
        graphics2D.setColor(Color.BLACK);
        this.setFont(new Font("Dialog", Font.BOLD, 20));
        graphics2D.drawString("DONNEES DU VEHICULE", 175, 775);
        graphics2D.drawString("Prochain point : " + this.model.getVehicles().get(0).getSection().getDestination().getId(), 15, 850);
        graphics2D.drawString("Distance parcourue sur la section : " + this.model.getVehicles().get(0).getDistanceOnSection() + " cm", 15, 880);
        graphics2D.drawString("Distance totale parcourue : " + this.model.getVehicles().get(0).getTotalDistance() + " cm", 15, 910);
        graphics2D.drawString("Vitesse du véhicule : " + this.model.getVehicles().get(0).getSpeed() + " m/s", 15, 940);
    }

    public void paintUserData(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 700, 200);
        graphics2D.setColor(Color.BLACK);
        this.setFont(new Font("Dialog", Font.BOLD, 20));
        graphics2D.drawString("INFORMATIONS DU GROUPE", 225, 50);
        graphics2D.drawString("GRP6 - K2000 (Version 1.0)", 15, 100);
        graphics2D.drawString("AMARY Clément, CARRE Etienne, DE GROSSI Hugo, WEIMANN Théo", 15, 150);
    }

    public void suckingDicks(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(700, 750, 300, 250);
        graphics2D.drawImage(logo, 675, 750, null);
    }
}