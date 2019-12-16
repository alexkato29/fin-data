package JSONExamples;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TradeToJSON {
    public static void main(String[] args) {
        //account#
        //registration
        //ticker
        //qty purchased
        //price

        //ADD COMMIT PUSH; pull is just pull

        ArrayList trades = new ArrayList();
        JSONArray jsontrades = new JSONArray();



        String acc = "";
        String reg = "";
        String ticker = "";
        int qtypurchased = 0;
        double price = 0;


        for (int i = 0; i<trades.size(); i++){
            JSONObject tradeobj = new JSONObject();
            JSONObject tradeobjinfo = new JSONObject();

            tradeobjinfo.put("Account Number", acc);
            tradeobjinfo.put("Registration", reg);
            tradeobjinfo.put("Ticker", ticker);
            tradeobjinfo.put("Qty. Purchased", qtypurchased);
            tradeobjinfo.put("Price", price);

            tradeobj.put("row", tradeobjinfo);
            jsontrades.add(tradeobj);


        }





        JSONObject row2Info = new JSONObject();
        row2Info.put("Security ID", "BOND");
        row2Info.put("Security Description", "PIMCO ETF TR ACTIVE BD ETF");
        row2Info.put("Closing Qty", "33");

        JSONObject row2 = new JSONObject();
        row2.put("row", row2Info);
        jsontrades.add(row2);




        try (FileWriter file = new FileWriter("TradeObjectData.json")) {

            file.write(jsontrades.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
