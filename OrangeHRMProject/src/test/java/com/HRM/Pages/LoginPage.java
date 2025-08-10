package com.HRM.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.HRM.Utilities.TakeScreenshot;



public class LoginPage {
	private WebDriver driver;
	
	public LoginPage(WebDriver driver ) {
		this.driver = driver;
		
	}
	private By userName = By.name("username");
	private By password = By.name("password");
	private By loginBtn = By.cssSelector("button[type='submit']");
	
	private String uName;
	private String pwd;
	
	public void setUname(String uName) {
		this.uName = uName;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUname() {
		return this.uName;
	}
	public String getPwd() {
		return this.pwd;
	}
	public String Login_App() throws IOException, InterruptedException {
		
		driver.findElement(userName).sendKeys(uName);
		driver.findElement(password).sendKeys(pwd);
		TakeScreenshot ss = new TakeScreenshot();
		ss.takeSS(driver,"Login");
		driver.findElement(loginBtn).click();
		ss.takeSS(driver, "Login");
		Thread.sleep(2000);
		return driver.getCurrentUrl();
		
	}
	public void Logout() throws Exception {
		//Thread.sleep(2000);
		driver.findElement(By.className("oxd-userdropdown-img")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Logout")).click();
	//	driver.quit();
	}

}
