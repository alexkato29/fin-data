import javafx.beans.property.SimpleStringProperty;

import java.awt.*;

/**
 * Created by AlexKatopodis on 5/10/20.
 */
public class Row {
    private SimpleStringProperty name;
    private SimpleStringProperty accntNum;
    private Button downloadPortfolio;
    private Button downloadTradeLog;
    private Button delete;

    public Row(String name, String accntNum) {
        this.name = new SimpleStringProperty(name);
        this.accntNum = new SimpleStringProperty(accntNum);
        downloadPortfolio = new Button("Portfolio");
        downloadTradeLog = new Button("Trade Log");
        delete = new Button("Delete");
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty getAccountNum() {
        return accntNum;
    }

    public void setAccountNum(String accntNum) {
        this.accntNum = new SimpleStringProperty(accntNum);
    }

    public Button getDownloadPortfolio() {
        return downloadPortfolio;
    }

    public void setDownloadPortfolio(Button downloadPortfolio) {
        this.downloadPortfolio = downloadPortfolio;
    }

    public Button getDownloadTradeLog() {
        return downloadTradeLog;
    }

    public void setDownloadTradeLog(Button downloadTradeLog) {
        this.downloadTradeLog = downloadTradeLog;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
