package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	@FindBy(css=".ng-animating")
	WebElement loadingSymbol;
	
	
	By productBy = By.cssSelector(".mb-3");
	By toastMsg = By.cssSelector("#toast-container");
	
	
	
//	Created one action method to get the product list;
	public List<WebElement> getProductList()
	{
		waitForELementToAppear(productBy);  //Waiting for products appear on the page, it is function declared in AbstractComponent class
		return products;
	}
	
//	to get the particular product(For Example "ADIDAS")
	public WebElement getProductByName(String prodName)
	{
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String prodName)
	{
		WebElement finalProduct = getProductByName(prodName);
		finalProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForELementToAppear(toastMsg);
		waitForELementToDisappear(loadingSymbol);
	}
	
	
	
}
