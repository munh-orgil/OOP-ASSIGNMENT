package frontend.student;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Model;
import backend.models.Answers;
import backend.models.Exams;
import frontend.modules.Modules;
import frontend.student.layout.Layout;
import frontend.widgets.CustomButton;

public class Questions extends JPanel {
    public Integer fullScore = 0;
    public Integer gotScore = 0;

    public Questions(int examId) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        backend.models.Questions questionsFilter = new backend.models.Questions();
        questionsFilter.ExamId = examId;
        Vector<backend.models.Questions> questionList = Model.List(backend.models.Questions.class, questionsFilter);
        
        Answers answersFilter = new Answers();
        answersFilter.ExamId = examId;
        Vector<Answers> answersList = Model.List(Answers.class, answersFilter);

        Exams examsFilter = new Exams();
        examsFilter.Id = examId;
        Vector<Exams> examsList = Model.List(Exams.class, examsFilter);
        Exams exam = examsList.get(0);

        Collections.sort(questionList);
        Vector<Choices> choices = new Vector<>();
        for(int i = 0 ; i < questionList.size(); i++) {
            backend.models.Questions question = questionList.get(i);
            String text = question.Ord + ". " + question.Title;
            JLabel questionTitle = new JLabel(text);
            questionTitle.setFont(getFont().deriveFont(20f));
            JPanel questionPanel = new JPanel(new BorderLayout());
            questionPanel.add(questionTitle, BorderLayout.NORTH);
            Choices choice = new frontend.student.Choices(question);
            
            fullScore += choice.fullScore();
            gotScore += choice.getScore();

            questionPanel.add(choice, BorderLayout.SOUTH);
            choices.add(choice);
            questionPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
            questionPanel.setMaximumSize(Modules.getPreferredHeight(questionPanel, (int)(Modules.screenWidth * 0.55)));

            add(questionPanel);
        }

        
        boolean ok = true;
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        int res = LocalDate.parse(exam.EndDate).compareTo(today);
        if(res == 0) {
            res = LocalTime.parse(exam.EndTime).compareTo(now);
        }
        if(res < 0) {
            ok = false;
        }
        if(answersList.size() == 0 && ok) {
            JPanel submitPanel = new JPanel();
            CustomButton submit = new CustomButton("Илгээх", 300, 50);
            submit.setRadius(10);
            submitPanel.add(submit);
            submitPanel.setMaximumSize(Modules.getPreferredHeight(submitPanel, 350));
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    for(int i = 0; i < choices.size(); i++) {
                        Choices choice = choices.get(i);
                        String text = choice.getText();
                        Integer choiceId = choice.getChoice();
                        Vector<Integer> checkboxes = choice.getCheckBox();
                        Answers answers = new Answers();
                        answers.ExamId = examId;
                        answers.QuestionId = choice.questionId;
                        answers.UserId = Modules.user.Id;
                        answers.Text = "";
                        answers.ChoiceId = 0;
    
                        if(text != "") {
                            answers.Text = text;
                        }
                        if(choiceId != 0) {
                            answers.ChoiceId = choiceId;
                        }
                        if(!Objects.isNull(checkboxes) && checkboxes.size() > 0) {
                            String res = "";
                            for(int j = 0; j < checkboxes.size() - 1; j++) {
                                res += checkboxes.get(j).toString() + ", ";
                            }
                            res += checkboxes.get(checkboxes.size() - 1).toString();
                            answers.Text = res;
                        }
    
                        Model.Create(answers);
                    }

                    Layout.cardLayout.show(Layout.contentPanel, "classes");
                }
            });
            add(submitPanel);
        } else {
            JPanel scorePanel = new JPanel(new FlowLayout());
            JLabel scoreLabel = new JLabel(gotScore.toString() + "/" + fullScore.toString());
            Font font = scoreLabel.getFont();
            scoreLabel.setFont(font.deriveFont(30f));
            scoreLabel.setForeground(new Color(100, 200, 100));
            scorePanel.add(scoreLabel);
            scorePanel.setMinimumSize(Modules.getPreferredHeight(scorePanel, 80));
            scorePanel.setMaximumSize(Modules.getPreferredHeight(scorePanel, 80));

            add(scorePanel);
        }
    }
}
