package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_StaticWait_part4 {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Alert alert;
	Select select;
	Window window;
	Random rand = new Random();
	String employeeAddress = "testing" + String.valueOf(rand.nextInt(99999)) + "@hotmail.vn";
	String emailAddress, firstName, lastName, password, midname, fullName;
	
	

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {

			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			// phai cai chrome v106 va browser 106
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		driver = new FirefoxDriver();
		/*
		 * implicit wait cũng là wait linh động, như explicit, nếu tìm thấy thì nó dừng,
		 * ko chạy hết thời gian. findElement: ko tìm thấy đánh fail test case và ném ra
		 * throw exception findElements : không đánh fail test case và ko show
		 * exception, mà chỉ về list empty
		 */
		
		/*
		 *  về tính năng cả 4 case này đều giống nhau, ko ảnh hưởng gì cả
		 
		// case1

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// case2
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().window().maximize();
		
		// case3
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		// case4
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		* 
		 */
		rand = new Random();
		emailAddress= "La"+ rand.nextInt(99999)+"@gmail.com";
		firstName = "La";
		lastName = "Dang";
		midname = "Thi";
		fullName= firstName + " " + midname+ " "+lastName;
		password="12345678";
		


	}
@Test
	public void TC_01_timeout_Less_than_5Second() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	
	}

	@Test

	public void TC_02_timeout_Equal_5Second() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_03_timeout_Morethan_5Second() {
		// tìm thấy rồi thì nó ko chờ nữa chạy 5s là dừng
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	
//	@Test
	public void TC_05_CreateNewAcc() {
		// NẾU KO SET implicit THÌ MẶC ĐỊNH =0, nên set ngay từ đầu trên beforetest
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timInSecond) {
		try {
			Thread.sleep(timInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	// kieu du lieu cua ham la webdriver
	public WebDriver getDriver() {
		return driver;

	}

}
