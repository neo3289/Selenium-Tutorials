package annotation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotation03 {

  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("beforeMethod");
  }

	 
  @Test
  public void Login01() {
	  System.out.println("LOGIN01");
	  
  }

  
  @Test
  public void Login02() {
	  System.out.println("LOGIN02");
	  
  }
  
  
  @Test
  public void Login03() {
	  System.out.println("LOGIN03");
	  
  }
  
  
  
  @AfterMethod
  public void afterMethod() {
	  
	  System.out.println("afterMethod");
  }



  @BeforeClass
  public void beforeClass() {
	  System.out.println("Before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("afterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("before test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("after test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("before suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("afterSuite");
  }

}
