package frontend.screens;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Classes {
    private JFrame frame = new JFrame();
    public JPanel classesPanel = new JPanel(new BorderLayout());

    public Classes() {
        classesPanel.setBackground(Color.BLUE);
        frame.add(classesPanel, frame);
    }
}
