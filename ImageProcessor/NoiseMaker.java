package ImageProcessor;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImageOp;
import java.awt.Color;
import java.util.Random;

/*
	Subclass NoiseMaker will take an input image, and add salt and pepper
	noise by manually placing noise into it.
	
	This was created because for some reason, this was not available anywhere else!
*/

public class NoiseMaker extends ImageProcessor
{
	private final int pepper = 0;
	private final int salt = 255;

	BufferedImageOp img_filter = null;
	
	Random r = new Random();
	
	public NoiseMaker(String src, String dst)
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
				int remainder = x % 100;
				
				if (remainder == 0)
				{
					int random = r.nextInt(90);
					int coinflip = r.nextInt(2);
					if ((x + random) < img.getWidth())
					{
						int modify_pixel = random + x;
						
						if (coinflip < 1)
						{
							super.manipulateImage(modify_pixel, y, new Color(pepper, pepper, pepper).getRGB());	
						}
						else
						{
							super.manipulateImage(modify_pixel, y, new Color(salt, salt, salt).getRGB());
						}
					}
				}
			}
		}
		
		super.writeImage();
	}	
}