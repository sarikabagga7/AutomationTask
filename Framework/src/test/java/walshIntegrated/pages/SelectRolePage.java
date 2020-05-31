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

public class SelectRolePage {

	WebDriver driver;

	public SelectRolePage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	// Select Role

	By selectRole = By.xpath("//button[text()='Account Administrator']");

	@FindAll({ @FindBy(xpath = "//a[@href]") })
	public List<WebElement> loginLinks;

	public void verifyUrlAfterSelectRole() {

		Assert.assertTrue(driver.getCurrentUrl().contains("role"), "Select Role screen does not contain role in url");
	}

	public void selectRoleAsAccountAdmin() {

		Utility.waitForWebElement(driver, selectRole).click();
	}

}
