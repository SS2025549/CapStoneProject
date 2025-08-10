package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ConfigReader;
import utils.ExtentReport;

public class LoginPage {
private WebDriver driver;
ExtentReports extent;
ExtentTest test;
private ConfigReader cr = new ConfigReader();
private WebDriverWait wait;
private String url;
private By signInLink = By.id("signin");
private By usernameInput = By.id("react-select-2-input");
private By passwordInput = By.id("react-select-3-input");
private By loginBtn = By.id("login-btn");
	public LoginPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		 
		
	}
	public String Login(String uName, String pwd) {
		try {
			
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get(cr.getAppUrl());	
		test.log(Status.INFO, "Browser launched");
		driver.findElement(signInLink).click();
		Thread.sleep(3000);
		WebElement username = driver.findElement(usernameInput);
		username.sendKeys(uName);
		username.sendKeys(Keys.ENTER); 
		WebElement password = driver.findElement(passwordInput);
		password.sendKeys(pwd);
		password.sendKeys(Keys.ENTER); 
		test.log(Status.INFO, "Entered Credentials");
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		Thread.sleep(3000);
		url= driver.getCurrentUrl();
		 test.log(Status.PASS, "Login successful. URL: " + url);

		
		
	}
		catch(Exception e) {
			e.getMessage();
			test.log(Status.FAIL, "Login failed: ");
		}
		return url;
	}
}
