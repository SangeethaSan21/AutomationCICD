package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException
	{
//	String prodName="ADIDAS ORIGINAL";
	Lpage.loginApplication("bhanu@gmail.com", "Simple@1");
	Assert.assertEquals("Incorrect email or password.", Lpage.getErrorMsg());
//	Assert.assertEquals("Incorre email or password.", Lpage.getErrorMsg()); //intentionaly failing bcoz to capture failed tc
}

	@Test
	public void ProductErrorValidation() 
		{
		String prodName="ADIDAS ORIGINAL";
		ProductCatalogue PCatalogue = Lpage.loginApplication("sangeethasan@gmail.com", "Simple@21");
		List<WebElement> products = PCatalogue.getProductList();
		PCatalogue.addProductToCart(prodName);
		CartPage CPage = PCatalogue.clickCartButton();//child class can access parent class as well so we have not use abstract component object.
		Boolean match = CPage.productVerify("ADIDAS ORIGINALLS");
		Assert.assertFalse(match);//Purposely making false because we know it is wrong product name .it is just for validation.
		}
	
}