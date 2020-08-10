package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.TestBase;
import util.SeleniumUtil;

public class NDTVHomePage extends TestBase {

	By ndtvmenu = By.xpath(guiLocatorsConfig.ndtvMenu);
	By ndtvWeatherLink = By.xpath(guiLocatorsConfig.ndtvWeatherLink);

	public void redirectToWeatherPage(WebDriver driver) {
		SeleniumUtil function = new SeleniumUtil(driver);

		function.clickButton(ndtvmenu);
		function.clickButton(ndtvWeatherLink);

	}
}
