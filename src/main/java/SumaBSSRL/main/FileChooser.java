package SumaBSSRL.main;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FileChooser extends JFrame{  
    
    String pathPDF = null;
    String pathImg = null;
    
    public String getPath(int parameter) {
    	try {
	        JFileChooser jfc=new JFileChooser(System.getProperty("user.dir"));  
	        
	        if (parameter != 0)
	        	jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	        else
	        	jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
	        
	        
	        jfc.showDialog (new JLabel (), "Seleccionar");  
	        File file=jfc.getSelectedFile();  
	        
	        return file.getAbsolutePath();  	        	

    	} catch (Exception e) {
    		return null;

    	}
    }
  
}