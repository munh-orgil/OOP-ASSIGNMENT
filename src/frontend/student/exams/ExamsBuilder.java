package frontend.student.exams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Model;
import backend.models.Classes;
import frontend.modules.Modules;
import frontend.student.Questions;
import frontend.student.layout.Layout;
import frontend.widgets.CustomButton;

public class ExamsBuilder extends JPanel {
    public ExamsBuilder(Vector<backend.models.Exams> examList, String title) {
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);
        
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(title);
        Font font = titleLabel.getFont();
        titleLabel.setFont(font.deriveFont(28f));
        titlePanel.add(titleLabel);
        titlePanel.setMaximumSize(Modules.getPreferredHeight(titlePanel, (int)(Modules.screenWidth * 0.55)));
        add(titlePanel);
        
        for(int i = 0; i < examList.size(); i++) {
            backend.models.Exams exam = examList.get(i);
            Classes classFilter = new Classes();
            classFilter.Id = exam.ClassId;
            Vector<Classes> classList = Model.List(Classes.class, classFilter);
            Classes classinfo = classList.get(0);
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
            JLabel className = new JLabel("Хичээл: " + classinfo.Name);
            Font classNameFont = className.getFont();
            className.setFont(classNameFont.deriveFont(24f));
            className.setForeground(new Color(100, 200, 100));
            eastButtonPanel.add(className, BorderLayout.NORTH);

            JLabel startDate = new JLabel("Эхлэх: " + exam.StartDate + " " + exam.StartTime);
            Font startDateFont = startDate.getFont();
            startDate.setFont(startDateFont.deriveFont(18f));
            startDate.setForeground(new Color(100, 100, 200));
            eastButtonPanel.add(startDate, BorderLayout.SOUTH);
            eastButtonPanel.setOpaque(false);

            buttonPanel.add(westButtonPanel, BorderLayout.WEST);
            buttonPanel.add(eastButtonPanel, BorderLayout.EAST);
            buttonPanel.setOpaque(false);
            
            CustomButton examButton = new CustomButton("", (int)(Modules.screenWidth * 0.55), 100);
            examButton.setRadius(20);
            examButton.add(buttonPanel);
            panel.add(examButton);
            panel.setMaximumSize(Modules.getPreferredHeight(panel, (int)(Modules.screenWidth * 0.58)));

            add(panel);

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

