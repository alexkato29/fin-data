package JSONExamples;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONTest {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("newJSON.json"))
        {
            //Read JSON file
            Object obj = parser.parse(reader);

            JSONArray rows = (JSONArray) obj;
//            System.out.println(rows);


            rows.forEach( (row) -> parseRowObject( (JSONObject) row ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseRowObject(JSONObject row)
    {
        //Get row object within list
        JSONObject rowObject = (JSONObject) row.get("row");
//        System.out.println(rowObject);

        String id = (String) rowObject.get("Security ID");
        System.out.println(id);

        String description = (String) rowObject.get("Security Description");
        System.out.println(description);

        String closingQty = (String) rowObject.get("Closing Qty");
        System.out.println(closingQty);

        System.out.println(" ");
    }
}

