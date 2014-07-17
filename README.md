##the imagemaster

A basic demonstration of various image processing techniques implemented in Java for cross platform demonstration.

###Compile instructions:

> javac Core.java

###Usage:

> java Core [filename] -[technique]

###Example usage:

> java Core grom.bmp -blur

> java Core grom.bmp -sharpen

This will blur/sharpen the file "grom.bmp" and output it into the same directory as "blur-grom.bmp" and "sharp-grom.bmp".

Before:

![loading](grom.bmp)

After blur:

![loading](../screenshots/blur-grom.bmp?raw=true)

After sharpen:

![loading](../screenshots/sharp-grom.bmp?raw=true)

###Current available image processing techniques:

-blur : this will use a symmetrical 1/9 kernel to blur the image specified.

-sharpen : this will use a -1/8th kernel with center element 2, to sharpen the image specified

###Coming soon..

-threshold

-noise

-sine
