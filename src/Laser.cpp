/*
  Laser.cpp - Simmple library for the laser module
*/
#include "Arduino.h"
#include "Laser.h"

Laser::Laser(int pin) : Component(pin, "LASER"){
  pinMode(pin, OUTPUT);
}

Laser::Laser(){
}

void Laser::on(){
  digitalWrite(Component::getPin(), HIGH);
  _isOn = true;
}

void Laser::off(){
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
