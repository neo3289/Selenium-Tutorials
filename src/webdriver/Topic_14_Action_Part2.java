package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_14_Action_Part2 {
	WebDriver driver;
	Actions action;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	String authenFireforx = projectPath + "\\AutoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "\\AutoIT\\authen_chrome.exe";
	String username1 = "admin";
	String password1 = "admin";

	@BeforeClass
	public void beforeClass() {

		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {

			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		//add action vao driver
		action = new Actions(driver);
		System.out.println(driver.toString());
		explicitWait = new WebDriverWait(driver, 30);
		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Click_And_Hold() {

		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		// dang chua 12 item 
		//1 - Click vào số 1 (nguồn)
	action.clickAndHold(listNumber.get(0))
		//2 - Vẫn giữ chuột/ chưa nhả ra
	.moveToElement(listNumber.get(7))
		//3 - Di chuột tới số (đích)
	.release()
		//4 - Nhả chuột trái ra */
		.perform();
	sleepInSecond(2);
	
	// lIst selected number
	
	List<WebElement> selectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(), 8);
	}
	@Test
	public void TC_02_Click_And_Hold_Random() {

		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//nhan ctr xuong cho may MAC va may WINDOW
				//may mac dung phim COMMAND
				//WINDOW dung CONTROL
		Keys key = null;
		if(osName.contains("Windows")) {
			key = Keys.CONTROL;
		}else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		// dang chua 12 item 
		//index count from 0
		action.keyDown(key).perform();
		
		action.click(listNumber.get(0))
		.click(listNumber.get(3))
		.click(listNumber.get(5))
		.click(listNumber.get(10)).perform();
		
		// Release phim CTR ra:
		action.keyUp(key).perform();
		sleepInSecond(2);
	
		
		// lIst selected number
	
	List<WebElement> selectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(selectedNumber.size(), 4);
	
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// dung ham thread de tranh lap di lap lai nhieu lan
	public void sleepInSecond(long timInSecond) {
		try {
			Thread.sleep(timInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
