package ImageProcessor;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
import java.awt.Color;

/*
	Subclass NoiseFilter will take an input image, and attempt to eliminate salt and pepper
	noise by using the average of surrounding pixels to calculate a new pixel value.
	
	TODO: Use a better averaging of surrounding pixels
*/

public class NoiseFilter extends ImageProcessor
{
	private int pepper = 0;
	private int salt = 255;

	BufferedImageOp img_filter = null;
	
	public NoiseFilter(String src, String dst)
	{
		super(src);	
		super.setDst(dst + src);	
	}
	
	//once requested, the filter will be run on the requested image
	public void runFilter()
	{
		BufferedImage img = super.getImage();
		
		for (int y = 0; y < img.getHeight(); y++)
		{
			for (int x = 0; x < img.getWidth(); x++)
			{
				int pixel = img.getRGB(x, y);
				Color c = new Color(pixel);
				
				int before_pixel = 0;
				int after_pixel = 0;
				
				int red = c.getRed();
				
				//we have noise! lets attempt to fix it
				if (salt == red || pepper == red)
				{	
					//get the before and after pixels
					if (x > 0)
					{
						before_pixel = img.getRGB(x-1, y);
					}
					if (x < img.getWidth()-1)
					{
						after_pixel = img.getRGB(x+1, y);
					}
					
					//get the pixel colors of the before and afters
					Color before_color = new Color(before_pixel);
					Color after_color = new Color(after_pixel);
					
					//generate an average value to use for our noisy pixel
					int average = (before_color.getRed() + before_color.getGreen() + before_color.getBlue() +
								  after_color.getRed() + after_color.getGreen() + after_color.getBlue()) / 6;
					
					//send our newly generated pixel through to be a part of the output image
					super.manipulateImage(x, y, new Color(average, average, average).getRGB());	
				}
			}
		}
		
		super.writeImage();
	}	
}