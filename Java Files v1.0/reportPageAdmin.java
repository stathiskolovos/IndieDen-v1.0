package indieDen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.LogManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import javax.swing.JTextArea;

public class reportPageAdmin extends JFrame {
	public reportPageAdmin() {
	}

	private JPanel contentPane;
	public int id;
	
	//Resolution
	static int width = 635;
	static int height = 760;
	
	public void reportPageAdmin(String str) throws IOException{
		
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
        
        JTextField text = new JTextField();
        text.setForeground(Color.WHITE);
        text.setBounds(10, 110, 600, 30);
        text.setEditable(false);
        text.setFont(new Font("Calibri", Font.BOLD, 26));
        text.setText("The following reports have been filed for this film.");
        text.setBackground(Color.DARK_GRAY);
        text.setBorder(null);
        panel.add(text);
        
        JTextPane reports = new JTextPane();
        reports.setBounds(10, 150, 600, 500);
        reports.setFont(new Font("Calibri", Font.BOLD, 16));
        reports.setEditable(false);
        reports.setBorder(null);
        reports.setBackground(Color.WHITE);
        read_txt(homePage.root_folder + str + "/reports.txt", reports);
        panel.add(reports);
        
        JScrollPane reports_scrollPane = new JScrollPane(reports);
        reports_scrollPane.setBounds(10, 150, 600, 500);
        reports_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reports_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        reports_scrollPane.setBorder(null);
        panel.add(reports_scrollPane);
        
        JButton ignore = new JButton("Ignore");
        ignore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//αν ο admin πατησει ignore απλά το σύστημα σβήνει το αρχείο
        		new File(homePage.root_folder + str + "/reports.txt").delete();
        		frame.setVisible(false);
        		frame.dispose();
        	}
        });
        ignore.setBackground(Color.LIGHT_GRAY);
        ignore.setBounds(510, 680, 100, 30);
        panel.add(ignore);
        
        JButton strike = new JButton("Strike");
        strike.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//H λειτουργία του κουμπιού είναι η ίδια με την λειτουργία του κουμπιού delete στο filmPage
        		try {
					FileUtils.deleteDirectory(new File(homePage.root_folder + str));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        		
        		homePage.reduce_cntr();
        		
        		String folder_id = str.replaceAll("[^0-9]", "");
        		id = Integer.valueOf(folder_id);
        		
        		for(int i = id + 1; i <= homePage.movie_counter+1; i++) {
        			File oldf = new File(homePage.root_folder + "movie" + i);
        			File newf = new File(homePage.root_folder + "movie" + (i-1));
        			
        			oldf.renameTo(newf);
        			
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
        strike.setBackground(Color.LIGHT_GRAY);
        strike.setBounds(400, 680, 100, 30);
        panel.add(strike);
        
        frame.setVisible(true);
        frame.repaint();
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
