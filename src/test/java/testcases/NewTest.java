package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.TestBase;
import pageObject.NDTVHomePage;
import pageObject.WeatherPage;

public class NewTest extends TestBase {

	public NDTVHomePage ndtvHomePage = new NDTVHomePage();
	public WeatherPage weatherPage = new WeatherPage();

	@BeforeClass(groups = { "WeatherreportingTestcaess" })
	public void configForWebpageLaunch() throws Exception {

		instantiateDriver();

	}

	@Test(groups = { "WeatherreportingTestcaess" }, enabled = true, priority = 1)
	public void f() throws InterruptedException {

		ndtvHomePage.redirectToWeatherPage(driver);
		weatherPage.getWeatherDetails(driver);
	}
}
