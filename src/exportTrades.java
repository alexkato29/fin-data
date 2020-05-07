import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class exportTrades {

    private Stage parentStage;


    public exportTrades(Stage parent){
        this.parentStage = parent;
    }

    public void export (String accountNum)  {

        ArrayList<Trade> tradeList;
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(parentStage);
        HashSet<String> accountNumbers = new HashSet<>();

        try {
            tradeList = readData.tradeCsvParse(file.getAbsolutePath());
            DirectoryChooser dirchooser = new DirectoryChooser();
            File directory = dirchooser.showDialog(parentStage);
            FileWriter csvWriter = new FileWriter( directory.getAbsoluteFile() + "\\" + accountNum+"_trade.csv");
            csvWriter.append("Ticker");
            csvWriter.append(",");
            csvWriter.append("Quantity");
            csvWriter.append("\n");

            for(Trade t: tradeList){
                if (t.getAccountNum().equals(accountNum)){
                    csvWriter.append(t.getTicker());
                    csvWriter.append(",");
                    csvWriter.append(Double.toString(t.getQuantity()));
                    csvWriter.append("\n");

                }
            }

            csvWriter.flush();
            csvWriter.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Trades");
            alert.setHeaderText(null);
            alert.setContentText("Trades have been exported");
            alert.showAndWait();

        } catch (IOException e){
            System.out.println("File Not Chosen");
        }
    }
}




