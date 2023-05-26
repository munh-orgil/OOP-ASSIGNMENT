package frontend.widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class CustomButton extends JButton {
    private int borderRadius = 0;
    public CustomButton(String text, int width, int height) {
        super(text);
        setOpaque(false); 
        setFocusPainted(false); 
        setContentAreaFilled(false); 
        setForeground(Color.BLACK); 
        setPreferredSize(new Dimension(width, height));
    }

    public void setRadius(int radius) {
        borderRadius = radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
        
        if (getModel().isPressed()) {
            g2.setColor(new Color(200, 200, 200));
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(220, 220, 220));
        } else {
            g2.setColor(Color.white);
        }

        
        g2.fill(roundedRectangle);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);

        g2.draw(roundedRectangle);

        g2.dispose();
    }
}