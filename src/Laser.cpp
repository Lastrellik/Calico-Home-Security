/**
  Calico Home Security System, Laser.cpp
  Purpose: Manages the Laser and creates a Laser object

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs

*/
#include "Arduino.h"
#include "Laser.h"
/**
  Constructor that sets the pin where the laser is and sets a pinMode that is
    to OUTPUT.
    The pin is set to:
    5 volts (5V boards);
    3.3 volts (3.3V boards);
*/
Laser::Laser(int pin) : Component(pin, "LASER"){
  if(Properties::MODULE_PI) Serial.write("13350"); // 13350 = Log, Debug, Laser object successfully created
  pinMode(pin, OUTPUT);
}
/**
  Base contructor
*/
Laser::Laser(){
}

void Laser::toggle(){
   if(_isOn){
     digitalWrite(Component::getPin(), LOW);
    _isOn = false;
   } else {
     digitalWrite(Component::getPin(), HIGH);
    _isOn = true;
   }
 }
/**
  Turns the Laser on
*/
void Laser::on(){
  if(Properties::MODULE_PI) Serial.write("13351"); // 13351 = Log, Debug, Laser has been turned on
  digitalWrite(Component::getPin(), HIGH);
  _isOn = true;
}
/**
  Turns the LASER off
*/
void Laser::off(){
  if(Properties::MODULE_PI) Serial.write("13352"); // 13352 = Log, Debug, Laser has been turned off
  digitalWrite(Component::getPin(), LOW);
  _isOn = false;
}
