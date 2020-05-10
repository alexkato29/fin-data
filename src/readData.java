

import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.beans.EventHandler;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


public class readData {



    public static ArrayList<Trade> tradeCsvParse(String tradeFilePath) throws IOException {
        ArrayList<Trade> csvTrades = new ArrayList<>();
        try{
            Reader reader = Files.newBufferedReader(Paths.get(tradeFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                //System.out.printf("%15s %15s %15s %15s\n", csvRecord.get(0),csvRecord.get(1),csvRecord.get(2),csvRecord.get(3));
                if (csvRecord.get(0).equals("Account #") || csvRecord.get(0).equals("") ) {
                    continue;
                } else if (Character.isLetter(csvRecord.get(0).charAt(0))){
                    break;
                }
                else{
                    String accountNum = csvRecord.get(0);
                    String accountHolder = csvRecord.get(2);
                    String registration = csvRecord.get(5);
                    String ticker = csvRecord.get(6);
                    double quantity = Double.parseDouble(csvRecord.get(8).replaceAll(",",""));
                    double price = Double.parseDouble(csvRecord.get(10).replaceAll("\\$","").replaceAll(",", ""));


                    csvTrades.add(new Trade(accountNum, accountHolder,registration, ticker, quantity, price));
                }
            }
        } catch (IOException e){
            System.out.println("You must pick a file");
        }
        return csvTrades;
    }
    public static Portfolio portfolioCsvParse(String portfolioFilePath) throws IOException {
        Portfolio add ;
        try{
            Reader reader = Files.newBufferedReader(Paths.get(portfolioFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);


            List<CSVRecord> recordList =csvParser.getRecords();
            recordList.remove(0);
            CSVRecord firstRow =  recordList.get(0);




            String accountNum = firstRow.get(0);
            String accountHolder = firstRow.get(3);
            Map<String, Security> securities = new HashMap<>();
            double totalValue = 0;


            for (CSVRecord csvRecord : recordList) {

                if(csvRecord.get(0).equalsIgnoreCase("")) break;
                String ticker = csvRecord.get(6);
                double quantity = Double.parseDouble(csvRecord.get(8).replaceAll(",",""));
                double price = Double.parseDouble(csvRecord.get(10).replaceAll("\\$","").replaceAll(",", ""));
                totalValue += quantity * price;
                securities.put(ticker, new Security(ticker, quantity, price));
            }
            //TODO: Check up on isINdividual if it should be true or not
            add = new Portfolio(accountNum, accountHolder, true, securities, totalValue );

            return add;
        } catch (Exception e){
            readData.showAlert("Error", "Something went Wrong\nreadData.portfolioCsvParse(String portfolioFilePath);");
        }
        return null;


    }
    public static File getDirectory(String key){
        JSONParser jsonParser = new JSONParser();


        // TODO: Change Directory when finished         VVVVVVV
        try (FileReader reader = new FileReader("./data/info.json")){

            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            String dir = (String)obj.get(key);
            return new File(dir);

        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void editDirectory(String key, String newDirectory){
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("./data/info.json")){
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            System.out.println(obj);
            obj.put(key, newDirectory);
            System.out.println(obj);


            reader.close();

            FileWriter file = new FileWriter("./data/info.json", false);
            file.write(obj.toJSONString());
            file.close();

        } catch (Exception e){
            e.printStackTrace();

        }






    }



    public static void showAlert(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }



    public static void jsonUpdate(String jsonDBFilePath, ArrayList<Trade> tradeList){

        try (Reader reader = new FileReader(jsonDBFilePath)) {

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            JSONArray portfolios = (JSONArray) data.get("portfolios");

            for (int i = 0; i < portfolios.size(); i++){
                JSONObject account = (JSONObject) portfolios.get(i);
                for (int j = 0; j < tradeList.size(); j++){
                    Trade trade = tradeList.get(j);
                    if (account.get("accountNum").equals(trade.getAccountNum())) {
                        JSONObject securities = (JSONObject) account.get("securities");

                        JSONObject company = (JSONObject) securities.get(trade.getTicker());
                        double newQuantity = (long)company.get("quantity") + trade.getQuantity();
                        double newPrice = trade.getPrice();

                        company.put("quantity", newQuantity);
                        company.put("price", newPrice);
                    }
                }
            }


//            String fileName = new SimpleDateFormat("yyyyMMddHHmm'.json'").format(new Date());
            String fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm'.json'").format(new Date());

            FileWriter file = new FileWriter(".\\data\\"+fileName, true);

//
//            FileWriter file = new FileWriter(".\\data\\" + fileName, false);



            try {
                file.write(data.toJSONString());
                System.out.println("Portfolio Updated");

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
