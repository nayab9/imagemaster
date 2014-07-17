package Blur;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Blur
{
	//TODO: take kernel as an input parameter instead of hardcode
	float[] kernel = {
	1/9f, 1/9f, 1/9f,
	1/9f, 1/9f, 1/9f,
	1/9f, 1/9f, 1/9f
	};
	
	String src, dst;
	
	BufferedImage img = null;
	BufferedImageOp blurKernel = new ConvolveOp(new Kernel(3, 3, kernel));
	
	public Blur(String src)
	{
		this.src = src;
		this.dst = "blur-" + src;
	}
	
	//demonstrate how the blur actually works, the matrix itself
	// x x x
	// x x x
	// x x x
	public void runBlur()
	{
		try
		{
			img = ImageIO.read(new File(src));
			img = blurKernel.filter(img, null);
			ImageIO.write(img, "BMP", new File(dst));
			System.out.println("Image output to: " + dst);
		}
		catch (IOException e)
		{
			System.out.println("Exception " + e.getMessage());
		}	
	}
}