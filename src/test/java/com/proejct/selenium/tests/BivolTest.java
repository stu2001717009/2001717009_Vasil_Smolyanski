package com.proejct.selenium.tests;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.internal.matchers.Contains;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.project.selenium.models.BivolWebPageModel;

/**
 * @author Vasil
 * Tests the UI of the webpage trafficnews
 */
public class BivolTest {
	WebDriver driver;
	BivolWebPageModel model;
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
		
		model = new BivolWebPageModel(driver);
	}
	/**
	 * Navigates to Link Окото на бивола and check the corresponding URL and heading
	 */
	
	@Test
	public void checkAnalysisPage() {
		final String expectedURL = "https://bivol.bg/category/analysis";
		model.navigateToMain();
		model.FindElement("Окото на бивола");
		WebElement linkPl = model.getLink();
		model.PressOnLink(linkPl);
		final String result = driver.getCurrentUrl();
		collector.checkThat(result,IsEqual.equalTo(expectedURL));
		WebElement mostRead = model.getMostRead();
		collector.checkThat(mostRead.getText(),IsEqual.equalTo("Данъкъ Биволъ"));

	}
	
	/**
	 * Navigates to Link Потеря and check the corresponding URL and heading
	 */
	
	@Test
	public void checkInvestigationPage() {
		final String expectedURL ="https://bivol.bg/category/investigations";
		model.navigateToMain();
		model.FindElement("ПОТЕРЯ");
		WebElement linkGb = model.getLink();
		model.PressOnLink(linkGb);
		final String result = driver.getCurrentUrl();
		collector.checkThat(result,IsEqual.equalTo(expectedURL));
		WebElement heading = driver.findElement(By.tagName("h1"));
		collector.checkThat(heading.getText(),IsEqual.equalTo("ПОТЕРЯ"));
	}

	

/**
 * Disposes of all resources which were in use
 */
@After
public void after() {
	driver.close();
}
}