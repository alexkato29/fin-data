import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;


/**
 * Created by AlexKatopodis on 5/10/20.
 */
public class Row {
    private SimpleStringProperty name;
    private SimpleStringProperty accountNum;
    private Button downloadPortfolio;
    private Button downloadTradeLog;
    private Button delete;

    public Row(String name, String accntNum, Button downloadPortfolioBtn, Button downloadTradeLogBtn, Button deleteBtn) {
        this.name = new SimpleStringProperty(name);
        this.accountNum = new SimpleStringProperty(accntNum);
        downloadPortfolio = downloadPortfolioBtn;
        downloadTradeLog = downloadTradeLogBtn;
        delete = deleteBtn;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getAccountNum() {
        return accountNum.get();
    }

    public void setAccountNum(String accntNum) {
        this.accountNum = new SimpleStringProperty(accntNum);
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
