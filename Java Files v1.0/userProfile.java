package indieDen;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class userProfile extends JFrame {

	private JPanel contentPane;
	
	JFrame frame;
	JPanel panel;
	JPanel banner;
	JLabel logo;
	
	JButton logout;
	JButton upload_pic;
	JButton confirm;
	
	JTextField username;
	JTextPane email;
	JTextPane aboutme;
	JButton edit_btn;
	JButton pic_btn;
	
	public String user_url = homePage.root_folder + "/users/" + loginPage.username;
	public String email_str;
	public String aboutme_str;
	public String profile_pic;
	
	public boolean editing_profile = false;
	
	//Custom Resolution
	int width = 550;
	int height = 600;
		
	public userProfile() throws FileNotFoundException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(50,50,width,height);
		frame.setTitle("Profile page of user " + loginPage.username);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				homePage.user.setEnabled(true);
        		frame.setVisible(false);
        		frame.dispose();
			}
		});
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		banner = new JPanel();
		banner.setBounds(0, 0, width, 100);
		banner.setBackground(homePage.myColor);
		panel.add(banner);
		banner.setLayout(null);
		
		logo = new JLabel();
        ImageIcon logo_icon = new ImageIcon(new ImageIcon("icons/logo.png").getImage().getScaledInstance(162, 100, Image.SCALE_DEFAULT));
        logo.setIcon(logo_icon);
        logo.setBounds(0,0,162,100);
        banner.add(logo);
        
        logout = new JButton("");
        logout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//O user kanei logout
        		loginPage.logedin = false;
        		loginPage.username = "Anonymous";
        		homePage.user.setEnabled(true);
        		frame.setVisible(false);
        		frame.dispose();
        		homePage.frame.setVisible(false);
        		homePage.frame.dispose();
        		
        		loginPage login_frame = new loginPage();
        	}
        });
        logout.setBackground(homePage.myColor);
        logout.setBounds(430, 0, 100, 100);
        ImageIcon logout_icon = new ImageIcon(new ImageIcon("icons/logout_ico.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
        logout.setIcon(logout_icon);
        logout.setBorderPainted(false);
        logout.setBorder(null);
        banner.add(logout);
        
        username = new JTextField();
        username.setForeground(Color.WHITE);
        username.setEditable(false);
        username.setFont(new Font("Calibri", Font.BOLD, 18));
        username.setBackground(Color.DARK_GRAY);
        username.setBounds(30, 160, 250, 30);
        username.setBorder(null);
        username.setText(loginPage.username);
        panel.add(username);
        
        email = new JTextPane();
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Calibri", Font.BOLD, 18));
        email.setBackground(Color.DARK_GRAY);
        email.setBounds(30, 220, 250, 30);
        email.setEditable(false);
        read_info(user_url + "/info/info.txt", email);
        panel.add(email);
        
        aboutme = new JTextPane();
        aboutme.setForeground(Color.WHITE);
        aboutme.setFont(new Font("Calibri", Font.BOLD, 16));
        aboutme.setBackground(Color.DARK_GRAY);
        aboutme.setBounds(30, 280, 250, 250);
        aboutme.setEditable(false);
        File aboutmeFile = new File(user_url + "/aboutme");
        if(aboutmeFile.exists()) {
        	read_info(user_url + "/aboutme/aboutme.txt", aboutme);
        }
        panel.add(aboutme);
        
        JScrollPane aboutme_scrollPane = new JScrollPane(aboutme);
        aboutme_scrollPane.setBounds(30, 280, 250, 250);
        aboutme_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        aboutme_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(aboutme_scrollPane);
        
        edit_btn = new JButton("Edit Profile");
        edit_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		edit_btn.setVisible(false);
        		confirm.setVisible(true);
        		upload_pic.setVisible(true);
        		editing_profile = true;
        		
        		email.setEditable(true);
        		aboutme.setEditable(true);
        	}
        });
        edit_btn.setBackground(Color.LIGHT_GRAY);
        edit_btn.setBounds(380, 505, 120, 25);
        panel.add(edit_btn);
        
        pic_btn = new JButton("");
        pic_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Αν ο χρήστης έχει επιλέξει να κάνει edit
        		if(editing_profile == true) {
        			try {
        				//Διαγραφή του φακέλου που περιέχει την παλία pic
						FileUtils.deleteDirectory(new File(user_url + "/profile_pic"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			/*Δημιουργία νέου φακέλου και άνοιγμα του για να κάνει ο χρήστης
        			 * drag n drop την νέα του pic*/
        			new File(user_url + "/profile_pic").mkdirs();
            		openFiles.file_manager(user_url + "/profile_pic");
        		}
        	}
        });
        File pic_file = new File(user_url + "/profile_pic");
        if(pic_file.exists()) {
        	set_pic();
        }else {
        	//Αν δεν υπάρχει φορτώνει την stock pic
        	ImageIcon user_icon = new ImageIcon(new ImageIcon("icons/user_ico.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        	pic_btn.setIcon(user_icon);
        }    
        pic_btn.setBackground(Color.DARK_GRAY);
        pic_btn.setBounds(350, 160, 150, 150);
        pic_btn.setBorder(null);
        panel.add(pic_btn);
        
        upload_pic = new JButton("Upload");
        upload_pic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		set_pic();
        	}
        });
        upload_pic.setBackground(Color.LIGHT_GRAY);
        upload_pic.setBounds(410, 321, 90, 25);
        upload_pic.setVisible(false);
        panel.add(upload_pic);
        
        confirm = new JButton("OK");
        confirm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Προκειμένου να γίνουν αλλαγές ο χρήστης πρέπει να συνδεθεί ξανα
        		JOptionPane.showMessageDialog(null, "In order the changes to take part you have to sign in again.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        		
        		edit_btn.setVisible(true);
        		confirm.setVisible(false);
        		upload_pic.setVisible(false);
        		editing_profile = false;
        		
        		email.setEditable(false);
        		aboutme.setEditable(false);
        		
        		email_str = email.getText();
        		aboutme_str = aboutme.getText();
        		
        		edit_user_info("info/info.txt", email_str);
        		
        		new File(user_url + "/aboutme").mkdirs();
        		edit_user_info("aboutme/aboutme.txt", aboutme_str);
        		
        		ImageIcon pic = new ImageIcon(new ImageIcon(user_url + username+ "/profile_pic/" +  profile_pic).getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
		    	homePage.user.setIcon(pic);
		    	
		    	frame.setVisible(false);
		    	frame.dispose();
		    	
		    	homePage.frame.setVisible(false);
		    	homePage.frame.dispose();
		    	
		    	homePage.user.setEnabled(true);
		    	
		    	loginPage login = new loginPage();
        	}
        });
        confirm.setBackground(Color.LIGHT_GRAY);
        confirm.setBounds(410, 505, 90, 25);
        confirm.setVisible(false);
        panel.add(confirm);
        
        frame.setVisible(true);
        frame.repaint();
	}
	
	public static void read_info(String str, JTextPane pane) throws FileNotFoundException{
		String content = "";
		
		File file = new File(str);
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			content = content.concat(scan.nextLine() + "\n");
		}
		
		pane.setText(content);	
		
		scan.close();
	}
	
	public void edit_user_info(String str1, String str2){
		try {
			File txt = new File(user_url + "/" + str1);
			FileWriter fileWriter = new FileWriter(txt);
			fileWriter.write(str2);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public void set_pic() {
		File file = new File(user_url + "/profile_pic");
        String[] fileList = file.list();
        for(String name:fileList){
            profile_pic = name;
        }
        ImageIcon pic = new ImageIcon(new ImageIcon(user_url + "/profile_pic/" +  profile_pic).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        pic_btn.setIcon(pic); 
	}
}
