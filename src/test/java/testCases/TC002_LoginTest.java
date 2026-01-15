package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBases.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void login_verify()
	{
		logger.info("***** Started TC002_LoginTest *****");
		
		try {
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			lp.enterEmail(p.getProperty("email"));
			lp.enterPassword(p.getProperty("password"));
			lp.clickLogin();
			
			MyAccountpage map=new MyAccountpage(driver);
			boolean actualresult=map.VerifyMyAccount();
			
			//Assert.assertEquals(actual result, true,"Login Failed");
			Assert.assertTrue(actualresult);
     		}
			
			catch(Exception e)
			{
				Assert.fail();
			}
		
		
			logger.info("***** Finished TC002_LoginTest *****");
		
	}

}
