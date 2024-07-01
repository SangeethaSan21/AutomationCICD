package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);  //since abstractComponents(parentClass) inheriting the LandingPage(Child class), we are passing the driver using Super(). Where using this we cane send the variable.
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id="userEmail")
	WebElement UserEmail;
	
	@FindBy(id="userPassword")
	WebElement UserPwd;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;

	public ProductCatalogue loginApplication(String email,String pwd)
	{
		UserEmail.sendKeys(email);
		UserPwd.sendKeys(pwd);
		submit.click();
		ProductCatalogue PCatalogue = new ProductCatalogue(driver);
		return PCatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMsg()
	{
		waitForWebELementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	
}
