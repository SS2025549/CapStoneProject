package com.HRM.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
	private WebDriver driver;
	private String adminURL;
	private By urllocator = By.xpath("//span[text() = 'Admin']");
	private By links = By.cssSelector("[class = 'oxd-main-menu']");
	private By username = By.cssSelector("[class ='oxd-input oxd-input--active']");
	private By searchbutton = By.xpath("//button[@type='submit']");
	private By resultRows   = By.cssSelector(".oxd-table-body .oxd-table-card");
	//private By roleDrpdn = By.xpath("//i[@class ='oxd-icon bi-caret-up-fill oxd-select-text--arrow']");
	private By Drpdwn = By.cssSelector("div.oxd-form-row:nth-of-type(1) .oxd-select-text--arrow");
	private By userroleInput = By.xpath("//div[@role='listbox']/div[2]");
	private By statusInput = By.className("oxd-select-text-input");
	private int usrCount = 0;
	private int roleCount = 0;
	private int statusCount = 0;
	private WebDriverWait wait;
	public AdminPage(WebDriver driver) {
		this.driver = driver;	
		
	}
	
	public void setAdminURL() {
		try {
			Thread.sleep(3000);
			driver.findElement(urllocator).click();
			this.adminURL = driver.getCurrentUrl();			
		}
		catch(Exception e) {
			e.getMessage();
		}		
		
	}
	public int getLinkCount() {
		try {
			Thread.sleep(3000);
			WebElement plink = driver.findElement(links);
			List<WebElement> clinks = plink.findElements(By.tagName("li"));
			return clinks.size();
		}
		catch(Exception e) {
			e.getMessage();
			return 0;
		}	
	}
	
	public int byUserName() {
		
		try {
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
			List<WebElement> usernameInput = wait.until(
				    ExpectedConditions.visibilityOfAllElementsLocatedBy(username));
			usernameInput.get(1).sendKeys("Admin");
			wait.until(ExpectedConditions.elementToBeClickable(searchbutton)).click();					 
			List<WebElement> rows = wait.until(
			            ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
			usrCount =  rows.size();	
			Thread.sleep(2000);
			
		}
		catch(Exception e) {
			e.getMessage();
			
		}
		driver.navigate().refresh();
		return usrCount;		
		
	}
	
	
	public int byUserRole() {
		try {
			
			wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(Drpdwn)).click();
	     	wait.until(ExpectedConditions.elementToBeClickable(userroleInput)).click();
	     	wait.until(ExpectedConditions.elementToBeClickable(searchbutton)).click();	     	
			List<WebElement> rows = wait.until(
		            ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
			roleCount =  rows.size();	
			
		}
		catch(Exception e) {
			e.getMessage();
		}
		driver.navigate().refresh();
		return roleCount;
	}
	
	public int byUserStatus() {
		try {
			
			wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
			List<WebElement> statusdrpdwn = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Drpdwn));
			statusdrpdwn.getLast().click();
			List<WebElement> statusValue = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(statusInput));
			statusValue.get(1).click();
		   	wait.until(ExpectedConditions.elementToBeClickable(searchbutton)).click();	     	
			List<WebElement> rows = wait.until(
		            ExpectedConditions.visibilityOfAllElementsLocatedBy(resultRows));
			statusCount =  rows.size();	
			
		}
		catch(Exception e) {
			e.getMessage();
		}
		driver.navigate().refresh();
		return statusCount;
	}

}
