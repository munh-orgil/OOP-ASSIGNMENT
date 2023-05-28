package frontend.teacher;

import javax.swing.*;
import java.awt.*;

public class QuestionContainer extends JPanel {
    private JTextField questionTextField;
    private JComboBox<String> inputTypeComboBox;
    private JPanel answerPanel;
    private JTextField textField;
    private java.util.List<JTextField> optionTextFields;

    public QuestionContainer() {
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());

        JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel questionLabel = new JLabel("Question:");
        questionTextField = new JTextField(30);
        questionPanel.add(questionLabel);
        questionPanel.add(questionTextField);

        JPanel inputTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel inputTypeLabel = new JLabel("Input Type:");
        inputTypeComboBox = new JComboBox<>(new String[] {"Text Field", "Radio Button", "Checkbox"});
        inputTypeComboBox.addActionListener(e -> updateAnswerPanel());
        inputTypePanel.add(inputTypeLabel);
        inputTypePanel.add(inputTypeComboBox);

        answerPanel = new JPanel();
        answerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        updateAnswerPanel();

        add(questionPanel, BorderLayout.NORTH);
        add(inputTypePanel, BorderLayout.CENTER);
        add(answerPanel, BorderLayout.SOUTH);
    }

    private void updateAnswerPanel() {
        answerPanel.removeAll();
        optionTextFields = new java.util.ArrayList<>();

        String selectedInputType = (String) inputTypeComboBox.getSelectedItem();

        if (selectedInputType.equals("Text Field")) {
            JLabel answerLabel = new JLabel("Answer:");
            textField = new JTextField(20);
            answerPanel.add(answerLabel);
            answerPanel.add(textField);
        } else if (selectedInputType.equals("Radio Button")) {
            JLabel answerLabel = new JLabel("Options:");
            JPanel radioButtonPanel = new JPanel(new BorderLayout());

            JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
            JTextField optionTextField = new JTextField(15);
            optionTextFields.add(optionTextField);
            optionsPanel.add(optionTextField);

            JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addButton = new JButton("Add Option");
            addButton.addActionListener(e -> {
                JTextField newOptionTextField = new JTextField(15);
                optionTextFields.add(newOptionTextField);
                optionsPanel.add(newOptionTextField);
                answerPanel.revalidate();
                answerPanel.repaint();
            });

            addButtonPanel.add(addButton);

            radioButtonPanel.add(answerLabel, BorderLayout.NORTH);
            radioButtonPanel.add(optionsPanel, BorderLayout.CENTER);
            radioButtonPanel.add(addButtonPanel, BorderLayout.SOUTH);

            answerPanel.add(radioButtonPanel);
        } else if (selectedInputType.equals("Checkbox")) {
            JLabel answerLabel = new JLabel("Options:");
            JPanel checkboxPanel = new JPanel(new BorderLayout());

            JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
            JTextField optionTextField = new JTextField(15);
            optionTextFields.add(optionTextField);
            optionsPanel.add(optionTextField);

            JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addButton = new JButton("Add Option");
            addButton.addActionListener(e -> {
                JTextField newOptionTextField = new JTextField(15);
                optionTextFields.add(newOptionTextField);
                optionsPanel.add(newOptionTextField);
                answerPanel.revalidate();
                answerPanel.repaint();
            });

            addButtonPanel.add(addButton);

            checkboxPanel.add(answerLabel, BorderLayout.NORTH);
            checkboxPanel.add(optionsPanel, BorderLayout.CENTER);
            checkboxPanel.add(addButtonPanel, BorderLayout.SOUTH);

            answerPanel.add(checkboxPanel);
        }

        answerPanel.revalidate();
        answerPanel.repaint();
    }
}
