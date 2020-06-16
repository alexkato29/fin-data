import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LandingController implements Initializable {

    private Database portfolioDatabase;
    private Stage primaryStage;
    private static Stage renderTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        portfolioDatabase = FinanceApp.getPortfolioDatabase();
        primaryStage = FinanceApp.getPrimaryStage();
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

    @FXML
    public void showTable(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:./styles/xml/tableview.fxml"));

        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox);

        renderTable = new Stage();
        renderTable.setScene(scene);
        renderTable.setTitle("Portfolio Manager");
        renderTable.show();
    }

    public static Stage getPrimaryStage() {
        return renderTable;
    }
}