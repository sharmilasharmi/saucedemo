package saucedemo.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import saucedemo.page.AddToCartPage;
import saucedemo.page.HomePage;
import saucedemo.page.ListingPage;
import saucedemo.page.MenuItemPage;

public class BaseClass {

	public static WebDriver driver = null;
	public static Properties prop = null;
	public static String browsername, url, regname, pass;
	public static boolean browserVal = false;

	public static HomePage hp;
	public static AddToCartPage ac;
	public static ListingPage lp;
	public static MenuItemPage menupage;

	@BeforeClass()
	public void setUp() throws IOException {
		launchBrowser();
		if (driver != null) {
			hp = PageFactory.initElements(driver, HomePage.class);
			ac = PageFactory.initElements(driver, AddToCartPage.class);
			lp = PageFactory.initElements(driver, ListingPage.class);
			menupage = PageFactory.initElements(driver, MenuItemPage.class);

		}
	}

	public void readFromProp() {
		prop = new Properties();

		try {
			prop.load(new FileInputStream(System.getProperty("user.dir") + "/configuration/prop.properties"));

			browsername = prop.getProperty("browser");
			System.out.println("browsername--" + browsername);
			url = prop.getProperty("saucedeom_url");
			regname = prop.getProperty("user_name");
			pass = prop.getProperty("pass_word");

			System.out.println("Property file read sucessfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void launchBrowser() throws IOException {
		readFromProp();
		try {
			if (browsername.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeoptions = new ChromeOptions();
				chromeoptions.addArguments("--disable-extensions");
				driver = new ChromeDriver(chromeoptions);
				System.out.println("Launched chrome");
			} else if (browsername.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				System.out.println("Launched firefox");
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(url);
	}

	@AfterClass()
	public void closeBrowser() {
		driver.quit();
		System.out.println("driver closed");
		
	}

}
