import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.Port;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class exportPortfolio implements EventHandler<javafx.event.ActionEvent> {

    private Stage parentStage;
    private File defaultDirectory;
    private HashMap<String,Portfolio> portfolios;
    private String accountNum;

    public exportPortfolio(Stage parent, File defaultDirectory, HashMap<String, Portfolio> portfolios){
        this.defaultDirectory = defaultDirectory;
        this.parentStage = parent;
        this.portfolios = portfolios;
    }

    public exportPortfolio(Stage parent, File defaultDirectory, HashMap<String, Portfolio> portfolios, String accountNum){
        this.defaultDirectory = defaultDirectory;
        this.parentStage = parent;
        this.portfolios = portfolios;
        this.accountNum = accountNum;
    }

    public void handle(javafx.event.ActionEvent event)  {

        // TODO: Make two methods, one for all portfolios and one for an individual portfolio to be exported.



        try {

            for (Portfolio p : portfolios.values()){
                FileWriter csvWriter = new FileWriter(defaultDirectory.getAbsoluteFile()+"\\exportedPortfolios\\" + p.getAccountNum() +"_portfolio.csv");
                csvWriter.append("Ticker");
                csvWriter.append(",");
                csvWriter.append("Quantity");
                csvWriter.append("\n");

                Map<String, Security> securities = p.getSecurities();

                for (Map.Entry<String, Security> set : securities.entrySet()) {
                    Security s = set.getValue();
                    csvWriter.append(s.getTicker());
                    csvWriter.append(",");
                    csvWriter.append(Double.toString(s.getQuantity()));
                    csvWriter.append("\n");
                }



                csvWriter.flush();
                csvWriter.close();


            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Portfolios");
            alert.setHeaderText(null);
            alert.setContentText("Portfolios have been exported");
            alert.showAndWait();




        } catch (IOException e){
            System.out.println("File Not Chosen");
        }
    }

}
