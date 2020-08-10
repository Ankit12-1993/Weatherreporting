package Base;

import com.google.common.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

import config.GUILocatorsConfig;
import config.PropertiesConfig;
import util.TestUtil;
import util.WebEventListener;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({ "unused", "static-access" })
public class TestBase {

	public static WebDriver driver = null;

	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public GUILocatorsConfig guiLocatorsConfig = new GUILocatorsConfig();
	public PropertiesConfig propertiesConfig = new PropertiesConfig();
	

	public TestBase() {
		propertiesConfig.populateConfiguration();
		guiLocatorsConfig.populateGUILocators();
	}

	public void instantiateDriver() {
		String browserName = propertiesConfig.browser;

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:/pro/New folder/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "E:/pro/geckodriver");
			driver = new FirefoxDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(propertiesConfig.URL);
		

	}

	public static RequestSpecification request() {
		return RestAssured.given().urlEncodingEnabled(false);
	}

}
