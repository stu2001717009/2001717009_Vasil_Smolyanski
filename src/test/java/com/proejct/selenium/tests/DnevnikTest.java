package com.proejct.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.project.selenium.models.DnevnikWebPageModel;
import com.project.selenium.models.BivolWebPageModel;

/**
 * @author Vasil
 * Tests the UI of the webpage Mediapool
 */
public class DnevnikTest {
	WebDriver driver;
	DnevnikWebPageModel model;
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	/**
	 * Sets up driver info
	 */
	@BeforeClass
	public static void setupClass() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	}
	
	/**
	 * Initial test configuration - adds the correct driver and PageObjectModel
	 */
	@Before
	public void setup() {
		driver = new ChromeDriver();
		
		model = new DnevnikWebPageModel(driver);
	}
	
	/**
	 * Test case which ensures that a required button is successfully pressed and ensures that a heading value is as expected
	 */
	@Test
	public void checkEnterWebsite() {
		final String expectedURL = "https://www.dnevnik.bg/?ref=logo";
		model.navigateToMain();
		model.PressOnEnterWebsiteButton();
		WebElement heading = model.getHeading();
		final String result = driver.getCurrentUrl();
		collector.checkThat(result,IsEqual.equalTo(expectedURL));
		collector.checkThat(heading.getText(),IsEqual.equalTo("Дневник - Новини от България и света, Анализи, Видео."));
	}
	
	/**
	 * Clicks on log in button, provides sample data to login form and initiate log in procedure. Checks whether an error would appear when providing wrong username and password
	 */
	@Test
	public void CheckLoginButton() {
		model.navigateToMain();
		WebElement LoginBtn = driver.findElement(By.className("i-profile"));
		LoginBtn.click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("a");
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys("a");
		WebElement submit = driver.findElement(By.linkText("Вход"));
		submit.click();
		WebElement error = driver.findElement(By.className("error"));
		collector.checkThat(error.getText(),IsEqual.equalTo("Въведен е невалиден email адрес."));	
	}
	
	/**
	 * Carries out a search with the parameter България and checks if the resulting URL is correct
	 */
	@Test
	public void CheckSearch() {
		final String expectedURL = "https://www.dnevnik.bg/search/%25D0%2591%25D1%258A%25D0%25BB%25D0%25B3%25D0%25B0%25D1%2580%25D0%25B8%25D1%258F";
		model.navigateToMain();
		WebElement searchbtn = driver.findElement(By.className("i-search"));
		searchbtn.click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement input = driver.findElement(By.id("query"));
		input.sendKeys("България");
		input.sendKeys(Keys.RETURN);
		final String result = driver.getCurrentUrl();
		collector.checkThat(result,IsEqual.equalTo(expectedURL));
	}
	
	
	/**
	 * Navigates to an uploaded article and checks if we can give positive grade if not logged
	 */
	@Test
	public void CheckUpComment() {
		final String expectedURL = "https://trafficnews.bg/horoskopi/";
		driver.get("https://www.dnevnik.bg/detski_dnevnik/2021/03/06/4182496_nevidimi_i_bezglasni_kak_mediite_v_bulgariia_pishat_za/?ref=home_mainStory#comments-wrapper");
		WebElement plus = driver.findElement(By.className("e-plus"));
		plus.click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement needToBeLogged = driver.findElement(By.id("commentLoginPrompt"));
		final String result = needToBeLogged.getCssValue("display");
		collector.checkThat(result, IsEqual.equalTo("block"));
	}


/**
 * Disposes of all resources which were in use
 */
@After
public void after() {
	driver.close();
}
}
