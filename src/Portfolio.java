import java.util.ArrayList;
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

    public Portfolio (String accountNum, String accountHolder, boolean isIndividual, Map<String, Security> securities, double portfolioValue) {
        this.accountNum = accountNum;
        this.accountHolder = accountHolder;
        this.isIndividual = isIndividual;
        this.securities = securities;
        this.portfolioValue = portfolioValue;
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
            for(Security s : securities.values()){
                s.applyTrade(t);
                if (s.getQuantity() <= 0){
                    securities.remove(s.getTicker());
//                   TODO: Remove security when quantity is all closed
                }
            }
        }


    }
    public String toString(){
        StringBuilder str = new StringBuilder("------Portfolio-----\n");
        for (Security s: securities.values()) {
            str.append(s.toString());
            str.append("\n");
        }
        str.append("------End-----");

        return str.toString();
    }
}