package group;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Users {
	 
 @Test (groups ="Users")
 public void Cash() {
	  System.out.println("Cash");
	  
 }

 
 @Test (groups ="Users")
 public void Visa() {
	  System.out.println("Visa");
	  
 }
 
 
 @Test (groups ="Users")
 public void QRCode() {
	  System.out.println("QRCode");
	  
 }
 
 
 @BeforeClass
	public void beforeClass() {
		
		
	}

 @AfterClass(alwaysRun= true)
 public void afterClass() {
 	//
 }

}




