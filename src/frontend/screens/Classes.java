package frontend.screens;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Classes {
    private JFrame frame;
    public static JPanel classesPanel = new JPanel(new GridBagLayout());

    public Classes() {
        classesPanel.setBackground(Color.BLUE);
        frame.add(classesPanel, frame);
    }
}
