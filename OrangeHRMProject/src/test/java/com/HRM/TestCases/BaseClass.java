package com.HRM.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.HRM.Pages.AdminPage;
import com.HRM.Pages.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AdminPage ap;
	
	public void initialSetup() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(7000);
		driver.manage().window().maximize();
		lp = new LoginPage(driver);
		ap = new AdminPage(driver);
	}
	
	public void closeBrowser()
	{
		driver.close();
	}
	
	
}
