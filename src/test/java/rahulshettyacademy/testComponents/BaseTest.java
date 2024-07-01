package rahulshettyacademy.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest  {
	public WebDriver driver;
	public LandingPage Lpage;
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("H:\\Eclipse\\SeleniumframeworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");
		
//		if(browserName.contains("Chrome"))
//		{
//			ChromeOptions options = new ChromeOptions();
//			if(browserName.contains("headless"))
//			{
//				options.addArguments("headless");					
//				
//			}
//			driver = new ChromeDriver(options);
//			driver.manage().window().fullscreen();
//		}
		if (browserName.contains("headless")) {	
					
	                ChromeOptions options = new ChromeOptions();	
			options.addArguments("--headless");	
			driver = new ChromeDriver(options);
	driver.manage().window().setSize(new Dimension(1440,990));      
//	  driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 990));		
	}
	 
	else if(browserName.equalsIgnoreCase("chrome")) {
					
			driver = new ChromeDriver();		
	}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
//			System.getProperty("webdriver.geck.driver", "H:\\geckodriver-v0.34.0-win-aarch64");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("internetExplorer"))
		{
			//internetExplorer code
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;//once the browser is opened and ready it is returned.
	}	
	
	//Launch the application
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		Lpage = new LandingPage(driver);
		Lpage.goTo();
		return Lpage;	
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeDriver()
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException 
	{
		//read json to string
//		String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//main\\java\\rahulshettyacademy\\data\\purchaseOrder.json"));
		String JsonContent = FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
		
		//Convert string Hashmap usinf Jackson databind		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent,new TypeReference <List<HashMap<String,String>>>(){
			
		});
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{	
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\" +testCaseName + ".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"\\reports\\" +testCaseName + ".png";
	}
}
