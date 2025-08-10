package com.HRM.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.HRM.Utilities.ExcelUtil;
import com.HRM.Utilities.ExtentReport;

public class LoginPageTestCase extends BaseClass{
	
	
	
	@Test
	public void PerformLogin() throws Exception {
		
		ExcelUtil ex = new ExcelUtil();
		String[] cellvalue = new String[2];
		ExtentReport report = new ExtentReport();
		report.InitialConfig();
		int total_row = ex.totalRows();
		int total_col = ex.totalColumns();
		String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		for (int i = 1; i < total_row; i++) {
			initialSetup();
			for (int j = 0; j < total_col; j++) {
				cellvalue[j] = ex.getCellData(i, j);				
			}
			lp.setUname(cellvalue[0]);
			lp.setPwd(cellvalue[1]);
			String Acturl = lp.Login_App();
			try {
				Assert.assertEquals(expUrl, Acturl);
				System.out.println("Test Passed! Login Successful");
				report.generateReport("Login Validation", "Pass");
				lp.Logout(); 
				closeBrowser();
			}
			catch (AssertionError e) {
			    System.out.println("Assertion failed: " + e.getMessage());
			    report.generateReport("Login Validation", "Fail");
			   closeBrowser();
			   
			}

		
		}
		
	}
	
}
