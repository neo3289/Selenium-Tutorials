package javaTester;



public class Topic_06_String {
public static void main (String[] args) {
	
		String username = "admin";
		String password ="admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		System.out.println(url);
		String[] arrayUrl= url.split("//");
		url = arrayUrl[0]+ "//" + username +":" + password +"@"+ arrayUrl[1];
		System.out.println(url);
		
		String firstName ="ABC";
		String lastName ="DBC";
		System.out.println(firstName+" "+lastName);
		System.out.println(firstName.concat("").concat(lastName));
	}
}

