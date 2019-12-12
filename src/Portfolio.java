import java.util.ArrayList;

/**
 * Created by AlexKatopodis on 12/5/19.
 */
public class Portfolio {
    private String accountNum;
    private String accountHolder;
    private boolean isIndividual;
    private ArrayList<Security> securities;
    private double portfolioValue;

    public Portfolio (String accountNum, String accountHolder, boolean isIndividual, ArrayList<Security> securities, double portfolioValue) {
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
    public ArrayList<Security> getSecurities() {
        return securities;
    }

    public void addSecurity(Security newSec) {
        for (Security existingSec : securities) {
            if (existingSec.equals(newSec)) {
                existingSec.setPrice(newSec.getPrice());
                existingSec.changeQuantity(newSec.getQuantity());
                return;
            }
        }
        securities.add(newSec);
    }

    // Valuing the Portfolio
    public double getPortfolioValue() {
        return portfolioValue;
    }

    public void calcPortfolioValue() {
        double newVal = 0;
        for (Security s : securities)
            newVal += (s.getPrice() * s.getQuantity());
        portfolioValue = newVal;
    }
}
