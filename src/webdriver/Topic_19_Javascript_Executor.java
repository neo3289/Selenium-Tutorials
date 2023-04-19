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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Javascript_Executor {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

	}

//@Test
	public void TC_01() {
	
	driver.get("https://www.facebook.com/");
	sleepInSecond(5);
	System.out.println(jsExecutor.executeScript("return document.URL;"));	
		
	}

//@Test
public void TC_02() {
	driver.get("http://live.techpanda.org/");
	
	//khoong giống hành vi của enduser, trên header Account bị disable. 
	// chỉ sử dụng khi cần	
	jsExecutor.executeScript("arguments[0].click;", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
	sleepInSecond(5);
		
	}
//@Test
public void TC_01_techPanda() {
	
	navigateToUrlByJS("http://live.techpanda.org/");
	sleepInSecond(5);
	// cach 1:
//	String homePageDomain = (String)executeForBrowser("return document.domain;");
//	Assert.assertEquals(homePageDomain, "live.techpanda.org");
	// or cach 2:
	Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");

	Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
	
	hightlightElement("//a[text()='Mobile']");
	clickToElementByJS("//a[text()='Mobile']");
	sleepInSecond(3);
	clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
	sleepInSecond(3);
	
	Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
	Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	hightlightElement("//a[text()='Customer Service']");
	clickToElementByJS("//a[text()='Customer Service']");
	
	sleepInSecond(3);
	
	Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");
	scrollToElementOnDown("//input[@id='newsletter']");
		
	sendkeyToElementByJS("//input[@id='newsletter']", employeeAddress);
	sleepInSecond(3);
	
	
	hightlightElement("//button[@title='Subscribe']");
	clickToElementByJS("//button[@title='Subscribe']");
	sleepInSecond(3);
	Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
	Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
	
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	sleepInSecond(5);
	Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");

	
	}

@Test
public void TC_02_html5ValidationMsg_Rode() {
	
	navigateToUrlByJS("https://warranty.rode.com/");
	sleepInSecond(5);
	By registerButton = By.xpath("//button[contains(text(),'Register')]");
	driver.findElement(registerButton).click();
	Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");
	getElement("//form[contains(@action,'register')]//input[@id='firstname']").sendKeys("Automation");
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	
	Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"), "Please fill out this field.");
	getElement("//form[contains(@action,'register')]//input[@id='surname']").sendKeys("FC");
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	
	Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='email']"), "Please fill out this field.");
	getElement("//form[contains(@action,'register')]//input[@id='email']").sendKeys(employeeAddress);
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	
	
	Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='password']"), "Please fill out this field.");
	getElement("//form[contains(@action,'register')]//input[@id='password']").sendKeys("123456789");
	
	driver.findElement(registerButton).click();
	sleepInSecond(3);
	
	Assert.assertEquals(getElementValidationMessage("//form[contains(@action,'register')]//input[@id='password-confirm']"), "Please fill out this field.");
	getElement("//form[contains(@action,'register')]//input[@id='password-confirm']").sendKeys("123456789");
	
//	driver.findElement(registerButton).click();
	sleepInSecond(3);
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
