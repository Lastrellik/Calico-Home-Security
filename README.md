Calico Home Security
====================

![Laser Pointer](./docs/laser-160991.png)
<sub>(_Image courtesy of [pixabay](https://pixabay.com/en/laser-optics-science-laser-pointer-160991/)_)</sub>

The **Calico Home Security System** is a home security platform to detect and react to intruders. It is an _extensible_ system with multiple _modules_ available to be added to it.


Documentation
=============

Hardware
--------

* [Arduino Uno](https://www.arduino.cc/en/Main/ArduinoBoardUno)
* Ordinary Red Laser Pointer
* Photoresistor
* Various Wires and Miscellaneous Odds & Ends


Development
===========

IDE / Dev Environment Setup Instructions
----------------------------------------

We've chosen to use **[PlatformIO](http://platformio.org/)** as our IDE. We chose this above using the main [Arduino IDE](https://www.arduino.cc/en/Main/Software) for a number of reasons:

1. The Arduino IDE is quite _'barebones'_. It doesn't support nice features like auto-complete or code linting. PlatformIO does out of the box.
2. PlatformIO is built on the popular [Atom](https://atom.io/) text editor. The community behind Atom has _many_ plugins for various code formats, styles, themes, etc.1
3. Arduino IDE doesn't have Git Plugin support. Because PlatformIO is built on Atom, the ability to use a Git Plugin was just a few clicks away. _(Sure - we could have used Git from the commandline, but we wanted to simplify our workflow as much as possible.)_
4. We knew coming in that we would likely be pulling in additional libraries for this project. Arduino IDE doesn't manage those for you. PlatformIO does out of the box.
5. PlatformIO is not only an IDE but a 'framework' which can be integrated into other IDEs such as Eclipse, CLion, Visual Studio, Netbeans, VIM, Emacs, Codeanywhere, and several others. This appealed to us because it gave the ability for a PlatformIO project to be built, run, uploaded, etc. using many development platforms.
6. Both the Arduino IDE and PlatformIO were supported on multiple platforms and were relatively self-contained which we wanted.

Some other IDEs considered:

1. [Sloeber](http://eclipse.baeyens.it/index.shtml) _(Prepackaged, Eclipse-based IDE.)_
2. [CLion](https://www.jetbrains.com/clion/)
3. [Programino](http://www.programino.com/) _(Offers a pretty neat feature which displays your Arduino board's pinouts and specs right in the IDE)_

### IDE Setup Instructions:

1. Ensure that a recent version of Python 2.X is installed
	* Most platforms, except [Windows](https://www.python.org/downloads/windows/), will likely have this preinstalled.
2. [Download](http://platformio.org/platformio-ide) the PlatformIO IDE for your platform
3. Run the installer. PlatformIO will install Atom then launch it.
	* Note - if you already have Atom installed it will launch the existing Atom instance you have installed and install itself as a plugin
4. Wait for installation to complete. When prompted, restart Atom.

### Git Atom Package Setup Instructions:
Ensure that you have a recent version of [Git](https://git-scm.com/downloads) installed before following these instructions.

1. In Atom...
2. File --> Settings
3. Select the Install tab
4. In the Search packages box type "git-plus". Press Enter to search.
5. Locate "git-plus" _(the correct one should have >1Million installs)_ and click the Install button
	* Note - it may show that there is a later version than the one that is supported by this version of Atom. Thats ok - just install the version which is available.
6. Click the Packages menu item. Git Plus should be listed.
7. Close the Setting tab



Compile Instructions
--------------------

1. asdf
2. asdf

Deploy Instructions
-------------------

1. asdf
2. asdf


Authors
=======

* Christopher Nash 
* Tyler Jacobs 
* Dave Tille 
* Jason Bruder

This project was created as an assignment for our CSIS 2810 Computer Architecture course.
