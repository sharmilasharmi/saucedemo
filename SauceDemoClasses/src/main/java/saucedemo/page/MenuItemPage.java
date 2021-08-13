package saucedemo.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import saucedemo.base.BaseClass;

public class MenuItemPage extends BaseClass {
	
	
	@FindBy(xpath="//button[contains(text(),'Open Menu')]")
	public WebElement menu;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	public WebElement logout;
	
	public MenuItemPage() {
		PageFactory.initElements(driver, this);
	}

}
