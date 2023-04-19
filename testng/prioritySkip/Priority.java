package prioritySkip;

import org.testng.annotations.Test;
/**
 * 2 ways to set test case order :
 * c1: set by Priority : input priority number
 * c2: named by Alphabet: ABC... XYZ/ abc...123...89
 * @author ADMIN
 *
 */

public class Priority {

	
	@Test(priority=3)
	public void deleteCustomer() {
		System.out.println("test  01");
		
	}
	@Test(priority=1)
	public void ReadCustomer() {
		System.out.println("test  02");
	}
	@Test(priority=0)
	public void newCustomer() {
		System.out.println("test  03");
	}
	@Test(priority=2)
	public void editCustomer() {
		System.out.println("test  04");
		
	}
	
	
/*
 * cách đặt tên theo abc này sẽ run theo order abc de cai nao chay truoc sau
 * dễ dàng quản lý chức năng
 * dùng số để dễ dàng tìm kiếm
 * dễ dàng count số lượng test case trong 1 class là bao nhiêu
 * cách viết này sẽ được ưu tiên hơn trong một project
 */
	@Test
	public void Customer_04() {
		System.out.println("test  abc 04");
		
	}
	@Test
	public void Customer_01() {
		System.out.println("test abc 01");
	}
	@Test
	public void Customer_02() {
		System.out.println("test  abc 02");
	}
	@Test
	public void Customer_03() {
		System.out.println("test  abc 03");
		
	}
	/**
	 * có thể set enabled true /false
	 * nếu ko set thì trông là enable
	 * hoặc comment anotation: //@Test
	 */
	
	@Test(enabled=false)
	public void deleteCustomer01() {
		System.out.println("test  01");
		
	}
	@Test(enabled=true)
	public void ReadCustomer02() {
		System.out.println("test  02");
	
	}
	
	/**
	 * có thể ghi mô tả của 1 test case trong annotation của @Test (description = " ")
	 * liên kết với 1 tESt case ID, JIRA....
	 * HIỂN thị thông tin vào trogn report HTML
	 */
	
	@Test(description = "id#12345_ create a new customer")
	public void ReadCustomer05() {
		System.out.println("test  05");
	
	}
	@Test(groups ="payment", priority =1, enabled =true, description = "create a new customer")
	public void ReadCustomer06() {
		System.out.println("test  02");
	
	}

}
