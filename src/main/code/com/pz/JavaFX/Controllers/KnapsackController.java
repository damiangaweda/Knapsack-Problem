package com.pz.JavaFX.Controllers;

import com.pz.Models.Item;
import com.pz.Models.ItemsPool;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KnapsackController implements Initializable {

    private Double knapsackSize;

    @FXML private Button addItemButton, removeItemButton;
    @FXML private Button calculateButton, resetButton, returnButton;
    @FXML private TextField knapsackCapacityField;

    @FXML private TableView<Item> itemTable, selectedItemTable;
    @FXML private TableColumn<Item, String> name, sname;
    @FXML private TableColumn<Item, Double> value, svalue;
    @FXML private TableColumn<Item, Double> weight, sweight;

    private ObservableList<Item> items;
    private ObservableList<Item> selectedItems;
    private ItemsPool selectedItemsPool = new ItemsPool();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = ItemManagementController.itemList;
        itemTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        value.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));
        weight.setCellValueFactory(new PropertyValueFactory<Item, Double>("weight"));
        sname.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        svalue.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));
        sweight.setCellValueFactory(new PropertyValueFactory<Item, Double>("weight"));
        itemTable.setItems(items);

        knapsackSize = 20.0;
        knapsackCapacityField.setText("20");
    }

    @FXML protected void addItem() {
        selectedItems = selectedItemTable.getItems();
        ObservableList<Item> selectedItems = itemTable.getSelectionModel().getSelectedItems();
        this.selectedItems.addAll(selectedItems);
        selectedItemsPool.addItems(selectedItems);
    }

    @FXML protected void removeItem() {
        selectedItems = selectedItemTable.getSelectionModel().getSelectedItems();
        for (Item selected : selectedItems) {
            selectedItemTable.getItems().remove(selected);
            selectedItemsPool.removeItem(selected);
        }
    }

    @FXML protected void reset(){
        selectedItems = selectedItemTable.getItems();
        selectedItems.clear();
        selectedItemsPool.clearItems();

        itemTable.getSelectionModel().clearSelection();
    }

    @FXML protected void calculate() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/results.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Knapsack problem app - Results");
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void returnToMainMenu() throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("../Resources/main.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(mainMenuParent));
        window.show();
    }
}
