package Blur;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Blur
{
	String src = null;
	String src = null;
	BufferedImage img = null;
	
	//TODO: take kernel as an input parameter instead of hardcoded
	float[] kernel = {
	1/9f, 1/9f, 1/9f,
	1/9f, 1/9f, 1/9f,
	1/9f, 1/9f, 1/9f
	};
	
	//TODO: determine the size of the kernel instead of hardcoding
	BufferedImageOp blurKernel = new ConvolveOp(new Kernel(3, 3, kernel));
	
	//simple constructor to take the specified source file
	//and specify the output file
	public Blur(String src)
	{
		this.src = src;
		this.dst = "blur-" + src;
	}
	
	//this call will process the blur itself
	//a simple blur technique is taking the specified kernel (flipped), aligning its center
	//to each pixel in our image; then multiplying and adding the matrices.
	//  i1  i2  i3  i4 i5             x1 x2 x3
	//  i6  i7  i8  i9 i10  = image   x4 x5 x6  = kernel
	// i11 i12 i13 i14 i15            x7 x8 x9 
	// i16 i17 i18 i19 i20
	//
	// as an example, to calculate the blur value for i7 above, we do the following:
	// i1 by x1 + i2 by x2 + i3 by x3 + 
	// i6 by x4 + i7 by x5 + i8 by x6 +
	// i11 by x7 + i12 by x8 + i18 by x9
	
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