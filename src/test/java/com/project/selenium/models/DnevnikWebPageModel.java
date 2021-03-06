package com.project.selenium.models;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vasil
 *Page Object model used for configuring UI Tests
 */
public class DnevnikWebPageModel {
	private WebDriver driver;
	WebElement heading;
	WebElement EnterWebsiteButton;
	
	
	
	/**
	 * @param driver
	 * Driver initialization
	 */
	public DnevnikWebPageModel(final WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/**
	 *  navigate to main webpage
	 */
	public void navigateToMain() {
		driver.get("https://www.dnevnik.bg/");
	}
	
	/**
	 * @return WebElement having an h1 tag
	 */
	public WebElement getHeading () {
		return heading = driver.findElement(By.tagName("h1"));
	}
	/**
	 *  Clicks a button with the following className: bairak_campaign
	 */
	public void PressOnEnterWebsiteButton() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		EnterWebsiteButton = driver.findElement(By.className("homepage"));
		
		EnterWebsiteButton.click();
	}
	
	
	
	
	

}




