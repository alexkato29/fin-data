/**
 * Created by AlexKatopodis on 12/5/19.
 */
public class Security {
    private String ticker;
    private double quantity;
    private double price; // This price is the most recent price.  Most recent is defined by whatever the price was last time it was traded.

    public Security (String ticker, double quantity, double lastPrice) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = lastPrice;
    }

    // Quantities
    public double getQuantity () {
        return quantity;
    }

    public void changeQuantity (double quantity) {
        this.quantity += quantity;
    }

    // Prices
    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    // Tickers
    public String getTicker () {
        return ticker;
    }

    public String toString(){
        return "Ticker: " + ticker + "\nQuantity: " + quantity + "\nPrice: " + price + "\n";
    }

    public boolean equals (Security s) {
        return s.getTicker().equals(ticker);
    }

    public void applyTrade(Trade t){
        if (t.getTicker().equals(ticker)){

            setPrice(t.getPrice());
            changeQuantity(t.getQuantity());

        }
    }
}
