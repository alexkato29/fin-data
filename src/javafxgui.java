import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

// Javafx.stage = Window
// Scene = stuff inside the window

public class javafxgui extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Portfolio Management");

        FileChooser fileChooser = new FileChooser();


        String database_file_path = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
        Database portfolioDatabase = new Database(database_file_path); // TODO: THIS COMMAND DOES NOT WORK YET
        System.out.println("Database has been uploaded");

        System.out.println(portfolioDatabase);

        Button optn1 = new Button("Scenario 1");
        Button optn2 = new Button("Scenario 2");
        Button optn3 = new Button("Scenario 3");
        Button optn4 = new Button("Scenario 4");


        optn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {

                ArrayList<Trade> tradeList;

                try {
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);

                    tradeList = readData.csvParse(file.getAbsolutePath());

                    for (Trade t: tradeList){
                        portfolioDatabase.applyTrade(t);
                    }

                    System.out.println("Database Updated");
                    System.out.println(portfolioDatabase);
//                    readData.jsonUpdate(database_file_path, tradeList);
                    // Above Line should be portfolioDatabase.update(tradeList)

                } catch (IOException e){
                    System.out.println("File Not Chosen");
                }
            }
        });
        optn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Trade> tradeList;

                try {
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);

                    tradeList = readData.csvParse(file.getAbsolutePath());



                } catch (IOException e){
                    System.out.println("File Not Chosen");
                }

            }
        });


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(optn1, 0,0);
        grid.add(optn2, 1,0);
        grid.add(optn3, 2,0);
        grid.add(optn4, 3, 0);

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 350, 150);
        primaryStage.setScene(scene);
        primaryStage.show();


//      At the close write the new database into a new JSON File

        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("Creating Database and exiting...");

//                        Map<String, Portfolio> portfolios = portfolioDatabase.getPortfolios();
//                        Map<String, Portfolio> port


//                        Iterator it = portfolios.entrySet().iterator();
//                        while (it.hasNext()) {
//                            Map.Entry pair = (Map.Entry)it.next();
//                            System.out.println(pair.getKey() + " = " + pair.getValue());
//                            it.remove(); // avoids a ConcurrentModificationException
////                        }
//
//
//
//
//                        System.exit(0);
                    }
                });
            }
        });

    }

}
