package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage
{

	public HomePage(WebDriver d) {
		super(d);
	}
		// Elements
		@FindBy(xpath = "//span[text()='My Account']") WebElement lnkMyaccount;

		@FindBy(linkText = "Register") WebElement lnkRegister;
		
		@FindBy(linkText = "Login")   WebElement lnkLogin;

		// Action Methods
		public void clickMyAccount() 
		{
			lnkMyaccount.click();
		}

		public void clickRegister() 
		{
			lnkRegister.click();
		}
		
		public void clickLogin()   
		{
			lnkLogin.click();
		}

}
