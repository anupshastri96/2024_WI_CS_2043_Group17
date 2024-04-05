package Runnables;

/**
 * This class is a test for the chart system
 */

import Database.DB_Access;
import Database.DB_Category;
import Database.DB_implemention;
import Exceptions.DateFormatException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

public class ChartTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        yAxis.setLabel("Total Spent");
        xAxis.setLabel("Month");
        LocalDate startDate = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        LocalDate endDate;

        StackedBarChart<String, Number> chart =
                new StackedBarChart<String, Number>(xAxis, yAxis);

        LinkedList<Pair<LocalDate, Number>> list = null;

        Connection connection = DB_Access.Connect();

        //This is number of months to search as
        for (int i = 0; i < 12; i++) {

            startDate = startDate.minusMonths(1).withDayOfMonth(1);
            endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

            LinkedList<String> categories = DB_Category.getCategoriesInRange(connection, 2,startDate, endDate);

            for (String category : categories) {
                double sum = 0;
                try {
                    sum = DB_implemention.budgetSum(connection, 2, category, startDate, endDate, 'W');
                } catch (DateFormatException e) {
                    e.printStackTrace();
                }
                XYChart.Series<String, Number> series =
                        new XYChart.Series<>();
                series.setName(category);
                series.getData().add(new XYChart.Data<String, Number>(startDate.getMonth().toString(), sum));
                chart.getData().add(series);
            }
        }

        DB_Access.Closing(connection);

        Scene scene = new Scene(chart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

/**
    @Override
    public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        sbc.setTitle("Country Summary");
        xAxis.setLabel("Country");
        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(austria, brazil, france, italy, usa)));
        yAxis.setLabel("Value");
        series1.setName("2003");
        series1.getData().add(new XYChart.Data<String, Number>(austria, 25601.34));
        series1.getData().add(new XYChart.Data<String, Number>(brazil, 20148.82));
        series1.getData().add(new XYChart.Data<String, Number>(france, 10000));
        series1.getData().add(new XYChart.Data<String, Number>(italy, 35407.15));
        series1.getData().add(new XYChart.Data<String, Number>(usa, 12000));
        series2.setName("2004");
        series2.getData().add(new XYChart.Data<String, Number>(austria, 57401.85));
        series2.getData().add(new XYChart.Data<String, Number>(brazil, 41941.19));
        series2.getData().add(new XYChart.Data<String, Number>(france, 45263.37));
        series2.getData().add(new XYChart.Data<String, Number>(italy, 117320.16));
        series2.getData().add(new XYChart.Data<String, Number>(usa, 14845.27));
        series3.setName("2005");
        series3.getData().add(new XYChart.Data<String, Number>(austria, 45000.65));
        series3.getData().add(new XYChart.Data<String, Number>(brazil, 44835.76));
        series3.getData().add(new XYChart.Data<String, Number>(france, 18722.18));
        series3.getData().add(new XYChart.Data<String, Number>(italy, 17557.31));
        series3.getData().add(new XYChart.Data<String, Number>(usa, 92633.68));

        Label lineLabel = new Label("Budget");
        StackPane linePane = new StackPane();

        Scene scene = new Scene(sbc, 800, 600);
        sbc.getData().addAll(series1, series2, series3);
        Line line = new Line(0, 100, xAxis.getWidth(), 100);
        line.setStrokeWidth(2);
        linePane.getChildren().addAll(line);
        sbc.setPlotArea();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        yAxis.setLabel("Total Spent");
        xAxis.setLabel("Month");

        BarChart barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series month1 = new XYChart.Series();
        month1.setName("Sept");
        month1.getData().add(new XYChart.Data<>(" ", 375));
        XYChart.Series month2 = new XYChart.Series();
        month2.setName("Oct");
        month2.getData().add(new XYChart.Data<>(" ", 350));
        XYChart.Series month3 = new XYChart.Series();
        month3.setName("Nov");
        month3.getData().add(new XYChart.Data<>(" ", 400));
        XYChart.Series month4 = new XYChart.Series();
        month4.setName("Dec");
        month4.getData().add(new XYChart.Data<>(" ", 700));
        XYChart.Series month5 = new XYChart.Series();
        month5.setName("Jan");
        month5.getData().add(new XYChart.Data<>(" ", 550));
        XYChart.Series month6 = new XYChart.Series();
        month6.setName("Feb");
        month6.getData().add(new XYChart.Data<>(" ", 650));

        barChart.getData().addAll(month1, month2, month3, month4, month5, month6);

        StackPane pane = new StackPane(barChart);

        Scene scene = new Scene(pane, 595, 300);
        stage.setTitle("Bar Chart");
        stage.setScene(scene);
        stage.show();
    }
*/
}
