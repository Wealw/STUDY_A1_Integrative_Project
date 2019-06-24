package View;

import Contract.IModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewPanel extends JPanel implements Observer {
    
    private final IModel model;
    private ViewFrame viewFrame;
    
    public ViewPanel(IModel model) {
        this.model = model;
        if(this.model == null) System.err.println("model null in viewPanel builder");
        setVisible(true);
    }
    
    public void update(final Observable observable, final Object object) {
        this.repaint();
    }
    
    private ViewFrame getViewFrame() {
        return this.viewFrame;
    }
    
    private void setViewFrame(final ViewFrame viewFrame) {
        this.viewFrame = viewFrame;
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        paintMap(graphics);
        paintVehiculeData(graphics);
        paintUserData(graphics);
        suckingDicks(graphics);
    }
    
    public void paintMap(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(40, 250, 900, 450);
    }
    
    public void paintVehiculeData(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 750, 600, 250);
        this.setFont(new Font ("Dialog", Font.BOLD, 20));
    }
    
    public void paintUserData(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 300, 200);
        graphics.fillRect(700, 0, 300, 200);
        this.setFont(new Font ("Dialog", Font.BOLD, 20));
    }
    
    public void suckingDicks(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(700, 750, 300, 250);
    }
}