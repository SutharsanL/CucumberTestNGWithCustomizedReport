package base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManagers {
	private static Map<Integer, WebDriver> webDriver = new HashMap<Integer,WebDriver>();
	private static Map<Integer, WebDriverWait> webDriverWait = new HashMap<Integer,WebDriverWait>();
	public static synchronized WebDriver getDriver() {
		try {
			return webDriver.get((int) (long) Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}return null;
	}

	public static synchronized  void setWebDriver(WebDriver driver) {
		webDriver.put((int) (long) Thread.currentThread().getId(), driver);
	}


	public static  void closeDriver() {
		   
		   System.out.println("Calling from Close Driver");
		
		try {

			for (Map.Entry<Integer, WebDriver> entry : webDriver.entrySet()) {
				if (entry.getValue() != null) {
					
					entry.getValue().quit();
					//webDriver.remove(entry.getKey(),entry.getValue());
				}
			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public static  WebDriverWait getWebdriverWait() {
		return webDriverWait.get((int) (long) Thread.currentThread().getId());
	}

	public static  void setWebdriverWait(WebDriverWait obj) {
		webDriverWait.put((int) (long) Thread.currentThread().getId(), obj);
	}
}
