package com.HRM.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class TakeScreenshot {
	private String filename;
	public TakeScreenshot()
	{
		Date d = new Date();
		this.filename = d.toString().replace(" ", "_").replace(":", "_");
	}
					
	public void takeSS(WebDriver driver, String testName) throws IOException {
		filename = testName+ " # "+filename;
	//	System.out.println(filename);
		File ssFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(ssFile, new File(filename+ ".jpeg"));
	}

}
