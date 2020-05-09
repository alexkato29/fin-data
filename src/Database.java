import javafx.scene.control.Alert;
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
    private HashMap<String, Portfolio> portfolios = new HashMap<String,Portfolio>();

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

                JSONArray securityArray = (JSONArray) p.get("securities");
                Map<String,Security> securities = new HashMap<>();
                for (int j = 0 ; j < securityArray.size(); j++){
                    JSONObject company = (JSONObject)securityArray.get(j);
                    String ticker = (String)company.get("ticker");
                    double quantity = new Double(company.get("quantity").toString());
                    double price = new Double(company.get("price").toString());

                    Security s = new Security(ticker, quantity, price);
                    securities.put(ticker, s);
                }




                Portfolio newPortfolio = new Portfolio(accountNum, accountHolder, isIndividual, securities, portfolioValue);
                addPortfolio(newPortfolio);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Database file Format is incorrect\n Restart the Program and Select the correct file");

            alert.showAndWait();
        }
    }

    public Portfolio getPortfolio (String accountNum) {
        return portfolios.get(accountNum);
    }

    public HashMap<String, Portfolio> getPortfolios() {
        return portfolios;
    }


    public void addPortfolio (Portfolio p) {
        portfolios.put(p.getAccountNum(), p);
    }

    public void deletePortfolio (Portfolio p) {
        portfolios.remove(p.getAccountNum());
    }

    public void applyTrade (Trade t){
        for(Portfolio p : portfolios.values()){
            p.applyTrade(t);
        }

    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Database\n");
        for (Portfolio p: portfolios.values()) {
            str.append(p.toString());
            str.append("\n");
        }

        return str.toString();
    }
}

