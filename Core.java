import ImageProcessor.*;

/* This is a simple demonstration of basic image processing techniques
 * The purpose of this code is to allow us to easily create a simple
 * cross-platform image processing utility to manipulate bitmap images
 * 
 * Created by Ben Houshmand, July 2014
*/

public class Core
{
    public static void main(String[] args)
    {
		final String blur1 = "-blur";
		final String blur2 = "blur-";
		final String sharpen1 = "-sharpen";
		final String sharpen2 = "sharp-";
		
		//specified kernels, choose anything!
		float[] blur_kernel = {
		1/9f, 1/9f, 1/9f,
		1/9f, 1/9f, 1/9f,
		1/9f, 1/9f, 1/9f
		};
		
		float[] sharp_kernel = {
		-1/8, -1/8, -1/8,
		-1/8,    2, -1/8,
		-1/8, -1/8, -1/8
		};
		
		int kernel_row = 3;
		int kernel_col = 3;
	
		//simple parameter parsing, no error checking
		String file = args[0];
		String func = args[1];
		String param = "0";
		
		//some techniques require a parameter
		if (args.length > 2)
		{
			param = args[2];
		}
		
		//verbosity to state processing information
		System.out.println("Requested file: " + file);
		System.out.println("Image Process type: " + func);
		System.out.println("Paramter: " + param);

		//a simple filter dispatcher
		//call the appropriate class to process the image
		//as requested by the user via the command line
		if (func.equals(blur1))
		{
			System.out.println("Blurring image");
			LinearFilter myFilter = new LinearFilter(file, blur2, blur_kernel, kernel_row, kernel_col);
			myFilter.runFilter();
		}
		if (func.equals(sharpen1))
		{
			System.out.println("Sharpening image");
			LinearFilter myFilter = new LinearFilter(file, sharpen2, sharp_kernel, kernel_row, kernel_col);
			myFilter.runFilter();
		}		
		/*
		implement additional image processing techniques here
		if (func.equals(" "))
		{

		}
		*/

		System.out.println("Processing Complete");
    }

}