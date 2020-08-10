package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Base.TestBase;
import util.SeleniumUtil;
import util.WaitUtil;

public class NDTVHomePage extends TestBase {

	By ndtvmenu = By.xpath(guiLocatorsConfig.ndtvMenu);
	By ndtvWeatherLink = By.xpath(guiLocatorsConfig.ndtvWeatherLink);

	public void redirectToWeatherPage(WebDriver driver) throws InterruptedException {
		SeleniumUtil function = new SeleniumUtil(driver);
		WaitUtil waitUtil = new WaitUtil();
		waitUtil.waitForPageLoad(driver);
		function.clickButton(ndtvmenu);
		function.clickButton(ndtvWeatherLink);

	}
}
