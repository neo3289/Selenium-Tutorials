package annotation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Annotation2 {
	
	// khởi tạo browser:
	@BeforeClass
	public void initBrowser() {
		
	}
	 
	  @Test
	  public void Login01() {
		  System.out.println("LOGIN1");
		  
	  }

	  
	  @Test
	  public void Login02() {
		  System.out.println("LOGIN2");
		  
	  }
	  @Test
	  public void Login03() {
		  System.out.println("LOGIN3");
		  
	  }
	  
	  @AfterClass
		public void ClosetBrowser() {
			
		}
}
