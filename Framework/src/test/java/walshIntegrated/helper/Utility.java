package walshIntegrated.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utility {

	public static void verifyPartialText(WebDriver driver, By byLocator, String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, 15);

		WebElement element = waitForWebElement(driver, byLocator);

		boolean status = wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));

		Assert.assertTrue(status);

	}

	public static void verifyText(WebDriver driver, By byLocator, String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, 15);

		boolean status = wait.until(ExpectedConditions.textToBe(byLocator, expectedText));

		Assert.assertTrue(status);
;
	}

	public static String getScreenshot(WebDriver driver) {
		String path = System.getProperty("user.dir") + "/Screenshots/Screenshot_" + getCurrentTime() + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot");
		}
		return path;
	}

	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("ss_mm_HH_dd_MM_yyyy");

		Date date = new Date();

		return dateFormat.format(date);

	}

	public static void acceptAlert(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent()).accept();
	}

	public static void dismissAlert(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
	}

	public static void verifyAlertText(WebDriver driver, String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String alert_text_actual = wait.until(ExpectedConditions.alertIsPresent()).getText();
		Assert.assertEquals(alert_text_actual, expectedText);
	}

	public static void verifyAlertPartially(WebDriver driver, String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String alert_text_actual = wait.until(ExpectedConditions.alertIsPresent()).getText();
		Assert.assertTrue(alert_text_actual.contains(expectedText));
	}

	// This method will handle sync issue- It will wait for webelement and then
	// highlight the same
	public static WebElement waitForWebElement(WebDriver driver, By byLocator) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		highLightElement(driver, element);

		return element;
	}

	public static WebElement waitForWebElement(WebDriver driver, By byLocator, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		highLightElement(driver, element);

		return element;
	}

	public static WebElement waitForWebElement(WebDriver driver, WebElement webElement, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
		highLightElement(driver, element);

		return element;
	}

	public static void verifyTitle(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Assert.assertTrue(wait.until(ExpectedConditions.titleIs(title)));
	}

	public static void verifyContainsTitle(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Assert.assertTrue(wait.until(ExpectedConditions.titleContains(title)));
	}

	public static void verifyURL(WebDriver driver, String url) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(url)));
	}

	public static void verifyURLContains(WebDriver driver, String url) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Assert.assertTrue(wait.until(ExpectedConditions.urlContains(url)));
	}

	public static void navigateToURL(WebDriver driver, String url) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.navigate().to(url);
		Assert.assertTrue(wait.until(ExpectedConditions.urlContains(url)));

	}

	public static void wait(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {

		}
	}

	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

	}

}
