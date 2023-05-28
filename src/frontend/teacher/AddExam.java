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
import javax.swing.JTextField;

public class AddExam {

    private JTextField questionField;
    private JComboBox<String> typeComboBox;
    private JPanel answerPanel;
    private JTextField textField;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;

    public AddExam() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel questionPanel = createQuestionPanel();
        answerPanel = createAnswerPanel();

        mainPanel.add(questionPanel);
        mainPanel.add(answerPanel);

        JButton submitButton = new JButton("Submit");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Google Forms Question Module");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(containerPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        questionField = new JTextField();
        questionField.setPreferredSize(new Dimension(200, 25));

        JLabel questionLabel = new JLabel("Question:");
        JPanel questionInputPanel = new JPanel();
        questionInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        questionInputPanel.add(questionLabel);
        questionInputPanel.add(questionField);

        questionPanel.add(questionInputPanel, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Question Type");

        JMenuItem fieldItem = new JMenuItem("Field");
        fieldItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTextField();
            }
        });

        JMenuItem radioItem = new JMenuItem("Radio");
        radioItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRadioButtons();
            }
        });

        JMenuItem checkboxItem = new JMenuItem("Checkbox");
        checkboxItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCheckBoxes();
            }
        });

        menu.add(fieldItem);
        menu.add(radioItem);
        menu.add(checkboxItem);
        menuBar.add(menu);

        questionPanel.add(menuBar, BorderLayout.SOUTH);

        return questionPanel;
    }

    private JPanel createAnswerPanel() {
        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new BorderLayout());
        answerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));

        answerPanel.add(textField, BorderLayout.CENTER);

        return answerPanel;
    }

    private void showTextField() {
        answerPanel.removeAll();
        answerPanel.add(textField, BorderLayout.CENTER);
        answerPanel.revalidate();
        answerPanel.repaint();
    }

    private void showRadioButtons() {
        answerPanel.removeAll();
        radioButton1 = new JRadioButton("Option 1");
        radioButton2 = new JRadioButton("Option 2");

        JPanel radioPanel = new JPanel();
        radioPanel.add(radioButton1);
        radioPanel.add(radioButton2);

        answerPanel.add(radioPanel, BorderLayout.CENTER);
        answerPanel.revalidate();
        answerPanel.repaint();
    }

    private void showCheckBoxes() {
        answerPanel.removeAll();
        checkBox1 = new JCheckBox("Option 1");
        checkBox2 = new JCheckBox("Option 2");

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.add(checkBox1);
        checkboxPanel.add(checkBox2);

        answerPanel.add(checkboxPanel, BorderLayout.CENTER);
        answerPanel.revalidate();
        answerPanel.repaint();
    }

    public static void main(String[] args) {
        new AddExam();
    }
}
