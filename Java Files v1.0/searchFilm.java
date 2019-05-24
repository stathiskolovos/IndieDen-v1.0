package indieDen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class searchFilm {
	
	String title = "";
	String search = "";
	
	int max_length = 0;
    int same_cntr = 0;
    int spaces = 0;
    float percent = 0;
	
    //Καλείται με όρισμα το string που έδωσε ο user
	public void searchFilm(String str) throws IOException{
		
		/*Το σύστημα διαβάζει σε μια λούπα τον τίτλο κάθε ταινίας από το αντίστοιχο αρχείο της
		 *και τον συγκρίνει με το string που έδωσε ο χρήστης */
		for(int i = 1; i <= homePage.movie_counter; i++){
			BufferedReader gettitle = new BufferedReader(new FileReader(homePage.root_folder + "movie" + i + "/film_info.txt"));
			title = gettitle.readLine();
		    gettitle.close();//κλείνω τον file reader
		    
		    //Για να κρατήσω το αρχικό string που έδωσε ο χρήστης 
		    search = str;
		    
		    /* Για να γίνει η συγκριση πρέπει τα strings να είναι ισα σε μήκος 
		     * Το σύστημα ελέγχει ποιο από τα δύο string είναι το μεγαλύτερο
		     * και προσθέτει στο μικρότερο 1 μέχρι να φτάσει σε μήκος το 
		     * μεγαλύτερο.*/
		    if(title.length() > str.length()) {
		    	max_length = title.length();
		    	spaces = title.length() - str.length();
		    	for(int j = 0; j < spaces; j++) {
		    		str = str + "1";
		    	}
		    } 
		    else if(title.length() < str.length()) {
		    	max_length = str.length();
		    	spaces = str.length() - title.length();
		    	for(int j = 0; j < spaces; j++) {
		    		title = title + "1";
		    	}
		    } 
		    else max_length = str.length();
		      
		    //Αύξηση του counter όταν ίδιο χαρακτήρες
		    for(int k = 0; k < max_length; k++) {
		    	if(title.charAt(k) == str.charAt(k)) {
		    		same_cntr++;
		    	}
		    }
		    
		    //Υπολογισμός ποσοστού ίδιων χαρακτήρων
		    percent = ((float)same_cntr / (float)max_length) * 100;
		    
		    System.out.println("Film   :" + title);
		    System.out.println("Search :" + str);
		    System.out.println("Same   :" + same_cntr);
		    System.out.println("Success:" + (int) Math.round(percent) );
		    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		    
		    //στρογγυλοποίηση του ποσοστού
		    //Εμφάνιση αποτελεσμάτων που έχουν επιτυχία >= 25%
		    if((int) Math.round(percent)  >= 25) {
		    	showResults results = new showResults();
		    	results.show_search_results(i);	
		    	searchPage.cnt++;
		    }
		    
		    //reset values to 0
		    max_length = 0;
		    same_cntr = 0;
		    spaces = 0;
		    percent = 0;
		    
		    str = search;
		        
		}
		searchPage.frame.setVisible(true);
	}

}
