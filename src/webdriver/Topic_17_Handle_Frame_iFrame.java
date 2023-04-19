package webdriver;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Handle_Frame_iFrame {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

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
		// TURN OFF NOTIFICATION FIREFORX
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);

		driver = new FirefoxDriver(options);
		//driver = new FirefoxDriver(options);
		
		//ep kieu javascript executor de click cho nhanh:
		jsExecutor = (JavascriptExecutor) driver;
		// driver = new ChromeDriver();
		// luôn khởi tạo sau driver

		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

//@Test
	public void TC_01_IFrame_click_use_Webelement() {
/**
 * click bang web element se bi cham
 */
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(5);
		////a[text()= 'Kyna.vn']/parent::div/folllowing-sibling::div
		Assert.assertTrue(driver.findElement(By.cssSelector("div.fanpage iframe")).isDisplayed());
		// switch qua iframe of facebook dang o index so 1 tren current page
		/** 3cach: lay theo index:, ID HOAC NAME , ELEMENT
		/** driver.switchTo().frame(0);
		 */
		//driver.switchTo().frame(0);
		// cach 3: lay  theo element:
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		System.out.println(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K likes");
		
		// Turn back to previous page
		driver.switchTo().defaultContent();
		
		//switch qua man hinh Chat iframe
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0818103788");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Automation testing");
		sleepInSecond(3);
		// quay lai trang main va search tu excel
		driver.switchTo().defaultContent();
		String keyword = "Excel";
		driver.findElement(By.id("live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(5);
		List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement course : courseNames) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
		}
		
		
	}

//@Test
	public void TC_02_IFrame() {
		// dag code den phut 59 video topic 37
		driver.get("https://skills.kynaenglish.vn/");
		// trang 1 contain iframe 2
		driver.switchTo().frame("2");
		
		//iframe 2 contain iframe 3
		driver.switchTo().frame("3");
		
		// Must come back from 3->2 (not support  3->1)
		driver.switchTo().parentFrame();
		// turn back from 2->1:
		driver.switchTo().defaultContent();
		

	}

//@Test
	public void TC_03_Iframe_JavascriptClick() {
	driver.get("https://skills.kynaenglish.vn/");
	sleepInSecond(5);
	////a[text()= 'Kyna.vn']/parent::div/folllowing-sibling::div
	Assert.assertTrue(driver.findElement(By.cssSelector("div.fanpage iframe")).isDisplayed());
	// switch qua iframe of facebook dang o index so 1 tren current page
	/** 3cach: lay theo index:, ID HOAC NAME , ELEMENT
	/** driver.switchTo().frame(0);
	 */
	//driver.switchTo().frame(0);
	// cach 3: lay  theo element:
	driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
	
	System.out.println(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText());
	Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K likes");
	
	// Turn back to previous page
	driver.switchTo().defaultContent();
	
	
	//border_overlay meshim_widget_widgets_BorderOverlay

	//switch qua man hinh Chat iframe
	driver.switchTo().frame("cs_chat_iframe");
	
	//driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
	/**
	 * Use javascript to click elemement:
	 */
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")));
	
	sleepInSecond(3);
	driver.findElement(By.cssSelector("input.input_name")).sendKeys("John");
	driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0818103788");
	new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
	driver.findElement(By.name("message")).sendKeys("Automation testing");
	sleepInSecond(3);
	// quay lai trang main va search tu excel
	driver.switchTo().defaultContent();
	String keyword = "Excel";
	driver.findElement(By.id("live-search-bar")).sendKeys(keyword);
	driver.findElement(By.cssSelector("button.search-button")).click();
	sleepInSecond(5);
	List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content>h4"));
	for (WebElement course : courseNames) {
		System.out.println(course.getText());
		Assert.assertTrue(course.getText().contains(keyword));
	}
	
	}
@Test
// frame inside a page. eg. embeded a login page in another page
public void TC_04_Frame() {
	// dag code den phut 59 video topic 37
	driver.get("https://netbanking.hdfcbank.com/netbanking/");

	// trang 1 contain iframe 2
	
	driver.switchTo().frame("login_page");
	//thao tac voi user Id
	driver.findElement(By.name("fldLoginUserId")).sendKeys("Johnkennedy");
	driver.findElement(By.cssSelector("a.login-btn")).click();
	//navigate to https://netportal.hdfcbank.com/nb-login/login.jsp
	sleepInSecond(3);
	
	driver.switchTo().defaultContent();
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("keyboard")));  
	Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());


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
