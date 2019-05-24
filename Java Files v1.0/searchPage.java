package indieDen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class searchPage extends JFrame {
	
	static JFrame frame;
	static JPanel panel;
	static ImageIcon poster;
	static JTextPane film_info;
	static JScrollPane film_info_scrollPane;
	static JButton home;
	
	JPanel contentPane;
	JPanel banner;
	JLabel logo;
	
	static int cnt;
	
	//H σελίδα εμφανίζεται όταν τελειώσει η searchFilm
	public searchPage() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(750, 450));
		frame.setTitle("IndieDen");
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {			
				homePage.frame.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
				//reset to 0
				cnt = 0;
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
        banner.setBackground(homePage.myColor);
        banner.setPreferredSize(new Dimension(homePage.width,100));
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
        
        //User Icon
        home = new JButton();
        home.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		homePage.frame.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
				//reset to 0
				cnt = 0;
        	}
        });
        home.setBackground(homePage.myColor);
        home.setBorderPainted(false);
        home.setBorder(null);
        home.setPreferredSize(new Dimension(100,100));
        ImageIcon home_ico = new ImageIcon(new ImageIcon("icons/home_ico.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        home.setIcon(home_ico);
        banner.add(home, BorderLayout.EAST);
    }

}
