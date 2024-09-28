package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups={"Regrassion","Master"})
	public void verify_account_registration() {
		logger.info("******** Starting verify_account_registration *********");	
		try
		{
			HomePage objHP=new HomePage(driver);
			
			objHP.clickMyAccount();
			logger.info("Clicked on MyAccount link ");
			
			objHP.clickRegister();
			logger.info("Clicked on Register link ");
			
			AccountRegistrationPage objARP=new AccountRegistrationPage(driver);
			
			logger.info("Providing customer details ");
			objARP.setFirstName(randomeString().toUpperCase());
			objARP.setLastName(randomeString().toUpperCase());
			objARP.setEmail(randomeString()+"@gmail.com"); //randomly generated email
			objARP.setTelephone(randomeNumber());
			
			String password = randomeAlphaNumber();
			objARP.setPassword(password);
			objARP.setConformPassword(password);
			
			objARP.clickCheckPolicy();
			objARP.clickContinue();
			
			logger.info("Validating expected message.. ");
			String confmsg=objARP.getConfirmationMsg();
			
			if(confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {
				logger.error("Test failed... ");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("********* Finished verify_account_registration *********");
	}
	
	
	
	
	
	
	
}
