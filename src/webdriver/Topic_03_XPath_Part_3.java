package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Test
public class Topic_03_XPath_Part_3 {
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
		driver.findElement(By.id("txtEmail")).clear();
		driver.findElement(By.id("txtEmail")).sendKeys("johnwick@gmail.net");
		driver.findElement(By.id("txtCEmail")).clear();
		driver.findElement(By.id("txtCEmail")).sendKeys("johnwick@gmail.com");	

		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	@Test
	public void TC_04_Passwork_Lessthan6() {
	
		driver.findElement(By.id("txtCEmail")).clear();
		driver.findElement(By.id("txtCEmail")).sendKeys("johnwick@gmail.net");	
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).clear();
		driver.findElement(By.id("txtCPassword")).sendKeys("128");	
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
	}
	@Test
	public void TC_05_InCorrec_ConfirmaPw() {
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtCPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("123456");	
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");	
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void TC_06_Invalid_PhoneNumber() {
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtCPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("097314716890");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	

	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
