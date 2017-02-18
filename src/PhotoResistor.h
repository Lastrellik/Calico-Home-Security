/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#ifndef Photoresistor_h
#define Photoresistor_h

#include "Arduino.h"

class Photoresistor {
public:
  Photoresistor(int pin);
  int getPin();
  String getComponentType();
  int takeReading();
private:
  int _pin;
  String _componentType;
};

#endif
