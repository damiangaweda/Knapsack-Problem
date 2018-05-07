package com.pz.JavaFX.Controllers;

import com.pz.Models.Item;
import com.pz.Models.ItemsPool;
import com.pz.Models.Knapsack;
import com.pz.Models.Population;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import com.pz.Services.GeneticAlgorithm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.pz.JavaFX.Controllers.ItemManagementController.alertWindow;

public class KnapsackController implements Initializable {
    //  FXML Interface
//  Buttons
    @FXML private Button addItemButton, removeItemButton;
    @FXML private Button calculateButton, resetButton, returnButton;
    //  Gen. Algorithm Settings
    @FXML private TextField populationSizeField;
    @FXML private Slider mutationSlider, targetIdenticalSlider;
    @FXML private TextField knapsackCapacityField;
    @FXML private Label mutationChanceLabel, TargetIdenticalLabel;

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
    public static ItemsPool selectedItemsPool = new ItemsPool();

    private Integer populationSize, mutationChance, targetIdentical;
    private Double knapsackSize;

    public static Knapsack result;

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

        mutationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                mutationChanceLabel.setText(String.format("%d", new_val.intValue()) + "%");
                mutationChance = new_val.intValue();
            }
        });
        targetIdenticalSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                TargetIdenticalLabel.setText(String.format("%d", new_val.intValue()) + "%");
                targetIdentical = new_val.intValue();
            }
        });
        populationSizeField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov,
                                String old_val, String new_val) {
                if (new_val.isEmpty())  return;
                if (!new_val.matches("^[0-9]*$")) {
                    populationSizeField.setStyle("-fx-text-fill: red;");
                    populationSize = 0;
                    return;
                }
                populationSizeField.setStyle("-fx-text-fill: black;");
                populationSize = Integer.valueOf(new_val);
            }
        });
        knapsackCapacityField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov,
                                String old_val, String new_val) {
                if (new_val.isEmpty())  return;
                if (!new_val.matches("^[0-9]*$")) {
                    knapsackCapacityField.setStyle("-fx-text-fill: red;");
                    knapsackSize = 0.0;
                    return;
                }
                knapsackCapacityField.setStyle("-fx-text-fill: black;");
                knapsackSize = Double.valueOf(new_val);
            }
        });
    }

    @FXML protected void addItem() {
        selectedItems = selectedItemTable.getItems();
        ObservableList<Item> selectedItems = itemTable.getSelectionModel().getSelectedItems();
        this.selectedItems.addAll(selectedItems);
        selectedItemsPool.addItems(selectedItems);

        totalCount.setText("Total count: " + Integer.toString(selectedItemsPool.getCount()));
        totalValue.setText("Total value: " + String.format("%.2f", selectedItemsPool.getValue()));
        totalWeight.setText("Total weight: " + String.format("%.2f", selectedItemsPool.getWeight()));
    }

    @FXML protected void removeItem() {
        selectedItems = selectedItemTable.getSelectionModel().getSelectedItems();
        for (Item selected : selectedItems) {
            selectedItemTable.getItems().remove(selected);
            selectedItemsPool.removeItem(selected);

            totalCount.setText("Total count: " + Integer.toString(selectedItemsPool.getCount()));
            totalValue.setText("Total value: " + String.format("%.2f", selectedItemsPool.getValue()));
            totalWeight.setText("Total weight: " + String.format("%.2f", selectedItemsPool.getWeight()));
        }
    }

    @FXML protected void reset(){
        selectedItems = selectedItemTable.getItems();
        selectedItems.clear();
        selectedItemsPool.clearItems();

        totalCount.setText("Total count: 0");
        totalValue.setText("Total value: 0");
        totalWeight.setText("Total weight: 0");

        itemTable.getSelectionModel().clearSelection();
    }

    @FXML protected void calculate() {
        if (selectedItemsPool.getPoolSize() <= 0 || populationSize <= 0 || knapsackSize <= 0) {
            alertWindow("Error", "Please fill out all fields");
            return;
        }

        result = new GeneticAlgorithm().getAnswer(new Population(populationSize, knapsackSize, mutationChance, selectedItemsPool), targetIdentical);
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
