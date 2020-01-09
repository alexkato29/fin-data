import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class updateData {
    private readData readdata;

    public updateData(readData readdata) {
        this.readdata = readdata;
    }

    public static void main(String[] args) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("newJSON.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray portfolioList = (JSONArray) obj;
            System.out.println(portfolioList);

            System.out.println("x");
            //Iterate over portfolio array
            //portfolioList.forEach( emp -> parsePortfolioObject( (JSONObject) emp ) );


            System.out.println(readData.csvParse("\\data"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parsePortfolioObject(JSONObject portfolio)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) portfolio.get("employee");

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) employeeObject.get("website");
        System.out.println(website);
    }
}
