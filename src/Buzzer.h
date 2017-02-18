/*
  Buzzer.h - Simple Library for making sounds on a Piezo Buzzer
*/
#ifndef Buzzer_h
#define Buzzer_h

#include "Arduino.h"

class Buzzer {
public:
  Buzzer(int pin);
  int getPin();
  String getComponentType();
  void soundTone(int frequency, int millisOn);
private:
  int _pin;
  String _componentType;
};

#endif
