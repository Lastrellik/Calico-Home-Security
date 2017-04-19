/**
  Calico Home Security System, Button.cpp
  Purpose: Determines if the Button has been pressed, turning it on or off

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#include "Arduino.h"
#include "Button.h"
/**
  Constructor for Button that sets the pin and the componentType
*/
Button::Button(int pin) : Component(pin, "BUTTON"){
  if(Properties::MODULE_PI) Serial.write("13150"); // 13150 = Log, Debug, Button object successfully created
  pinMode(pin, INPUT);
}
/**
  Base constructor
*/
Button::Button(){
}
/**
  @param pressed is initially set to false unless digitalRead reads Button's pin
    set to HIGH
  @return pressed is returned to be false or true unless Button's pin is set to
    HIGH
*/
boolean Button::isPressed() {
  boolean pressed = false;
  if (digitalRead(Component::getPin()) == HIGH) { // HIGH == Pressed
    pressed = true;
    if(Properties::MODULE_PI) Serial.write("13151"); // 13151 = Log, Debug, Button has been pressed
  }
  return pressed;
}
