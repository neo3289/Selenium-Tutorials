package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Window;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class Topic_06_Web_Element_Elements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("OS.name");
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#development");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}

	//test case nao khong muon chay thi comment vao @test
	//@Test
	public void TC_01_isDisplayed(){
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if(driver.findElement(emailTextbox).isDisplayed()) {
			System.out.println("Email textbox is displayed");
		}
		else {
			System.out.println("Email textbox is not displayed");
		}
		
		//Textarea
		if(driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium GRID");
			System.out.println("educationTextArea is displayed");
		}
		else {
			System.out.println("educationTextArea is not displayed");
		}
		
		//Radio
		//Textarea
		
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age Under 18 is displayed");
		}
		else {
			System.out.println("Age Under 18 is not displayed");
		}
		if(driver.findElement(nameUser5Text).isDisplayed()) {
		
			System.out.println("Name User 5 is displayed");
		}
		else {
			System.out.println("Name User 5 is not displayed");
		}
			
	}
	
	//@Test
	public void TC_02_isEnable() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//check password is enable or not
		if(driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password textbox is enabled");
		}
		else {
			System.out.println("Password textbox is enable is not enabled");
		}
		
		if(driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("biography textbox is enabled");
		}
		else {
			System.out.println("biography textbox is enable is not enabled");
		}
		
		if(driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("emailTextbox textbox is enabled");
		}
		else {
			System.out.println("emailTextbox textbox is enable is not enabled");
		}
		
		
	}
	
	//@Test
	public void TC_03_isSelected() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
	   //verify checkbox/radio button are selected?
			Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
			Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
			
			//click vao checkbox/radio button are selected?
			driver.findElement(ageUnder18Radio).click();
			driver.findElement(developmentCheckbox).click();
		sleepInSecond(2);
		//sau do verify 2 element do dc chon roi:
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
	}
	
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		   driver.findElement(By.id("email")).sendKeys("ladang@gmail.com");
		   By passwordTextbox = By.id("new_password");
		   By signUpButton = By.id("create-account-enabled");
		   
		   driver.findElement(passwordTextbox).sendKeys("abc");
		 //  driver.findElement(signUpButton).click();
		   sleepInSecond(3);
		   //verify lowcase
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		   
		//clear du lieu cu va nhap lai moi ABC
		   driver.findElement(passwordTextbox).clear();
		   driver.findElement(passwordTextbox).sendKeys("ABC");
		 //  driver.findElement(signUpButton).click();
		   sleepInSecond(3);
		   //verify Uppercase
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		   
		 //clear du lieu cu va nhap lai moi SEND KEY 123
		   driver.findElement(passwordTextbox).clear();
		   driver.findElement(passwordTextbox).sendKeys("123");
		  // driver.findElement(signUpButton).click();
		   sleepInSecond(3);
		   //verify number
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed()); 
		   
		 //clear du lieu cu va nhap lai moi send special key !@#
		   driver.findElement(passwordTextbox).clear();
		   driver.findElement(passwordTextbox).sendKeys("!@#");
		//   driver.findElement(signUpButton).click();
		   sleepInSecond(3);
		   //verify lowcase
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed()); 
		   
		   //clear du lieu cu va nhap lai moi send 8 characters minimum
		   driver.findElement(passwordTextbox).clear();
		   driver.findElement(passwordTextbox).sendKeys("ABCZYZGHM");
		//   driver.findElement(signUpButton).click();
		   sleepInSecond(3);
		   //verify lowcase
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		   Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed()); 
		   
		   //clear du lieu cu va nhap lai Completed all >8characters 
		   //verify fulldata
		   driver.findElement(passwordTextbox).clear();
		   driver.findElement(passwordTextbox).sendKeys("123abcABC!@#");
		//   driver.findElement(signUpButton).click();
		   sleepInSecond(3);
		   //verify lowcase
		   Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		   Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		   Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		   Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		   Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed()); 
		   
	}
	
	
	
	//dung ham thread de tranh lap di lap lai nhieu lan
	public void sleepInSecond(long timInSecond)
	{
		try {
			Thread.sleep(timInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	@AfterClass
	public void afterClass() {
	//driver.quit();
	}
}
