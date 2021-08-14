package saucedemo.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuItemPage {

	@FindBy(xpath = "//button[contains(text(),'Open Menu')]")
	public WebElement menu;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	public WebElement logout;

}
