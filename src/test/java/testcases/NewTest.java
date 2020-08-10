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
	public String lat = "28.644800";
	public String lon = "77.216721";
	public String appid = "7fe67bf08c80ded756e598d6f8fedaea";
	public String units = "metric";

	@BeforeMethod(groups = { "WeatherreportingTestcaess" })

	public void configForWebpageLaunch() throws Exception {

		instantiateDriver();

	}

	@Test(groups = { "WeatherreportingTestcaess" }, enabled = true, priority = 1, retryAnalyzer = RetryTest.class)
	public void VerifyHumidity() throws Exception {

		ndtvHomePage.redirectToWeatherPage(driver);
		weatherPage.redirectToWeatherDetails(driver, cityName);
		String humidityValueNDTV = weatherPage.getHumidityValueFromWeatherdetailPage(driver);
		String tempInFarhen = weatherPage.getTemperatureInFarhenhiteWeatherdetailPage(driver);
		Response response = backendServices.openWeatherMapApi(lat, lon, appid, units);
		JSONObject jsonObj = new JSONObject(response.asString());
		JSONObject getSth = jsonObj.getJSONObject("current");

		Object humidityAPIValue = getSth.get("humidity");
		Assert.assertEquals(humidityAPIValue, humidityValueNDTV);

	}

	@Test(groups = { "WeatherreportingTestcaess" }, enabled = true, priority = 2, retryAnalyzer = RetryTest.class)
	public void VerifyTemperature() throws Exception {

		ndtvHomePage.redirectToWeatherPage(driver);
		weatherPage.redirectToWeatherDetails(driver, cityName);

		String tempInDegree = weatherPage.getTemperatureInDegreeWeatherdetailPage(driver);
		String tempInFarhen = weatherPage.getTemperatureInFarhenhiteWeatherdetailPage(driver);
		Response response = backendServices.openWeatherMapApi(lat, lon, appid, units);
		JSONObject jsonObj = new JSONObject(response.asString());
		JSONObject getSth = jsonObj.getJSONObject("current");

		Object tempAPIValue = getSth.get("temp");
		Assert.assertEquals(tempAPIValue, tempInDegree);

	}

	@AfterMethod(groups = { "WeatherreportingTestcaess" })
	public void tearDownTest() throws IOException, InterruptedException {
		// driver.quit();
		Thread.sleep(5000);
		// kill.driverProcessKill();
	}
}
