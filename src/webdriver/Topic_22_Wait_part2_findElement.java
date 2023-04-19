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

public class Topic_22_Wait_part2_findElement {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
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
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/reg/");
		/*
		 *implicit wait cũng là wait linh động, như explicit, nếu tìm thấy thì nó dừng, ko chạy hết thời gian.
		 *findElement:  ko tìm thấy đánh fail test case và ném ra throw exception
		 *findElements : không đánh fail test case và ko show exception, mà chỉ về list empty
		 */
	}

@Test
	public void TC_01_findElement() {
	// mún dùng đi dùng lại thì tạo thành biến (bài 5-6)
	WebElement element = driver.findElement(By.cssSelector("input[name='firstname']"));
	
	// trả về 1 element.
	// bị ảnh hưởng bởi implicit wait.
	// nếu tìm thấy sẽ trả về, nếu ko tìm thấy sẽ try again nửa giây tìm lại 1 lần cho tới khi hết thời gian timeout của implicit wait
	// neus ko tìm thấy sẽ ném ra ngoại lệ
	/*
	 * findElement không nên sử dùng cho Non-present elements trong HTML
	 * nên dùng findElements để tìm Non-present elements trong HTML
	 *  3 trường hợp:
	 *  case1: nếu tìm nhưng chỉ có 1 element dc tìm thấy
	 *   */
	 //case2: tìm thấy > 1 element dc tìm thấy, nó sẽ lấy luôn 1st element đầu tiên để sử dụng
	
	driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Automation FC");
	
	
	// case 3: không tìm thấy element nào hết
	////div[text()="What's your name?"]
	// nó sẽ tìm đi tìm lại , nửa giây 1 lần quét lại, nếu tìm thấy sẽ ko cần chờ timeout
	// nếu ko tìm thấy sẽ throw exception 
	
	driver.findElement(By.xpath("//div[text()=\"What's your name?\"]"));
	

	}

@Test

// xem thêm bài 10 click list web elements để click hết checkbox
public void TC_02_findElements() {
	// 
	List<WebElement> elements = driver.findElements(By.cssSelector("input[name='firstname']"));
	//case1: nếu tìm nhưng chỉ có 1 element dc tìm thấy
	System.out.println("case 2: tìm thấy số element:= "+ elements.size());
	
	// trả về list elements = nhiều element
	// CÓ THỂ dùng hàm size() để tìm số element trong list
	// tìm tất cả element trong page hiện tại
	// bị ảnh hưởng bởi implicit wait
	// nếu tìm thấy sẽ trả về nhiều hơn 0 items, nếu như không tìm thấy sẽ trả về list rỗng (0 item)
	//neus ko tìm thấy sẽ ko ném ra ngoại lệ


	// case2: tìm thấy > 1 element dc tìm thấy:
	List<WebElement> elements2 = driver.findElements(By.cssSelector("input[type='text']"));
	System.out.println("case 2: tìm thấy số element:= "+ elements2.size());
	
	// case 3: không tìm thấy element nào hết
	 
	List<WebElement> elements3= driver.findElements(By.xpath("//div[text()=\"What's your name?\"]"));
	System.out.println("case 3: tìm thấy số element:= "+ elements3.size());
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
