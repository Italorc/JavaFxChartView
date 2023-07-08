package com.example.javaassgn1chartview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        //add items
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
