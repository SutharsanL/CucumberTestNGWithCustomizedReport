package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class FileHandling {

	public static String reportJsonFileLocation="src/test/resources/TestSteps.json";
	public static String propFileLocation="src/main/resources/TestData.properties";
	private static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter("./reports/ExtentReport.html");

	public static String getPropData(String data) {
		String value="";
		try {
		File f=new File(propFileLocation);
		FileInputStream fi=new FileInputStream(f);
		Properties prop=new Properties();
		prop.load(fi);
		value=prop.getProperty(data);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	 public synchronized static ExtentReports getReport() {
	    	extent =new ExtentReports();
	    htmlReporter.loadXMLConfig("./src/main/resources/extent-config.xml", true);
		extent.attachReporter(htmlReporter);
		return extent;
	    }
	 
	 public static String getStepName(String scenario,int index) {
		 String data = null;
		 try {
				JSONParser parser= new JSONParser();
				Object obj=parser.parse(new FileReader(reportJsonFileLocation));
				JSONObject jsonObj=(JSONObject)obj;
				JSONArray scn=(JSONArray)jsonObj.get(scenario);
				 data=scn.get(index-1).toString().split("\"")[3];
				}catch (Exception e) {
					e.printStackTrace();
				}
		 return data;
	 }
}
