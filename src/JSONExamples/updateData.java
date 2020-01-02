package JSONExamples;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class updateData {
    public static void main(String[] args) {

        // read from resource file
        JSONObject value;
        try (Reader in = new InputStreamReader(getClass().getResourceAsStream("/simple.json"))) {
            JSONParser parser = new JSONParser();
            value = (JSONObject) parser.parse(in);
        }
        JSONObject entity = (JSONObject) value.get("entity");

        // update id
        entity.put("id", "manipulated");

        // write to output file
        try (Writer out = new FileWriter("output.json")) {
            out.write(value.toJSONString());
        }
    }
}

