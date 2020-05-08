import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Map;


public class javafxgui extends Application {

    Database portfolioDatabase;
//    private File defaultDirectory = new File("./files");
    private File  defaultDirectory = new File("./");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {





        primaryStage.setTitle("Portfolio Management");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(defaultDirectory);

        String database_file_path = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
        portfolioDatabase = new Database(database_file_path);


        showAlert("Database Update", "Database has been Uploaded");





        /*
        Download Related Buttons - Does not Change JSON Database
         */
        Button testBtn = new Button("Testing Directories");
        Button exportTradesBtn = new Button("Reformat Trades (StockRover)");
        Button exportPortfolioBtn = new Button("Reformat Portfolio ");
        Button downloadBtn = new Button("Download Portfolios");


        /*
        Upload Related Buttons - Does change the JSON Database
         */
        Button uploadTradesBtn = new Button("Upload Trades to Database");
        Button addPortfolioBtn = new Button("Add Portfolio");
        Button saveDatabaseBtn = new Button("Save Database");








//        Gets a trade log from the client and uploads it and updating the database

        testBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        uploadTradesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ArrayList<Trade> tradeList;

                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(defaultDirectory);
                    File file = fileChooser.showOpenDialog(primaryStage);

                    tradeList = readData.tradeCsvParse(file.getAbsolutePath());

                    for (Trade t: tradeList){
                        portfolioDatabase.applyTrade(t);
                    }


                    showAlert("Database Update", "Database has been Updated");
                    System.out.println("Database Updated");


                } catch (IOException e){
                    System.out.println("File Not Chosen");
                }




//                Move this to save when


            }
        });
        addPortfolioBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Portfolio add;
                try {
                    FileChooser fileChooser = new FileChooser();
//                    fileChooser.setInitialDirectory(defaultDirectory);
                    File file = fileChooser.showOpenDialog(primaryStage);

                    add = readData.portfolioCsvParse(file.getAbsolutePath());
                    portfolioDatabase.addPortfolio(add);
                    showAlert("Add Portfolio", "Portfolio has been added");


                } catch (IOException e){
                    System.out.println("File Not Chosen");
                }



            }
        });
        saveDatabaseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JSONObject data = new JSONObject();
                JSONArray array = new JSONArray();
                Map<String, Portfolio> portfolios = portfolioDatabase.getPortfolios();
                for (Portfolio p : portfolios.values()){
                    JSONObject jsonPortfolio = new JSONObject();
                    jsonPortfolio.put("accountNum", p.getAccountNum());
                    jsonPortfolio.put("accountHolder", p.getAccountHolder());
                    jsonPortfolio.put("isIndividual", p.isIndividual());
                    jsonPortfolio.put("portfolioValue", p.getPortfolioValue());


                    JSONArray securities = new JSONArray();
                    for(Security s : p.getSecurities().values()){
                        JSONObject jsonSecurity = new JSONObject();
                        jsonSecurity.put("ticker", s.getTicker());
                        jsonSecurity.put("quantity", s.getQuantity());
                        jsonSecurity.put("price", s.getPrice());
                        securities.add(jsonSecurity);
                    }
                    jsonPortfolio.put("securities", securities);


                    array.add(jsonPortfolio);
                }

                String fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm'.json'").format(new Date());
                data.put("portfolios", array);
                DirectoryChooser saveFolder = new DirectoryChooser();
                File directory = saveFolder.showDialog(primaryStage);

                try (FileWriter file =  new FileWriter(directory.getAbsoluteFile()+"\\"+fileName, true);) {
                    file.write(data.toJSONString());
                    file.flush();
                    showAlert("Database Update", fileName + " file has been database created.\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//        exportTradesBtn.setOnAction(new exportTrades(primaryStage, defaultDirectory));
//        exportPortfolioBtn.setOnAction(new exportPortfolio(primaryStage,defaultDirectory, portfolioDatabase.getPortfolios()));
        downloadBtn.setOnAction(new downloadTable(primaryStage, portfolioDatabase.getPortfolios()));


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

//        grid.add(exportTradesBtn, 0,0);
//        grid.add(exportPortfolioBtn, 1,0);
        grid.add(downloadBtn, 2, 0);
        grid.add(uploadTradesBtn, 0,1);
        grid.add(addPortfolioBtn, 1, 1);
        grid.add(saveDatabaseBtn, 2, 1);

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public void showAlert(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}