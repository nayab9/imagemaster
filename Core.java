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
		final String threshold1 = "-threshold";
		final String threshold2 = "threshold-";
		final String color1 = "-color";
		final String color2 = "color-";	
		final String noise1 = "-removenoise";
		final String noise2 = "removenoise-";
		final String noise3 = "-makenoise";
		final String noise4 = "noisy-";			
		final String sine1 = "-sine";
		final String sine2 = "sine-";
		
		//specified kernels, choose anything!
		float[] blur_kernel = {
		1/9f, 1/9f, 1/9f,
		1/9f, 1/9f, 1/9f,
		1/9f, 1/9f, 1/9f
		};
		
		float[] sharp_kernel = {
		-1/8f, -1/8f, -1/8f,
		-1/8f,    2f, -1/8f,
		-1/8f, -1/8f, -1/8f
		};
		
		int kernel_row = 3;
		int kernel_col = 3;
		
		int low = 0;
		int high = 255;
	
		//simple parameter parsing, no error checking
		String file = args[0];
		String func = args[1];
		String param = "0";
		String color = "";
		
		//some techniques require a parameter
		if (args.length > 2)
		{
			param = args[2];
		}
		if (args.length > 3)
		{
			color = args[3];
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
		else if (func.equals(sharpen1))
		{
			System.out.println("Sharpening image");
			LinearFilter myFilter = new LinearFilter(file, sharpen2, sharp_kernel, kernel_row, kernel_col);
			myFilter.runFilter();
		}		
		else if (func.equals(threshold1))
		{
			System.out.println("Thresholding image");
			
			ThresholdFilter myFilter = null;
			
			if (color.equals(color1))
			{
				System.out.println("-color switch requested");
				myFilter = new ThresholdFilter(file, threshold2, param, low, high, color2);
			}
			else
			{
				myFilter = new ThresholdFilter(file, threshold2, param, low, high);
			}
			
			myFilter.runFilter();
		}	
		else if (func.equals(noise1))
		{
			System.out.println("Removing noise from image");
			NoiseFilter myFilter = new NoiseFilter(file, noise2);
			myFilter.runFilter();
		}		
		else if (func.equals(noise3))
		{
			System.out.println("Adding noise to image");
			NoiseMaker myFilter = new NoiseMaker(file, noise4);
			myFilter.runFilter();
		}			
		else if (func.equals(noise3))
		{
			System.out.println("Adding noise to image");
			NoiseMaker myFilter = new NoiseMaker(file, noise4);
			myFilter.runFilter();
		}
		else if (func.equals(sine1))
		{
			System.out.println("Applying calibration image to corrupted image");
			CalibrationFilter myFilter = new CalibrationFilter(file, sine2, param);
			myFilter.runFilter();
		}
		
		System.out.println("Processing Complete");
    }

}