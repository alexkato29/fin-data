import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.applet.Main;
import sun.util.resources.cldr.ta.CurrencyNames_ta;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainController extends Application {
    private Database portfolioDatabase;
    private Stage primaryStage;

    // Buttons
    @FXML private Button applyTradeBtn;
    @FXML private Button addPortfolioBtn;
    @FXML private Button tableBtn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:./styles/xml/landing.fxml"));
        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Portfolio Manager");
        primaryStage.show();
    }




    @FXML
    public void initialize() {
        showAlert("Initialize Database", "Welcome! Please choose a database.");
        FileChooser fileChooser = new FileChooser();
        String database_file_path = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
        portfolioDatabase = new Database(database_file_path);
        showAlert("Database Update", "Database has been Uploaded");

        applyTradeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                applyTrade(primaryStage);
            }
        });
    }


    // Applies a Trade
    private void applyTrade(Stage primaryStage) {
        System.out.println(portfolioDatabase);
        System.out.println(primaryStage);

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
        System.out.println(primaryStage);
        System.out.println(portfolioDatabase);
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



    // Show a pop up alert
    public void showAlert(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
