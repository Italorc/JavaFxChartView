package com.example.javaassgn1chartview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class tableController implements Initializable {

    @FXML
    private TableView<WorldCup> goalsAvgTable;
    @FXML
    private TableColumn<WorldCup, String> tcEdition;
    @FXML
    private TableColumn<WorldCup, Double> tcGoalsAvg;
    private  WorldCup[] WORLDCUPEDITIONS = {};

    private final ObservableList<WorldCup> worldCupList = FXCollections.observableArrayList(WORLDCUPEDITIONS);

    private Stage stage;
    private Scene scene;

    //Creating a method to change view
    public void switchToChartView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("chartView.fxml"));
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
        //display data on the table column cell
       tcEdition.setCellValueFactory(new PropertyValueFactory<>("edition"));
       tcGoalsAvg.setCellValueFactory(new PropertyValueFactory<>("goalsAvg"));
        //display row data
        goalsAvgTable.setItems(worldCupList);
        //reading items from database
        try {
            Statement statement = ((Connection) connection).createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM goals_avg");

            while (resultSet.next()) {
                worldCupList.add(new WorldCup(resultSet.getString("cup_year"), resultSet.getDouble("goals_avg")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
