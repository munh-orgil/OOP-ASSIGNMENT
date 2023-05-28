package frontend.teacher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddExam extends JPanel {
    private JButton addQuestionButton;
    private JPanel containerPanel;

    public AddExam() {
        setLayout(new BorderLayout());

        // Create a panel to hold the containers
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        // Create a QuestionContainer
        QuestionContainer questionContainer = new QuestionContainer();

        // Create a FormContainer
        FormContainer formContainer = new FormContainer();

        // Add the containers to the container panel
        containerPanel.add(questionContainer);
        containerPanel.add(formContainer);

        // Create the "Add Question" button
        addQuestionButton = new JButton("Add Question");
        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuestionContainer newQuestionContainer = new QuestionContainer();
                containerPanel.add(newQuestionContainer);
                revalidate();
                repaint();
            }
        });

        // Create a scroll pane to hold the container panel
        JScrollPane scrollPane = new JScrollPane(containerPanel);

        // Add components to the main panel
        add(addQuestionButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}