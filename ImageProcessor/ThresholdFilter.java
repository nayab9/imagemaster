package ImageProcessor;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
//import java.awt.image.ThresholdOp;

/*
	Subclass ThresholdFilter will take an input threshold, and compare each pixel
	in the bitmap to this threshold - any value below this threshold will be
	set to 0, and any value above will be set to 255.
	
*/

public class ThresholdFilter extends ImageProcessor
{
	private int threshold = 0;
	private int low = 0;
	private int high = 255;

	BufferedImageOp img_filter = null;
	
	//This subclass requires a kernel, with row and columns specified
	//in order to apply it to the image specified
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
		//img_filter = new ThresholdOP(threshold, low, high);
		super.setImage(img_filter.filter(super.getImage(), null));

		super.writeImage();
	}
}