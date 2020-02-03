import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.Port;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexKatopodis on 1/2/20.
 */
public class Database {
    private String pathToFile;
    private HashMap<String, Portfolio> portfolios;

    public Database (String pathToFile) {
        this.pathToFile = pathToFile;

        try (Reader reader = new FileReader(pathToFile)) {
            org.json.simple.parser.JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            JSONArray storedPortfolios = (JSONArray) data.get("portfolios");

            for (int i = 0; i < storedPortfolios.size(); i++) {
                JSONObject p = (JSONObject) storedPortfolios.get(i);
                String accountNum = (String) p.get("accountNum");
                String accountHolder = (String) p.get("accountHolder");
                boolean isIndividual = (boolean) p.get("isIndividual");
                double portfolioValue = (double) p.get("portfolioValue");
                JSONObject securityData = (JSONObject) p.get("securities");
                Map<String, Security> securities = new HashMap<String, Security>();
                for (int j = 0; j < securityData.size(); j++) {
                    Security security = securities.get(j);
                    securities.put(security.getTicker(), security);
                }
                Portfolio newPortfolio = new Portfolio(accountNum, accountHolder, isIndividual, securities, portfolioValue);
                portfolios.put(accountNum, newPortfolio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    public Portfolio getPortfolio (String accountNum) {
        return portfolios.get(accountNum);
    }

    public HashMap<String, Portfolio> getPortfolios() {
        return portfolios;
    }

    //
//    public boolean updatePortfolio (Portfolio p) {
//        return isSuccessful;
//    }

//    public boolean newPortfolio (Portfolio p) {
//        return isSuccessful;
//    }
}
