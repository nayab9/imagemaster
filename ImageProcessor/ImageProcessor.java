package ImageProcessor;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.Color;

/*
	This base class will provide the common functional processing across
	every type of image processing demonstration utilized.
	
	Each child sublass will implement their own filter as necessary.
*/

public class ImageProcessor
{
	private String src = null;
	private String dst = null;
	private BufferedImage img = null;
	
	//simple constructor to take the specified source file
	//and read the image into our image buffer for processing
	public ImageProcessor(String src)
	{
		this.src = src;
		readImage();
	}
	
	//parent class filter method, children will implement this
	public void runFilter()
	{
	}
	
	//simple method to read in the image specified for processing
	public void readImage()
	{
		try
		{
			img = ImageIO.read(new File(src));
			System.out.println("Image read from: " + src);
		}
		catch (IOException e)
		{
			System.out.println("Read exception " + e.getMessage());
		}
	}
	
	//method to write-out the newly processed image
	public void writeImage()
	{
		try
		{
			ImageIO.write(img, "BMP", new File(dst));
			System.out.println("Image written to: " + dst);
		}
		catch (IOException e)
		{
			System.out.println("Write exception " + e.getMessage());
		}
	}
	
	public void setImage(BufferedImage img)
	{
		this.img = img;
	}
	
	public BufferedImage getImage()
	{
		return img;
	}
	
	public void manipulateImage(int x, int y, int color)
	{
		img.setRGB(x, y, color);
	}
	
	public void setDst(String dst)
	{
		this.dst = dst;
	}
}