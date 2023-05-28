package frontend.teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormContainer extends JPanel {
    private JTextField textField;
    private JComboBox<String> inputTypeComboBox;
    private JPanel answerPanel;
    private List<JTextField> optionTextFields;

    public FormContainer() {
        setLayout(new BorderLayout());

        // Question panel
        JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel questionLabel = new JLabel("Question:");
        JTextField questionTextField = new JTextField(20);
        questionPanel.add(questionLabel);
        questionPanel.add(questionTextField);

        // Input type panel
        JPanel inputTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel inputTypeLabel = new JLabel("Input Type:");
        inputTypeComboBox = new JComboBox<>(new String[]{"Text Field", "Radio Button", "Checkbox"});
        inputTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnswerPanel();
            }
        });
        inputTypePanel.add(inputTypeLabel);
        inputTypePanel.add(inputTypeComboBox);

        // Answer panel
        answerPanel = new JPanel();
        optionTextFields = new ArrayList<>();

        // Add components to the main container
        add(questionPanel, BorderLayout.NORTH);
        add(inputTypePanel, BorderLayout.CENTER);
        add(answerPanel, BorderLayout.SOUTH);

        // Initialize the answer panel
        updateAnswerPanel();
    }

    private void updateAnswerPanel() {
        answerPanel.removeAll();
        optionTextFields.clear();
    
        String selectedInputType = (String) inputTypeComboBox.getSelectedItem();
    
        if (selectedInputType.equals("Text Field")) {
            JLabel answerLabel = new JLabel("Answer:");
            textField = new JTextField(20);
            answerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            answerPanel.add(answerLabel);
            answerPanel.add(textField);
        } else if (selectedInputType.equals("Radio Button")) {
            JLabel answerLabel = new JLabel("Options:");
            JPanel radioButtonPanel = new JPanel(new BorderLayout());
    
            JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
            optionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add 5-pixel padding
            JTextField optionTextField = new JTextField(15);
            optionTextFields.add(optionTextField);
            optionsPanel.add(optionTextField);
    
            JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Wrap the button in a JPanel
            JButton addButton = new JButton("Add Option");
            addButton.setPreferredSize(new Dimension(100, 25)); // Set preferred size
            addButton.addActionListener(e -> {
                JTextField newOptionTextField = new JTextField(15);
                optionTextFields.add(newOptionTextField);
                optionsPanel.add(newOptionTextField);
                answerPanel.revalidate();
                answerPanel.repaint();
            });
    
            addButtonPanel.add(addButton); // Add the button to the button panel
    
            radioButtonPanel.add(answerLabel, BorderLayout.NORTH);
            radioButtonPanel.add(optionsPanel, BorderLayout.CENTER);
            radioButtonPanel.add(addButtonPanel, BorderLayout.SOUTH); // Add the button panel to the SOUTH position
    
            answerPanel.setLayout(new BorderLayout());
            answerPanel.add(radioButtonPanel, BorderLayout.CENTER);
        } else if (selectedInputType.equals("Checkbox")) {
            JLabel answerLabel = new JLabel("Options:");
            JPanel checkboxPanel = new JPanel(new BorderLayout());
    
            JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
            optionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add 5-pixel padding
            JTextField optionTextField = new JTextField(15);
            optionTextFields.add(optionTextField);
            optionsPanel.add(optionTextField);
    
            JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Wrap the button in a JPanel
            JButton addButton = new JButton("Add Option");
            addButton.setPreferredSize(new Dimension(100, 25)); // Set preferred size
            addButton.addActionListener(e -> {
                JTextField newOptionTextField = new JTextField(15);
                optionTextFields.add(newOptionTextField);
                optionsPanel.add(newOptionTextField);
                answerPanel.revalidate();
                answerPanel.repaint();
            });
    
            addButtonPanel.add(addButton); // Add the button to the button panel
    
            checkboxPanel.add(answerLabel, BorderLayout.NORTH);
            checkboxPanel.add(optionsPanel, BorderLayout.CENTER);
            checkboxPanel.add(addButtonPanel, BorderLayout.SOUTH); // Add the button panel to the SOUTH position
    
            answerPanel.setLayout(new BorderLayout());
            answerPanel.add(checkboxPanel, BorderLayout.CENTER);
        }
    
        answerPanel.revalidate();
        answerPanel.repaint();
    }
    

    public String getQuestion() {
        Component[] components = getComponents();
        JPanel questionPanel = (JPanel) components[0];
        JTextField questionTextField = (JTextField) questionPanel.getComponent(1);
        return questionTextField.getText();
    }

    public String getAnswer() {
        String selectedInputType = (String) inputTypeComboBox.getSelectedItem();
        StringBuilder answer = new StringBuilder();

        if (selectedInputType.equals("Text Field")) {
            answer.append(textField.getText());
        } else if (selectedInputType.equals("Radio Button") || selectedInputType.equals("Checkbox")) {
            for (JTextField optionTextField : optionTextFields) {
                answer.append(optionTextField.getText()).append(", ");
            }
            if (!optionTextFields.isEmpty()) {
                answer.delete(answer.length() - 2, answer.length()); // Remove the trailing comma and space
            }
        }

        return answer.toString();
    }
}
