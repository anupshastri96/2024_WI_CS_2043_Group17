import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExpenseTrackerGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Expense Tracker");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels
        Label dateLabel = new Label("Date:");
        GridPane.setConstraints(dateLabel, 0, 0);

        Label valueLabel = new Label("Value:");
        GridPane.setConstraints(valueLabel, 0, 1);

        Label descriptionLabel = new Label("Description:");
        GridPane.setConstraints(descriptionLabel, 0, 2);

        Label tagLabel = new Label("Tag:");
        GridPane.setConstraints(tagLabel, 0, 3);

        // Text Fields
        TextField dateInput = new TextField();
        dateInput.setPromptText("YYYY-MM-DD");
        GridPane.setConstraints(dateInput, 1, 0);

        TextField valueInput = new TextField();
        GridPane.setConstraints(valueInput, 1, 1);

        TextField descriptionInput = new TextField();
        GridPane.setConstraints(descriptionInput, 1, 2);

        TextField tagInput = new TextField();
        GridPane.setConstraints(tagInput, 1, 3);

        // Buttons
        Button addButton = new Button("Add Transaction");
        GridPane.setConstraints(addButton, 1, 4);

        Button removeButton = new Button("Remove Transaction");
        GridPane.setConstraints(removeButton, 1, 5);

        Button editButton = new Button("Edit Transaction");
        GridPane.setConstraints(editButton, 1, 6);

        Button tagButton = new Button("Tag Transaction");
        GridPane.setConstraints(tagButton, 1, 7);

        Button setGoalButton = new Button("Set Goal");
        GridPane.setConstraints(setGoalButton, 0, 8);

        Button setReminderButton = new Button("Set Reminder");
        GridPane.setConstraints(setReminderButton, 0, 9);

        Button generateReportButton = new Button("Generate Report");
        GridPane.setConstraints(generateReportButton, 0, 10);

        // Statistics Labels
        Label totalExpensesLabel = new Label("Total Expenses: $0.00");
        GridPane.setConstraints(totalExpensesLabel, 0, 11);

        Label totalIncomeLabel = new Label("Total Income: $0.00");
        GridPane.setConstraints(totalIncomeLabel, 0, 12);

        Label avgIncomeLabel = new Label("Average Income: $0.00");
        GridPane.setConstraints(avgIncomeLabel, 0, 13);

        Label avgIncomeChangeLabel = new Label("Rate of Change of Average Income: $0.00");
        GridPane.setConstraints(avgIncomeChangeLabel, 0, 14);

        grid.getChildren().addAll(dateLabel, valueLabel, descriptionLabel, tagLabel,
                dateInput, valueInput, descriptionInput, tagInput, addButton, removeButton, editButton, tagButton,
                setGoalButton, setReminderButton, generateReportButton,
                totalExpensesLabel, totalIncomeLabel, avgIncomeLabel, avgIncomeChangeLabel);

        Scene scene = new Scene(grid, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
