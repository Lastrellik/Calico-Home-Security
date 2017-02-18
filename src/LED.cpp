/*
  LED.cpp - Simple Library for controlling an LED
*/
#include "Arduino.h"
#include "LED.h"

LED::LED(int pin) {
  pinMode(pin, OUTPUT);
  _pin = pin;
  _isOn = false;
  _componentType = "LED";
}

int LED::getPin() {
  return _pin;
}

String LED::getComponentType() {
  return _componentType;
}

void LED::on() {
  digitalWrite(_pin, HIGH);
  _isOn = true;
}

void LED::off() {
  digitalWrite(_pin, LOW);
  _isOn = false;
}

void LED::toggle() {
  if (_isOn) {
    LED::off();
  } else {
    LED::on();
  }
}
