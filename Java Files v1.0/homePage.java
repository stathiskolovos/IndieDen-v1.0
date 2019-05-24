package indieDen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class homePage extends JFrame {
	
	//Παίρνουμε τις διαστάσεις της οθόνης
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double screen_width = screenSize.getWidth();
	static double screen_height = screenSize.getHeight();
	
	//Θέτουμε το μέγεθος του παραθύρου στο μέγεθος της οθόνης
	static int width = (int)screen_width;
	static int height =(int)screen_height;
	
	static JFrame frame;
	static JPanel panel;
	
	JPanel contentPane;
	JPanel banner;
	JLabel logo;
	
	JPanel search_panel;
	JTextField search;
	JButton search_btn;
	
	static ImageIcon poster;
	static JTextPane film_info;
	static JScrollPane film_info_scrollPane;
	
	static JButton upload;
	static JButton user;
	
	static int cnt = 0;
	static int y = 10;
	static int x = 10;
	
	String content;
	String user_url = root_folder + "/users/" + loginPage.username;
	
	static String root_folder = System.getProperty("user.home") + "/Desktop/IndieDen/";
	static String movie_folder;
	public String profile_pic;
	public String search_str;
	
	String image = "";
		
	static int movie_counter = 0;
	static String mv_cntr = "";
	
	static Color myColor = Color.decode("#680223");
	
	public homePage() throws FileNotFoundException{
		
		//Δημιουργία του φακέλου εγκατάστασης
		new File(root_folder).mkdirs();
		
		//Αν ο το αρχείο movie_counter υπάρχει τότε το σύστημα κάνει retrieve τον counter
		File f = new File(root_folder + "movie_counter.txt");
		if(f.exists()) {
			retrieve_counter(root_folder + "movie_counter.txt");		
		}

		frame = new JFrame();
		frame.setMinimumSize(new Dimension(750, 450));
		frame.setTitle("IndieDen");
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			/*
			 * Κάθε φορά που κλείνει η εφαρμογή το σύστημα αποθηκεύει τον counter.
			 * Τον γράφει στο αρχείο.*/
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					create_cntr();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);
        
        contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        
        //Banner Layout
        banner = new JPanel();
        banner.setBackground(myColor);
        banner.setPreferredSize(new Dimension(width,100));
        banner.setLayout(new BorderLayout(0, 0));
        contentPane.add(banner, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scrollPane);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        logo = new JLabel();
        ImageIcon logo_icon = new ImageIcon(new ImageIcon("icons/logo.png").getImage().getScaledInstance(162, 100, Image.SCALE_DEFAULT));
        logo.setIcon(logo_icon);
        banner.add(logo, BorderLayout.WEST);
        
        //Search Panel Layout
        search_panel = new JPanel();
        search_panel.setBackground(myColor);
        banner.add(search_panel, BorderLayout.CENTER);
        search_panel.setLayout(null);
        
        //Search Textfield
        search = new JTextField();
        search.setBounds(110, 35, 250, 30);
        search.setBackground(Color.LIGHT_GRAY);
        search.setText("Search bar");
        search_panel.add(search);
        
        //Search Button
        JButton search_btn = new JButton();
        search_btn.setBorderPainted(false);
        search_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchPage SearchPage = new searchPage();
    			searchPage.frame.setVisible(false);
        		search_str = search.getText();
        		searchFilm Search = new searchFilm();
        		try {
					Search.searchFilm(search_str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		frame.setVisible(false); 
        		search.setText("Search bar");
        		}
        });
        search_btn.setBackground(Color.LIGHT_GRAY);
        ImageIcon search_ico = new ImageIcon(new ImageIcon("icons/search_ico.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        search_btn.setBounds(360, 35, 30, 30);
        search_btn.setIcon(search_ico);
        search_panel.add(search_btn);
        
        //Upload Button
        upload = new JButton();
        upload.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/* O χρήστης πρέπει να είναι συνδεδεμένος για να ανεβάσει ταινία στην εφαρμογή.
        		 * Το σύστημα ελέγχει αν ο χρήστης έχει συνδεθεί. Αν όχι τον κατευθύνει στο login page.
        		 * */

        		if(loginPage.logedin == false) {
        			JOptionPane.showMessageDialog(null, "You should be logged in in order to upload a film.", "Sorry...", JOptionPane.INFORMATION_MESSAGE);
        			frame.setVisible(false);
        			frame.dispose();
        			loginPage login = new loginPage();
        		}else {
        			//Απενεργοποίηση του κουμπιού.

        			upload.setEnabled(false);
        			//Αύξηση του counter για το πλήθος των ταινιών

            		movie_counter++;
            		//Δημιουργία του ονόματος του φακέλου ταινίας από το σύστημα 

            		movie_folder = "movie" + movie_counter;
            		//Μετατροπή int -> string για την αποθήκευση.
            		mv_cntr = Integer.toString(movie_counter);
            		//Δημιουργία του φακέλου της ταινίας και call uploadPage()
            		new File(root_folder + movie_folder).mkdirs();
            		try {
    					uploadPage upload = new uploadPage();
    				} catch (FileNotFoundException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
        		}        		
        	}
        });
        upload.setBorderPainted(false);
        ImageIcon upload_ico = new ImageIcon(new ImageIcon("icons/upload_ico.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        upload.setBounds(0 , 0 , 100, 100);
        upload.setBackground(myColor);
        upload.setIcon(upload_ico);
        search_panel.add(upload);
        
        //User Icon
        user = new JButton();
        user.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Έλεγχος αν ο admin είναι συνδεδεμένος
        		if(loginPage.admin == true) {
        			//Αν είναι η λειτουργία του κουμπιού είναι απλώς να κάνει logout από το σύστημα
        			frame.setVisible(false);
            		frame.dispose();
            		loginPage.admin = false;
            		loginPage.username = "Anonymous";
            		
            		//Καλόύμε login αν θέλει να ξανασυνδεθεί ως χρήστης ή admin
            		loginPage login_frame = new loginPage();
        		}else {
        			//Αν ο χρήστης είναι συνδεδεμένος το σύστημα ανοίγει το user profile
        			if(loginPage.logedin == true) {
            			try {
            				user.setEnabled(false);
        					userProfile userprofile = new userProfile();
        				} catch (FileNotFoundException e1) {
        					// TODO Auto-generated catch block
        					e1.printStackTrace();
        				}
            		//Αν δεν είναι τον οδηγεί πίσω στο login page προκειμένου να συνδεθεί.
            		}else {
            			JOptionPane.showMessageDialog(null, "You are not logged in.", "Sorry...", JOptionPane.INFORMATION_MESSAGE);
            			frame.setVisible(false);
            			frame.dispose();
            			loginPage loginpage = new loginPage();
            		}
        		}	
        	}
        });
        user.setBackground(myColor);
        user.setBorderPainted(false);
        user.setBorder(null);
        user.setPreferredSize(new Dimension(100,100));
        banner.add(user, BorderLayout.EAST);
        

        /*
         * Κάθε φορά που ανοίγει η εφαρμογή το σύστημα ανάλογα με τον counter καλεί
         * την addFilm προκειμένου να προσθέσει τις ταινίες στην αρχική σελίδα
         * */
        for(int i = 1; i < movie_counter+1; i++) {
        	
        	/*Για κάθε ταινία χρειαζόμαστε την αφίσα.
        	 * Μέσα στο φάκελο poster υπάρχει ένα αρχείο του οποίου το 
        	 * ονομα βρίσκουμε και το βάζουμε στο string image*/

        	File file = new File(root_folder + "/movie" + i + "/poster");
            String[] fileList = file.list();
            for(String name:fileList){
                image = name;
            }
            movie_folder = "movie" + i;
            //Call addFilm για να προστεθεί η ταινία στην αρχική. Την καλούμε με όρισμα
            //την διαδρομή για το αρχείο που βρίσκεται μέσα στο φάκελο poster.
            addFilm add_film = new addFilm(); 
            add_film.make_film_button(root_folder + "/movie" + i + "/poster/" + image);
        }
        
	}
	
	//***μπορώ σε μία κλάση και να την καλώ από κει
	public static void retrieve_counter(String str) throws FileNotFoundException{
		File file = new File(str);
		Scanner scan = new Scanner(file);
		
		//Ως str είναι η διαδρομή του αρχειου για τον counter
		while(scan.hasNextLine()) {
				mv_cntr = mv_cntr.concat(scan.nextLine());		
		}
		movie_counter = Integer.valueOf(mv_cntr);
		
		scan.close();
	}
	
	public static void reduce_cntr() {
		movie_counter = movie_counter - 1;
		mv_cntr = Integer.toString(movie_counter);
	}
	
	public static void create_cntr() throws IOException {
		File txt = new File(root_folder + "/movie_counter.txt");
		FileWriter fileWriter = new FileWriter(txt);
		if(mv_cntr.length() == 0 ) {mv_cntr = "0";}
		fileWriter.write(mv_cntr);
		fileWriter.flush();
		fileWriter.close();
	}
}
