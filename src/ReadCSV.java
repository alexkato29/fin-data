

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadCSV {

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
}
