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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementController implements Initializable {
    @FXML private Button returnButton;
    @FXML private Button importButton, exportButton;
    @FXML private Button addItemButton, modifyItemButton, deleteItemButton;

    @FXML private TableView<Item> itemTable;
    @FXML private TableColumn<Item, String> name;
    @FXML private TableColumn<Item, Double> value;
    @FXML private TableColumn<Item, Double> weight;

    public static ObservableList<Item> itemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        itemList.add(new Item("itemName1", 10.0, 10.0));
        itemList.add(new Item("itemName2", 20.0, 20.0));
        itemList.add(new Item("itemName3", 30.0, 30.0));
        itemList.add(new Item("itemName4", 40.0, 40.0));
        itemList.add(new Item("itemName5", 50.0, 50.0));

        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        value.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));
        weight.setCellValueFactory(new PropertyValueFactory<Item, Double>("weight"));
        itemTable.setItems(itemList);
    }

    @FXML
    protected void importItems() {
        alertWindow("Error", "Feature not available yet!");
    }

    @FXML
    protected void exportItems() {
        alertWindow("Error", "Feature not available yet!");
    }

    @FXML
    protected void addItem() {
        popupWindow("Add new item");
    }

    @FXML
    protected void modifyItem () {
        Item selectedIndex = itemTable.getSelectionModel().getSelectedItem();
        int index = itemTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == null)
            alertWindow("Error", "You haven't selected anything");
        else {
            popupWindow("Modify existing item", selectedIndex.getName(), selectedIndex.getValue(), selectedIndex.getWeight(), index);
            //alertWindow("Error", "Feature not available yet!");
        }
    }

    @FXML
    protected void deleteItem () {
        Item selectedIndex = itemTable.getSelectionModel().getSelectedItem();
        if (selectedIndex == null)
            alertWindow("Error", "You haven't selected anything");
        else
            itemTable.getItems().remove(selectedIndex);
    }

    private void popupWindow(String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/itemWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void popupWindow(String title, String name, Double value, Double weight, int index) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/itemWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setResizable(false);

            ItemWindowController childWindow = fxmlLoader.<ItemWindowController>getController();
            childWindow.setFields(name, value, weight, index);

            stage.setScene(new Scene(root1));
            stage.show();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected static void alertWindow(String title, String description) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(description);

        alert.showAndWait();
    }

    @FXML
    protected void returnToMainMenu() throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("../Resources/main.fxml"));

        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(mainMenuParent));
        window.show();
    }
}
