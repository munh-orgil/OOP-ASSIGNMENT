package frontend.teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuestionContainer extends JPanel {
    private JTextField questionTextField;
    private JComboBox<String> inputTypeComboBox;
    private JPanel answerPanel;
    private JTextField textField;
    private List<JTextField> optionTextFields;
    private List<JRadioButton> optionRadioButtons;
    private List<JCheckBox> optionCheckBoxes;

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
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
        updateAnswerPanel();

        add(questionPanel, BorderLayout.NORTH);
        add(inputTypePanel, BorderLayout.CENTER);
        add(answerPanel, BorderLayout.SOUTH);
    }

    private void updateAnswerPanel() {
        answerPanel.removeAll();
        optionTextFields = new ArrayList<>();
        optionRadioButtons = new ArrayList<>();
        optionCheckBoxes = new ArrayList<>();

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

            JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JTextField optionTextField = new JTextField(15);
            JRadioButton radioButton = new JRadioButton("Correct");
            optionTextFields.add(optionTextField);
            optionRadioButtons.add(radioButton);
            optionPanel.add(optionTextField);
            optionPanel.add(radioButton);
            optionsPanel.add(optionPanel);

            JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addButton = new JButton("Add Option");
            addButton.addActionListener(e -> {
                JPanel newOptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JTextField newOptionTextField = new JTextField(15);
                JRadioButton newRadioButton = new JRadioButton("Correct");
                optionTextFields.add(newOptionTextField);
                optionRadioButtons.add(newRadioButton);
                newOptionPanel.add(newOptionTextField);
                newOptionPanel.add(newRadioButton);
                optionsPanel.add(newOptionPanel);
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

            JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JTextField optionTextField = new JTextField(15);
            JCheckBox checkBox = new JCheckBox("Correct");
            optionTextFields.add(optionTextField);
            optionCheckBoxes.add(checkBox);
            optionPanel.add(optionTextField);
            optionPanel.add(checkBox);
            optionsPanel.add(optionPanel);

            JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addButton = new JButton("Add Option");
            addButton.addActionListener(e -> {
                JPanel newOptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JTextField newOptionTextField = new JTextField(15);
                JCheckBox newCheckBox = new JCheckBox("Correct");
                optionTextFields.add(newOptionTextField);
                optionCheckBoxes.add(newCheckBox);
                newOptionPanel.add(newOptionTextField);
                newOptionPanel.add(newCheckBox);
                optionsPanel.add(newOptionPanel);
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

    public String getQuestionText() {
        return questionTextField.getText();
    }

    public String getInputType() {
        return (String) inputTypeComboBox.getSelectedItem();
    }

    public String getAnswer() {
        String selectedInputType = (String) inputTypeComboBox.getSelectedItem();

        if (selectedInputType.equals("Text Field")) {
            return textField.getText();
        } else if (selectedInputType.equals("Radio Button")) {
            for (int i = 0; i < optionTextFields.size(); i++) {
                if (optionRadioButtons.get(i).isSelected()) {
                    return optionTextFields.get(i).getText();
                }
            }
        } else if (selectedInputType.equals("Checkbox")) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < optionTextFields.size(); i++) {
                if (optionCheckBoxes.get(i).isSelected()) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(optionTextFields.get(i).getText());
                }
            }
            return stringBuilder.toString();
        }

        return "";
    }
}
