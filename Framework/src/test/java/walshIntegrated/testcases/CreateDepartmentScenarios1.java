package walshIntegrated.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import walshIntegrated.factories.DataProviderFactory;
import walshIntegrated.pages.BaseClass;
import walshIntegrated.pages.DepartmentsPage;
import walshIntegrated.pages.LoginWalshPage;
import walshIntegrated.pages.LogoutPage;
import walshIntegrated.pages.SelectRolePage;

public class CreateDepartmentScenarios1 extends BaseClass {

	LoginWalshPage login;
	LogoutPage logout;
	SelectRolePage role;
	DepartmentsPage dept;

	@Test(priority = 0)
	public void verifyPage() {

		login = PageFactory.initElements(driver, LoginWalshPage.class);

		logout = PageFactory.initElements(driver, LogoutPage.class);

		role = PageFactory.initElements(driver, SelectRolePage.class);

		dept = PageFactory.initElements(driver, DepartmentsPage.class);

		logger = report.createTest("URL validation");

		login.verifyUrlBeforeLogin();

		logger.info("Validating url");
	}

	// Dept Page - Create Department

	@Test( priority = 1, dependsOnMethods = "verifyPage")
	public void TC_01_Department_Create() {

		logger = report.createTest("Create Department Scenario");
		
		logger.info("Login as Account Administrator");

		login.loginToApplication(DataProviderFactory.getExcel().getCellData("Dev", 1, 0),

				DataProviderFactory.getExcel().getCellData("Dev", 1, 1));

		login.verifyUrlAfterLogin();

		logger.info("Logged in");

		role.selectRoleAsAccountAdmin();

		logger.info("Account Administrator role selected");

		dept.navigateToDepartmentsPage();

		dept.verifyUrlAfterNavigateToDept();

		logger.info("Department Page");

		dept.AddDepartmentsPage();

		logger.info("Department Added");

		dept.verifyDeparment("Safety_peerjiAdd");

		logger.info("Department updation verified");

	}


}
