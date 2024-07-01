package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	WebDriver driver;	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement CountriesList;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement placeOrder;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By countryResult = By.cssSelector(".ta-results");
	
	public void SelectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(CountriesList, countryName).build().perform();
		waitForELementToAppear(countryResult);
		selectCountry.click();		
	}	
	public ConfirmationPage SubmitOrder()
	{
		placeOrder.click();
		ConfirmationPage ConfPage = new ConfirmationPage(driver);	
		return ConfPage;
	}
}
