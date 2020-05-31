package walshIntegrated.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import walshIntegrated.factories.DataProviderFactory;
import walshIntegrated.helper.Utility;

public class LoginWalshPage {

	WebDriver driver;

	public LoginWalshPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	// Login Locators
	By email = By.name("email");

	By password = By.name("password");

	By loginButton = By.id("button-signin");

	@FindAll({ @FindBy(xpath = "//a[@href]") })
	public List<WebElement> loginLinks;

	public void verifyUrlBeforeLogin() {
		Assert.assertTrue(driver.getCurrentUrl().contains("juno"), "Login Page does not contain juno in url");
	}

	public void verifyUrlAfterLogin() {
		Utility.wait(2);

		Assert.assertTrue(driver.getCurrentUrl().contains("role"), "Select Role screen  does not contain role keyword");
	}

	public void clickOnLoginButton() {
		Utility.waitForWebElement(driver, loginButton).click();
	}

	public void loginToApplication(String userName, String passWord) {
		Utility.waitForWebElement(driver, email).sendKeys(userName);
		Utility.waitForWebElement(driver, password).sendKeys(passWord);
		Utility.waitForWebElement(driver, loginButton).click();
	}

	public static void verifyLinkActive(String linkUrl) {
		try {
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println(linkUrl);

		}

	}

	public void verifyLinks() {

		for (int i = 0; i < loginLinks.size(); i++) {

			WebElement ele = loginLinks.get(i);

			String url = ele.getAttribute("href");

			verifyLinkActive(url);

		}
	}

}
