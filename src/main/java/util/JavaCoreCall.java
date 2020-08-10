package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

public class JavaCoreCall 
{

	public static String takeScreenShot(WebDriver driver, String filename, boolean realTimePlatform)
	{
			String destFile = System.getProperty("user.dir") + "/Screenshots/";
			filename = filename + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
			
			destFile = destFile +filename;
			
			try
			{
				File srcFile = null;
				if (driver != null)
					srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				
				FileUtils.copyFile(srcFile, new File(destFile));
			}
			catch (Exception e) 
			{
				System.out.println("Exception in taking screenshot "+e.getMessage());
			}
			 
			if(realTimePlatform)
			{
				destFile = "http://10.42.50.70:8080/job/MsiteBST/ws/Screenshots/"+filename;
			}
			return destFile;
	}
	
	
	public static void cleanDirectory(String dirName) 
	{
		File dirFiles = new File(System.getProperty("user.dir") + "/"+dirName+"/");

		if (dirFiles.exists()) 
		{
			try 
			{
				FileUtils.cleanDirectory(dirFiles);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public static String pageLoadTime(WebDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		double loadTime = (Double) jse.executeScript(
		 "return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
	
		return "Pageload Time :: "+loadTime +" seconds :: "+driver.getCurrentUrl();
			
	}
	
	
	public static HashMap<String , String> sytemDetails(WebDriver driver2, String baseURL)
	{
		 HashMap<String , String> mapConfig = new HashMap<String , String>();
		 Capabilities cap = ((RemoteWebDriver) driver2).getCapabilities();
		 
		 mapConfig.put("Browser ",cap.getBrowserName().toLowerCase());
		 mapConfig.put("Browser Version ",cap.getVersion().toString());
		 mapConfig.put("Operating System ",cap.getPlatform().toString());
		 mapConfig.put("Test Platform or URL ",baseURL);
		 
		return mapConfig;
	} 
	

}