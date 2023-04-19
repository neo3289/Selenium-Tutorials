package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Window;
import java.util.List;
import java.util.Random;
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

import javaTester.Topic_05_Random;


public class Topic_06_Web_Element_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("OS.name");
	String emailAddress, firstName, lastName, password, midname, fullName;
	Random rand;
	
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		rand = new Random();
		emailAddress= "La"+ rand.nextInt(99999)+"@gmail.com";
		firstName = "La";
		lastName = "Dang";
		midname = "Thi";
		fullName= firstName + " " + midname+ " "+lastName;
		password="12345678";
				
		
		
		
		}

	//test case nao khong muon chay thi comment vao @test
//	@Test
	public void TC_01_emptyEmailPassword(){
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		
		sleepInSecond(2);
	
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
	//	driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
			
	}
	
//	@Test
	public void TC_02_LoginInvalidEmail() {
		
        driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		
		sleepInSecond(2);
		driver.findElement(By.id("email")).sendKeys("12343234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("123456");
	
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
		
		
	}
	
//	@Test
	public void TC_03_Pwlessthan6() {
		
		 driver.get("http://live.techpanda.org/");
			
			driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
			
			sleepInSecond(2);
			driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("123");
		
			driver.findElement(By.id("send2")).click();
			sleepInSecond(2);
			
			Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	//@Test
	public void TC_04_IncorrectEmail_Pass() {
		
		 driver.get("http://live.techpanda.org/");
			
			driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
			
			sleepInSecond(2);
			//su dung bien de random email
			driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("123123123");
		
			driver.findElement(By.id("send2")).click();
			sleepInSecond(10);
			
			
			Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(),"Invalid login or password.");
		
		
	}
	
	@Test
	public void TC_05_CreateNewAcc() {
		
		 driver.get("http://live.techpanda.org/");
			
			driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
			
			sleepInSecond(2);
			
			driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	
			Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Create an Account']")).getText(),"CREATE AN ACCOUNT");
			sleepInSecond(2);
			driver.findElement(By.id("firstname")).sendKeys(firstName);
			driver.findElement(By.id("middlename")).sendKeys(midname);
			driver.findElement(By.id("lastname")).sendKeys(lastName);
			driver.findElement(By.id("email_address")).sendKeys(emailAddress);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("confirmation")).sendKeys(password);
			driver.findElement(By.cssSelector("button[title='Register']")).click();
			
			Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");

			
		String ContactInformationText= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(ContactInformationText);
		System.out.println(fullName);
	
		Assert.assertTrue(ContactInformationText.contains(fullName));
		Assert.assertTrue(ContactInformationText.contains(emailAddress));
	
//		//logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_06_Login_Valid_Account() {
		
		 driver.get("http://live.techpanda.org/");
			
			driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
			
			sleepInSecond(2);
			//su dung bien de random email
			driver.findElement(By.id("email")).sendKeys(emailAddress);
			driver.findElement(By.id("pass")).sendKeys(password);
		
			driver.findElement(By.id("send2")).click();
			sleepInSecond(2);
			
			
			String ContactInformationText= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
			System.out.println(ContactInformationText);
			System.out.println(fullName);
		
			Assert.assertTrue(ContactInformationText.contains(fullName));
			Assert.assertTrue(ContactInformationText.contains(emailAddress));
		

		
		
	}
	
	
	
	

	
	
	//test case 5-6 buoi sau hoc
	
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
	driver.quit();
	}
}
