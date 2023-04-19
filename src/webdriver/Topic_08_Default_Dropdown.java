package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
//	Select select;
	Actions action;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumber = "";
	String comment = "This is generated data \not real people";
	String firstName, lastName, emailAddress, companyName, passWord, day, month, year;
	String countryName, provinceName, city, address, postalcode, phoneNo;

	@BeforeClass
	public void beforeClass() {

//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//	driver = new FirefoxDriver();

		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {

			// System.setProperty("webdriver.chrome.driver", projectPath +
			// "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		// driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "Elon";
		lastName = "Musk";
		emailAddress = "elonmusk" + getRandomNumber() + "@gmail.com";
		companyName = "SpaceX";
		passWord = "123456789";
		day = "1";
		month = "August";
		year = "1930";

		countryName = "United States";
		provinceName = "Arizona";
		city = "Marianna";
		address = "1985 Virgil Street";
		postalcode = "32446";
		phoneNo = "0973-147-000";

	}

	// test case nao khong muon chay thi comment vao @test
	@Test
	public void TC_01_RegisterNewAcc() {
		driver.get("https://demo.nopcommerce.com");

		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys((lastName));
		// select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		// select.selectByIndex(7);
		// select.selectByValue("14");
		// select.selectByVisibleText("18");
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		Assert.assertFalse(new Select(driver.findElement(By.name("DateOfBirthDay"))).isMultiple());

		driver.findElement(By.id("Email")).sendKeys((emailAddress));
		driver.findElement(By.id("Company")).sendKeys((companyName));
		driver.findElement(By.id("Password")).sendKeys((passWord));
		driver.findElement(By.id("ConfirmPassword")).sendKeys((passWord));

		// verify register successfully
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.cssSelector("a.ico-account")).click();

		// verify account info
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);

		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);

	}

	
	@Test
	public void TC_02_Add_Address() {
		// driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();

		driver.findElement(By.id("Address_FirstName")).sendKeys((firstName));
		driver.findElement(By.id("Address_LastName")).sendKeys((lastName));
		driver.findElement(By.id("Address_Email")).sendKeys((emailAddress));
		driver.findElement(By.id("Address_Company")).sendKeys((companyName));

		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
		//sleepInSecond(1000);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys((city));
		driver.findElement(By.id("Address_Address1")).sendKeys((address));
		driver.findElement(By.id("Address_City")).sendKeys((city));
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys((postalcode));
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys((phoneNo));

		driver.findElement(By.cssSelector("button.save-address-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " + lastName);
		// so sanh tuong doi dung assert true
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNo));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.address1")).getText().contains(address));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(provinceName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalcode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(), countryName);

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

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}
}
