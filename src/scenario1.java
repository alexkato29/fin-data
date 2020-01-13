import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class scenario1 implements EventHandler<ActionEvent> {

    private Stage parentStage;
    private final  String DATABASEFILEPATH = ".\\data\\newJSON.json";
    public scenario1(Stage parent){
        this.parentStage = parent;
    }

    public void handle(ActionEvent event)  {

        ArrayList<Trade> tradeList;

        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(parentStage);

            tradeList = readData.csvParse(file.getAbsolutePath());
            readData.jsonUpdate(DATABASEFILEPATH, tradeList);


        } catch (IOException e){
            System.out.println("File Not Chosen");
        }
    }
}




