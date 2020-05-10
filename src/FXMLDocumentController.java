import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    private Database portfolioDatabase;
    private Stage primaryStage;

    @FXML TableView table;

    @FXML TableColumn names;
    @FXML TableColumn nums;
    @FXML TableColumn port;
    @FXML TableColumn tl;
    @FXML TableColumn del;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        portfolioDatabase = FinanceApp.getPortfolioDatabase();
        primaryStage = FinanceApp.getPrimaryStage();

        ObservableList<Row> rows = FXCollections.observableArrayList();

        for (Portfolio p : portfolioDatabase.getPortfolios().values())
            rows.add(new Row(p.getAccountHolder(), p.getAccountNum()));

        names.setCellValueFactory(new PropertyValueFactory<Row, String>("name"));
        nums.setCellValueFactory(new PropertyValueFactory<Row, String>("accountNum"));
        port.setCellValueFactory(new PropertyValueFactory<Row, String>("downloadPortfolio"));
        tl.setCellValueFactory(new PropertyValueFactory<Row, String>("downloadTradeLog"));
        del.setCellValueFactory(new PropertyValueFactory<Row, String>("delete"));

        table.setItems(rows);
    }

    @FXML
    public void applyTrade(ActionEvent event) {
        event.consume();

        ArrayList<Trade> tradeList;

        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);

            tradeList = readData.tradeCsvParse(file.getAbsolutePath());


            for (Trade t : tradeList) {
                portfolioDatabase.applyTrade(t);
            }

            readData.showAlert("Database Update", "Database has been Updated");


        } catch (Exception e) {

            readData.showAlert("Error", "Check Console for error");
            e.printStackTrace();
        }
    }


    @FXML
    public void addPortfolio(ActionEvent event) {
        event.consume();
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);

            Portfolio add = readData.portfolioCsvParse(file.getAbsolutePath());
            portfolioDatabase.addPortfolio(add);
            readData.showAlert("Add Portfolio", "Portfolio has been added");


        } catch (Exception e) {
            readData.showAlert("Error", "File Not Chosen");
        }
    }
}