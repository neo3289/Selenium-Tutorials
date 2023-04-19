package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_12_Alert {
	WebDriver driver;
	Actions action;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	String authenFireforx = projectPath + "\\AutoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "\\AutoIT\\authen_chrome.exe";
	String username1 = "admin";
	String password1 = "admin";

	@BeforeClass
	public void beforeClass() {

		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {

			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		explicitWait = new WebDriverWait(driver, 30);
		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

//	@Test
	public void TC_01_Accept_Alert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		// 1. co the swith qua tuong tac luon:

		// driver.switchTo().alert();
		alert = driver.switchTo().alert();
		// 2. can wait truoc, khi nao xuat hien moi switch va tuong tac:
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		// Accept cai alert nay
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
				"You clicked an alert successfully");
	}

//	@Test

	public void TC_02_Confirm_Alert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		// 1. co the swith qua tuong tac luon:

		// driver.switchTo().alert();
		// alert = driver.switchTo().alert();
		// 2. can wait truoc, khi nao xuat hien moi switch va tuong tac:
		// wait và switch qua luôn
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		// Accept cai alert nay
		// alert.accept();
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	// @Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		// 1. co the swith qua tuong tac luon:

		// driver.switchTo().alert();
		// alert = driver.switchTo().alert();
		// 2. can wait truoc, khi nao xuat hien moi switch va tuong tac:
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		// nhap text sau do clcik vao Accept cai alert nay
		String courseName = "Fullstack Selenium Java";
		alert.sendKeys(courseName);
		alert.accept();

		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName);
	}

	// @Test
	public void TC_04_Athentication_Alert() {

		driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());

	}

	//@Test
	public void TC_05_Athentication_Alert() {
		// truyen truc tiep username password
		// http://+username :password @ the-internet.herokuapp.com/basic_auth
		driver.get("http://the-internet.herokuapp.com/");
		// get href de tra value = url
		String authenURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		driver.get(passUserAndPassToUrl(authenURL, "admin", "admin"));
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	 @Test
	public void TC_06_Athentication_Alert_AutoIT() throws IOException {
		if (driver.toString().contains("Chrome")) {
			// Runtime.getRuntime().exec: thuc thi 1 file exe trong code Java
			Runtime.getRuntime().exec(new String[] { authenChrome, username1, password1 });

		} else if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { authenFireforx, username1, password1 });
		}

		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());

	}

	public String passUserAndPassToUrl(String url, String username, String pw) {
		String[] arrayURL = url.split("//");
		return arrayURL[0] + "//" + username + ":" + pw + "@" + arrayURL[1];
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// dung ham thread de tranh lap di lap lai nhieu lan
	public void sleepInSecond(long timInSecond) {
		try {
			Thread.sleep(timInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
