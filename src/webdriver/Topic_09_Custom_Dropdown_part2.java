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

public class Topic_09_Custom_Dropdown_part2 {
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
	// hàm sleep bên dưới là wait fix cứng thời gian.
	
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

		// chọn item cho các dropdown dùng hàm
		// chọn lần 1
		System.out.println("1st select:");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		//span#speed-button>span.ui-selectmenu-text
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		sleepInSecond(5);
		// chọn lần 2
		System.out.println("2nd select:");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");

		// chọn lần 3
				System.out.println("3rd select:");
				selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
				Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
				

				// chọn lần 4
						System.out.println("4rd select:");
						selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Medium");
						Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");
						
						// chọn lần 5 drop khac
						System.out.println("5th select:");
						selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Prof.");
						Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");
						
	}

	@Test

	public void TC_02_RactJS() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(3);
		selectItemInDropdown("i.dropdown.icon", "span.text", "Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
		sleepInSecond(3);
		selectItemInDropdown("i.dropdown.icon", "span.text", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		selectItemInDropdown("i.dropdown.icon", "span.text", "Elliot Fu");
		//Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		
	}
	
	@Test
public void TC_03_VueJS() {
		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(3);
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		sleepInSecond(3);
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
	//	Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		
	}
	@Test
public void TC_04_Editable() {
		//list country dropdown
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(3);
		enterAndselectItemInDropdown("input.search", "span.text", "Albania");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");
		sleepInSecond(3);
		enterAndselectItemInDropdown("input.search", "span.text", "Anguilla");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Anguilla");
		
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

		//explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCSS)));
		// đưa hêt item trong drop down vào 1 list
		
		List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCSS)));
		// 3 - Tìm item xem đúng cái đang cần hay ko ( dùng vòng lặp duyệt)
		for (WebElement tempItem : speedDropdownItems) {
			// taọ ra biến tạm duyệt qua list item trên, gán 1 biến get text, hoặc get any
			// thuộc tính
			//dung trim de xoa khoang trang
			String itemText = tempItem.getText().trim();
			//in hêt mảng đó ra: System.out.println(itemText);
		//	System.out.println(itemText);
			// 4 - Kiểm tra cái text của item đúng vs cái mình mong muốn

			if (itemText.equals(expectedTextItem)) {
				// 5 - Click vào item đó
				System.out.println("click vào item:"+ expectedTextItem);
				tempItem.click();
				// thoát ra khỏi vòng lặp sẽ không xét cho case còn lại dùng hàm break()
				break;
			} 
			
		}

	}

	// kieu du lieu cua ham la webdriver
	public WebDriver getDriver() {
		return driver;

	}

	public void enterAndselectItemInDropdown(String textboxCSS, String allItemCSS, String expectedTextItem) {
		//1. clear data cu
		driver.findElement(By.cssSelector(textboxCSS)).clear();
		
		//2. nhap expected item vao - so ra các item matching voi search text
		driver.findElement(By.cssSelector(textboxCSS)).sendKeys(expectedTextItem);
		sleepInSecond(2);
		List<WebElement> DropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCSS)));
		
		for (WebElement tempItem : DropdownItems) {
			
			String itemText = tempItem.getText().trim();


			if (itemText.equals(expectedTextItem)) {
				sleepInSecond(2);
				System.out.println("click vào item:"+ expectedTextItem);
				tempItem.click();
				
				break;
			} 
			
		}

	}

	
	
}
