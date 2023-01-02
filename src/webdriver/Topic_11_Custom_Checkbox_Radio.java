package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

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
		// driver = new ChromeDriver();

		// luôn khởi tạo sau driver

		explicitWait = new WebDriverWait(driver, 30);
		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_01_CheckBox_IsSelected() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		// Case1:
		// thẻ input bị che nên không thao tác được
		// thẻ input lại dùng để verìy được -> vì hàm isSelected() chỉ work với thẻ
		// input
		// select an item
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		// verify selected successfully.
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))
						.isSelected());

	}

	// @Test
	public void TC_02_CheckBox_IsSelected1() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(10);
		// Case2:
		// thẻ khác với thẻ Input để click, có thể là thẻ Span, Div, Table... đang hiển
		// thị
		// thẻ được chọn thao tác bên trên lại KHÔNG dùng để verìy được -> vì hàm
		// isSelected() chỉ work với thẻ input
		// select an item
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
		sleepInSecond(5);
		// verify selected successfully.
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div"))
				.isSelected());

	}

//	@Test
	public void TC_02_CheckBox_IsSelected2() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(10);
		// Case2:
		// thẻ khác với thẻ Input để click, có thể là thẻ Span, Div, Table... đang hiển
		// thị
		// thẻ được chọn thao tác bên trên lại KHÔNG dùng để verìy được -> vì hàm
		// isSelected() chỉ work với thẻ input
		// select an item
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		sleepInSecond(5);
		// verify selected successfully.
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).isSelected());
		// Note: thẻ span/div/label luôn trả về False vì ko support hàm isSelected()
	}

	// @Test
	public void TC_03_CheckBox_IsSelected() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(10);
		// Case3:
		// thẻ khác với thẻ Input để click, có thể là thẻ Span, Div, Table... đang hiển
		// thị
		// thẻ được chọn thao tác bên trên lại KHÔNG dùng để verify được -> vì hàm
		// isSelected() chỉ work với thẻ input

		// select an item by tag # input:
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		sleepInSecond(5);
		// verify selected successfully.
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))
						.isSelected());
		// Note: thẻ span/div/label luôn trả về False vì ko support hàm isSelected()

	}

	@Test
	public void TC_04_Radiobutton_IsSelected() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(10);
		/*
		 * Case 4: Thẻ input bị ẩn nhưng vẫn dùng để click =>Hàm Click() của WebElemment
		 * nó không thao tác được vào element bị ẩn (Element có chiều dài rộng và cao
		 * lớn hơn 0) Nên sẽ dùng hàm click của JAVASCRIPT Trong selinum cung cấp 1 thư
		 * viện để nhúng đoạn code JS vào kịch bản để test- JavascriptExecutor Thẻ input
		 * dùng để verify isSelected.
		 **/
		// tạo biến dùng lại biến By
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		// chọn = Javascript
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(radioButton));
		sleepInSecond(5);
		// verify selected successfully.
		Assert.assertTrue(
				driver.findElement(radioButton)
						.isSelected());
		// Note: thẻ span/div/label luôn trả về False vì ko support hàm isSelected()

	}
	@Test
	public void TC_05_CheckBox_IsSelected() {
		driver.get("https://docs.google.com/forms/d/1UOTTJjutMbek-FV10Y-g-PYTmSb8klkEUteM3w2wJvQ/edit");
		sleepInSecond(10);
		/*
		 * Case 4: Thẻ input bị ẩn nhưng vẫn dùng để click =>Hàm Click() của WebElemment
		 * nó không thao tác được vào element bị ẩn (Element có chiều dài rộng và cao
		 * lớn hơn 0) Nên sẽ dùng hàm click của JAVASCRIPT Trong selinum cung cấp 1 thư
		 * viện để nhúng đoạn code JS vào kịch bản để test- JavascriptExecutor Thẻ input
		 * dùng để verify isSelected.
		 **/
		// tạo biến dùng lại biến By
		By radioButton = By.xpath("");
		By checkBox= By.xpath("");
		
		// chọn = Javascript
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(radioButton));
		sleepInSecond(5);
		
	
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(checkBox));
		sleepInSecond(5);
		
		// verify selected successfully.
		//- Cách 1: dùng isSelected
		Assert.assertTrue(
				driver.findElement(radioButton)
						.isSelected());
		Assert.assertTrue(
				driver.findElement(checkBox)
						.isDisplayed());
		//- cách 2: dùng getAttribute = checked
		Assert.assertEquals(driver.findElement(checkBox).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");

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

	// * Tranh lap lai code nhieu lan thi viet ham de dung lai*
	// Hàm đi kèm tham số
	// Nếu truyền cứng 1 giá trị vào trong hàm = vô nghĩa
	// nếu define thành tham số thì có thể dùng lại nhiều lần
	public void selectItemInDropdown(String parentCSS, String allItemCSS, String expectedTextItem) {
		// 1 - Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector(parentCSS)).click();

		// 2 - Chờ cho tất cả các item được load ra thành công
		// locator phải lấy xpath đại diện cho tất cả 5 elements
		// lấy đến thẻ nào chứa text
		// dùng presence ko quan tâm dc hiện thị hay ko mà nó quan tâm đến hiển thị hết
		// trong thẻ HTML
		// trong khoảng nhìn thấy là visible: nên có thể chỉ hiện thị ở scroll hiện tại
		// thôi

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCSS)));
		// đưa hêt item trong drop down vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCSS));
		// 3 - Tìm item xem đúng cái đang cần hay ko ( dùng vòng lặp duyệt)
		for (WebElement tempItem : speedDropdownItems) {
			// taọ ra biến tạm duyệt qua list item trên, gán 1 biến get text, hoặc get any
			// thuộc tính
			String itemText = tempItem.getText();
			System.out.println(itemText);
			// 4 - Kiểm tra cái text của item đúng vs cái mình mong muốn

			if (itemText.equals(expectedTextItem)) {
				// 5 - Click vào item đó
				System.out.println("click vào item");
				tempItem.click();
				// thoát ra khỏi vòng lặp sẽ không xét cho case còn lại dùng hàm break()
				break;
			} else {
				System.out.println("Không click vào item");
			}
		}

	}

	// kieu du lieu cua ham la webdriver
	public WebDriver getDriver() {
		return driver;

	}

}
