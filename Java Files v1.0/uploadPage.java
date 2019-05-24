package indieDen;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class uploadPage extends JFrame {
	
	JFrame frame;
	JPanel panel;
	JPanel banner;
	JLabel logo;
	
	JTextField title;
	JTextArea desc;
	JTextArea contr;
	JTextArea genres;
	JLabel agert;
	
	JLabel video_file;
	JButton add_video;
    JLabel video;
    
    JLabel srt_file;
    JButton add_srt;
    JLabel srt;
    
    JLabel trailer_file;
    JButton add_trailer;
    JLabel trailer;
    
    JLabel poster;
    JButton add_poster;
    JButton uploadPoster;
	
	JRadioButton monetize;
	JLabel price_tag;
    JTextField set_price;
    
    JButton confirm;
    JButton cancel;
	
	String file_url = homePage.root_folder + homePage.movie_folder;
	
	String image = "";
	String txt = "";
	
	String title_str = "";
	String description_str = "";
	String contributors_str = "";
	String genres_str = "";
	String price = "";
	
	String age_rating = "";
	
	boolean viewer_restriction = false;
	
	static public int movie_price = 0;
	
	ArrayList<String> info = new ArrayList<String>();
	
	//Custom Resolution
	int width = 1020;
	int height = 720;
	
	public uploadPage() throws FileNotFoundException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(50,50,width,height);
		frame.setTitle("Upload Film");
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				homePage.reduce_cntr();
        		homePage.upload.setEnabled(true);
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
        	
        title = new JTextField(10);
        title.setText("Film Title");
        title.setBounds(10, 110, 250, 20);
		panel.add(title);
		
		desc = new JTextArea();
		desc.setText("Film Description");
		desc.setBounds(10, 140, 250, 200);
		desc.setLineWrap(true);
		panel.add(desc);
		
		JScrollPane desc_scrollPane = new JScrollPane(desc);
		desc_scrollPane.setBounds(10, 140, 250, 200);
		desc_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		desc_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(desc_scrollPane);
        
        contr = new JTextArea();
        contr.setText("Contributors");
        contr.setBounds(10, 350, 250, 50);
        contr.setLineWrap(true);
        panel.add(contr);
        
        JScrollPane contr_scrollPane = new JScrollPane(contr);
        contr_scrollPane.setBounds(10, 350, 250, 50);
        contr_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contr_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(contr_scrollPane);
        
        genres = new JTextArea();
        genres.setText("Genres");
        genres.setBounds(10, 410 , 250, 50);
        genres.setLineWrap(true);
        panel.add(genres);
        
        JScrollPane genres_scrollPane = new JScrollPane(genres);
        genres_scrollPane.setBounds(10, 410, 250, 50);
        genres_scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        genres_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(genres_scrollPane);
        
        agert = new JLabel("Age Rating");
        agert.setForeground(Color.WHITE);
        agert.setBounds(10, 479, 70, 25);
        panel.add(agert);
        
        /*Checkboxes για το age rating της ταινίας
         *Έστω ένα από αυτά να είναι επιλεγμένο η boolean age restriction
         * γινεται true ώστε να εμφανίζεται η κατάλληλη προειδοποίηση 
         * πριν ο χρήστης παίξει την ταινία
         * */

        JCheckBox violence = new JCheckBox("Violence");
        violence.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(violence.isSelected()) {
        			age_rating = age_rating + "Violence\n";
        			viewer_restriction = true;
        		}
        	}
        });
        violence.setBackground(Color.DARK_GRAY);
        violence.setForeground(Color.WHITE);
        violence.setBounds(10, 520, 97, 23);
        panel.add(violence);
                
        JCheckBox gore = new JCheckBox("Gore");
        gore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(gore.isSelected()) {
        			age_rating = age_rating + "Gore\n";
        			viewer_restriction = true;
        		}
        	}
        });
        gore.setBackground(Color.DARK_GRAY);
        gore.setForeground(Color.WHITE);
        gore.setBounds(10, 560, 97, 23);
        panel.add(gore);
        
        JCheckBox ex_lang = new JCheckBox("Expicit Langouage");
        ex_lang.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(ex_lang.isSelected()) {
        			age_rating = age_rating + "Explicit Language\n";
        			viewer_restriction = true;
        		}
        	}
        });
        ex_lang.setForeground(Color.WHITE);
        ex_lang.setBackground(Color.DARK_GRAY);
        ex_lang.setBounds(10, 600, 150, 23);
        panel.add(ex_lang);
        
        JCheckBox nudity = new JCheckBox("Nudity");
        nudity.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(nudity.isSelected()) {
        			age_rating = age_rating + "Nudity\n";
        			viewer_restriction = true;
        		}
        	}
        });
        nudity.setForeground(Color.WHITE);
        nudity.setBackground(Color.DARK_GRAY);
        nudity.setBounds(160, 520, 97, 23);
        panel.add(nudity);
        
        JCheckBox sexual = new JCheckBox("Sexual Theme");
        sexual.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(sexual.isSelected()) {
        			age_rating = age_rating + "Sexual Theme\n";
        			viewer_restriction = true;
        		}
        	}
        });
        sexual.setForeground(Color.WHITE);
        sexual.setBackground(Color.DARK_GRAY);
        sexual.setBounds(160, 560, 130, 23);
        panel.add(sexual);
        
        JCheckBox drug_use = new JCheckBox("Drug Use");
        drug_use.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(drug_use.isSelected()) {
        			age_rating = age_rating + "Drug Use\n";
        			viewer_restriction = true;
        		}
        	}
        });
        drug_use.setForeground(Color.WHITE);
        drug_use.setBackground(Color.DARK_GRAY);
        drug_use.setBounds(160, 600, 97, 23);
        panel.add(drug_use);
        
        video_file = new JLabel("Video File");
        video_file.setFont(new Font("Calibri", Font.BOLD, 14));
        video_file.setForeground(Color.WHITE);
        video_file.setBounds(380, 110, 130, 25);
        panel.add(video_file);
        
        add_video = new JButton("Choose File");
        add_video.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*Δημιουργία του φακέλου video στο φάκελο της ταινίας και άνοιγμα για να κάνει ο
        		 * χρήστης drag n drop το αρχείο
        		 * */

        		new File(file_url + "/video").mkdirs();
        		openFiles.file_manager(file_url + "/video");
        	}
        });
        add_video.setFont(new Font("Calibri", Font.BOLD, 14));
        add_video.setBackground(Color.LIGHT_GRAY);
        add_video.setHorizontalTextPosition(SwingConstants.LEFT);
        add_video.setBounds(430, 140, 130, 40);
        panel.add(add_video);
        
        video = new JLabel();
        ImageIcon video_icon = new ImageIcon(new ImageIcon("icons/video_ico.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        video.setIcon(video_icon);
        video.setBounds(380, 140, 40, 40);
        panel.add(video);
               
        srt_file = new JLabel("Subtitles File");
        srt_file.setFont(new Font("Calibri", Font.BOLD, 14));
        srt_file.setForeground(Color.WHITE);
        srt_file.setBounds(380, 205, 130, 25);
        panel.add(srt_file);
        
        add_srt = new JButton("Choose File");
        add_srt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*Βοηθητικό μήνυμα και άνοιγμα του φακέλου video*/
        		JOptionPane.showMessageDialog(null, "In order your subtitles to work the name of subs and film should be the same.", "Warning!", JOptionPane.INFORMATION_MESSAGE);
        		openFiles.file_manager(file_url + "/video");
        	}
        });
        add_srt.setFont(new Font("Calibri", Font.BOLD, 14));
        add_srt.setBackground(Color.LIGHT_GRAY);
        add_srt.setHorizontalTextPosition(SwingConstants.LEFT);
        add_srt.setBounds(430, 235, 130, 40);
        panel.add(add_srt);
        
        srt = new JLabel();
        ImageIcon srt_icon = new ImageIcon(new ImageIcon("icons/srt_ico.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        srt.setIcon(srt_icon);
        srt.setBounds(380, 235, 40, 40);
        panel.add(srt);
         
        trailer_file = new JLabel("Movie Trailer");
        trailer_file.setForeground(Color.WHITE);
        trailer_file.setFont(new Font("Calibri", Font.BOLD, 14));
        trailer_file.setBounds(380, 300, 130, 25);
        panel.add(trailer_file);
        
        add_trailer = new JButton("Choose File");
        add_trailer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*Δημιουργία του φακέλου trailer στο φάκελο της ταινίας και άνοιγμα για να κάνει ο
        		 * χρήστης drag n drop το αρχείο
        		 * */

        		new File(file_url + "/trailer").mkdirs();
        		openFiles.file_manager(file_url + "/trailer");
        	}
        });
        add_trailer.setHorizontalTextPosition(SwingConstants.LEFT);
        add_trailer.setFont(new Font("Calibri", Font.BOLD, 14));
        add_trailer.setBackground(Color.LIGHT_GRAY);
        add_trailer.setBounds(430, 330, 130, 40);
        panel.add(add_trailer);
        
        trailer = new JLabel();
        ImageIcon trailer_icon = new ImageIcon(new ImageIcon("icons/trailer_ico.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        trailer.setIcon(trailer_icon);
        trailer.setBounds(380, 330, 40, 40);
        panel.add(trailer);
        
        poster = new JLabel("Film Poster");
        poster.setForeground(Color.WHITE);
        poster.setFont(new Font("Calibri", Font.BOLD, 14));
        poster.setBounds(740, 110, 130, 25);
        panel.add(poster);
        
        add_poster = new JButton();
        add_poster.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*Δημιουργία του φακέλου poster στο φάκελο της ταινίας και άνοιγμα για να κάνει ο
        		 * χρήστης drag n drop το αρχείο
        		 * */

        		new File(file_url + "/poster").mkdirs();
        		openFiles.file_manager(file_url + "/poster");
        	}
        });
        add_poster.setBackground(Color.LIGHT_GRAY);
        ImageIcon add_icon = new ImageIcon(new ImageIcon("icons/add_ico.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        add_poster.setIcon(add_icon);
        add_poster.setBounds(740, 140, 250, 360);
        panel.add(add_poster);
         
        uploadPoster = new JButton("Upload Poster");
        uploadPoster.setBackground(Color.LIGHT_GRAY);
        uploadPoster.setBounds(870, 510, 120, 30);
        uploadPoster.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Το σύστημα παίρνει το όνομα του αρχείου που βρίσκεται στον φακελο poster και το
        		//αποθηκεύει στο string image

        		File file = new File(file_url + "/poster");
                String[] fileList = file.list();
                for(String name:fileList){
                    image = name;
                }
                //Φόρτωση της εικόνας στο add_poster button
                ImageIcon poster_icon = new ImageIcon(new ImageIcon(file_url + "/poster/" +  image).getImage().getScaledInstance(250, 360, Image.SCALE_DEFAULT));
                add_poster.setIcon(poster_icon);  
        	}
        });
        panel.add(uploadPoster);
            
        price_tag = new JLabel("Movie Price :");
        price_tag.setForeground(Color.WHITE);
        price_tag.setFont(new Font("Calibri", Font.BOLD, 14));
        price_tag.setBounds(380, 475, 80, 25);
        panel.add(price_tag);
        
        set_price = new JTextField();
        set_price.setText("FREE!");
        set_price.setBounds(464, 475, 60, 20);
        set_price.setEnabled(false);
        panel.add(set_price);
        
        monetize = new JRadioButton("Monetization");
        monetize.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(monetize.isSelected()) {
        			set_price.setEnabled(true);
        		}else {
        			set_price.setEnabled(false);
        		}
        	}
        });
        monetize.setHorizontalTextPosition(SwingConstants.LEADING);
        monetize.setIconTextGap(10);
        monetize.setHorizontalAlignment(SwingConstants.CENTER);
        monetize.setFont(new Font("Calibri", Font.BOLD, 22));
        monetize.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        monetize.setBackground(Color.DARK_GRAY);
        monetize.setForeground(Color.WHITE);
        monetize.setBounds(380, 430, 180, 40);
        panel.add(monetize);
        
        //Ανέβασμα της ταινίας στην εφαρμογή
        confirm = new JButton("Upload");
        confirm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Προκειμένου να ολοκληρωθεί σωστά το ανέβασμα ο χρήστης θα πρέπει να έχει προσθέσει
        		//poster kai video kai trailer

        		File poster = new File(file_url + "/poster");
        		File video = new File(file_url + "/video");
        		File trailer = new File(file_url + "/trailer");
        		if(poster.exists() && video.exists() && trailer.exists()) {
        			//To σύστημα παίρνει ως εισόδους όλα τα πεδία που έχει επιλέξει
        			//και συπμληρώσει ο χρήστης

        			title_str = title.getText();
            		description_str = desc.getText();
            		contributors_str = contr.getText();
            		genres_str = genres.getText();
            		
            		if(set_price.isEnabled()) {
            			price = set_price.getText() + "$";
            		}else {
            			price = set_price.getText();
            		}
            		//Δημιουργία όλων των απαραίτητων αρχείων
            		create_txt();
            		
        			try {
        				//Call addFilm για να προστεθεί η ταινία στην αρχική
        				addFilm add_film = new addFilm();
    					add_film.make_film_button(file_url + "/poster/" +  image);
    				} catch (FileNotFoundException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
        			homePage.upload.setEnabled(true);
            		frame.setVisible(false);
            		frame.dispose();
        		}else {
        			JOptionPane.showMessageDialog(null, "In order to upload a film you need to add the movie, the trailer and a poster.","Warning!", JOptionPane.INFORMATION_MESSAGE);
        		}
        		
        	}
        });
        confirm.setBackground(Color.LIGHT_GRAY);
        confirm.setBounds(890, 630, 100, 30);
        panel.add(confirm);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Μείωση του counter επειδή αν και αυξήθηκε όταν πατήσε το κουμπί
        		//pload ο χρήστης αποφάσισε να μην ανεβάσει ταινία.
        		homePage.reduce_cntr();
        		homePage.upload.setEnabled(true);
        		frame.setVisible(false);
        		frame.dispose();
        	}
        });
        cancel.setBackground(Color.LIGHT_GRAY);
        cancel.setBounds(780, 630, 100, 30);
        panel.add(cancel);
         
        frame.setVisible(true);
        frame.repaint();
	}
	
	public void create_txt(){
		try {
			//τα περιεχόμενα της ταινίας
			File film_info_txt = new File(homePage.root_folder + homePage.movie_folder + "/film_info.txt");
			FileWriter fileWriter = new FileWriter(film_info_txt);
			fileWriter.write(title_str + "\n\n");
			fileWriter.write(description_str + "\n\n");
			fileWriter.write(contributors_str + "\n\n");
			fileWriter.write(genres_str + "\n\n");
			fileWriter.write("Price : " + price + "\n\n");
			fileWriter.write("Uploaded by user " + loginPage.username + " on " +  java.time.LocalDate.now());
			fileWriter.flush();
			fileWriter.close();
			
			//Αν υπάρχει κάποια επιλογη age rating δημιουργία αρχείου για αποθήκευση των επιλογών
			if(viewer_restriction == true) {
				File age_rating_txt = new File(homePage.root_folder + homePage.movie_folder + "/age_rating.txt");
				FileWriter awriter = new FileWriter(age_rating_txt);
				awriter.write(age_rating);
				awriter.flush();
				awriter.close();
			}
			
			//Αν η ταινία είναι επί πληρωμή
			if(monetize.isSelected()) {
				File monetized_txt = new File(homePage.root_folder + homePage.movie_folder + "/monetized.txt");
				FileWriter mwriter = new FileWriter(monetized_txt);
				mwriter.write(age_rating);
				mwriter.flush();
				mwriter.close();
			}
			
			//Αρχείο με το όνομα του χρήστη που δημοσίευσε την ταινία
			File publisher_txt = new File(homePage.root_folder + homePage.movie_folder + "/publisher.txt");
			FileWriter pwriter = new FileWriter(publisher_txt);
			pwriter.write(loginPage.username);
			pwriter.flush();
			pwriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
