package webApp;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listener.ReportNGListener;



// gọi Hàm @Listner để chụp hình và làm report
@Listeners(ReportNGListener.class)
public class Admin {
	
	//CRUD: CUSTOMER
	// API: GET, PUT, DELETE


@Test(groups ="web")
public void Admin_01_Create_New_Admin() {

	
}
// test case phu thuoc
@Test(groups ="web", dependsOnMethods ="Admin_01_Create_New_Admin" )
public void Admin_02_View_Admin() {
	
}
// Test case 1, 2 phai PAss thi case 3 moi chạy
@Test(groups ="web", dependsOnMethods = {"Admin_01_Create_New_Admin" ,"Admin_02_View_Admin"})
public void Admin_03_Edit_Admin() {
	
}
@Test(groups ="web", dependsOnMethods ="Admin_03_Edit_Admin" )
public void Admin_04_Delete_Admin() {
	Assert.assertFalse(true);
}
}
