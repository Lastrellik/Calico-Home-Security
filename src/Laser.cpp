/**
  Calico Home Security System, Laser.cpp
  Purpose: Manages the Laser and creates a Laser object

  @author Chris Nash, Jason Bruderer, David Tille, Tyler Jacobs
  @version To be Determined
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
  Serial.write(13350); // 13350 = Log, Debug, Laser object successfully created
  pinMode(pin, OUTPUT);
}
/**
  Base contructor
*/
Laser::Laser(){
}
/**
  Turns the lASER on
*/
void Laser::on(){
  Serial.write(13351); // 13351 = Log, Debug, Laser has been turned on
  digitalWrite(Component::getPin(), HIGH);
  _isOn = true;
}
/**
  Turns the LASER off
*/
void Laser::off(){
  Serial.write(13352); // 13352 = Log, Debug, Laser has been turned off
  digitalWrite(Component::getPin(), LOW);
  _isOn = false;
}
}
