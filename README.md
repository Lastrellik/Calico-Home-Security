Calico Home Security
====================

![Laser Pointer](./docs/laser-160991.png)
<sub>(_Image courtesy of [pixabay](https://pixabay.com/en/laser-optics-science-laser-pointer-160991/)_)</sub>

The **Calico Home Security System** is a home security platform to detect and react to intruders. It is an _extensible_ system with multiple _modules_ available to be added to it.


Table of Contents
=================

- [Documentation](#documentation)
  * [Hardware](#hardware)
- [Development](#development)
- [Wiring Diagrams / Schematics](#wiring-diagrams--schematics)
- [About the Project](#about-the-project)
  * [Authors](#authors)
  * [Background](#background)
  * [Why Calico?](#why-calico)

Documentation
=============

Hardware
--------

* [Arduino Uno](https://www.arduino.cc/en/Main/ArduinoBoardUno)
* Red Laser
* Photoresistor
* Piezo Buzzer
* A Few LEDs
* Various Wires and Miscellaneous Odds & Ends


Development
===========

* [IDE / Dev Environment Setup Instructions](./docs/development/dev_environment_setup.md)
* [Logging & Command Message Details](./docs/development/logging.md)
* [Future Development Ideas](./docs/development/future/future_ideas.md)


Wiring Diagrams / Schematics
============================

The wiring diagrams are all kept in the [docs](./docs/circuit_diagrams) directory.

Note that some modules will not function when other modules are present. Please refer to the table in the [Circuit Diagrams Readme](./docs/circuit_diagrams/README.md) for a compatibility chart.

* [BASE Module](./docs/circuit_diagrams/module_BASE/README.md)
* [WIFI Module](./docs/circuit_diagrams/module_WIFI/README.md)
* [Raspberry Pi w/Touchscreen Module](./docs/circuit_diagrams/module_PI/README.md)
* [TURRET Module](./docs/circuit_diagrams/module_TURRET/README.md)


About the Project
=================

Authors
-------

* Christopher Nash
* Tyler Jacobs
* Dave Tille
* Jason Bruderer

Background
----------

This project was created as an assignment for our CSIS 2810 Computer Architecture course.

The requirements for our project were that it must include a Micro Controller and it must be the foundation of the project. We chose to use an Arduino Uno for our project. The other requirement was that it must include at least one 'sensor' which reads in data and makes decisions based on that data.

Picture in your mind the classic movie scene where the good guy is trying to break into the vault of the bad guy to get back the master plans he has stolen. To get into the vault, the good guy must pass through a maze of laser trip wires without breaking a single beam or the alarm will sound!

We wanted to replicate those laser trip wires. We also wanted to have a well architected, well documented, and totally awesome project to be able to present to the class. Thus the **Calico Home Security System** was born!


Why Calico?
-----------

*Calico*, adj: Having sections or patches colored differently and usually brightly.

We wanted our system to be modular from the very beginning. Calico seemed a fitting name.

![Calico Logo](./src/module_TOUCHSCREEN/logo.png)

**Big or Small, Calico does it all.**
