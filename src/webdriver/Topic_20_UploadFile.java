package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
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

public class Topic_20_UploadFile {
	WebDriver driver;
	//Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	//WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
//	Alert alert;
//	Select select;
//	Window window;
//	Random rand = new Random();
//	String employeeAddress = "testing"+ String.valueOf(rand.nextInt(99999))+"@hotmail.vn";
	
	
	String hagiangPhoto = "hagiang.jpg";
	String hanoiPhoto = "hanoi.jpg";
	String hotayPhoto = "hotay.jpg";
	
	String hagiangPhotoPath = projectPath+ File.separator+"\\uploadFiles\\"+File.separator+ hagiangPhoto;
	String hanoiPhotoPhotoPath = projectPath+ File.separator+"\\uploadFiles\\"+File.separator+ hanoiPhoto;
	String hotayPhotoPath = projectPath+ File.separator+"\\uploadFiles\\"+File.separator+ hotayPhoto;

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
		
		// MAC VS linux:  đường dẫn dùng 1 xược và xược phải
		// F:/AUTOMATION/1.Software install
		// window xược trái và dùng 2 xược

	}

//@Test
	public void TC_01_1File_Per_Time() {
	
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys("F:\\AUTOMATION\\1.Software install\\eclipse-java-2022-06-R-win32-x86_64\\workspace\\Selenium Tutorial\\uploadFiles\\hagiang.jpg");
	sleepInSecond(3);
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys("F:\\AUTOMATION\\1.Software install\\eclipse-java-2022-06-R-win32-x86_64\\workspace\\Selenium Tutorial\\uploadFiles\\hanoi.jpg");
	sleepInSecond(3);
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys("F:\\AUTOMATION\\1.Software install\\eclipse-java-2022-06-R-win32-x86_64\\workspace\\Selenium Tutorial\\uploadFiles\\hotay.jpg");
	sleepInSecond(3);
	}

//@Test
public void TC_02_1File_Per_time_UseBien() {
	// define bien By upload to reuse
	By uploadFile = By.xpath("//input[@type='file']");
	
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	driver.findElement(uploadFile).sendKeys(hagiangPhotoPath);
	sleepInSecond(3);
	driver.findElement(uploadFile).sendKeys(hotayPhotoPath);
	sleepInSecond(3);
	driver.findElement(uploadFile).sendKeys(hanoiPhotoPhotoPath);
	sleepInSecond(3);

	
	// verify file dc load lên thành công 
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" +hagiangPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" +hotayPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" +hanoiPhoto+ "']")).isDisplayed());

	
	// click upload cho từng file :
	List <WebElement> startbuttons = driver.findElements(By.cssSelector("tbody.files button.start"));
	for (WebElement start: startbuttons) {
		start.click();
		sleepInSecond(2);
	}
	
	// verify file dc upload lên thành công sau khi click Start
	System.out.println("verify file dc upload lên thành công sau khi click Start: ");
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" +hagiangPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" +hanoiPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" +hotayPhoto+ "']")).isDisplayed());
	
	// verify hinh upload len la hình thực bằng javascript:
	Assert.assertTrue(isImageLoaded("//a[@title='hagiang.jpg']/img"));
	Assert.assertTrue(isImageLoaded("//a[@title='hotay.jpg']/img"));
	Assert.assertTrue(isImageLoaded("//a[@title='hanoi.jpg']/img"));

	
	}
@Test
public void TC_02_MulptileFile_Per_time_UseBien() {
	// define bien By upload to reuse
	By uploadFile = By.xpath("//input[@type='file']");
	
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");

	//Load 3 files in the same time:
	driver.findElement(uploadFile).sendKeys(hagiangPhotoPath+"\n"+ hotayPhotoPath+ "\n"+ hanoiPhotoPhotoPath);
	sleepInSecond(3);
	
	
	
	
	// verify file dc load lên thành công 
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" +hagiangPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" +hanoiPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" +hotayPhoto+ "']")).isDisplayed());
	
	
	// click upload cho từng file :
	List <WebElement> startbuttons = driver.findElements(By.cssSelector("tbody.files button.start"));
	for (WebElement start: startbuttons) {
		start.click();
		sleepInSecond(5);
	}
	
	// verify file dc upload lên thành công sau khi click Start
	System.out.println("verify file dc upload lên thành công sau khi click Start: ");
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" +hagiangPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" +hanoiPhoto+ "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()= '" +hotayPhoto+ "']")).isDisplayed());
	
	// verify hinh upload len la hình thực bằng javascript:
	Assert.assertTrue(isImageLoaded("//a[@title='hagiang.jpg']/img"));
	Assert.assertTrue(isImageLoaded("//a[@title='hanoi.jpg']/img"));
	Assert.assertTrue(isImageLoaded("//a[@title='hotay.jpg']/img"));
	
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
