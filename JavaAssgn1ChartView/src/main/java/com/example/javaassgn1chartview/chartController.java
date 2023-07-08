package com.example.javaassgn1chartview;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class chartController implements Initializable {
    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Connecting to database
        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection connection = dbConnector.connect();


        XYChart.Series<String, Double> worldCup = new XYChart.Series<>();

        try {
            Statement statement = ((Connection) connection).createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM goals_avg");

            while (resultSet.next()) {
                worldCup.getData().add(new XYChart.Data<>(resultSet.getString("cup_year"), resultSet.getDouble("goals_avg")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        barChart.getData().addAll(worldCup);
    }
}