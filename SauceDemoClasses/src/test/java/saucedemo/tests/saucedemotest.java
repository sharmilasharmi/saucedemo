package saucedemo.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import saucedemo.base.BaseClass;
import saucedemo.base.utilsClass;

public class saucedemotest extends BaseClass {

	utilsClass utils = new utilsClass();

	// Enter the valid credentials and validate if user is successfully logged in or
	// not

	@BeforeMethod
	public void verifyLogin() {
		try {

			utils.waitForEleVisible(hp.username, 20);
			utils.typeText(hp.username, regname);
			utils.typeText(hp.password, pass);
			utils.clickAction(hp.loginbutton);
			Assert.assertTrue(utils.elementDisplayed(hp.ptoducttitle));
			System.out.println("Successfully logged in to the application");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not able to login to the application");
		}
	}

	// Select the Name A-Z filter and validate the sort by name A-Z filter
	// functionality

	@Test(priority = 0)
	public void checkAtoZsorting() throws IOException {

		utils.selectDropDownUsingValue(lp.sortfilter, "az");
		lp.validatesortByName("az");
	}

	// Select the Name Z-A filter and validate the sort by name Z-A filter
	// functionality

	@Test(priority = 1)
	public void checkZtoAsorting() throws IOException {
		utils.selectDropDownUsingValue(lp.sortfilter, "za");
		lp.validatesortByName("za");
	}

	// Select the Price low-High filter and validate the sort by price low-High
	// filter functionality

	@Test(priority = 2)
	public void checkLowtoHighsorting() throws IOException {
		utils.selectDropDownUsingValue(lp.sortfilter, "lohi");
		lp.validateSortByPrice("lohi");
	}

	// Select the Price High-low filter and validate the sort by price High-low
	// filter functionality

	@Test(priority = 3)
	public void checkHightoLowsorting() throws IOException {
		utils.selectDropDownUsingValue(lp.sortfilter, "hilo");
		lp.validateSortByPrice("hilo");
	}

	/*
	 * Clear the previous state of the application by removing the product from cart
	 * if anything added before Add a single product to the cart and validate the
	 * product is added to cart or not
	 * 
	 * Description - Select corresponding no for the product to be selected 1 -
	 * Sauce Labs Backpack, 2 - Sauce Labs Bike Light, 3 - Sauce Labs Bolt T-Shirt, 4 -
	 * Sauce Labs Fleece Jacket, 5 - Sauce Labs Onesie, 6 - Test.allTheThings()
	 * T-Shirt (Red)
	 */

	@Test(priority = 4)
	public void validateSingleProduct() throws IOException {
		ac.resetAppState();
		ac.addSingleProduct(5);

	}

	/*
	 * Clear the previous state of the application by removing the product from cart
	 * if anything added before Add multiple product to the cart and validate the
	 * products are added to cart or not
	 */

	@Test(priority = 5)
	public void validateMultipleProduct() throws IOException, InterruptedException {

		int n = 3; // Enter no.of.product to be added into the cart
		ac.resetAppState();
		ac.addMultipleProduct(n);

	}

	// Select the logout option from the menu bar and validate the user is logged
	// out or not

	@AfterMethod
	public void verifyLogOut() {
		try {
			utils.waitForEleVisible(menupage.menu, 20);

			utils.clickAction(menupage.menu);

			utils.clickAction(menupage.logout);

			Assert.assertTrue(hp.loginbutton.isDisplayed());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not able to logout to the application");
		}
	}

}
