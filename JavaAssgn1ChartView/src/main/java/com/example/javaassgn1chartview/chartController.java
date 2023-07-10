package com.example.javaassgn1chartview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Stage stage;
    private Scene scene;

    //Creating a method to change view
    public void switchToTableView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("tableView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Connecting to database
        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection connection = dbConnector.connect();


        XYChart.Series<String, Double> worldCup = new XYChart.Series<>();
        //reading items from database
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