/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Buzzer.h"

Buzzer::Buzzer(int pin) : Component(pin, "BUZZER"){
  pinMode(pin, OUTPUT);
}

void Buzzer::soundTone(int frequency) {
  tone(Component::getPin(), frequency);
}

void Buzzer::soundTone(int frequency, int millisOn) {
  tone(Component::getPin(), frequency, millisOn);
}

void Buzzer::stopTone() {
  noTone(Component::getPin());
}
