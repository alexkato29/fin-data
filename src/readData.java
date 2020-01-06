

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class readData {

    private final int ACCOUNT_NUM_COL = 0;


    public static ArrayList<Trade> csvParse(String tradeFilePath) throws IOException {
        ArrayList<Trade> csvTrades = new ArrayList<>();
        try{
            Reader reader = Files.newBufferedReader(Paths.get(tradeFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                //System.out.printf("%15s %15s %15s %15s\n", csvRecord.get(0),csvRecord.get(1),csvRecord.get(2),csvRecord.get(3));
                if (csvRecord.get(0).equals("Account #") || csvRecord.get(0).equals("") ) {
                    continue;
                } else{
                    String accountNum = csvRecord.get(0);
                    String registration = csvRecord.get(5);
                    String ticker = csvRecord.get(6);
                    double quantity = Double.parseDouble(csvRecord.get(8).replaceAll(",",""));
                    double price = Double.parseDouble(csvRecord.get(10).replaceAll("\\$","").replaceAll(",", ""));

                    csvTrades.add(new Trade(accountNum, registration, ticker, quantity, price));



                }



            }
        } catch (IOException e){
            System.out.println("You must pick a file");
        }
        return csvTrades;
    }

    public static void jsonUpdate(String jsonDBFilePath, ArrayList<Trade> tradeList){

        try (Reader reader = new FileReader(".\\data\\newJSON.json")) {

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            JSONArray portfolios = (JSONArray) data.get("portfolios");

            System.out.println(portfolios);
            for (int i = 0; i < portfolios.size(); i++){
                JSONObject account = (JSONObject) portfolios.get(i);
                for (int j = 0; j < tradeList.size(); j++){
                    Trade trade = tradeList.get(j);
                    if (account.get("accountNum").equals(trade.getAccountNum())) {
                        JSONObject securities = (JSONObject) account.get("securities");

                        securities.get(trade.getTicker());

                    }



                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }


//            @SuppressWarnings("resource")
//            FileWriter file = new FileWriter(".\\testdata\\TC_11.json");
//            file.write(data.toJSONString());
//            file.flush();

        }




    }


