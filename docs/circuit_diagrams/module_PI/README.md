The Raspberry Pi Module
===============

The **Raspberry PI module** integrates a [Raspberry Pi 3](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/) into the circuit to add another layer of functionality. It is initially intended to include the following features:

*A 7'' Touchscreen to allow for a GUI
*A timestamped logging system
*A way to manage users and keycodes
*Email/Text Message alerts
*SSH control
*USB webcam support
*A web interface



Parts
-----
* [Raspberry Pi 3 Model B](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/)  
  -[This kit](https://www.amazon.com/LoveRPi-Raspberry-Plug-Play-Starter/dp/B01IYBZEV6/ref=sr_1_16?s=pc&ie=UTF8&qid=1491114514&sr=1-16&keywords=raspberry+pi+3) comes with all the hardware you need to get started.
* [7 Inch Touchscreen](https://www.amazon.com/Raspberry-Pi-7-Touchscreen-Display/dp/B0153R2A9I/ref=sr_1_1?s=electronics&ie=UTF8&qid=1491114627&sr=1-1&keywords=7+inch+touchscreen+raspberry+pi)
* [Raspberry Pi Touchscreen Case](https://www.amazon.com/Case-Official-Raspberry-Touchscreen-Display/dp/B01HV97F64/ref=pd_sim_147_1?_encoding=UTF8&psc=1&refRID=HCS99YG62WD3F73SRZXA)


Setup
=====
Since this not much is required for the Raspberry Pi Module to work, it would be wasteful to use the whole Raspbian Operating System to do what we're trying to do. Instead, what we've done is build the OS up from scratch starting with [Raspbian Jesse Lite](https://www.raspberrypi.org/downloads/raspbian/) and then installing the [PIXEL](https://www.raspberrypi.org/blog/introducing-pixel/) shell by following [this guide](https://www.raspberrypi.org/forums/viewtopic.php?f=66&t=133691). We then installed Java and set up a developer and user account and installed VNC and SSH. 

Development
============
In order for the Raspberry Pi to communicate with the Arduino through Serial, we needed to add the user to the uucp, dialout, lock and tty groups according to [this guide](https://github.com/Fazecast/jSerialComm/wiki/Installation) from JSerialComm. 

Since there is no Java IDE (nor is there need for one) on this installation of Raspbian, we need to compile and run the Java code from the command line. To simplify this, create a bash script that compiles each of the jar files and specifies the classpath similar to this: 

````
#!/bin/bash
javac -cp /home/developer/Calico-Home-Security/lib/jSerialComm-1.3.11.jar module_PI/Raspberry_PI/*.java
java -cp .:/home/developer/Calico-Home-Security/lib/jSerialComm-1.3.11.jar module_PI.Raspberry_PI.PiApp
````

Please keep in mind that this is just syntax and may not work specifically for an in-production build of Calico Home Security.
