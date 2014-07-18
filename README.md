##the imagemaster

A basic demonstration of various image processing techniques implemented in Java to support cross-platform testing.

###Compile instructions:

> javac Core.java

###Usage:

> java Core [filename] -[filter] [param(default:0)] -[color(optional)]

###Example usage:

> java Core grom.bmp -blur

> java Core grom.bmp -sharpen

> java Core grom.bmp -threshold 150

> java Core grom.bmp -threshold 150 -color

> java Core grom.bmp -makenoise

> java Core noisy-grom.bmp -removenoise

This will peform the requested filter on the file "grom.bmp" and output it into the same directory.

Before:

![loading](grom.bmp)

After blur:

![loading](../screenshots/blur-grom.bmp?raw=true)

After sharpen:

![loading](../screenshots/sharp-grom.bmp?raw=true)

After black and white threshold:

![loading](../screenshots/threshold-grom.bmp?raw=true)

After color threshold:

![loading](../screenshots/color-threshold-grom.bmp?raw=true)

After making noise:

![loading](../screenshots/noisy-grom.bmp?raw=true)

After removing noise:

![loading](../screenshots/removenoise-noisy-grom.bmp?raw=true)

###Current available image processing techniques:

-blur : this will use a symmetrical 1/9 kernel to blur the image specified.

-sharpen : this will use a -1/8th kernel with center element 2, to sharpen the image specified

-threshold: this will use a low value of 0 and high value of 255, and adjust each pixel according to a specified threshold

**BONUS:** the -color switch will allow the user to keep color instead of defaulting to black and white.

-removenoise: this will attempt to remove salt and papper noise from an image

**BONUS:** the -noisemaker switch will randomly add salt and pepper noise to an image

###Coming soon..

-sine
