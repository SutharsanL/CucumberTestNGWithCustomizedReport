package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;
import base.WebDriverManagers;

public class FlipkartPage extends BaseClass{
WebDriver driver;
	public FlipkartPage(WebDriver driver) {
		driver=WebDriverManagers.getDriver();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='✕']")
	private WebElement popUP;
	
	@FindBy(name="q")
	private WebElement searchBar;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement searchButton;
	
	@FindBy(xpath="//div[contains(text(),'iPhone 14')]")
	private WebElement product;
	
	@FindBy(xpath="//button[text()='Add to cart']")
	private WebElement addToCart;
	
	@FindBy(xpath="//span[text()='Cart']/ancestor::a")
	private WebElement cartIcon;
	
	@FindBy(xpath="//div[text()='Flipkart (1)']")
	private WebElement cartValue;
	
	@FindBy(xpath="//div[text()='Remove']")
	private WebElement removeButton;
	
	@FindBy(xpath="//div[text()='Cancel']/following-sibling::div[text()='Remove']")
	private WebElement removeConfirm;
	
	@FindBy(xpath="//div[text()='Missing Cart items?']")
	private WebElement validateEmptyCart;
	
	public void closePopUp() throws Exception {
		Assert.assertEquals(true, clickOnElement(popUP, true));
	}
	
	public void searchProduct(String product) throws Exception {
		typeOnElement(searchBar, product, true);
		clickOnElement(searchButton, true);
		
	}
	
	public void clickFirstProduct() throws Exception {
		String productName= getTextFromElement(product, true);
		clickOnElement(product, true);
		switchTab();
		//Assert.assertEquals(productName, driver.getTitle());
		
	}
	
	public void clickAddtoCart() throws Exception {
		clickOnElement(addToCart, true);
	}
	
	public void clickCartIcon() throws Exception {
		clickOnElement(cartIcon, true);
		
	}
	
	public void validateCart() throws Exception {
		Assert.assertEquals(true, waitForElementVisible(cartValue, true));
	}
	
	public void removeFromCart() throws Exception {
		clickOnElement(removeButton, true);
		clickOnElement(removeConfirm, true);
		Assert.assertEquals(true, waitForElementVisible(validateEmptyCart, true));
	}
	
}
