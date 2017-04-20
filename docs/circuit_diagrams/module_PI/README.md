The Raspberry Pi Module
===============

The **Raspberry PI module** integrates a [Raspberry Pi 3](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/) into the circuit to add another layer of functionality. It is installed by simply plugging a serial cable from any of the USB ports on the Raspberry Pi into the serial port on the Arduino Uno. It includes the following features:

* A 7'' Touchscreen to allow for a GUI
* A timestamped logging system
* A way to manage users and keycodes
* Email alerts with attachments
* SSH and VNC control
* USB webcam support
* A web interface

This module is set up to arm, disarm, calibrate, decalibrate and trigger through the GUI. When the alarm is armed and the laser is broken, the attached USB webcam takes a picture and sends it as an attachment to the recipients listed in the command line arguments (see below). It then goes into a "tripped" state where it plays a waiting noise for 30 seconds while it waits for potential intruder to disarm the alarm. If it is not disarmed in the given time, it will "trigger" and sound the alarm while taking another picture to send as an email attachment. The alarm will sound until it is disarmed. All actions are logged in a log file called *PI_LOG.txt* and pictures are stored in the folder *PI_PICS* as jpg files.


Parts
-----
* [Raspberry Pi 3 Model B](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/)  
  -[This kit](https://www.amazon.com/LoveRPi-Raspberry-Plug-Play-Starter/dp/B01IYBZEV6/ref=sr_1_16?s=pc&ie=UTF8&qid=1491114514&sr=1-16&keywords=raspberry+pi+3) comes with all the hardware you need to get started.
* [7 Inch Touchscreen](https://www.amazon.com/Raspberry-Pi-7-Touchscreen-Display/dp/B0153R2A9I/ref=sr_1_1?s=electronics&ie=UTF8&qid=1491114627&sr=1-1&keywords=7+inch+touchscreen+raspberry+pi)
* [Raspberry Pi Touchscreen Case](https://www.amazon.com/Case-Official-Raspberry-Touchscreen-Display/dp/B01HV97F64/ref=pd_sim_147_1?_encoding=UTF8&psc=1&refRID=HCS99YG62WD3F73SRZXA)
* [USB webcam](https://www.amazon.com/Logitech-Widescreen-Calling-Recording-Desktop/dp/B006JH8T3S/ref=pd_lpo_147_bs_tr_img_2?_encoding=UTF8&psc=1&refRID=JT81BGE43A87D11F0GR4)


Raspberry Pi Setup
=====
To avoid wasting resources on the Raspberry Pi, the operating system has been built up from scratch starting with [Raspbian Jessie Lite](https://www.raspberrypi.org/downloads/raspbian/). Rasbian Lite is a Linux operating system built specifically for the Raspberry Pi and has no extra programs installed, not even a GUI, which will be installed later. Raspbian Jessie is installed by following these steps:

* [Download](https://www.raspberrypi.org/downloads/raspbian/) Raspbian Jessie Lite and burn it to an SD Card. Step by step instructions with download liks to all necessary tools are found [here](https://www.raspberrypi.org/documentation/installation/installing-images/README.md).
* Insert the SD card into the Raspberry PI and connect it to a monitor with an HDMI cable. Then connect a keyboard, mouse, webcam and power cable to the Pi.
* Once the Raspberry Pi boots up, log in with the username "pi" and password "raspberry".
* Create the "calico" and the "developer" user with the commands ```sudo adduser calico``` and ```sudo adduser developer```. When prompted, enter in the desired password and optionally add details when asked.
* Add the "developer" account to the suders group with the command ```sudo usermod -a -G sudo developer```.
* Switch over to the developer account with the command ```sudo su developer``` and enter in the developer password set up for that user account earlier.
* Remove the "pi" user with the command ```sudo userdel pi``` and delete the pi home folder with ```sudo rm -rf /home/pi```.
* For initial configuration, we'll need to expand the filesystem by going into the Raspberry Pi configuration menu with the command ```sudo raspi-config```. 
* Select the option to **Expand Filesystem** to expand the file system to the entire SD card. Reboot the Pi when asked.
* After rebooting, log into the Pi using the developer account and connect the Pi to WiFi by following [this guide](https://learn.adafruit.com/adafruits-raspberry-pi-lesson-3-network-setup/setting-up-wifi-with-occidentalis).
* Once connected to the network, update the Raspberry Pi software with the command ```sudo apt-get update && sudo apt-get upgrade -y && sudo reboot```
* Log into the Pi again and set up the appropriate language, regional setting and keyboard layout for your particular case with the command ```sudo raspi-config``` and selecting #5, "Internationalization Options".
* Next, install the [PIXEL](https://www.raspberrypi.org/blog/introducing-pixel/) shell with the following commands: 
```
sudo apt-get install --no-install-recommends xserver-xorg
sudo apt-get install raspberrypi-ui-mods
sudo apt-get install lightdm
```
if ever asked, be sure to install all dependencies.
* Reboot the Pi with ```sudo reboot``` and log in as the developer again, but this time using the gui.
* Open up a terminal window with ctrl+alt+t and go back into the Raspberry Pi configuration menu with ```sudo raspi-config``` and install VNC by navigating to **Interfacing Options** and selecting **VNC** and then choosing **Yes**. You can find more information on setting up and controlling the Raspberry Pi through VNC [here](https://www.raspberrypi.org/documentation/remote-access/vnc/).
* Next, install and enable open-ssh server through the same method as the previous step, but with **SSH** instead of **VNC**. More information on installing and controlling the Raspberry Pi via SSH can be found [here](http://www.instructables.com/id/Use-ssh-to-talk-with-your-Raspberry-Pi/)
* Install Maven with the command ```sudo apt-get install maven```
* Install git with the command ```sudo apt-get install git```
* Install Java with these commands:
```
sudo apt-add-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
```
* Insure Java is installed with ```javac -version```

## Touchscreen and Case Installation
The security system is equipped with a [Touchscreen](https://www.amazon.com/Case-Official-Raspberry-Touchscreen-Display/dp/B01HV97F64/ref=pd_sim_147_1?_encoding=UTF8&psc=1&refRID=HCS99YG62WD3F73SRZXA) and encapsulated in a [Case](https://www.amazon.com/Case-Official-Raspberry-Touchscreen-Display/dp/B01HV97F64/ref=pd_sim_147_1?_encoding=UTF8&psc=1&refRID=HCS99YG62WD3F73SRZXA) in order to maximize convenience. Installation instructions for the Touchscreen can be found [here](https://thepihut.com/blogs/raspberry-pi-tutorials/45295044-raspberry-pi-7-touch-screen-assembly-guide) and installation instructions for the case can be found [here](https://smarticase.com/products/smartipi-touch). A power adapter for the Pi and the Touchscreen is included with the case.

To install the whole module, simply mount the touchscreen into the case by screwing it into the assembly and run the included ribbon cable into the monitor port in the Raspberry Pi. No additionaly drivers or configuration is required for the touchscreen. A more detailed set of instructions for connecting the Raspberry Pi to the touchscreen can be found [here](https://thepihut.com/blogs/raspberry-pi-tutorials/45295044-raspberry-pi-7-touch-screen-assembly-guide). 

Development
============
In order for the Raspberry Pi to communicate with the Arduino through Serial, add the *developer* and *calico* users to the uucp, dialout, lock and tty groups according to [this guide](https://github.com/Fazecast/jSerialComm/wiki/Installation) from JSerialComm. Installation of Calico Home Security from the [official GitHub repository](https://github.com/Lastrellik/Calico-Home-Security) is accomplished on the Raspberry Pi by the following steps:
* Log in to the Raspberry Pi's GUI using the developer credentials.
* In the terminal, switch to the developer account with the command ```cd ~```
* Clone the GitHub repository with ```git clone https://github.com/Lastrellik/Calico-Home-Security.git```
* For security purposes, change the Linux permissions on all files in the **Calico Home Security** with the command ```chmod 700 ~/Calico-Home-Security/```
* Plug in the [Base Module](https://github.com/Lastrellik/Calico-Home-Security/tree/master/docs/circuit_diagrams/module_BASE) to a USB port on the Raspberry Pi.

## Running Calico Home Security
The JUnit tests on the Raspberry Pi are fully dependent on both the USB webcam and the Arduino Uno being plugged in to the Raspberry Pi. Running the program is currently accomplished by running the command ```mvn exec:java -Dexec.mainClass="module_PI.Raspberry_PI.main.PiApp" -Dexec.args="<sending gmail address> <sending account password> <all recepient email addresses separated by a space>"``` where **sending gmail address** is the email address the alert emails will be sending from, **sending account password** is the password of that Gmail account. 
