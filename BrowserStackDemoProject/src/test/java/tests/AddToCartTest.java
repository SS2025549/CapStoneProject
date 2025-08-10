package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest{
	int qty =0;
	@Test(priority=1)
	public void AppLogin() {
		instantiateLogin();
		lp.Login("demouser", "testingisfun99");
	}
	@Test(priority=2,dependsOnMethods="AppLogin")
	public void AddSingleItem_ToCart() {
		//TC_004: Add single item to cart
		instantiateCart();
		qty = cp.AddanItem("iPhone 12");
		Assert.assertEquals(1, qty,"Expected to Add 1 item to cart");
		System.out.println(qty+" item added to cart");
		
	}
	@Test(priority=2,dependsOnMethods="AppLogin")
		public void MultipleItems_ToCart() {
		//TC_005: Add multiple items to cart and verify cart count
		instantiateCart();
		int toAdd = 2;
		qty = cp.AddMultipleItems(toAdd);
		Assert.assertEquals(toAdd, qty,"Expected to Add 1 item to cart");
		System.out.println(qty+" item added to cart");		
		}
	@Test(priority=2,dependsOnMethods={"AppLogin","MultipleItems_ToCart"})
	public void RemovefromCart() {
		//TC_006: Remove item from cart
		instantiateCart();
		int toRemove = 1;
		qty = cp.removeCart(toRemove);
		System.out.println("Removed "+ toRemove+" Available: "+qty);
		
	}
}
