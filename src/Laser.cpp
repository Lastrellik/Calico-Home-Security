/**
  Calico Home Security System, Laser.h
  Purpose: Manges the lASER and creats a LASER object

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
  digitalWrite(Component::getPin(), HIGH);
  _isOn = true;
}
/**
  Turns the LASER off
*/
void Laser::off(){
  digitalWrite(Component::getPin(), LOW);
  _isOn = false;
}
//Need to think about removing toggle as we don't really use it
void Laser::toggle(){
  if(_isOn){
    digitalWrite(Component::getPin(), LOW);
    _isOn = false;
  } else {
    digitalWrite(Component::getPin(), HIGH);
    _isOn = true;
  }
}
