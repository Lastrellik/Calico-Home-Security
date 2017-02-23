/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#ifndef Photoresistor_h
#define Photoresistor_h

#include "Arduino.h"
#include "Component.h"

class Photoresistor : public Component {
public:
  Photoresistor(int pin);
  int takeReading();
};

#endif
