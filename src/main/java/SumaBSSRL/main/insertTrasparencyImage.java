package SumaBSSRL.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;


public class insertTrasparencyImage {

	 // This method refers to the signature image to save
	private RenderedImage getImage(int w, int h) {

	    int width = w;
	    int height = h;

	    // Create a buffered image in which to draw
	    BufferedImage bufferedImage = new BufferedImage(width, height,
	            BufferedImage.TYPE_INT_RGB);

	    // Create a graphics contents on the buffered image
	    Graphics2D g2d = bufferedImage.createGraphics();

	    // Draw graphics
	    g2d.setColor(Color.WHITE);
	    g2d.fillRect(0, 0, width, height);
	    
	    g2d.setPaint(Color.BLACK);
	    g2d.drawOval(0, 0, width, height);

	    //drawPoints(Tablet.getPenPoints(), g2d, Color.BLACK);

	    // Graphics context no longer needed so dispose it
	    g2d.dispose();

	    return bufferedImage;
	}
    
}