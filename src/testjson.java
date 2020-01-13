import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class testjson {

    public static void main(String[] args) {
        try (Reader reader = new FileReader(".\\data\\newJSON.json")) {

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            JSONArray portfolios = (JSONArray) data.get("portfolios");

            ArrayList<Trade> tradeList = readData.csvParse(".\\data\\test.csv");


            for (int i = 0; i < portfolios.size(); i++){
                JSONObject account = (JSONObject) portfolios.get(i);
                for (int j = 0; j < tradeList.size(); j++){
                    Trade trade = tradeList.get(j);
                    if (account.get("accountNum").equals(trade.getAccountNum())) {
                        JSONObject securities = (JSONObject) account.get("securities");

                        JSONObject company = (JSONObject) securities.get(trade.getTicker());
                        System.out.println(trade.getTicker() + " " + company);

                        double newQuantity = (long)company.get("quantity") + trade.getQuantityPurchased();
                        double newPrice = trade.getPrice();
                        company.put("quantity", 9999);
                        company.put("price", 9999);

                        System.out.println(company);
                    }
                }
            }

            FileWriter file = new FileWriter(".\\data\\testJSON.json", true);
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
}



