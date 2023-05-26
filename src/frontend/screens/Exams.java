package frontend.screens;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exams {
    private JFrame frame = new JFrame();
    public JPanel examsPanel = new JPanel(new BorderLayout());

    public Exams() {
        examsPanel.setBackground(Color.GREEN);
        frame.add(examsPanel, frame);
    }
}
