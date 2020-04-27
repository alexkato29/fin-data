import java.util.ArrayList;

public class testjson {

    public static void main(String[] args) {
        Database db = new Database("./data/newFormat.json");

        try{
            ArrayList<Trade> test = readData.tradeCsvParse("./data/newtrade.csv");
            System.out.println(test);
        }
        catch (Exception e){
        }
//        Portfolio test = db.getPortfolio("123");
//        System.out.println(test.getAccountHolder());
//        System.out.println(test.getAccountNum());
//        System.out.println(test.getPortfolioValue());
//        System.out.println(test.getSecurities());
    }
}



