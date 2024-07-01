package rahulshettyacademy.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;

public class StepdefinitionImpl extends BaseTest{
	public ConfirmationPage ConfPage;
	public LandingPage Lpage;
	public ProductCatalogue PCatalogue;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		Lpage = launchApplication();
	}
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void I_logged_with_UN_and_PWD(String username,String password)
	{
		
		PCatalogue = Lpage.loginApplication(username,password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void I_add_to_cart(String Productname)
	{
		List<WebElement> products = PCatalogue.getProductList();
		PCatalogue.addProductToCart(Productname);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String Productname)
	{
		CartPage CPage = PCatalogue.clickCartButton();
		Boolean match = CPage.productVerify(Productname);
		Assert.assertTrue(match);
		CheckOutPage COPage = CPage.checkOut();
		COPage.SelectCountry("India");
		ConfPage =COPage.SubmitOrder();
	}
	@Then("{string} message is displayed on Confirmationpage.")
	public void confirmation_msg_displayed(String string)
	{
		String ConfirmMsg = ConfPage.getConfirmMsg();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_msg_displayed(String string)
	{
		Assert.assertEquals(string, Lpage.getErrorMsg());
		driver.close();
	}
}

