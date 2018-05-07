package com.pz.JavaFX.Controllers;

import com.pz.Models.Item;
import com.pz.Models.ItemsPool;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    //  FXML Interface
//  Buttons
    @FXML private Button addItemButton, removeItemButton;
    @FXML private Button calculateButton, resetButton, returnButton;
    //  Gen. Algorithm Settings
    @FXML private TextField populationSizeField;
    @FXML private Slider mutationSlider, targetIdenticalSlider;
    @FXML private TextField knapsackCapacityField;
    @FXML private Label mutationChanceLabel, targetIdenticalLabel;

    //  Selected items summary
    @FXML private Label totalCount, totalValue, totalWeight;

    //  Tables
    @FXML private TableView<Item> itemTable, selectedItemTable;
    @FXML private TableColumn<Item, String> name, sname;
    @FXML private TableColumn<Item, Double> value, svalue;
    @FXML private TableColumn<Item, Double> weight, sweight;

    // Java logic
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

//        mutationSlider.setValue(0);
//        mutationChanceLabel.textProperty().bind(new SimpleDoubleProperty(mutationSlider.getValue()).asString());
//        targetIdenticalLabel.textProperty().bind(new SimpleDoubleProperty(targetIdenticalSlider.getValue()).asString());
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

    @FXML protected void updateMutationLabel() {
        mutationChanceLabel.setText(Double.toString(mutationSlider.getValue()));
    }

    @FXML protected void returnToMainMenu() throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("../Resources/main.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(mainMenuParent));
        window.show();
    }
}
