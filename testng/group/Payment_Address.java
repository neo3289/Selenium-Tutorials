package group;

import org.testng.annotations.Test;

public class Payment_Address {
	 
 @Test (groups ="Payment_Address")
 public void Cash() {
	  System.out.println("Cash");
	  
 }

 
 @Test (groups ="Payment_Address")
 public void Visa() {
	  System.out.println("Visa");
	  
 }
 
 
 @Test (groups ="Payment_Address")
 public void QRCode() {
	  System.out.println("QRCode");
	  
 }
}
