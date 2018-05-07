package com.pz.JavaFX.Controllers;

import com.pz.Models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ItemWindowController {

    @FXML private Button addItemButton, cancelButton;
    @FXML private TextField nameField, valueField, weightField;
    @FXML private Label nameError, valueError, weightError;
    private int index;

    @FXML
    protected void addItem() {

        Item item;

        nameError.setText("");
        valueError.setText("");
        weightError.setText("");
        if (!validateItemName() || !validateItemValue() || !validateItemWeight()) {}
        else {
            Stage window = (Stage) addItemButton.getScene().getWindow();
            if (window.getTitle().equals("Add new item")) {
                item = new Item(nameField.getText(), Double.parseDouble(valueField.getText()), Double.parseDouble(weightField.getText()));
                ItemManagementController.itemList.add(item);
                ItemManagementController.itemsPool.addItem(item);
            }
            else{
                item = new Item(nameField.getText(), Double.parseDouble(valueField.getText()), Double.parseDouble(weightField.getText()));
                ItemManagementController.itemList.set(index, item);
                ItemManagementController.itemsPool.setItemAt(index,item);
            }

            window.close();
        }
    }

    public void setFields(String name, Double value, Double weight, int index) {
        this.nameField.setText(name);
        this.valueField.setText(value.toString());
        this.weightField.setText(weight.toString());
        this.index = index;
    }

    private Boolean validateItemName() {
        if (nameField.getText().equals("")) {
            nameError.setText("Failed! Enter an item name!");
            return false;
        }
        if (nameField.getText().length() < 3) {
            nameError.setText("Failed! Enter at least 3 characters!");
            return false;
        }
        if (!nameField.getText().matches("([A-z])\\w+")) {
            nameError.setText("Failed! Use letters only!");
            return false;
        }
        return true;
    }

    private Boolean validateItemValue() {
        if (valueField.getText().equals("")) {
            valueError.setText("Failed! Enter an item value!");
            return false;
        }
        if (valueField.getText().length() < 1) {
            valueError.setText("Failed! Enter at least 1 number!");
            return false;
        }
        if (!valueField.getText().matches("([.0-9])*\\d")) {
            valueError.setText("Failed! Use numbers only!");
            return false;
        }
        return true;
    }

    private Boolean validateItemWeight() {
        if (weightField.getText().equals("")) {
            weightError.setText("Failed! Enter an item weight!");
            return false;
        }
        if (weightField.getText().length() < 1) {
            weightError.setText("Failed! Enter at least 1 number!");
            return false;
        }
        if (!weightField.getText().matches("([.0-9])*\\d")) {
            weightError.setText("Failed! Use numbers only!");
            return false;
        }
        return true;
    }

    @FXML
    protected void cancel() {
        Stage window = (Stage) cancelButton.getScene().getWindow();
        window.close();
    }
}
