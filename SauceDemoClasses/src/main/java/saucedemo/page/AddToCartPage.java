package saucedemo.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
	

	public AddToCartPage() {
		PageFactory.initElements(driver, this);
	}


	public void addSingleProduct(int no) {
		AddToCartPage ac = new AddToCartPage();

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

	public void addMultipleProduct(int[] products) throws IOException, InterruptedException {
		AddToCartPage ac = new AddToCartPage();
		WebElement addtocartbtn;
		List<String> itemnames = new ArrayList<String>();
		List<String> cartnames = new ArrayList<String>();

		System.out.println("size of--" + products.length);
		for (int i = 0; i < products.length; i++) {
			System.out.println("i value--" + products[i]);

			itemnames.add(driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[" + products[i] + "]"))
					.getText());
			System.out.println("products[i]--" + products[i]);
			System.out.println("itemnames--" + itemnames);
			if (i == 0) {
				addtocartbtn = driver
						.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[" + products[i] + "]"));
				utils.clickAction(addtocartbtn);
			} else if (i > 0) {
				int minusaddtocart = (products[i] - 1);
				System.out.println("minusaddtocart--" + minusaddtocart);
				addtocartbtn = driver
						.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[" + minusaddtocart + "]"));
				Thread.sleep(1000);
				utils.clickAction(addtocartbtn);
			}

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

}
