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

public class addFilm {
	
	static int movie_id = 0;
	
	public void make_film_button(String png) throws FileNotFoundException{
		
		movie_id++;
		
		int posterw = homePage.height/4 + 51;
		int posterh = homePage.width/4;
		
		homePage.x =10 + homePage.cnt*(20+2*posterw);
				
        //Film Poster/Button
        JButton film = new JButton();
        film.setName("movie"+movie_id);
        film.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		filmPage film_frame = new filmPage();
            	try {
    				film_frame.filmPage(film.getName());
    			} catch (IOException e1) {
    					// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
            	homePage.frame.setVisible(false);
        	}
        });
        film.setBackground(Color.DARK_GRAY);
        film.setBounds(homePage.x , homePage.y, posterw,posterh);
        homePage.poster = new ImageIcon(new ImageIcon(png).getImage().getScaledInstance(posterw, posterh, Image.SCALE_DEFAULT));
        film.setIcon(homePage.poster);
        homePage.panel.add(film);
              
        //Film info
        homePage.film_info = new JTextPane();
        homePage.film_info.setEditable(false);
        homePage.film_info.setBounds(10 + posterw + homePage.x, homePage.y,posterw,posterh);
        read_txt(homePage.root_folder + homePage.movie_folder + "/film_info.txt", homePage.film_info);
        homePage.panel.add(homePage.film_info);
        
        homePage.film_info_scrollPane = new JScrollPane(homePage.film_info);
        homePage.film_info_scrollPane.setBounds(10 + posterw + homePage.x, homePage.y,posterw,posterh);
        homePage.film_info_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        homePage.film_info_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        homePage.panel.add(homePage.film_info_scrollPane);
        
        //Σε κάθε γραμμή τρεις ταινίες
        homePage.cnt++;
        if(homePage.cnt == 3) {
        	homePage.y = homePage.y + posterh + 10;
        	homePage.cnt = 0;
        }
        
        homePage.panel.setPreferredSize(new Dimension(homePage.width + 80, homePage.y + posterh));
        homePage.frame.repaint();
    } 
	
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
