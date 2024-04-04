package Runnables;

import Database.DB_Access;
import Database.DB_User;
import Exceptions.PasswordNotEqualException;
import Objects.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.InputMismatchException;

public class BudgetApplication extends Application {

    protected static User user = null;
    private boolean registerWindowOpen = false;

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements for the login form
        Text title = new Text("Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Text subTitle = new Text("");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);

        // Set action for the login button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // Check username and password
            Connection dbConnect = DB_Access.Connect();
            user = DB_User.login(dbConnect,username,password);

            //Invalid credentials entered
            if (user == null){
                subTitle.setText("Username or password is incorrect");
            }
            //Successful login
            else {
                primaryStage.close();
                openBudgetApplication();
            }
        });

        // Create UI elements for the registration button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            if (!registerWindowOpen)
                openRegistrationWindow();
        });

        // Create a grid pane layout for the login form
        GridPane loginGridPane = new GridPane();
        loginGridPane.setHgap(10);
        loginGridPane.setVgap(10);
        loginGridPane.setPadding(new Insets(20));

        // Add UI elements to the login grid pane
        loginGridPane.add(title, 0, 0, 2, 1);
        loginGridPane.add(usernameLabel, 0, 1);
        loginGridPane.add(usernameField, 1, 1);
        loginGridPane.add(passwordLabel, 0, 2);
        loginGridPane.add(passwordField, 1, 2);
        loginGridPane.add(loginButton, 1, 3);
        loginGridPane.add(subTitle, 1, 4, 2, 4);
        loginGridPane.add(registerButton, 2, 3);

        // Create the scene
        Scene scene = new Scene(loginGridPane, 240, 350);
        primaryStage.setMaxHeight(240);
        primaryStage.setMinHeight(240);
        primaryStage.setMaxWidth(350);
        primaryStage.setMinWidth(350);

        // Set the stage title and scene, then show the stage
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openBudgetApplication(){

        // Create a new stage for the window
        Stage budgetApplicationStage = new Stage();

        // Upon closing window
        budgetApplicationStage.setOnCloseRequest(e -> closeApplication());

        // Create UI elements

        // Create a grid pane layout

        // Add UI elements to the grid pane

        // Create the scene for the window

        // Set the stage title and scene, then show the stage

    }

    private void closeApplication() {
    }

    // Method to open the registration window
    private void openRegistrationWindow() {
        registerWindowOpen = true;

        // Create a new stage for the registration window
        Stage registrationStage = new Stage();

        // Upon closing registration window
        registrationStage.setOnCloseRequest(e -> registerWindowOpen = false);

        // Create UI elements for the registration form
        Text title = new Text("Registration");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label reenterPasswordLabel = new Label("Re-Enter Password:");
        PasswordField reenterPasswordField = new PasswordField();

        Label monthlyIncomeLabel = new Label("Monthly Income (Optional):");
        TextField monthlyIncomeField = new TextField();

        Label statusText = new Label("");

        Button registerButton = new Button("Register");
        registerButton.setDefaultButton(true);
        registerButton.setOnAction(e -> {
            // Process registration
            String username = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String reenterPassword = reenterPasswordField.getText();
            Double monthlyIncome;
            try {
                monthlyIncome = Double.parseDouble(monthlyIncomeField.getText());
            } catch (NumberFormatException ex) {
                monthlyIncome = 0.0;
            }
            System.out.println(username + email + password + reenterPassword);

            try {
                Connection dbConnection = DB_Access.Connect();
                if (DB_User.checkIfUserExists(dbConnection, username))
                    throw new InputMismatchException("Username is already taken");
                if (username.isBlank())
                    throw new InputMismatchException("Please enter a username");
                if (email.isBlank())
                    throw new InputMismatchException("Please enter a email address");
                if (password.isBlank())
                    throw new InputMismatchException("Please enter a password");
                if (reenterPassword.isBlank())
                    throw new InputMismatchException("Please re-enter password");
                if (!password.equals(reenterPassword))
                    throw new PasswordNotEqualException("Passwords do not match");
                if (monthlyIncome < 0)
                    throw new InputMismatchException("Monthly income must be positive");
                else {
                    statusText.setText("Creating Account...");
                    DB_User.addUser(dbConnection, username, password, email, monthlyIncome);

                    //Open account created window
                    openRegisterConfirmationWindow();

                    // Close the registration window
                    registrationStage.close();

                    registerWindowOpen = false;
                }

            }
            catch (Exception ex) {
                statusText.setText(ex.getMessage());
            }
        });

        // Create a grid pane layout for the registration form
        GridPane registrationGridPane = new GridPane();
        registrationGridPane.setHgap(10);
        registrationGridPane.setVgap(10);
        registrationGridPane.setPadding(new Insets(20));

        // Add UI elements to the registration grid pane
        registrationGridPane.add(title, 0, 0, 2, 1);
        registrationGridPane.add(nameLabel, 0, 1);
        registrationGridPane.add(nameField, 1, 1);
        registrationGridPane.add(emailLabel, 0, 2);
        registrationGridPane.add(emailField, 1, 2);
        registrationGridPane.add(passwordLabel, 0, 3);
        registrationGridPane.add(passwordField, 1, 3);
        registrationGridPane.add(reenterPasswordLabel, 0, 4);
        registrationGridPane.add(reenterPasswordField, 1, 4);
        registrationGridPane.add(monthlyIncomeLabel, 0, 5);
        registrationGridPane.add(monthlyIncomeField, 1, 5);
        registrationGridPane.add(registerButton, 1, 6);
        registrationGridPane.add(statusText, 0, 6);

        // Create the scene for the registration window
        Scene registrationScene = new Scene(registrationGridPane, 400, 400);
        registrationStage.setMaxHeight(400);
        registrationStage.setMinHeight(400);
        registrationStage.setMaxWidth(400);
        registrationStage.setMinWidth(400);

        // Set the stage title and scene, then show the stage
        registrationStage.setTitle("Registration");
        registrationStage.setScene(registrationScene);
        registrationStage.show();
    }

    private void openRegisterConfirmationWindow(){

        Stage registerConfirmationStage = new Stage();

        Label statusLabel = new Label("Account created successfully!");

        Button continueButton = new Button("Continue to login");
        continueButton.setOnAction(e -> {
            // Close the window when the continue button is clicked
            registerConfirmationStage.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(statusLabel, continueButton);

        Scene scene = new Scene(layout, 300, 150);
        registerConfirmationStage.setTitle("Account Created");
        registerConfirmationStage.setScene(scene);

        //this makes it so only this window can be interacted with
        registerConfirmationStage.initModality(Modality.APPLICATION_MODAL);
        registerConfirmationStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
