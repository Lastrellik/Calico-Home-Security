/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Photoresistor.h"

Photoresistor::Photoresistor(int pin) : Component(pin, "PHOTORESISTOR"){
  pinMode(pin, INPUT);
}

Photoresistor::Photoresistor(){
}

int Photoresistor::takeReading() {
  return analogRead(Component::getPin());
}
