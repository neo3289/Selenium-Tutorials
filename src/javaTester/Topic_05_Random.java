package javaTester;

import java.util.Random;

public class Topic_05_Random {
public static void main (String[] args) {
	//kieu du lieu random
	//ultilities tien loi
	// data type : class/ interface/collection/string/float
	Random rand = new Random();
	//Random rand_02 = new Random();
	//Random rand_03 = new Random();
	//Random rand_04 = new Random();
	System.out.println(rand.nextDouble());
	System.out.println(rand.nextFloat());
	System.out.println(rand.nextInt(99999));
	System.out.println(rand.nextLong());
	System.out.println("Laneiger"+ rand.nextInt(999) +"@gmail.com");
}
}
