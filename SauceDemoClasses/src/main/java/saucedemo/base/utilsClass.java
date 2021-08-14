package saucedemo.base;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import saucedemo.page.HomePage;

public class utilsClass extends BaseClass {

	public void waitForEleVisible(WebElement ele, int time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (NoSuchElementException e) {
			System.out.println("Unable to locate the element--" + ele);
		}
	}

	public void clickAction(WebElement ele) throws IOException {
		try {
			waitForEleVisible(ele, 20);
			ele.click();
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			System.out.println("Unable to locate the element--" + ele);

		}
	}

	public void typeText(WebElement ele, String msg) {
		try {
			waitForEleVisible(ele, 20);
			ele.clear();
			Thread.sleep(500);
			for (int i = 0; i < msg.length(); i++) {
				ele.sendKeys(Character.toString(msg.charAt(i)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ele);
		}
	}

	public void selectDropDownUsingValue(WebElement ele, String value) {

		try {
			waitForEleVisible(ele, 20);
			Select sel = new Select(ele);
			sel.selectByValue(value);
		} catch (WebDriverException e) {
			e.printStackTrace();
			System.out.println(ele);
		}
	}

	public Boolean elementDisplayed(WebElement ele) {
		try {
			waitForEleVisible(ele, 20);
			if (ele.isDisplayed()) {
				return true;

			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
