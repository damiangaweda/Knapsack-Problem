package com.pz.App;

import com.pz.Models.ItemsPool;
import com.pz.Models.Knapsack;
import com.pz.Models.Population;
import com.pz.Services.GeneticAlgorithm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../JavaFX/Resources/main.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Knapsack problem app");
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.getProperty("user.dir");
        launch(args);

        //plecak = new GenethicAlgorithm().getAnswer(new Population(liczba osobników w populacji, maksymalna waga plecaka, szansa na mutacje, wybrany itemspool), ile ma % takich samych żeby się skończyło)
        Knapsack answer = new GeneticAlgorithm().getAnswer(new Population(50,20.0,1,new ItemsPool()),50);
        answer.getAdaptationScore(); // <--- score



    }
}
