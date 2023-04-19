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
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.FluentFuture;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_28_Wait_part8_Fluent_Wait {
	WebDriver driver;

	WebDriverWait explicitWait;
	// FluentWait fluentWait;
	FluentWait<WebDriver> fluentwaitDriver;
	FluentWait<WebElement> fluentwaitElement;

	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	/***
	 * Lý Thuyết * Implicit : apply cho tìm element Explicit: apply cho tìm điều
	 * kiện , trạng thái của các element: presence, invisible,... Nếu khong dùng
	 * implicit thì băt buộc phải wait explicit rất là kỹ Nếu kết hợp cả 2 loại wait
	 * thì KHÔNG nên dùng wait explicit có tham số là Webelement vì nó ra exception
	 * lỗi của implicit trước, trong khi code đang là wait explicit Nên sẽ gây hiểu
	 * nhầm khi đọc code và trace lỗi
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
		
		
		//lưu ý ko set hàm implicitWait vì nó có thể làm ảnh hưởng tới các hàm findelement bên trong fluentưait

		// seleninum version2.x /3.x:
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// selenium version4.0:
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

@Test
	public void TC_01_Element_FluentWait_GetText() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		fluentwaitDriver = new FluentWait<WebDriver>(driver);
		
		//selenium version 2.x (timeUnit)
		// có báo dấu gạch ngang có nghĩa là hàm này đang lỗi thời:
	//	fluentwaitDriver.withTimeout(15, TimeUnit.SECONDS);
		
		//selenium version 3.x- 4.x: dùng bản này:
		// Setting tổng thời gian chờ là bao lâu:
		fluentwaitDriver.withTimeout(Duration.ofSeconds(20));
		
		// retry time to search:
		fluentwaitDriver.pollingEvery(Duration.ofMillis(100));
		
		//Ignore exception when no found element
		fluentwaitDriver.ignoring(NoSuchElementException.class);
		
		// condition of fluent wait:
		// T la input, V: output cua ham apply()
		fluentwaitDriver.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
				System.out.println("text=" +text);
				return text.equals("Hello World!");
			}
			
		});
		
	}

@Test
	public void TC_02_Element_FluentWait2_cach2_GetText() {

	driver.get("https://automationfc.github.io/dynamic-loading/");
	driver.findElement(By.cssSelector("div#start>button")).click();
	fluentwaitDriver = new FluentWait<WebDriver>(driver);
	
	//selenium version 2.x (timeUnit)
	// có báo dấu gạch ngang có nghĩa là hàm này đang lỗi thời:
//	fluentwaitDriver.withTimeout(15, TimeUnit.SECONDS);
	
	//selenium version 3.x- 4.x: dùng bản này:
	// Setting tổng thời gian chờ là bao lâu:
	fluentwaitDriver.withTimeout(Duration.ofSeconds(20));
	
	// retry time to search:
	fluentwaitDriver.pollingEvery(Duration.ofMillis(100));
	
	//Ignore exception when no found element
	fluentwaitDriver.ignoring(NoSuchElementException.class);
	
	// condition of fluent wait:
	String helloWorldText=fluentwaitDriver.until(new Function<WebDriver, String>() {

		@Override
		public String apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return driver.findElement(By.cssSelector("div#finish>h4")).getText();

		}
		
	});
	Assert.assertEquals(helloWorldText, "Hello World!");

	}


@Test
	public void TC_03_Element_FluentWait_Equal() {

	driver.get("https://automationfc.github.io/dynamic-loading/");
	driver.findElement(By.cssSelector("div#start>button")).click();
	fluentwaitDriver = new FluentWait<WebDriver>(driver);
	
	//selenium version 2.x (timeUnit)
	// có báo dấu gạch ngang có nghĩa là hàm này đang lỗi thời:
//	fluentwaitDriver.withTimeout(15, TimeUnit.SECONDS);
	
	//selenium version 3.x- 4.x: dùng bản này:
	// Setting tổng thời gian chờ là bao lâu:
	fluentwaitDriver.withTimeout(Duration.ofSeconds(20));
	
	// retry time to search:
	fluentwaitDriver.pollingEvery(Duration.ofMillis(100));
	
	//Ignore exception when no found element
	fluentwaitDriver.ignoring(NoSuchElementException.class);
	
	// condition of fluent wait:
	fluentwaitDriver.until(new Function<WebDriver, Boolean>() {

		@Override
		public Boolean apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");

		}
		
	});
	
	//Wrapper Class: Type cua ham
	// boolean-> Boolean
	// int -> Integer
	//float - Float

	}

@Test
public void TC_04_Element_FluentWait_IsDisplay() {

driver.get("https://automationfc.github.io/dynamic-loading/");
driver.findElement(By.cssSelector("div#start>button")).click();
fluentwaitDriver = new FluentWait<WebDriver>(driver);

//selenium version 2.x (timeUnit)
// có báo dấu gạch ngang có nghĩa là hàm này đang lỗi thời:
//fluentwaitDriver.withTimeout(15, TimeUnit.SECONDS);

//selenium version 3.x- 4.x: dùng bản này:
// Setting tổng thời gian chờ là bao lâu:
fluentwaitDriver.withTimeout(Duration.ofSeconds(10));

// retry time to search:
fluentwaitDriver.pollingEvery(Duration.ofMillis(100));

//Ignore exception when no found element
fluentwaitDriver.ignoring(NoSuchElementException.class);

// condition of fluent wait:
fluentwaitDriver.until(new Function<WebDriver, Boolean>() {

	@Override
	public Boolean apply(WebDriver driver) {
		// TODO Auto-generated method stub
		return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();

	}
	
});

}

@Test
// test case này sẽ fail: vì element chưa tồn tại đã verify rồi.
public void TC_05_Element_FluentWait_WebElement() {

driver.get("https://automationfc.github.io/dynamic-loading/");
driver.findElement(By.cssSelector("div#start>button")).click();
fluentwaitElement = new FluentWait<WebElement>(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));

//selenium version 3.x- 4.x: dùng bản này:
// Setting tổng thời gian chờ là bao lâu:
fluentwaitElement.withTimeout(Duration.ofSeconds(20))
.pollingEvery(Duration.ofMillis(100))
.ignoring(NoSuchElementException.class);

String helloWorldText =fluentwaitElement.until(new Function<WebElement, String>() {

	@Override
	public String apply(WebElement element) {
		// TODO Auto-generated method stub
		return element.getText();

	}
	
});
Assert.assertEquals(helloWorldText, "Hello World!");
}

@Test
//test case này sẽ PASS
public void TC_06_Element_FluentWait_WebElement() {

driver.get("https://automationfc.github.io/fluent-wait");
fluentwaitElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("div#javascript_countdown_time")));

//Setting tổng thời gian chờ là bao lâu + exception:
fluentwaitElement.withTimeout(Duration.ofSeconds(5))
.pollingEvery(Duration.ofMillis(100))
.ignoring(NoSuchElementException.class);

boolean status =fluentwaitElement.until(new Function<WebElement, Boolean>() {

	@Override
	public Boolean apply(WebElement element) {
		// TODO Auto-generated method stub
		String text = element.getText();
		System.out.println(text);
		return text.endsWith("00");

	}	
});
Assert.assertTrue(status);
}

/***
 * Tạo hàm customise để tái sử dụng khi khi muốn chỉnh polling
 * @param locator
 * @return
 */
public WebElement findElementCustomise(By locator) {
	FluentWait<WebDriver> fluentwaitDriver = new FluentWait<WebDriver>(driver);

	fluentwaitDriver.withTimeout(Duration.ofSeconds(30))

			.pollingEvery(Duration.ofSeconds(1))

			.ignoring(NoSuchElementException.class);

	return fluentwaitDriver.until(new Function<WebDriver, WebElement>() {

		@Override
		public WebElement apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return driver.findElement(locator);
		}

	});
}

@Test
public void TC_07_Element_FluentWait_IsDisplay_UseCustomiseContructor() {

driver.get("https://automationfc.github.io/dynamic-loading/");
findElementCustomise(By.cssSelector("div#start>button"));
fluentwaitDriver = new FluentWait<WebDriver>(driver);

fluentwaitDriver.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100)).ignoring(NoAlertPresentException.class);

// condition of fluent wait:
fluentwaitDriver.until(new Function<WebDriver, Alert>() {

	@Override
	public Alert apply(WebDriver driver) {
		// TODO Auto-generated method stub
		return fluentwaitDriver.until(ExpectedConditions.alertIsPresent());

	}
	
});

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
		// date.toString();
		return date.toString();
	}

}
