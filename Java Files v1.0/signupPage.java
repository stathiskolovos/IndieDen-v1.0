package indieDen;

import java.awt.Color;
import java.awt.Image;

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
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

import org.apache.commons.io.FileUtils;

public class signupPage extends JFrame {
	
	JFrame frame;
	JPanel panel;
	JPanel banner;
	JLabel logo;
	
	JLabel username_lbl;
	JTextField user_txt;
	
	JLabel pass_lbl;
	JTextField pass_txt;
	
	JLabel email_lbl;
	JTextField email_txt;
	
	JButton confirm_btn;
	JButton cancel_btn;
	
	public static String users_path = homePage.root_folder + "/users";
	
	String username = "";
	String password = "";
	String email = "";

	
	public signupPage() {
		
		//Δημιουργία του φακέλου users στο φάκελο εγκατάστασης
		new File(users_path).mkdirs();
		
		frame = new JFrame("Sign Up");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 550, 600);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		banner = new JPanel();
		banner.setBounds(0, 0, 700, 100);
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
        username_lbl.setBounds(100, 150, 150, 30);
        panel.add(username_lbl);
        
        user_txt = new JTextField();
        user_txt.setColumns(10);
        user_txt.setBounds(100, 180, 150, 30);
        panel.add(user_txt);
        
        pass_lbl = new JLabel("Password");
        pass_lbl.setForeground(Color.WHITE);
        pass_lbl.setFont(new Font("Calibri", Font.BOLD, 18));
        pass_lbl.setBounds(100, 250, 150, 30);
        panel.add(pass_lbl);
        
        pass_txt = new JTextField();
        pass_txt.setColumns(10);
        pass_txt.setBounds(100, 280, 150, 30);
        panel.add(pass_txt);
        
        email_lbl = new JLabel("Email");
        email_lbl.setForeground(Color.WHITE);
        email_lbl.setFont(new Font("Calibri", Font.BOLD, 18));
        email_lbl.setBounds(100, 350, 150, 30);
        panel.add(email_lbl);
        
        email_txt = new JTextField();
        email_txt.setColumns(10);
        email_txt.setBounds(100, 380, 250, 30);
        panel.add(email_txt);
        
        confirm_btn = new JButton("Sign Up");
        confirm_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Το σύστημα παίρνει σαν είσοδο αυτά που έχει πλητρολογήσει ο user στα πεδία.
        		username = user_txt.getText();
        		password = pass_txt.getText();
        		email = email_txt.getText();
        		
        		//Πρώτος έλεγχος αν ο χρήστης έχει αφήσει κενά τα πεδία
        		if( username.length() > 0 || password.length() > 0) {
        			//Το σύστημα ελέγχει αν υπάρχει χρήστης με το ίδιο όνομα και εμφανίζει κατάλληλη προειδοποίηση
        			File user_file = new File(signupPage.users_path + "/" + username);
        			if(user_file.exists() == true){
        				JOptionPane.showMessageDialog(null, "There is already a user with the same name. Please try a different one", "Warning!", JOptionPane.INFORMATION_MESSAGE);
        			}else {
        				//Δημιουργία user file στο φάκελο εγκατάστασης

                		new File(users_path + "/" + username).mkdirs();
                		
                		//Δημιουργία password file στο φάκελο του user
                		new File(users_path + "/" + username + "/password").mkdirs();
                		create_user_info("password/password.txt", password);
                		
                		//Δημιουργία info file στο φάκελο του 
                		new File(users_path + "/" + username + "/info").mkdirs();
                		create_user_info("info/info.txt", email);
                		
                		//Δημιουργία profile pic file στο φάκελο του 
                		new File(users_path + "/" + username + "/profile_pic").mkdirs();
                		try {
                			//Ως πρώτη pic το σύστημα φορτώνει τη stock
							FileUtils.copyFileToDirectory(new File("icons/user_ico.png"), new File(users_path + "/" + username + "/profile_pic"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}              		
                		frame.setVisible(false);
                		frame.dispose();
        			}
        		}else {
        			JOptionPane.showMessageDialog(null,"You should give a username and a password in order to sugn up.", "Warning!", JOptionPane.INFORMATION_MESSAGE);
        		}
        		
        	}
        });
        confirm_btn.setBackground(Color.LIGHT_GRAY);
        confirm_btn.setBounds(420, 520, 90, 25);
        panel.add(confirm_btn);
        
        cancel_btn = new JButton("Cancel");
        cancel_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		frame.dispose();
        	}
        });
        cancel_btn.setBackground(Color.LIGHT_GRAY);
        cancel_btn.setBounds(320, 520, 90, 25);
        panel.add(cancel_btn);
		
		frame.setVisible(true);
	}
	
	/*
	 * Η μέθοδος παίρνει δύο ορίσματα τύπου String το str1 δείνει τη 
	 * διαδρομή του φακέλου και το str2 δείνει τη θα γραφτεί μέσα σε
	 * αυτό το αρχείο */
	public void create_user_info(String str1, String str2){
		try {
			File txt = new File(users_path + "/" + username + "/" + str1);
			FileWriter fileWriter = new FileWriter(txt);
			fileWriter.write(str2);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
