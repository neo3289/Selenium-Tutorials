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


public class Topic_06_Web_Element_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("OS.name");
	
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
	
	@Test
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
	
	@Test
	public void TC_04_IncorrectEmail_Pass() {
		
		 driver.get("http://live.techpanda.org/");
			
			driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
			
			sleepInSecond(2);
			driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("123123123");
		
			driver.findElement(By.id("send2")).click();
			sleepInSecond(10);
			
			
			Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(),"Invalid login or password.");
		
		
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
	//driver.quit();
	}
}
