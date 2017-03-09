/*
  Photoresistor.h - Simple Library for reading from a Photoresistor
*/
#include "Arduino.h"
#include "Buzzer.h"

Buzzer::Buzzer(int pin) : Component(pin, "BUZZER"){
  pinMode(pin, OUTPUT);
}

Buzzer::Buzzer(){
}

void Buzzer::soundTone(int frequency) {
  Serial.println("Buzzer has started sounding");
  tone(this->getPin(), frequency);
}

void Buzzer::soundTone(int frequency, int millisOn) {
  tone(this->getPin(), frequency, millisOn);
}

void Buzzer::soundAlarmHighTone(){
  int frequency = 2000;
  int millisOn = 333;
  this->soundTone(frequency, millisOn);
}

void Buzzer::soundAlarmLowTone(){
  int frequency = 1500;
  int millisOn = 333;
  this->soundTone(frequency, millisOn);
}
void Buzzer::soundAffirmativeTone(){
  this->soundTone(800, 300);
}

void Buzzer::soundNegativeTone(){
  this->soundTone(400, 300);
}

void Buzzer::stopTone() {
  Serial.println("Buzzer has stopped sounding");
  noTone(this->getPin());
}
