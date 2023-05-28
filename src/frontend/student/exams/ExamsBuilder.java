package frontend.student.exams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.modules.Modules;
import frontend.student.Questions;
import frontend.student.layout.Layout;
import frontend.widgets.CustomButton;

public class ExamsBuilder extends JPanel {
    public ExamsBuilder(Vector<backend.models.Exams> examList, backend.models.Classes classinfo) {
        GridLayout gl = new GridLayout(examList.size() + 4, 1);
        setLayout(gl);
            
        for(int j = 0; j < examList.size(); j++) {
            backend.models.Exams exam = examList.get(j);
            FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
            JPanel panel = new JPanel(fl);

            JPanel buttonPanel = new JPanel(new BorderLayout());
            JPanel westButtonPanel = new JPanel(new BorderLayout());
            JLabel examTitle = new JLabel(exam.Title);
            Font titleFont = examTitle.getFont();
            examTitle.setFont(titleFont.deriveFont(24f));
            westButtonPanel.add(examTitle, BorderLayout.NORTH);
            
            JLabel examDescription = new JLabel(exam.Description);
            Font descriptionFont = examDescription.getFont();
            examDescription.setFont(descriptionFont.deriveFont(18f));
            examDescription.setForeground(new Color(100, 100, 100));
            westButtonPanel.add(examDescription, BorderLayout.SOUTH);
            westButtonPanel.setOpaque(false);

            JPanel eastButtonPanel = new JPanel(new BorderLayout());
            JLabel className = new JLabel("Анги: " + classinfo.Name);
            Font classNameFont = className.getFont();
            className.setFont(classNameFont.deriveFont(24f));
            className.setForeground(new Color(100, 200, 100));
            eastButtonPanel.add(className, BorderLayout.NORTH);

            JLabel startDate = new JLabel("Эхлэх: " + exam.StartTime);
            Font startDateFont = startDate.getFont();
            startDate.setFont(startDateFont.deriveFont(18f));
            startDate.setForeground(new Color(100, 100, 200));
            eastButtonPanel.add(startDate, BorderLayout.SOUTH);
            eastButtonPanel.setOpaque(false);

            buttonPanel.add(westButtonPanel, BorderLayout.WEST);
            buttonPanel.add(eastButtonPanel, BorderLayout.EAST);
            buttonPanel.setOpaque(false);

            CustomButton examButton = new CustomButton("", Modules.screenWidth - 600, 100);
            examButton.setRadius(20);
            examButton.add(buttonPanel);
            panel.add(examButton);
            
            add(panel, BorderLayout.CENTER);

            examButton.addActionListener(new ActionListener () {
                public void actionPerformed(ActionEvent ae) {
                    Questions questions = new Questions(exam.Id);
                    Layout.contentPanel.add(questions, "questions");
                    Layout.cardLayout.show(Layout.contentPanel, "questions");
                }
            });
        }
    }
}
