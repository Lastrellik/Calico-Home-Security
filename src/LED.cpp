/*
  LED.cpp - Simple Library for controlling an LED
*/
#include "Arduino.h"
#include "LED.h"

LED::LED(int pin) : Component(pin, "LED") {
  pinMode(pin, OUTPUT);
  _isOn = false;
}
//default uses onboard LED
LED::LED() : Component(13, "LED"){
  pinMode(13, OUTPUT);
  _isOn = false;
}

void LED::on() {
  digitalWrite(Component::getPin(), HIGH);
  _isOn = true;
}

void LED::off() {
  digitalWrite(Component::getPin(), LOW);
  _isOn = false;
}

void LED::toggle() {
  if (_isOn) {
    LED::off();
  } else {
    LED::on();
  }
}
