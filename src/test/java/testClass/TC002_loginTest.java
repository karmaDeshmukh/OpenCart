package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_loginTest extends BaseClass {
	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("********* Startign TC_002_loginTest********");
		try {
			//HomePage
			HomePage objHP=new HomePage(driver);
			objHP.clickMyAccount();
			logger.info("Clicked on MyAccount link ");
			
			objHP.clickLogin();
			logger.info("Clicked on login link ");
			
			//LoginPage
			LoginPage objLP=new LoginPage(driver);
			objLP.setEmail(pr.getProperty("Email"));
			objLP.setPassword(pr.getProperty("Password"));
			objLP.clickLogin();
			
			//MyAccountPage
			MyAccountPage objMAP=new MyAccountPage(driver);
			boolean targetPage = objMAP.isMyAccountPageExists();
			
			Assert.assertTrue(targetPage);
			objMAP.clickLogout();
		}
		catch(Exception e){
			Assert.fail();
		}
		
	}

}
