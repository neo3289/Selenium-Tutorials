package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_29_Wait_Part09_Page_Ready {
	WebDriver driver;

	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {

			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			
		}
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20)      ;          
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action = new Actions(driver);
		
	}

@Test
	public void TC_01_Orangge_HRM_API() {
		driver.get("https://api.orangehrm.com/");
		// wait for icon loading biến mất
		// Khi nó biến mất là khi nó loading dữ liệu về thành công
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
		
	}

@Test	
	public void TC_02_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com");
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		// Navigate from Login page to Dashboard page
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		
	
		//explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
		
		//dùng hàm common đã viết trước bên dưới, gọi lại để sử dụng
		Assert.assertTrue(waitForAjaxBusyLoadingInvisible());
		
		// Navigate from Dashboard page to Login page
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		Assert.assertTrue(waitForAjaxBusyLoadingInvisible());		
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
		
	}

	@Test	
	public void TC_03_Admin_NopCommerce_PageLoadSuccess() {
		driver.get("https://admin-demo.nopcommerce.com");
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		// Navigate from Login page to Dashboard page
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		
	
		//explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));
		
		//dùng hàm common đã viết trước bên dưới, gọi lại để sử dụng
		Assert.assertTrue(isPageLoadedSuccess());
		
		// Navigate from Dashboard page to Login page
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());	
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
		
	}
	
	
	
@Test
	public void TC_04_Blog_Test_project(){
		
		driver.get("https://blog.testproject.io/");
		//hower mouse into any elements to active loading page
		// because the page need user's interact with page if mouse move
	
		String keyword ="Selenium";
		action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		

		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text() = 'Search Results']")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postArticles = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : postArticles) {
			Assert.assertTrue(article.getText().contains(keyword));
			//System.out.println(postArticles);
		}
		
	}

	@AfterClass(alwaysRun= true)
	public void afterClass() {
		driver.quit();
	}
	
	/**
	 * tạo 1 hàm để chờ cho 1 page loading ajar chờ loading icon biến mất
	 */
	public boolean waitForAjaxBusyLoadingInvisible() {
		
		
		return 	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
				
	}
	
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	
}
