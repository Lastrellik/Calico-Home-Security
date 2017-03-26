/**
  Calico Home Security System, Buzzer.cpp
  Purpose: sets the tone of the Buzzer and if it's on and off

  @author Chris Nash, Jason Bruderer, David Tille, Tyler Jacobs
  @version To be Determined
*/
#include "Arduino.h"
#include "Buzzer.h"
/**
  Constructor sets the pin of the Buzzer and componentType to Buzzer
*/
Buzzer::Buzzer(int pin) : Component(pin, "BUZZER"){
  pinMode(pin, OUTPUT);
}
/**
  Base constructor
*/
Buzzer::Buzzer(){
}
/**
  Uses the int frequency to determine the pitch of the tone being passed to the
    tone function.
    This version of the soundTone Function does not use duration with the tone
    function.
*/
void Buzzer::soundTone(int frequency) {
  //Need to take a look at while we're using properies for a Serail.println
  //does not affected the tone
  if(Properties::DEBUGGING_ACTIVE) Serial.println("Buzzer has started sounding");
  tone(this->getPin(), frequency);
}
/**
  Uses the int frequency to determine the pitch of the tone being passed to the
    tone function.
    Uses the int millisOn to determine the duration of the tone being passed to
     the tone function.
*/
void Buzzer::soundTone(int frequency, int millisOn) {
  tone(this->getPin(), frequency, millisOn);
}
/**
  Sets what is to used in the soundTone that is to be used in soundTone that
    uses frequency and duration.
  Sets the frequncy to be of a higher tone
  @param frequency setter
  @param millisOn setter
*/
void Buzzer::soundAlarmHighTone(){
  int frequency = 2000;
  int millisOn = 333;
  this->soundTone(frequency, millisOn);
}
/**
  Sets what is to used in the soundTone that is to be used in soundTone that
    uses frequency and duration.
  Sets the frequncy to be of a lower tone
  @param frequency setter
  @param millisOn setter
*/
void Buzzer::soundAlarmLowTone(){
  int frequency = 1500;
  int millisOn = 333;
  this->soundTone(frequency, millisOn);
}
/**
  Sets the frequency tone to be what we consider Affirmative.
*/
void Buzzer::soundAffirmativeTone(){
  this->soundTone(800, 300);
}
/**
  Sets the frequency tone to be what we consider Negative
*/
void Buzzer::soundNegativeTone(){
  this->soundTone(400, 300);
}
/**
  Stops the tone of the Buzzer sounding
*/
void Buzzer::stopTone() {
  if(Properties::DEBUGGING_ACTIVE) Serial.println("Buzzer has stopped sounding");
  noTone(this->getPin());
}
