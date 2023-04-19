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

public class Topic_27_Wait_part7_Mix_Explicit_Implicit {
	WebDriver driver;
	
	WebDriverWait explicitWait;
	
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
//	String hagiangPhoto = "hagiang.jpg";
//	String hanoiPhoto = "hanoi.jpg";
//	String hotayPhoto = "hotay.jpg";
//	
//	String hagiangPhotoPath = projectPath+ File.separator+"\\uploadFiles\\"+File.separator+ hagiangPhoto;
//	String hanoiPhotoPhotoPath = projectPath+ File.separator+"\\uploadFiles\\"+File.separator+ hanoiPhoto;
//	String hotayPhotoPath = projectPath+ File.separator+"\\uploadFiles\\"+File.separator+ hotayPhoto;
	
	/***
	 *Lý Thuyết
	 * * Implicit : apply cho tìm element
	 * Explicit: apply cho tìm điều kiện , trạng thái của các element: presence, invisible,...
	 * Nếu khong dùng implicit thì băt buộc phải wait explicit rất là kỹ
	 * Nếu kết hợp cả 2 loại wait thì KHÔNG nên dùng wait explicit có tham số là Webelement vì nó ra exception lỗi của implicit trước, trong khi code đang là wait explicit
	 * Nên sẽ gây hiểu nhầm khi đọc code và trace lỗi
	 */

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
		// move xuống dưới để test để ko ảnh hưởng toàn bộ test case
		// trạng thái visible/invisible/presence/click/select....
		//	explicitWait = new WebDriverWait(driver, 30);
			
		// tìm xong 1 element mới apply implicity wait của element đó. ko cần dùng ở trên, vì dưới dùng rồi
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)	;
		


	}
//@Test
	public void TC_01_Element_Found() {
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)	;
		// trạng thái visible/invisible/presence/click/select....
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.get("https://www.facebook.com/");
		//implicit: 
		System.out.println("implicit:");
		System.out.println("1-start:"+ getDateTimeNow());
		driver.findElement(By.xpath("//button[@name='login']"));
		System.out.println("2-end:"+ getDateTimeNow());
		
		System.out.println("exlicit:");
		System.out.println("1-start:"+ getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='login']")));
		System.out.println("2-end:"+ getDateTimeNow());
	}

//@Test
public void TC_02_Element_Not_Found_implicit() {
	
	
	
	// refer topic 22: case 3: không tìm thấy element nào hết. 
	////div[text()="What's your name?"]
	// thì hàm implicit sẽ nó sẽ tìm đi tìm lại , nửa giây 1 lần quét lại, nếu tìm thấy sẽ ko cần chờ timeout
	// nếu ko tìm thấy sẽ throw exception 
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)	;
	//Implicit : apply cho tìm element
	
		explicitWait = new WebDriverWait(driver, 15);
		// trạng thái visible/invisible/presence/click/select....
		
		driver.get("https://www.facebook.com/");
		//implicit: 
		System.out.println("implicit:");
		System.out.println("test case 2: 1-start:"+ getDateTimeNow());
		driver.findElement(By.xpath("//button[@name='seleninum']"));
		System.out.println("test case 2:2-end:"+ getDateTimeNow());
		
		
	}

//@Test
public void TC_03_Element_Not_Found_implicit_And_Explicit() {
	
	
	
	// refer topic 22: case 3: không tìm thấy element nào hết. 
	////div[text()="What's your name?"]
	// thì hàm implicit sẽ nó sẽ tìm đi tìm lại , nửa giây 1 lần quét lại, nếu tìm thấy sẽ ko cần chờ timeout
	// nếu ko tìm thấy sẽ throw exception 
	// Nếu không tìm thấy nó sẽ show ra 2 exception của cả 2 loại wait này
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)	;
	//Implicit : apply cho tìm element
	
		explicitWait = new WebDriverWait(driver, 15);
		// trạng thái visible/invisible/presence/click/select....
		
		driver.get("https://www.facebook.com/");
		//implicit: + implicit

		System.out.println("test case 3_1-start:"+ getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='seleninum']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("test case 3 -2-end:"+ getDateTimeNow());
		
		
	}

@Test
public void TC_04_Element_Not_Found_Only_ExplicitBy() {
	
	// nếu không set implicit thì mặc định nó =0 giây.
		explicitWait = new WebDriverWait(driver, 5);
		// trạng thái visible/invisible/presence/click/select....
		
		driver.get("https://www.facebook.com/");
		//implicit: + implicit

		System.out.println("test case 4_1-start:"+ getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='seleninum']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("test case 4 -2-end:"+ getDateTimeNow());
		
		
	}


@Test
public void TC_05_Element_Not_Found_Only_Explicit_WebElement() {
	
	// nếu không set implicit thì mặc định nó =0 giây.
		explicitWait = new WebDriverWait(driver, 5);
		// trạng thái visible/invisible/presence/click/select....
		
		driver.get("https://www.facebook.com/");
		//implicit: + implicit

		System.out.println("test case 5_1-start:"+ getDateTimeNow());
		try {
			// nếu như element đcd tìm thấy sẽ chạy tiếp đoạn wait visible
			// nếu như ko tìm thấy nó sẽ không bao giờ chạy -> fail ngay đoạn tìm find Element rồi nên nó ko chạy đoạn exception nữa. ko chạy step tiếp nhé.
			// do đó test casse số 5 sẽ chạy ít thời gian hơn test case số 4
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@name='seleninum']"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("test case 5 -2-end:"+ getDateTimeNow());
		
		
	}
@Test
public void TC_06_Element_Not_Found_Only_Explicit_WebElement() {
	
	// nếu không set implicit thì mặc định nó =0 giây.
	//case 6: set implicit #0
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)	;
		explicitWait = new WebDriverWait(driver, 5);
		// trạng thái visible/invisible/presence/click/select....
		
		driver.get("https://www.facebook.com/");
		//implicit: + implicit

		System.out.println("test case 6_1-start:"+ getDateTimeNow());
		try {
			// nếu như element đcd tìm thấy sẽ chạy tiếp đoạn wait visible
			// nếu như ko tìm thấy nó sẽ không bao giờ chạy -> fail ngay đoạn tìm find Element rồi nên nó ko chạy đoạn exception nữa. ko chạy step tiếp nhé.
			// do đó test casse số 5 sẽ chạy ít thời gian hơn test case số 4
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@name='seleninum']"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("test case 6 -2-end:"+ getDateTimeNow());
		
		
	}

@Test
public void TC_07_Element_Not_Found_implicit_setExplicit_equal_0() {
	
	
	
	// refer topic 22: case 3: không tìm thấy element nào hết. 
	////div[text()="What's your name?"]
	// thì hàm implicit sẽ nó sẽ tìm đi tìm lại , nửa giây 1 lần quét lại, nếu tìm thấy sẽ ko cần chờ timeout
	// nếu ko tìm thấy sẽ throw exception 
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)	;
	//Implicit : apply cho tìm element
	
		explicitWait = new WebDriverWait(driver, 0);
		// trạng thái visible/invisible/presence/click/select....
		
		driver.get("https://www.facebook.com/");
		//implicit: 
		System.out.println("test case 7_1-start:"+ getDateTimeNow());
		try {
			// nếu như element đcd tìm thấy sẽ chạy tiếp đoạn wait visible
			// nếu như ko tìm thấy nó sẽ không bao giờ chạy -> fail ngay đoạn tìm find Element rồi nên nó ko chạy đoạn exception nữa. ko chạy step tiếp nhé.
			// do đó test casse số 5 sẽ chạy ít thời gian hơn test case số 4
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@name='seleninum']"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("test case 7 -2-end:"+ getDateTimeNow());
		
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
