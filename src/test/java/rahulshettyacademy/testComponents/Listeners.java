package rahulshettyacademy.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

	public class Listeners extends BaseTest implements ITestListener						
	{		
		ExtentTest test;
		ExtentReports extent = ExtentReporterNG.geteportObject();
		ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
		 @Override		
		    public void onTestStart(ITestResult result) {									
		        test = extent.createTest(result.getMethod().getMethodName());	
		        extentTest.set(test);
		    }	
		 
		  @Override		
		   	public void onTestSuccess(ITestResult result) {						  
			  extentTest.get().log(Status.PASS, "Test Passed");		
		    }
		  
		  @Override		
		    public void onTestFailure(ITestResult result) {								
		    	extentTest.get().fail(result.getThrowable());	
		    	
		    	
		    	try 
		    	{
					driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
				} 
				catch (Exception e1) {
					
					e1.printStackTrace();
				}
		    	
		    	//take a screenshot
		    	String Filepath = null;
				try {
					Filepath = getScreenshot(result.getMethod().getMethodName(),driver);
				} catch (IOException e) {
					e.printStackTrace();
				}
				extentTest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName()); //if face error add try catch
		    }	
		 
	    @Override		
	    public void onFinish(ITestContext arg0) {					
	       extent.flush();			
	    
	    }		

	    @Override		
	    public void onStart(ITestContext arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    @Override		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	  	

	    @Override		
	    public void onTestSkipped(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		
	}
	
