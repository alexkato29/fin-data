import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;

/**
 * Created by AlexKatopodis on 1/2/20.
 */
public class Database {
    private String pathToFile;

    public Database (String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Portfolio getPortfolio (String accountNum) {
        return null;
    }

    public boolean updatePortfolio (Portfolio p) {
        return true;
    }

    public boolean newPortfolio (Portfolio p) {
        return true;
    }
}
