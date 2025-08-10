package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest{
	@Test(priority=1)
	public void CheckoutOrder()
	{
		//TC_007: Place order with valid details
		instantiateLogin();
		instantiateCart();
		instantiateCheckout();
		lp.Login("demouser", "testingisfun99");
		cp.AddMultipleItems(2);
		ckp.checkout_Order();
	}
	@Test(priority=2, dependsOnMethods = "CheckoutOrder")
	public void place_order() {
		instantiateCheckout();
		String msg = ckp.completeOrder();
		Assert.assertEquals(msg,"Your Order has been successfully placed.","Order not confirmed");
		System.out.println(msg);
	}
	@Test
	public void checkout_withoutItems() {
		instantiateLogin();
		instantiateCheckout();
		lp.Login("demouser", "testingisfun99");
		int qty = ckp.noOrderCheckout();
		Assert.assertEquals(qty, 0,"There are items to checkout");
		System.out.println("No Items in Cart!");
	}
}
