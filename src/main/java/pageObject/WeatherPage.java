package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.TestBase;
import util.SeleniumUtil;

public class WeatherPage extends TestBase {

	By weatherInputSearchBox = By.xpath(guiLocatorsConfig.weatherInputSearchBox);
	By weathercitycheckbox = By.xpath(guiLocatorsConfig.weathercitycheckbox);
	By weathersearchreloadbutton = By.xpath(guiLocatorsConfig.weathersearchreloadbutton);
	By weatherClickCity = By.xpath(guiLocatorsConfig.weatherClickCity);

	public void getWeatherDetails(WebDriver driver) throws InterruptedException {

		SeleniumUtil function = new SeleniumUtil(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(weathersearchreloadbutton));
		function.clickButton(weathersearchreloadbutton);
		Thread.sleep(3000);
		function.setTextInTextBox(weatherInputSearchBox, "New delhi");

		// function.clickButton(weathercitycheckbox);
		System.out.println("values is ");

		function.clickButton(weatherClickCity);
	}

}
