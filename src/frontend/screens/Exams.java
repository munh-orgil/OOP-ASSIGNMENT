package frontend.screens;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exams {
    private JFrame frame;
    public static JPanel examsPanel = new JPanel(new GridBagLayout());

    public Exams() {
        examsPanel.setBackground(Color.GREEN);
        frame.add(examsPanel, frame);
    }
}
