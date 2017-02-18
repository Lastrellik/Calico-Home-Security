/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Photoresistor.h"

Photoresistor::Photoresistor(int pin) {
  pinMode(pin, INPUT);
  _pin = pin;
  _componentType = "PHOTORESISTOR";
}

int Photoresistor::getPin() {
  return _pin;
}

String Photoresistor::getComponentType() {
  return _componentType;
}

int Photoresistor::takeReading() {
  return analogRead(_pin);
}
