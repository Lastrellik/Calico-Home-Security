The Raspberry Pi Module
===============

The **Raspberry PI module** integrates a [Raspberry Pi 3](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/) into the circuit to add another layer of functionality. It is installed by simply plugging a serial cable from any of the USB ports on the Raspberry Pi into the serial port on the Arduino Uno. It is initially intended to include the following features:

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
Since not much is required for the Raspberry Pi Module to work, it would be wasteful to use the whole Raspbian Operating System to do what we're trying to do. Instead, what we've done is build the OS up from scratch starting with [Raspbian Jesse Lite](https://www.raspberrypi.org/downloads/raspbian/) and then installing the [PIXEL](https://www.raspberrypi.org/blog/introducing-pixel/) shell by following [this guide](https://www.raspberrypi.org/forums/viewtopic.php?f=66&t=133691). We then installed Java, SSH and VNC and set up port forwarding for SSH control. For users, we created a "developer" account with sudo permissions and a non-administrator "calico" account for the general application. The permissions on the development and JAR folders were set up accordingly. The "calico" account is set to log in by default.

To install the whole module, simply mount the touchscreen into the case by screwing it into the assembly and run the included ribbon cable into the monitor port in the Raspberry Pi. No additionaly drivers or configuration is required for the touchscreen. A more detailed set of instructions for connecting the Raspberry Pi to the touchscreen can be found [here](https://thepihut.com/blogs/raspberry-pi-tutorials/45295044-raspberry-pi-7-touch-screen-assembly-guide). 

Development
============
In order for the Raspberry Pi to communicate with the Arduino through Serial, we needed to add the user to the uucp, dialout, lock and tty groups according to [this guide](https://github.com/Fazecast/jSerialComm/wiki/Installation) from JSerialComm. 

Since there is no Java IDE (nor is there need for one) on this installation of Raspbian, we need to compile and run the Java code from the command line. To simplify this, create a bash script that compiles each of the jar files and specifies the classpath similar to this: 

```bash
#!/bin/bash
javac -cp /home/developer/Calico-Home-Security/lib/jSerialComm-1.3.11.jar module_PI/Raspberry_PI/*.java
java -cp .:/home/developer/Calico-Home-Security/lib/jSerialComm-1.3.11.jar module_PI.Raspberry_PI.PiApp
````

Notice the fully qualified names of the files and the use of the wildcard. The script will need to be ran from within the src folder for ease of access to each of the classes. Please keep in mind that this is just syntax and may not work specifically for an in-production build of Calico Home Security.
