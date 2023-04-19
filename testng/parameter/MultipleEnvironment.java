package parameter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MultipleEnvironment {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	
	
	// nếu nhiều biến thì viết PARAMETER theo kiểu mảng:
	//@Parameters({"browser","environment"})
	
	
	/**
	 * Run test case bên file XML: RunMulptipleBrowser
	 * @param browserName
	 */
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		
		System.out.println("Browser name ="+ browserName);
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			
			
		}else {
			throw new RuntimeException ("Browser name is Invalid");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Parameters("environment")
	@Test
	public void TC_01_LoginToSystem(@Optional("dev") String environmentName)  {
		driver.get(getEnvironmentURL(environmentName)+"/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		
	}
	public String getEnvironmentURL(String environmentName) {
		String url =null;
		if (environmentName.toLowerCase().equals("dev")) {
			url = "http://dev.techpanda.org";
			
		}
		else if (environmentName.toLowerCase().equals("testing")) {
			url = "http://testing.dev.techpanda.org";
			
		}else if (environmentName.toLowerCase().equals("staging")) {
			url = "http://staging.techpanda.org";
			
			
		}else if (environmentName.toLowerCase().equals("live")) {
			url = "http://live.techpanda.org";
					
		}
		
		else {
			throw new RuntimeException ("Environment name is Invalid");
		}
		return url;
	}

	
	@AfterClass(alwaysRun= true)
	public void afterClass() {
		driver.quit();
	}
}
