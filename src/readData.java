

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

    public static void jsonParse(){
            JSONParser parser = new JSONParser();

            try (Reader reader = new FileReader("c:\\projects\\test.json")) {

                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                System.out.println(jsonObject);

                String name = (String) jsonObject.get("name");
                System.out.println(name);

                long age = (Long) jsonObject.get("age");
                System.out.println(age);

                // loop array
                JSONArray msg = (JSONArray) jsonObject.get("messages");
                Iterator<String> iterator = msg.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
