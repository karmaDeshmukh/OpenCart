package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")//getting data provider from different class
	public void  verify_loginDDT(String email,String pwd,String exp) {
		logger.info("********* Starting TC003_LoginDDT********");
		try {
		//HomePage
		HomePage objHP=new HomePage(driver);
		objHP.clickMyAccount();
		logger.info("Clicked on MyAccount link ");
		
		objHP.clickLogin();
		logger.info("Clicked on login link ");
		
		//LoginPage
		LoginPage objLP=new LoginPage(driver);
		objLP.setEmail(email);
		objLP.setPassword(pwd);
		objLP.clickLogin();
		
		//MyAccountPage
		MyAccountPage objMAP=new MyAccountPage(driver);
		boolean targetPage = objMAP.isMyAccountPageExists();
		
		/*Data is valid -login success - test pass -logout
		  				-login failed - test fail
		  
		  Data is invalid -login success -test fail - logout
		  				  -login success -test pass
		 */
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				Assert.assertTrue(true);
				objMAP.clickLogout();
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				objMAP.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
			System.out.println(e.getMessage());
		}
		logger.info("********* Finished TC003_LoginDDT*********");
	}
}
