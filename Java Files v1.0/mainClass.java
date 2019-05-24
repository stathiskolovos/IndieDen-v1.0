package indieDen;

import java.io.FileNotFoundException;

public class mainClass {
	public static void main(String[] args) throws FileNotFoundException{
		
		/*
		 * Καλώ πρώτα τη loninPage για να κάνει ο χρήστης login στην
		 * εφαρμογή και στη συνέχεια καλώ τη homePage αλλά την κάνω 
		 * αόρατη προκειμένου να φορτωθούν όλα τα γραφικά.	
		 * */

		
		loginPage loginFrame = new loginPage();
		homePage homeframe = new homePage();
		homeframe.frame.setVisible(false);
	}
}
