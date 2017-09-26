package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;

	By searchBox = By.name("q");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void search(String searchWord) {
		WebElement searchEl = driver.findElement(searchBox);
		searchEl.clear();
		searchEl.sendKeys(searchWord);
	}

}
