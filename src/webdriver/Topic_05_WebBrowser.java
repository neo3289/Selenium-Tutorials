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

import okio.Timeout;

@Test
public class Topic_05_WebBrowser {
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


	public void TC_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
			
			//verify kiem tra 
			// Assert.assertTrue -> kiem tra dieu kien tra ve la dung
			//Assert.assertFalse -> trả ve thuc te vs mong doi # nhau
			//Assert.assertEquails -> kiem tra thuc te vs mong doi  = nhau
			
			
			Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
			Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
			Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
			Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
			Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
			Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

		
	}
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	//	driver.findElement(By.id("txtFirstname-error")).send
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kenedy");
		driver.findElement(By.id("txtEmail")).sendKeys("123@345@789");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@345@789");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0973147168");		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		}
	@Test
	public void TC_03_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kenedy");	
		driver.findElement(By.id("txtEmail")).sendKeys("johnwick@gmail.net");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnwick@gmail.com");	
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");	
		driver.findElement(By.id("txtPhone")).sendKeys("0973147168");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	@Test
	public void TC_04_Passwork_Lessthan6() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kenedy");	
		driver.findElement(By.id("txtEmail")).sendKeys("johnwick@gmail.net");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnwick@gmail.net");	
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("128");	
		driver.findElement(By.id("txtPhone")).sendKeys("0973147168");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
	}
	@Test
	public void TC_05_InCorrec_ConfirmaPw() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kenedy");	
		driver.findElement(By.id("txtEmail")).sendKeys("johnwick@gmail.net");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnwick@gmail.net");	
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");	
		driver.findElement(By.id("txtPhone")).sendKeys("0973147168");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void TC_06_Invalid_PhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.id("txtFirstname")).sendKeys("John Kenedy");	
		driver.findElement(By.id("txtEmail")).sendKeys("johnwick@gmail.net");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnwick@gmail.net");	
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("097314716890");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	
	@Test
	public void TC_01_Webelements() {
		//close current tab, neu = 1 thi cung dong luon trinh duyet
		driver.close();
		
		// close the browser , don't care how many tab
		driver.quit();
		
		//co the luu vao thanh 1  bien de su dung cho step sau-> dung lai nhieu lan (clean code)
		driver.findElement(By.xpath(""));
		//E.g
		//Tim 1 element
		WebElement emailTextbox = driver.findElement(By.xpath(""));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		//bad code
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		//có thể sử dụng luôn không cần tạo biến
		
		//tim nhieu element
		//Listweb elements: chia nhieu web element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		//open an  1 URL
		driver.get("https://facebook.com/");
		//so sanh 2 bien
		assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		String Vietnamesepage = driver.getTitle();
		assertEquals(Vietnamesepage, "https://www.facebook.com/");
		
		//tra ve Source code cua HTML hien tai
		//dung verify tương đối
		 driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains(""));
		
		//tra ve title cua page hien tai
		Assert.assertEquals(driver.getTitle(), "abc");
		
		//lay ra id cua window/tab ma drvier dag active
		String loginWindowId =driver.getWindowHandle();
		
		// lay ra ID CUA TAT CA Windows
		Set<String> allWinId = driver.getWindowHandles();
		
		// Kieu Option rat nhieu giong nhu driver, lien quan den xu ly ham cookie/cache
		Options opt = driver.manage();
		opt.getCookies();
		opt.logs();
		opt.notify();
		
		//testcase khac set coookie vao lai va ko can login
		
		Timeouts time = opt.timeouts();
		time.implicitlyWait(5, TimeUnit.SECONDS);//5s=5000s=5000000 milcros
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		
		//khoang thoi gian cho page load xong trong vòng bao nhiêu s
		time.pageLoadTimeout(5, TimeUnit.MILLISECONDS);
		
		//khoang thoi gian cho script thuc thi
		time.setScriptTimeout(5, TimeUnit.MILLISECONDS);
		
		//window
		org.openqa.selenium.WebDriver.Window win = opt.window();
		win.fullscreen();
		win.maximize();
		
		//test GUI/FONT/COLOR/SIZE/POSITION/LOCATION
		
	win.getPosition();
	win.getSize();
	
	Navigation nav = driver.navigate();
	nav.back();
	nav.refresh();
	nav.forward();
	nav.to("url");
	
	TargetLocator tar = driver.switchTo();
	tar.alert();
	tar.frame("");
	tar.window(loginWindowId);
		
		
		
	

		
		
		
		
		
		
		
		
		
		
		 
		
		
		
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
