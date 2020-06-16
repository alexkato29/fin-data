import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by AlexKatopodis on 5/8/20.
 */

// THIS IS THE
public class FinanceApp extends Application {


    private static Database portfolioDatabase;
    private static Stage primaryStage;
    private File defaultDirectory = new File("./data");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {


        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        Map<String, Portfolio> portfolios = portfolioDatabase.getPortfolios();
        for (Portfolio p : portfolios.values()){
            JSONObject jsonPortfolio = new JSONObject();
            jsonPortfolio.put("accountNum", p.getAccountNum());
            jsonPortfolio.put("accountHolder", p.getAccountHolder());
            jsonPortfolio.put("isIndividual", p.isIndividual());
            jsonPortfolio.put("portfolioValue", p.getPortfolioValue());
            jsonPortfolio.put("lastUpdated", p.getLastUpdated().toString());


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


        try (FileWriter file =  new FileWriter("./data/"+fileName, true);) {
            file.write(data.toJSONString());
            file.flush();
            readData.showAlert("Database Update", fileName + " file has been database created.\n");
        } catch (IOException e) {
            readData.showAlert("Error", "Check Console for Error");
            e.printStackTrace();
        }


        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try{
            readData.showAlert("Database Chooser", "Please choose the most recent or desired database.");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(defaultDirectory);
            File dbFile = fileChooser.showOpenDialog(primaryStage);
            portfolioDatabase = new Database(dbFile.getAbsolutePath());
            readData.showAlert("Proceeding...", "Database has been uploaded.");


        } catch(Exception e){
            readData.showAlert("Error", "Check Console for Error");
            e.printStackTrace();
        }

        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:./styles/xml/landing.fxml"));

        VBox vbox = loader.<VBox>load();

        // readData.showAlert("Test", primaryStage.toString());

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Portfolio Manager");
        primaryStage.show();
    }

    public static Database getPortfolioDatabase() {
        return portfolioDatabase;
    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
}



