package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Base.TestBase;

public class SeleniumUtil extends TestBase {

	WebDriver driver = null;

	public SeleniumUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyTextOfTargetedElement(By locator, String expectedText) {
		String actualText = driver.findElement(locator).getText().trim();
		Assert.assertEquals(actualText, expectedText);
	}

	public void verifyTextOfTargetedElement(By locator, int expectedMatchingXPathCount) {
		int iCount = 0;
		iCount = driver.findElements(locator).size();
		Assert.assertEquals(iCount, expectedMatchingXPathCount);
	}

	public void setTextInTextBox(By locator, String text) {
		driver.findElement(locator).click();
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}

	public void clickButton(By locator) {
		driver.findElement(locator).click();
	}

	public String getTextFromPage(By locator) {
		String text = driver.findElement(locator).getText();
		return text;
	}

	public String getPageTitle() {
		String title = driver.getCurrentUrl();
		return title;
	}

	public void doubleClickElement(By locator) {
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(locator);
		actions.doubleClick(elementLocator);
	}

	public void mouseHoverElement(By locator) {
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(locator);
		actions.moveToElement(elementLocator).click().build().perform();

	}
}
