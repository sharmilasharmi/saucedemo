package saucedemo.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import saucedemo.base.BaseClass;

public class ListingPage extends BaseClass {

@FindBy(xpath="//select[@class='product_sort_container']")
public WebElement sortfilter;


@FindBys(@FindBy(xpath="//div[@class='inventory_item_name']"))
public List<WebElement> itemnames;

@FindBys(@FindBy(xpath="//div[@class='inventory_item_price']"))
public List<WebElement> itemprice;


public ListingPage() {
	PageFactory.initElements(driver, this);
}


public void validateSortByPrice(String value) {
	ListingPage lp = new ListingPage();
	List<Double> originalpricelist = new ArrayList<Double>();
	List<Double> temppricelist = new ArrayList<Double>();

	List<WebElement> productpricelist = lp.itemprice;
	for (WebElement productprice : productpricelist) {

		String price = productprice.getText().substring(1);
		double num = Double.parseDouble(price);
		originalpricelist.add(num);
		temppricelist.add(num);
	}

	if (value.equals("lohi")) {
		Collections.sort(temppricelist);
		Assert.assertTrue(originalpricelist.equals(temppricelist));

	} else if (value.equals("hilo")) {
		Collections.sort(temppricelist, Collections.reverseOrder());
		Assert.assertTrue(originalpricelist.equals(temppricelist));

	}
	
	

}

public void validatesortByName(String value) {
	ListingPage lp = new ListingPage();
	List<String> originalnamelist = new ArrayList<String>();
	List<String> tempnamelist = new ArrayList<String>();

	for (WebElement productnames : lp.itemnames) {
		originalnamelist.add(productnames.getText());
		tempnamelist.add(productnames.getText());

	}
	if (value.equals("az")) {
		Collections.sort(tempnamelist);
		Assert.assertTrue(originalnamelist.equals(tempnamelist));

	} else if (value.equals("za")) {
		Collections.sort(tempnamelist, Collections.reverseOrder());
		Assert.assertTrue(originalnamelist.equals(tempnamelist));

	}

}








}
