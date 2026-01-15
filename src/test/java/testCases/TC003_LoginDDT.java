package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBases.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass 
{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="Datadriven")
	public void Verify_loginDDT(String email, String password, String exp)
	{
		logger.info("**** Started TC003_LoginDDT **** ");
		
		try
		{
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(email);;
			lp.enterPassword(password);
			lp.clickLogin();	
			
		// My account page	
		MyAccountpage mcc=new MyAccountpage(driver);
		boolean actualresult=mcc.VerifyMyAccount();
		
		
		/*
		 Data is valid -- login success-test pass-logout 
		 					login failed-test fail 
		 					
		 Data is invalid -- login success-test fail-logout 
		 					login failed-test pass
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(actualresult==true)
			{
				mcc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(actualresult==true)
			{
				mcc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**** Finished TC003_LoginDDT **** ");
		
	}

}
