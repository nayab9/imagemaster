package ImageProcessor;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
import java.awt.Color;

/*
	Subclass ThresholdFilter will take an input threshold, and compare each pixel
	in the bitmap to this threshold - any value below this threshold will be
	set to 0, and any value above will be set to 255.
	
	With the -color switch, a user can preserve color with the output image.
	If -color is left out, the image will become black & white.
*/

public class ThresholdFilter extends ImageProcessor
{
	private int threshold = 0;
	private int low = 0;
	private int high = 255;
	private String color = "";

	BufferedImageOp img_filter = null;
	
	//This subclass requires a kernel, with row and columns specified
	//in order to apply it to the image specified
	//two constructors, one for the color switch and one without
	public ThresholdFilter(String src, String dst, String threshold, int low, int high, String color)
	{
		super(src);	
		this.color = color;
		super.setDst(color + dst + src);
		this.threshold = Integer.parseInt(threshold);
		this.low = low;
		this.high = high;	
	}
	//without the -color switch, defaults to black and white
	public ThresholdFilter(String src, String dst, String threshold, int low, int high)
	{
		super(src);	
		super.setDst(dst + src);
		this.threshold = Integer.parseInt(threshold);
		this.low = low;
		this.high = high;	
	}
	//once requested, the filter will be run on the requested image
	public void runFilter()
	{
		BufferedImage img = super.getImage();
		
		//For this filter, we will need to modify each individual pixel to
		//determine whether or not it is above or below the threshold value.
		for (int y = 0; y < img.getHeight(); y++)
		{
			for (int x = 0; x < img.getWidth(); x++)
			{
				int pixel = img.getRGB(x, y);
				Color c = new Color(pixel);
				
				int red = getThreshold(c.getRed());
				int green = getThreshold(c.getGreen());
				int blue = getThreshold(c.getBlue());
					
				if (color.equals("color-"))
				{
					//we've determined the new threshold requirement, now set the
					//pixel in our image to the new low or high value				
					super.manipulateImage(x, y, new Color(red, green, blue).getRGB());
				}
				else
				{
					//we want black & white, thus we can average the pixels and choose
					//the threshold accordingly
					int average = getThreshold((c.getRed() + c.getGreen() + c.getBlue())/3);
					
					super.manipulateImage(x, y, new Color(average, average, average).getRGB());					
				}
			}
		}
		
		super.writeImage();
	}
	
	//a simple method to return the high or low value
	//determined by our threshold
	public int getThreshold(int input)
	{
		if (input < threshold)
		{
			return low;
		}
		else
		{
			return high;
		}
	}
}