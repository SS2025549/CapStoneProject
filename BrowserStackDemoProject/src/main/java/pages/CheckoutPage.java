package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CheckoutPage {
	public WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	private WebDriverWait wait;
	private String ckBtn = "//div[@class ='buy-btn']";
	private By fName = By.id("firstNameInput");
	private By lName = By.id("lastNameInput");
	private By addr = By.id("addressLine1Input");
	private By province = By.id("provinceInput");
	private By pCode = By.id("postCodeInput");
	private By subBtn = By.id("checkout-shipping-continue");
	private By cartOpen = By.xpath("//span[@class='bag bag--float-cart-closed']");
	private By cartBag = By.xpath("//span[@class = 'bag__quantity']");
	private String msg;
	private int cartQty=0;
	public CheckoutPage(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void checkout_Order() {
		driver.findElement(By.xpath(ckBtn)).click();
	}

	public String completeOrder() {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.visibilityOfElementLocated(fName)).sendKeys("Asha");
			wait.until(ExpectedConditions.visibilityOfElementLocated(lName)).sendKeys("Nelson");					
			wait.until(ExpectedConditions.visibilityOfElementLocated(addr)).sendKeys("200 SW Market ST");			
			wait.until(ExpectedConditions.visibilityOfElementLocated(province)).sendKeys("Oregon");			
			wait.until(ExpectedConditions.visibilityOfElementLocated(pCode)).sendKeys("97201");			
			wait.until(ExpectedConditions.visibilityOfElementLocated(subBtn)).click();
			Thread.sleep(3000);
			msg = driver.findElement(By.id("confirmation-message")).getText();
			test.log(Status.PASS, "Order placed Successfully!");
		} catch (Exception e) {
			e.getMessage();
			test.log(Status.FAIL, "Order not placed!");
		}
		return msg;
		
	}
	public int noOrderCheckout() {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.visibilityOfElementLocated(cartOpen)).click();
			Thread.sleep(2000);
			cartQty = Integer.parseInt(driver.findElement(cartBag).getText());
			test.log(Status.PASS, "No Items not placed the order!");
		}
		catch(Exception e) {
			e.getMessage();
			test.log(Status.PASS, "Checkout Failed!");
		}
		return cartQty;
		
	}

}
