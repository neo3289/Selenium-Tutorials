package javaTester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_type {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//kieu du lieu nguyen thuy (primitive): gia tri ko thay doi neu bien tham chieu thay doi : a= 5, a=b, a=7 => a=7, b=5
		// so nguyen: short, long - khong co phan thap phan
		
		// kich thuoc do rong de luu tru du lieu tu nho den lon
		
		byte bNumber= 127;
		short sNumber = 32000;
		int iNumber = 1234567890;
		long lNumber = 83294;
		
		// so thuc: float, double co thap phan
		float studentPoint = 9.5f;
		double employeeSalary = 35.6d;
		
		// logic: boolean
		boolean status = true;// name
		status = false;//nu
		
		//ki tu: char
		char a= 'A';
		
		
		//kieu du lieu tham chieu (reference): khi gia tri dc gan thay doi thi no se co the thay doi gia tri nhung thang gan theo
		//class
		FirefoxDriver driver = new FirefoxDriver();	
		// interface
		WebElement firstNameTextbox;
		// String
		 String firstName ="Automation testing";
		
		// oBJECT
		 Object people;
		 
		
		// ARRAY, chuoi luon nam trong nhay kep""
		 
		 String [] studentname = {"Nguyen Van An","Le Van Hung","Nguyen Thi Loan "};
		
		// COLLECTION: LIST/SET/QUEUE - advance JAVA
		 List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
		// MAP
		Map<String, Integer> student = new HashMap<String, Integer>();

	}

}
