package SumaBSSRL.main;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class insertImage {

	  public insertImage(String origPDF, String origImg, String outPut) throws DocumentException, IOException {
		  
			PdfReader reader = new PdfReader(origPDF);
			Image image = Image.getInstance(origImg);
			
			PdfStamper stamper = new PdfStamper(reader, new BufferedOutputStream(new FileOutputStream(outPut)));
			
			image.setAbsolutePosition(470, 50); //fix me
			
			PdfContentByte canvas = stamper.getOverContent(1);
			canvas.addImage(image);
			
			stamper.close();
		    
		  }

}
