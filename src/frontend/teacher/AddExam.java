package frontend.teacher;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class AddExam extends JPanel {

    private JPanel questionPanel;
    private JButton addQuestionButton;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JSpinner timeSpinner;
    private JFormattedTextField durationField;

    public AddExam() {
        setLayout(new BorderLayout());

        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

        
        addQuestionButton = new JButton("Add Question");
        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestionContainer();
            }
        });
        add(addQuestionButton, BorderLayout.SOUTH);

        // Create the date components
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        dayComboBox = new JComboBox<>(days);

        String[] months = new String[]{
                "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"
        };
        monthComboBox = new JComboBox<>(months);

        // Create the time component
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        // Create the duration component
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        durationField = new JFormattedTextField(formatter);
        durationField.setColumns(7); // Set minimum width to 60 pixels

        JPanel pickerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pickerPanel.add(new JLabel("Date: "));
        pickerPanel.add(dayComboBox);
        pickerPanel.add(monthComboBox);
        pickerPanel.add(new JLabel("Time: "));
        pickerPanel.add(timeSpinner);
        pickerPanel.add(new JLabel("Duration: "));
        pickerPanel.add(durationField);

        add(pickerPanel, BorderLayout.NORTH);

    }

    private void addQuestionContainer() {
        QuestionContainer questionContainer = new QuestionContainer();
        questionPanel.add(questionContainer);
        questionPanel.revalidate();
        questionPanel.repaint();
    }

}
