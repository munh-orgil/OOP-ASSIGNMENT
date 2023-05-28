package frontend.widgets;

import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField implements FocusListener {
    private Color placeholderColor;
    private String placeholderText;
    private boolean isPlaceholderActive;

    public CustomTextField(int columns) {
        super(columns);
        setPlaceholderColor(Color.GRAY);
        addFocusListener(this);
    }

    public void setPlaceholderColor(Color color) {
        placeholderColor = color;
        setForeground(placeholderColor);
    }

    public void setPlaceholderText(String text) {
        placeholderText = text;
        setText(placeholderText);
        isPlaceholderActive = true;
    }

    public String getActualText() {
        return isPlaceholderActive ? "" : getText();
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (isPlaceholderActive) {
            setText("");
            setForeground(Color.BLACK);
            isPlaceholderActive = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setText(placeholderText);
            setForeground(placeholderColor);
            isPlaceholderActive = true;
        }
    }

    @Override
    public void setBorder(Border border) {
        super.setBorder(border);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
    }
}
