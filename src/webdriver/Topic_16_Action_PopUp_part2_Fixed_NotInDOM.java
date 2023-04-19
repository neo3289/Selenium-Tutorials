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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Action_PopUp_part2_Fixed_NotInDOM {
	WebDriver driver;
	Actions action;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String dragDropHelperPathPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
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

		// disable pop up find at link:
		// https://stackoverflow.com/questions/43908995/how-to-disabling-notification-using-selenium-for-firefox-browser
		// co doan script disable voi chrome
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);

		// ---chrome disable pop up:
//		Map <String, Integer> prefs = new HashMap<String, Integer>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
		// driver = new ChromeDriver(options);

		driver = new FirefoxDriver(options);
		// add action vao driver
		action = new Actions(driver);
		// ep kieu dữ liệu tường minh:
		jsExecutor = (JavascriptExecutor) driver;

		System.out.println(driver.toString());
		// explicitWait = new WebDriverWait(driver, 30);
		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	/**
	 * POP UP CỐ ĐỊNH: có thể mở theo yêu cầu của user Pop up ko cố định - có thẻ
	 * đóng mở ngẫu nhiên pop up có trong DOM và không có trong DOM CÓ TRONG DOM: dÙ
	 * ĐÓNG HAY MỞ THÌ element của nó vẫn tồn tại trong object module. Không có
	 * trong DOM: khi nó hiển thị thì có, nhưng khi đóng lại thì mất luôn, không tồn
	 * tại trong HTML nữa. Hàm implicitylyWait: nó sẽ ảnh hưởng tới việc tìm
	 * Element: hàm findElement/find Elements
	 * 
	 */

	@Test
	public void TC_01_Fix_PopUp_NOTin_DOM_Tiki() {

		driver.get("https://tiki.vn/");
		/**
		 * NÊN KHAI Báo biến bằng BY vì By chỉ định danh thôi, chưa đi tìm element. Nếu
		 * khai báo bằng WEb element thì nó cần chạy vào tìm element và nó sẽ bị ảnh
		 * hưởng bởi implicit wait, timf lại cho hết implicit timeout thôi, element ko
		 * có trong DOM ko tìm dc. WebElement loginPopupElement =
		 * driver.findElement(By.cssSelector("div.ReactModal_Content")); FindElement:
		 * tim thấy thì ko cần hết timeout, nếu ko thấy sẽ chờ hết timeout. mỗi nửa giây
		 * tìm lại 1 lần, hết timeout sẽ đánh fail test case và ném ra ngoại lệ
		 * NosuchElementException. ko nên dùng để tìm element ko có trong DOM, ngay cả
		 * khi có IF ELSE nó cũng fail ngay sau 10s. FindElements: ko đánh fail test
		 * case và ném ra ngoại lệ và trả về 1 list rỗng.và vẫn chạy tiếp test case. =>
		 * dùng để verify element ko có trong DOM
		 */

		By loginPopUp = By.cssSelector("div.ReactModal__Content");
		// verify pop up chưa hiển thị nên size =0
		Assert.assertEquals(driver.findElements(loginPopUp).size(), 0);
		// click cho bật login PopUp lên
		// data-view-id*=: contains
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(loginPopUp).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopUp).isDisplayed());
		// input[name='tel']
		// img.close-img

		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("0973147168");
		sleepInSecond(3);

		/*
		 * /EG: ví dụ if else cho findElement
		 * 
		 * if (driver.findElement(loginPopUp).isDisplayed()) { driver.
		 * findElement(By.cssSelector("input[name='tel']")).sendKeys("0973147168");
		 * sleepInSecond(2); //... }else { //.. }
		 * 
		 */
		// tiep tuc dang nhap bang email:
		sleepInSecond(3);
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']"))
						.isDisplayed());
		// close pop up
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(3);
		// verify ko hien thi nua
		Assert.assertEquals(driver.findElements(loginPopUp).size(), 0);

	}

//	@Test
	public void TC_02_Fix_PopUp_NOTin_DOM_FACEBOOK() {

		driver.get("https://www.facebook.com/");
		By createAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 1);
		driver.findElement(By.name("firstname")).sendKeys("Automation");
		driver.findElement(By.name("lastname")).sendKeys("FC");
		driver.findElement(By.name("reg_email__")).sendKeys("0974147168");
		driver.findElement(By.name("reg_passwd__")).sendKeys("Automation");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("28");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Jan");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1996");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);

	}

	// @Test
	public void TC_02_Fixed_In_DOM_PopUp() {

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
