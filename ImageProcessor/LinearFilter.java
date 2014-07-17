package ImageProcessor;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/*
	Subclass LinearFilter will specifically apply 2D Convultion techniques
	to the image specified.
	
	Given an input kernel and image, apply the kernel to the image
	The user can choose any kernel and bitmap image for processing,
	the rest will be taken care of by this subclass
	
	The linear filter is done as follows:
	
	A specified kernel (flipped horizontally and vertically), aligning its center
	to each pixel in our image; then multiplying and adding the matrices.
	  i1  i2  i3  i4 i5             x1 x2 x3
	  i6  i7  i8  i9 i10  = image   x4 x5 x6  = kernel
	 i11 i12 i13 i14 i15            x7 x8 x9 
	 i16 i17 i18 i19 i20
	
	 as an example, to calculate the value for i7 above, we do the following:
	 
	 i1 by x1 + i2 by x2 + i3 by x3 + 
	 i6 by x4 + i7 by x5 + i8 by x6 +
	 i11 by x7 + i12 by x8 + i13 by x9
	 
	this accumulation of values will give us our entry at i7.
*/

public class LinearFilter extends ImageProcessor
{
	private int kernel_row = 0;
	private int kernel_col = 0;
	private float[] kernel;
	BufferedImageOp img_filter = null;
	
	//This subclass requires a kernel, with row and columns specified
	//in order to apply it to the image specified
	public LinearFilter(String src, String dst, float[] kernel, 
						int kernel_row, int kernel_col)
	{
		super(src);
		super.setDst(dst + src);
		this.kernel = kernel;
		this.kernel_row = kernel_row;
		this.kernel_col = kernel_col;
	}

	//once requested, the filter will be run on the requested image
	public void runFilter()
	{
		img_filter = new ConvolveOp(new Kernel(kernel_row, kernel_col, kernel));
		super.setImage(img_filter.filter(super.getImage(), null));

		super.writeImage();
	}
}