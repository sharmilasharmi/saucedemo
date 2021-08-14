package saucedemo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import saucedemo.base.BaseClass;
import saucedemo.base.utilsClass;

public class HomePage extends BaseClass {

	@FindBy(id = "user-name")
	public WebElement username;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(id = "login-button")
	public WebElement loginbutton;

	@FindBy(xpath = "//span[text()='Products']")
	public WebElement ptoducttitle;
}
