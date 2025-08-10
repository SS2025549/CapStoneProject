package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverFactory {
	public WebDriver driver;
	public WebDriverFactory(WebDriver driver) {
		this.driver = driver;
	}
	public WebDriver OpenBrowser() {
		driver = new ChromeDriver();
		return driver;
	}
	public void browserWindow(WebDriver driver) {		
		driver.manage().window().maximize();		
	}
	public void browserrefresh() {
		driver.navigate().refresh();
	}
	public void closeBrowser() {
		driver.close();
	}

}
