package tests;

import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginTest extends BaseTest{
	public String expurl = "https://bstackdemo.com/?signin=true";
	@Test
	public void loginApp() {		
		//TC_001: Login with valid credentials
		instantiateLogin();
		String acturl = lp.Login("demouser","testingisfun99");
		Assert.assertEquals(acturl, expurl,"Login Failed");
		System.out.println("Login Successfull");
		
	}
	
	@Test
	public void loginApp_with_invalid_Credentials() {
		//TC_002: Login with invalid credentials
		instantiateLogin();
		String acturl = lp.Login("Testuser","testingisnotfun99");
		Assert.assertNotEquals(acturl, expurl,"Login is not failed as expected");
		System.out.println("Login Failed");
		
	}
	@Test
	public void loginApp_with_EmptyCredentials() {
		//TC_003: Login with empty username/password
		instantiateLogin();
		String acturl = lp.Login("","");
		Assert.assertNotEquals(acturl, expurl,"Login is not failed as expected");
		System.out.println("Login Failed");
	}
}
