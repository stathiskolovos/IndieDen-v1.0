package indieDen;

import java.awt.Color;
import java.awt.Image;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class loginPage extends JFrame {
	
	//Window elements
	JFrame frame;
	JPanel panel;
	JPanel banner;
	JLabel logo;
	
	JLabel username_lbl;
	JTextField user_txt;
	
	JLabel pass_lbl;
	JTextField pass_txt;
	
	JButton login_btn;
	JButton signup_btn;
	JButton cont_btn;
		
	public static boolean admin = false;
	public static boolean logedin = false;
	public static boolean pass_ok = false;
	
	static String username = "";
	static String password = "";
	public String profile_pic;
	
	//To url για να εντοπίζει η εφαρμογή το φάκελο users στο φάκελο εγκατάστασης
	public String user_url = System.getProperty("user.home") + "/Desktop/IndieDen/users/";
	
	
	public loginPage() {
		frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		banner = new JPanel();
		banner.setBounds(0, 0, 500, 100);
		banner.setBackground(homePage.myColor);
		panel.add(banner);
		banner.setLayout(null);
		
		logo = new JLabel();
        ImageIcon logo_icon = new ImageIcon(new ImageIcon("icons/logo.png").getImage().getScaledInstance(162, 100, Image.SCALE_DEFAULT));
        logo.setIcon(logo_icon);
        logo.setBounds(0,0,162,100);
        banner.add(logo);
        
        username_lbl = new JLabel("Username");
        username_lbl.setForeground(Color.WHITE);
        username_lbl.setFont(new Font("Calibri", Font.BOLD, 18));
        username_lbl.setBounds(175, 150, 150, 30);
        panel.add(username_lbl);
        
        user_txt = new JTextField();
        user_txt.setBounds(175, 180, 150, 30);
        panel.add(user_txt);
        user_txt.setColumns(10);
        
        pass_lbl = new JLabel("Password");
        pass_lbl.setForeground(Color.WHITE);
        pass_lbl.setFont(new Font("Calibri", Font.BOLD, 18));
        pass_lbl.setBounds(175, 250, 150, 30);
        panel.add(pass_lbl);
        
        pass_txt = new JTextField();
        pass_txt.setColumns(10);
        pass_txt.setBounds(175, 280, 150, 30);
        panel.add(pass_txt);
        
        //Εμφάνιση και λειτουργία login button
        login_btn = new JButton("Login");
        login_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Το σύστημα παίρνει σαν είσοδο αυτά που έχει πλητρολογήσει ο user στα πεδία.
        		username = user_txt.getText();
        		password = pass_txt.getText();
        		
        		//Πρώτος έλεγχος αν συνδέεται admin ή user
        		if(username.equals("admin") && password.equals("admin")) {
        			admin = true;
        			ImageIcon pic = new ImageIcon(new ImageIcon("icons/logout_ico.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
			    	homePage.user.setIcon(pic); 
        			homePage.frame.setVisible(true);
    				frame.setVisible(false);
    				frame.dispose();
        		}else{
        			//Έλεγχος αν ο χρήστης έχει αφήσει κενά τα πεδία.
        			if( username.length() > 0 || password.length() > 0) {
        				//Το σύστημα ψάχνει αν υπάρχει ο φάκελος για το username που δόθηκε και αν υπάρχει call retrieve_pass 
            			File user_file = new File(signupPage.users_path + "/" + username);
                		if(user_file.exists() == true) {
                			try {
    							retrive_pass(signupPage.users_path + "/" + username + "/password/password.txt");
    						} catch (FileNotFoundException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
                			//Αν η retrieve_pass επιστρέψει ότι pass_ok true τότε γίνεται το login
                			if(pass_ok == true) {
                				logedin = true;
                				//Αν ο χρήστης είναι παλιός και έχει προσθέσει εικόνα προφίλ το σύστημα ψάχνει να την φορτώσει.
                				File pic_file = new File(user_url + username + "/profile_pic");
                			    if(pic_file.exists()) {
                			    	String[] fileList = pic_file.list();
                			    	for(String name:fileList){
                			    		profile_pic = name;
                			    	}
                			    	ImageIcon pic;
                			    	
                			    	if(profile_pic == null) {
                			    		pic = new ImageIcon(new ImageIcon("icons/user_ico.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
                			    	}else {
                			    		pic = new ImageIcon(new ImageIcon(user_url + username+ "/profile_pic/" +  profile_pic).getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
                			    	}
                			    	homePage.user.setIcon(pic);        			    	
                			    }
                				homePage.frame.setVisible(true);
                				frame.setVisible(false);
                				frame.dispose();
                			}
                			else{
                				JOptionPane.showMessageDialog(null, "Wrong username or password.", "Please try again.", JOptionPane.INFORMATION_MESSAGE);
                			}
                		}else {
                			JOptionPane.showMessageDialog(null, "Wrong username or password.", "Please try again.", JOptionPane.INFORMATION_MESSAGE);
                		}
            		}else {
            			JOptionPane.showMessageDialog(null, "You should give a username and a password in order to login.", "Warning!", JOptionPane.INFORMATION_MESSAGE);
            		}
        		}   		
        	}
        });
        login_btn.setBackground(Color.LIGHT_GRAY);
        login_btn.setBounds(385, 430, 90, 25);
        panel.add(login_btn);
        
        signup_btn = new JButton("Sign Up");
        signup_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Αν ο χρήστης θέλει να δημιουργήσει ένα λογαριασμό
        		signupPage signFrame = new signupPage();
        	}
        });
        signup_btn.setBackground(Color.LIGHT_GRAY);
        signup_btn.setBounds(285, 430, 90, 25);
        panel.add(signup_btn);
        
        JButton cont_btn = new JButton("Continue without login");
        cont_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Αν ο χρήστης δεν θέλει να συνδεθεί. Το σύστημα φορτώνει ως profile pic την stock
		        ImageIcon user_icon = new ImageIcon(new ImageIcon("icons/user_ico.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
		        homePage.user.setIcon(user_icon);
        		homePage.frame.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
        	}
        });
        cont_btn.setBackground(Color.LIGHT_GRAY);
        cont_btn.setBounds(10, 430, 200, 25);
        panel.add(cont_btn);
		
		frame.setVisible(true);
	}
	
	
	/* Αρχικά διαβάσει το φάκελο που δόθηκε ως όρισμα και επιστρέφει το περιεχόμενό του
	 * στο string pass. Στη συνέχεια συγκρίνει το string αυτό με το password που δόθηκε
	 * στο textfield και αν είναι ίδια επιστρέφει pass_ok true*/
	public void retrive_pass(String str) throws FileNotFoundException{
		String pass = "";
		
		File file = new File(str);
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			pass = pass.concat(scan.nextLine());
		}
		
		if(password.equals(pass)) {
			pass_ok = true;
		}else {
			pass_ok = false;
		}
		
		scan.close();
	}
}
