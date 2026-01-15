package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBases.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("******  Starting TC001_AccountRegistrationTest  ******");	
	    try 
	    {
		HomePage hp=new HomePage(driver);
			
		hp.clickMyAccount();
		logger.info("clicked on my account..");
		
		hp.clickRegister();
		logger.info("clicked on Register..");	
		
		AccountRegistrationPage Regpg=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details..");
		Regpg.setFirstName(randomString().toUpperCase());
		Regpg.setLastName(randomString().toLowerCase());
		Regpg.setEmail(randomString()+"@gmail.com");
		String password=randomalphanumericString();
		Regpg.setPassword(password);
		Regpg.setConfirmPassword(password);
		
		Regpg.setTelephone(randomnumericString());
		Regpg.setPrivacyPolicy();
		logger.info("Checked the privacy checkbox..");
		Regpg.clickContinue();
		logger.info("Clicked on continue button..");
		logger.info("Validating expected message..");
		String confmsg=Regpg.getconfirmationmsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	    }
		catch(Exception e)
	    {
			Assert.fail();
	    }
	    logger.info("**** Finished TC001_AccountRegistrationTest ****");
	}
	
}
