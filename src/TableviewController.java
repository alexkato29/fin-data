import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by AlexKatopodis on 5/11/20.
 */
public class TableviewController implements Initializable {

    private Database portfolioDatabase;
    private ObservableList<Row> rows;
    private HashMap<Button, Portfolio> connection;

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

        rows = FXCollections.observableArrayList();
        connection = new HashMap<>();

        for (Portfolio p : portfolioDatabase.getPortfolios().values()){
            Button pbutton = new Button("Portfolio");
            pbutton.setOnAction(this::downloadPortfolio);
            Button tbutton = new Button("Trade Log");
            tbutton.setOnAction(this::downloadTL);
            Button dbutton = new Button("Delete");
            dbutton.setOnAction(this::delete);
            connection.put(pbutton, p);
            connection.put(tbutton, p);
            connection.put(dbutton, p);

            rows.add(new Row(p.getAccountHolder(), p.getAccountNum(), pbutton, tbutton, dbutton ) );
//            rows.add(new Row(p.getAccountHolder(), p.getAccountNum(), new Button("hello"), new Button("hello"), new Button("hello") ) );
        }

        names.setCellValueFactory(new PropertyValueFactory<Row, String>("name"));
        nums.setCellValueFactory(new PropertyValueFactory<Row, String>("accountNum"));
        port.setCellValueFactory(new PropertyValueFactory<Row, String>("downloadPortfolio"));
        tl.setCellValueFactory(new PropertyValueFactory<Row, String>("downloadTradeLog"));
        del.setCellValueFactory(new PropertyValueFactory<Row, String>("delete"));

        table.setItems(rows);
    }

    @FXML
    public void downloadPortfolio(ActionEvent event) {
        // TODO: Make the button download the given portfolio's CSV using the method "export" inside of portfolio.
        Portfolio p = connection.get(event.getSource());
        DirectoryChooser dirchooser = new DirectoryChooser();
        File saveFolder = dirchooser.showDialog(new Stage());
        p.export(saveFolder);

    }

    @FXML
    public void downloadTL(ActionEvent event) {
        // TODO: Make this button download the Trade Log for the given portfolio using the ExportTrades class
        Portfolio p = connection.get(event.getSource());
        exportTrades E = new exportTrades(new Stage());
        E.export(p.getAccountNum());
    }

    @FXML
    public void delete(ActionEvent event)  {
        // TODO: Make this button delete a portfolio using the "delete" method in Database

        if (!readData.showConfirmation("Delete Portfolio", "Are you sure you want to delete this portfolio?"))
            return;

        Portfolio p = connection.get(event.getSource());
        portfolioDatabase.deletePortfolio(p);

        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(new URL("file:./styles/xml/tableview.fxml"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            VBox vbox = loader.<VBox>load();
            Scene scene = new Scene(vbox);
            Stage primaryStage = LandingController.getPrimaryStage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Portfolio Manager");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}