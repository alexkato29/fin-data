import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by AlexKatopodis on 5/8/20.
 */
public class FXMLTest extends Application {
    private Database portfolioDatabase;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:./styles/xml/landing.fxml"));
        VBox vbox = loader.<VBox>load();

        portfolioDatabase = new Database("./data/2020-04-16_11-02.json");
        this.primaryStage = primaryStage;

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Portfolio Manager");
        primaryStage.show();
    }

    // Applies a Trade
    @FXML
    private void applyTrade(ActionEvent event) {
        event.consume();

        ArrayList<Trade> tradeList;

        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);

            tradeList = readData.tradeCsvParse(file.getAbsolutePath());

            for (Trade t: tradeList){
                portfolioDatabase.applyTrade(t);
            }


            showAlert("Database Update", "Database has been Updated");
            System.out.println("Database Updated");


        } catch (IOException e) {
            System.out.println("File Not Chosen");
        }
    }

    // Adds a New Portfolio
    @FXML
    public void addPortfolio(ActionEvent event) {
        Portfolio add;
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);

            add = readData.portfolioCsvParse(file.getAbsolutePath());
            portfolioDatabase.addPortfolio(add);
            showAlert("Add Portfolio", "Portfolio has been added");


        } catch (IOException e){
            System.out.println("File Not Chosen");
        }
    }

    public void showAlert(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
