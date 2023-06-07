package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // for logger

import java.util.Date;
import java.util.ResourceBundle;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class BaseClass 
{
public static WebDriver d;
public Logger logger;
public ResourceBundle rb;

@BeforeClass(groups = {"sanity","regression","master"})
@Parameters("browser")   // getting browser parameter from testng.xml
public void setup(String br)
{
	logger = LogManager.getLogger(this.getClass());
	rb=ResourceBundle.getBundle("config");
	
	//launching the browser based on parameter
	if (br.equals("chrome")) 
	{
		d = new ChromeDriver();
	} else if (br.equals("edge"))
	{
		d = new EdgeDriver();
	} else 
	{
		d = new ChromeDriver();
	}
		d.manage().deleteAllCookies();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		d.get(rb.getString("appURL"));

		d.manage().window().maximize();
	}
	
	@AfterClass(groups = {"sanity","regression","master"})
	public void tearDown()
	{
		d.quit();
	}


	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString=RandomStringUtils.randomAlphanumeric(6);
		
		return generatedString;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) d;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
}
