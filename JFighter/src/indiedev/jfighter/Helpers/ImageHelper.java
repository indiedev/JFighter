package indiedev.jfighter.Helpers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;
public class ImageHelper 
{
	
	//loads the image from given String path
	public static final Image getResourceImage(File imagePath)
	{
		System.out.println("Image path:"+imagePath.getAbsolutePath());
		try
		{
			//loads the image
			return ImageIO.read(imagePath);
		
		}
		catch(Exception e)
		{
			System.out.println("Error while loading image"+e);
		}
		return null;
	}//getResourceImage

	public static final BufferedImage getCompatibleBufferedImage(Image img)
	{
		BufferedImage br_img;
		
		//checking if ALPHA component exists
		if(hasAlphaComponent(img))
		{
			//creating comptable bufferedimage(ALPHA)...
			br_img=SystemConstants.GFX_DEFCONFIG.createCompatibleImage(img.getWidth(null),img.getHeight(null),Transparency.BITMASK);
		}
		else
		{
			//creating comptable bufferedimage(NON-ALPHA)...
			br_img=SystemConstants.GFX_DEFCONFIG.createCompatibleImage(img.getWidth(null),img.getHeight(null),Transparency.OPAQUE);
		}
		
		//now getting the graphics from the BufferedImage.
		Graphics2D g2d=(Graphics2D)br_img.getGraphics();
		
		//drawing onto bufferedimage
		g2d.drawImage(img,0,0,null);
		g2d.dispose();
		
		//return the bufferedimage containing our Image
		return br_img;
	}
	
	
	
	//checks the APLHA component availability on a given Image
	private static final boolean hasAlphaComponent(Image img)
	{	
	    // Use a pixel grabber to retrieve the image's color model;
	    // grabbing a single pixel
        PixelGrabber pg = new PixelGrabber(img, 0, 0, 1, 1, false);
	    try {
	    	//grabbed the pixel
	        pg.grabPixels();
	        
	        //checking if has ALPHA component
	        if(pg.getColorModel().hasAlpha())
		        return true;
	        else
	        	return false;
	    } catch (InterruptedException e) {
	    	System.out.println("Unable to retrieve APLHA information for Image ");
	    	return false;
	    }

	}
	
}
