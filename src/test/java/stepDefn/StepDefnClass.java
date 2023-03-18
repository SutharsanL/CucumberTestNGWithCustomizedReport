package stepDefn;

import base.BaseClass;
import base.WebDriverManagers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FlipkartPage;
import utils.FileHandling;

public class StepDefnClass extends BaseClass {
	FlipkartPage flipkart;
	Hooks hooks;

	public StepDefnClass(Hooks hook) {
		this.hooks = hooks;
		objectCreator();
	}

	public void objectCreator() {
		flipkart = new FlipkartPage(getDriver());
	}

	@Given("Launch Flipkart Shopping Application")
	public void launch_flipkart_shopping_application() {
		try {
			launchUrl(FileHandling.getPropData("url"));
			flipkart.closePopUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("Search {string} in search bar")
	public void search_in_search_bar(String string) {
		try {
			flipkart.searchProduct(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("Click the first mobile on the list")
	public void click_the_first_mobile_on_the_list() {
		try {
			flipkart.clickFirstProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("Click on Add to Cart button")
	public void click_on_add_to_cart_button() {
		try {
			flipkart.clickAddtoCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("Click Cart Icon")
	public void click_cart_icon() {
		try {
			flipkart.clickCartIcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("I validate the the Item in the cart")
	public void i_validate_the_the_item_in_the_cart() {
		try {
			flipkart.validateCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("Remove Item from the Cart")
	public void remove_item_from_the_cart() {
		try {
			flipkart.removeFromCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
