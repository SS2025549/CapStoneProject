package com.HRM.TestCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPageTestCase extends BaseClass{
	
  @Test(priority=1)
  public void logintoApp() {
	  try {
		  initialSetup();
		  lp.setUname("Admin");
		  lp.setPwd("admin123");
		  String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		  String url = lp.Login_App();
		  Assert.assertEquals(expUrl, url);
		  System.out.println("Login Successful");
		
		  
	  }
	  catch(Exception e) {
		  e.printStackTrace();
				  
	  }
	  
	
  }
  
  @Test(priority=2,dependsOnMethods={"logintoApp"})
  public void navigateAdminPage() {
	  ap.setAdminURL();
	//  closeBrowser();
	
	  
  }
  @Test(priority=3,dependsOnMethods={"logintoApp"})
  public void countLinks() {
	  int linkcount = ap.getLinkCount();
	  try {
		  Assert.assertEquals(linkcount, 12);
		  System.out.println("Options in Left Menu are "+linkcount);  
		  
	  }
	  catch(Exception e) {
		  e.printStackTrace();		
		  
	  }
	//  closeBrowser(); 
  }
  @Test(priority=3,dependsOnMethods={"logintoApp","navigateAdminPage"})
  public void searchByUserName() {
	int recordCount = ap.byUserName();
	Assert.assertNotEquals(recordCount, 0, "No Records Found");
	System.out.println("Total User Records: "+recordCount);
	
  }
  @Test(priority=3,dependsOnMethods={"logintoApp","navigateAdminPage"})
  public void searchByUserRole() {
	int recordCount = ap.byUserRole();
	Assert.assertNotEquals(recordCount, 0, "No Records Found");
	System.out.println("Total UserRole Records: "+recordCount);
	
  }
  @Test(priority=3,dependsOnMethods={"logintoApp","navigateAdminPage"})
  public void searchByUserStatus() {
	int recordCount = ap.byUserStatus();
	Assert.assertNotEquals(recordCount, 0, "No Records Found");
	System.out.println("UserStatus as enabled: "+recordCount);
	
  }
}
