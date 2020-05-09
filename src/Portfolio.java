import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by AlexKatopodis on 12/5/19.
 */
public class Portfolio {
    private String accountNum;
    private String accountHolder;
    private boolean isIndividual;
    private Map<String, Security> securities;
    private double portfolioValue;
    private LocalDateTime lastUpdated;

    public Portfolio (String accountNum, String accountHolder, boolean isIndividual, Map<String, Security> securities, double portfolioValue) {
        this.accountNum = accountNum;
        this.accountHolder = accountHolder;
        this.isIndividual = isIndividual;
        this.securities = securities;
        this.portfolioValue = portfolioValue;
        setLastUpdated();
    }

    // Account Number and Holder
    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    // Is it an Individual Account
    public boolean isIndividual() {
        return isIndividual;
    }

    // Managing Securities
    public Map<String, Security> getSecurities() {
        return securities;
    }

    // Updating the LastUpdated Date
    public void setLastUpdated() {
        lastUpdated = LocalDateTime.now();
    }

    // Valuing the Portfolio
    public double getPortfolioValue() {
        return portfolioValue;
    }

    public void calcPortfolioValue() {
        double newVal = 0;
        for (Map.Entry security : securities.entrySet()) {
            Security s = (Security) security.getValue();
            newVal += (s.getPrice() * s.getQuantity());
        }
        portfolioValue = newVal;
    }

//    public void applyTrade (Trade t) {
//        addSecurity(t.getSecurity());
//        calcPortfolioValue();
//    }

//    public void addSecurity(Security newSec) {
//        Security tempSec = securities.get(newSec.getTicker());
//        if (tempSec != null) {
//            tempSec.changeQuantity(newSec.getQuantity());
//            tempSec.setPrice(newSec.getPrice());
//            return;
//        }
//        securities.put(tempSec.getTicker(), tempSec);
//    }

    public void applyTrade (Trade t){

        if (!(t.getAccountNum().equalsIgnoreCase(accountNum))){
            return;
        }
        String newTicker = t.getTicker();
        if (securities.get(newTicker)==null){
            Security newSecurity = new Security(t.getTicker(), t.getQuantity(), t.getPrice());
            securities.put(newSecurity.getTicker(), newSecurity);
        } else{

            ArrayList<String> removeSecurities = new ArrayList<>();
            for(Security s : securities.values()){

                s.applyTrade(t);

                if (s.getQuantity() <= 0){
//                    System.out.println(s.getTicker() + " should be removed");
//                    System.out.println(securities.keySet().toArray()[0].equals(s.getTicker()));

                    removeSecurities.add(s.getTicker());
//                    securities.remove(ticker); // TODO: this line doesn't work

                }
            }

            for(String e : removeSecurities){
                securities.remove(e);
            }
        }
    }

    public void export (File defaultDirectory) {

        try {
            System.out.println(defaultDirectory.getAbsolutePath());
            FileWriter csvWriter = new FileWriter(defaultDirectory.getAbsoluteFile() + "\\" + getAccountHolder() +"_portfolio.csv");
            csvWriter.append("Ticker");
            csvWriter.append(",");
            csvWriter.append("Quantity");
            csvWriter.append("\n");

            Map<String, Security> securities = getSecurities();

            for (Map.Entry<String, Security> set : securities.entrySet()) {
                Security s = set.getValue();
                csvWriter.append(s.getTicker());
                csvWriter.append(",");
                csvWriter.append(Double.toString(s.getQuantity()));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();


        } catch (IOException e){
            System.out.println("File Not Chosen");
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder("------" + accountHolder + "-----\n");
        for (Security s: securities.values()) {
            str.append(s.toString());
            str.append("\n");
        }
        str.append("------End-----");

        return str.toString();
    }
}