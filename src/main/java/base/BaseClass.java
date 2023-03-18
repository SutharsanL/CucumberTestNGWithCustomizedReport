package base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import utils.ExtentTestManager;

public class BaseClass extends WebDriverManagers{
	
	public boolean waitForElementVisible(WebElement element, boolean logFlag) throws Exception   {
		boolean elementVisible = false;
		try {
			WebDriverManagers.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			elementVisible = true;
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Pass", element, "Visible");
			}
	} catch (StaleElementReferenceException e) {
		waitForElementVisible(element, logFlag);
	} catch (Exception e) {
		if(logFlag==true) {
			ExtentTestManager.logEventToReport(getDriver(), "Fail", element, "Not Visible");
		}
		e.printStackTrace();
	} 
	return elementVisible;
}
	public boolean clickOnElement(WebElement element, boolean logFlag) throws Exception{
		boolean flag = false;
		try {
		
				if (waitForElementVisible(element, logFlag)) {
					element.click();
					if(logFlag==true) {
						ExtentTestManager.logEventToReport(getDriver(), "Pass", element, "Clicked");
					}
					flag = true;
				}
		} 
			  catch (ElementClickInterceptedException e) {
				  JavascriptExecutor js = ((JavascriptExecutor) WebDriverManagers.getDriver());
					js.executeScript(
							"window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
					js.executeScript("arguments[0].click();", element);
				    flag =true;
			  }
			 
		catch (Exception e) {
			if(logFlag==true) {
				
					ExtentTestManager.logEventToReport(getDriver(), "Fail", element, "Failed to Click");
			
			}
			e.printStackTrace();
		
	}
		return flag;
	}
	
	public boolean scrollInto(WebElement element, boolean logFlag) throws Exception {
		boolean flag = false;
		try {
			((JavascriptExecutor) WebDriverManagers.getDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded();", element);
			
			flag = true;
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Pass", element, "Scrolled");
			}
		} catch (Exception e) {
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Fail", element, "Not Scrolled");
			}
			e.printStackTrace();
			
		}
		return flag;
	}
	
	public boolean selectByVisibleText(WebElement element, String value, boolean logFlag) throws Exception   {
		boolean flag = false;
		try {

			waitForElementVisible(element, logFlag);
			Select select = new Select(element);
			select.selectByVisibleText(value);
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Pass", element, "Selected");
			}
			flag = true;
		} catch (Exception e) {
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Fail", element, "Not Selected");
			}
			e.printStackTrace();
		}
		return flag;
	}
	public boolean typeOnElement(WebElement element, Object text, boolean logFlag) throws Exception   {
		boolean flag = false;
		try {
			
				if (waitForElementVisible(element, logFlag)) {
					element.clear();
					element.sendKeys(text.toString());
					if(logFlag==true) {
						ExtentTestManager.logEventToReport(getDriver(), "Pass", element, "Entered Text");
					}
					flag = true;
				
			}
		} catch (Exception e) {
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Fail", element, "Text Failed");
			}
			e.printStackTrace();
			
		} 
		
		
		return flag;
	}
	protected String getTextFromElement(WebElement element, boolean logFlag) throws Exception   {
		String text = "";
		try {

			if (waitForElementVisible(element, logFlag))
				
			text = element.getText();
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Pass", element, "Get Text");
			}
		} catch (Exception e) {
			if(logFlag==true) {
				ExtentTestManager.logEventToReport(getDriver(), "Fail", element, "Unable to get text");
			}
			e.printStackTrace();
		}
		return text;
	}
	
	protected boolean refreshPage()   {

		boolean flag = false;
		try {
			WebDriverManagers.getDriver().navigate().refresh();
			flag = true;
		} catch (Exception e) {
		e.printStackTrace();	
		}
		return flag;
	}
	
	public boolean launchUrl(String url) {
		boolean flag=false;
		try {
			WebDriverManagers.getDriver().get(url);
			flag=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean switchTab() {
		boolean flag=false;
		try {
			List<String> browserTabs = new ArrayList<String>(WebDriverManagers.getDriver().getWindowHandles());
			if (browserTabs.size() > 1) {
				  WebDriverManagers.getDriver().switchTo().window(browserTabs.get(1));
				  flag=true;
			}else {
				System.out.println("Only one Tab found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
		}

