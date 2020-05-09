import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by AlexKatopodis on 5/8/20.
 */

// THIS IS THE
public class FinanceApp extends Application {


    private Stage primaryStage;
    private File defaultDirectory = new File("./data");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Parent root = FXMLLoader.load(getClass().getResource("file:./styles/xml/landing.fxml"));
//        primaryStage.setTitle("Fin-Data Application");
//        primaryStage.setScene(new Scene);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:./styles/xml/landing.fxml"));

        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Portfolio Manager");
        primaryStage.show();


    }




}

