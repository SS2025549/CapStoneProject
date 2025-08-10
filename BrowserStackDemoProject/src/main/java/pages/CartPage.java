package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CartPage {
	public WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	WebDriverWait wait;
	private String cartItem = "//div[@class='shelf-item']/p[text()='";
	private String addBtn = "/following-sibling::div[2]";
	private String pdtlist = "//div[@class='shelf-item']//div[4]";
	private String rCart = "//div[@class='shelf-item__del']";
	private String cartBag = "//span[@class = 'bag__quantity']";
	private int cartqty = 0;

	public CartPage(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public int AddanItem(String cItem) {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			cartItem = cartItem + cItem + "\']" + addBtn;
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cartItem))).click();
			cartqty =Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartBag))).getText());
			test.log(Status.PASS, "Item Added Successfully!");
		} catch (Exception e) {
			e.getMessage();
			test.log(Status.FAIL, "Unable to add an Item! ");
		}
		return cartqty;
	}
	
	public int AddMultipleItems(int qty) {
		
		try {
			
			List <WebElement> productList = driver.findElements(By.xpath(pdtlist));
			Thread.sleep(2000);
			for(int i=0;i<qty;i++) {
				
				productList.get(i).click();
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
			cartqty = Integer.parseInt((driver.findElement(By.xpath(cartBag)).getText()));
			test.log(Status.PASS, "Items Added Successfully!");
		}
		catch(Exception e)
		{
			e.getMessage();
			test.log(Status.FAIL, "Unable to add Items! ");
		}
		return cartqty;
	}
	
	public int removeCart(int qty) {
		try {
			cartqty = Integer.parseInt((driver.findElement(By.xpath(cartBag)).getText()));
			if(cartqty>qty) {
				for(int i=0;i<qty;i++) {
					driver.findElement(By.xpath(rCart)).click();
				}				
				cartqty = Integer.parseInt((driver.findElement(By.xpath(cartBag)).getText()));	
				test.log(Status.PASS, "Items Removed Successfully!");
			}
			else
			{
				cartqty = 0;
			}
		}
		catch(Exception e) {
			e.getMessage();
			test.log(Status.FAIL, "Unable to remove Items! ");
		}
		return cartqty;
	}

}
