package View;

import Contract.IModel;

import javax.swing.*;
import java.awt.*;

public class ViewFrame extends JFrame {
    
    private final IModel model;
    private int width = 1000;
    private int height = 1000;
    
    private ViewPanel panel;
    
    ViewFrame(IModel model) {
        this.model = model;
        this.setTitle("GRP6-K2000");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new ViewPanel(this.model);
        this.panel.setBackground(Color.BLACK);
        this.setContentPane(this.panel);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void printMessage(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public void repaint() {
        this.panel.repaint();
    }
    
    public ViewPanel getViewPanel() {
        return this.panel;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
}