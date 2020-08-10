package testcases;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import Base.TestBase;
import pageObject.NDTVHomePage;
import pageObject.WeatherPage;
import services.BackendServices;
import util.RetryTest;

public class NewTest extends TestBase {

	public NDTVHomePage ndtvHomePage = new NDTVHomePage();
	public WeatherPage weatherPage = new WeatherPage();
	public BackendServices backendServices = new BackendServices();

	public String cityName = "New Delhi";

	@BeforeMethod(groups = { "WeatherreportingTestcaess" })
	public void configForWebpageLaunch() throws Exception {

		instantiateDriver();

	}

	@Test(groups = { "WeatherreportingTestcaess" }, enabled = true, priority = 1, retryAnalyzer = RetryTest.class)
	public void f() throws Exception {

		Response response = backendServices.openWeatherMapApi();
		JSONObject jsonObj = new JSONObject(response.asString());
		JSONObject getSth = jsonObj.getJSONObject("current");

		ndtvHomePage.redirectToWeatherPage(driver);
		weatherPage.redirectToWeatherDetails(driver, cityName);
		String humidityValueNDTV = weatherPage.getHumidityValueFromWeatherdetailPage(driver);
		String tempInDegree = weatherPage.getTemperatureInDegreeWeatherdetailPage(driver);
		String tempInFarhen = weatherPage.getTemperatureInFarhenhiteWeatherdetailPage(driver);

		Object level = getSth.get("humidity");
		System.out.println("json object inside value is " + level);

		Assert.assertEquals(level, humidityValueNDTV);

	}

	@AfterMethod(groups = { "WeatherreportingTestcaess" })
	public void tearDownTest() throws IOException, InterruptedException {
		 driver.quit();
		Thread.sleep(5000);
		// kill.driverProcessKill();
	}
}
