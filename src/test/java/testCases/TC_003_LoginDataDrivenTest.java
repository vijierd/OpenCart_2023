package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;
import testBase.BaseClass;

public class TC_003_LoginDataDrivenTest extends BaseClass
{
@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = {"master"})
public void test_LoginDDT(String email, String pwd, String exp)
{
	logger.info(" ***Starting TC_003_LoginDataDrivenTest***");
	try
	{
		HomePage hp=new HomePage(d);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(d);
		lp.setEmail(email); // valid email, get it from properties file
		lp.setPassword(pwd); // valid password, get it from properties file
		lp.clickLogin();
		
		
			/*************************************************************************************
			Data		|	Target Page	|	Test		
			-----------------------------------------	
			Vaid		|	True				|	Pass	
			Valid		|	False				| 	Fail
			Invalid 	| 	True				|	Fail
			Invaild	|	False				|	Pass
		 ****************************************************************************************/
		MyAccountPage macc=new MyAccountPage(d);
		boolean targetpage=macc.isMyAccountPageExists();//isMyAccountPageExists();
		if (exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				//Data is username and password
				logger.info("Login is successfull - Data is valid and Target page is true");
				logger.info("TestCase Passed......");
				macc.clickLogout(); 
				Assert.assertTrue(true);
			}else if (targetpage==false) 
			{
				logger.info("Login is Unsuccessfull - Data is valid and Target page is false");
				logger.info("TestCase Passed......");
				Assert.assertTrue(false);
			}
		}else if(exp.equalsIgnoreCase("Invalid")) 
		{
			if (targetpage==true) //-ve here already logged in but testcase is passed but invalid .so, we want to fail the test case
			{
				logger.info("Login is successfull - Data is invalid and Target page is True");
				logger.info("TestCase Passed......");
				macc.clickLogout();
				Assert.assertTrue(false);
			}else if (targetpage==false) 
			{
				logger.info("Login is Unsuccessfull - Data is invalid and Target page is false");
				logger.info("TestCase Passed......");
				Assert.assertTrue(true);
			}
			
		}
		
	} catch (Exception e) {
		Assert.fail();
	}

	logger.info(" ***Finished TC_003_LoginDataDrivenTest***");
	}
}
