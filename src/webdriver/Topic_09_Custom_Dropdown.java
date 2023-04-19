package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	Actions action;
	// Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
// wait tường minh
	// implicit va exclicity la wait linh dong. neu chua tim thay thi cho tiep. neu
	// tim thay roi thi ko can cho het timout
	// Neu cho tiep mà đặt được điều kiện thấy element thì ko cần chờ hết timeout
	// nếu chờ tiếp mà hết timout thì đánh fail
	// hàm sleep bên dưới là wait fix cứng thời gian
	
	
	
	@BeforeClass
	public void beforeClass() {

		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

		} else {

			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		// implicitlyWait: wait ngầm định
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_JQuery() {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// * CHỌN LẦN 1 */
		System.out.println("CHỌN LẦN 1");
//		1 - Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();

//		2 - Chờ cho tất cả các item được load ra thành công
		// locator phải lấy xpath đại diện cho tất cả 5 elements
		// lấy đến thẻ nào chứa text
		// dùng presence ko quan tâm dc hiện thị hay ko mà nó quan tâm đến hiển thị hết
		// trong thẻ HTML
		// trong khoảng nhìn thấy là visible: nên có thể chỉ hiện thị ở scroll hiện tại
		// thôi

		explicitWait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		// đưa hêt item trong drop down vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
//3 - Tìm item xem đúng cái đang cần hay ko ( dùng vòng lặp duyệt)
		for (WebElement tempItem : speedDropdownItems) {
			// taọ ra biến tạm duyệt qua list item trên, gán 1 biến get text, hoặc get any
			// thuộc tính
			String itemText = tempItem.getText();
			System.out.println(itemText);
//	4 - Kiểm tra cái text của item đúng vs cái mình mong muốn

			if (itemText.equals("Fast")) {
//				5 - Click vào item đó
				System.out.println("click vào item");
				tempItem.click();
				// thoát ra khỏi vòng lặp sẽ không xét cho case còn lại dùng hàm break()
				break;
			} else {
				System.out.println("Không click vào item");
			}
		}
		sleepInSecond(5);

		// * CHỌN LẦN 2 */
		System.out.println("CHỌN LẦN 2");
//		1 - Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();

//		2 - Chờ cho tất cả các item được load ra thành công
		// locator phải lấy xpath đại diện cho tất cả 5 elements
		// lấy đến thẻ nào chứa text
		// dùng presence ko quan tâm dc hiện thị hay ko mà nó quan tâm đến hiển thị hết
		// trong thẻ HTML
		// trong khoảng nhìn thấy là visible: nên có thể chỉ hiện thị ở scroll hiện tại
		// thôi

		explicitWait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		// đưa hêt item trong drop down vào 1 list
		// List<WebElement> speedDropdownItems =
		// driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
//3 - Tìm item xem đúng cái đang cần hay ko ( dùng vòng lặp duyệt)
		for (WebElement tempItem : speedDropdownItems) {
			// taọ ra biến tạm duyệt qua list item trên, gán 1 biến get text, hoặc get any
			// thuộc tính
			String itemText = tempItem.getText();
			System.out.println(itemText);
//	4 - Kiểm tra cái text của item đúng vs cái mình mong muốn

			if (itemText.equals("Faster")) {
//				5 - Click vào item đó
				System.out.println("click vào item");
				tempItem.click();
				// thoát ra khỏi vòng lặp sẽ không xét cho case còn lại dùng hàm break()
				break;
			} else {
				System.out.println("Không click vào item");
			}

		}

	}

	@Test

	public void TC_02_ValidatePageTitle() {
	}

	@Test
	public void TC_03_LoginFormDisplayed() {

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
