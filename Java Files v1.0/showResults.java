package indieDen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class showResults {
	
	// όλη η κλάση εκτελείται με παρόμοιο τρόπο όπως η addFilm
	
	int y = 10;
	int x = 10;
	
	public void show_search_results(int id) throws FileNotFoundException{
		
		String png = "";
		
		//Συντεταγμένες για την τοποθέτηση γραφικών
		int posterw = homePage.height/4 + 51;
		int posterh = homePage.width/4;
		
		x =10 + searchPage.cnt * (20+2*posterw);
		
	    //Film Poster/Button
	    JButton film = new JButton();
	    film.setName("movie"+id);
	    film.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		filmPage film_frame = new filmPage();
	        	try {
					film_frame.filmPage(film.getName());
				} catch (IOException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	searchPage.frame.setVisible(false);
	        	searchPage.frame.dispose();
	    	}
	    });
	    film.setBackground(Color.DARK_GRAY);
	    film.setBounds(x , y, posterw,posterh);
	    File file = new File(homePage.root_folder + "movie" + id +  "/poster");
	    String[] fileList = file.list();
	    for(String name:fileList){
	    	 png = name;
	    }
	    searchPage.poster = new ImageIcon(new ImageIcon(homePage.root_folder + "movie" + id +  "/poster/" + png).getImage().getScaledInstance(posterw, posterh, Image.SCALE_DEFAULT));
	    film.setIcon(searchPage.poster);
	    searchPage.panel.add(film);
	          
	    //Film info
	    searchPage.film_info = new JTextPane();
	    searchPage.film_info.setEditable(false);
	    searchPage.film_info.setBounds(10 + posterw + x, y,posterw,posterh);
	    read_txt(homePage.root_folder + "movie" + id + "/film_info.txt", searchPage.film_info);
	    searchPage.panel.add(searchPage.film_info);
	    
	    searchPage.film_info_scrollPane = new JScrollPane(searchPage.film_info);
	    searchPage.film_info_scrollPane.setBounds(10 + posterw + x, y,posterw,posterh);
	    searchPage.film_info_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    searchPage.film_info_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    searchPage.panel.add(searchPage.film_info_scrollPane);
	    
	    //Σε κάθε γραμμή τρεις ταινίες
	    if(searchPage.cnt == 3) {
	    	y = y + posterh + 10;
	    	searchPage.cnt = 0;
	    }
	    
	    searchPage.panel.setPreferredSize(new Dimension(homePage.width + 80, y + posterh));
	    searchPage.frame.repaint();
	} 

	//Το σύστημα διαβάζει το αρχείο και τοποθετεί το περιεχόμενο στο text pane
	public void read_txt(String str, JTextPane pane) throws FileNotFoundException{
		String content = "";
		
		File file = new File(str);
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			content = content.concat(scan.nextLine() + "\n");
		}
		
		pane.setText(content);	
		
		scan.close();
	}

}
