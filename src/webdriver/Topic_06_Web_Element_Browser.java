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


public class Topic_06_Web_Element_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("OS.name");
	

	@BeforeClass
	public void beforeClass() {
//		if (osName.contains("Mac OS")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
//			
//		}else {
//			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		}
	//	driver = new ChromeDriver();
		//dung firefore de chay selenium 
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}

	@Test
	public void TC_01_Url(){
		
		driver.get("http://live.techpanda.org/");
		//use XPATH OR CSS
	   //driver.findElement(By.xpath("//div[@class='footer-container']//a@[title='My Account']")).click();
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		
		//WAIT implicily ap dung cho tim element, chu ko phai dung cho chuyen trang
		sleepInSecond(2);
	
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//click vao Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
			
	}
	
	@Test
	public void TC_02_Title() {
		//
		driver.get("http://live.techpanda.org/");
		//use XPATH OR CSS
	   //driver.findElement(By.xpath("//div[@class='footer-container']//a@[title='My Account']")).click();
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		
		//WAIT implicily ap dung cho tim element, chu ko phai dung cho chuyen trang
		sleepInSecond(2);
	
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//click vao Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_03_Navigation() {
		//
		driver.get("http://live.techpanda.org/");
		//use XPATH OR CSS
	   //driver.findElement(By.xpath("//div[@class='footer-container']//a@[title='My Account']")).click();
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		
		//WAIT implicily ap dung cho tim element, chu ko phai dung cho chuyen trang
		sleepInSecond(2);
	
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//click vao Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		
		//navigate to Login
		driver.navigate().back();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//forward lai trang dang ky
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_getPageSource_HTML() {
		//
		driver.get("http://live.techpanda.org/");
		//use XPATH OR CSS
	   //driver.findElement(By.xpath("//div[@class='footer-container']//a@[title='My Account']")).click();
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
	
	
	//verify page HTML CO CHUA 1 CHUOI MONG MUOI
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		sleepInSecond(2);
		//click vao Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

		
		
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
		driver.quit();
	}
}
