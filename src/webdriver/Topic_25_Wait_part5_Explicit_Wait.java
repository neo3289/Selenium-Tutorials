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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_25_Wait_part5_Explicit_Wait {
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
		explicitWait = new WebDriverWait(driver, 30);
		
		rand = new Random();
		emailAddress= "La"+ rand.nextInt(99999)+"@gmail.com";
		firstName = "La";
		lastName = "Dang";
		midname = "Thi";
		fullName= firstName + " " + midname+ " "+lastName;
		password="12345678";
		


	}
//@Test
	public void TC_01_Wait_For_Attribute_Contain_Value() {
	 driver.get("http://live.techpanda.org/index.php");
		
		explicitWait = new WebDriverWait(driver, 30);
		// wait cho search box xuất hiện, có chứa giá trị là 1 đoạn placeholder text
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder","Search entire store here..." ));
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder","Search entire store" ));
		
		// cach2: dùng webelement sau attribute
		// có findElement nó sẽ nhận implicit
		explicitWait.until(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector("input#search")),"placeholder","Search entire store here..."));
		//verify luôn ko wait
		Assert.assertEquals(driver.findElement(By.cssSelector("input#search")).getAttribute("placeholder"), "Search entire store here...");
	
	}

//@Test
public void TC_02_Wait_For_Attribute_Clickable() {
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	explicitWait = new WebDriverWait(driver, 10);
	
	// wait cho start button được ready trước khi click:
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
	driver.findElement(By.cssSelector("div#start>button")).click();
	
	//--------------------------------------
	driver.get("https://login.mailchimp.com/signup/");
	explicitWait = new WebDriverWait(driver, 10);
	// wait cho start button được ready trước khi click:
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#create-account-enabled")));
	driver.findElement(By.cssSelector("button#create-account-enabled")).click();
	
	//--------------------------------------
	
	// test truong hợp clickable cho button Register bị fail. Chờ 10s, ko click dc thì đánh fail
	
	driver.get("https://www.fahasa.com/customer/account/create");
	explicitWait = new WebDriverWait(driver, 10);
	// wait cho start button được ready trước khi click:
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.fhs-btn-register")));
	driver.findElement(By.cssSelector("button.fhs-btn-register")).click();
	
	
}
// trước khi action/verìy thêm các steps wait thì ko mất gì hết
// vif nó làm cho test case chay theo dạng synchronize, nếu chưa thỏa mãn điều kiện thì chờ tiếp, nếu thỏa mãn rồi, thì nó ko chờ thời gian nữa

//@Test
public void TC_03_Wait_For_Element_Selected() {
	driver.get("https://automationfc.github.io/multiple-fields/");
	
	explicitWait = new WebDriverWait(driver, 10);
	
	
	//wait cho 29 checkbox dc load ra:
	explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input.form-checkbox"),29));  

	List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("input.form-checkbox"));
	
	// Dùng vòng lặp để duyệt qua và click vào tất cả checkbox này:
	for (WebElement checkbox : allCheckboxs) {
		checkbox.click();
	}
	
	// verify tất cả checkbox đã đc chọn thành công:
	for (WebElement checkbox : allCheckboxs) {
		explicitWait.until(ExpectedConditions.elementToBeClickable(checkbox));
		Assert.assertTrue(checkbox.isSelected());
		
		checkbox.click();
	}
	
}

//@Test
//frame inside a page. eg. embeded a login page in another page
public void TC_04_Wait_For_Frame() {
	// dag code den phut 59 video topic 37
	driver.get("https://netbanking.hdfcbank.com/netbanking/");
	explicitWait = new WebDriverWait(driver, 10);

	// Switch to frame:
	driver = explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("login_page")));  
	
	//thao tac voi user Id
	driver.findElement(By.name("fldLoginUserId")).sendKeys("Johnkennedy");
	driver.findElement(By.cssSelector("a.login-btn")).click();
	//navigate to https://netportal.hdfcbank.com/nb-login/login.jsp
	sleepInSecond(3);
	
	// switch to default
	driver.switchTo().defaultContent();
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("keyboard")));  
	Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());


}

//@Test
public void TC_05_Wait_For_GetText() {
	
	 driver.get("http://live.techpanda.org/");
	 explicitWait = new WebDriverWait(driver, 10);
	 
	 // verify account treen header
	// explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']"),"My Account"));
	 
	 //verify account dưới footer
	 explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='footer-container'] a[title='My Account']")));
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		
		 explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("send2")));
		driver.findElement(By.id("send2")).click();

		 explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("#advice-required-entry-email"),"This is a required field." ));
		 driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		
		 explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("#advice-required-entry-pass"),"This is a required field." ));
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
		
	
}

@Test
public void TC_06_Wait_URL_GetTitle() {
	
	 driver.get("http://live.techpanda.org/");
	 explicitWait = new WebDriverWait(driver, 10);
	 
	
		driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
		
		//WAIT implicily ap dung cho tim element, chu ko phai dung cho chuyen trang
		explicitWait.until(ExpectedConditions.urlContains("customer/account/login/"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//click vao Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		explicitWait.until(ExpectedConditions.urlContains("customer/account/create/"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		 driver.get("http://live.techpanda.org/");
			driver.findElement(By.cssSelector("div[class='footer-container'] a[title='My Account']")).click();
			explicitWait.until(ExpectedConditions.titleContains("Customer Login"));
			Assert.assertEquals(driver.getTitle(), "Customer Login");
			
			
			driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
				explicitWait.until(ExpectedConditions.titleIs("Create New Customer Account"));
				Assert.assertEquals(driver.getTitle(), "Create New Customer Account");	
		
		
}



@Test
public void TC_07_timeout_Lessthan_5s() {
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	explicitWait = new WebDriverWait(driver, 3);

	driver.findElement(By.cssSelector("div#start>button")).click();
	
	// chờ cho 1item biến mất (loading icon)
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
	
	// chờ cho 1item xuất hiện Hello world text
	explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"),"Hello World!"));	
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

}
@Test
	public void TC_08_timeout_Equal_5Second() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		driver.findElement(By.cssSelector("div#start>button")).click();
		// chờ cho 1item biến mất (loading icon)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		// chờ cho 1item xuất hiện Hello world text
		
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"),"Hello World!"));	
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

@Test
	public void TC_09_timeout_Morethan_5Second() {
		// tìm thấy rồi thì nó ko chờ nữa chạy 5s là dừng
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 100);	
		driver.findElement(By.cssSelector("div#start>button")).click();	
		// chờ cho 1item biến mất (loading icon)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		// chờ cho 1item xuất hiện Hello world text
		
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"),"Hello World!"));	
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	
//@Test
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
