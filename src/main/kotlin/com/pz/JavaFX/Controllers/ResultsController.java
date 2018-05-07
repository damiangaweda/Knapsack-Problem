package com.pz.JavaFX.Controllers;

import com.pz.Models.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

    @FXML private Button returnButton, showAllButton;
    @FXML private TextField scoreField;

    @FXML private TableView<Item> resultTable;
    @FXML private TableColumn<Item, String> name;
    @FXML private TableColumn<Item, Double> value;
    @FXML private TableColumn<Item, Double> weight;

    public static ObservableList<Item> itemList = FXCollections.observableArrayList();

    // Change this to population best score
    private Double bestScore = 115.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList.add(new Item("Item Name 1", 1.0, 1.0));
        itemList.add(new Item("Item Name 2", 2.0, 2.0));

        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        value.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));
        weight.setCellValueFactory(new PropertyValueFactory<Item, Double>("weight"));
        resultTable.setItems(itemList);

        scoreField.setText(bestScore.toString());
    }

    @FXML
    protected void showAll() {
        try {
            Parent allResults = FXMLLoader.load(getClass().getResource("../Resources/allResults.fxml"));

            Stage window = (Stage) showAllButton.getScene().getWindow();
            window.setScene(new Scene(allResults));
            window.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void close() {
        Stage window = (Stage) returnButton.getScene().getWindow();
        window.close();
    }
}
