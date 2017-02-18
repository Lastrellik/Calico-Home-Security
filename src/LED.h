/*
  LED.h - Simple Library for controlling an LED
*/
#ifndef LED_h
#define LED_h

#include "Arduino.h"

class LED {
public:
  LED(int pin);
  int getPin();
  String getComponentType();
  void on();
  void off();
  void toggle();
private:
  int _pin;
  String _componentType;
  boolean _isOn;
};

#endif
