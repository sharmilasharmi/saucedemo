package saucedemo.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import saucedemo.base.BaseClass;
import saucedemo.base.utilsClass;

public class AddToCartPage extends BaseClass {
	
	utilsClass utils = new utilsClass();
	String producttitle = null;
	String producttitlecart = null;

	@FindBys(@FindBy(xpath="//button[contains(text(),'Add to cart')]"))
	public List<WebElement>  addtocartbutton;
	
	@FindBy(xpath="//span[@class='shopping_cart_badge']")
	public WebElement cartcount;

	@FindBys(@FindBy(xpath="//div[@class='cart_item']"))
	public List<WebElement> cartitems;

	@FindBy(xpath="//a[@class='shopping_cart_link']")
	public WebElement cartlink;
	
	@FindBys(@FindBy(xpath="//button[contains(text(),'Remove')]"))
	public List<WebElement> Removebutton;
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	public WebElement productname;
	
	@FindBy(xpath="//button[contains(text(),'Remove')]")
	public WebElement Rembtn;
	
	
	/*
	 * Add the single product according to the user selection on the "no" variable
	 * validate the product added in the cart had the same name of the product selected on the listing page
	 */
	
	
	public void addSingleProduct(int no) {

		try {
			producttitle = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[" + no + "]")).getText();

			WebElement addtocartbtn = driver
					.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[" + no + "]"));
			utils.clickAction(addtocartbtn);
			utils.clickAction(ac.cartlink);
			producttitlecart = ac.productname.getText();
			Assert.assertEquals(producttitle, producttitlecart);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	/*
	 * Add the total no.of.product "n" to the cart and
	 * 	validate the product added in the cart had the same name of the product selected on the listing page
	 */
	
	

	public void addMultipleProduct(int n) throws IOException, InterruptedException {
		WebElement addtocartbtn;
		List<String> itemnames = new ArrayList<String>();
		List<String> cartnames = new ArrayList<String>();
		for (int i = 1; i <= n; i++) {

			itemnames.add(driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[" + i + "]"))
					.getText());
			addtocartbtn = driver
					.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]"));
			utils.clickAction(addtocartbtn);

		}
		
		utils.clickAction(ac.cartlink);

		int cartsize = driver.findElements(By.xpath("//div[@class='cart_item']")).size();
		System.out.println("cartsize--" + cartsize);
		for (int j = 1; j <= cartsize; j++) {
			cartnames.add(driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[" + j + "]")).getText());

		}
		System.out.println("cartnames--" + cartnames);
		System.out.println("itemnames--" + itemnames);
		Assert.assertTrue(itemnames.equals(cartnames));
	}
	
	/*
	 * Reset the state of the product - By removing the product if anything previously added
	 */
	
	public void resetAppState() {
		try {
			if(ac.Removebutton.size() >= 1) {
				
			for(WebElement removebtn : ac.Removebutton) {
				
					utils.clickAction(removebtn);
				}
		}
			else {
				System.out.println("There are no products added before");
			}
		
		}catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		
		
		
	}
	
	


