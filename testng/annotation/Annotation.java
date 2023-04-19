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

public class Annotation {
//  @Test(dataProvider = "dp")
//  public void f(Integer n, String s) {
//  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("lam gi truoc moi test case");
  }

  
  @Test
  public void Login() {
	  System.out.println("LOGIN");
	  
  }
  @Test
  public void Register() {
	  System.out.println("Register");
  }
  
  @AfterMethod
  public void afterMethod() {
	  System.out.println("lam gi sau moi test case");
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
