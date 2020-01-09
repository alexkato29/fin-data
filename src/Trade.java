public class Trade {


    private String accountNum;
    private String registration;
    private Security security;

    public Trade(String accountNum, String registration, String ticker, double quantityPurchased, double price) {
        this.accountNum = accountNum;
        this.registration = registration;
        setSecurity(ticker, price, quantityPurchased);
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Security getSecurity () {
        return security;
    }

    public void setSecurity (String ticker, double price, double quantityPurchased) {
        security = new Security (ticker, price, quantityPurchased);
    }

    @Override
    public String toString(){
        return "Account #: " + accountNum + "\nRegistration: " + registration + "\nTicker: " + security.getTicker() + "\nQuantity Purchased: " + security.getQuantity() +"\nPrice: " + security.getPrice() + "\n";


    }
}
