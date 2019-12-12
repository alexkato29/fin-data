public class Trade {


    private String accountNum;
    private String registration;
    private String ticker;
    private double quantityPurchased;  // closing quanitity
    private double price;

    public Trade(String accountNum, String registration, String ticker, double quantityPurchased, double price) {
        this.accountNum = accountNum;
        this.registration = registration;
        this.ticker = ticker;
        this.quantityPurchased = quantityPurchased;
        this.price = price;
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

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Account #: " + accountNum + "\nRegistration: " + registration + "\nTicker: " + ticker + "\nQuantity Purchased: " + quantityPurchased +"\nPrice: " + price + "\n";


    }
}
