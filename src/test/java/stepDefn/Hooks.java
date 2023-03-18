package stepDefn;

import org.hamcrest.Condition.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentTestManager;

import base.BaseClass;
import base.WebDriverManagers;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.FileHandling;

public class Hooks extends BaseClass{
	 WebDriver driver;
	 int lineNum;
@Before
	public void initSetup(Scenario scenario) {
		try {
			lineNum=1;
		if(FileHandling.getPropData("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
			
		} else if(FileHandling.getPropData("browser").equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
		}
		driver.manage().window().maximize();
		WebDriverManagers.setWebDriver(driver);
		WebDriverManagers.setWebdriverWait(new WebDriverWait(driver,30));
		ExtentTestManager.startTest(scenario.getName());
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	}
@BeforeStep
public void preStep(Scenario scenario) {
	ExtentTestManager.startChildTest(FileHandling.getStepName(scenario.getName(), lineNum));
System.out.println("line: "+FileHandling.getStepName(scenario.getName(), lineNum));
	
}

@AfterStep
public void afterStep() {
	ExtentTestManager.endChildTest();
	lineNum++;

}
	@After
	public void testClosure() {
		ExtentTestManager.endTest();
		WebDriverManagers.getDriver().quit();
	}
	
}