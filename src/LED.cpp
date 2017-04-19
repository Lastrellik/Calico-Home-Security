/**
  Calico Home Security System, LED.cpp
  Purpose: LED creaties an LED ojbect and determines whether it should be on or
    off.

  @author Christopher Nash, Jason Bruderer, David Tille, Tyler Jacobs
  
*/
#include "Arduino.h"
#include "LED.h"
/**
  Constructor for LED that sets the pin and the componentType and sets the LED
    to be off initially
*/
LED::LED(int pin) : Component(pin, "LED") {
  if(Properties::MODULE_PI) Serial.write("13400"); // 13400 = Log, Debug, LED object successfully created
  pinMode(pin, OUTPUT);
  _isOn = false;
}
/**
  Constructor that sets the pin of the LED hardcoded to 13 and the componentType
    to LED and sets the LED to be off initially
*/
LED::LED() : Component(13, "LED"){
  if(Properties::MODULE_PI) Serial.write("13400"); // 13400 = Log, Debug, LED object successfully created
  pinMode(13, OUTPUT);
  _isOn = false;
}
/**
  Sets the LED to be turned to on
  @param _isOn sets it to be true
*/
void LED::on() {
  digitalWrite(this->getPin(), HIGH);
  _isOn = true;
}
/**
  Sets the LED to be turned to off
  @param _isOn sets it to be false
*/
void LED::off() {
  digitalWrite(this->getPin(), LOW);
  _isOn = false;
}
/**
  Flashes the LED on and off by using functions of off() and on()
    uses hardcoded delays
*/
void LED::flash(){
  this->on();
  delay(70);
  this->off();
  delay(50);
}
/**
  Flashes the LED on and off by using fucntions of off() and on()
    uses the variable millisOn for the delays
*/
void LED::flash(int millisOn){
  this->on();
  delay(millisOn);
  this->off();
  _isOn = false;
}
/**
  Toggles the LED on and off
  @param sets _isOn to off if on and on if off
*/
void LED::toggle() {
  if(Properties::MODULE_PI) Serial.write("13404"); // 13404 = Log, Debug, LED toggling
  if (_isOn) {
    LED::off();
  } else {
    LED::on();
  }
}
