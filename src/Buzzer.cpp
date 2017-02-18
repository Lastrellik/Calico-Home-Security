/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Buzzer.h"

Buzzer::Buzzer(int pin) {
  pinMode(pin, OUTPUT);
  _pin = pin;
  _componentType = "BUZZER";
}

int Buzzer::getPin() {
  return _pin;
}

String Buzzer::getComponentType() {
  return _componentType;
}

void Buzzer::soundTone(int frequency) {
  tone(_pin, frequency);
}

void Buzzer::soundTone(int frequency, int millisOn) {
  tone(_pin, frequency, millisOn);
}

void Buzzer::stopTone() {
  noTone(_pin);
}
