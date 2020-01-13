import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by AlexKatopodis on 1/2/20.
 */
public class Database {
    private String pathToFile;
    private Map<String, Portfolio> portfolios;

    public Database (String pathToFile) {
        this.pathToFile = pathToFile;

    }

//    private Map<String, Portfolio> createNewDB () {
//
//    }
//
//    public Portfolio getPortfolio (String accountNum) {
//    }
//
//    public boolean updatePortfolio (Portfolio p) {
//        return isSuccessful;
//    }
//
//    public boolean newPortfolio (Portfolio p) {
//        return isSuccessful;
//    }
}
