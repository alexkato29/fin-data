import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
    File defaultDirectory = new File(".\\data");

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


//        System.out.println(portfolioDatabase);

        Button optn1 = new Button("exportTrades");
        Button optn2 = new Button("exportPortfolio");

        Button dbBTN = new Button("Upload Trades to Database");
        Button downloadBtn = new Button("Download Portfolios");



        dbBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ArrayList<Trade> tradeList;

                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(defaultDirectory);
                    File file = fileChooser.showOpenDialog(primaryStage);



                    tradeList = readData.csvParse(file.getAbsolutePath());



                    for (Trade t: tradeList){

                        portfolioDatabase.applyTrade(t);
                    }

                    System.out.println("Database Updated");
//                    System.out.println(portfolioDatabase);


                } catch (IOException e){
                    System.out.println("File Not Chosen");
                }

                Map<String, Portfolio> portfolios = portfolioDatabase.getPortfolios();


                JSONObject data = new JSONObject();
                JSONArray array = new JSONArray();
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
                try (FileWriter file =  new FileWriter(defaultDirectory.getAbsoluteFile()+"\\"+fileName, true);) {
                    file.write(data.toJSONString());
                    file.flush();
                    showAlert("Database Update", fileName + " file has been database created.\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        optn1.setOnAction(new exportTrades(primaryStage, defaultDirectory));
        optn2.setOnAction(new exportPortfolio(primaryStage,defaultDirectory, portfolioDatabase.getPortfolios()));
        downloadBtn.setOnAction(new downloadTable(primaryStage, defaultDirectory, portfolioDatabase.getPortfolios()));


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(optn1, 0,0);
        grid.add(optn2, 1,0);

        grid.add(dbBTN, 2,0);

        grid.add(downloadBtn, 3, 0);

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
