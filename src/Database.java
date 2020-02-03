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
    private ArrayList<Portfolio> portfolios;


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

    public Portfolio getPortfolio (String accountNum) {
        return null;
    }

//    public boolean updatePortfolio (Portfolio p) {
//        return false;
//    }
//
//    public boolean newPortfolio (Portfolio p) {
//        return false;
//    }
}
