package TelecomProject;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


public class TelecomTest {
	
	String userToken;
	String userEmail;
	String loginToken;
	
	@Test(priority=1)	
	public void AddNewUser() {
		Long Uemail = System.currentTimeMillis();
		Response res = given().header("Content-Type","application/json")
						.body("{\n"
								+ "\"firstName\": \"QA\",\n"
								+ "\"lastName\": \"API\",\n"
								+ "\"email\": \"QA"+Uemail+"@yopmail.com\",\n"
								+ "\"password\": \"myPassword\"\n"
								+ "}")
						.when().post("https://thinking-tester-contact-list.herokuapp.com/users");
		userToken = res.jsonPath().getString("token");
		userEmail = res.jsonPath().getString("user.email");
		String[] status_msg = (res.getStatusLine()).split(" ",3);		
		Assert.assertEquals(res.getStatusCode(), 201);
		Assert.assertEquals(status_msg[2], "Created");
				
	}
	
	@Test(priority=2, dependsOnMethods = "AddNewUser")
	public void getUserProfile() {
		
		Response res = given().header("Content-Type","application/json")
						.header("Authorization","Bearer "+userToken)
						.when().get("https://thinking-tester-contact-list.herokuapp.com/users/me");
		String[] status_msg = (res.getStatusLine()).split(" ",3);		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(status_msg[2], "OK");
			
	}	
	
	@Test(priority=2, dependsOnMethods = "AddNewUser")
	public void updateUser() {
		Response res = given().header("Content-Type","application/json")
				.header("Authorization","Bearer "+userToken)
				.body("{\n"
						+ "\"firstName\": \"QA\",\n"
						+ "\"lastName\": \"API\",\n"
						+ "\"email\": \"" + userEmail + "\","
						+ "\"password\": \"mynewPassword\"\n"
						+ "}")
				.when().patch("https://thinking-tester-contact-list.herokuapp.com/users/me");
		String[] status_msg = (res.getStatusLine()).split(" ",3);		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(status_msg[2], "OK");
		
	}
	@Test(priority=2, dependsOnMethods = "updateUser")	
	public void loginUser() {
		Response res = given().header("Content-Type","application/json")
					.body("{\r\n"
							+"\"email\": \"" + userEmail + "\","
							+ "\"password\": \"mynewPassword\"\r\n"
							+ "}")
					.when().post("https://thinking-tester-contact-list.herokuapp.com/users/login");
	
		loginToken = res.jsonPath().getString("token");

		String[] status_msg = (res.getStatusLine()).split(" ",3);		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(status_msg[2], "OK");
		
	}
	
	@Test(priority=2, dependsOnMethods = "loginUser")	
	public void addContact() {
		Response res = given().header("Content-Type","application/json")
				.header("Authorization","Bearer "+loginToken)
				.body("{\r\n"
						+ "\"firstName\": \"Amy\",\r\n"
						+ "\"lastName\": \"Doe\",\r\n"
						+ "\"birthdate\": \"1970-01-01\",\r\n"
						+ " \"email\": \"jdoe@fake.com\",\r\n"
						+ " \"phone\": \"8005555555\",\r\n"
						+ "\"street1\": \"1 Main St.\", \"street2\": \"Apartment A\", \"city\": \"Anytown\",\r\n"
						+ "\"stateProvince\": \"KS\", \"postalCode\": \"12345\", \"country\": \"USA\"\r\n"
						+ "}")
				.when().post("https://thinking-tester-contact-list.herokuapp.com/contacts");
		String[] status_msg = (res.getStatusLine()).split(" ",3);		
		Assert.assertEquals(res.getStatusCode(), 201);
		Assert.assertEquals(status_msg[2], "Created");	
		
	}
	@Test(priority=2, dependsOnMethods = "loginUser")	
	public void getContactList() {
			Response res = given().header("Content-Type","application/json")
					.header("Authorization","Bearer "+loginToken)
					.when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
			String[] status_msg = (res.getStatusLine()).split(" ",3);		
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(status_msg[2], "OK");	
		
	}
							
	@Test(priority=2, dependsOnMethods = "loginUser")	
	public void getContact() {
			Response res = given().header("Content-Type","application/json")
					.header("Authorization","Bearer "+loginToken)
					.when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
			String[] status_msg = (res.getStatusLine()).split(" ",3);		
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(status_msg[2], "OK");	
		
	}						
	@Test(priority=2, dependsOnMethods = "loginUser")	
	public void updateContacts() {
			Response res = given().header("Content-Type","application/json")
					.header("Authorization","Bearer "+loginToken)
					.body("{\r\n"
							+ "\"firstName\": \"Amy\", \"lastName\": \"Miller\",\r\n"
							+ "\"birthdate\": \"1992-02-02\",\r\n"
							+ "\"email\": \"amiller@fake.com\", \"phone\": \"8005554242\",\r\n"
							+ "\"street1\": \"13 School St.\", \"street2\": \"Apt. 5\",\r\n"
							+ "\"city\": \"Washington\", \"stateProvince\": \"QC\",\r\n"
							+ "\"postalCode\": \"A1A1A1\", \"country\": \"Canada\"\r\n"
							+ "}")
					.when().put("https://thinking-tester-contact-list.herokuapp.com/contacts/");
			String[] status_msg = (res.getStatusLine()).split(" ",3);		
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(status_msg[2], "OK");
			String email = res.jsonPath().getString("user.email");
			Assert.assertTrue(email.matches("/^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$/"), "Email format is valid");
			//Assert.assertTrue("Email format is valid", email.matches("/^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$/"));

		
	}	
	@Test(priority=2, dependsOnMethods = "loginUser")	
	public void updateContact() {
		Response res = given().header("Content-Type","application/json")
				.header("Authorization","Bearer "+loginToken)
				.body("{\r\n"
						+ "\"firstName\": \"Anna\"\r\n"
						+ "}")
				.when().patch("https://thinking-tester-contact-list.herokuapp.com/contacts/");
		String[] status_msg = (res.getStatusLine()).split(" ",3);		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(status_msg[2], "OK");
		String fName = res.jsonPath().getString("user.firstName");
		Assert.assertTrue(fName.matches("Anna"), "Valid Name");
		
		
	}
	@Test(priority=2, dependsOnMethods = "loginUser")	
	public void logoutUser() {
		Response res = given().header("Content-Type","application/json")
				.header("Authorization","Bearer "+loginToken)
				.when().post("https://thinking-tester-contact-list.herokuapp.com/users/logout");
		String[] status_msg = (res.getStatusLine()).split(" ",3);		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(status_msg[2], "OK");
	}
						

}