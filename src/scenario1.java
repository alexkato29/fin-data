import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class scenario1 implements EventHandler<ActionEvent> {

    private Stage parentStage;
    private File defaultDirectory;

    public scenario1(Stage parent, File defaultDirectory){
        this.defaultDirectory = defaultDirectory;
        this.parentStage = parent;
    }

    public void handle(ActionEvent event)  {

        ArrayList<Trade> tradeList;

        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(parentStage);
            fileChooser.setInitialDirectory(defaultDirectory);

            tradeList = readData.csvParse(file.getAbsolutePath());



            HashSet<String> accountNumbers = new HashSet<>();


            for (Trade t : tradeList) {
                accountNumbers.add(t.getAccountNum());
            }


            for (String accountNum: accountNumbers){
                FileWriter csvWriter = new FileWriter(accountNum+".csv");
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




        } catch (IOException e){
            System.out.println("File Not Chosen");
        }
    }
}




