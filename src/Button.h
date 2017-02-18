/*
  Button.h - Simple Library for making sounds on a Piezo Buzzer
*/
#ifndef Button_h
#define Button_h

#include "Arduino.h"

class Button {
public:
  Button(int pin);
  int getPin();
  String getComponentType();
  boolean isPressed();
private:
  int _pin;
  String _componentType;
};

#endif
