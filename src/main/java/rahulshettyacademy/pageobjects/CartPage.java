package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

WebDriver driver;
		public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutBtn;
	
	By productTitlesAppear = By.cssSelector(".cartSection h3");
	By checkOutBtnAppear = By.xpath("//button[text()='Checkout']");
	
	public Boolean productVerify(String FinalProduct)
	{
		waitForAllELementToAppear(productTitlesAppear);
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(FinalProduct));
		return match;
	}	
	public CheckOutPage checkOut()
	{
		waitForELementToAppear(checkOutBtnAppear);
		checkOutBtn.click();
		CheckOutPage COPage = new CheckOutPage(driver);
		return COPage;		
	}	
}
