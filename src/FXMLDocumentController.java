import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable{

    private Database portfolioDatabase;
    @FXML javafx.scene.control.Button closeButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO: Try to get the Parent Window into


        try{

            //Initialize File Chooser
            FileChooser fileChooser = new FileChooser();

            //Find Previous Directory

            fileChooser.setInitialDirectory(new File("./data"));
            File dbFile = fileChooser.showOpenDialog(new Stage());

            //Update

            portfolioDatabase = new Database(dbFile.getAbsolutePath());


        } catch(Exception e){
            readData.showAlert("Error", "Oops something went wrong");
        }





    }

    @FXML
    public void applyTrade(ActionEvent event) {
        event.consume();

        ArrayList<Trade> tradeList;

        try {
            FileChooser fileChooser = new FileChooser();
            File previousDir =  readData.getDirectory("uploadTradesDir");
            fileChooser.setInitialDirectory(previousDir);
            File file = fileChooser.showOpenDialog(new Stage());

            readData.editDirectory("uploadTradesDir", file.getParent());
            tradeList = readData.tradeCsvParse(file.getAbsolutePath());


            for (Trade t: tradeList){
                portfolioDatabase.applyTrade(t);
            }

            readData.showAlert("Database Update", "Database has been Updated");


        } catch (Exception e) {
            readData.showAlert("Error", "File Not Chosen");
        }
    }


    @FXML
    public void addPortfolio(ActionEvent event) {

        try {
            FileChooser fileChooser = new FileChooser();
            File previousDir =  readData.getDirectory("uploadPortfolio");
            fileChooser.setInitialDirectory(previousDir);
            File file = fileChooser.showOpenDialog(new Stage());
            readData.editDirectory("uploadPortfolio", file.getParent());





            Portfolio add = readData.portfolioCsvParse(file.getAbsolutePath());
            portfolioDatabase.addPortfolio(add);
            readData.showAlert("Add Portfolio", "Portfolio has been added");


        } catch (Exception e){
            readData.showAlert("Error", "File Not Chosen");
        }
    }















}
