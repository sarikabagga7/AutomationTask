package walshIntegrated.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import orangeHRM.factories.DataProviderFactory;
import orangeHRM.pages.BaseClass;
import orangeHRM.pages.LoginPage;
import orangeHRM.pages.LogoutPage;

public class LoginScenarios extends BaseClass {

	LoginPage login;
	LogoutPage logout;
	
	
	@Test(priority=0)
	public void verifyPage()
	{		
		
		login=PageFactory.initElements(driver, LoginPage.class);
		
		logout=PageFactory.initElements(driver, LogoutPage.class);
		
		logger=report.createTest("URL validation");
		
		login.verifyUrlBeforeLogin();	
		
		logger.info("Validating url");
	}
	
	//Orange HRM
	
	@Test(priority=1,dependsOnMethods="verifyPage")
	public void loginToApplication()
	{
		
		
		logger=report.createTest("Login as admin");
		
		login.loginToApplication(DataProviderFactory.getExcel().getCellData("OrangeHRM", 1, 0),
				
				DataProviderFactory.getExcel().getCellData("OrangeHRM", 1, 1));
		
		login.verifyUrlAfterLogin();
		
		logger.info("Logged in");
	}
	
	// TD Insurance
	@Test(priority=1, enabled=false)
	public void loginToApplication1()
	{
		
		
		logger=report.createTest("Login as admin");
		
		login.loginToApplication1(DataProviderFactory.getExcel().getCellData("QA", 1, 0),
				
				DataProviderFactory.getExcel().getCellData("QA", 1, 1));
		
		//login.verifyUrlAfterLogin();
		
		logger.info("Logged in");
	}
	
	
	@Test(priority=2,dependsOnMethods="loginToApplication")
	public void logoutFromApplication()
	{
		logger=report.createTest("Logout");
		
		logout.logOutFromApplicationWithAdminRole();
		
		logger.info("Logout done");
		
	}

}
