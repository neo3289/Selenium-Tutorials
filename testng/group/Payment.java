package group;

import org.testng.annotations.Test;

public class Payment {
	 
 @Test (groups ="payment")
 public void Cash() {
	  System.out.println("Cash");
	  
 }

 
 @Test (groups ="payment")
 public void Visa() {
	  System.out.println("Visa");
	  
 }
 
 
 @Test (groups ="payment")
 public void QRCode() {
	  System.out.println("QRCode");
	  
 }
}
