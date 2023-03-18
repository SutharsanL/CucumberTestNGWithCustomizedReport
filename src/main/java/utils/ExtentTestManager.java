package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import base.WebDriverManagers;



public class ExtentTestManager {
	public static ExtentReports extent=FileHandling.getReport();
	 public static Map<Integer,ExtentTest> extentTestMap = new HashMap<Integer,ExtentTest>();
	  public static Map<Integer,ExtentTest> extentParentTestMap = new HashMap<Integer,ExtentTest>();
	  public static Map<Integer,ExtentTest> extentChildMapTestMap = new HashMap<Integer,ExtentTest>();
	  public static Map<Integer,ExtentTest> extentInnerChildMapTestMap = new HashMap<Integer,ExtentTest>();
	    public static Map<String, Integer> reportStatus = new HashMap<String, Integer>();
         
	    public static synchronized ExtentTest getTest() throws Exception {
	    	ExtentTest test = null;
	    	try {
				 test=(ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
	    	return test;
	    }
	    public static synchronized ExtentTest getParentTest() {
	    	ExtentTest test = null;
	    	try {
				test= (ExtentTest)extentParentTestMap.get((int)(long)Thread.currentThread().getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//	LOGGER.log(Level.ALL, e.getLocalizedMessage());
				e.printStackTrace();
			}
	    	return test;
	    }
	    
	    public static synchronized ExtentTest getChildTest() {
	    	ExtentTest test = null;
	    	try {
				return (ExtentTest)extentChildMapTestMap.get((int)(long)Thread.currentThread().getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return test;
	    }
	    
	    public static synchronized ExtentTest getInnerChildTest() {
	    	ExtentTest test = null;
	    	try {
				test= (ExtentTest)extentInnerChildMapTestMap.get((int)(long)Thread.currentThread().getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//	LOGGER.log(Level.ALL, e.getLocalizedMessage());
				e.printStackTrace();
			}
	    	return test;
	    }
	 
	    public static synchronized void endTest() {
	        try {
				extent.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	    }
	 
	    public static synchronized ExtentTest startTest(String testName) {
	        ExtentTest test = null;
			try {
				test = extent.createTest(testName);
				extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
				extentParentTestMap.put((int) (long)Thread.currentThread().getId(), test);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return test;
	    }
	    public static synchronized ExtentTest startChildTest(String testName) {
	    	ExtentTest childTest=null;
	    	try {
			 childTest=getParentTest().createNode("<b>"+testName+"</b>");
			   extentTestMap.put((int) (long) (Thread.currentThread().getId()), childTest);
			   extentChildMapTestMap.put((int) (long) (Thread.currentThread().getId()), childTest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return childTest;
	    }
	    public static synchronized ExtentTest startInnerChildTest(String testName) {
	    	ExtentTest innerChildTest=null;
	    	try {
	    		innerChildTest=getChildTest().createNode("<b>"+testName+"</b>");
			   extentTestMap.put((int) (long) (Thread.currentThread().getId()), innerChildTest);
			   extentInnerChildMapTestMap.put((int) (long) (Thread.currentThread().getId()), innerChildTest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return innerChildTest;
	    }
	    public static synchronized void endChildTest() {
		       try {
				extentTestMap.put((int) (long) (Thread.currentThread().getId()), getParentTest());
				endTest();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
	    
	    public static synchronized void endInnerChildTest() {
		       try {
				extentTestMap.put((int) (long) (Thread.currentThread().getId()), getChildTest());
				endTest();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
	    
	    public static String captureScreen(WebDriver driver, String imagePath) {
			 try {
				 System.out.println("AShot "+driver.toString());
		     ImageIO.write(Shutterbug.shootPage(driver,ScrollStrategy.VIEWPORT_ONLY,500,true).withName("FullPageScreenshot").getImage(),"PNG",new File(imagePath));

		    } 
		    catch (IOException e) {
		    	System.out.println(e.getMessage());
		    }
		    	return imagePath;
		}
	    
	    public static String addScreenShot(WebDriver d, String imgpath) throws Exception {
			String image = "";
			FileInputStream imageFile;
			try {
				File imgfile = new File(captureScreen(WebDriverManagers.getDriver(),imgpath));
				imageFile = new FileInputStream(imgpath);
	            byte imageData[] = new byte[(int) imgfile.length()];
	            imageFile.read(imageData);
	            byte[] base64EncodedByteArray = org.apache.commons.codec.binary.Base64.encodeBase64(imageData);
	            image = new String(base64EncodedByteArray);
		    }
			catch(Exception e) {
				e.printStackTrace();
			}
				return "data:image/png;base64,"+image;
		}
	    
	    public static synchronized void logEventToReport(WebDriver d, String status, Object element, String description) throws Exception {
			try {
				//element = byPassWebElement(element);
				if(status.equalsIgnoreCase("pass")) {
					ExtentTestManager.getTest().log(Status.PASS, "<b>["+element+"] - <span style='color:green'>"+StringUtils.capitalize(description)+"</span></b>"+Thread.currentThread().getId());
				}
				else if(status.equalsIgnoreCase("fail")) {
					ExtentTestManager.getTest().log(Status.FAIL, "<b>["+element+"] - <span style='color:red'>"+StringUtils.capitalize(description)+"</span></b>"+Thread.currentThread().getId());
					ExtentTestManager.getTest().addScreenCaptureFromBase64String(addScreenShot(WebDriverManagers.getDriver(), "./image"+Thread.currentThread().getId()+".png"), description);
				}
				else if(status.equalsIgnoreCase("error")) {
					ExtentTestManager.getTest().log(Status.ERROR, description);
				}
			} catch (Exception e) {
				System.out.println("error block report");
				if(status.equalsIgnoreCase("fail")||status.equalsIgnoreCase("error")) {
				ExtentTestManager.getTest().log(Status.FAIL, "Log expection block : "+ "Object Name :" +element +"Description : "+description);
				ExtentTestManager.getTest().addScreenCaptureFromBase64String(addScreenShot(WebDriverManagers.getDriver(), "./image"+Thread.currentThread().getId()+".png"), description);
				}else {
					ExtentTestManager.getTest().log(Status.INFO, "Log expection block : "+ "Object Name :" +element +"Description : "+description);
					ExtentTestManager.getTest().log(Status.INFO, element.toString());
				}
				e.printStackTrace();
			}
		}
	    
	    
}
