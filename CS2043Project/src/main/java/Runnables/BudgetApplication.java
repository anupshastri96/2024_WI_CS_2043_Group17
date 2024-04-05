package Runnables;

import Database.*;
import Exceptions.DateFormatException;
import Exceptions.PasswordNotEqualException;
import Objects.Category;
import Objects.Goal;
import Objects.Transaction;
import Objects.User;
import Verification.Verify;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.LinkedList;

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

        //Transaction Menu
        MenuItem addTransactionItem = new MenuItem("Add Transaction");
        addTransactionItem.setOnAction(e -> {addTransaction();});


        MenuItem updateTransactionItem = new MenuItem("Update Transaction");
        updateTransactionItem.setOnAction(e -> {editTransaction();});


        MenuItem deleteTransactionItem = new MenuItem("Delete Transaction");
        deleteTransactionItem.setOnAction(e ->{deleteTransaction();});

        Menu transactionsMenu = new Menu("Transactions");
        transactionsMenu.getItems().addAll(addTransactionItem, updateTransactionItem, deleteTransactionItem);

        //Category Menu
        MenuItem addCategoryItem = new MenuItem("Add Category");
        addCategoryItem.setOnAction(e -> addCategoryWindow());

        MenuItem updateCategoryItem = new MenuItem("Update Category");
        updateCategoryItem.setOnAction(e -> updateCategoryWindow());
        MenuItem deleteCategoryItem = new MenuItem("Delete Category");

        Menu categoriesMenu = new Menu("Categories");
        categoriesMenu.getItems().addAll(addCategoryItem, updateCategoryItem, deleteCategoryItem);

        //Goals Menu
        MenuItem addGoalItem = new MenuItem("Add Goal");
        addGoalItem.setOnAction(e -> {addGoal();});


        MenuItem updateGoalItem = new MenuItem("Update Goal");
        updateGoalItem.setOnAction(e -> {updateGoal();});

        MenuItem deleteGoalItem = new MenuItem("Delete Goal");
        deleteGoalItem.setOnAction(e -> {});

        MenuItem contributeGoal = new MenuItem("Contribute to Goal");
        contributeGoal.setOnAction(e -> {contributeToGoal();});

        Menu goalsMenu = new Menu("Goals");
        goalsMenu.getItems().addAll(addGoalItem, updateGoalItem, deleteGoalItem, contributeGoal);

        Menu statementMenu = new Menu("Statements");

        Menu dataMenu = new Menu("Data");

        Menu chartsMenu = new Menu("Charts");


        //Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(transactionsMenu,categoriesMenu,goalsMenu,statementMenu,dataMenu,chartsMenu);





        // Create UI elements
        BorderPane dashboard = new BorderPane();
        dashboard.setTop(menuBar);
        dashboard.setBottom(null);
        dashboard.setLeft(null);
        dashboard.setRight(null);
        dashboard.setCenter(null);

        Scene dashboardScene = new Scene(dashboard, 1280, 720);

        budgetApplicationStage.setScene(dashboardScene);
        budgetApplicationStage.setTitle("Budget Application");
        budgetApplicationStage.show();

    }

    private void closeApplication() {

    }

    private void addCategoryWindow(){

        Stage addCategoryStage = new Stage();

        Label nameLabel = new Label("Category Name:");
        TextField nameField = new TextField();

        Label budgetLabel = new Label("Budget Amount (Optional):");
        TextField budgetField = new TextField();

        Label statusText = new Label(" ");

        // Create buttons
        Button createButton = new Button("Create Category");
        Button cancelButton = new Button("Close");

        createButton.setOnAction(e -> {
            String name = nameField.getText();

            Double budget;
            try {
                budget = Double.parseDouble(budgetField.getText());
            } catch (NumberFormatException ex) {
                budget = 0.0;
            }

            try {
                Connection dbConnection = DB_Access.Connect();
                if (DB_Category.checkIfCategoryExists(dbConnection, user.getUserId(), name))
                    throw new InputMismatchException("Category already exists");
                if (name.isBlank())
                    throw new InputMismatchException("Please enter a category name");
                if (budget < 0)
                    throw new InputMismatchException("Monthly budget must be positive");
                else {
                    statusText.setText("Creating Category...");
                    DB_Category.addCategory(dbConnection, user.getUserId(), name, budget);

                    statusText.setText("Category added successfully");
                }
            }
            catch (InputMismatchException ex) {
                statusText.setText(ex.getMessage());
            }
        });

        cancelButton.setOnAction(e -> addCategoryStage.close());

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(createButton, cancelButton);

        GridPane fieldsPane = new GridPane();
        fieldsPane.setPadding(new Insets(10));
        fieldsPane.setVgap(10);
        fieldsPane.setHgap(10);
        fieldsPane.add(nameLabel, 0, 0);
        fieldsPane.add(nameField, 1, 0);
        fieldsPane.add(budgetLabel, 0, 1);
        fieldsPane.add(budgetField, 1, 1);

        VBox addCategoryWindow = new VBox(10);
        addCategoryWindow.getChildren().addAll(fieldsPane, buttonsBox);
        Scene addCategoryScene = new Scene(addCategoryWindow, 300, 150);

        addCategoryStage.setScene(addCategoryScene);
        addCategoryStage.setTitle("Add Category");
        addCategoryStage.initModality(Modality.APPLICATION_MODAL);
        addCategoryStage.show();

    }

    private void updateCategoryWindow(){
        Stage updateCategoryStage = new Stage();

        Label categoryNameLabel = new Label("Category Name:");
        TextField categoryNameField = new TextField();

        Label newCategoryNameLabel = new Label("New Category Name:");
        TextField newCategoryNameField = new TextField();

        Label newBudgetLabel = new Label("New Budget Amount:");
        TextField newBudgetField = new TextField();

        Label statusLabel = new Label();

        Button updateButton = new Button("Update");
        Button closeButton = new Button("Close");

        updateButton.setOnAction(e -> {
            String categoryName = categoryNameField.getText();
            String newCategoryName = newCategoryNameField.getText();
            Integer newBudget = Integer.parseInt(newCategoryNameField.getText());

            try {
                Connection dbConnection = DB_Access.Connect();
                if (!DB_Category.checkIfCategoryExists(dbConnection, user.getUserId(), categoryName))
                    throw new InputMismatchException("Category does not exist");
                if (categoryName.isBlank())
                    throw new InputMismatchException("Please enter a category name");
                if (newCategoryName.isBlank())
                    throw new InputMismatchException("Please enter a new category name");
                else {
                    statusLabel.setText("Creating Category...");
                    DB_Category.updateCategory(dbConnection, user.getUserId(), categoryName, newCategoryName, newBudget);

                    statusLabel.setText("Category added successfully");
                }
            }
            catch (InputMismatchException ex) {
                statusLabel.setText(ex.getMessage());
            }
            statusLabel.setText("Category name updated successfully");
        });

        // Set action for the close button
        closeButton.setOnAction(e -> updateCategoryStage.close());

        GridPane fieldsPane = new GridPane();
        fieldsPane.setPadding(new Insets(10));
        fieldsPane.setVgap(10);
        fieldsPane.setHgap(10);

        fieldsPane.add(categoryNameLabel, 0, 0);
        fieldsPane.add(categoryNameField, 1, 0);
        fieldsPane.add(newCategoryNameLabel, 0, 1);
        fieldsPane.add(newCategoryNameField, 1, 1);
        fieldsPane.add(newBudgetField, 1, 2);
        fieldsPane.add(newBudgetField, 1, 2);
        fieldsPane.add(statusLabel, 0, 3, 2, 3);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(updateButton, closeButton);

        VBox updateCategoryWindow = new VBox(10);
        updateCategoryWindow.getChildren().addAll(fieldsPane, buttonBox);
        Scene updateCategoryScene = new Scene(updateCategoryWindow, 300, 150);

        updateCategoryStage.setScene(updateCategoryScene);
        updateCategoryStage.setTitle("Update Category");
        updateCategoryStage.initModality(Modality.APPLICATION_MODAL);
        updateCategoryStage.show();

        Scene scene = new Scene(fieldsPane, 300, 200);
        updateCategoryStage.setScene(scene);
        updateCategoryStage.show();

    }

    private void deleteCategoryWindow(){
        Stage deleteCategoryStage = new Stage();

        Label categoryNameLabel = new Label("Category:");
        TextField categoryNameField = new TextField();

        Connection dbConnect = DB_Access.Connect();
        ComboBox<String> categoryComboBox = new ComboBox<>();
        LinkedList<Category> categories = DB_Category.getCategoryList(dbConnect, user.getUserId());
        for (Category category : categories) {
            categoryComboBox.getItems().add(category.getName());
        }


        Label statusLabel = new Label();

        Button deleteButton = new Button("Delete");
        Button closeButton = new Button("Close");

        deleteButton.setOnAction(e -> {
            String categoryName = categoryNameField.getText();

            try {
                Connection dbConnection = DB_Access.Connect();
                if (categoryComboBox.getValue().isBlank())
                    throw new InputMismatchException("Please select a category");
                if (!DB_Category.checkIfCategoryExists(dbConnection, user.getUserId(), categoryComboBox.getValue()))
                    throw new InputMismatchException("Category does not exist");
                else {
                    statusLabel.setText("Deleting Category...");
                    DB_Category.deleteCategory(dbConnection, user.getUserId(), categoryComboBox.getValue());

                    statusLabel.setText("Category deleted successfully");
                }
            }
            catch (InputMismatchException ex) {
                statusLabel.setText(ex.getMessage());
            }
            statusLabel.setText("Category name updated successfully");
        });

        // Set action for the close button
        closeButton.setOnAction(e -> deleteCategoryStage.close());

        GridPane fieldsPane = new GridPane();
        fieldsPane.setPadding(new Insets(10));
        fieldsPane.setVgap(10);
        fieldsPane.setHgap(10);

        fieldsPane.add(categoryNameLabel, 0, 0);
        fieldsPane.add(categoryComboBox, 1, 0);
        fieldsPane.add(deleteButton, 0, 1);
        fieldsPane.add(closeButton, 1, 1);

        fieldsPane.add(statusLabel, 0, 2, 1, 2);

        Scene addCategoryScene = new Scene(fieldsPane, 300, 150);

        deleteCategoryStage.setScene(addCategoryScene);
        deleteCategoryStage.setTitle("Delete Category");
        deleteCategoryStage.initModality(Modality.APPLICATION_MODAL);
        deleteCategoryStage.show();

        Scene scene = new Scene(fieldsPane, 300, 200);
        deleteCategoryStage.setScene(scene);
        deleteCategoryStage.show();
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

    public void addTransaction(){
        Stage transactionFormStage = new Stage();
        transactionFormStage.setTitle("Add new Transaction");

        // Create UI elements
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label typeLabel = new Label("Type:");
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("W", "D");// W for Withdrawal, D for Deposit

        Label amountLabel = new Label("Amount");
        TextField amountField = new TextField();

        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();

        Label categoryLabel = new Label("Category:");
        ComboBox<String> categoryComboBox = new ComboBox<>();
        Connection dbConnect = DB_Access.Connect();
        LinkedList<Category> categoryList = DB_Category.getCategoryList(dbConnect, user.getUserId());
        for(Category category : categoryList) {
            categoryComboBox.getItems().add(category.getName());
        }

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(b -> {
            double amount = Double.parseDouble(amountField.getText());
            DB_Transaction.addTransaction(dbConnect, user.getUserId(), nameField.getText(),
                    typeComboBox.getValue().charAt(0), amount, descriptionField.getText(), categoryComboBox.getValue());
            DB_Access.Closing(dbConnect);
            transactionFormStage.close();

        });
        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Add UI elements to the layout
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(typeLabel, 0, 1);
        gridPane.add(typeComboBox, 1, 1);
        gridPane.add(amountLabel, 0, 2);
        gridPane.add(amountField, 1, 2);
        gridPane.add(descriptionLabel, 0, 3);
        gridPane.add(descriptionField, 1, 3);
        gridPane.add(categoryLabel, 0, 4);
        gridPane.add(categoryComboBox, 1, 4);
        gridPane.add(submitButton, 1, 5);

        // Create the scene and show the stage
        Scene scene = new Scene(gridPane, 350, 300);
        transactionFormStage.setScene(scene);
        transactionFormStage.show();

    }
    public void editTransaction(){
        Stage updateTransactionForm = new Stage();
        updateTransactionForm.setTitle("Find Transaction to Update");
        Label transactionIdLabel = new Label("Transaction ID to update:");
        TextField transactionIdTextField = new TextField();
        Button findTransactionButton = new Button("Find Transaction");

        GridPane initialGridPane = new GridPane();
        initialGridPane.setHgap(10);
        initialGridPane.setVgap(10);
        initialGridPane.setPadding(new Insets(20));

        initialGridPane.add(transactionIdLabel, 0, 0);
        initialGridPane.add(transactionIdTextField, 1, 0);
        initialGridPane.add(findTransactionButton, 1, 1);

        Scene initialScene = new Scene(initialGridPane, 350, 150);
        updateTransactionForm.setScene(initialScene);
        updateTransactionForm.show();

        findTransactionButton.setOnAction(b -> {
            Connection dbConnect = DB_Access.Connect();
            int transactionId = 0;
            try {
                transactionId = Integer.parseInt(transactionIdTextField.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Transaction ID must be an integer.");
                return;
            }

            Transaction transaction = DB_Transaction.getTransactionById(dbConnect, transactionId);
            if (transaction == null) {
                System.out.println("No transaction found with ID: " + transactionId);
                return;
            }
            updateTransactionForm.close(); // Close the find transaction window if transaction is found

            // Create the second stage for updating the transaction
            Stage updateTransactionStage = new Stage();
            updateTransactionStage.setTitle("Update Transaction");

            // UI components for updating the transaction
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20));

            // Assuming Transaction class has appropriate getters
            TextField nameField = new TextField(transaction.getName());
            ComboBox<String> typeComboBox = new ComboBox<>();
            typeComboBox.getItems().addAll("W", "D");
            typeComboBox.setValue(String.valueOf(transaction.getType()));
            TextField amountField = new TextField(String.valueOf(transaction.getAmount()));
            TextField descriptionField = new TextField(transaction.getDescription());
            ComboBox<String> categoryComboBox = new ComboBox<>();
            LinkedList<Category> categories = DB_Category.getCategoryList(dbConnect, user.getUserId()); // Assuming '1' is the user ID
            for (Category category : categories) {
                categoryComboBox.getItems().add(category.getName());
            }
            categoryComboBox.setValue(transaction.getCategory().getName());
            Button updateButton = new Button("Update");

            // Adding UI components to GridPane
            gridPane.add(new Label("Name:"), 0, 0);
            gridPane.add(nameField, 1, 0);
            gridPane.add(new Label("Type:"), 0, 1);
            gridPane.add(typeComboBox, 1, 1);
            gridPane.add(new Label("Amount:"), 0, 2);
            gridPane.add(amountField, 1, 2);
            gridPane.add(new Label("Description:"), 0, 3);
            gridPane.add(descriptionField, 1, 3);
            gridPane.add(new Label("Category:"), 0, 4);
            gridPane.add(categoryComboBox, 1, 4);
            gridPane.add(updateButton, 1, 5);

            Scene scene = new Scene(gridPane, 400, 300);
            updateTransactionStage.setScene(scene);
            updateTransactionStage.show();

            int finalTransactionId = transactionId;
            updateButton.setOnAction(updateEvent -> {
                try {
                    // Attempt to parse amount field to double
                    double amount = Double.parseDouble(amountField.getText());
                    // Assume method exists to update transaction
                    DB_Transaction.updateTransaction(dbConnect, finalTransactionId, new java.sql.Date(new java.util.Date().getTime()), nameField.getText(), typeComboBox.getValue().charAt(0), amount, descriptionField.getText(), categoryComboBox.getValue());
                    updateTransactionStage.close(); // Close the update window on successful update
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid amount.");
                }
            });
        });
    }

    public void deleteTransaction(){
        Stage deleteTransactionStage = new Stage();
        deleteTransactionStage.setTitle("Delete Transaction");

        // UI components for deleting a transaction
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        Label transactionIdLabel = new Label("Transaction ID to delete:");
        TextField transactionIdTextField = new TextField();
        Button deleteButton = new Button("Delete");
        Label statusLabel = new Label();

        // Adding UI components to GridPane
        gridPane.add(transactionIdLabel, 0, 0);
        gridPane.add(transactionIdTextField, 1, 0);
        gridPane.add(deleteButton, 1, 1);
        gridPane.add(statusLabel, 1, 2);

        Scene scene = new Scene(gridPane, 300, 120);
        deleteTransactionStage.setScene(scene);
        deleteTransactionStage.show();

        deleteButton.setOnAction(deleteEvent -> {
            Connection dbConnect = DB_Access.Connect();
            int transactionId = Integer.parseInt(transactionIdTextField.getText());
            boolean found = DB_Transaction.checkIfTrancactionExists(dbConnect, user.getUserId(), transactionId);
            if(found){
                // Call method to delete the transaction from the database
                DB_Transaction.deleteTransaction(dbConnect, transactionId);
                statusLabel.setText("Transaction deleted successfully.");
            }
            else{
                statusLabel.setText("Please enter a valid transaction ID.");

            }

        });
        deleteTransactionStage.close();
    }
    public void addGoal(){
        Stage addGoalStage = new Stage();
        addGoalStage.setTitle("Add New Goal");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        Label nameLabel = new Label("Goal Name:");
        TextField nameField = new TextField();

        Label targetAmountLabel = new Label("Target Amount:");
        TextField targetAmountField = new TextField();

        Label dateLabel = new Label("Date (YYYY-MM-DD):");
        TextField dateField = new TextField();

        Button addButton = new Button("Add Goal");
        Label statusLabel = new Label();

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(targetAmountLabel, 0, 1);
        gridPane.add(targetAmountField, 1, 1);
        gridPane.add(dateLabel, 0, 2);
        gridPane.add(dateField, 1, 2);
        gridPane.add(addButton, 1, 3);
        gridPane.add(statusLabel, 0, 4, 2, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        addGoalStage.setScene(scene);
        addGoalStage.show();

        addButton.setOnAction(addEvent -> {
            Connection dbConnect = DB_Access.Connect();
            try {
                String name = nameField.getText().trim();
                double totalAmount = Double.parseDouble(targetAmountField.getText().trim());
                String date = dateField.getText().trim();

                if (name.isEmpty() || date.isEmpty()) {
                    statusLabel.setText("Goal name and date cannot be empty.");
                    return;
                }

                DB_Goal.addGoal(dbConnect, user.getUserId(), name, totalAmount, date);
                statusLabel.setText("Goal added successfully.");

            } catch (NumberFormatException ex) {
                statusLabel.setText("Please enter a valid target amount.");
            } catch (Exception ex) {
                statusLabel.setText("Failed to add the goal.");
            }
            finally {
                DB_Access.Closing(dbConnect);
                addGoalStage.close();
            }
        });
    }
    public void updateGoal(){
        Connection dbConnect = DB_Access.Connect();
        Stage updateGoalStage = new Stage();
        updateGoalStage.setTitle("Update Goal");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        ComboBox<String> goalsComboBox = new ComboBox<>();
        LinkedList<Goal> goals = DB_Goal.getGoalList(dbConnect, user.getUserId());
        for (Goal goal : goals) {
            goalsComboBox.getItems().add(goal.getGoalName());
        }
    }
    public void contributeToGoal(){
        Connection dbConnect = DB_Access.Connect();
        Stage contributeStage = new Stage();
        contributeStage.setTitle("Contribute to Goal");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        ComboBox<String> goalsComboBox = new ComboBox<>();
        LinkedList<Goal> goals = DB_Goal.getGoalList(dbConnect, user.getUserId());
        for (Goal goal : goals) {
            goalsComboBox.getItems().add(goal.getGoalName());
        }

        Label contributionLabel = new Label("Contribution Amount:");
        TextField contributionField = new TextField();
        Button contributeButton = new Button("Contribute");
        Label statusLabel = new Label();


        gridPane.add(new Label("Select Goal:"), 0, 0);
        gridPane.add(goalsComboBox, 1, 0);
        gridPane.add(contributionLabel, 0, 1);
        gridPane.add(contributionField, 1, 1);
        gridPane.add(contributeButton, 1, 2);
        gridPane.add(statusLabel, 0, 3, 2, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        contributeStage.setScene(scene);
        contributeStage.show();

        contributeButton.setOnAction(contributeEvent -> {
            if (goalsComboBox.getValue() == null || contributionField.getText().isEmpty()) {
                statusLabel.setText("Please select a goal and enter an amount.");
                return;
            }

            try {
                double amount = Double.parseDouble(contributionField.getText());
                DB_Goal.contributeToGoal(dbConnect, user.getUserId(), goalsComboBox.getValue(), amount);
                statusLabel.setText("Contribution made successfully.");
            }
            catch (NumberFormatException ex) {
                statusLabel.setText("Please enter a valid amount.");
            }
            catch (Exception ex) {
                statusLabel.setText("Error contributing to goal.");
            }
            finally {
                DB_Access.Closing(dbConnect);
                contributeStage.close();
            }
        });
    }
}
