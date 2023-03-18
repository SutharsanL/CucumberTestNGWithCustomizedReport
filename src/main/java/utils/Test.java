package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		String reportJsonFileLocation="src/test/resources/TestSteps.json";
		JSONParser parser= new JSONParser();
		Object obj=parser.parse(new FileReader(reportJsonFileLocation));
		JSONObject jsonObj=(JSONObject)obj;
		JSONArray scn=(JSONArray)jsonObj.get("Add Item to Cart");
	    String data=scn.get(0).toString().split("\"")[3];
	    System.out.println(data);
		}
	

}
