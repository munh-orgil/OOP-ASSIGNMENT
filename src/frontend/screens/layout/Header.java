package frontend.screens.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Header {
    public JPanel header = new JPanel(new FlowLayout());
    
    public Header() {
        header.setPreferredSize(new Dimension(Layout.frame.getWidth(), 100));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        
    }
}
