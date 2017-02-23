/*
  Button.h - Simple Library for making sounds on a Piezo Buzzer
*/
#ifndef Button_h
#define Button_h

#include "Arduino.h"
#include "Component.h"
class Button : public Component{
public:
  Button(int pin);
  Button();
  boolean isPressed();
};

#endif
