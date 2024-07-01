package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;

public class OrderSubmission extends BaseTest {
	String prodName="ADIDAS ORIGINAL";
		
		@Test(dataProvider="getData" ,groups = {"purchase"})
		public void SubmitOrder(HashMap<String,String> input) throws IOException
		{
		
		//created a Landing Page object in BaseTest class and we are calling launch application method here using object of Landing page
			//		LandingPage Lpage = launchApplication(); 
			//		why we are not using above line because we have used @BeforeMethod in BaseTest class for launchApplication method.but we Lpage object error here so
			//		we create lPage object globally using public in basetest.
		
		ProductCatalogue PCatalogue = Lpage.loginApplication(input.get("email"), input.get("pwd"));
		
		//		ProductCatalogue PCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = PCatalogue.getProductList();
		PCatalogue.addProductToCart(input.get("prodName"));
		CartPage CPage = PCatalogue.clickCartButton();//child class can access parent class as well so we have not use abstract component object.
		
		//		CartPage CPage = new CartPage(driver);
		Boolean match = CPage.productVerify(input.get("prodName"));
		Assert.assertTrue(match);
		CheckOutPage COPage = CPage.checkOut();
		
		//		CheckOutPage COPage = new CheckOutPage(driver);
		COPage.SelectCountry("India");
		ConfirmationPage ConfPage =COPage.SubmitOrder();
		
		// 		ConfirmationPage ConfPage = new ConfirmationPage(driver);		
		String ConfirmMsg = ConfPage.getConfirmMsg();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
		
//To verify ADIDAS ORIGINAL is displaying in orders Page
		
		@Test(dependsOnMethods = {"SubmitOrder"}) //why here this method given bcoz first the order should be placed then only we can check in orders page.
		public void OrderHistoryTest()
		{
			ProductCatalogue PCatalogue = Lpage.loginApplication("bhanusan@gmail.com", "Simple@1");
			OrderPage orderPage = PCatalogue.clickOrderButton();
			Assert.assertTrue(orderPage.VerifyOrderDisplay(prodName)); //I was getting error in prodname because i declared prodname in methods level so i was not able to access
//													// so i declared in class level 
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
List<HashMap<String,String>> data = getJsonDataToMap("H:\\Eclipse\\SeleniumframeworkDesign\\src\\main\\java\\rahulshettyacademy\\data\\purchaseOrder.json");
			
		return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
		
		
}		
		
//		HashMap<Object,Object> map = new HashMap<Object,Object>();
//		map.put("email", "bhanusan@gmail.com");
//		map.put("pwd", "Simple@1");
//		map.put("prodName", "ADIDAS ORIGINAL");
//		
//		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//		map1.put("email", "sangeethasan@gmail.com");
//		map1.put("pwd", "Simple@21");
//		map1.put("prodName", "ZARA COAT 3");		
		
		
// another way of executing		
//		@DataProvider
//		public Object[][] getData()
//		{
//			return new Object[][] = {{"bhanusan@gmail.com","Simple@1","ADIDAS ORIGINAL"},{"sangeethasan@gmail.com","Simple@21","ZARA COAT 3"}};
//		}





