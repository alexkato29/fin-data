import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by AlexKatopodis on 5/11/20.
 */
public class TableviewController implements Initializable {

    private Database portfolioDatabase;

    @FXML
    TableView table;

    @FXML
    TableColumn names;
    @FXML TableColumn nums;
    @FXML TableColumn port;
    @FXML TableColumn tl;
    @FXML TableColumn del;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        portfolioDatabase = FinanceApp.getPortfolioDatabase();

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
}