package GUI;

import Database.DB_User;
import Objects.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinancialTrackerGUI extends JFrame {
    private JPasswordField passwordField;
    private JPanel loginScreen;
    private JPanel newUserScreen;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JButton newUserButton;
    private JLabel welcomeLabel;

    public FinancialTrackerGUI() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeLabel.setText("Signing in...");

                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                User user;
                user = User.login(username,password,DB_User.getUserList());
                if(user != null){
                    welcomeLabel.setText("Login Successful");
                }
                else{
                    welcomeLabel.setText("Username/Password incorrect");
                }
            }
        });
    }
    public static void main(String[] args){
        FinancialTrackerGUI gui = new FinancialTrackerGUI();;
        gui.setContentPane(gui.loginScreen);
        gui.setTitle("Financial Tracker");
        gui.setSize(300,200);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
