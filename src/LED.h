/*
  LED.h - Simple Library for controlling an LED
*/
#ifndef LED_h
#define LED_h

#include "Arduino.h"
#include "Component.h"

class LED : public Component {
public:
  LED(int pin);
  LED();
  void on();
  void off();
  void toggle();
private:
  boolean _isOn;
};

#endif
