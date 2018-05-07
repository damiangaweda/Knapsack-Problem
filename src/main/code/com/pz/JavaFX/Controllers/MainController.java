package com.pz.JavaFX.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML private Button startButton, itemDatabaseButton, exitButton;

    @FXML
    protected void startApplication() throws IOException {
        Parent prepareWorkplace = FXMLLoader.load(getClass().getResource("../Resources/settings.fxml"));

        Stage window = (Stage) startButton.getScene().getWindow();
        window.setScene(new Scene(prepareWorkplace));
        window.show();
    }

    @FXML
    protected void openItemDatabase() throws IOException {
        Parent itemDatabase = FXMLLoader.load(getClass().getResource("../Resources/itemManagement.fxml"));

        Stage window = (Stage) startButton.getScene().getWindow();
        window.setScene(new Scene(itemDatabase));
        window.show();
    }

    @FXML
    protected void exitApp() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
