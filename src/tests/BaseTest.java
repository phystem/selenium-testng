package tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import util.ExcelUtil;

public class BaseTest {

	WebDriver driver;
	String url;

	private void createDriver(String browser) {
		System.out.println("Creating driver " + browser);
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println(browser + " not Configured . Defaulting to Firefox");
			driver = new FirefoxDriver();
			break;
		}
	}

	@BeforeSuite
	@Parameters({ "dataFile" })
	public void beforeSuite(String dataFile) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Madhanraj\\Desktop\\SeleniumPresentation\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver",
				"C:\\Users\\Madhanraj\\Desktop\\SeleniumPresentation\\IEDriverServer.exe");
		ExcelUtil.load(dataFile);
	}

	@BeforeClass
	@Parameters({ "browser", "url" })
	public void init(String browser, String url) {
		createDriver(browser);
		this.url = url;
		driver.get(url);
		takeScreenShot("Url Loaded");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void takeScreenShot(String name) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(name + ".png");
		try {
			FileUtils.copyFile(screenshot, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
