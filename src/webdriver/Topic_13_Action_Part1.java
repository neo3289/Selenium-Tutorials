package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Desktop.Action;
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

public class Topic_13_Action_Part1 {
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
		action = new Actions(driver);
		System.out.println(driver.toString());
		explicitWait = new WebDriverWait(driver, 30);
		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

//	@Test
	public void TC_01_HoverElement_Tooltip() {

		driver.get("https://automationfc.github.io/jquery-tooltip/");
		// peform() la ham bat buoc de chay cac action, giong nhu xe may phai open
		// ignition moi khoi dong mac du minh lam cac action khac
		// movetoElement dung su ly bat element trong menu
		// Release(): release chuot
		// hover chuot
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);
		/*
		 * setTimeout(() => {debugger;}, 2000);
		 * 
		 * 2000ms = 2s
		 * 
		 * Dùng Debug/ Dùng setTimeout đều được
		 * 
		 * 1 máy chỉ có 1 con chuột/ bàn phím
		 * 
		 * Trong lúc đang run script mà di chuột bị mất sự kiện/ action
		 * 
		 * Xung đột các sự kiện
		 * 
		 * Ko được dùng chuột và bàn phím để làm gì hết
		 * 
		 * Có 1 máy riêng để run auto
		 */
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

	}
	// @Test

	public void TC_02_HoverToElement_myntra() {

		driver.get("https://www.myntra.com/");
		// Hover vao Kids
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']")))
				.perform();
		sleepInSecond(10);

		driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']\"")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");

	}

	@Test
public void TC_03_HoverToElement_fahasa() {

		driver.get("https://www.fahasa.com/");
		// handle popup de xu ly quang cao tat manual
		sleepInSecond(20);
		action.click();
		// Hover lan 1:
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(3);

		
		//a[@title='Sách Trong Nước']
		// Hover lan 2:
	//	action.moveToElement(driver.findElement(By.xpath("//a(@title='Sách Trong Nước')"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		sleepInSecond(3);
		//// div[@contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']
		driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']"))
				.click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']"))
						.isDisplayed());

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
