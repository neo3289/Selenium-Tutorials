package annotation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion {

	boolean status = false;
	String fullname ="selenium";
	
	@Test
	public void TC_01() {
		//assert true/false
		//IsXXX: isdisplayed/isEnable/isSelected/IsMultiple
		
		Assert.assertTrue(status);
		
		//BẰNG VS mong đợi
		//AssertEquals
		
		Assert.assertEquals(fullname, "Selenium");
	}
	
}
