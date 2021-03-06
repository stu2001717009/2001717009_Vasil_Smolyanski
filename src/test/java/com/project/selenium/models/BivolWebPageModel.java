package com.project.selenium.models;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * @author Vasil
 * Page Object model used for configuring UI Tests
 */
public class BivolWebPageModel {
	private WebDriver driver;

	WebElement element;
	
	@FindBy(linkText = "Данъкъ Биволъ")
	WebElement MostRead;
	
	/**
	 * @param driver
	 * Initializes driver
	 */
	public BivolWebPageModel(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/**
	 * navigates to https://bivol.bg/
	 */
	public void navigateToMain() {
		driver.get("https://bivol.bg/");
	}
	/**
	 * @param element
	 * Performs a click on the provided element
	 */
	public void PressOnLink(WebElement element) {
		element.click();
	}
	/**
	 * @return WebElement MostRead element
	 */
	public WebElement getMostRead() {
		return MostRead;
		
	}
	/**
	 * @return WebElement that is stored locally
	 * 
	 */
	public WebElement getLink() {
		return element;
		
	}
	/**Finds and saves a WebElement by its text property
	 * @param name String - text property of the supplied element 
	 */
	public void FindElement(String name) {
		this.element = driver.findElement(By.linkText(name));
	}
	

}
