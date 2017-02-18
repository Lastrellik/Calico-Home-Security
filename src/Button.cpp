/*
  Button.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Button.h"

Button::Button(int pin) {
  pinMode(pin, INPUT);
  _pin = pin;
  _componentType = "BUTTON";
}

int Button::getPin() {
  return _pin;
}

boolean Button::isPressed() {
  boolean pressed = false;
  if (digitalRead(_pin) == HIGH) { // HIGH == Pressed
    pressed = true;
  }
  return pressed;
}
