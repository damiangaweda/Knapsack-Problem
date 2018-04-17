package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class allResultsController implements Initializable {
    @FXML
    private Button returnButton;
    /*
    @FXML private TableView<Population> resultTable;
    @FXML private TableColumn<Population, String> population;
    @FXML private TableColumn<Population, Double> value;
    @FXML private TableColumn<Population, Double> weight;

    public static ObservableList<populationList> populationList = FXCollections.observableArrayList();
    */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        // load populations into the list
        // like this:
        //populationList.add(new Population("Population #1", 1.0, 1.0));

        population.setCellValueFactory(new PropertyValueFactory<Population, String>("population"));
        value.setCellValueFactory(new PropertyValueFactory<Population, Double>("value"));
        weight.setCellValueFactory(new PropertyValueFactory<Population, Double>("weight"));
        resultTable.setItems(populationList);
        */
    }

    @FXML
    protected void close() {
        try {
            Parent bestResult = FXMLLoader.load(getClass().getResource("../Resources/results.fxml"));

            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(bestResult));
            window.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
}
