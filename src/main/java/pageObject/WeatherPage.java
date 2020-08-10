package pageObject;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.TestBase;
import util.SeleniumUtil;
import util.WaitUtil;

public class WeatherPage extends TestBase {

	By weatherInputSearchBox = By.xpath(guiLocatorsConfig.weatherInputSearchBox);
	By weathersearchreloadbutton = By.xpath(guiLocatorsConfig.weathersearchreloadbutton);
	By weatherClickCity = By.xpath(guiLocatorsConfig.weatherClickCity);
	By weatherdetailtooltip = By.xpath(guiLocatorsConfig.weatherdetailtooltip);

	public void redirectToWeatherDetails(WebDriver driver, String cityName) throws InterruptedException {

		SeleniumUtil function = new SeleniumUtil(driver);
		WaitUtil waitUtil = new WaitUtil();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(weathersearchreloadbutton));
		function.clickButton(weathersearchreloadbutton);
		waitUtil.sleep(3000);
		function.setTextInTextBox(weatherInputSearchBox, cityName);

		try {
			WebElement weathercitycheckbox = driver.findElement(By.xpath("//input[@id='" + cityName + "']"));

			System.out.println("checkbox value iss " + weathercitycheckbox.isDisplayed());
			if (weathercitycheckbox.isDisplayed()) {
				if (weathercitycheckbox.isSelected()) {
					System.out.println("city already visible in the map");
				} else {
					weathercitycheckbox.click();
				}
			} else {
				System.out.println("city not available on the website");
			}

			function.clickButton(weatherClickCity);
			waitUtil.sleep(3000);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String getHumidityValueFromWeatherdetailPage(WebDriver driver) {

		SeleniumUtil function = new SeleniumUtil(driver);
		WaitUtil waitUtil = new WaitUtil();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(weatherdetailtooltip));

		String toottipText = function.getTextFromPage(weatherdetailtooltip);
		String REGEX = "Humidity: ";
		String REGEX1 = "%";
		Pattern pattern = Pattern.compile(REGEX);
		String[] array = pattern.split(toottipText);
		String firstString = array[1];

		Pattern pattern1 = Pattern.compile(REGEX1);
		String[] arr = pattern1.split(firstString);
		String humidityValue = arr[0];
		return humidityValue;

	}

	public String getTemperatureInDegreeWeatherdetailPage(WebDriver driver) {

		SeleniumUtil function = new SeleniumUtil(driver);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(weatherdetailtooltip));

		String toottipText = function.getTextFromPage(weatherdetailtooltip);
		String REGEX = "Temp in Degrees: ";
		String REGEX1 = "\n";
		Pattern pattern = Pattern.compile(REGEX);
		String[] array = pattern.split(toottipText);
		String firstString = array[1];

		Pattern pattern1 = Pattern.compile(REGEX1);
		String[] arr = pattern1.split(firstString);
		String tempInDegree = arr[0];
		return tempInDegree;

	}

	public String getTemperatureInFarhenhiteWeatherdetailPage(WebDriver driver) {

		SeleniumUtil function = new SeleniumUtil(driver);
		WaitUtil waitUtil = new WaitUtil();

		String toottipText = function.getTextFromPage(weatherdetailtooltip);
		String REGEX = "Temp in Fahrenheit: ";
		Pattern pattern = Pattern.compile(REGEX);
		String[] array = pattern.split(toottipText);
		String firstString = array[1];

		return firstString;

	}

}
