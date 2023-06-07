package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage 
{
	WebDriver d;
	
	public BasePage(WebDriver d) {
		
		this.d=d;
		PageFactory.initElements(d, this);
	}
}
