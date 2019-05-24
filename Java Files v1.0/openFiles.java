package indieDen;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class openFiles {
	
	//Για να ανοίξουμε το file manager
	public static void file_manager(String url) {
		try {
			 Desktop.getDesktop().open(new File(url));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   	
	}

}
