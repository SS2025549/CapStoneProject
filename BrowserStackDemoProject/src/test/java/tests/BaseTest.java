package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import utils.ExtentReport;
import utils.WebDriverFactory;

public class BaseTest {
	public WebDriver driver;	
	public LoginPage lp;
	public CartPage cp;
	public CheckoutPage ckp;
	ExtentReports extent;
	ExtentTest test;


	private WebDriverFactory wd = new WebDriverFactory(driver);
	@BeforeTest
	public void initialSetup() {
		driver = wd.OpenBrowser();
		wd.browserWindow(driver);	
		ckp = new CheckoutPage(driver,test);
	}
	
	public void instantiateLogin() {
		extent = ExtentReport.createInstance("LoginPage");
	    test = extent.createTest("Login Test - bstackdemo.com");
	    lp = new LoginPage(driver,test);
	}
	public void instantiateCart() {
		extent = ExtentReport.createInstance("CartPage");
	    test = extent.createTest("Cart Page Test - bstackdemo.com");
	    cp = new CartPage(driver,test);	   
	}
	public void instantiateCheckout() {
		extent = ExtentReport.createInstance("CheckoutPage");
	    test = extent.createTest("Checkout Page Test - bstackdemo.com");
	    cp = new CartPage(driver,test);	   
	}
	
	@AfterTest
	public void closeApp() {
		 ExtentReport.flushReport();
		 wd.closeBrowser();
	}
	
}
