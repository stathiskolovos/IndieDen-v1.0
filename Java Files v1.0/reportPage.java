package indieDen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class reportPage extends JFrame {
	public reportPage() {
	}
	
	JFrame frame;
	JPanel panel;
	JPanel banner;
	JLabel logo;
	
	JTextField text;
	JTextField text1;
	
	private JTextField textField;
	public static String report_txt;
	
	static String report_info = "";
	
	//Custom Resolution
	static int width = 630;
	static int height = 600;
	
	public void report(String url){
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(50,50,width,height);
		frame.setTitle("Filing Report");
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
        		frame.setVisible(false);
        		frame.dispose();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JPanel banner = new JPanel();
		banner.setBounds(0, 0, width, 100);
		banner.setBackground(homePage.myColor);
		panel.add(banner);
		banner.setLayout(null);
		
		JLabel logo = new JLabel();
        ImageIcon logo_icon = new ImageIcon(new ImageIcon("icons/logo.png").getImage().getScaledInstance(162, 100, Image.SCALE_DEFAULT));
        logo.setIcon(logo_icon);
        logo.setBounds(0,0,162,100);
        banner.add(logo);
        
        text = new JTextField();
        text.setForeground(Color.WHITE);
        text.setBounds(10, 110, 200, 30);
        text.setEditable(false);
        text.setFont(new Font("Calibri", Font.BOLD, 26));
        text.setText("What is the issue?");
        text.setBackground(Color.DARK_GRAY);
        text.setBorder(null);
        panel.add(text);
        
        /*O χρήστης διαλέγει τα checkboxes που επιθυμεί. Αναλόγως στο string report_info
         * προστίθενται τα επιλεγμένα*/

        
        JCheckBox hateful = new JCheckBox("");
        hateful.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(hateful.isSelected()) {
        			report_info = report_info + "Hateful Content\n";
        		}
        	}
        });
        hateful.setForeground(Color.WHITE);
        hateful.setFont(new Font("Calibri", Font.BOLD, 20));
        hateful.setText("Hateful Content");
        hateful.setBackground(Color.DARK_GRAY);
        hateful.setBorder(null);
        hateful.setBounds(10, 160, 250, 30);
        panel.add(hateful);
        
        JCheckBox child_abuse = new JCheckBox("");
        child_abuse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(child_abuse.isSelected()) {
        			report_info = report_info + "Child Abuse\n";
        		}
        	}
        });
        child_abuse.setForeground(Color.WHITE);
        child_abuse.setFont(new Font("Calibri", Font.BOLD, 20));
        child_abuse.setText("Child Abuse");
        child_abuse.setBackground(Color.DARK_GRAY);
        child_abuse.setBorder(null);
        child_abuse.setBounds(10, 200, 250, 30);
        panel.add(child_abuse);
        
        JCheckBox terrorism = new JCheckBox("");
        terrorism.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(terrorism.isSelected()) {
        			report_info = report_info + "Promotes Terrorism\n";
        		}
        	}
        });
        terrorism.setForeground(Color.WHITE);
        terrorism.setFont(new Font("Calibri", Font.BOLD, 20));
        terrorism.setText("Promotes Terrorism");
        terrorism.setBackground(Color.DARK_GRAY);
        terrorism.setBorder(null);
        terrorism.setBounds(10, 240, 250, 30);
        panel.add(terrorism);
        
        JCheckBox violence = new JCheckBox("");
        violence.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(violence.isSelected()) {
        			report_info = report_info + "Incites Violence\n";
        		}
        	}
        });
        violence.setForeground(Color.WHITE);
        violence.setFont(new Font("Calibri", Font.BOLD, 20));
        violence.setText("Incites Violence");
        violence.setBackground(Color.DARK_GRAY);
        violence.setBorder(null);
        violence.setBounds(10, 280, 250, 30);
        panel.add(violence);
        
        JCheckBox age_rating = new JCheckBox("");
        age_rating.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(age_rating.isSelected()) {
        			report_info = report_info + "Inappropiate Age Rating\n";
        		}
        	}
        });
        age_rating.setForeground(Color.WHITE);
        age_rating.setFont(new Font("Calibri", Font.BOLD, 20));
        age_rating.setText("Inappropiate Age Rating");
        age_rating.setBackground(Color.DARK_GRAY);
        age_rating.setBorder(null);
        age_rating.setBounds(10, 320, 250, 30);
        panel.add(age_rating);
        
        JCheckBox copyright = new JCheckBox("");
        copyright.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(copyright.isSelected()) {
        			report_info = report_info + "Copyright Infringment\n";
        		}
        	}
        });
        copyright.setForeground(Color.WHITE);
        copyright.setFont(new Font("Calibri", Font.BOLD, 20));
        copyright.setText("Copyright Infrigement");
        copyright.setBackground(Color.DARK_GRAY);
        copyright.setBorder(null);
        copyright.setBounds(10, 360, 250, 30);
        panel.add(copyright);
        
        JCheckBox other = new JCheckBox("");
        other.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(other.isSelected()) {
        			report_info = report_info + "Other\n";
        		}
        	}
        });
        other.setForeground(Color.WHITE);
        other.setFont(new Font("Calibri", Font.BOLD, 20));
        other.setText("Other");
        other.setBackground(Color.DARK_GRAY);
        other.setBorder(null);
        other.setBounds(10, 400, 250, 30);
        panel.add(other);
        
        text1 = new JTextField();
        text1.setText("Please justify your report.");
        text1.setForeground(Color.WHITE);
        text1.setFont(new Font("Calibri", Font.BOLD, 26));
        text1.setEditable(false);
        text1.setBorder(null);
        text1.setBackground(Color.DARK_GRAY);
        text1.setBounds(300, 110, 300, 30);
        panel.add(text1);
        
        //Ο χρήστης εδώ γράφει παραπάνω πληροφορίες για το report του
        JTextPane report = new JTextPane();
        report.setForeground(Color.BLACK);
        report.setFont(new Font("Calibri", Font.BOLD, 16));
        report.setBorder(null);
        report.setBackground(Color.WHITE);
        report.setEditable(true);
        report.setBounds(300, 150, 300, 300);
        panel.add(report);
        
        JScrollPane report_scrollPane = new JScrollPane(report);
        report_scrollPane.setBounds(300, 150, 300, 300);
        report_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        report_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        report_scrollPane.setBorder(null);
        panel.add(report_scrollPane);
        
        JButton apply = new JButton("Apply");
        apply.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Το σύστημα παίρνει ως είσοδο αυτά που έχει συμπληρώσει ο χρήστης
        		report_txt = report.getText();
        		create_report_txt(url);
        		JOptionPane.showMessageDialog(null, "Your report has been filed successfully. Now admins will take care. Thank you for the input.", "Successful!", JOptionPane.INFORMATION_MESSAGE);
        		report_info = " ";
        		frame.setVisible(false);
            	frame.dispose();  	
        	}
        });
        apply.setBackground(Color.LIGHT_GRAY);
        apply.setBounds(500, 520, 100, 30);
        panel.add(apply);
        
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		frame.dispose();
        	}
        });
        cancel.setBackground(Color.LIGHT_GRAY);
        cancel.setBounds(390, 520, 100, 30);
        panel.add(cancel);
              
        frame.setVisible(true);
        frame.repaint();
	}
	
	//Δημιουργία του αρχείου report
	public static void create_report_txt(String str){
		try {
			File txt = new File(homePage.root_folder + str + "/reports.txt");
			FileWriter fileWriter = new FileWriter(txt, true);
			if(loginPage.username.length() == 0) {
				loginPage.username = "Anonymous";
			}
			fileWriter.write("A report filed by user " + loginPage.username + "\n");
			fileWriter.write("User has checked the following:\n" + report_info + "\n");
			fileWriter.write(report_txt + "\n\n");
			fileWriter.write("Applied on " +  java.time.LocalDate.now());
			fileWriter.write("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
