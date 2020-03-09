public class Trade {


    private String accountNum;
    private String accountHolder;
    private String registration;
    private Security security;


    public Trade(String accountNum, String accountHolder, String registration, String ticker, double quantityPurchased, double price) {
        this.accountNum = accountNum;
        this.registration = registration;
        this.accountHolder = accountHolder;
        setSecurity(ticker, quantityPurchased, price);
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

    public void setSecurity (String ticker,  double quantityPurchased, double price) {
        security = new Security (ticker, quantityPurchased, price);
    }

    public String getTicker () {
        return security.getTicker();
    }

    public double getPrice () {
        return security.getPrice();
    }

    public double getQuantity () {
        return security.getQuantity();
    }

    @Override
    public String toString(){
        return "Account #: " + accountNum +"\nAccount Holder: " + accountHolder +"\nRegistration: " + registration + "\nTicker: " + security.getTicker() + "\nQuantity Purchased: " + security.getQuantity() +"\nPrice: " + security.getPrice() + "\n";


    }
}
