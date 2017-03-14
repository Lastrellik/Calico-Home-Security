/*
  Button.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Button.h"

Button::Button(int pin) : Component(pin, "BUTTON"){
  pinMode(pin, INPUT);
}

Button::Button(){
}

boolean Button::isPressed() {
  if(Properties::DEBUGGING_ACTIVE) Serial.println("Button has been pressed");
  boolean pressed = false;
  if (digitalRead(Component::getPin()) == HIGH) { // HIGH == Pressed
    pressed = true;
  }
  return pressed;
}
