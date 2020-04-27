import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class exportTrades implements EventHandler<ActionEvent> {

    private Stage parentStage;
    private File defaultDirectory;

    public exportTrades(Stage parent, File defaultDirectory){
        this.defaultDirectory = defaultDirectory;
        this.parentStage = parent;
    }

    public void handle(ActionEvent event)  {

        ArrayList<Trade> tradeList;

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(defaultDirectory);
            File file = fileChooser.showOpenDialog(parentStage);


            tradeList = readData.tradeCsvParse(file.getAbsolutePath());



            HashSet<String> accountNumbers = new HashSet<>();


            for (Trade t : tradeList) {
                accountNumbers.add(t.getAccountNum());
            }


            for (String accountNum: accountNumbers){
                FileWriter csvWriter = new FileWriter(defaultDirectory.getAbsoluteFile()+"\\exportedTrades\\"+ accountNum+"_trade.csv");
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






            }
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




