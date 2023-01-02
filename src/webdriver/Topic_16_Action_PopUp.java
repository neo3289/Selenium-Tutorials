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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Action_PopUp {
	WebDriver driver;
	Actions action;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String dragDropHelperPathPath = projectPath +"\\dragAndDrop\\drag_and_drop_helper.js";
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {

		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {

			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		//disable pop up find at link: https://stackoverflow.com/questions/43908995/how-to-disabling-notification-using-selenium-for-firefox-browser
		// co doan script disable voi chrome
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		
		
		//---chrome disable pop up:
//		Map <String, Integer> prefs = new HashMap<String, Integer>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
		//driver = new ChromeDriver(options);
		
		
		driver = new FirefoxDriver(options);
		//add action vao driver
		action = new Actions(driver);
		//ep kieu dữ liệu tường minh:
		jsExecutor= (JavascriptExecutor) driver;
		
		System.out.println(driver.toString());
		explicitWait = new WebDriverWait(driver, 30);
		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//@Test
	public void TC_01_Double_Click() {

		driver.get("https://ngoaingu24h.vn");
		
		
	}
//	@Test
	public void TC_02_Fix_PopUp_in_DOM() {
		
		driver.get("https://ngoaingu24h.vn");
		By loginPopUp = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		//verify popup is undisplayed:
		Assert.assertFalse(driver.findElement(loginPopUp).isDisplayed());
		
		// Click vao button Login:
		driver.findElement(By.cssSelector("button.login_")).click();
		//verify popup is displayed:
		Assert.assertTrue(driver.findElement(loginPopUp).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-v1")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	
	}
	@Test
	public void TC_02_Fixed_In_DOM_PopUp() {
		driver.get("https://skills.kynaenglish.vn/");
		//By loginFixedPopUp = By.cssSelector("div#k-popup-account-login-mb div.modal-content");
		By loginFixedPopUp = By.cssSelector("div#k-popup-account-login");
		
		//verify popup is undisplayed:
		Assert.assertFalse(driver.findElement(loginFixedPopUp).isDisplayed());
		
		// Click vao button Login:
		driver.findElement(By.cssSelector("a.login-btn")).click();
		//verify popup is displayed:
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(loginFixedPopUp).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456789");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		
	}
	
//	@Test
	// dung drag and drop HTM4 không chạy cho html5
	public void TC_04_Drag_Drop_html5() throws IOException {
		
		
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
