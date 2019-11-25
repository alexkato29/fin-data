package JSONExamples;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSON {
    public static void main(String[] args) {

        JSONObject row1Info = new JSONObject();
        row1Info.put("Security ID", "FCASH");
        row1Info.put("Security Description", "CASH");
        row1Info.put("Closing Qty", "1,438.06");

        JSONObject row1 = new JSONObject();
        row1.put("row", row1Info);


        JSONObject row2Info = new JSONObject();
        row2Info.put("Security ID", "BOND");
        row2Info.put("Security Description", "PIMCO ETF TR ACTIVE BD ETF");
        row2Info.put("Closing Qty", "33");

        JSONObject row2 = new JSONObject();
        row2.put("row", row2Info);


        JSONArray rows = new JSONArray();
        rows.add(row1);
        rows.add(row2);


        try (FileWriter file = new FileWriter("newJSON.json")) {

            file.write(rows.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
