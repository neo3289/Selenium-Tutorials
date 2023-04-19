package webdriver;

import static org.testng.Assert.assertTrue;

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

public class Topic_18_Handle_WindowTab {
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
	public void TC_01_Github_With_Two_WindowTab() {
	// truy cap trang: https://automationfc.github.io/basic-form/index.html
	driver.get("https://automationfc.github.io/basic-form/index.html");
	String githubId= driver.getWindowHandle();
	System.out.println("githubId:= "+ githubId);
	
	//githubId:= bc81259f-840d-4317-ab94-32afe114f2b0
	System.out.println("page title:= "+ driver.getTitle());
	
		//click vao google link
	
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(3);
	System.out.println("page title := "+ driver.getTitle());
	// return all ID of available tabs:
	SwitchToWindowByID(githubId);
	
	
	// driver dang tai trang google roi:
	System.out.println("page title:= Google"+ driver.getTitle());
	
	// quay lai trang github
	//tra ve ID CUA DRIVER đang đứng tại đó
	String googleId = driver.getWindowHandle();
	SwitchToWindowByID(googleId);
	System.out.println("page title:= GitHub"+ driver.getTitle());
	
	
		
	}

//@Test
public void TC_02_Swith_Greater_Two_WindowTab() {
	// truy cap trang: https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String githubId= driver.getWindowHandle();
		System.out.println("githubId:= "+ githubId);
		
		//githubId:= bc81259f-840d-4317-ab94-32afe114f2b0
		System.out.println("page title:= "+ driver.getTitle());
		
			//click vao google link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		SwitchToWindowByTitle("Google");
		System.out.println("page title -Google:= "+ driver.getTitle());
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Automation FC");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
		sleepInSecond(10);
	
		// quay lai trang github
		SwitchToWindowByTitle("Selenium WebDriver");
		System.out.println("page title - GITHUB:= "+ driver.getTitle());
		sleepInSecond(10);
		// CHUYEN QUA TRANG FACEBOOK
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		
		SwitchToWindowByTitle("Facebook – log in or sign up");
		System.out.println("page title:= Facebook:"+ driver.getTitle());
		driver.findElement(By.id("email")).sendKeys("la.dang@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("ladang");
		sleepInSecond(3);
		
		// quay lai trang github
		SwitchToWindowByTitle("Selenium WebDriver");
		System.out.println("page title - GITHUB:= "+ driver.getTitle());
		
		// switch to TIKI
		SwitchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("page title -TIKE:= "+ driver.getTitle());
		closeAllWindowByTitle(githubId);
		sleepInSecond(3);
		System.out.println("page title parentId:= "+ driver.getTitle());

	}

@Test
	public void TC_03_TechPanda() {
	
	driver.get("http://live.techpanda.org/");
	driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	sleepInSecond(3);
	driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
	
	driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
	
	driver.findElement(By.xpath("//button[@title='Compare']")).click();
	sleepInSecond(3);
	
	SwitchToWindowByTitle("Products Comparison List - Magento Commerce");
	System.out.println("page title: "+ driver.getTitle());
	
	driver.findElement(By.xpath("//button[@title='Close Window']")).click();
	sleepInSecond(3);
	
	SwitchToWindowByTitle("Mobile");
	System.out.println("page title: "+ driver.getTitle());
	
	}
//@Test

public void TC_04_Frame() {
	//https://dictionary.cambridge.org/vi/
driver.get("https://dictionary.cambridge.org/vi/");
//...............
//...............


}
/**
 * Viet ham de switch dùng cho 2 tab - ONLY 2 TAB
 * 
 */
public void SwitchToWindowByID (String pageId) {
	Set<String> allWindowID = driver.getWindowHandles();
	for (String id : allWindowID) {
		// != khac bang KO so sanh vung nho nen phai dung equal 
		// vung tham chieu thi dung so sanh vung nho equal
		// neu item nào mà khác với ID truyền vào thì switch sang  nó:
		if(!id.equals(pageId)) {
			driver.switchTo().window(id);
			sleepInSecond(3);
		}
	}
		
}

/**
 * Viet ham de switch dùng cho > 2 window tab and title of pages are unique
 */
public void SwitchToWindowByTitle(String pageTitle) {
	Set<String> allWindowTitle = driver.getWindowHandles();
	
	// dùng vòng lặp để duyệt qua từng item:
	// vòng lặp for iterator.....
	for (String runWindow  : allWindowTitle) {
		driver.switchTo().window(runWindow);
		String actualPageTile = driver.getTitle();
		
		if(actualPageTile.equals(pageTitle)) {
			// nếu tìm thấy page item thì thoát vòng lặp không cần duyệt nữa
			break;
		}
	}
		
}


/**
 * Viet ham close all windows without PARENT window
 */
public void closeAllWindowByTitle(String parentID) {
	Set<String> allWindowID = driver.getWindowHandles();
	
	// dùng vòng lặp để duyệt qua từng item:
	// vòng lặp for iterator.....
	for (String id  : allWindowID) {
		if(!id.equals(parentID)) {
			driver.switchTo().window(id);
			driver.close();
			
		}
	}
	driver.switchTo().window(parentID);
		
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
