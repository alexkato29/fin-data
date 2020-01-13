import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by AlexKatopodis on 1/2/20.
 */
public class Database {
    private String pathToFile;

    public Database (String pathToFile) {
        this.pathToFile = pathToFile;

        try (Reader reader = new FileReader(".\\data\\testJSON.json")) {

            org.json.simple.parser.JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            JSONArray portfolios = (JSONArray) data.get("portfolios");

            ArrayList<Trade> tradeList = readData.csvParse(".\\data\\test.csv");

            System.out.println(data);
            for (int i = 0; i < portfolios.size(); i++){
                JSONObject account = (JSONObject) portfolios.get(i);
                for (int j = 0; j < tradeList.size(); j++){
                    Trade trade = tradeList.get(j);
//                    System.out.println(account.get("securities") + " = " + trade.getTicker());
                    if (account.get("accountNum").equals(trade.getAccountNum())) {
                        JSONObject securities = (JSONObject) account.get("securities");

                        JSONObject company = (JSONObject) securities.get(trade.getTicker());
//                        System.out.println(trade.getTicker() + " " + company);

                        double newQuantity = (long)company.get("quantity") + trade.getQuantity();
                        double newPrice = trade.getPrice();
                        System.out.println();
                        company.put("quantity", 9999);
                        company.put("price", 9999);
                    }
                }
            }

            FileWriter file = new FileWriter(".\\data\\output.json", true);
            try {
                file.write(data.toJSONString());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                file.flush();
                file.close();
            }


        }

        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

//    public Portfolio getPortfolio (String accountNum) {
//    }
//
//    public boolean updatePortfolio (Portfolio p) {
//        return isSuccessful;
//    }

//    public boolean newPortfolio (Portfolio p) {
//        return isSuccessful;
//    }
}
