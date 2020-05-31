package walshIntegrated.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import walshIntegrated.factories.DataProviderFactory;
import walshIntegrated.helper.Utility;

public class DepartmentsPage {

	WebDriver driver;

	public DepartmentsPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	// Setup

	By SetUpLink = By.xpath("//a[@title='Setup']");

	By DepartmentsLink = By.xpath("//a[@href='#/departments']");

	By AddDepartmentButton = By.xpath("//button[@type='submit']");

	By AddDepartmentName = By.xpath("//ng2-completer[@name='Names']/div/input");

	By AddDepartmentDesc = By.xpath("//textarea[@name='Description']");

	By AddDepartmentStatus = By.id("Status");

	By AddDeprtmentSaveBtn = By.xpath("//button[contains(text(),'Save')]");

	By UpdateDepartmentLink = By.xpath("//td[text()='Safety_peerjiAdd']/preceding::a[3]");

	@FindAll({ @FindBy(xpath = "//*[@role='grid']/tbody/tr/td[3]") })
	public List<WebElement> RowDepartmentsName;

	public void verifyUrlAfterNavigateToDept() {
		Assert.assertTrue(driver.getCurrentUrl().contains("department"),
				"Department Page does not contain department url");
	}

	public void navigateToDepartmentsPage() {

		Utility.navigateToURL(driver, DataProviderFactory.getConfig().getValue("DeptPage"));

		// Utility.waitForWebElement(driver, SetUpLink).click();

		// Actions actions = new Actions(driver);

		// actions.moveToElement(DepartmentsLink).click().perform();

		// Utility.waitForWebElement(driver, DepartmentsLink).click();
	}

	public void AddDepartmentsPage() {

		Utility.waitForWebElement(driver, AddDepartmentButton).click();

		Utility.waitForWebElement(driver, AddDepartmentName).sendKeys("Safety_peerjiAdd");

		Utility.waitForWebElement(driver, AddDepartmentDesc).sendKeys("Safety_peerjiAdd");

		WebElement ele = Utility.waitForWebElement(driver, AddDepartmentStatus);

		Select status = new Select(ele);

		status.selectByValue("Active");

		Utility.waitForWebElement(driver, AddDeprtmentSaveBtn).click();

	}

	public void verifyDeparment(String NewDeptName) {
		Utility.wait(7);

		System.out.println("Test: - " + RowDepartmentsName.size());

		boolean exist = false;
		for (int i = 0; i < RowDepartmentsName.size(); i++) {

			WebElement ele = RowDepartmentsName.get(i);

			String deptName = ele.getText();

			// assert
			System.out.println(deptName);

			if (deptName.equals(NewDeptName)) {
				exist = true;
				System.out.println("Dept Found-" + NewDeptName);
				break;
			} else {
				exist = false;
			}

		}
		System.out.println("Exist Value" + exist);

		if (exist == true) {
			assert true;
		}

		else
			assert false;

	}

	public void UpdateDepartmentsPage() {

		Utility.waitForWebElement(driver, UpdateDepartmentLink).click();

		WebElement UpdateDeptName = Utility.waitForWebElement(driver, AddDepartmentName);

		UpdateDeptName.clear();

		UpdateDeptName.sendKeys("Safety_peerjiNew");

		WebElement UpdateDeptDesc = Utility.waitForWebElement(driver, AddDepartmentDesc);

		UpdateDeptDesc.clear();

		UpdateDeptDesc.sendKeys("Safety_peerjiNew");

		WebElement ele = Utility.waitForWebElement(driver, AddDepartmentStatus);

		Select status = new Select(ele);

		status.selectByValue("Active");

		Utility.waitForWebElement(driver, AddDeprtmentSaveBtn).click();

	}

}
