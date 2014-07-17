##the imagemaster

A basic demonstration of various image processing techniques.

###Compile instructions:

> javac Core.java

###Usage:

> java Core [filename] -[technique]

###Example usage:

> java Core grom.bmp -blur

This will blur the file grom.bmp and output it into the same directory as blur-grom.bmp.

Before and After images:

![loading](grom.bmp)

###Current available image processing techniques:

-blur : this will use a symmetrical 1/9 kernel to blur the image specified.

###Coming soon..
-sharpen
-threshold
-noise
-sine
