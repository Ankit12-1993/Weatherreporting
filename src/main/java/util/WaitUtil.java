package util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WaitUtil {

	@SuppressWarnings("deprecation")
	public WebElement fluentWait(WebDriver driver, final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);

			}
		});
		return webElement;
	}

	public static String readLocalStorage(WebDriver driver, String key) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", key));
	}

	public static String readSessionStorage(WebDriver driver, String key) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript(String.format("return window.sessionStorage.getItem('%s');", key));
	}

	public <T> boolean waitUntil(WebDriver driver, Function<? super WebDriver, T> function, int timeOutInSeconds) {
		boolean condition = true;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(function);
		} catch (TimeoutException e) {
			condition = false;
		}

		return condition;
	}

	public <T> boolean waitUntil(WebElement webElem, Function<? super WebElement, T> function, int timeOutInSeconds) {
		boolean condition = true;
		try {
			Wait<WebElement> wait = new FluentWait<WebElement>(webElem).withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
					.ignoring(ElementNotVisibleException.class).ignoring(StaleElementReferenceException.class);
			wait.until(function);
		} catch (TimeoutException e) {
			condition = false;
		}
		return condition;
	}

	public WebElement fluentWaitForElementTextLength(WebDriver driver, final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (driver.findElement(locator).getText().length() > 0)
					return driver.findElement(locator);
				return driver.findElement(By.id("invalid"));

			}
		});
		return webElement;
	}

	public static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebElement explicitWaitByVisibilityOfElement(WebDriver driver, int seconds, WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(el));

		return el;
	}

	public static List<WebElement> explicitWaitByVisibilityOfList(WebDriver driver, int seconds,
			List<WebElement> eleList) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOfAllElements(eleList));
		return eleList;
	}

	public static void explicitWaitByVisibilityOfAllElement(WebDriver driver, int seconds, By el) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(el));
	}

	public static void waitForPageLoad(WebDriver driver) throws InterruptedException {
		driver.wait(2000);
	}

	public static WebElement selAndJSClick(WebDriver driver, WebElement ele, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);

		return ele;
	}

	public static WebElement JSMoveClick(WebDriver driver, WebElement ele, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);

		return ele;
	}

	public static WebElement clickable(WebDriver driver, int seconds, WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(el));

		return el;
	}

	public static void waitJSForPageLoad(WebDriver driver, int waitTime) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("falied to wait for page load");
		}

	}

	public static WebElement JSSendKeys(WebDriver driver, WebElement ele, int waitTime, String keyToPass) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();

		ele.sendKeys(keyToPass);
		return ele;
	}

	public static WebElement selMoveToEle(WebDriver driver, WebElement ele, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		return ele;
	}

	public static void selMoveToEleClick(WebDriver driver, WebElement ele, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));

		Actions act = new Actions(driver);
		act.moveToElement(ele).click().perform();
	}

	public static WebElement jsMoveToElement(WebDriver driver, WebElement ele, int waitTime) {
		JavascriptExecutor scpt = (JavascriptExecutor) driver;
		scpt.executeScript("document.getElementById('[insert id]').click();");
		return ele;

	}

	public static boolean urlToChange(WebDriver driver, String urlKeyword, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.urlContains(urlKeyword));
		if (driver.getCurrentUrl().contains(urlKeyword)) {
			return true;
		}
		return false;
	}

	public static void multiScroll(WebDriver driver, int scrollCount) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		for (int i = 0; i < scrollCount; i++) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		}
	}

	public static boolean urlToChange(WebDriver driver, int timeLimit, String keyWord) {
		WebDriverWait wait = new WebDriverWait(driver, timeLimit);

		if (wait.until(ExpectedConditions.urlContains(keyWord))) {
			return true;
		}
		return false;
	}

	public static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

}
