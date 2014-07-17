import Blur.*;

public class Core
{
    public static void main(String[] args)
    {
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

		//a simple technique dispatcher
		//call the appropriate class to process the image
		//as requested by the user
		if (func.equals("-blur"))
		{
			System.out.println("Blurring image");
			Blur myBlur = new Blur(file);
			myBlur.runBlur();
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