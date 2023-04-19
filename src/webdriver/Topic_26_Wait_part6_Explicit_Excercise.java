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
import java.util.Date;
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

public class Topic_26_Wait_part6_Explicit_Excercise {
	WebDriver driver;
	
	WebDriverWait explicitWait;
	
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
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
		driver = new FirefoxDriver();
		// trạng thái visible/invisible/presence/click/select....
			explicitWait = new WebDriverWait(driver, 30);
		
		// tìm xong 1 element mới apply implicity wait của element đó. ko cần dùng ở trên, vì dưới dùng rồi
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)	;
		


	}
//@Test
	public void TC_01_Telerik() {
	 driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	 System.out.println(" 1- start:"+ getDateTimeNow());
	 
	 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
	 //Selected Dates text sẽ in ra ngày đã chọn
	 WebElement textToday = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
	 System.out.println("2- start :"+ getDateTimeNow());
	 Assert.assertEquals(textToday.getText(),"No Selected Dates to display.");
	 
	
	 //wait cho ngày cần click là clickable
	 System.out.println("3- start :"+ getDateTimeNow());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='25']/parent::td")));
	
	// click vào ngày hiện tại 
		
		// có thể gộp thành 1 hàm, vừa vừa click
		 System.out.println("3- start :"+ getDateTimeNow());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='25']/parent::td"))).click();
		//xpath:  //div[not(@style='display:none;')]/div[@class='raDiv']
		// css: div:not([style='display:none;'])>div.raDiv
		//wait loading icon biến mất:
		 System.out.println("4- start :"+ getDateTimeNow());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:not([style='display:none;'])>div.raDiv")));
		
		
		 System.out.println("5- start :"+ getDateTimeNow());
		// wait cho tới khi ngày được chọn là selected:
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='25']/parent::td[@class='rcSelected']")));
		
		
		
		/* 
		 * element đã được cập nhật sang elemetn mới nên ta phải tìm lại element ko có nó sẽ bị lỗi ko tìm dc element ban đầu:
		 * 
		 */
		 System.out.println("6- start :"+ getDateTimeNow());
		 WebElement selecteDate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		 
		Assert.assertEquals(selecteDate.getText(),"Saturday, March 25, 2023");
		 System.out.println("7- end :"+ getDateTimeNow());
		
		
		
	}

@Test
public void TC_02_Upload() {
	driver.get("https://gofile.io/uploadFiles");
	//div#mainContent>div>div>div.spinner-border
	//Wait for loading Icon in Main page disapear:
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div>div.spinner-border")));
	
	By uploadFile = By.xpath("//input[@type='file']");
	

	//Load 3 files in the same time:
	driver.findElement(uploadFile).sendKeys(hagiangPhotoPath+"\n"+ hotayPhotoPath+ "\n"+ hanoiPhotoPhotoPath);
	
	//Wait for loading Icon in Upload Page disapear:
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainUploadInitInfo>div>div>div.spinner-border")));
	//div.progress-bar
	// Wait for all progress bars disappear:
	explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
	// wait for success message to appear:
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']")));
	
	// //div[@class='col-6 text-center']//a[@class='ajaxLink']
	//wait then click on the link:
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'mainUploadSuccessLink')]//a[@class='ajaxLink']"))).click();
	
	//wait table containing all the files which was uploaded:
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesContentTable")));
	
	// verify Download button
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" +hagiangPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" +hotayPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" +hanoiPhoto + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
	
	
	// verify Play button
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" +hagiangPhoto + "']/parent::a/parent::div/following-sibling::div/button[contains(@class,'filesContentOptionPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" +hotayPhoto + "']/parent::a/parent::div/following-sibling::div/button[contains(@class,'filesContentOptionPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" +hanoiPhoto + "']/parent::a/parent::div/following-sibling::div/button[contains(@class,'filesContentOptionPlay')]")).isDisplayed());
}




@AfterClass
public void afterClass() {
	driver.quit();
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
	
public String getDateTimeNow() {
	Date date = new Date();
	//date.toString();
	return date.toString();
}


}
