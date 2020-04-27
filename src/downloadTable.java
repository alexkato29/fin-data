import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;



import java.io.File;
import java.util.HashMap;

public class downloadTable implements EventHandler<javafx.event.ActionEvent>{

    private Stage parentStage;
    private File defaultDirectory;
    TableView tableView;
    private HashMap<String,Portfolio> portfolios;
    public downloadTable(Stage primaryStage, File defaultDirectory, HashMap<String,Portfolio> portfolios){
        this.parentStage = primaryStage;
        this.defaultDirectory = defaultDirectory;
        this.portfolios= portfolios;
    }

    @Override
    public void handle(ActionEvent event) {
        tableView = new TableView();
        tableView.setPlaceholder(new Label("No Rows to display"));


        TableColumn<String, Portfolio> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("accountHolder"));
        TableColumn<String, Portfolio> accnumColumn = new TableColumn<>("Account Num");
        accnumColumn.setCellValueFactory(new PropertyValueFactory<>("accountNum"));




        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(accnumColumn);


        for(Portfolio p : portfolios.values()){
            tableView.getItems().add(p);
        }
        addButtonToTable();




        Stage stage = new Stage();
        stage.setTitle("Portfolios");
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 300, 500);
        stage.setScene(scene);
        stage.show();



    }
    private void addButtonToTable() {
        TableColumn<Portfolio, Void> dpBtn = new TableColumn("Download Portfolio");
        TableColumn<Portfolio, Void> dtBtn = new TableColumn("Download TradeLog");


        Callback<TableColumn<Portfolio, Void>, TableCell<Portfolio, Void>> dtCellFactory = new Callback<TableColumn<Portfolio, Void>, TableCell<Portfolio, Void>>() {
            @Override
            public TableCell<Portfolio, Void> call(final TableColumn<Portfolio, Void> param) {
                final TableCell<Portfolio, Void> cell = new TableCell<Portfolio, Void>() {

                    private final Button btn = new Button("TradeLog");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Portfolio portfolio = getTableView().getItems().get(getIndex());
                            exportTrades E = new exportTrades(parentStage ,defaultDirectory);
                            E.export(portfolio.getAccountNum());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        Callback<TableColumn<Portfolio, Void>, TableCell<Portfolio, Void>> dpCellFactory = new Callback<TableColumn<Portfolio, Void>, TableCell<Portfolio, Void>>() {
            @Override
            public TableCell<Portfolio, Void> call(final TableColumn<Portfolio, Void> param) {
                final TableCell<Portfolio, Void> cell = new TableCell<Portfolio, Void>() {

                    private final Button btn = new Button("Portfolio");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Portfolio portfolio = getTableView().getItems().get(getIndex());
                            System.out.println(portfolio);
                            portfolio.export(defaultDirectory);

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        dpBtn.setCellFactory(dpCellFactory);
        dtBtn.setCellFactory(dtCellFactory);

        tableView.getColumns().add(dpBtn);
        tableView.getColumns().add(dtBtn);

    }



}

