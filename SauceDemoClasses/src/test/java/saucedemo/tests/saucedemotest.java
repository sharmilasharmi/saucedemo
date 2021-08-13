package saucedemo.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import saucedemo.base.BaseClass;
import saucedemo.base.utilsClass;
import saucedemo.page.AddToCartPage;
import saucedemo.page.HomePage;
import saucedemo.page.ListingPage;
import saucedemo.page.MenuItemPage;

public class saucedemotest extends BaseClass {

	utilsClass utils = new utilsClass();

	

	@BeforeMethod
	public void verifyLogin() {
		try {
			HomePage hp = new HomePage();
			
			utils.waitForEleVisible(hp.username, 20);
			utils.typeText(hp.username, regname);
			utils.typeText(hp.password, pass);
			utils.clickAction(hp.loginbutton);
			System.out.println("Successfully logged in to the application");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not able to login to the application");
		}
	}

	
	@Test(priority = 0)
	public void checkAtoZsorting() throws IOException {

		ListingPage lp = new ListingPage();
		utils.selectDropDownUsingValue(lp.sortfilter, "az");
		lp.validatesortByName("az");
	}

	@Test(priority = 1)
	public void checkZtoAsorting() throws IOException {
		ListingPage lp = new ListingPage();
		utils.selectDropDownUsingValue(lp.sortfilter, "za");
		lp.validatesortByName("za");
	}

	@Test(priority = 2)
	public void checkLowtoHighsorting() throws IOException {
		ListingPage lp = new ListingPage();
		utils.selectDropDownUsingValue(lp.sortfilter, "lohi");
		lp.validateSortByPrice("lohi");
	}
	@Test(priority = 3)
	public void checkHightoLowsorting() throws IOException {
		ListingPage lp = new ListingPage();
		utils.selectDropDownUsingValue(lp.sortfilter, "hilo");
		lp.validateSortByPrice("hilo");
	}

	/*
	 * Description - Select corresponding no for the product to be selected 
	 * 1 - Sauce Labs Backpack 
	 * 2 - Sauce Labs Bike Light 
	 * 3 - Sauce Labs Bolt T-Shirt 
	 * 4 - Sauce Labs Fleece Jacket
	 * 5 - Sauce Labs Onesie 6 - Test.allTheThings() T-Shirt (Red)
	 */
	
	@Test(priority = 4)
	public void validateSingleProduct() throws IOException {
		AddToCartPage ac = new AddToCartPage();
        ac.addSingleProduct(5);

	}

	@Test(priority = 5)
	public void validateMultipleProduct() throws IOException, InterruptedException {
		AddToCartPage ac = new AddToCartPage();

		int[] arr = { 3, 5 }; /* Please select the product above mentioned by give number - Don't use
								 duplicate numbers */
		
		ac.addMultipleProduct(arr);

	}

	
	@AfterMethod
	public void verifyLogOut() {
		try {
			HomePage hp = new HomePage();
			MenuItemPage menupage = new MenuItemPage();
			utils.clickAction(menupage.menu);

			utils.clickAction(menupage.logout);

			Assert.assertTrue(hp.loginbutton.isDisplayed());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not able to logout to the application");
		}
	}

}
