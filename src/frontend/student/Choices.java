package frontend.student;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Objects;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import backend.Model;
import backend.models.Answers;
import frontend.modules.Modules;
import frontend.widgets.CustomTextField;

public class Choices extends JPanel {
    public String answerText = "";
    public Integer choiceId = 0;
    public Vector<Integer> checkBoxes = new Vector<>();
    public Integer questionId = 0;
    public Integer fullScore = 0;
    public Integer score = 0;
    public Choices(backend.models.Questions question) {
        questionId = question.Id;

        backend.models.Choices choicesFilter = new backend.models.Choices();
        choicesFilter.QuestionId = question.Id;
        Vector<backend.models.Choices> choiceList = Model.List(backend.models.Choices.class, choicesFilter);

        Answers answerFilter = new Answers();
        answerFilter.UserId = Modules.user.Id;
        answerFilter.QuestionId = questionId;
        Vector<Answers> answersList = Model.List(Answers.class, answerFilter);
        Answers answer = new Answers();
        if(answersList.size() > 0) {
            answer = answersList.get(0);
        }

        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);
        switch (question.Type) {
            case "radio": {
                ButtonGroup buttonGroup = new ButtonGroup();
                for(int i = 0; i < choiceList.size(); i++) {
                    backend.models.Choices choice = choiceList.get(i);
                    fullScore += choice.Score;
                    JRadioButton radio = new JRadioButton(choice.Text);
                    if(!Objects.isNull(answer) && answer.ChoiceId == choice.Id) {
                        radio.setSelected(true);
                        score += choice.Score;
                    }
                    buttonGroup.add(radio);
                    radio.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            choiceId = choice.Id;
                        }
                    });
                    add(radio);
                }
                break;
            }
            case "text": {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
                CustomTextField textField = new CustomTextField(50);
                textField.setPreferredSize(new Dimension((int)(Modules.screenWidth * 0.4), 60));
                textField.setText(answer.Text);
                if(!Objects.isNull(answer) && !Objects.isNull(choiceList) && choiceList.size() > 0) {
                    fullScore += choiceList.get(0).Score;
                    if(choiceList.get(0).Text.equals(textField.getText())) {
                        score += choiceList.get(0).Score;
                    }
                }
                panel.add(textField);
                textField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        answerText = textField.getText();
                    }
        
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        answerText = textField.getText();
                    }
        
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        answerText = textField.getText();
                    }
                });
                add(panel);
                break;
            }
            case "checkbox": {
                for(int i = 0; i < choiceList.size(); i++) {
                    backend.models.Choices choice = choiceList.get(i);
                    fullScore += choice.Score;
                    JCheckBox checkBox = new JCheckBox(choice.Text);
                    if(!Objects.isNull(answer.Text)) {
                        if(answer.Text.contains(choice.Id.toString())) {
                            checkBox.setSelected(true);
                            score += choice.Score;
                        }
                    }
                    checkBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            if (checkBox.isSelected()) {
                                checkBoxes.add(choice.Id);
                            } else {
                                checkBoxes.remove(choice.Id);
                            }
                        }
                    });
                    add(checkBox);  
                }
                break;
            }
        }
    }

    public Integer fullScore() {
        return fullScore;
    }
    public Integer getScore() {
        return score;
    }

    public String getText() {
        return answerText;
    }
    public Integer getChoice() {
        return choiceId;
    }
    public Vector<Integer> getCheckBox() {
        if(!Objects.isNull(checkBoxes)) {
            Collections.sort(checkBoxes);
        }
        return checkBoxes;
    }
}
