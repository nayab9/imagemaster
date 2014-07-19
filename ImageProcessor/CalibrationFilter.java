package ImageProcessor;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
import java.awt.Color;
import java.lang.Math;

/*
	Subclass CalibrationFilter will take an input image and calibration image,
	by using the calibration image, we will calculate the offset which will be
	applied to the original corrupted image. This should return to us the original
	uncorrupted image.
	
	The way I have done this is as follows:
	It is assumed the calibration image was originally grey (128) per pixel.
	The sine corruption has changed the values; thus, by taking the difference
	between the original 128 and current value, we can determine the pattern
	that was applied to the calibration image.  Once we have this difference, we can
	apply the reverse effect onto the corrupted image, this should bring us back
	the original image prior to the corruption.
*/

public class CalibrationFilter extends ImageProcessor
{
	BufferedImage calibrate = null;
	private String calibration = "";
	private final int grey = 128;

	BufferedImageOp img_filter = null;
	
	//we will now be given an additional calibration image to use against our origin image
	public CalibrationFilter(String src, String dst, String calibration)
	{
		super(src);	
		super.setDst(dst + src);
		this.calibration = calibration;
		readCalibrationImage(calibration);
	}
	
	//once requested, the filter will be run on the requested image
	public void runFilter()
	{
		BufferedImage img = super.getImage();
		
		for (int y = 0; y < img.getHeight(); y++)
		{
			for (int x = 0; x < img.getWidth(); x++)
			{
				//for each x and y pixel, we will calculate the difference
				//between the calibration image and 128. This value will then be
				//applied to our original corrupted image by addition.
				int original_pixel = img.getRGB(x, y);
				int calibrate_pixel = calibrate.getRGB(x, y);
				Color o_pixel = new Color(original_pixel);
				Color c_pixel = new Color(calibrate_pixel);
				
				int red = addCalibration(o_pixel.getRed(), c_pixel.getRed());
				int green = addCalibration(o_pixel.getGreen(), c_pixel.getGreen());
				int blue = addCalibration(o_pixel.getBlue(), c_pixel.getBlue());
				
				super.manipulateImage(x, y, new Color(red, green, blue).getRGB());					
			}
		}
		
		System.out.println("Filter applied");
		
		super.writeImage();
	}
	
	//a simple method to read the calibration image
	private void readCalibrationImage(String src)
	{
		try
		{
			calibrate = ImageIO.read(new File(src));
			System.out.println("Calibration image read from: " + src);
		}
		catch (IOException e)
		{
			System.out.println("Read exception " + e.getMessage());
		}
	}
	
	private int getDifference(int value)
	{
		return (grey - value);
	}
	
	//given an origin pixel, calibration pixel and knowing grey = 128
	//calculate and return the newly minted pixel
	private int addCalibration(int original, int cal)
	{
		int difference = grey - cal;
		int total = original + difference;
	
		if (total > 255)
		{
			total = 255;
		}
		else if (total < 0)
		{
			total = 0;
		}
		
		//debug print statement
		//System.out.println("Original: " + original + " calibration: " + cal + " grey: " + grey + " new value: " + total);
		
		return total;
	}
}