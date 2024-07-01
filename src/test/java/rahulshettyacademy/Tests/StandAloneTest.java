package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String prodName="ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("bhanusan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Simple@1");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));
		
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
//		ele.stream().map(WebElement::getText).forEach(System.out::println);
		
//		WebElement product = ele.stream().filter(prod->prod.findElement(By.xpath("//div[@class='card-body']//b")).getText().equals(prodName)).findFirst().orElse(null);
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
	
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection h3")));
//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//		cartProducts.stream().filter(cartProduct->cartProduct.getText().equalsIgnoreCase(prodName));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(prodName));
		Assert.assertTrue(match);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Checkout']"))).click();
//		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		WebElement text = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(text, "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		String FinalText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();
		Assert.assertTrue(FinalText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(FinalText);		
		
	driver.close();
	}

}

/*		WebElement prod = null;
for (WebElement product : ele)
{
String productNameText = product.getText();
if (productNameText.equals(prodName))
{
prod = product; 
break;
}
}
if (prod != null) {
prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
}
else
{
System.out.println("Product not found: " + prodName);
}
*/