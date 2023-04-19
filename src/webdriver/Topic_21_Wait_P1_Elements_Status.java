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

public class Topic_21_Wait_P1_Elements_Status {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	//dung webdriverwait de check presence
	
	JavascriptExecutor jsExecutor;
	Alert alert;
	Select select;
	Window window;
	Random rand = new Random();
	String employeeAddress = "testing"+ String.valueOf(rand.nextInt(99999))+"@hotmail.vn";

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
		driver= new FirefoxDriver();
		// ép kiểu tường minh. ko dùng new object với javascript vì nó là 1 interface của selenium
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		
		//apply cho viec tim element : findelement:
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		//explicitWait = new WebDriverWait(driver, getRandomNumber())
		
		// apply cho trạng thái của elements: cụ thể:
		
		explicitWait = new WebDriverWait(driver, 30);
		

	}
	
	/* Trạng thái của element:
- Hiển thị (visible/ displayed)
- Ko hiển thị (invisible/ undisplayed)
- Xuất hiện (presence)
- Staleness

Điều kiện 1: Element có trên UI và có trong cây HTML:
Điều kiện 2: Element ko có (ko nhìn thấy) trên UI nhưng vẫn có trong cây HTML
Điều kiện 3: Element ko có/ ko nhìn thấy trên UI và cũng ko có trong cây HTML

	 * 
	 */
	

//@Test
public void TC_01_Visible_Displayed() {

	driver.get("https://facebook.com");
	// chờ cho email textbox dc hiển thị trước khi sendkey vào nó:
	// explicitwait: cơ chế chờ flexible: nếu như step 1 thấy element sẽ vào step2
	// luôn, ko cần chờ hết 30s:
	// trước 1 action luôn là 1 step wait, nó chạy ổn định hơn

	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
	driver.findElement(By.cssSelector("input#email")).sendKeys("ladang@gmail.com");
}

//@Test
	public void TC_02_InVisible_UnDisplayed_I() {
		// dieu kien 2: elemeent ko có tren UI nHƯNG vẫn có trong case HTML
		driver.get("https://facebook.com");

		// confirmation Email textbox is undislayed
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
		
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("ladang@gmail.com");
	// confirmation email texbox is displayed:
				explicitWait.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
				driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']")).sendKeys("ladang@gmail.com");

	}
//@Test
	public void TC_03_InVisible_UnDisplayed_II() {
		// dieu kien 3: elemeent ko có tren UI nHƯNG và ko có trong HTML
		driver.get("https://facebook.com");
		// nó sẽ tìm đi tìm element  sau đó apply điều kiện check thời gian chạy mất hơn (~30s). do đó test case 2 và test case 3: t2 chạy nhanh hơn.
		// TC2: nó tìm thấy element nên apply điều kiện check luôn nên chạy nhanh (5s)
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

	}

//@Test
public void TC_03_Presence() {
	//dieu kien 3: elemeent ko có tren UI nHƯNG vẫn có trong case HTML 
	// apply presence khi quan tâm tới element có trong DOM - html là được, ko quan tâm nó có trên UI hay ko?
	driver.get("https://facebook.com");
	driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
	
	// confirmation Email textbox is presence
	
	driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
	explicitWait.until(ExpectedConditions
			.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	
	
}

@Test
public void TC_04_Staleness() {
	// apply cả có tRONG HTML sau đó nó apply điều kiện 3 
	driver.get("https://facebook.com");
	driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
	explicitWait.until(ExpectedConditions
			.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	
	//b1: element phải có trong html:
	WebElement confirmationEmailTextbox = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
	driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
	
	//wait cho confirm email staleness -> chay nhanh hon, staleness = invisible nhưng staleness chạy nhanh hơn, phải tạo biến cho nó trc để lưu vào biến
	//Wait until an element is no longer attached to the DOM.
	//Parameters:element The element to wait for.Returns:false if the element is still attached to the DOM, true otherwise.
	explicitWait.until(ExpectedConditions
			.stalenessOf(confirmationEmailTextbox));
	
	
	
}


public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean areExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void hightlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
	sleepInSecond(1);
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public void scrollToElementOnTop(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript(
			"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
			getElement(locator));
	return status;
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
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
