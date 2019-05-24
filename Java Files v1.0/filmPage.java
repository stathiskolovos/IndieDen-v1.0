package indieDen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class filmPage extends JFrame {
	
	public filmPage() {
	}
	
	JPanel panel;
	JFrame frame;
	JTextArea film_title;
	JTextPane film_info;
	JTextField rating;
	
	JTextPane comm_area;
	JTextPane review_area;
	
	JButton post_review;
	JButton post_comment;
	
	JTextPane type_area;
	
	JScrollPane review_area_scrollPane;
	JScrollPane comm_area_scrollPane;
	JComboBox rating_cb;
	
	String filmTitle;
	String image;
	String review;
	String comment;
	String trailer;
	String movie;
	String age_rating_str;
	String url;
	String publisher;
	
	public int film_rating;
	int id;
			
	boolean show_reviews = true;
	
	int width = 1920;
	int height= 1080;
	
	private static JTextField rate_txt;
	
	int reviews_cntr = 0;
	String rv_cntr = "";
	
	int total_rating = 0;
	String total_rt = "";
	
	float average_rt = 0;
		
	public void filmPage(String str) throws IOException {
		
		/*Για τον υπολογισμό του μέσου όρου κάθε ταινίας χρειάζονται 2 δεδομένα
		 * ο counter(πόσες κριτικές έχουν γίνει) και η συνολική βαθμολογία που 
		 * εχει λάβει η ταινία*/
		//Αν ο φάκελος υπάρχει το σύστημα επιστρέφει τα αναγκαία δεδομένα

		File cntr = new File(homePage.root_folder + str + "/reviews_cntr.txt");
		if(cntr.exists()) {
			Scanner scan_cntr = new Scanner(cntr);	
			while(scan_cntr.hasNextLine()) {
				rv_cntr = rv_cntr.concat(scan_cntr.nextLine());		
			}
			reviews_cntr = Integer.valueOf(rv_cntr);
			
			scan_cntr.close();
			
			System.out.println("Counter : " + reviews_cntr);
			
			File total = new File(homePage.root_folder + str + "/total_rating.txt");
			Scanner scan_total = new Scanner(total);	
			while(scan_total.hasNextLine()) {
				total_rt = total_rt.concat(scan_total.nextLine());		
			}
			total_rating = Integer.valueOf(total_rt);	
			System.out.println("Total rating is: " +total_rating);
			
			scan_total.close();
		}
		
		//Αν έχουν γίνει κριτικές το σύστημα υπολογίζει το μο
		if(reviews_cntr > 0) {
			average_rt = (float)total_rating / (float)reviews_cntr;
			System.out.println("Average rating is: " +average_rt);
		}
		
		url = str;
			
		//Για να πάρουμε τον τίτλο κάθε ταινίας αρκεί να διαβάσουμε την πρώτη γραμμή του αρχείου info
		BufferedReader title = new BufferedReader(new FileReader(homePage.root_folder + str + "/film_info.txt"));
	    filmTitle = title .readLine();
	    title.close();//κλείνω τον file reader
		
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(width/2, height/2));
		frame.setTitle("IndieDen");
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {			
				homePage.frame.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(width - 20, height - 160));
        
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        
        //banner layout Panel
        JPanel banner_layout = new JPanel();
        banner_layout.setBackground(homePage.myColor);
        banner_layout.setPreferredSize(new Dimension(width,100));
        banner_layout.setLayout(new BorderLayout(0, 0));
        
        contentPane.add(banner_layout, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scrollPane);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //logo
        JLabel logo = new JLabel();
        ImageIcon logo_icon = new ImageIcon(new ImageIcon("icons/logo.png").getImage().getScaledInstance(162, 100, Image.SCALE_DEFAULT));
        logo.setIcon(logo_icon);
        banner_layout.add(logo, BorderLayout.WEST);
        
        film_title = new JTextArea();
        film_title.setEditable(false);
        film_title.setForeground(Color.WHITE);
        film_title.setFont(new Font("Calibri", Font.BOLD, 26));
        film_title.setText(filmTitle);
        film_title.setBackground(Color.DARK_GRAY);
        film_title.setBounds(10, 10, 400, 30);
        film_title.setColumns(10);
        panel.add(film_title);
        
        JButton trailer_btn = new JButton();
        //Ως εικόνα το poster της κάθε ταινίας
        File file = new File(homePage.root_folder + str +  "/poster");
        String[] fileList = file.list();
        //Επιστρέφει το όνομα του αρχείου που υπάρχει στο φάκελο
        for(String name:fileList){
            image = name;
        }
        ImageIcon trailer_btn_ico = new ImageIcon(new ImageIcon(homePage.root_folder + str +  "/poster/" +  image).getImage().getScaledInstance(400, 600, Image.SCALE_DEFAULT));
        trailer_btn.setIcon(trailer_btn_ico);
        trailer_btn.setBorder(null);
        trailer_btn.setBackground(Color.DARK_GRAY);
        trailer_btn.setBounds(10, 50, 400, 600);
        trailer_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 File file = new File(homePage.root_folder + str +  "/trailer");
        	     String[] fileList = file.list();
        	     for(String name:fileList){
        	    	 trailer = name;
        	     }
        		 try {
        			//Το αρχείο βίντεο παίζει με τον προεπιλεγμένο video player του συστήματος
					Desktop.getDesktop().open(new File(homePage.root_folder + str +  "/trailer/" +  trailer));
				 } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 }
        	}
        });
        panel.add(trailer_btn);
        
        JButton watch_btn = new JButton("Watch");
        watch_btn.setBorderPainted(false);
        watch_btn.setFont(new Font("Calibri", Font.BOLD, 16));
        watch_btn.setBackground(Color.LIGHT_GRAY);
        watch_btn.setBounds(30, 850, 150, 50);
        watch_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Αν υπάρχει το αρχείο monetized η ταινία είναι επι πληρωμή
        		 File monetized = new File(homePage.root_folder + str +  "/monetized.txt");
        		 if(monetized.exists()) {
        			 JOptionPane.showMessageDialog(null, "This film is not for free. You need to buy it in order to watch it.", "Warning!", JOptionPane.PLAIN_MESSAGE);
        		 }else {
        			 viewer_warning();
            		 play_movie();
            		
        		 }
        	}
        });
        panel.add(watch_btn);
        
        JButton buy_btn = new JButton("Buy");
        buy_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Έλεγχος αν υπάρχει το αρχείο
        		 File monetized = new File(homePage.root_folder + str +  "/monetized.txt");
        		 System.out.println(homePage.root_folder + str +  "/monetized.txt");
        		 if(monetized.exists()) {
	        		JOptionPane.showMessageDialog(null, "Don't worry we don't need your money yet.", "This is only a test.", JOptionPane.PLAIN_MESSAGE);
	        		viewer_warning();
	        		play_movie();
        		 }else {
        			 JOptionPane.showMessageDialog(null, "Just press the watch button.", "The film is for FREE!", JOptionPane.PLAIN_MESSAGE);
        		 }
        	}
        });
        buy_btn.setFont(new Font("Calibri", Font.BOLD, 16));
        buy_btn.setBorderPainted(false);
        buy_btn.setBackground(Color.LIGHT_GRAY);
        buy_btn.setBounds(190, 850, 150, 50);
        panel.add(buy_btn);
        
        JButton share_btn = new JButton("");
        share_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Copy the url and send it to a friend\n" + homePage.root_folder + str + "\nThank you <3", "Sharing is caring", JOptionPane.PLAIN_MESSAGE);
        	}
        });
        ImageIcon share_btn_icon = new ImageIcon(new ImageIcon("icons/share.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        share_btn.setIcon(share_btn_icon);
        share_btn.setBorderPainted(false);
        share_btn.setBackground(Color.DARK_GRAY);
        share_btn.setBounds(350, 850, 50, 50);
        panel.add(share_btn);
        
        JButton flag_btn = new JButton();
        //Έλεγχος αν υπάρχει το reports
        File reports = new File(homePage.root_folder + str +  "/reports.txt");
        flag_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Έλεγχος αν είναι user ή admin για να οδηγήσει στην κατάλληλη οθόνη
        		if(loginPage.admin == true) {
        			if(reports.exists()) {
        				reportPageAdmin admin = new reportPageAdmin();
            			try {
    						admin.reportPageAdmin(url);
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
        			}else {
        				JOptionPane.showMessageDialog(null, "This film has no reports.", "Nothing here!", JOptionPane.PLAIN_MESSAGE);
        			}    			
        		}else {
        			reportPage report = new reportPage();
        			report.report(url);
        		}       		   	           		
        	}
        });
        ImageIcon flag_btn_icon;
        //Αν υπάρχουν reports red flag για τον admin
        if(loginPage.admin == true && reports.exists()) {
        	flag_btn_icon = new ImageIcon(new ImageIcon("icons/red_flag.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        }else {
        	flag_btn_icon = new ImageIcon(new ImageIcon("icons/flag_ico.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        }
        flag_btn.setIcon(flag_btn_icon);
        flag_btn.setBorderPainted(false);
        flag_btn.setBackground(Color.DARK_GRAY);
        flag_btn.setBounds(410, 850, 50, 50);
        panel.add(flag_btn);
        
        film_info = new JTextPane();
        film_info.setForeground(Color.WHITE);
        film_info.setFont(new Font("Calibri", Font.BOLD, 16));
        film_info.setBorder(null);
        film_info.setBackground(Color.DARK_GRAY);
        film_info.setEditable(false);
        film_info.setBounds(420, 110, 600, 600);
        read_txt(homePage.root_folder + str + "/film_info.txt", film_info);
        panel.add(film_info);
        
        JScrollPane film_info_scrollPane = new JScrollPane(film_info);
        film_info_scrollPane.setBounds(420, 110, 600, 600);
        film_info_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        film_info_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        film_info_scrollPane.setBorder(null);
        panel.add(film_info_scrollPane);
        
        JButton review_btn = new JButton("Reviews");
        review_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		show_reviews = true;
        		check_state();
        	}
        });
        review_btn.setFont(new Font("Calibri", Font.BOLD, 16));
        review_btn.setBorderPainted(false);
        review_btn.setBackground(Color.LIGHT_GRAY);
        review_btn.setBounds(1200, 50, 150, 50);
        panel.add(review_btn);
        
        JButton comm_btn = new JButton("Comments");
        comm_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		show_reviews = false;
        		check_state();
        	}
        });
        comm_btn.setFont(new Font("Calibri", Font.BOLD, 16));
        comm_btn.setBorderPainted(false);
        comm_btn.setBackground(Color.LIGHT_GRAY);
        comm_btn.setBounds(1360, 50, 150, 50);
        panel.add(comm_btn);
        
       //Περιοχή όπου εμφανίζονται τα σχόλια
        comm_area = new JTextPane();
        comm_area.setForeground(Color.BLACK);
        comm_area.setFont(new Font("Calibri", Font.BOLD, 16));
        comm_area.setEditable(false);
        comm_area.setBorder(null);
        comm_area.setBackground(Color.WHITE);
        comm_area.setBounds(1200, 400, 600, 450);
        comm_area.setText("Comments");
        File comments_file = new File(homePage.root_folder + str + "/comments.txt");
        if(comments_file.exists() == true) {
        	read_txt(homePage.root_folder + str + "/comments.txt", comm_area);
        }
        panel.add(comm_area);
        
        comm_area_scrollPane = new JScrollPane(comm_area);
        comm_area_scrollPane.setBounds(1200, 400, 600, 450);
        comm_area_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        comm_area_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        comm_area_scrollPane.setBorder(null);
        panel.add(comm_area_scrollPane);
        
        //Περιοχή όπου εμφανίζονται οι κριτικές
        review_area = new JTextPane();
        review_area.setForeground(Color.BLACK);
        review_area.setFont(new Font("Calibri", Font.BOLD, 16));
        review_area.setEditable(false);
        review_area.setBorder(null);
        review_area.setBackground(Color.WHITE);
        review_area.setBounds(1200, 400, 600, 450);
        File reviews_file = new File(homePage.root_folder + str + "/reviews.txt");
        if(reviews_file.exists() == true) {
        	read_txt(homePage.root_folder + str + "/reviews.txt", review_area);
        }
        panel.add(review_area);
         
        review_area_scrollPane = new JScrollPane(review_area);
        review_area_scrollPane.setBounds(1200, 400, 600, 450);
        review_area_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        review_area_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        review_area_scrollPane.setBorder(null);
        panel.add(review_area_scrollPane);
        
        JButton back_btn = new JButton("Back");
        back_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {   
        		homePage.frame.setVisible(true);
        		frame.setVisible(false);
        		frame.dispose();
        	}
        });
        back_btn.setBackground(Color.LIGHT_GRAY);
        back_btn.setBounds(1780, 880, 100, 30);
        panel.add(back_btn);
        
        //Επιστροφή του ονόματοες του χρήστη που δημοσίευσε την ταινία
        read_publisher(homePage.root_folder + str +  "/publisher.txt");
             
        JButton delete_btn = new JButton("Delete");
        delete_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Προειδοποίηση  
        		int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove film from system?", "Warning!",
        		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		if(response == JOptionPane.YES_OPTION) {        			
            		try {
            			//διαγραφή του φακέλου ταινίας
    					FileUtils.deleteDirectory(new File(homePage.root_folder + str));
    				} catch (IOException e2) {
    					// TODO Auto-generated catch block
    					e2.printStackTrace();
    				}
            		//Μετά τη διαγραφή ελαττωση του πλήθους των ταινιών
            		homePage.reduce_cntr();
           		
            		//Από το όνομα του φακέλου κρατάω μόνο τον αριθμό ,το id
            		String folder_id = str.replaceAll("[^0-9]", "");
            		id = Integer.valueOf(folder_id);
           		
            		/*Σε αυτή τη λούπα μετανομάζω τους φακέλους των ταινιών που είναι μετα την ταινία
            		 * που διαγράφηκε
            		 * Αν είναι 5 φάκελοι και διαγράψω τον 3ο γινεται το εξής:
            		 * rename 4 to 3
            		 * remane 5 to 4*/

            		for(int i = id + 1; i <= homePage.movie_counter+1; i++) {
            			File oldf = new File(homePage.root_folder + "movie" + i);
            			File newf = new File(homePage.root_folder + "movie" + (i-1));
           			
            			oldf.renameTo(newf);
           			
            		}
            		JOptionPane.showMessageDialog(null, "Now app will terminate in order the changes to take place. Please relaunch the app.", "Movie was successfully removed!", JOptionPane.INFORMATION_MESSAGE);
            		
            		frame.setVisible(false);
            		frame.dispose();
            		
            		//Save new counter
            		try {
						homePage.create_cntr();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
            		
            		//Τερματισμός εφαρμογής για να ολοκληρωθεί η εργασία
            		//**add relaunch action later
            		System.exit(0);
        		}
        		
        	}
        });
        delete_btn.setBackground(Color.LIGHT_GRAY);
        delete_btn.setBounds(1670, 880, 100, 30);
        delete_btn.setVisible(false);
        panel.add(delete_btn);
        
        //Σύγκριση strings χωρίς όμως κενα και αλλαγές γραμμων
        if(publisher.replaceAll("\\s+","").equals(loginPage.username.replaceAll("\\s+","")) || loginPage.admin == true) {      	
        	delete_btn.setVisible(true);
        }else {
        	delete_btn.setVisible(false);
        }
        
        post_review = new JButton("Post");
        post_review.addActionListener(new ActionListener() {
        	//Έλεγχος αν ο χρήστης είναι συνδεδεμένος
        	public void actionPerformed(ActionEvent e) {
        		if(loginPage.logedin == false) {
        			JOptionPane.showMessageDialog(null, "You should be logged in in order to review a film.", "Sorry...", JOptionPane.INFORMATION_MESSAGE);
        			frame.setVisible(false);
        			frame.dispose();
        			loginPage login_frame = new loginPage();
        		}else{   
        			//αύξηση πλήθους κριτικών
        			reviews_cntr++;
        			rv_cntr = Integer.toString(reviews_cntr);
        			create_info(homePage.root_folder + str + "/reviews_cntr.txt", rv_cntr);
        			
        			//Πρόσθεση βαθμολογίας χρήστη στην ολική βαθμολογία της ταινίας
        			film_rating = rating_cb.getSelectedIndex();
        			total_rating = total_rating + film_rating;
        			total_rt = Integer.toString(total_rating);
        			create_info(homePage.root_folder + str + "/total_rating.txt", total_rt);
        			   			
        			//Update average rating graphics
        			average_rt = (float)total_rating / (float)reviews_cntr;
        			String avg_str = String.format("%.01f", average_rt);
        			rating.setText("This film's rating is " + avg_str + "/10");
        			
        			//Ως είσοδο τι έχει συμπληρώσει ο χρήστης στο type_area
        			review = type_area.getText();
            		create_reviews_txt(str);
            		
            		//Post review στην περιοχή των κριτικών
            		try {
    					read_txt(homePage.root_folder + str + "/reviews.txt", review_area);
    				} catch (FileNotFoundException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            		//Επιστροφή στην πρώτη επιλογή
            		rating_cb.setSelectedIndex(0);
            		
            		type_area.setText("Write your review...");         		
        		}
        		
        	}
        });
        post_review.setBackground(Color.LIGHT_GRAY);
        post_review.setBounds(1725, 345, 75, 30);
        panel.add(post_review);
        
        post_comment = new JButton("Post");
        post_comment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Έλεγχος αν ο user loged in
        		if(loginPage.logedin == false) {
        			JOptionPane.showMessageDialog(null, "You should be logged in in order to leave a comment.", "Sorry...", JOptionPane.INFORMATION_MESSAGE);
        			frame.setVisible(false);
        			frame.dispose();
        			//πίσω login page
        			loginPage login_frame = new loginPage();
        		}else {
        			//Ως είσοδο τι έχει συμπληρώσει ο χρήστης στο type_area
        			comment = type_area.getText();
            		create_comments_txt(str);
            		
            		//Post comment στην περιοχή των σχολίων
            		try {
    					read_txt(homePage.root_folder + str + "/comments.txt", comm_area);
    				} catch (FileNotFoundException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            		type_area.setText("Leave your comment...");
        		}
        		
        	}
        });
        post_comment.setBackground(Color.LIGHT_GRAY);
        post_comment.setBounds(1725, 345, 75, 30);
        panel.add(post_comment);
        
        //Περιοχή editable from user
        type_area = new JTextPane();
        type_area.setForeground(Color.BLACK);
        type_area.setFont(new Font("Calibri", Font.BOLD, 16));
        type_area.setEditable(true);
        type_area.setBorder(null);
        type_area.setBackground(Color.WHITE);
        type_area.setBounds(1200, 110, 600, 220);
        type_area.setText("Write your review...");
        panel.add(type_area);
        
        JScrollPane type_area_scrollPane = new JScrollPane(type_area);
        type_area_scrollPane.setBounds(1200, 110, 600, 220);
        type_area_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        type_area_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        type_area_scrollPane.setBorder(null);
        panel.add(type_area_scrollPane);
        
        rating = new JTextField();
        rating.setFont(new Font("Calibri", Font.BOLD, 20));
        rating.setEditable(false);
        rating.setBorder(null);
        rating.setBackground(Color.DARK_GRAY);
        rating.setForeground(Color.WHITE);
        rating.setBounds(420, 50, 600, 50);
        //Αν υπάρχει μέσος όρος
        if(average_rt > 0) {
        	String avg_str = String.format("%.01f", average_rt);
        	rating.setText("This film's rating is " + avg_str + "/10");   
        }else {
        	rating.setText("This film is not yet rated.");        
        }             
        panel.add(rating);
            	 	
    	rate_txt = new JTextField();
    	rate_txt.setText("Your rating : ");
    	rate_txt.setForeground(Color.WHITE);
    	rate_txt.setFont(new Font("Calibri", Font.BOLD, 16));
    	rate_txt.setBorder(null);
    	rate_txt.setBackground(Color.DARK_GRAY);
    	rate_txt.setBounds(1200, 345, 100, 30);
    	panel.add(rate_txt);
    	
    	rating_cb = new JComboBox();
    	rating_cb.setFont(new Font("Calibri", Font.BOLD, 16));
    	for (int i = 0; i <= 10; i++) {
    		rating_cb.addItem(i);
    	}
    	rating_cb.setBackground(Color.LIGHT_GRAY);
    	rating_cb.setBounds(1310, 345, 50, 30);
    	rating_cb.setSelectedIndex(0);
    	panel.add(rating_cb);
    	
    	//Αρχικά εμφανίζονται τα reviews
    	post_comment.setVisible(false);
    	comm_area.setVisible(false);
    	comm_area_scrollPane.setVisible(false);
    	type_area.setText("Write your review...");
    	       
        frame.setVisible(true);
        
        JOptionPane.showMessageDialog(null, "Click on the film poster to see the trailer.", "Just helping", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Παίζει η ταινία με τον προεπιλεγμένο player
	public void play_movie() {
		 File file = new File(homePage.root_folder + url +  "/video");
	     String[] fileList = file.list();
	     for(String name:fileList){
	    	 movie = name;
	     }
		 try {
			Desktop.getDesktop().open(new File(homePage.root_folder + url +  "/video/" +  movie));
		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 }
	}
	
	//Μέθοδος για να ελέγχει το σύστημα αν θα δείχνει τα σχόλια ή τις κριτικές
	public void check_state() {
		 if(show_reviews == true) {
	        	post_review.setVisible(true);
	        	review_area.setVisible(true);
	        	review_area_scrollPane.setVisible(true);
	        	rating_cb.setVisible(true);
	        	rate_txt.setVisible(true);
	        	
	        	post_comment.setVisible(false);
	        	comm_area.setVisible(false);
	        	comm_area_scrollPane.setVisible(false);
	        	
	        	type_area.setText("Write your review...");
	        }else {
	        	post_review.setVisible(false);
	        	review_area.setVisible(false);
	        	review_area_scrollPane.setVisible(false);
	        	rating_cb.setVisible(false);
	        	rate_txt.setVisible(false);
	        	
	        	post_comment.setVisible(true);
	        	comm_area.setVisible(true);
	        	comm_area_scrollPane.setVisible(true);
	        	
	        	type_area.setText("Leave your comment...");        	
	        }
	}
	
	public void read_age_rating(String str) throws FileNotFoundException{
		String content = "";
		
		File file = new File(str);
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			content = content.concat(scan.nextLine() + "\n");
		}
		
		age_rating_str = content;
		System.out.println(age_rating_str);
		
		scan.close();
	}
	
	public void read_publisher(String str) throws FileNotFoundException{
		String content = "";
		
		File file = new File(str);
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			content = content.concat(scan.nextLine() + "\n");
		}
		
		publisher= content;
		System.out.println(publisher);
		
		scan.close();
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
	
	//Ενημέρωση του αρχείου review.txt
	public void create_reviews_txt(String str){
		try {
			File txt = new File(homePage.root_folder + str + "/reviews.txt");
			FileWriter fileWriter = new FileWriter(txt, true);	
			fileWriter.write("A review by " + loginPage.username + "\n");
			fileWriter.write(review + "\n");
			fileWriter.write("User's rating is: " + film_rating + "/10\n\n");
			fileWriter.write("Posted on " +  java.time.LocalDate.now());
			fileWriter.write("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	//Ενημέρωση του αρχείου comments.txt
	public void create_comments_txt(String str){
		try {
			File txt = new File(homePage.root_folder + str + "/comments.txt");
			FileWriter fileWriter = new FileWriter(txt, true);
			fileWriter.write("A comment by " + loginPage.username + "\n");
			fileWriter.write(comment + "\n\n");
			fileWriter.write("Posted on " +  java.time.LocalDate.now());
			fileWriter.write("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Μέθοδος που χρησιμοποιείται για τη δημιουργία των total_rating και counter αρχείων*/
	public void create_info(String str,String str1) {
		try {
			File txt = new File(str);
			FileWriter fileWriter = new FileWriter(txt);
			if(str1.length() == 0 ) {str1 = "0";}
			fileWriter.write(str1);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException d) {
			d.printStackTrace();
		}
	}
	
	public void viewer_warning() {
		//Αν υπάρχει το αρχείο age_rating κατάλληλη προειδοποίηση
		 File age_rating = new File(homePage.root_folder + url +  "/age_rating.txt");
		 if(age_rating.exists()) {
			 try {
				//Επιστρέφει το περιεχόμενο του αρχείου για να το εμφανίσει στο μήνυμα
				read_age_rating(homePage.root_folder + url +  "/age_rating.txt");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Viewer discretion is advised. Creator has pointed that this film contains:\n" + age_rating_str, "Warning!", JOptionPane.PLAIN_MESSAGE);
		 }
	}
	
}
