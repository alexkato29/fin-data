import javafx.event.Event;
import javafx.event.EventHandler;
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

public class scenario2 implements EventHandler<javafx.event.ActionEvent> {

    private Stage parentStage;
    private File defaultDirectory;
    private HashMap<String,Portfolio> portfolios;

    public scenario2(Stage parent, File defaultDirectory, HashMap<String,Portfolio> portfolios){
        this.defaultDirectory = defaultDirectory;
        this.parentStage = parent;
        this.portfolios = portfolios;
    }

    public void handle(javafx.event.ActionEvent event)  {



        try {

            for (Portfolio p : portfolios.values()){
                FileWriter csvWriter = new FileWriter(p.getAccountNum() + ".csv");
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




        } catch (IOException e){
            System.out.println("File Not Chosen");
        }
    }

}
