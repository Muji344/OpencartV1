package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	 
	 public AccountRegistrationPage(WebDriver driver)
	 {
		 super(driver);
	 }
	 
@FindBy(xpath="//input[@name='firstname']")
WebElement txtFirstname;

@FindBy(xpath="//input[@name='lastname']")
WebElement txtLastName;

@FindBy(xpath="//input[@name='email']")
WebElement txtEmail;

@FindBy(xpath="//input[@name='telephone']")
WebElement txtTelephone;

@FindBy(xpath="//input[@name='password']")
WebElement txtPassword;

@FindBy(xpath="//input[@name='confirm']")
WebElement txtConfirmPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement chkPivacyPolicy;

@FindBy(xpath="//input[@type='submit']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname)
{
	txtFirstname.sendKeys(fname);
}
public void setLastName(String lname)
{
	txtLastName.sendKeys(lname);
}
public void setEmail(String email)
{
	txtEmail.sendKeys(email);
}
public void setTelephone(String teleph)
{
	txtTelephone.sendKeys(teleph);
}
public void setPassword(String pass)
{
	txtPassword.sendKeys(pass);
}
public void setConfirmPassword(String pass)
{
	txtConfirmPassword.sendKeys(pass);
}
public void setPrivacyPolicy()
{
	chkPivacyPolicy.click();
}
public void clickContinue()
{
	btnContinue.click();
	//btnContinue.submit();
	//Actions act= new Actions(driver)
	//act.moveToElement(btnContinue).click().perform();
}
public String getconfirmationmsg()
{
	try {
		return (msgConfirmation.getText());
	} catch(Exception e) {
		return (e.getMessage());
	}
}
}
		