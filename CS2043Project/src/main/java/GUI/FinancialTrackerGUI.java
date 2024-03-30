package GUI;

import Database.DB_User;
import Objects.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinancialTrackerGUI extends JFrame {


    //Login Panel
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton newUserButton;
    private JLabel welcomeLabel;

    // newUser panel
    private JTextField emailField;

    //Dashboard components.
    private JLabel balance;
    private JButton transaction;
    private JButton editProfile;
    private JButton viewBudget;

    //PANELS
    private JPanel login;
    private JPanel newUser;
    private JPanel dashBoard;
    private JPanel currentPanel;

    public FinancialTrackerGUI() {
        // Initialize panels
        initializePanels();

        // Set initial panel
        switchPanel(login); // This will set login as the initial panel

        // Setting JFrame properties
        setTitle("Financial Tracker");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeLabel.setText("Signing in...");

                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                User user = User.login(username, password, DB_User.getUserList());
                if (user != null) {
                    welcomeLabel.setText("Login Successful");
                    switchPanel(dashBoard); // Switch to dashboard on successful login
                } else {
                    welcomeLabel.setText("Username/Password incorrect");
                }
            }
        });
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(newUser);
            }
        });
    }

    private void initializePanels() {
        // Initialize your panels here with their components
        login = new JPanel(new FlowLayout());


        // Username Panel
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField(20);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        // Password Panel
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);


        // Adding components to the login panel
        welcomeLabel = new JLabel("Welcome!");
        loginButton = new JButton("Login");
        newUserButton = new JButton("New User");

        login.add(welcomeLabel);
        login.add(usernamePanel);
        login.add(passwordPanel);
        login.add(newUserButton);
        login.add(loginButton);

        // Initialize the newUser panel with GridBagLayout for precise alignment
        newUser = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.NORTH; // End row after each component
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components fill their row
        gbc.insets = new Insets(5, 5, 5, 5); // External padding of components

        // Username Label and TextField
        usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField(20);
        gbc.gridx = 0; // Column 0
        newUser.add(usernameLabel, gbc);
        gbc.gridx = 1; // Column 1
        newUser.add(usernameTextField, gbc);

        // Password Label and PasswordField
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        gbc.gridx = 0; // Reset to column 0
        newUser.add(passwordLabel, gbc);
        gbc.gridx = 1; // Column 1
        newUser.add(passwordField, gbc);

        // Email Label and TextField
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        gbc.gridx = 0; // Reset to column 0
        newUser.add(emailLabel, gbc);
        gbc.gridx = 1; // Column 1
        newUser.add(emailField, gbc);

        dashBoard = new JPanel(new FlowLayout());
        // Initialize the dashboard panel with BorderLayout
        dashBoard = new JPanel(new BorderLayout(10, 10)); // Add some padding between components

        // Top Panel for balance
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        balance = new JLabel("Balance: $99999.99");
        balance.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        topPanel.add(balance);

        // Center Panel for buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Add some padding between buttons
        transaction = new JButton("Add Transaction");
        editProfile = new JButton("Edit Profile");
        viewBudget = new JButton("View Budgets");
        centerPanel.add(transaction);
        centerPanel.add(editProfile);
        centerPanel.add(viewBudget);

        dashBoard.add(topPanel, BorderLayout.NORTH);
        dashBoard.add(centerPanel, BorderLayout.CENTER);

    }

    public void switchPanel(JPanel newPanel) {
        if (currentPanel != null) {
            getContentPane().remove(currentPanel); // Remove the current panel
        }
        currentPanel = newPanel; // Update the current panel reference
        getContentPane().add(currentPanel, BorderLayout.CENTER); // Add the new panel, ensure layout is considered
        getContentPane().validate(); // Revalidate the content pane
        getContentPane().repaint(); // Repaint the content pane
    }

    public static void main(String[] args) {
        // The GUI is now initialized within its constructor
        new FinancialTrackerGUI();
    }
}
