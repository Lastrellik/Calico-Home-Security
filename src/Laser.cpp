/*
  Laser.cpp - Simmple library for the laser module
*/
#include "Arduino.h"
#include "Laser.h"

Laser::Laser(int pin) : Component(pin, "LASER"){
  pinMode(pin, OUTPUT);
}

void Laser::engage(){
  digitalWrite(Component::getPin(), HIGH);
  _isOn = true;
}

void Laser::disengage(){
  digitalWrite(Component::getPin(), LOW);
  _isOn = false;
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
