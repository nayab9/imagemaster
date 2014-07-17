import Blur.*;

public class Core
{
    public static void main(String[] args)
    {
		String file = args[0];
		String func = args[1];
		String param = "0";
		
		if (args.length > 2)
		{
			param = args[2];
		}
		
		System.out.println("Requested file: " + file);
		System.out.println("Image Process type: " + func);
		System.out.println("Paramter: " + param);

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