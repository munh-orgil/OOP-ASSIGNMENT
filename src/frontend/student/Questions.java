package frontend.student;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Model;
import frontend.modules.Modules;

public class Questions extends JPanel {
    public Questions(int examId) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        backend.models.Questions questionsFilter = new backend.models.Questions();
        questionsFilter.ExamId = examId;
        Vector<backend.models.Questions> questionList = Model.List(backend.models.Questions.class, questionsFilter);
        Collections.sort(questionList);
        for(int i = 0 ; i < questionList.size(); i++) {
            backend.models.Questions question = questionList.get(i);
            String text = question.Ord + ". " + question.Title;
            JLabel questionTitle = new JLabel(text);
            questionTitle.setFont(getFont().deriveFont(20f));
            JPanel questionPanel = new JPanel(new BorderLayout());
            questionPanel.add(questionTitle, BorderLayout.NORTH);
            questionPanel.add(new frontend.student.Choices(question), BorderLayout.SOUTH);
            questionPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
            questionPanel.setMaximumSize(Modules.getPreferredHeight(questionPanel, (int)(Modules.screenWidth * 0.55)));

            add(questionPanel);
        }
    }
}
