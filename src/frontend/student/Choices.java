package frontend.student;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import backend.Model;
import frontend.modules.Modules;
import frontend.widgets.CustomTextField;

public class Choices extends JPanel {
    public Choices(backend.models.Questions question) {
        backend.models.Choices choicesFilter = new backend.models.Choices();
        choicesFilter.QuestionId = question.Id;
        Vector<backend.models.Choices> choiceList = Model.List(backend.models.Choices.class, choicesFilter);
        BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(bl);
        switch (question.Type) {
            case "radio": {
                ButtonGroup buttonGroup = new ButtonGroup();
                for(int i = 0; i < choiceList.size(); i++) {
                    backend.models.Choices choice = choiceList.get(i);
                    JRadioButton radio = new JRadioButton(choice.Text);
                    buttonGroup.add(radio);
                    add(radio);
                }
                break;
            }
            case "text": {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
                CustomTextField textField = new CustomTextField(50);
                textField.setPlaceholderText("test");
                textField.setPreferredSize(new Dimension((int)(Modules.screenWidth * 0.4), 60));
                panel.add(textField);
                add(panel);
                break;
            }
            case "checkbox": {
                for(int i = 0; i < choiceList.size(); i++) {
                    backend.models.Choices choice = choiceList.get(i);
                    JCheckBox checkBox = new JCheckBox(choice.Text);
                    add(checkBox);  
                }
                break;
            }
        }
    }
}
