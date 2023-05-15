import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class mainFrame extends JFrame {
    final private Font mainFont = new Font("Product Sans", Font.BOLD,18);
    JTextField tfUsername, tfPassword;

    JLabel lbWelcome;

        public void initialize(){
            /*Form Panel*/
            JLabel lbUsername = new JLabel("Username");
            lbUsername.setFont(mainFont);
            lbUsername.setBorder(new EmptyBorder(20,20,20,20));

            tfUsername = new JTextField();
            tfUsername.setFont(mainFont);
            tfUsername.setBorder(new EmptyBorder(20,20,20,20));

            JLabel lbPassword = new JLabel("Password");
            lbPassword.setFont(mainFont);
            lbPassword.setBorder(new EmptyBorder(20,20,20,20));

            tfPassword = new JPasswordField();
            tfPassword.setFont(mainFont);
            tfPassword.setBorder(new EmptyBorder(20,20,20,20));

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(4,1,5,5));
            formPanel.add(lbUsername);
            formPanel.add(tfUsername);
            formPanel.add(lbPassword);
            formPanel.add(tfPassword);

            /*Form Panel End*/


            /*Welcome*/

            lbWelcome = new JLabel("Welcome to Exam Application");
            lbWelcome.setFont(mainFont);
            lbWelcome.setBorder(new EmptyBorder(20,20,20,20));

            /*Welcome End*/

            /*Buttons*/
            JButton btnOK = new JButton("Log In");
            btnOK.setFont(mainFont);
            btnOK.setBorder(new EmptyBorder(20,20,20,20));
            btnOK.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String Username = tfUsername.getText();
                    lbWelcome.setText("Hello" + " " + Username);
                    }
                
                });
            

                JButton btnClear = new JButton("Clear");
                btnClear.setFont(mainFont);
                btnClear.setBorder(new EmptyBorder(20,20,20,20));
                btnClear.addActionListener(new ActionListener() {
    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tfUsername.setText("");
                        tfPassword.setText("");
                        lbWelcome.setText("");
                        }
                    
                    });

                JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new GridLayout(1,2,5,5));
                buttonsPanel.add(btnOK);
                buttonsPanel.add(btnClear);

            /*Buttons End*/
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(20,20));
            mainPanel.setBackground(new Color(128,128,128));
            mainPanel.add(formPanel, BorderLayout.NORTH);
            mainPanel.add(lbWelcome, BorderLayout.CENTER);
            mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

            add(mainPanel);

            setTitle("Welcome");
            setSize(500,600);
            setMinimumSize(new Dimension(300,400));
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
        }

        public static void main(String[] args) {
            mainFrame myFrame = new mainFrame();
            myFrame.initialize();
        }
}
