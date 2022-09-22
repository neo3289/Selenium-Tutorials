package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_03_XPath_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("OS.name");
	

	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://demo.nopcommerce.com/register");
		}


	@Test
	public void TC_01_ID() {
		
		// thao tac len element thi dau tien tim dc elemment do
		// FIND CAI GI: ID/ CLASS/ NAME/CSS/ XPATH.....
		// FIND tim thay element roi thi action len element do : click, send key
			driver.findElement(By.id("FirstName")).sendKeys("Automation");
		
	}
	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
			driver.findElement(By.className("search-text")).sendKeys("Macbook");
		
	}
	@Test
	public void TC_03_Name() {
		//click vao advance search checkbox
			driver.findElement(By.name("advs")).click();
		
	}
	@Test
	public void TC_04_TagName() {
		//timra bao nhieu the input tren man hinh hien tai (time element so nhieu)
	
	System.out.println("Total tag name input:"+ driver.findElements(By.tagName("input")).size());
		
	}
	@Test
	public void TC_05_LinkText() {
		//click vao duong link addresses - duong link tuyệt đối
		driver.findElement(By.linkText("Addresses")).click();
	
		
	}
	
	@Test
	public void TC_06_PartiallinkText() {
		//click vao duong link addresses - duong link tương đối - tốc độ chậm hơn
		driver.findElement(By.partialLinkText("vendor account")).click();
	
		
	}
	@Test
	public void TC_07_CSS() {
		//mo lai trang register	
		driver.get("https://demo.nopcommerce.com/register");
		// cách 1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Seleninum");
	// cách 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		
	//cach 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("ladt.hanu@gmail.com");
		
		
	}
	@Test
	public void TC_08_xPath() {
		driver.get("https://demo.nopcommerce.com/register");
		// cách 1
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Seleninum");
	// cách 2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
		
	//cach 3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("ladt.hanu@gmail.com");
	
		
	}
	
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
