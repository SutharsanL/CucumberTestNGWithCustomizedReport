package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import base.WebDriverManagers;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.FileHandling;

@CucumberOptions(
		features="src/test/resources/features",
		glue="stepDefn",
		monochrome = true
	//	tags=""
		)

public class RunnerClass extends AbstractTestNGCucumberTests{
	
	 
	  @DataProvider (parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	  }
	  





}
